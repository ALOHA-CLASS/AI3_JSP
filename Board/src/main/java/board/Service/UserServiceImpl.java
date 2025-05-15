package board.Service;

import java.util.HashMap;
import java.util.Map;

import board.DAO.UserDAO;
import board.DTO.Users;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO = new UserDAO();

	@Override
	public int join(Users user) {
		try {
			int result = userDAO.insert(user);
			return result;		// 등록 성공
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;				// 등록 실패
	}

	@Override
	public boolean idCheck(String username) {
		Map<Object, Object> map = new HashMap<>();
		map.put("username", username);
		
		Users user = null;
		try {
			user = userDAO.selectBy(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 아이디 중복X
		if( user == null ) {
			return false;
		}
		return true;
	}

}









