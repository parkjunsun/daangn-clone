package js.daangnclone.domain.board;

import js.daangnclone.cmn.category.Category;
import js.daangnclone.domain.member.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Override
//    @EntityGraph(attributePaths = {"member"})
//    List<Board> findAll(Sort sort);

    @Query("select b from Board b order by b.createdAt desc")
    Page<Board> findAll(Pageable pageable);

    @Query("select b from Board b where b.boardStatus = :boardStatus order by b.createdAt desc")
    Page<Board> findByBoardStatus(Pageable pageable, @Param("boardStatus") BoardStatus boardStatus);

    @Query("select b from Board b where b.id = :id")
    Optional<Board> findBoard(@Param("id") Long id);

    List<Board> findByMemberAndBoardStatusOrderByCreatedAt(Member member, BoardStatus boardStatus);

    long countByMemberAndBoardStatus(Member member, BoardStatus boardStatus);

    List<Board> findByPurchaser(Member purchaser);

    Optional<Board> findByTitle(String title);

    Page<Board> findByTitleContains(Pageable pageable, String searchWord);

    Page<Board> findByCategory(Pageable pageable, Category category);

}
