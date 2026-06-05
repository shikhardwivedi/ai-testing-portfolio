package com.shikhar.utils;

import okhttp3.*;
import org.json.*;

public class AITestGenerator {

    private static final String API_URL =
            "https://api.anthropic.com/v1/messages";
    private static final String API_KEY =
            "YOUR_CLAUDE_API_KEY";

    public static String generateTestCases(String userStory)
            throws Exception {

        OkHttpClient client = new OkHttpClient();

        String prompt = "You are a Senior SDET. Given this user story: " +
                "\"" + userStory + "\" " +
                "Generate 3 Selenium Java TestNG test methods " +
                "with explicit waits and assertions. " +
                "Return only the Java code, no explanation.";

        String requestBody = new JSONObject()
                .put("model", "claude-sonnet-4-20250514")
                .put("max_tokens", 1000)
                .put("messages", new JSONArray()
                        .put(new JSONObject()
                                .put("role", "user")
                                .put("content", prompt)))
                .toString();

        Request request = new Request.Builder()
                .url(API_URL)
                .post(RequestBody.create(requestBody,
                        MediaType.get("application/json")))
                .addHeader("x-api-key", API_KEY)
                .addHeader("anthropic-version", "2023-06-01")
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();
        JSONObject json = new JSONObject(responseBody);
        return json.getJSONArray("content")
                .getJSONObject(0)
                .getString("text");
    }

    public static void main(String[] args) throws Exception {
        String userStory =
                "As a user I want to login to OrangeHRM " +
                        "so that I can access my dashboard";
        String generatedTests = generateTestCases(userStory);
        System.out.println("🤖 AI Generated Tests:");
        System.out.println(generatedTests);
    }
}