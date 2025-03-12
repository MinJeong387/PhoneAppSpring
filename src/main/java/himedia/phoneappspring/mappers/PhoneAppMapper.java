package himedia.phoneappspring.mappers;

import java.util.List;

import himedia.phoneappspring.repository.vo.PhoneAppVo;


public interface PhoneAppMapper {

//	<select id="selectAllNumbers" resultType="PhoneAppVo">
	List<PhoneAppVo> selectAllNumbers();		// 전체 연락처 조회
	
//	<select id="selectNumberById" parameterType="Integer" resultType="PhoneAppVo">
	PhoneAppVo selectNumberById(Integer id);	// 특정 연락처 조회
	
//	<insert id="insertNumber" parameterType="PhoneAppVo">
	int insertNumber(PhoneAppVo phoneVo);		//	연락처 추가

//	<update id="updateNumber" parameterType="PhoneAppVo">
	int updateNumber(PhoneAppVo phoneVo);		//	연락처 수정
	
//	<delete id="deleteNumber" parameterType="int">
	int deleteNumber(Integer id);

//	<select id="selectByName" parameterType="PhoneAppVo" resultType="PhoneAppVo">
	List<PhoneAppVo> selectByName(String name);
	
//	<select id="selectByPhonenumber" parameterType="PhoneAppVo" resultType="PhoneAppVo">
	List<PhoneAppVo> selectByPhonenumber(String phoneNumber);
}