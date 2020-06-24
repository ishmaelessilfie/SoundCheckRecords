package com.SoundTrackRecords;


import com.SoundTrackRecords.Controller.ProjectController;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef="auditorAware")
public class SoundTrackRecordsApplication {
    
    @Bean
    public AuditorAware<String> auditorAware(){
        return new SpringSecurityAuditorAware();
    }

	public static void main(String[] args) {
          // new File(ProjectController.uploadDirectory).mkdir();
		SpringApplication.run(SoundTrackRecordsApplication.class, args);
	}

}
