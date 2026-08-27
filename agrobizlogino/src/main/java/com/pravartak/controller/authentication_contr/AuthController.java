package com.pravartak.controller.authentication_contr;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONObject;

public class AuthController {

    private static final String API_KEY ="AIzaSyCS_MR5MO1SZTyMRJOQnLX3xA0Qi6sfzs0";

    private final HttpClient client =
            HttpClient.newHttpClient();

    // =========================================================
    // SIGN UP
    // =========================================================

    public String signUp(
            String email,
            String password) {

        JSONObject payload =
                new JSONObject()
                        .put("email", email)
                        .put("password", password)
                        .put("returnSecureToken", true);

        try {

            URI uri = URI.create(
                    "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key="
                            + API_KEY);

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header(
                                    "Content-Type",
                                    "application/json")
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(payload.toString()))
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            System.out.println(
                    "Firebase signup response received.");
            //System.out.println(response.body());

            if (response.statusCode() == 200) {

                JSONObject result =
                        new JSONObject(response.body());

                return result.getString("localId");
            }

            return null;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }

    // =========================================================
    // SIGN IN
    // =========================================================

    public String signIn(
            String email,
            String password) {

        JSONObject payload =
                new JSONObject()
                        .put("email", email)
                        .put("password", password)
                        .put("returnSecureToken", true);

        try {

            URI uri = URI.create(
                    "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key="
                            + API_KEY);

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(uri)
                            .header(
                                    "Content-Type",
                                    "application/json")
                            .POST(
                                    HttpRequest.BodyPublishers
                                            .ofString(payload.toString()))
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());

            System.out.println(
                    "Firebase sign-in response received.");

            //System.out.println(response.body());

            if (response.statusCode() == 200) {

                JSONObject result =
                        new JSONObject(response.body());

                return result.getString("localId");
            }

            return null;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}