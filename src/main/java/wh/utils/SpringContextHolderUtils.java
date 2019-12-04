package wh.utils;

import wh.entity.Student;
import wh.service.IStudentService;

public class SpringContextHolderUtils {

    private static IStudentService studentService = SpringContextHolder.getBean(IStudentService.class);

    /**
     * 工具代理返回
     * @param id
     * @return
     */
    public static Student getDistList(Integer id) {
        Student student = studentService.get(id);
        return student;
    }

}
