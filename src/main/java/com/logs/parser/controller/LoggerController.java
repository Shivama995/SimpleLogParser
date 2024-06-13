package com.logs.parser.controller;

import com.logs.parser.exceptions.InvalidQueryException;
import com.logs.parser.exceptions.LogFileDoesNotExistException;
import com.logs.parser.exceptions.WhiteSpaceNotAllowedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LoggerController {

    @Autowired
    com.logs.parser.service.LoggerService LoggerService;

    @Autowired
    com.logs.parser.service.QueryService QueryService;

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
    public List<TreeMap<String, String>> GetAllLogData() throws LogFileDoesNotExistException {
        return LoggerService.GetAllLogData();
    }

    @GetMapping("ChangeColumn")
    public List<TreeMap<String, String>> ChangeColumn(
            @RequestParam("existingColumn") String existingColumn,
            @RequestParam("newColumn") String newColumn) throws WhiteSpaceNotAllowedException, LogFileDoesNotExistException {
        return LoggerService.ChangeColumn(existingColumn, newColumn);
    }

    @GetMapping("Query")
    public List<TreeMap<String, String>> Query(@RequestParam("query") String query) throws LogFileDoesNotExistException, InvalidQueryException {
        return QueryService.SimpleQuery(query);
    }
}
