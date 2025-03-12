package himedia.phoneappspring.mappers;

import java.util.List;

import himedia.phoneappspring.repository.vo.PhoneAppVo;


public interface PhoneAppMapper {

//	<select id="selectAllItems" resultType="phoneAppVo">
	List<PhoneAppVo> selectAllItems();	// 조회
	
//	<insert id="insertItem" parameterType="PhoneAppVo">
	int insertItem(PhoneAppVo item);	// 추가

}