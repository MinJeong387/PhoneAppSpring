package himedia.phoneappspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.phoneappspring.repository.vo.PhoneAppVo;
import himedia.phoneappspring.service.PhoneAppService;

@RestController
@RequestMapping("/api/phoneApp")
public class PhoneAppController {

	@Autowired
	private PhoneAppService phoneAppService;
	
	// GET: /api/phoneApp - 전체 전화번호 목록 불러오기
    @GetMapping
    public ResponseEntity<List<PhoneAppVo>> getAllNumbers() {
        List<PhoneAppVo> phoneVo = phoneAppService.selectAllNumbers();
        return ResponseEntity.ok(phoneVo);
    }
    
    // GET: /api/phoneApp/{id} - 특정 전화번호 하나 불러오기
    @GetMapping("/{id}")
    public ResponseEntity<PhoneAppVo> getNumberById(@PathVariable("id") Integer id) {
        PhoneAppVo phoneVo = phoneAppService.selectNumberById(id);
        return ResponseEntity.ok(phoneVo);
    }
    
    // POST: /api/phoneApp - 새 전화번호 추가
    @PostMapping("/insert")
    public ResponseEntity<PhoneAppVo> addNumber(@RequestBody PhoneAppVo phoneVo) {
        PhoneAppVo addedPhoneVo = phoneAppService.insertNumber(phoneVo);
        return ResponseEntity.ok(addedPhoneVo);
    }
    
    // PUT: /api/phoneApp/{id} - 기존 전화번호 수정
    @PutMapping("/update/{id}")
    public ResponseEntity<PhoneAppVo> updateNumber(@RequestBody PhoneAppVo phoneVo, 
            @PathVariable("id") Integer id) {
    	phoneVo.setId(id); // 요청 파라미터로 받은 id 설정
        PhoneAppVo updatedPhoneVo = phoneAppService.updateNumber(phoneVo);
        return ResponseEntity.ok(updatedPhoneVo);
    }
    
    // DELETE: /api/phoneApp/{id} - 전화번호 삭제
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteNumber(@PathVariable("id") Integer id) {
        phoneAppService.deleteNumber(id);
        return ResponseEntity.noContent().build(); // 삭제 후 아무 것도 반환하지 않음
    }
    
	// GET: /api/phoneApp/{name} - 이름으로 연락처 검색하기
    @GetMapping("/search/{name}")
    public ResponseEntity<List<PhoneAppVo>> selectByName(@PathVariable String name) {
        List<PhoneAppVo> phoneVo = phoneAppService.selectByName(name);
        return ResponseEntity.ok(phoneVo);
    }
    
	// GET: /api/phoneApp/{phonenumber} - 전화번호로 연락처 검색하기
    @GetMapping("/search/{phonenumber}")
    public ResponseEntity<List<PhoneAppVo>> selectByPhonenumber(@PathVariable String phonenumber) {
        List<PhoneAppVo> phoneVo = phoneAppService.selectByPhonenumber(phonenumber);
        return ResponseEntity.ok(phoneVo);
    }
	
}