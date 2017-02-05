package edu.utdallas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.Code;
import edu.utdallas.util.MultiColumnPrinter;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import sun.security.krb5.internal.crypto.Des;

public class JUnitExecutionListener extends RunListener {

    @Override
    public void testRunFinished(Result result) throws Exception {
        super.testRunFinished(result);
        File report = new File(System.getProperty("user.dir") + "/statement_coverage_report.txt");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(report);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Statement coverage information is written to report.txt in the root directory");
        writer.println("Statement Coverage is listed below for each class and package as well as the total:");
        writer.println();



        final PrintWriter finalWriter = writer;
        MultiColumnPrinter printer = new MultiColumnPrinter(5, 10, "*", 0, false) {
            @Override
            public void doPrint(String str) {
                finalWriter.append(str);
                System.out.print(str);
            }

            @Override
            public void doPrintln(String str) {
                finalWriter.append("\n");
                System.out.println();
            }
        };


        String titleRow[] = new String[5];
        titleRow[0] = "Package Name";
        titleRow[1] = "Class Name";
        titleRow[2] = "Executed Statements";
        titleRow[3]="Total statements";
        titleRow[4] = "Statement Coverage";
        printer.addTitle(titleRow);


        List<CodeCoverage> results = CodeCoverageAPI.getAllCodeCoverage();

        for(CodeCoverage codeCoverage:results){
            String row[] = new String[5];


            row[0] = codeCoverage.getPackageName();
            row[1] = codeCoverage.getClassName();
            row[2] = String.valueOf(codeCoverage.getExecutedLines());
            row[3]= String.valueOf(codeCoverage.getTotalLines());
            row[4] = codeCoverage.getCodeCoverage() + " %";
            printer.add(row);
        }

        printer.print();
        CodeCoverage totalCodeCoverage = CodeCoverageAPI.getTotalCoverage();
        writer.append("\nTotal number of Lines Executed: ").append( String.valueOf(totalCodeCoverage.getExecutedLines()));
        writer.append("\n\nTotal number of Lines: ").append( String.valueOf(totalCodeCoverage.getTotalLines()));
        writer.append("\n\nTotal Code Coverage: ").append( String.valueOf(totalCodeCoverage.getCodeCoverage())).append(" %");


        System.out.println("Total number of Lines Executed in the Project: " + totalCodeCoverage.getExecutedLines());
        System.out.println("Total number of Lines in the Project: " + totalCodeCoverage.getTotalLines());
        System.out.println("Total Statement Coverage of the Project: " + totalCodeCoverage.getCodeCoverage() + " %");

        writer.close();


    }
}


