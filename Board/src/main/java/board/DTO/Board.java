package board.DTO;

import java.util.Date;

import com.alohaclass.jdbc.annotation.Column;
import com.alohaclass.jdbc.annotation.Pk;
import com.alohaclass.jdbc.annotation.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table("board")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {
	@Pk
	private int no;
	private String id;
	private String title;
	private int userNo;
	private String content;
	private Date createdAt;
	private Date updatedAt;
	
	@Column(exist = false)
	private Users user;
}