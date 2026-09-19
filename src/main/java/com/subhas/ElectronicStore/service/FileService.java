package com.subhas.ElectronicStore.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String uploadImage(MultipartFile file , String path) throws IOException;
    
    public InputStream getResource(String path, String name) throws FileNotFoundException;
}
