package js.daangnclone.service;

import js.daangnclone.domain.member.Member;

public interface MemberService {

    Member save(Member member);
    Member findMember(Long id);
    void updateMemberCertifyYn(Long id);
}
