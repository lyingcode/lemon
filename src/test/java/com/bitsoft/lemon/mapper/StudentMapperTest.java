package com.bitsoft.lemon.mapper;

import com.bitsoft.lemon.base.BaseUnitTest;
import com.bitsoft.lemon.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentMapperTest extends BaseUnitTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void saveStudents() {
        Student student = new Student();
        student.setId(2);
        student.setRealName("zhang_san");
        studentMapper.saveStudents(student);
    }
}