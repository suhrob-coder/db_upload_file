package com.example.db_upload_file.Repository;


import com.example.db_upload_file.Entity.File_Info;
import org.springframework.data.jpa.repository.JpaRepository;

public interface File_info_repository extends JpaRepository<File_Info, Integer> {
}
