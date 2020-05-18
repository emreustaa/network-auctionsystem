package com.fsm.frontend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.frontend.Ekranlar.Muzayede.MuzayedeEkrani;
import com.fsm.frontend.Objects.Message.Message;
import com.fsm.frontend.Objects.User.Credentials.SignUpCredentials;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class RestUtils {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String HOST = MuzayedeEkrani.getHOST();
    private static final int HTTP_SERVER_PORT = MuzayedeEkrani.getHttpServerPort();
    private static final String HTTP_SERVER_ADDRESS = "http://" + HOST + ":" + HTTP_SERVER_PORT;
    private static final ObjectMapper MAPPER = ClientUtils.getMapper();

    public static HttpResponse<String> sendMessageToHttpServer(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(response);
    }

    public static HttpRequest prepareGET(String address) {
        return HttpRequest.newBuilder()
                .uri(getUri(address))
                .header("Content-Type", "application/json")
                .build();
    }

    public static Message createUser(SignUpCredentials credentials) {
        String address = "/users/createUser";
        Message signUpCommand = ClientUtils.getUserSignUpCommand(credentials);
        return getPOSTResponse(address, signUpCommand);
    }

    public static HttpRequest preparePOST(String address, Message message) {
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(getUri(address))
                        .header("Content-Type", "application/json")
                        .POST(getBody(message))
                        .build();
        return Objects.requireNonNull(request);
    }

    private static URI getUri(String address) {
        return URI.create(HTTP_SERVER_ADDRESS + address);
    }

    private static HttpRequest.BodyPublisher getBody(Message message) {
        HttpRequest.BodyPublisher bodyPublisher = null;
        try {
            bodyPublisher = HttpRequest.BodyPublishers.ofString(MAPPER.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(bodyPublisher);
    }

    private static Message getPOSTResponse(String address, Message message) {
        HttpRequest request = preparePOST(address, message);
        HttpResponse<String> response = sendMessageToHttpServer(request);
        return (Message) ClientUtils.getMessageFrom(response.body());
    }

    public static SignUpCredentials getCredentialsById(String userName) {
        String address = String.format("/users/getUserById?id=%s", userName);
        HttpRequest request = prepareGET(address);
        HttpResponse<String> response = sendMessageToHttpServer(request);
        SignUpCredentials credentials = null;
        try {
            credentials = MAPPER.readValue(
                    response.body(), SignUpCredentials.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return credentials;
    }

}
