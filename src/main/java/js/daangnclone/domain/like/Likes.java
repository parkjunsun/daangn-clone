package js.daangnclone.domain.like;

import js.daangnclone.domain.TimeEntity;
import js.daangnclone.domain.comment.Comment;
import js.daangnclone.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Entity
@Table(name = "likes")
public class Likes extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public void setMember(Member member) {
        this.member = member;
        member.getLikeList().add(this);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
        comment.getLikeList().add(this);
    }

}
