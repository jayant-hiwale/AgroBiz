
package com.pravartak.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class AIService {

    // OpenAI Responses API
    private static final String API_URL =
            "https://api.openai.com/v1/responses";

    // AI model
    private static final String MODEL =
            "gpt-5.6-luna";


    // =========================================================
    // ASK AI
    // =========================================================

    public String askAI(String question) {

        try {

            // -------------------------------------------------
            // Get API key from Windows environment variable
            // -------------------------------------------------

            String apiKey =
                    System.getenv("OPENAI_API_KEY");


            if (apiKey == null || apiKey.isBlank()) {

                return "OpenAI API key is not configured.\n\n"
                        + "Please set the OPENAI_API_KEY "
                        + "environment variable.";
            }


            // -------------------------------------------------
            // Create JSON request
            // -------------------------------------------------

            JSONObject requestBody =
                    new JSONObject();

            requestBody.put(
                    "model",
                    MODEL
            );


            // -------------------------------------------------
            // Create input
            // -------------------------------------------------

            JSONArray input =
                    new JSONArray();


            JSONObject message =
                    new JSONObject();

            message.put(
                    "role",
                    "user"
            );


            message.put(
                    "content",
                    "You are AgroBiz AI Farming Advisor.\n\n"
                            + "Help farmers with simple and practical "
                            + "agricultural advice.\n"
                            + "You can answer questions about crops, "
                            + "soil, irrigation, fertilizers, pests, "
                            + "diseases and farming practices.\n\n"
                            + "Use simple language that farmers can "
                            + "easily understand.\n\n"
                            + "Farmer's question:\n"
                            + question
            );


            input.put(message);


            requestBody.put(
                    "input",
                    input
            );


            // -------------------------------------------------
            // Create HTTP client
            // -------------------------------------------------

            HttpClient client =
                    HttpClient.newHttpClient();


            // -------------------------------------------------
            // Create HTTP request
            // -------------------------------------------------

            HttpRequest request =
                    HttpRequest.newBuilder()

                            .uri(
                                    URI.create(API_URL)
                            )

                            .header(
                                    "Content-Type",
                                    "application/json"
                            )

                            .header(
                                    "Authorization",
                                    "Bearer " + apiKey
                            )

                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(
                                                    requestBody.toString()
                                            )
                            )

                            .build();


            // -------------------------------------------------
            // Send request
            // -------------------------------------------------

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers
                                    .ofString()
                    );


            // -------------------------------------------------
            // Check HTTP status
            // -------------------------------------------------

            if (response.statusCode() < 200
                    || response.statusCode() >= 300) {

                return "AI request failed.\n\n"
                        + "Status Code: "
                        + response.statusCode()
                        + "\n\n"
                        + response.body();
            }


            // -------------------------------------------------
            // Read response JSON
            // -------------------------------------------------

            JSONObject responseJson =
                    new JSONObject(
                            response.body()
                    );


            // -------------------------------------------------
            // Extract AI response
            // -------------------------------------------------

            JSONArray output =
                    responseJson.optJSONArray(
                            "output"
                    );


            if (output == null) {

                return "AI returned an unexpected response.";
            }


            StringBuilder answer =
                    new StringBuilder();


            for (int i = 0;
                 i < output.length();
                 i++) {


                JSONObject outputItem =
                        output.optJSONObject(i);


                if (outputItem == null) {
                    continue;
                }


                JSONArray content =
                        outputItem.optJSONArray(
                                "content"
                        );


                if (content == null) {
                    continue;
                }


                for (int j = 0;
                     j < content.length();
                     j++) {


                    JSONObject contentItem =
                            content.optJSONObject(j);


                    if (contentItem == null) {
                        continue;
                    }


                    String text =
                            contentItem.optString(
                                    "text",
                                    ""
                            );


                    if (!text.isEmpty()) {

                        answer.append(text);
                    }
                }
            }


            // -------------------------------------------------
            // Return answer
            // -------------------------------------------------

            if (answer.length() == 0) {

                return "AI did not return any text.";
            }


            return answer.toString();


        } catch (Exception e) {

            e.printStackTrace();

            return "Unable to connect to AI.\n\n"
                    + "Error: "
                    + e.getMessage();
        }
    }
}

