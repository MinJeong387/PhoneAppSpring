package himedia.phoneappspring.service;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private String uploadDir;

    public String uploadProfilePicture(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(uploadDir, fileName);
        File filePath = new File(uploadDir, fileName); 
        
        System.out.println("uploadDir: " + uploadDir);
        System.out.println("fileName: " + fileName);
        System.out.println("filePath: " + filePath);

        // 📌 업로드 폴더가 없으면 생성
        Files.createDirectories(Paths.get(uploadDir));
        
        System.out.println("파일 경로:"+  filePath);
        Files.copy(file.getInputStream(), filePath.toPath(), StandardCopyOption.REPLACE_EXISTING);

        PhotoVo photo = new PhotoVo();
        photo.setFileName(fileName);
        photo.setFilePath(filePath.getAbsolutePath());	//filePath.toString()
        photo.setUploadDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        photoMapper.insertOrUpdatePhoto(photo);  

        return filePath.toString();
    }
    
	//	프로필 사진 조회
	public PhotoVo getPhotoById(Integer id) {
        return photoMapper.getPhotoById(id);
    }
}
