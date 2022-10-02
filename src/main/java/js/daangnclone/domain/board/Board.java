package js.daangnclone.domain.board;

import js.daangnclone.cmn.Category;
import js.daangnclone.domain.TimeEntity;
import js.daangnclone.domain.attention.Attention;
import js.daangnclone.domain.comment.Comment;
import js.daangnclone.domain.member.Member;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends TimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;
    private int price;
    private String image;
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String content;
    private String detail;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Attention> attentionList = new ArrayList<>();

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> commentList = new ArrayList<>();

    public void setMember(Member member) {
        this.member = member;
        member.getBoardList().add(this);
    }

    public void addView() {
        this.view += 1;
    }

    @Builder
    public Board (String title, Category category, int price, String image, String content, String detail, int view, Member member) {
        this.title = title;
        this.category = category;
        this.price = price;
        this.image = image;
        this.content = content;
        this.detail = detail;
        this.view = view;
        this.member = member;
    }
}
