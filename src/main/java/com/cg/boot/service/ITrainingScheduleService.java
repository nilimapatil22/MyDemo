package com.cg.boot.service;

import java.util.List;

import com.cg.boot.model.TrainingSchedule;

public interface ITrainingScheduleService {

	TrainingSchedule addSchedule(TrainingSchedule schedule);

	TrainingSchedule getSchedule(int id);

	List<TrainingSchedule> getAllSchedules();

	List<TrainingSchedule> getScheduleByStudentId(int id);

	TrainingSchedule updateSchedule(TrainingSchedule schedule);

	List<TrainingSchedule> deleteSchedule(int id);

}
