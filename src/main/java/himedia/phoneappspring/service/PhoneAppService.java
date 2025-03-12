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

	// 전체 연락처 조회
	public List<PhoneAppVo> selectAllNumbers() {
		return phoneAppMapper.selectAllNumbers();
	}

	// 특정 연락처 조회
	public PhoneAppVo selectNumberById(Integer id) {
		return phoneAppMapper.selectNumberById(id);
	}

	// 연락처 추가
	public PhoneAppVo insertNumber(PhoneAppVo phoneVo) {
		phoneAppMapper.insertNumber(phoneVo);
		return phoneAppMapper.selectNumberById(phoneVo.getId());
	}
	
	//	기존 연락처 수정
	public PhoneAppVo updateNumberPartially(Integer id, PhoneAppVo phoneVo) {
		phoneVo.setId(id);
		phoneAppMapper.updateNumberPartially(phoneVo);
		return phoneAppMapper.selectNumberById(id);
	}

	// 연락처 수정
	public PhoneAppVo updateNumber(PhoneAppVo phoneVo) {
		phoneAppMapper.updateNumber(phoneVo);
		return phoneVo;
	}

	// 연락처 삭제
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