package com.example.homepage.controller;

import com.example.homepage.model.App;
import com.example.homepage.repository.AppRepository;
import com.example.homepage.storage.StorageFileNotFoundException;
import com.example.homepage.storage.StorageProperties;
import com.example.homepage.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by ybdevelop on 2016/10/18.
 */
@RestController
@RequestMapping("/homepage/app")
public class AppController {
    private final StorageService storageService;

    @Autowired
    public AppController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Autowired
    public AppRepository appRepository;

    @GetMapping("/icon/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> showFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/jpeg")
                .body(file);
    }

    @GetMapping("/showall")
    public Object showAll(){
        return appRepository.findAll();
    }

    @PostMapping("/create")
    public String create(@RequestParam("file") MultipartFile icon, String name) {
        storageService.store(icon);
        App app = new App(icon.getOriginalFilename(),name);
        appRepository.save(app);
        return "添加成功";
    }
    @PostMapping("/update")
    public Object update(Integer id, @RequestParam("file") MultipartFile icon, String name){
        storageService.store(icon);
        App app = appRepository.findOne(id);
        app.update(icon.getOriginalFilename(), name);
        appRepository.save(app);
        return "更新成功";
    }
    @GetMapping("/update")
    public Object delete(Integer id){
        appRepository.delete(id);
        return "删除成功";
    }


    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
