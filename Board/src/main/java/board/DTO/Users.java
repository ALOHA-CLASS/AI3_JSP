package board.DTO;

import java.util.Date;

import com.alohaclass.jdbc.annotation.Column;
import com.alohaclass.jdbc.annotation.Pk;
import com.alohaclass.jdbc.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
	@Pk
	private int no;
	private String id;
	private String username;
	private String password;
	@Column(exist = false)
	private String passwordConfirm;
	private String name;
	private String email;
	private Date createdAt;
	private Date updatedAt;
}