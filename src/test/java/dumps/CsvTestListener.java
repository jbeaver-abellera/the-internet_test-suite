package dumps;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// NOT YET TESTED //
public class CsvTestListener implements ITestListener {
        private List<TestResult> testResults = new ArrayList<>();

        @Override
        public void onTestSuccess(ITestResult result) {
            testResults.add(new TestResult(result.getName(), "SUCCESS"));
        }

        @Override
        public void onTestFailure(ITestResult result) {
            testResults.add(new TestResult(result.getName(), "FAILURE"));
        }

        @Override
        public void onTestSkipped(ITestResult result) {
            testResults.add(new TestResult(result.getName(), "SKIPPED"));
        }

        @Override
        public void onFinish(ITestContext context) {
            try {
                writeResultsToCsv(testResults, "test-results.csv");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void writeResultsToCsv(List<TestResult> results, String filePath) throws IOException {
            try (FileWriter out = new FileWriter(filePath);
                 CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Test Name", "Result"))) {
                for (TestResult result : results) {
                    printer.printRecord(result.getTestName(), result.getResult());
                }
            }
        }

        private static class TestResult {
            private final String testName;
            private final String result;

            public TestResult(String testName, String result) {
                this.testName = testName;
                this.result = result;
            }

            public String getTestName() {
                return testName;
            }

            public String getResult() {
                return result;
            }
        }
}

