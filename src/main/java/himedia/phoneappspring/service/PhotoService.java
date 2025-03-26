package himedia.phoneappspring.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import himedia.phoneappspring.mappers.PhotoMapper;
import himedia.phoneappspring.repository.vo.PhotoVo;



@Service
public class PhotoService {
	@Autowired
    private PhotoMapper photoMapper;
    
	
	@Value("${file.upload-dir}")
	private String uploadDir;  // 파일 업로드 경로를 application.properties 파일에서 읽어옵니다.

	
    public String uploadPhoto(MultipartFile file, Integer userinfoId) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        PhotoVo photo = new PhotoVo();
        photo.setFileName(fileName);
        photo.setFilePath("/api/phoneApp/photo/" + fileName);
        photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        photo.setUserinfoId(userinfoId);

        photoMapper.insertOrUpdatePhoto(photo);  
//        System.out.println(">>>> userinfoId: " + userinfoId);
        return filePath.toString();
    }
	
	

	//	프로필 사진 조회
	public PhotoVo getPhotoByUserinfoId(Integer id) {
        return photoMapper.getPhotoByUserinfoId(id);
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
    // 프로필 사진 업로드
//  public  String uploadPhoto(MultipartFile file, Integer userinfoId) {
//      try {
//
//          // 📌 업로드 폴더가 없으면 생성
//          File uploadFolder = new File(uploadDir);
//          if (!uploadFolder.exists()) {
//              uploadFolder.mkdirs();  // 디렉터리 자동 생성
//          }
//          
//          // 업로드 경로 설정
//          String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//          String filePath = Paths.get(uploadDir, fileName).toString();
//          System.out.println(filePath);
//
//          // 로컬 저장 (AWS S3 연동 가능)
//          File destFile = new File(filePath);
//          file.transferTo(destFile);
//
//          // 업로드 날짜 설정
//          String uploadDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//
//          // PhotoVo 생성
//          PhotoVo photo = new PhotoVo(null, fileName, filePath, uploadDate, userinfoId);
//
//          // DB 저장 (중복 시 업데이트)
//          photoMapper.insertOrUpdatePhoto(photo);
//
//          return ResponseEntity.ok("파일 업로드 성공: " + fileName);
//      } catch (IOException e) {
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패: " + e.getMessage());
//      }
//  }
	
	
	
    // 프로필 사진 조회
//  public ResponseEntity<?> getPhotoById(Integer id) {
//      try {
//          PhotoVo photo = photoMapper.getPhotoById(id);
//
//          if (photo == null) {
//              return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 ID의 사진을 찾을 수 없습니다.");
//          }
//
//          return ResponseEntity.ok(photo);
//      } catch (Exception e) {
//          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("사진 조회 중 오류 발생: " + e.getMessage());
//      }
//  }	
	
	
	
	
	
	
	
	
//	public String uploadProfilePicture(MultipartFile file, Integer userinfoId) throws IOException {
//	    // 업로드할 파일의 이름을 UUID로 설정하여 중복을 피함
//	    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//	    
//	    // 업로드할 파일 경로를 설정 (uploadDir + fileName)
//	    Path filePath = Paths.get(uploadDir, fileName);
//	    
//	    // 📌 업로드 폴더가 없으면 생성
//	    Files.createDirectories(Paths.get(uploadDir));  // 업로드 디렉토리가 없으면 생성
//
//	    // 파일을 서버에 저장
//	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//	    // 로그로 파일 경로 확인
//	    System.out.println("uploadDir: " + uploadDir);
//	    System.out.println("fileName: " + fileName);
//	    System.out.println("filePath: " + filePath);
//
//	    // 업로드된 파일 정보 저장
//	    PhotoVo photo = new PhotoVo();
//	    photo.setFileName(fileName);
//	    photo.setFilePath(filePath.toString()); // 실제 파일 경로 (서버 내 저장 경로)
//	    photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//	    photo.setUserinfoId(userinfoId);
//
//	    // 📌 데이터베이스에 파일 정보를 저장
//	    photoMapper.insertOrUpdatePhoto(photo);  // filePath는 데이터베이스에 저장
//
//	    // 클라이언트에 반환할 URL을 생성하여 반환
//	    return "/api/phoneApp/photo/" + fileName;  // 클라이언트에서 접근할 수 있는 URL 경로
//	}
//
//
//    
//	//	프로필 사진 조회
//	public PhotoVo getPhotoById(Integer id) {
//        return photoMapper.getPhotoById(id);
//    }
}
