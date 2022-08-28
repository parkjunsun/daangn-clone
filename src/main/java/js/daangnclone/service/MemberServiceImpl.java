package js.daangnclone.service;

import js.daangnclone.domain.member.Member;
import js.daangnclone.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 유저정보가 없습니다."));
    }

    @Transactional
    @Override
    public void updateMemberCertifyYn(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("해당 유저정보가 없습니다."));
        findMember.setCertifyYn("Y");
    }
}
