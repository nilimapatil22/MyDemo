package com.cg.boot.service;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.boot.exceptions.DataNotFoundException;
import com.cg.boot.model.User;
import com.cg.boot.repository.UserRepository;

@Service
@Transactional
public class LoginService implements ILoginService {
	@Autowired
	UserRepository repository;

	@Override
	public User getUser(int id, String password) {
		User user = repository.findById(id).orElse(null);
		if (user == null) {
			throw new DataNotFoundException("User with given id  not present");
		} else if (!user.getPassword().equals(password)) {
			throw new DataNotFoundException("Invalid Password");
		}

		return user;

	}

}