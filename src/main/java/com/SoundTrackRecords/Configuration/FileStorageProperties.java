package com.SoundTrackRecords.Configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.SoundTrackRecords.Util.AppConstants;
import org.springframework.context.annotation.Configuration;
@Configuration
@ConfigurationProperties(prefix = AppConstants.FILE_PROPERTIES_PREFIX)
public class FileStorageProperties {
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
}