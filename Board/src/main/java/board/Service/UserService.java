package board.Service;

import board.DTO.Users;

public interface UserService {
	// 회원가입
	public int join(Users user);
	
	// 아이디 중복 확인
	public boolean idCheck(String username);
	
	// 로그인
	public boolean login(Users user);
	
	// 아이디로 회원 조회
	public Users selectByUsername(String username);
}











