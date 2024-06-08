package com.logs.parser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static com.logs.parser.runtimeConfig.Regex.CombinedPattern;
import static com.logs.parser.runtimeConfig.RuntimeContext.getFile;
import static com.logs.parser.runtimeConfig.RuntimeContext.setFile;
import static com.logs.parser.runtimeConfig.RuntimeContext.addInFileData;
import static com.logs.parser.runtimeConfig.RuntimeContext.getFileData;

@Component
public class LoggerService {

    //region Members
    @Autowired
    private QueryService QueryService;
    //endregion

    //region Methods
    public boolean UploadFile(String filePath)
    {
        try {
            File logFile = new File(filePath);
            if(!logFile.exists() || !logFile.isFile()) return false;
            ProcessLogFile(logFile);
            return true;
        }
        catch (Exception ex) { return false; }
    }

    public String DisplayFile()
    {
        String FileRaw = "";
        try (BufferedReader br = new BufferedReader(new FileReader(getFile()))) {
            while (br.readLine() != null) {
                FileRaw += br.readLine();
                FileRaw += "\n\r";
            }
        } catch (Exception e) { throw new RuntimeException(e); }
        return  FileRaw;
    }

    public void ProcessLogFile(File logFile)
    {
        setFile(logFile);
        try (BufferedReader br = new BufferedReader(new FileReader(getFile()))) {
            while (br.readLine() != null) {
                ProcessLogData(br.readLine());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void ProcessLogData(String log)
    {
        if (log == null || log.isEmpty()) return;
        Pattern LogPattern               = Pattern.compile(CombinedPattern);
        Matcher LogMatcher               = LogPattern.matcher(log);
        TreeMap<String, String> LogData  =  new TreeMap<String, String>();
        int DataCount                    = 0;

        while (LogMatcher.find()) {
            String value = LogMatcher.group(1) != null ?
                    LogMatcher.group(1) :
                    LogMatcher.group(2);

                // Remove surrounding quotes if present
            if (value != null && value.startsWith("\"") && value.endsWith("\"")) {
                value = value.substring(1, value.length() - 1);
        }
            LogData.put("mxcustom" + ++DataCount, value);
        }
        addInFileData(LogData);
    }

    public List<TreeMap<String, String>> GetAllLogData()
    {
        return getFileData();
    }

    public List<TreeMap<String, String>> ChangeColumn(String existingColumn, String newColumn)
    {
        for (TreeMap<String,String> log:
             getFileData()) {
            if(log.containsKey(existingColumn))
            {
                String Log = log.remove(existingColumn);
                log.put(newColumn, Log);
            }
        }
        return  getFileData();
    }
    //endregion
}
