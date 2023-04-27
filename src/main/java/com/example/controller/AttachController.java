package com.example.controller;

import com.example.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/upload/v1")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        String fileName = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(fileName);
    }
    @PostMapping("/upload/v2")
    public ResponseEntity<String> upload2(@RequestParam("file") MultipartFile file) {
        String fileName = attachService.saveToSystem2(file);
        return ResponseEntity.ok().body(fileName);
    }
    @PostMapping("/upload/v3")
    public ResponseEntity<String> upload3(@RequestParam("file") MultipartFile file) {
        String fileName = attachService.saveToSystem3(file);
        return ResponseEntity.ok().body(fileName);
    }

    @GetMapping(value = "/open/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("id") String id) {
        if (id != null && id.length() > 0) {
            try {
                return this.attachService.loadImage(id);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }
    @GetMapping(value = "/open_general/v2/{id}", produces = MediaType.ALL_VALUE)
    public byte[] open_general2(@PathVariable("id") String id) {
        return attachService.open_general2(id);
    }

    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        Resource file = attachService.download(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(attachService.delete(id));
    }
}
