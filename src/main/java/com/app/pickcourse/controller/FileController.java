package com.app.pickcourse.controller;

import com.app.pickcourse.domain.vo.FileVO;
import com.app.pickcourse.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/files/*")
public class FileController {
    private final FileService fileService;

    @PostMapping("upload")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {

        FileVO thumbnail = fileService.upload(file);

        Map<String, Object> response = new HashMap<>();
        response.put("thumbnail", thumbnail);

        return ResponseEntity.ok(response);
    }

    @PostMapping("upload/multi")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        log.info("들어옴");

        List<FileVO> thumbnails = new ArrayList<>();
        files.forEach( file -> {
            thumbnails.add(fileService.upload(file));
        });

        Map<String, Object> response = new HashMap<>();
        response.put("thumbnails", thumbnails);

        return ResponseEntity.ok(response);
    }

    @GetMapping("display")
    @ResponseBody
    public byte[] display(String path) throws IOException{
        byte[] file = null;
        try {
            file = FileCopyUtils.copyToByteArray(new File("/upload/" + path));
        }catch (NoSuchFileException e){
            throw new RuntimeException();
        }
        return file;
    }

    @GetMapping("download")
    public ResponseEntity<Resource> download(String path) throws IOException{
        Resource resource = new FileSystemResource("/upload/" + path);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + new String(("한동석짱_" + path.split("_")[1]).getBytes("UTF-8"), "ISO-8859-1"));
        return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
    }
}












