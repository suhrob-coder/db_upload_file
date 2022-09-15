package com.example.db_upload_file.Repository;


import com.example.db_upload_file.Entity.File_Info;
import com.example.db_upload_file.Entity.File_byte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface File_byte_Repository extends JpaRepository<File_byte, Integer> {

    Optional<File_Info> findByFileinfoId(Integer fileinfo_id);
}
