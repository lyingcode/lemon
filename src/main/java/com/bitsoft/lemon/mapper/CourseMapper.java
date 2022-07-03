package com.bitsoft.lemon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CourseMapper {
    @Update("update t_course set number=number+1 where id = #{courseId}")
    void addCourseNumber(int courseId);
}
