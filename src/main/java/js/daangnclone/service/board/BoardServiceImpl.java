package js.daangnclone.service.board;

import js.daangnclone.cmn.DateUtil;
import js.daangnclone.domain.area.Area;
import js.daangnclone.domain.area.AreaRepository;
import js.daangnclone.domain.board.Board;
import js.daangnclone.domain.board.BoardRepository;
import js.daangnclone.domain.category.Category;
import js.daangnclone.domain.category.CategoryRepository;
import js.daangnclone.domain.member.Member;
import js.daangnclone.web.board.dto.BoardForm;
import js.daangnclone.web.board.dto.BoardMultiResponse;
import js.daangnclone.web.board.dto.BoardSingleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final AreaRepository areaRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Board registerItem(BoardForm boardForm, Member member) {

        Board board = Board.builder()
                .title(boardForm.getTitle())
                .category(categoryRepository.findById(boardForm.getCategory()).get())
                .content(boardForm.getContent().replace("\r\n", "<br>"))
                .detail(boardForm.getDetail())
                .price(boardForm.getPrice())
                .member(member)
                .build();

        return boardRepository.save(board);
    }

    @Override
    public List<BoardMultiResponse> inquireAllBoardList() {
        List<Board> findBoardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));

        return findBoardList.stream()
                .map(board -> BoardMultiResponse.builder()
                        .id(board.getId())
                        .title(board.getTitle())
                        .image(board.getImage())
                        .price(board.getPrice())
                        .content(board.getContent())
                        .detail(board.getDetail())
                        .category(board.getCategory().getCategoryName())
                        .diffCreatedAt(DateUtil.diffDate(board.getCreatedAt()))
                        .nickname(board.getMember().getNickname())
                        .city(board.getMember().getArea().getAreaName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public BoardSingleResponse inquireBoard(Long id) {
        Board findBoard = boardRepository.findBoard(id).orElse(null);

        return BoardSingleResponse.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .image(findBoard.getImage())
                .price(findBoard.getPrice())
                .content(findBoard.getContent())
                .detail(findBoard.getDetail())
                .category(findBoard.getCategory().getCategoryName())
                .diffCreatedAt(DateUtil.diffDate(findBoard.getCreatedAt()))
                .nickname(findBoard.getMember().getNickname())
                .city(findBoard.getMember().getArea().getAreaName())
                .view(findBoard.getView())
                .commentList(findBoard.getCommentList())
                .build();
    }

    @Override
    @Transactional
    public void updateView(Long id) {
        Board findBoard = boardRepository.findById(id).orElse(null);
        findBoard.addView();
    }

    @Override
    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElse(null);
    }
}
