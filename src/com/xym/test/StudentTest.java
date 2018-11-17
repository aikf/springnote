package com.xym.test;

import com.xym.entities.AllCollectionType;
import com.xym.entities.Course;
import com.xym.entities.Student;
import com.xym.service.IStudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * @ClassName StudentTest
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 上午8:22
 * @Version 1.0
 **/
public class StudentTest {

    @Test
    public void firstSpring(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }

    @Test
    public void learnCourse(){
        Student student = new Student();
        student.learnHtml();
        student.learnJava();
    }

    @Test
    public void learnCourseWithFactory(){
        Student student = new Student();
        student.learn("java");
        student.learn("html");
    }

    @Test
    public void learnCourseWithIoC(){
        Student student = new Student();
        student.learnWithIoC("javaCourse");
        student.learnWithIoC("htmlCourse");
    }

    @Test
    public void testDI(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Course course = (Course) context.getBean("course");
        System.out.println(course);
    }

    @Test
    public void collectionDemo(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AllCollectionType collectionDemo = (AllCollectionType) context.getBean("collectionDemo");
        System.out.println(collectionDemo);
    }

    @Test
    public void aop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        IStudentService service = (IStudentService) context.getBean("studentService");
        service.addStudent(null);
        service.deleteStudent();
    }

}