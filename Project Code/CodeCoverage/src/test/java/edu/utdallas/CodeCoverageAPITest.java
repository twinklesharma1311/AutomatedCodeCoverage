package edu.utdallas;

/**
 * Created by twinklesharma on 11/19/16.
 */
public class CodeCoverageAPITest {

    @org.junit.Test
    public void test() throws Exception {

        for (int i = 0; i < 10; i++) {
            CodeCoverageAPI.addLine("classA",i);
        }
        for (int i = 0; i < 10; i++) {
            CodeCoverageAPI.addLine("test/classB",i);
        }

        for (int i = 0; i < 10; i++) {
            CodeCoverageAPI.markLineExecuted("test/classB",i);
        }
        CodeCoverageAPI.markLineExecuted("classA",7);
        CodeCoverageAPI.markLineExecuted("classA",2);
        CodeCoverageAPI.markLineExecuted("test/classB",6);
        CodeCoverageAPI.markLineExecuted("test/classB",1);
        System.out.println(CodeCoverageAPI.getCodeCoverage("classA"));
        System.out.println(CodeCoverageAPI.getCodeCoverage("test/classB"));
        System.out.println(CodeCoverageAPI.getAllCodeCoverage());
        System.out.println(CodeCoverageAPI.getHashMap());

        System.out.println(CodeCoverageAPI.getTotalCoverage());

    }
}