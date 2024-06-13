package com.logs.parser.service;

import com.logs.parser.exceptions.InvalidQueryException;
import com.logs.parser.exceptions.LogFileDoesNotExistException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import static com.logs.parser.runtimeConfig.RuntimeContext.getFileData;
import static com.logs.parser.runtimeConfig.Extensions.split;

@Component
public class QueryService {
    public List<TreeMap<String, String>> SimpleQuery(String query) throws LogFileDoesNotExistException, InvalidQueryException {
        List<TreeMap<String, String>> CurrentList = getFileData();
        CurrentList =PerformAdvancedQueryActions(query, CurrentList);
        return CurrentList;
    }


//    private List<TreeMap<String, String>> PerformQueryActions(String query, List<TreeMap<String, String>> currentList)
//    {
//        List<TreeMap<String,String>> Result = new ArrayList<TreeMap<String, String>>();
//        Result = PerformWhereActions(query, currentList);
//        return Result;
//    }

    private List<TreeMap<String, String>> PerformAdvancedQueryActions(String query, List<TreeMap<String, String>> currentList) throws InvalidQueryException {
        try {
            List<TreeMap<String, String>> Result = new ArrayList<>();
            int QueryCount = 0;
            String[] Params = split(query, " ", true);

            while (QueryCount < Params.length) {
                String Action = Params[QueryCount];
                List<TreeMap<String,String>> TempDataList = null;

                switch (Action) {
                    case "is":
                        TempDataList = PerformIsAction(Params[QueryCount - 1], Params[QueryCount + 1], currentList);
                        QueryCount += 2;
                        break;
                    case "contains":
                        TempDataList = PerformContainsAction(Params[QueryCount - 1], Params[QueryCount + 1], currentList);
                        QueryCount += 2;
                        break;
//                    case "between":
//                        TempDataList = PerformBetweenAction(Params[QueryCount-1], Params[QueryCount+1],Params[QueryCount+2] , Params[QueryCount+3], currentList);
//                        QueryCount += 4;
//                        break;
                    default: QueryCount++;
                }
                if(Result == null || Result.isEmpty()) Result = TempDataList;
            }
            return Result;
        }
        catch (Exception ex) { throw new InvalidQueryException(); }
//        Result = PerformWhereActions(query, currentList);
    }

//    private List<TreeMap<String, String>> PerformWhereActions(String query, List<TreeMap<String, String>> currentList)
//    {
//        List<TreeMap<String,String>> Result = new ArrayList<TreeMap<String, String>>();
//        if (query.contains("is")) {
//            var params = split(query, "is", true);
//            Result = PerformIsAction(params[0], params[1], currentList);
//        }
//        else if (query.contains("contains")) {
//            var params = split(query, "contains", true);
//            Result = PerformContainsAction(params[0], params[1], currentList);
//        }
//        else if (query.contains("between")) {
//            var params = split(query, "between", true);
//            Result = PerformBetweenAction(params[0],params[1],currentList);
//        }
//
//        return Result;
//    }

    private List<TreeMap<String, String>> PerformContainsAction(String column, String value, List<TreeMap<String, String>> currentList)
    {
        List<TreeMap<String,String>> Result = new ArrayList<TreeMap<String, String>>();

        for (TreeMap<String, String> map:
                currentList){
            if(map.containsKey(column) && map.get(column).contains(value))
                Result.add(map);
        }
        return Result;
    }
    private List<TreeMap<String, String>> PerformIsAction(String column, String value, List<TreeMap<String, String>> currentList)
    {
        List<TreeMap<String,String>> Result = new ArrayList<TreeMap<String, String>>();

        for (TreeMap<String, String> map:
            currentList){
            if(map.containsKey(column) && map.get(column).equals(value))
                Result.add(map);
        }
        return Result;
    }

//    private List<TreeMap<String,String>> PerformBetweenAction(String column, String value1, String identifier, String value2, List<TreeMap<String, String>> currentList)
//    {
//        List<TreeMap<String,String>> Result = new ArrayList<TreeMap<String,String>>();
//        if(!identifier.equals("and")) throw new RuntimeException();
//
//        for (TreeMap<String, String> map:
//                currentList){
//            if(map.containsKey(column) && map.get(column) > value1 && map.get(column) < value2)
//                Result.add(map);
//        }
//        return Result;
//    }
}
