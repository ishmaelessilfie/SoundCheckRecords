package com.SoundTrackRecords.Service;

import com.SoundTrackRecords.Exception.FileStorageException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SoundTrackRecords.Model.Users;
import com.SoundTrackRecords.Repository.UserRepository;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	UserRepository userRepository;

	@Override
	public Users createUser(Users users, MultipartFile file){
		return userRepository.save(users);

	}

	@Override
	public List<Users> getAllUser() {
		return userRepository.findAll();
	}

}
