package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    @Transactional  //0714
    public String join(Member member) {
        validateDuplicateMember(member);    //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

//    public Member login(String id, String pwd) {
//        return memberRepository.findById(id)
//                .filter(m -> m.getPwd().equals(pwd))
//                .orElse(null);
//    }
    public Optional<Member> login(String id, String pwd) {
        return memberRepository.findById(id)
                .filter(m -> m.getPwd().equals(pwd));
    }
//    public Optional<Member> login(String id, String pwd) {
//        Optional<Member> found_member = memberRepository.findById(id);
//
//        if(found_member==null){
//            return null;
//        }
//
//        else
//            return found_member;
//    }

//    private final MemberRepository memberRepository;
//
//    /**
//     * @return null 로그인 실패
//     */
//    public Member login(String loginId, String password) {
//        return memberRepository.findByLoginId(loginId)
//                .filter(m -> m.getPassword().equals(password))
//                .orElse(null);
//    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }

}

