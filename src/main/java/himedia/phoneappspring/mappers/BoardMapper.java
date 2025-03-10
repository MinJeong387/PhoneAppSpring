package himedia.phoneappspring.mappers;

import java.util.List;

import com.commit.repository.vo.BoardVo;


public interface BoardMapper {
//	<select id="selectAllItems" resultType="boardVo">
	List<BoardVo> selectAllItems();
	
//	<update id="updateItem" parameterType="boardVo">
	int updateItem(BoardVo item);

}