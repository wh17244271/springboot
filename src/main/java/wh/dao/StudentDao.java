package wh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wh.entity.Student;

@Mapper
public interface StudentDao {
	
//	@Select("select a.name name ,a.age age from student a where a.id = #{id}")
	Student getUser(@Param("id") Integer id);

}
