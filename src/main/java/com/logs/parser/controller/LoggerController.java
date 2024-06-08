package com.logs.parser.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LoggerController {

    @Autowired
    com.logs.parser.service.LoggerService LoggerService;

    @GetMapping("HelloWorld")
    public String HelloWorld(){
        return "HelloWorld";
    }

    @GetMapping("UploadFile")
    public boolean UploadFile(@RequestParam("filePath") String filePath) {
        return LoggerService.UploadFile(filePath);
    }

    @GetMapping("DisplayFile")
    public String DisplayFile(){
        return LoggerService.DisplayFile();
    }

    @GetMapping("GetAllLogData")
    public List<TreeMap<String, String>> GetAllLogData()
    {
        return LoggerService.GetAllLogData();
    }

    @GetMapping("ChangeColumn")
    public List<TreeMap<String, String>> ChangeColumn(
            @RequestParam("existingColumn") String existingColumn,
            @RequestParam("newColumn") String newColumn)
    {
        return LoggerService.ChangeColumn(existingColumn, newColumn);
    }
}
