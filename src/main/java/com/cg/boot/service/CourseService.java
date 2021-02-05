package com.cg.boot.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.model.Course;
import com.cg.boot.repository.CourseRepository;

@Service
@Transactional
public class CourseService implements ICourseService {
	@Autowired
	CourseRepository repository;

	@Override
	public Course addCourse(Course course) {
		return repository.save(course);
	}

	@Override
	public Course getCourse(int id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public List<Course> getAllCourses() {
		return repository.findAll();
	}

	@Override
	public Course updateCourse(Course course) {
		 Course courseDetails = getCourse(course.getCourseId());
if (courseDetails != null) {
			
	courseDetails = repository.save(course);
		}
		return courseDetails;		
	}

	@Override
	public List<Course> deleteCourse(int id) {
		repository.deleteById(id);
		return repository.findAll();
	}

}
