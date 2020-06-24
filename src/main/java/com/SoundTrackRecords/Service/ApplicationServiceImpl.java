package com.SoundTrackRecords.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.UserRepository;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Users createUser(Users users) {
		return userRepository.save(users);

	}

	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}

}
