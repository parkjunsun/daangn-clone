package js.daangnclone.service.board;

import js.daangnclone.Exception.CustomException;
import js.daangnclone.cmn.category.Category;
import js.daangnclone.cmn.DateUtil;
import js.daangnclone.domain.attention.AttentionRepository;
import js.daangnclone.domain.board.Board;
import js.daangnclone.domain.board.BoardRepository;
import js.daangnclone.domain.board.BoardStatus;
import js.daangnclone.domain.board.event.BoardCreatedEvent;
import js.daangnclone.domain.chat.ChatRepository;
import js.daangnclone.domain.member.Member;
import js.daangnclone.web.board.dto.BoardForm;
import js.daangnclone.web.board.dto.BoardMultiResponse;
import js.daangnclone.web.board.dto.BoardSingleResponse;
import js.daangnclone.web.purchase.dto.PurchaseResponse;
import js.daangnclone.web.sale.dto.SaleResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static js.daangnclone.Exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final AttentionRepository attentionRepository;
    private final ChatRepository chatRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    @Transactional
    public Board registerItem(BoardForm boardForm, Member member) {

        Board board = Board.builder()
                .title(boardForm.getTitle())
                .category(Category.of(boardForm.getCategory()))
                .content(boardForm.getContent().replace("\r\n", "<br>"))
                .detail(boardForm.getDetail())
                .price(boardForm.getPrice())
                .boardStatus(BoardStatus.SALE_ON)
                .member(member)
                .build();

        Board newBoard = boardRepository.save(board);
        eventPublisher.publishEvent(new BoardCreatedEvent(newBoard));
        return newBoard;
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
                        .boardStatus(board.getBoardStatus())
                        .attentionCnt(attentionRepository.countByBoard(board))
                        .chatRoomCnt(chatRepository.countByBoard(board))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public BoardSingleResponse inquireBoard(Long id) {
        Board findBoard = boardRepository.findBoard(id).orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));

        return BoardSingleResponse.builder()
                .id(findBoard.getId())
                .title(findBoard.getTitle())
                .image(findBoard.getImage())
                .price(findBoard.getPrice())
                .content(findBoard.getContent())
                .detail(findBoard.getDetail())
                .category(findBoard.getCategory().getCategoryName())
                .diffCreatedAt(DateUtil.diffDate(findBoard.getCreatedAt()))
                .memberId(findBoard.getMember().getId())
                .nickname(findBoard.getMember().getNickname())
                .city(findBoard.getMember().getArea().getAreaName())
                .view(findBoard.getView())
                .boardStatus(findBoard.getBoardStatus())
                .build();
    }

    @Override
    @Transactional
    public void updateView(Long id) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
        findBoard.addView();
    }

    @Override
    @Transactional
    public Board updateBoardStatus(Long id, BoardStatus boardStatus) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
        findBoard.setBoardStatus(boardStatus);

        return findBoard;
    }

    @Override
    public Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
    }


    @Override
    public List<SaleResponse> inquireSaleList(Member member, BoardStatus boardStatus) {
        List<Board> saleList = boardRepository.findByMemberAndBoardStatus(member, boardStatus);

        return saleList.stream()
                .map(board -> SaleResponse.builder()
                        .boardId(board.getId())
                        .boardTitle(board.getTitle())
                        .boardImage(board.getImage())
                        .boardPrice(board.getPrice())
                        .boardStatus(board.getBoardStatus())
                        .link("/board/" + board.getId())
                        .area(board.getMember().getArea())
                        .purchaser(board.getPurchaser())
                        .diffCreatedAt(DateUtil.diffDate(board.getCreatedAt()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public long getCount(Member member, BoardStatus boardStatus) {
        return boardRepository.countByMemberAndBoardStatus(member, boardStatus);
    }

    @Override
    public SaleResponse inquireSale(Long boardId) {
        Board sale = boardRepository.findBoard(boardId).orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));

        return SaleResponse.builder()
                .boardId(sale.getId())
                .boardTitle(sale.getTitle())
                .boardImage(sale.getImage())
                .boardPrice(sale.getPrice())
                .boardStatus(sale.getBoardStatus())
                .area(sale.getMember().getArea())
                .purchaser(sale.getPurchaser())
                .diffCreatedAt(DateUtil.diffDate(sale.getCreatedAt()))
                .build();
    }

    @Override
    @Transactional
    public void updatePurchaser(Long boardId, Member purchaser) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new CustomException(BOARD_NOT_FOUND));
        findBoard.setPurchaser(purchaser);
    }

    @Override
    public List<PurchaseResponse> inquirePurchaseList(Member seller) {
        List<Board> purchaseList = boardRepository.findByPurchaser(seller);
        return purchaseList.stream()
                .map(board -> PurchaseResponse.builder()
                        .board(board)
                        .build())
                .collect(Collectors.toList());
    }
}
