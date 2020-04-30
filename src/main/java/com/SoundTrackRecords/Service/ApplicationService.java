package com.SoundTrackRecords.Service;

import java.util.List;

import com.SoundTrackRecords.Model.Users;

public interface ApplicationService {

	Users createUser(Users Users);

	List<Users> getAllUser();
}
