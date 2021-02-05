package com.cg.boot.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.service.ITrainingScheduleService;

@RestController
public class StudentTrainingScheduleController {
	@Autowired
	ITrainingScheduleService scheduleService;

	/*
	 * Get schedule based on schedule id
	 */
	@GetMapping("/getStudentSchedule/{id}")
	public ResponseEntity<TrainingSchedule> getSchedule(@PathVariable("id") int id) {
		TrainingSchedule trainingSchedule = scheduleService.getSchedule(id);
		if (trainingSchedule == null) {
			throw new DataNotFoundException("No schedule present with given id: " + id);
		}
		return new ResponseEntity<TrainingSchedule>(trainingSchedule, HttpStatus.OK);

	}
	
	/*
	 * Get schedules based on student id
	 */
	@GetMapping("/getStudentScheduleByStudentId/{id}")
	public ResponseEntity<List<TrainingSchedule>> getScheduleByStudentId(@PathVariable("id") int id) {
		List<TrainingSchedule> trainingSchedules = scheduleService.getScheduleByStudentId(id);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedules, HttpStatus.OK);
	}
}
