package board.Service;

import java.util.List;

import board.DTO.Board;

public interface BoardService {
	// 게시글 목록
	List<Board> list();
	// 게시글 조회
	Board select(int no);
	Board selectById(String id);
	// 게시글 등록
	Board insert(Board board);
	// 게시글 수정
	boolean update(Board board);
	// 게시글 삭제
	boolean delete(Board board);
}
