package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음 (DIP 위반)
    // private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;
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
}
