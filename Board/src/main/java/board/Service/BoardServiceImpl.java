package board.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.DAO.BoardDAO;
import board.DAO.UserDAO;
import board.DTO.Board;
import board.DTO.Users;

public class BoardServiceImpl implements BoardService {
	
	private BoardDAO boardDAO = new BoardDAO();
	private UserDAO userDAO = new UserDAO();

	@Override
	public List<Board> list() {
		List<Board> list = null;
		try {
			list = boardDAO.list();
			for (Board board : list) {
				int userNo = board.getUserNo();
				Users user = userDAO.select(userNo);
				board.setUser(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Board select(int no) {
		Board board = null;
		try {
			board = boardDAO.select(no);
			int userNo = board.getUserNo();
			Users user = userDAO.select(userNo);
			board.setUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}

	@Override
	public Board insert(Board board) {
		int result = 0;
		try {
			result = boardDAO.insert(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( result > 0 ) {
			return board;
		}
		return null;
	}

	@Override
	public boolean update(Board board) {
		int result = 0;
		try {
			result = boardDAO.update(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public boolean delete(Board board) {
		int result = 0;
		String id = board.getId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		try {
			result = boardDAO.deleteBy(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result > 0;
	}

	@Override
	public Board selectById(String id) {
		Board board = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("id", id);
			board = boardDAO.selectBy(map);
			int userNo = board.getUserNo();
			Users user = userDAO.select(userNo);
			board.setUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}

	@Override
	public boolean updateById(Board board) {
		int result = 0;
		String id = board.getId();
		Board originBoard = selectById(id);
		int no = originBoard.getNo();
		board.setNo(no);
		try {
			result = boardDAO.update(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result > 0;
	}

}










