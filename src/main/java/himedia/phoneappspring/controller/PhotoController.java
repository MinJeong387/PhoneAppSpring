package himedia.phoneappspring.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import himedia.phoneappspring.repository.vo.PhoneAppVo;
import himedia.phoneappspring.service.PhoneAppService;
import himedia.phoneappspring.service.PhotoService;

@RestController
@RequestMapping("/api/phoneApp/photo")
public class PhotoController {
	
	private static final Logger logger = LoggerFactory.getLogger(PhotoController.class);

	@Autowired
	private PhotoService photoService;
	private PhoneAppService phoneAppService;

//    @Value("${file.upload-dir}")
//    private String uploadDir; // uploadDir 변수 선언 및 초기화

    // POST : /api/phoneApp/photo/upload -> 프로필 사진 업로드
    @PostMapping("/upload")
    public ResponseEntity <Map<String, String>> uploadProfilePicture(@RequestParam("file") MultipartFile file,
            @RequestParam("userinfoId") Integer userinfoId) {
    	Map<String, String> response = new HashMap<>();
        if (file.isEmpty()) {
//            return ResponseEntity.badRequest().body("파일을 선택해주세요.");
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            String filePath = photoService.uploadProfilePicture(file, userinfoId);
//            return ResponseEntity.ok("프로필 사진이 성공적으로 업로드되었습니다. 파일 경로: " + filePath);
         // 반환된 URL을 response에 설정
            response.put("photoUrl", filePath);
            
            return ResponseEntity.ok(response);
        } catch (IOException e) {
        	logger.error("파일 업로드 중 오류 발생", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
	

    // GET : /api/phoneApp/photo/upload/{id} -> 프로필 사진 조회
//    @GetMapping("upload/{id}")
//    public ResponseEntity<PhotoVo> getPhoto(@PathVariable Integer id) {
//        System.out.println("요청된 ID: " + id);
//
//        // ID로 사진 정보 조회
//        PhotoVo photo = photoService.getPhotoById(id);
//        
//		if (photo != null) {
//			return ResponseEntity.ok(photo);
//		} else {
//			System.out.println("null이다......::" + id);
//			return ResponseEntity.notFound().build();
//		}
//    }
    
    @GetMapping("/api/phoneApp/{id}")
    public ResponseEntity<PhoneAppVo> getContactById(@PathVariable Integer id) {
        PhoneAppVo contact = phoneAppService.selectNumberById(id); // DB에서 연락처 정보를 조회
        if (contact == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(contact); // `photoUrl`을 포함한 `PhoneAppVo` 객체 반환
    }

    
    
	private static final String IMAGE_DIR = "/home/user/uploads/";
	
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
        Path imagePath = Paths.get(IMAGE_DIR + filename);
        Resource resource = new UrlResource(imagePath.toUri());

        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // 이미지 타입 설정
                .body(resource);
    }  
    
}

