package com.bitsoft.lemon.service;

import com.bitsoft.lemon.mapper.StudentMapper;
import com.bitsoft.lemon.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseService courseService;

    @Transactional(rollbackFor = Exception.class)
    public void saveStudent(String realName) {
        Student student = new Student();
        student.setRealName(realName);
        doSaveStudent(student);
        try {
            courseService.regCourse(student.getId());
        } catch (Exception e) {
            log.error("保存课程信息发生异常", e);
        }

    }

    public void doSaveStudent(Student student) {
        Student temp = studentMapper.selectStudentByRealName(student.getRealName());
        if (temp != null) {
            student.setId(temp.getId());
        } else {
            studentMapper.saveStudents(student);
            temp = studentMapper.selectStudentByRealName(student.getRealName());
            student.setId(temp.getId());
        }
    }

}
