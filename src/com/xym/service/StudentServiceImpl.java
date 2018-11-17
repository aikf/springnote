package com.xym.service;

import com.xym.dao.IStudentDao;
import com.xym.dao.StudentDaoImpl;
import com.xym.entities.Student;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName StudentServiceImpl
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 下午3:57
 * @Version 1.0
 **/
public class StudentServiceImpl implements IStudentService {
//    IStudentDao studentDao = new StudentDaoImpl();
    private IStudentDao studentDao;

    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public boolean addStudent(Student student) {
        System.out.println(1/0); //测试异常通知
        studentDao.addStudent(student);
        return true;
    }

    @Override
    public void deleteStudent() {
        System.out.println("删除学生。。。");
    }
}