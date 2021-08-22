package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor // final 이 붙은 필드를 모아서 생성자를 자동으로 만들어 준다.
public class OrderServiceImpl implements OrderService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP 위반!
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // final 로 되어있으면 기본적으로 생성자를 통해서 할당
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//     RequiredArgsConstructor 가 기보적으로 생성자를 만들어준다.
//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
