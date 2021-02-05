package com.cg.boot.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.boot.model.PreviousProgressDetails;
import com.cg.boot.model.ProgressDetails;
import com.cg.boot.repository.PreviousProgressDetailsRepository;
import com.cg.boot.repository.ProgressDetailsRepository;

@Service
@Transactional
public class ProgressDetailsService implements IProgressDetailsService {

	@Autowired
	ProgressDetailsRepository repository;
	@Autowired
	PreviousProgressDetailsRepository previousRepository;
	PreviousProgressDetails previousDetails;
	/*
	 * Get all ProgressDetails By Grade
	 */

	@Override
	public List<ProgressDetails> getAllProgressDetails(String grade) {
		return repository.findAllByGrade(grade);
	}
/*
 * 
 */
	@Override
	public List<PreviousProgressDetails> getAllProgressDetailsByStudentId(int studentId) {
		return previousRepository.findAllByStudentId(studentId);
	}

	/*
	 * Add ProgressDetails
	 */
	@Override
	public ProgressDetails addProgressDetails(@Valid ProgressDetails progressDetails) {
		previousDetails=new PreviousProgressDetails(progressDetails.getGrade(),progressDetails.getDate(),progressDetails.getAdminId(),progressDetails.getStudentId());
		previousRepository.save(previousDetails);
		return repository.save(progressDetails);
	}

	/*
	 * Update ProgressDetails
	 */
	@Override
	public ProgressDetails updateProgressDetails(ProgressDetails progressDetails) {
		previousDetails=new PreviousProgressDetails(progressDetails.getGrade(),progressDetails.getDate(),progressDetails.getAdminId(),progressDetails.getStudentId());
		previousRepository.save(previousDetails);
		return repository.save(progressDetails);
	}

	/*
	 * Delete ProgressDetails
	 */
	@Override
	public List<ProgressDetails> deleteProgressDetails(int id) {
		repository.deleteById(id);
		return repository.findAll();
	}
	/*
	 * get ProgressDetails By Id
	 */

	@Override
	public ProgressDetails getProgressDetails(int gradeId) {
		return repository.findById(gradeId).orElse(null);
	}

	/*
	 * Get All ProgressDetails
	 */
	@Override
	public List<ProgressDetails> getAllProgressDetails() {
		return repository.findAll();
	}

}
