package com.xym.entities;

import com.xym.factory.CourseFactory;
import com.xym.newinstance.HtmlCourse;
import com.xym.newinstance.ICourse;
import com.xym.newinstance.JavaCourse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName Student
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 上午8:17
 * @Version 1.0
 **/
public class Student {
    private int stuNo;
    private String stuName;
    private int stuAge;

    @Override
    public String toString() {
        return "Student{" +
                "stuNo=" + stuNo +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getStuAge() {
        return stuAge;
    }

    public void setStuAge(int stuAge) {
        this.stuAge = stuAge;
    }

    public void learnJava(){
        JavaCourse javaCourse = new JavaCourse();
        javaCourse.learn();
    }

    public void learnHtml(){
        HtmlCourse htmlCourse = new HtmlCourse();
        htmlCourse.learn();
    }

    //学习任何课程
    public void learn(String courseName) {
        //根据课程名称获取相应课程
        ICourse course = CourseFactory.getCourse(courseName);
        course.learn();
    }

    public void learnWithIoC(String beanID) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从SpringIOC提供的超级工厂中获取课程
        ICourse course = (ICourse) context.getBean(beanID);
        course.learn();
    }
}