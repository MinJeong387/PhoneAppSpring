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

	// 전화번호 목록 불러오기
	public List<PhoneAppVo> selectAllNumbers() {
		return phoneAppMapper.selectAllNumbers();
	}

	// 전화번호 하나 선택
	public PhoneAppVo selectNumberById(Integer id) {
		return phoneAppMapper.selectNumberById(id);
	}

	// 전화번호 추가
	public PhoneAppVo insertNumber(PhoneAppVo phoneVo) {
		phoneAppMapper.insertNumber(phoneVo);
		return phoneAppMapper.selectNumberById(phoneVo.getId());
	}

	// 전화번호 수정
	public PhoneAppVo updateNumber(PhoneAppVo phoneVo) {
		phoneAppMapper.updateNumber(phoneVo);
		return phoneVo;
	}

	// 전화번호 삭제
	public int deleteNumber(Integer id) {
		return phoneAppMapper.deleteNumber(id);
	}
	
	// 이름으로 연락처 검색
	public List<PhoneAppVo> selectByName(String name) {
		return phoneAppMapper.selectByName(name);
	}
	
	// 전화번호로 연락처 검색
	public List<PhoneAppVo> selectByPhonenumber(String phoneNumber) {
		return phoneAppMapper.selectByPhonenumber(phoneNumber);
	}

}