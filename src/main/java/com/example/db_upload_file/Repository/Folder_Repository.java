package com.example.db_upload_file.Repository;

import com.example.db_upload_file.Entity.File_folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Folder_Repository extends JpaRepository<File_folder, Integer> {
}
