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
public class PhotoVo {

	//	id
	private Integer id;
	//	파일이름
	private String fileName;
	//	파일경로
	private String filePath;
	//	업로드 날짜
	private String uploadDate;
	//	user id
	private Integer userinfoId;

	
}