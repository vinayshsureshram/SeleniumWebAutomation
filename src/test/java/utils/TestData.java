package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestData {
    private final JSONObject testData;
    private final String testEnvironment;

    public TestData(String testEnvironment) throws IOException, ParseException {
        this.testEnvironment = testEnvironment;
        JSONParser parser = new JSONParser();

        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("testData.json");
        testData = (JSONObject) parser.parse(new InputStreamReader(in, StandardCharsets.UTF_8));
    }


    /**
     * Method that takes any number of parameters in relation to the depth of json data structure to
     * read and return the value of the respective key.
     * For example:
     * readTestData("userInfo","passion","code");
     *
     * @param args keys to JSON structure
     * @return test data
     */
    public String readTestData(String... args) {
        JSONObject temp = (JSONObject) testData.get(testEnvironment);
        if (args.length == 1) {
            return (String) temp.get(args[0]);
        } else {
            for (int i = 0; i < args.length - 1; i++) {
                temp = (JSONObject) temp.get(args[i]);
            }
            return (String) temp.get(args[args.length - 1]);
        }
    }
}
