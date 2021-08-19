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


// 어떤 구현체를 사용할지 여기서 설정  DIP, OCP 위반 문제해결
// 이렇게 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전이라 한다.
// 즉, AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 IoC 컨테이너 또는 DI 컨테이너라 한다.
public class AppConfig {

    // 생성자 주입  => 의존관계를 마치 외부에서 주입해주는것 같다고해서 (Dependency Injection)DI, 우리말로 의존관계 주입이라고 한다.
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
        // 이 메서드만 바꾸면 다른것은 아무것도 바꿀게 없음 . 다른건 전혀 손댈 필요가 없는 의존관계 주입 DI
        // return new FixDiscountPolicy();
    }
}
