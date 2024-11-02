package com.upao.pe.fhahback.services;

import com.upao.pe.fhahback.exceptions.StorageException;
import com.upao.pe.fhahback.exceptions.StorageFileNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class StorageService {

    private Path rootLocation;

    @Value("${storage.location}")
    private String storageLocation;

    @PostConstruct
    public void init(){
        if (storageLocation.trim().isEmpty()){
            throw new RuntimeException("File upload location can not be Empty");
        }

        rootLocation = Paths.get(storageLocation);

        try{
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage");
        }
    }

    public String store(MultipartFile file){
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }
            String originalFilename = file.getOriginalFilename();
            String filename = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFilename);

            Path destinationFile = this.rootLocation.resolve(Paths.get(filename)).normalize().toAbsolutePath();

            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new StorageException("Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
            return filename;
        }
        catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    public Path load(String filename){
        return rootLocation.resolve(filename);
    }

    public Resource loadAsResource(String filename){
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);
            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    public void delete(String filename) {
        Path file = load(filename);
        try {
            FileSystemUtils.deleteRecursively(file);
        } catch (IOException ex) {
            throw new RuntimeException("Cann't delete the file.");
        }
    }
}
