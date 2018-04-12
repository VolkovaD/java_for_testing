package kz.qa.jft.rest;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

    public boolean isIssueOpen(int issueId){

        String json = RestAssured.get("http://demo.bugify.com/api/issues/" + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);

        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonElement issue_array = issues.getAsJsonArray().get(0);

        String status = issue_array.getAsJsonObject().get("state_name").getAsString();

        if (status.equals("Closed")) {
            return false;
        } return true;

    }

    public void skipIfNotFixed(int issueId) throws Exception {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}
