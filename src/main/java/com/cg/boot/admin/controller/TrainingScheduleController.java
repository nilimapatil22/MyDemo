package com.cg.boot.admin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.service.ITrainingScheduleService;

@RestController
public class TrainingScheduleController {
	@Autowired
	ITrainingScheduleService scheduleService;

	/*
	 * Add Schedule
	 */
	@PostMapping("/addSchedule")
	public TrainingSchedule addSchedule(@Valid @RequestBody TrainingSchedule schedule) {
		TrainingSchedule trainingSchedule = scheduleService.addSchedule(schedule);
		return trainingSchedule;

	}

	/*
	 * Get schedule based on schedule id
	 */
	@GetMapping("/getSchedule/{id}")
	public ResponseEntity<TrainingSchedule> getSchedule(@PathVariable("id") int id) {
		TrainingSchedule trainingSchedule = scheduleService.getSchedule(id);
		if (trainingSchedule == null) {
			throw new DataNotFoundException("No schedule present with given id: " + id);
		}
		return new ResponseEntity<TrainingSchedule>(trainingSchedule, HttpStatus.OK);

	}
	/*
	 * Get all schedules
	 */

	@GetMapping("/getAllSchedules")
	public ResponseEntity<List<TrainingSchedule>> getAllSchedule() {
		List<TrainingSchedule> trainingSchedules = scheduleService.getAllSchedules();
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedules, HttpStatus.OK);

	}

	/*
	 * Get schedules based on student id
	 */
	@GetMapping("/getScheduleByStudentId/{id}")
	public ResponseEntity<List<TrainingSchedule>> getScheduleByStudentId(@PathVariable("id") int id) {
		List<TrainingSchedule> trainingSchedules = scheduleService.getScheduleByStudentId(id);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedules, HttpStatus.OK);
	}
	/*
	 * Update Schedule
	 */

	@PutMapping("/updateSchedule")
	public ResponseEntity<TrainingSchedule> updateSchedule(@Valid @RequestBody TrainingSchedule schedule) {
		TrainingSchedule trainingSchedule = scheduleService.updateSchedule(schedule);
		return new ResponseEntity<TrainingSchedule>(trainingSchedule, HttpStatus.OK);

	}

	/*
	 * Delete Schedule
	 */
	@DeleteMapping("/deleteSchedule/{id}")
	public ResponseEntity<List<TrainingSchedule>> deleteSchedule(@PathVariable("id") int id) {
		List<TrainingSchedule> trainingSchedules = scheduleService.deleteSchedule(id);
		return new ResponseEntity<List<TrainingSchedule>>(trainingSchedules, HttpStatus.OK);
	}

}
