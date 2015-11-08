package auctionRMI.interfaces;

import com.mnr.auction.card.CardType;
import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

public interface AuctionInterface extends Remote {
    /**
     * Store Methods
     */

    ItemType addItemType(String name) throws RemoteException;
    Item addItem(int itemTypeId, String name, String description, int startPrice) throws RemoteException;
    ArrayList<Item> searchItems(int itemTypeId, String query) throws RemoteException;

    /**
     * User Methods
     */

    boolean register(String email, String username, String password, String address) throws RemoteException;
    String logIn(String username, String password) throws RemoteException;
    void logOut(String token) throws RemoteException;
    void addCard(Date date, int number, CardType type, String token) throws RemoteException;
    String placeBid (int itemId, String token, float bid) throws RemoteException;

    /**
     * Auction Methods
     */

    void startAuction(Date startDate, Date endDate, int itemId) throws RemoteException;
}
