package board.DTO;

import java.util.Date;
import com.alohaclass.jdbc.annotation.*;
import lombok.*;


@Table("persistence_logins")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersistenceLogins {
	@Pk
	private int no;
	private String id;
	private String username;
	private String token;
	private Date expiryDate; 
	private Date createdAt; 
	private Date updatedAt; 
}
