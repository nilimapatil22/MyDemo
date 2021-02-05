package com.cg.boot.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.TrainingSchedule;
import com.cg.boot.repository.TrainingScheduleRepository;

@Service
@Transactional
public class TrainingScheduleService implements ITrainingScheduleService{
    @Autowired 
	TrainingScheduleRepository repository;
    
	/*
	 * Add training schedule
	 */
    @Override
	public TrainingSchedule addSchedule(TrainingSchedule schedule) {
		TrainingSchedule trainingSchedule = repository.save(schedule);
		
		return trainingSchedule;
	}
    
    /*
	 * Get schedule based on schedule id
	 */

	@Override
	public TrainingSchedule getSchedule(int id) {
		return repository.findById(id).orElse(null);
	}
	
	/*
	 * Get all schedules
	 */

	@Override
	public List<TrainingSchedule> getAllSchedules() {
		return repository.findAll();
	}
	/*
	 * Get schedules based on student id
	 */

	@Override
	public List<TrainingSchedule> getScheduleByStudentId(int id) {
		List<TrainingSchedule> scheduleList = repository.findAllByStudentId(id);
		if(scheduleList.isEmpty()) {
			throw new DataNotFoundException("No schedules are present with given student id: "+id);
		}
		return scheduleList;
	}
	/*
	 * Update Schedule
	 */

	@Override
	public TrainingSchedule updateSchedule(TrainingSchedule schedule) { 
		if(getSchedule(schedule.getScheduleId())==null) {
			throw new DataNotFoundException("No schedule present to update");
		}
		if(!isValidDate(schedule.getDate())) {
			throw new DataNotFoundException("Date should be in yyyy-MM-dd format");
		}
		return repository.save(schedule);
	}
	/*
	 * Delete Schedule
	 */
    @Override
	public List<TrainingSchedule> deleteSchedule(int id) {
		if( getSchedule(id)==null) {
			throw new DataNotFoundException("No schedule present to delete with given id: "+id);
		}
		repository.deleteById(id);
		return repository.findAll();
	}
    
	public boolean isValidDate(String date) {
		boolean flag = false;
		 String regex = "((?:20)[2-3][1-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])"; 
		 Pattern p = Pattern.compile(regex); Matcher m = p.matcher(date);
		 if(m.matches()) {
			 flag = true;
		 }
		 return flag;
		
	}

	
	

}
