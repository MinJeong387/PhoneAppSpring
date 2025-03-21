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
    
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//    public String uploadProfilePicture(MultipartFile file, Integer userinfoId) throws IOException {
//        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(uploadDir, fileName);
////        File filePath = new File(uploadDir, fileName); 
//        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//        
//        System.out.println("uploadDir: " + uploadDir);
//        System.out.println("fileName: " + fileName);
//        System.out.println("filePath: " + filePath);
//
//        PhotoVo photo = new PhotoVo();
//        photo.setFileName(fileName);
//        photo.setFilePath(filePath.toString());
//        photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        photo.setUserinfoId(userinfoId);
//        
//        // 📌 업로드 폴더가 없으면 생성
////        Files.createDirectories(Paths.get(uploadDir));
//        
////        System.out.println("파일 경로:"+  filePath);
////        Files.copy(file.getInputStream(), filePath.toPath(), StandardCopyOption.REPLACE_EXISTING);
//
////        PhotoVo photo = new PhotoVo();
////        photo.setFileName(fileName);
////        photo.setFilePath(filePath.getAbsolutePath());	//filePath.toString()
////        photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//
//        photoMapper.insertOrUpdatePhoto(photo);  
//
//        return filePath.toString();
//    }
	
	@Value("${file.upload-dir}")
	private String uploadDir;  // 파일 업로드 경로를 application.properties 파일에서 읽어옵니다.

	public String uploadProfilePicture(MultipartFile file, Integer userinfoId) throws IOException {
	    // 업로드할 파일의 이름을 UUID로 설정하여 중복을 피함
	    String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
	    
	    // 업로드할 파일 경로를 설정 (uploadDir + fileName)
	    Path filePath = Paths.get(uploadDir, fileName);
	    
	    // 📌 업로드 폴더가 없으면 생성
	    Files.createDirectories(Paths.get(uploadDir));  // 업로드 디렉토리가 없으면 생성

	    // 파일을 서버에 저장
	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	    // 로그로 파일 경로 확인
	    System.out.println("uploadDir: " + uploadDir);
	    System.out.println("fileName: " + fileName);
	    System.out.println("filePath: " + filePath);

	    // 업로드된 파일 정보 저장
	    PhotoVo photo = new PhotoVo();
	    photo.setFileName(fileName);
	    photo.setFilePath(filePath.toString()); // 실제 파일 경로 (서버 내 저장 경로)
	    photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	    photo.setUserinfoId(userinfoId);

	    // 📌 데이터베이스에 파일 정보를 저장
	    photoMapper.insertOrUpdatePhoto(photo);  // filePath는 데이터베이스에 저장

	    // 클라이언트에 반환할 URL을 생성하여 반환
	    return "/api/phoneApp/photo/" + fileName;  // 클라이언트에서 접근할 수 있는 URL 경로
	}


    
	//	프로필 사진 조회
	public PhotoVo getPhotoById(Integer id) {
        return photoMapper.getPhotoById(id);
    }
}
