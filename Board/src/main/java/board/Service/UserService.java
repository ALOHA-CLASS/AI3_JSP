package board.Service;

import board.DTO.Users;

public interface UserService {
	// 회원가입
	public int join(Users user);
	
	// 아이디 중복 확인
	public boolean idCheck(String username);
}
