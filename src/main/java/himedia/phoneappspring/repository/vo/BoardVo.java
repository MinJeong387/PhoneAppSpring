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
public class BoardVo {

	//	ID
	Integer id;
	//	유저ID
	Integer userId;
	//	제목
	String 	title;
	//	계획
	String 	plan;
	//	소개
	String 	introduction;
	 
}