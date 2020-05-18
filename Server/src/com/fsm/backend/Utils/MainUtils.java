package com.fsm.backend.Utils;

import com.fsm.backend.Annotation.Controller;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class MainUtils {

    private static final String CONTROLLER_PACKAGE = "com.fsm.backend.Controller";
    private static final int HTTP_SERVER_PORT = 8080;

    public static void startHTTPServer() {
        HttpServer server = Objects.requireNonNull(createServer());
        List<Class<?>> controllers = MainUtils.
                getControllers(CONTROLLER_PACKAGE);
        createContexts(server, controllers);

        server.setExecutor(null);
        server.start();
        System.out.println("Http Server started on port: " + HTTP_SERVER_PORT);
    }

    public static Object getInstance(Class<?> cls) {
        Object instance = null;
        try {
            //noinspection ConfusingArgumentToVarargsMethod
            instance = Optional.of(cls.
                    getDeclaredConstructor(null)
                    .newInstance(null))
                    .orElseThrow(() -> new RuntimeException("instance could not be created"));
        } catch (IllegalAccessException |
                InvocationTargetException |
                InstantiationException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(instance);
    }

    public static List<Class<?>> getControllers(String packageName) {
        return PathScanner.getInstance()
                .getClassesOf(packageName,
                        cls -> cls.isAnnotationPresent(Controller.class));
    }

    public static void createContexts(HttpServer server, List<Class<?>> controllers) {
        controllers.forEach(cls ->
                server.createContext('/' + getPath(cls),
                        (HttpHandler) getInstance(cls)));
    }

    private static String getPath(Class<?> cls) {
        return cls.getAnnotation(Controller.class).path();
    }

    public static HttpServer createServer() {
        try {
            return HttpServer.create(
                    new InetSocketAddress(HTTP_SERVER_PORT), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        OutputStream body = httpExchange.getResponseBody();
        body.write(response.getBytes());
        body.close();
    }

}
