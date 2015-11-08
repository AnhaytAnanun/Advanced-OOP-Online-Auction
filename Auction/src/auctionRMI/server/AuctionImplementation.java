package auctionRMI.server;

import com.mnr.auction.Dispatcher;
import com.mnr.auction.card.CardType;
import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;
import auctionRMI.interfaces.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

public class AuctionImplementation extends UnicastRemoteObject implements AuctionInterface {
    private Dispatcher dispatcher;

    public AuctionImplementation() throws RemoteException {
        dispatcher = Dispatcher.sharedDispatcher();
    }

    /**
     * Store Methods
     */

    @Override
    public ItemType addItemType(String name) {
        return dispatcher.addItemType(name);
    }

    @Override
    public Item addItem(int itemTypeId, String name, String description, int startPrice) {
        return dispatcher.addItem(itemTypeId, name, description, startPrice);
    }

    @Override
    public ArrayList<Item> searchItems(int itemTypeId, String query) {
        return dispatcher.searchItems(itemTypeId, query);
    }

    /**
     * User Methods
     */

    @Override
    public boolean register(String email, String username, String password, String address) {
        return dispatcher.register(email, username, password, address);
    }

    @Override
    public String logIn(String username, String password) {
        return dispatcher.logIn(username, password);
    }

    @Override
    public void logOut(String token) {
        dispatcher.logOut(token);
    }

    @Override
    public void addCard(Date date, int number, CardType type, String token) {
        dispatcher.addCard(date, number, type, token);
    }

    @Override
    public String placeBid (int itemId, String token, float bid) {
        return dispatcher.placeBid(itemId, token, bid);
    }

    /**
     * Auction Methods
     */

    @Override
    public void startAuction(Date startDate, Date endDate, int itemId) {
        dispatcher.startAuction(startDate, endDate, itemId);
    }
}
