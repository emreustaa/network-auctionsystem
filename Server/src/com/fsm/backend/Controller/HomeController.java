package com.fsm.backend.Controller;

import com.fsm.backend.Annotation.Action;
import com.fsm.backend.Annotation.Controller;
import com.fsm.backend.Annotation.QueryParam;
import com.fsm.backend.Enums.TYPE;
import com.fsm.backend.Interfaces.MyHttpHandler;
import com.fsm.backend.Objects.Auction.Auction;
import com.fsm.backend.Objects.Auction.Bid;
import com.fsm.backend.Objects.DAO.AuctionDAO;
import com.fsm.backend.Objects.Message.Message;
import com.fsm.backend.Utils.Broadcast;
import com.fsm.backend.Utils.ControllerUtils;

@SuppressWarnings("unused")
@Controller(path = "auctions")
public class HomeController implements MyHttpHandler {

    @Action(path = "createAuction", type = TYPE.POST)
    public Message createAuction(@QueryParam(type = Message.class) Message message) {
        Auction auction = new Auction(message.getEvent().getAuctionDAO().getItem());
        Auction.repository.add(auction);
        Message auctionCreated = ControllerUtils.getAuctionCreatedEvent(auction);
        Broadcast.broadCastMessage(auctionCreated);
        System.out.println("Create auction requested");
        return ControllerUtils.getDummyResponse();
    }

    @Action(path = "getAllAuctions")
    public Message getAllAuctions() {
        return ControllerUtils
                .getAuctionsRsp(Auction.
                        repository.getAllAsDAO());
    }

    @Action(path = "getAuctionById")
    public AuctionDAO getAuctionById(String id) {
        return AuctionDAO.getDAOFromAuction(Auction.repository.findById(id));
    }

    @Action(path = "updatePrice", type = TYPE.POST)
    public Message updatePrice(@QueryParam(type = Message.class) Message message) {
        Bid update = ControllerUtils.updatePrice(message.getEvent().getBid());
        Message event = ControllerUtils.getPriceUpdatedEvent(update);
        System.out.println("PRICE UPDATED");
        Broadcast.broadCastMessage(event);

        return ControllerUtils.getDummyResponse();
    }

}
