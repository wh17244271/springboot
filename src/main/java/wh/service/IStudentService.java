package wh.service;

import org.apache.ibatis.annotations.Param;
import wh.entity.Student;


public interface IStudentService {

	Student get(@Param("id") Integer id);
}
