package himedia.phoneappspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import himedia.phoneappspring.mappers.PhoneAppMapper;
import himedia.phoneappspring.repository.vo.PhoneAppVo;


@Service
public class PhoneAppService {
	
	@Autowired
	private PhoneAppMapper phoneAppMapper;
	
	//	목록 조회
	public List<PhoneAppVo> selectAllItems() {
		List<PhoneAppVo> items = phoneAppMapper.selectAllItems(); 
		return items;
	}
	
	//	추가
	public PhoneAppVo insertItem(PhoneAppVo item) {
		phoneAppMapper.insertItem(item);
		return item;
	}

}