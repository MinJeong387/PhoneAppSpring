package himedia.phoneappspring.mappers;

import org.apache.ibatis.annotations.Mapper;

import himedia.phoneappspring.repository.vo.PhotoVo;

@Mapper
public interface PhotoMapper {
	
	
//	<insert id="insertOrUpdatePhoto" parameterType="photoVo">
	void insertOrUpdatePhoto(PhotoVo photo);
	
//	<select id="getPhoto" parameterType="int" resultType="photoVo">
    PhotoVo getPhotoById(Integer id);
}