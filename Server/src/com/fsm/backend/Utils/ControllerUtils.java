package com.fsm.backend.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.DAO.AuctionDAO;
import com.fsm.backend.Objects.Message.*;
import com.fsm.backend.Utils.Request.ParamHandler;
import com.fsm.backend.Utils.Request.Request;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Predicate;

public class ControllerUtils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Request getRequest(HttpExchange exchange) {

        String action = getAction(exchange);
        TYPE type = TYPE.valueOf(exchange.getRequestMethod());
        Map<String, Object> params = ParamHandler.getParams(exchange);
        Class<? extends HttpHandler> controller = getHandler(exchange);
        String body = ParamHandler.getBody(exchange);

        return new Request()
                .setAction(action)
                .setController(controller)
                .setParams(params)
                .setType(type)
                .setBody(body);
    }

    private static Class<? extends HttpHandler> getHandler(HttpExchange httpExchange) {
        return httpExchange.getHttpContext().getHandler().getClass();
    }

    public static String responseAsJsonString(Request request) {
        Method method = getMethod(request);
        return getResponse(method, request);
    }

    private static String getResponse(Method method, Request request) {
        String result = "error in getResult";
        try {
            Object res = getInvokeResult(method, request);
            result = mapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(res);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(result);
    }

    private static Object getInvokeResult(Method method, Request request) {
        Object result = null;
        //Object result = new Object();
        try {
            Object[] args = getArgs(request, method);
            result = method.invoke(
                    getInstanceOf(request.getController()),
                    args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(result);
    }

    private static Object[] getArgs(Request request, Method method) {
        if (request.getType().equals(TYPE.GET)) {
            if (request.getParams().isEmpty()) return null;
        }

        if (request.getType().equals(TYPE.POST)) {
            Object object = getTargetObject(request.getBody(), method);
            return new Object[]{object};
        }
        return request.getParams().values().toArray();
    }

    public static Object getTargetObject(String body, Method targetMethod) {
        Class<?> paramType = ParamHandler.getParamType(targetMethod);
        return ParamHandler.getObjectFrom(body, paramType);
    }

    private static String getAction(HttpExchange httpExchange) {
        String path = httpExchange.getRequestURI().getPath();
        //substring to remove space at the beginning of parts
        String[] parts = path.substring(1).split("/");
        return parts.length > 1 ? parts[1] : "index";
    }

    private static Method getMethod(Request request) {
        Method result = null;
        for (Method method : request.getController().getMethods()) {
            Action action = method.getAnnotation(Action.class);
            if (pathAndTypeMatches(request, action)) {
                result = method;
                break;
            }
        }
        return Objects.requireNonNull(result);
    }

    private static boolean pathAndTypeMatches(Request request, Action target) {
        return target.path().equals(request.getAction()) &&
                target.type().equals(request.getType());
    }

    public static Message getServerStoppedCommand() {
        return new Message()
                .setCommand(new Command(MessageRepo.STOP_SERVER_CMD));
    }

    public static Object getObjectFrom(String jsonString) {
        Object message = null;
        try {
            message = mapper.readValue(jsonString, Message.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(message);
    }

    public static String getJsonString(Message message) {
        String jsonString = null;
        try {
            jsonString = ControllerUtils.
                    getMapper().
                    writerWithDefaultPrettyPrinter()
                    .writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return Objects.requireNonNull(jsonString);
    }

    private static Object getInstanceOf(Class<?> controller) {
        return MainUtils.getInstance(controller);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static Message getAuctionCreatedEvent(Auction auction) {
        return new Message()
                .setEvent(new Event(MessageRepo.AUCTION_CREATED_EVT)
                        .setAuctionDAO(AuctionDAO.getDAOFromAuction(auction)));
    }

    public static Message getPriceUpdatedEvent(Bid bid) {
        return new Message()
                .setEvent(new Event(MessageRepo.PRICE_UPDATED_EVT)
                        .setBid(bid));
    }

    public static Message getDummyResponse() {
        return new Message()
                .setResponse(new Response(MessageRepo.DUMMY_RSP));
    }

    public static Bid updatePrice(Bid bid) {
        return Auction.repository
                .findById(bid.getAuctionId())
                .updatePrice(bid);
    }

    public static Message getAuctionsRsp(List<AuctionDAO> auctions) {
        return new Message()
                .setResponse(new Response(MessageRepo.GET_AUCTIONS_RSP)
                .setAuctionDAOs(auctions));
    }

    public static Predicate<UUID> toSameAuction(UUID TargetAuctionId) {
        return auctionId -> auctionId.equals(TargetAuctionId);
    }

}
