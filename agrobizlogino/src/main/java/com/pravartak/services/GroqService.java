package com.pravartak.services;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.Map;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class GroqService {

    private static final String API_URL =
            "https://api.groq.com/openai/v1/chat/completions";

    /*
     * Groq API key.
     *
     * Add your key to Windows Environment Variables:
     *
     * GROQ_API_KEY
     *
     * Do NOT put the real API key directly inside Git-tracked code.
     */
private static final String API_KEY =
        Dotenv.configure()
                .directory("../")
                .ignoreIfMissing()
                .load()
                .get("GROQ_API_KEY", System.getenv("GROQ_API_KEY"));

    /*
     * Groq model.
     */
    private static final String MODEL =
            "qwen/qwen3.6-27b";


    private final HttpClient httpClient;


    public GroqService() {

        httpClient =
                HttpClient.newHttpClient();
    }


    /**
     * Sends a text-only farming question to Groq.
     */
    public String askQuestion(
            String question)
            throws IOException, InterruptedException {

        validateApiKey();


        String systemPrompt =
                "You are AgroBiz AI Farming Advisor. "
                + "You are an expert agricultural assistant helping Indian farmers. "
                + "Give practical, clear and easy-to-understand farming guidance. "
                + "Consider Indian farming conditions when relevant. "
                + "Do not invent facts. "
                + "If a question requires a veterinarian, agricultural officer, soil laboratory, "
                + "or other professional, clearly recommend consulting the appropriate expert. "
                + "Use simple language suitable for farmers. "
                + "IMPORTANT: Do not show your internal reasoning or thinking process. "
                + "Do not write things such as 'thinking process', 'analyze user input', "
                + "or 'identify guidelines'. "
                + "Give only the final answer to the farmer. "
                + "Do not wrap the entire answer in ** symbols.";


        String escapedSystem =
                escapeJson(systemPrompt);


        String escapedQuestion =
                escapeJson(question);


        String json =
                "{"
                + "\"model\":\"" + MODEL + "\","
                + "\"messages\":["
                + "{"
                + "\"role\":\"system\","
                + "\"content\":\"" + escapedSystem + "\""
                + "},"
                + "{"
                + "\"role\":\"user\","
                + "\"content\":\"" + escapedQuestion + "\""
                + "}"
                + "]"
                + "}";


        return sendRequest(json);
    }


    /**
     * Sends a farming question together with an image.
     */
    public String askQuestionWithImage(
            String question,
            Path imagePath)
            throws IOException, InterruptedException {

        validateApiKey();


        if (imagePath == null ||
                !Files.exists(imagePath)) {

            return askQuestion(question);
        }


        byte[] imageBytes =
                Files.readAllBytes(imagePath);


        String base64Image =
                Base64.getEncoder()
                        .encodeToString(imageBytes);


        String mimeType =
                Files.probeContentType(imagePath);


        if (mimeType == null) {

            mimeType =
                    "image/jpeg";
        }


        String imageDataUrl =
                "data:"
                + mimeType
                + ";base64,"
                + base64Image;


        String systemPrompt =
                "You are AgroBiz AI Farming Advisor. "
                + "You are an expert agricultural assistant helping Indian farmers. "
                + "Analyze the provided farm image carefully when relevant. "
                + "Give practical, clear and easy-to-understand farming guidance. "
                + "Consider Indian farming conditions when relevant. "
                + "Do not claim a disease or problem with certainty from an image alone. "
                + "If the image is insufficient for diagnosis, clearly say so. "
                + "For serious crop disease, pest infestation, animal disease, "
                + "or other high-risk situations, recommend consulting the appropriate expert. "
                + "Use simple language suitable for farmers. "
                + "IMPORTANT: Do not show your internal reasoning or thinking process. "
                + "Give only the final answer to the farmer. "
                + "Do not wrap the entire answer in ** symbols.";


        String escapedSystem =
                escapeJson(systemPrompt);


        String escapedQuestion =
                escapeJson(question);


        String escapedImage =
                escapeJson(imageDataUrl);


        String json =
                "{"
                + "\"model\":\"" + MODEL + "\","
                + "\"messages\":["
                + "{"
                + "\"role\":\"system\","
                + "\"content\":\"" + escapedSystem + "\""
                + "},"
                + "{"
                + "\"role\":\"user\","
                + "\"content\":["
                + "{"
                + "\"type\":\"text\","
                + "\"text\":\"" + escapedQuestion + "\""
                + "},"
                + "{"
                + "\"type\":\"image_url\","
                + "\"image_url\":{"
                + "\"url\":\"" + escapedImage + "\""
                + "}"
                + "}"
                + "]"
                + "}"
                + "]"
                + "}";


        return sendRequest(json);
    }


    /**
     * Sends HTTP request to Groq.
     */
    private String sendRequest(
            String json)
            throws IOException, InterruptedException {

        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(
                                URI.create(API_URL))
                        .header(
                                "Content-Type",
                                "application/json")
                        .header(
                                "Authorization",
                                "Bearer " + API_KEY)
                        .POST(
                                HttpRequest.BodyPublishers
                                        .ofString(json))
                        .build();


        HttpResponse<String> response =
                httpClient.send(
                        request,
                        HttpResponse.BodyHandlers
                                .ofString());


        if (response.statusCode() >= 200 &&
                response.statusCode() < 300) {

            return extractAssistantResponse(
                    response.body());
        }


        throw new IOException(
                "Groq API Error "
                + response.statusCode()
                + ": "
                + response.body());
    }


    /**
     * Extracts choices[0].message.content
     * from Groq JSON response.
     */
    private String extractAssistantResponse(
            String json)
            throws IOException {

        String marker =
                "\"content\":\"";


        int start =
                json.indexOf(marker);


        if (start == -1) {

            throw new IOException(
                    "Could not read AI response from Groq.");
        }


        start += marker.length();


        StringBuilder result =
                new StringBuilder();


        boolean escaped =
                false;


        for (int i = start;
             i < json.length();
             i++) {

            char c =
                    json.charAt(i);


            if (escaped) {

                switch (c) {

                    case 'n':
                        result.append('\n');
                        break;


                    case 'r':
                        result.append('\r');
                        break;


                    case 't':
                        result.append('\t');
                        break;


                    case '"':
                        result.append('"');
                        break;


                    case '\\':
                        result.append('\\');
                        break;


                    case '/':
                        result.append('/');
                        break;


                    case 'b':
                        result.append('\b');
                        break;


                    case 'f':
                        result.append('\f');
                        break;


                    /*
                     * Handle Unicode escape sequences.
                     *
                     * This is important because:
                     *
                     * \u003c = <
                     * \u003e = >
                     */
                    case 'u':

                        if (i + 4 < json.length()) {

                            String hex =
                                    json.substring(
                                            i + 1,
                                            i + 5);


                            try {

                                char unicodeChar =
                                        (char) Integer.parseInt(
                                                hex,
                                                16);

                                result.append(
                                        unicodeChar);

                                i += 4;

                            } catch (NumberFormatException e) {

                                result.append('u');
                            }

                        } else {

                            result.append('u');
                        }

                        break;


                    default:
                        result.append(c);
                        break;
                }


                escaped =
                        false;


            } else if (c == '\\') {

                escaped =
                        true;


            } else if (c == '"') {

                break;


            } else {

                result.append(c);
            }
        }


        String answer =
                result.toString();


        /*
         * Clean the AI response before
         * displaying it in the JavaFX UI.
         */
        return cleanAIResponse(answer);
    }


    /**
     * Removes unwanted AI reasoning/thinking
     * and Markdown formatting.
     */
    private String cleanAIResponse(
            String response) {

        if (response == null) {

            return "";
        }


        String cleaned =
                response.trim();


        // ========================================================
        // REMOVE <think>...</think>
        // ========================================================

        /*
         * Some reasoning models return:
         *
         * <think>
         * internal reasoning...
         * </think>
         *
         * We only want the final answer.
         */

        int thinkStart =
                cleaned.indexOf(
                        "<think>");


        int thinkEnd =
                cleaned.indexOf(
                        "</think>");


        if (thinkStart != -1 &&
                thinkEnd != -1 &&
                thinkEnd > thinkStart) {

            cleaned =
                    cleaned.substring(
                            thinkEnd + "</think>".length()
                    ).trim();
        }


        // ========================================================
        // HANDLE CASE WHERE THINK TAGS ARE STILL ENCODED
        // ========================================================

        cleaned =
                cleaned.replace(
                        "u003cthinku003e",
                        ""
                );


        cleaned =
                cleaned.replace(
                        "u003c/thinku003e",
                        ""
                );


        cleaned =
                cleaned.replace(
                        "u003cthink>",
                        ""
                );


        cleaned =
                cleaned.replace(
                        "u003c/think>",
                        ""
                );


        // ========================================================
        // REMOVE MARKDOWN BOLD SYMBOLS
        // ========================================================

        /*
         * Converts:
         *
         * **Analyze User Input:**
         *
         * into:
         *
         * Analyze User Input:
         */

        cleaned =
                cleaned.replace(
                        "**",
                        ""
                );


        // ========================================================
        // REMOVE EXTRA THINKING PHRASES
        // ========================================================

        cleaned =
                cleaned.replace(
                        "Here's a thinking process:",
                        ""
                );


        cleaned =
                cleaned.replace(
                        "Here is a thinking process:",
                        ""
                );


        // ========================================================
        // REMOVE EXTRA BLANK LINES
        // ========================================================

        cleaned =
                cleaned.replaceAll(
                        "\\n{3,}",
                        "\n\n"
                );


        return cleaned.trim();
    }


    /**
     * Makes a String safe for JSON.
     */
    private String escapeJson(
            String text) {

        if (text == null) {

            return "";
        }


        return text
                .replace(
                        "\\",
                        "\\\\")
                .replace(
                        "\"",
                        "\\\"")
                .replace(
                        "\r",
                        "\\r")
                .replace(
                        "\n",
                        "\\n")
                .replace(
                        "\t",
                        "\\t")
                .replace(
                        "\b",
                        "\\b")
                .replace(
                        "\f",
                        "\\f");
    }


    /**
     * Checks whether the API key exists.
     */
    private void validateApiKey()
            throws IOException {

        if (API_KEY == null ||
                API_KEY.isBlank()) {

            throw new IOException(
                    "GROQ_API_KEY environment variable is not set.\n\n"
                    + "Please add your Groq API key to Windows Environment Variables "
                    + "and restart your IDE.");
        }
    }



/**
 * Generates a complete farming plan from structured farmer information.
 *
 * This method is specifically used by the AgroBiz
 * guided Farming Plan Generator in FarmerDashboard.
 */
public String generateFarmingPlan(
        String farmingType,
        Map<String, String> farmerDetails)
        throws IOException, InterruptedException {

    validateApiKey();

    if (farmingType == null || farmingType.isBlank()) {

        throw new IOException(
                "Farming type is required.");
    }

    if (farmerDetails == null ||
            farmerDetails.isEmpty()) {

        throw new IOException(
                "Farmer information is required.");
    }

    // =========================================================
    // SYSTEM PROMPT
    // =========================================================

    String systemPrompt =
            "You are AgroBiz Farming Plan AI. "

            + "You are an expert agricultural planning assistant "
            + "helping Indian farmers create practical farming plans. "

            + "IMPORTANT: Your response must ONLY contain information "
            + "related to the selected farming activity. "

            + "Do not discuss unrelated topics. "

            + "Create a practical plan using the farmer's actual "
            + "information provided in the user message. "

            + "Do not invent missing farmer information. "

            + "If important information is missing, clearly state "
            + "the assumption you made or mention that the farmer "
            + "should verify it locally. "

            + "Consider Indian farming conditions when relevant. "

            + "Costs must be treated as estimates and should not "
            + "be presented as guaranteed prices. "

            + "Do not promise guaranteed profit or guaranteed yield. "

            + "Separate one-time setup costs from recurring costs. "

            + "Include important risks and practical ways to reduce "
            + "those risks. "

            + "Use simple language suitable for Indian farmers. "

            + "Give practical step-by-step guidance. "

            + "Do not show internal reasoning or thinking process. "

            + "Do not write things such as thinking process, "
            + "analysis, chain of thought, or internal reasoning. "

            + "Give only the final farming plan.";

    // =========================================================
    // BUILD FARMER INFORMATION
    // =========================================================

    StringBuilder farmerInformation =
            new StringBuilder();

    farmerInformation.append(
            "Selected farming type: ")
            .append(farmingType)
            .append("\n\n");

    farmerInformation.append(
            "Farmer information:\n");

    for (Map.Entry<String, String> entry :
            farmerDetails.entrySet()) {

        farmerInformation
                .append("- ")
                .append(entry.getKey())
                .append(": ")
                .append(entry.getValue())
                .append("\n");
    }

    // =========================================================
    // FINAL OUTPUT INSTRUCTIONS
    // =========================================================

    farmerInformation.append(
            """

            Create the farming plan using the following structure:

            1. FARMING PLAN SUMMARY

            Give a short overview of the proposed farming activity.

            2. FARMER REQUIREMENTS

            Summarize:
            - available area
            - planned capacity
            - budget
            - water
            - electricity
            - infrastructure
            - labour
            - experience
            - other important information

            3. FARM SETUP REQUIREMENTS

            Explain the infrastructure, equipment, materials,
            housing, growing area, shed, pond, beds, tanks,
            or other requirements appropriate for this farming type.

            4. STEP-BY-STEP FARMING PROCESS

            Explain the complete process from starting the farm
            through production and harvesting/marketing.

            5. INPUT REQUIREMENTS

            Explain the important inputs required for this
            particular farming activity.

            6. DAILY AND WEEKLY MANAGEMENT

            Give practical management activities.

            7. LABOUR REQUIREMENT

            Explain the approximate labour requirement based on
            the farmer's planned scale.

            8. WATER AND RESOURCE MANAGEMENT

            Explain water and other important resource requirements.

            9. PRODUCTION TIMELINE

            Explain the major stages and approximate timeline.

            10. ESTIMATED INITIAL SETUP COST

            Provide an estimated breakdown such as:

            Item | Estimated Cost

            Use reasonable estimates and clearly mention that
            actual prices vary by location and market.

            11. ESTIMATED RECURRING COST

            Separate recurring expenses such as inputs,
            feed, labour, electricity, maintenance, etc.,
            according to the farming type.

            12. ESTIMATED TOTAL BUDGET

            Summarize the approximate initial and recurring costs.

            13. EXPECTED PRODUCTION / OUTPUT

            Give realistic estimates where possible.
            Never guarantee production.

            14. MARKETING PLAN

            Explain practical ways the farmer can sell the
            produced farm output.

            15. MAJOR RISKS

            List the important risks specific to this
            farming activity.

            16. RISK MANAGEMENT

            Explain how the farmer can reduce or manage each risk.

            17. IMPORTANT PRECAUTIONS

            Give important farming precautions.

            18. FIRST 30 DAYS ACTION PLAN

            Give a practical checklist of what the farmer should
            do during the first 30 days.

            19. FINAL RECOMMENDATION

            Give a short practical conclusion based on the
            farmer's available resources and selected farming type.

            IMPORTANT:

            Do not provide information about another type of farming.

            For example, if the selected farming type is Poultry,
            do not provide goat, dairy, mushroom, fish, pearl,
            or unrelated crop farming information.

            Keep the plan practical and easy to understand.
            """);

    // =========================================================
    // ESCAPE JSON
    // =========================================================

    String escapedSystem =
            escapeJson(systemPrompt);

    String escapedFarmerInformation =
            escapeJson(
                    farmerInformation.toString());

    // =========================================================
    // JSON REQUEST
    // =========================================================

    String json =
            "{"
            + "\"model\":\"" + MODEL + "\","
            + "\"messages\":["
            + "{"
            + "\"role\":\"system\","
            + "\"content\":\""
            + escapedSystem
            + "\""
            + "},"
            + "{"
            + "\"role\":\"user\","
            + "\"content\":\""
            + escapedFarmerInformation
            + "\""
            + "}"
            + "]"
            + "}";

    return sendRequest(json);
    }

        // =========================================================
        // BUYER AI QUESTION
        // =========================================================

        public String askBuyerQuestion(
                String question)
                throws IOException, InterruptedException {

        validateApiKey();

        if (question == null ||
                question.isBlank()) {

                throw new IOException(
                        "Buyer question is empty.");
        }

        String systemPrompt =
                "You are AgroBiz AI Buyer Advisor. "

                + "You are an expert assistant helping agricultural "
                + "buyers in India. "

                + "Help buyers understand agricultural markets, "
                + "produce quality, procurement, grading, storage, "
                + "transport, logistics, pricing considerations, "
                + "and purchasing decisions. "

                + "Give practical and easy-to-understand answers. "

                + "Consider Indian agricultural conditions when relevant. "

                + "Do not invent current market prices. "

                + "If the user asks for a current price and you do not "
                + "have verified live market data, clearly say that "
                + "the price needs to be checked from a current "
                + "market or official source. "

                + "Do not guarantee profit, quality, yield, or price. "

                + "When discussing produce quality, explain visible "
                + "quality indicators and practical buying checks. "

                + "When discussing storage, explain appropriate "
                + "storage considerations without inventing exact "
                + "conditions when they are unknown. "

                + "Use simple language. "

                + "Do not reveal internal reasoning or chain of thought. "

                + "Give only the final answer.";

        String escapedSystem =
                escapeJson(systemPrompt);

        String escapedQuestion =
                escapeJson(question);

        String json =
                "{"
                + "\"model\":\"" + MODEL + "\","
                + "\"messages\":["
                + "{"
                + "\"role\":\"system\","
                + "\"content\":\""
                + escapedSystem
                + "\""
                + "},"
                + "{"
                + "\"role\":\"user\","
                + "\"content\":\""
                + escapedQuestion
                + "\""
                + "}"
                + "]"
                + "}";

        return sendRequest(json);
        }


        // =========================================================
        // BUYER AI IMAGE QUESTION
        // =========================================================

        public String askBuyerQuestionWithImage(
                String question,
                Path imagePath)
                throws IOException, InterruptedException {

        validateApiKey();

        if (question == null ||
                question.isBlank()) {

                throw new IOException(
                        "Buyer image question is empty.");
        }

        if (imagePath == null ||
                !Files.exists(imagePath)) {

                return askBuyerQuestion(question);
        }

        byte[] imageBytes =
                Files.readAllBytes(imagePath);

        /*
        * Groq currently supports qwen/qwen3.6-27b
        * for image input, with a 20 MB image/request limit.
        */
        if (imageBytes.length > 20 * 1024 * 1024) {

                throw new IOException(
                        "Image is larger than 20 MB. "
                        + "Please select a smaller image.");
        }

        String base64Image =
                Base64.getEncoder()
                        .encodeToString(imageBytes);

        String mimeType =
                Files.probeContentType(imagePath);

        if (mimeType == null) {
                mimeType = "image/jpeg";
        }

        String imageDataUrl =
                "data:"
                + mimeType
                + ";base64,"
                + base64Image;

        String systemPrompt =
                "You are AgroBiz AI Buyer Advisor. "

                + "You are helping an agricultural buyer "
                + "evaluate agricultural produce from an image. "

                + "Analyze only what can reasonably be observed "
                + "from the provided image. "

                + "Discuss visible quality characteristics such as "
                + "appearance, color, size, uniformity, visible damage, "
                + "bruising, spoilage indicators, contamination "
                + "indicators, cleanliness, and possible grading "
                + "considerations when visible. "

                + "Do not claim that an image proves freshness, "
                + "chemical residue, pesticide residue, internal "
                + "quality, nutritional value, disease, or safety. "

                + "Clearly distinguish visible observations from "
                + "things that require physical inspection or testing. "

                + "Give practical buying checks the buyer should perform "
                + "before purchasing. "

                + "Do not guarantee quality or safety. "

                + "Use simple language. "

                + "Do not reveal internal reasoning or chain of thought. "

                + "Give only the final answer.";

        String escapedSystem =
                escapeJson(systemPrompt);

        String escapedQuestion =
                escapeJson(question);

        String escapedImage =
                escapeJson(imageDataUrl);

        String json =
                "{"
                + "\"model\":\"" + MODEL + "\","
                + "\"messages\":["
                + "{"
                + "\"role\":\"system\","
                + "\"content\":\""
                + escapedSystem
                + "\""
                + "},"
                + "{"
                + "\"role\":\"user\","
                + "\"content\":["
                + "{"
                + "\"type\":\"text\","
                + "\"text\":\""
                + escapedQuestion
                + "\""
                + "},"
                + "{"
                + "\"type\":\"image_url\","
                + "\"image_url\":{"
                + "\"url\":\""
                + escapedImage
                + "\""
                + "}"
                + "}"
                + "]"
                + "}"
                + "]"
                + "}";

        return sendRequest(json);

        }
        }