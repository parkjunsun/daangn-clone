package js.daangnclone.service.member;

import js.daangnclone.Exception.CustomException;
import js.daangnclone.domain.area.AreaRepository;
import js.daangnclone.domain.member.Member;
import js.daangnclone.domain.member.MemberRepository;
import js.daangnclone.domain.member.MemberRole;
import js.daangnclone.web.member.dto.MemberDetailsForm;
import js.daangnclone.web.member.dto.MemberForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static js.daangnclone.Exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final AreaRepository areaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Member save(MemberForm memberForm) {
        Member member = Member.builder()
                .provider("daanngn")
                .username(memberForm.getUsername())
                .nickname(memberForm.getNickname())
                .password(passwordEncoder.encode(memberForm.getPassword()))
                .email(memberForm.getEmail())
                .area(areaRepository.findById(memberForm.getCity()).get())
                .memberRole(MemberRole.ROLE_USER)
                .certifyYn("N")
                .build();
        return memberRepository.save(member);

    }

    @Override
    public Member findMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

    @Transactional
    @Override
    public void updateMemberCertifyYn(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        findMember.setCertifyYn("Y");
    }

    @Override
    public void validateCertifyLocation(Long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(USER_NOT_FOUND));
        String certifyYn = findMember.getCertifyYn();

        if (certifyYn.equals("N")) {
            throw new CustomException(NOT_CERTIFIED_LOCATION);
        }
    }

    @Override
    public String validateDuplicateUsername(String username) {
        Optional<Member> findMember = memberRepository.findByUsername(username);

        if (findMember.isPresent()) {
            return "N";
        } else {
            return "Y";
        }
    }

    @Override
    public String validateDuplicateNickname(String nickname) {
        Optional<Member> findMember = memberRepository.findByNickname(nickname);

        if (findMember.isPresent()) {
            return "N";
        } else {
            return "Y";
        }
    }

    @Override
    @Transactional
    public void addDetails(Long id, MemberDetailsForm detailsForm) {
        Member findMember = memberRepository.findById(id).orElse(null);

        findMember.setNickname(detailsForm.getNickname());
        findMember.setArea(areaRepository.findById(detailsForm.getCity()).get());
    }
}
