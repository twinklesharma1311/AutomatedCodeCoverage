package edu.utdallas;

import java.util.*;

/**
 * Created by twinklesharma on 11/16/16.
 */
public class CodeCoverageAPI {


    private static HashMap<String, HashMap<Integer, Boolean>> hashMap = new HashMap<String, HashMap<Integer, Boolean>>();

    public static HashMap<String, HashMap<Integer, Boolean>> getHashMap() {
        return hashMap;
    }

    //called for keeping track of all statements traversed during asm->total lines
    public static void addLine(String className, Integer line_number) {
        HashMap<Integer, Boolean> newHash = null;
        if (hashMap.containsKey(className)) {
            newHash = hashMap.get(className);
        } else {
            newHash = new HashMap<Integer, Boolean>();
            hashMap.put(className, newHash);
        }

        newHash.put(line_number, false);
    }

    //called at the time of jvm when specific lines are executed->executed lines
    public static void markLineExecuted(String className, Integer line_number) {
        hashMap.get(className).put(line_number, true);


    }

    //called after tests are completed to get code coverage of one class
    public static CodeCoverage getCodeCoverage(String className) {
        HashMap<Integer,Boolean> hash=hashMap.get(className);
        int total_lines=hash.keySet().size();
        int executed_lines=0;

        for(Integer line: hash.keySet()){

            if(hash.get(line))
            executed_lines++;
        }

        return new CodeCoverage(total_lines,executed_lines,className);
    }

    //called to show code coverage of whole project
    public static List<CodeCoverage> getAllCodeCoverage() {
        List<CodeCoverage> allCode = new LinkedList<CodeCoverage>();
        for (String classname : hashMap.keySet()) {
            CodeCoverage coverage = getCodeCoverage(classname);
            allCode.add(coverage);
        }
        return allCode;
    }

    public static CodeCoverage getTotalCoverage(){
        List<CodeCoverage> codeCoverageList=getAllCodeCoverage();
        int totalLines = 0;
        for (CodeCoverage codeCoverage:codeCoverageList){
            totalLines += codeCoverage.getTotalLines();
        }
        int executedLines = 0;
        for (CodeCoverage codeCoverage:codeCoverageList){
            executedLines += codeCoverage.getExecutedLines();
        }

        return new CodeCoverage(totalLines, executedLines, "");
    }

}