package com.upao.pe.fhahback.controllers;

import com.upao.pe.fhahback.serializers.files.UploadSerializer;
import com.upao.pe.fhahback.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("files")
public class FileUploadController {
    @Autowired
    StorageService storageService;

    @PostMapping("/upload/")
    public UploadSerializer upload(@RequestParam("file") MultipartFile multipartFile) {
        String path = storageService.store(multipartFile);
        return new UploadSerializer(path);
    }

    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getResource(@PathVariable String filename) throws IOException {
        Resource resource = storageService.loadAsResource(filename);
        String contentType = Files.probeContentType(resource.getFile().toPath());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType).body(resource);
    }
}
