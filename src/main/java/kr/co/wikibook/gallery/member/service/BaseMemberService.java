package kr.co.wikibook.gallery.member.service;

import kr.co.wikibook.gallery.common.util.HashUtils;
import kr.co.wikibook.gallery.member.entity.Member;
import kr.co.wikibook.gallery.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseMemberService implements MemberService{
    private final MemberRepository memberRepository;
    @Override
    public void save(String name, String loginId, String loginPw) {
        // 솔트 생성
        String loginPwSalt = HashUtils.generateSalt(16);

        // 입력 패스워드에 솔트를 적용
        String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

        Member member = new Member(name, loginId, loginPwSalted ,loginPwSalt);
        memberRepository.save(member);
    }

    @Override
    public Member find(String loginId, String loginPw) {
        // 로그인 아이디로 회원 조회
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);

        // 회원 데이터가 있으면
        if(memberOptional.isPresent()){
            Member member = memberOptional.get();

            // 솔트 조회
            String loginPwSalt = memberOptional.get().getLoginPwSalt();

            // 입력 패스워드에 솔트를 적용
            String loginPwSalted = HashUtils.generateHash(loginPw, loginPwSalt);

            // 저장된 회원 로그인 패스워드와 일지 여부 확인
            if(member.getLoginPw().equals(loginPwSalted)){
                return member;
            }
        }

        return null;
    }

    @Override
    public Member find(String loginId) {
        // 회원 데이터가 있으면 해당 값 리턴
        return memberRepository.findByLoginId(loginId).orElse(null);
    }
}
