package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: 사용자 A 10000원 주문
        statefulService1.order("userA", 10000);
        // ThreadB: 사용자 B 10000원 주문

        statefulService1.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        System.out.println("price = " + price);

        // 사용자 A는 10000원만 주문했지만 조회하는 사이에 사용자 B가 20000원을 주문해서
        // 사용자 A는 20000원을 내야하는 상황이 왔고 엄청난 문제가 발생하게 된다.
        // 따라서 공유필드는 조심해야한다! 스프링 빈은 항상 무상태(stateless)로 설계해야한다.!!!
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}
