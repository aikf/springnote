package com.xym.service;

import com.xym.entities.Student;

public interface IStudentService {
    boolean addStudent(Student student);

    void deleteStudent();
}
