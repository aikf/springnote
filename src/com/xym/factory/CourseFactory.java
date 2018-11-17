package com.xym.factory;

import com.xym.newinstance.HtmlCourse;
import com.xym.newinstance.ICourse;
import com.xym.newinstance.JavaCourse;

/**
 * @ClassName CourseFactory
 * @Description TODO
 * @Author ak
 * @Date 2018/11/14 上午9:06
 * @Version 1.0
 **/

//课程工厂
public class CourseFactory {
    //根据名字攻取课程
    public static ICourse getCourse(String name) {
        if (name.equals("java")) {
            return new JavaCourse();
        }else {
            return new HtmlCourse();
        }
    }
}