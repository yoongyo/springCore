package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 어떤 구현체를 사용할지 여기서 설정  DIP, OCP 위반 문제해결
// 이렇게 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전이라 한다.
// 즉, AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너라 한다.
@Configuration   // Spring Container 에 등록시킨다.
public class AppConfig {

    // 생성자 주입  => 의존관계를 마치 외부에서 주입해주는것 같다고해서 (Dependency Injection)DI, 우리말로 의존관계 주입이라고 한다.
    @Bean   // Spring Container 에 등록시킨다.
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        // 이 메서드만 바꾸면 다른것은 아무것도 바꿀게 없음 . 다른건 전혀 손댈 필요가 없는 의존관계 주입 DI
        // return new FixDiscountPolicy();
    }
}

// ApplicationContext 를 스프링 컨테이너라고 한다.
// 기존에는 개발자가 'AppConfig' 를 사용해서 직접 객체를 생성하고 DI를 했지만, 이제부터는 스프링 컨테이너를 통해서 사용한다.
// 스프링 컨테이너는 '@Configuration' 이 붙은 'AppConfig' 를 설정 정보로 사용한다.
// 여기서 '@Bean' 이라 적힌 메서드르 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다. 이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
// 스프링 빈은 '@Bean' 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다. ('memberService', 'orderService')
// 이전에는 개발자가 필요한 객체를 'AppConfig' 를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 스프링 빈를 찾아야한다.
// 스프링빈은 'applicationContext.getBean()' 메서드를 사용해서 찾을 수 있다.
// 기존에는 개발자가 직접 자바코드로 모든것을 했다면 이제부터는 스프링 컨테이너에 객체를 스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.

// 스프링 컨테이너가 관리해줌으로서 어떤 장점이 있을까? -> 어마어마한 장점이 있다. 추후 공개

