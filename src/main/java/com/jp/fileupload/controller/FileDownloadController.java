package com.jp.fileupload.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jp.fileupload.model.DatabaseFile;
import com.jp.fileupload.service.DatabaseFileService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class FileDownloadController {
	@Autowired
	private DatabaseFileService fileStorageService;

	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		 Optional<DatabaseFile> databaseFile = fileStorageService.getFile(fileName);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(databaseFile.get().getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.get().getFileName() + "\"")
				.body(new ByteArrayResource(databaseFile.get().getData()));
	}

}
