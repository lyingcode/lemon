package com.bitsoft.lemon.mapper;

import com.bitsoft.lemon.model.Student;
import org.apache.ibatis.annotations.*;

@Mapper
public interface StudentMapper {
    @Insert("INSERT INTO t_student(real_name) VALUES(#{realName})")
    void saveStudents(Student student);

    @Results(id = "studentMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "realName", column = "real_name")
    })
    @Select("select * from t_student where real_name = #{realName} limit 1")
    Student selectStudentByRealName(@Param("realName") String realName);
}
