package hello.core.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음 (DIP 위반)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();


    private final MemberRepository memberRepository;

    @Autowired  // 자동 의존관계 주입 마치 (ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
