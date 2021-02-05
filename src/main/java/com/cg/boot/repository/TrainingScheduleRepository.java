package com.cg.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.boot.model.TrainingSchedule;

public interface TrainingScheduleRepository extends JpaRepository<TrainingSchedule, Integer> {

	List<TrainingSchedule> findAllByStudentId(int id);

}
