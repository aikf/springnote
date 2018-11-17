package com.xym.dao;

import com.xym.entities.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @ClassName StudentDaoImpl
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 下午2:55
 * @Version 1.0
 **/

@Repository
public class StudentDaoImpl implements IStudentDao{

    @Override
    public void addStudent(Student student){
        System.out.println("增加学生。。。");
    }
}