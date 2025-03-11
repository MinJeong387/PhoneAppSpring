package himedia.phoneappspring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import himedia.phoneappspring.repository.vo.BoardVo;
import himedia.phoneappspring.service.BoardService;

@RestController
@RequestMapping("/api/board")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
//	GET : /api/board
	@GetMapping
	public ResponseEntity<List<BoardVo>> getAllItems() {
		List<BoardVo> items = 
				boardService.selectAllItems();
		return ResponseEntity.ok(items);
	}
	
	
	
//	PUT : /api/board/{id} -> 기존 항목 수정
	@PutMapping("/{id}")
	public ResponseEntity<BoardVo> updateItem(@RequestBody BoardVo item,
			@PathVariable("id") Integer id) {
		item.setId(id);
		BoardVo updatedItem = boardService.updateItem(item);
		return ResponseEntity.ok(updatedItem);
	}
	
}

// 커밋(민정)