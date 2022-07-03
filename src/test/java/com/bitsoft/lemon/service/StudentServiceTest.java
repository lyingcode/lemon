package com.bitsoft.lemon.service;

import com.bitsoft.lemon.base.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentServiceTest extends BaseUnitTest {
    @Autowired
    private StudentService studentService;

    @Test
    void saveStudent() {
        try {
            studentService.saveStudent("小明");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}