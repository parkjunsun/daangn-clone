package js.daangnclone.domain.board;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    @EntityGraph(attributePaths = {"member"})
    List<Board> findAll(Sort sort);

    @Override
    @EntityGraph(attributePaths = {"member"})
    Optional<Board> findById(Long id);

}
