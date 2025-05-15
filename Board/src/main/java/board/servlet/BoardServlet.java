package board.servlet;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import board.DTO.Board;
import board.DTO.Users;
import board.Service.BoardService;
import board.Service.BoardServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private BoardService boardService = new BoardServiceImpl();
	/**
	 * [GET]
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = request.getPathInfo();
		String page = "";
		
		// 게시글 목록
		if( path.equals("/list") || path.equals("/list.jsp") ) {
			List<Board> boardList = boardService.list();
			request.setAttribute("boardList", boardList);
			page = "/page/board/list.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		
		// 게시글 조회
		if( path.equals("/read") || path.equals("/read.jsp") ) {
			String id = request.getParameter("id");
			Board board = boardService.selectById(id);
			request.setAttribute("board", board);
			page = "/page/board/read.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
		
		// 게시글 등록
		if( path.equals("/create") || path.equals("/create.jsp") ) {
			page = "/page/board/create.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * [POST]
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String root = request.getContextPath();
		String path = request.getPathInfo();
		String page = "";
		// 게시글 등록
		if( path.equals("/create") ) {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userNo = ((Users) request.getSession().getAttribute("loginUser")).getNo();
			Board board = Board.builder()
									     .id(UUID.randomUUID().toString())
										 .title(title)
										 .content(content)
										 .userNo(userNo)
										 .build();
			
			Board createdBoard = boardService.insert(board);
			if( createdBoard != null ) {
				response.sendRedirect(root + "/board/list");
			}
			else {
				response.sendRedirect(root + "/board/create.jsp?error=true");
			}
		}
	}

}










