package board.Service;

import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import board.DAO.UserDAO;
import board.DTO.Users;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO = new UserDAO();

	@Override
	public int join(Users user) {
		try {
			// 비밀번호 암호화
			String password = user.getPassword();
			String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(encodedPassword);
			// 회원 등록
			int result = userDAO.insert(user);
			
			return result;		// 등록 성공
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;				// 등록 실패
	}

	@Override
	public boolean idCheck(String username) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		
		Object user = null;
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

	@Override
	public boolean login(Users user) {
		String username = user.getUsername();
		String password = user.getPassword();
		
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		
		Users joinedUser = null;
		try {
			joinedUser = userDAO.selectBy(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 아이디가 존재 하지 않는 경우
		if( joinedUser == null ) 
			return false;
		// 비밀번호 일치 여부 확인
		String joinedPasswword = joinedUser.getPassword();
		boolean result = BCrypt.checkpw(password, joinedPasswword);
		return result;
	}

	@Override
	public Users selectByUsername(String username) {
		Map<String, Object> map = new HashMap<>();
		map.put("username", username);
		Users user = null;
		try {
			user = userDAO.selectBy(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}