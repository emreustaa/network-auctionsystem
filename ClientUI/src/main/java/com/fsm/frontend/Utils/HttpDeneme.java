package com.fsm.frontend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.frontend.Objects.Message.Message;
import com.fsm.frontend.Objects.User.Credentials.LoginCredentials;
import com.fsm.frontend.Objects.User.Credentials.SignUpCredentials;
import com.fsm.frontend.Objects.User.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.BodyPublisher;

import java.net.http.HttpResponse;
import java.util.Objects;

public class HttpDeneme {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String prefix = "http://localhost:8080";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        //testCreateUser();

        //LoginCredentials credentials = new LoginCredentials("ahmad", "pass", 123);
        //testLogin(credentials);
        //getAllAuctions();
        //getUserById();
    }

    private static void testCreateUser() {
        SignUpCredentials credentials = new SignUpCredentials("ahmad", "123", "ahmadMail", 500);
        Message result = createUser(credentials);

        if(result.isError()) {
            System.out.println(result.getError().getErrorMessage());
            return;
        }

        SignUpCredentials userInfo = result.getResponse().getCredentials();
        User user = new User(userInfo);
        System.out.println("userName: " +user.getUserName());
    }

    private static void testLogin(LoginCredentials credentials) {

        Message result = login(credentials);

        if (result.isError()) {
            System.out.println(result.getError().getErrorMessage());
            return;
        }

        SignUpCredentials userInfo = result.getResponse()
                .getCredentials();
        User user = new User(userInfo);
        System.out.println("userName: " +user.getUserName());
    }

    private static Message login(LoginCredentials credentials) {
        String address = "/users/login";
        Message loginCommand = ClientUtils.getUserLoginCommand(credentials);
        return getPOSTResponse(address, loginCommand);
    }

    private static Message createUser(SignUpCredentials credentials) {
        String address = "/users/createUser";
        Message signUpCommand = ClientUtils.getUserSignUpCommand(credentials);
        return getPOSTResponse(address, signUpCommand);
    }

    private static Message getPOSTResponse(String address, Message message) {
        HttpRequest request = preparePOST(address, message);
        HttpResponse<String> response = send(request);
        return (Message) ClientUtils.getMessageFrom(response.body());
    }

    private static void getUserById() {
        SignUpCredentials credentials = getUserById("");
        User user = new User(credentials);
        System.out.println(user);
    }

    private static void getAllAuctions() {
        String address = "/auctions/getAllAuctions";
        HttpRequest request = prepareGET(address);
        HttpResponse<String> response = send(request);
        System.out.println(response.body());
    }

    private static SignUpCredentials getUserById(String id) {
        String address = String.format("/users/getUserById?id=%s", id);
        HttpRequest request = prepareGET(address);
        HttpResponse<String> response = send(request);
        SignUpCredentials credentials = null;
        try {
            credentials = mapper.readValue(
                    response.body(), SignUpCredentials.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    private static HttpResponse<String> send(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(response);
    }

    private static HttpRequest prepareGET(String address) {
        return HttpRequest.newBuilder()
                .uri(getUri(address))
                .header("Content-Type", "application/json")
                .build();
    }

    private static HttpRequest preparePOST(String address, Message message) {
        HttpRequest request =
                HttpRequest.newBuilder()
                        .uri(getUri(address))
                        .header("Content-Type", "application/json")
                        .POST(getBody(message))
                        .build();
        return Objects.requireNonNull(request);
    }

    private static URI getUri(String address) {
        return URI.create(prefix + address);
    }

    private static BodyPublisher getBody(Message message) {
        BodyPublisher bodyPublisher = null;
        try {
            bodyPublisher = BodyPublishers.ofString(mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(bodyPublisher);
    }

}
