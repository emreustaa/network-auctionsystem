package com.fsm.backend.Objects.Auction;

import com.fsm.backend.Interfaces.ListRepository;
import com.fsm.backend.Objects.DAO.AuctionDAO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionRepository extends ListRepository<Auction> {

    public AuctionRepository() { }

    public AuctionRepository(Collection<Auction> sample) {
        super(sample);
    }

    public List<AuctionDAO> getAllAsDAO() {
        return this.stream()
                .map(AuctionDAO::getDAOFromAuction)
                .collect(Collectors.toList());
    }

}
