package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.Course;

public interface ICourseService {

	Course addCourse(Course course);

	Course getCourse(int id);

	List<Course> getAllCourses();

	Course updateCourse(Course course);

	List<Course> deleteCourse(int id);

}
