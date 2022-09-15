package com.example.db_upload_file.Controller;


import com.example.db_upload_file.Entity.File_folder;
import com.example.db_upload_file.Repository.Folder_Repository;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/folder")
public class Folder_Controller {
    @Autowired
    Folder_Repository folder_repository;

    String baza_adress = "src\\main\\resources\\baza\\";

    @PostMapping("/post")
    public String Download_folder(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if(file!=null){
            File_folder file_folder = new File_folder();
            file_folder.setFileorginalnomi(file.getOriginalFilename());
            file_folder.setFilehajmi(file.getSize());
            file_folder.setFileturi(file.getContentType());
            String[] newname = file.getOriginalFilename().split("\\.");
            String randomname = UUID.randomUUID().toString()+"." + newname[newname.length-1];
            file_folder.setNewname(randomname);
            folder_repository.save(file_folder);
            Path path = Paths.get(baza_adress + randomname);
            Files.copy(file.getInputStream(),path);

            return "saqlandi!";
        }
        return "joylanmadiii!";
    }


    @GetMapping("/get/{id}")
    public void UploadFolder(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<File_folder> byId = folder_repository.findById(id);
        if(byId.isPresent()){
            File_folder file_folder = byId.get();
            response.setContentType(file_folder.getFileturi());
            response.setHeader("Content-Disposition","attachment; filename=\""+ file_folder.getNewname() +"\"");
            FileInputStream inputStream = new FileInputStream(baza_adress + file_folder.getNewname());
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }
}
