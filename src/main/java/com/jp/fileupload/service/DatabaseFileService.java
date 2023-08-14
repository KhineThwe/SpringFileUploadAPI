package com.jp.fileupload.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.jp.fileupload.exception.FileNotFoundException;
import com.jp.fileupload.exception.FileStorageException;
import com.jp.fileupload.model.DatabaseFile;
import com.jp.fileupload.repository.DatabaseFileRepository;

@Service
public class DatabaseFileService {
	@Autowired
	private DatabaseFileRepository dbFileRepository;

	public DatabaseFile storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence" + fileName);
			}

			DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());
			return dbFileRepository.save(dbFile);

		} catch (IOException ex) {
			throw new FileStorageException("Could not store file" + fileName + ". Please try again!", ex);
		}

	}
	
	 public Optional<DatabaseFile> getFile(String fileId) {
	        return dbFileRepository.findById(fileId);
	    }

}
