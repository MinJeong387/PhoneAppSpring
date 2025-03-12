package himedia.phoneappspring.repository.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PhoneAppVo {

	
	//	ID
	private Integer id;
	
	//	이름
	private String name;
	
	//	전화번호
	private String phoneNumber;
	
	//	이메일
	private String email;
	
	//	닉네임
	private String nickname;

	//	메모
	private String memo;

	
	public PhoneAppVo(String name, String phoneNumber, String email, String nickname, String memo) {
		super();
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.nickname = nickname;
		this.memo = memo;
	}
	
	
	
	 
}