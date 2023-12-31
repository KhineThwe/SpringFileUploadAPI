package com.jp.fileupload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jp.fileupload.model.DatabaseFile;

@Repository
public interface DatabaseFileRepository  extends JpaRepository<DatabaseFile, String>{

}
