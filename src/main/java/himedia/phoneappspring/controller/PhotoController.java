package himedia.phoneappspring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import himedia.phoneappspring.repository.vo.PhotoVo;
import himedia.phoneappspring.service.PhotoService;

@RestController
@RequestMapping("/api/phoneApp/photo")
public class PhotoController {

	@Autowired
	private PhotoService photoService;

	 //	POST : /api/phoneApp/photo/upload -> 프로필 사진 업로드
    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadPhoto(@RequestParam("file") MultipartFile file,
                                                           @RequestParam("userinfoId") Integer userinfoId) {
        Map<String, Object> response = new HashMap<>();

        if (file.isEmpty()) {
            response.put("message", "파일을 선택해주세요.");
            return ResponseEntity.badRequest().body(response);
        }

        try {
            String photoVo = photoService.uploadPhoto(file, userinfoId);
            response.put("message", "프로필 사진이 성공적으로 업로드되었습니다.");
            response.put("data", photoVo);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            response.put("message", "파일 업로드 중 오류가 발생했습니다.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    
    
    //	GET : /api/phoneApp/photo/upload/{id} -> 프로필 사진 조회
	@GetMapping("/upload/{id}")
	public ResponseEntity<PhotoVo> getPhotoByUserinfoId(@PathVariable Integer id) {
	    PhotoVo photoVo = photoService.getPhotoByUserinfoId(id);

	    if (photoVo != null) {
	        return ResponseEntity.ok(photoVo);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	
	
	private static final String IMAGE_DIR = "/home/user/uploads/";

	
//    @GetMapping("/{filename}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
//        Path imagePath = Paths.get(IMAGE_DIR + filename);
//        Resource resource = new UrlResource(imagePath.toUri());
//
//        if (!resource.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG) // 이미지 타입 설정
//                .body(resource);
//    }
}


