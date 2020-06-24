package com.SoundTrackRecords.Service;

import com.SoundTrackRecords.Exception.FileStorageException;
import java.util.List;

import com.SoundTrackRecords.Model.Users;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {

	Users createUser(Users Users, MultipartFile file);

	List<Users> getAllUser();
}
