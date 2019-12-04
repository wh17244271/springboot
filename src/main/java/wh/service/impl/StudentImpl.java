package wh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wh.dao.StudentDao;

import wh.entity.Student;
import wh.service.IStudentService;

@Service("studentImpl")
public class StudentImpl implements IStudentService {
	@Autowired
	private StudentDao studentDao ;

	@Override
	public Student get(Integer id) {
		return studentDao.getUser(id);
	}
	
	

}
