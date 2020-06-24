package com.SoundTrackRecords.Service;

import com.SoundTrackRecords.Exception.FileStorageException;
import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

	public String storeFile(MultipartFile file) throws IOException,FileStorageException;

	public Resource loadFileAsResource(String fileName);
}
