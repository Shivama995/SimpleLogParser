package com.logs.parser.runtimeConfig;

import com.logs.parser.exceptions.LogFileDoesNotExistException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class RuntimeContext {

    //region Members
    private static File file;
    private static ArrayList<TreeMap<String, String>> fileData;
    private static int CustomKeyCount = 0;
    //endregion

    //region Getters
    public static File getFile() {
        return file;
    }

    public static void setFile(File file) {
        RuntimeContext.file = file;
    }

    public static List<TreeMap<String, String>> getFileData() throws LogFileDoesNotExistException {
        if(fileData == null || fileData.isEmpty()) throw new LogFileDoesNotExistException();
        return fileData;
    }

    public static void setFileData(ArrayList<TreeMap<String, String>> fileData) {
        RuntimeContext.fileData = fileData;
    }

    public static int getCustomKeyCount()
    {
        return ++CustomKeyCount;
    }

    public static void addInFileData(TreeMap<String, String> fileData) {
        if(RuntimeContext.fileData == null || RuntimeContext.fileData.isEmpty()) setFileData(new ArrayList<TreeMap<String, String>>() {});
        RuntimeContext.fileData.add(fileData);
    }
    //endregion and

}
