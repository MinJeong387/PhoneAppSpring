package himedia.phoneappspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
//	GET : /api/phoneApp
	@GetMapping
	public ResponseEntity<List<PhoneAppVo>> getAllItems() {
		List<PhoneAppVo> items = phoneAppService.selectAllItems();
		return ResponseEntity.ok(items);
	}

	
	
	
//	POST : /api/phoneApp
	@PostMapping("/insert")
	public ResponseEntity<PhoneAppVo> insertItem(@RequestBody PhoneAppVo item) {
		PhoneAppVo insertItem = phoneAppService.insertItem(item);
		return ResponseEntity.ok(insertItem);
	}
	
}

// 커밋(민정)
// 커밋(호정)