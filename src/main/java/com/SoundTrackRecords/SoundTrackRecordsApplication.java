package com.SoundTrackRecords;


import com.SoundTrackRecords.Controller.ProjectController;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoundTrackRecordsApplication {

	public static void main(String[] args) {
          // new File(ProjectController.uploadDirectory).mkdir();
		SpringApplication.run(SoundTrackRecordsApplication.class, args);
	}

}
