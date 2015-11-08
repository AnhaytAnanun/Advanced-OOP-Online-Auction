package auctionRMI.client;

import com.mnr.auction.card.CardType;
import com.mnr.auction.item.Item;
import com.mnr.auction.item.ItemType;
import auctionRMI.interfaces.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class TestClient {
    private static final String FIRST_EMAIL = "email@email.email";
    private static final String FIRST_USERNAME = "first";
    private static final String FIRST_PASSWORD = "qwerty";

    private static final String SECOND_EMAIL = "mail@mail.mail";
    private static final String SECOND_USERNAME = "second";
    private static final String SECOND_PASSWORD = "123456";

    public static void main(String[] args) {
        try {
            AuctionInterface auctionServer = (AuctionInterface) Naming.lookup("rmi://localhost/auctionRMI");
            startTesting(auctionServer);
        } catch (NotBoundException e) {
            System.out.println("Cannot locate server");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("Bad server name");
            e.printStackTrace();
        } catch(Exception e) {
            System.out.println("Cannot create a server object");
            e.printStackTrace();
        }
    }

    private static void startTesting(final AuctionInterface auctionServer) throws RemoteException {
        /**
         * Create System
         */

        int itemTypeId = addItemType(auctionServer);
        addItem(itemTypeId, auctionServer);
        addItem(-1, auctionServer);
        final ArrayList<Item> items = searchItems(itemTypeId, auctionServer);

        register(FIRST_EMAIL, FIRST_USERNAME, FIRST_PASSWORD, auctionServer);
        register(SECOND_EMAIL, SECOND_USERNAME, SECOND_PASSWORD, auctionServer);
        register(SECOND_EMAIL, SECOND_USERNAME, SECOND_PASSWORD, auctionServer);

        final String firstToken = login(FIRST_USERNAME, FIRST_PASSWORD, auctionServer);
        final String secondToken = login(SECOND_USERNAME, SECOND_PASSWORD, auctionServer);

        placeBid(items.get(0).getId(), secondToken, 510000, auctionServer);

        addAuction(items.get(0).getId(), auctionServer);

        placeBid(items.get(0).getId(), firstToken, 600000, auctionServer);
        placeBid(items.get(0).getId(), secondToken, 510000, auctionServer);

        addCard(firstToken, auctionServer);

        bidDelayed(items.get(0).getId(), firstToken, 700000, auctionServer);
        bidDelayed(items.get(0).getId(), secondToken, 700000, auctionServer);

        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1500);

                    logout(firstToken, auctionServer);

                    placeBid(items.get(0).getId(), firstToken, 600000, auctionServer);

                    logout(secondToken, auctionServer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static int addItemType(AuctionInterface auctionServer) throws RemoteException {
        ItemType itemType = auctionServer.addItemType("Picture");
        auctionServer.addItemType("Picture");

        return itemType.getId();
    }

    private static void addItem(int itemTypeId, AuctionInterface auctionServer) throws RemoteException {
        auctionServer.addItem(itemTypeId, "Night", "Van Gog", 500000);
    }

    private static ArrayList<Item> searchItems(int itemTypeId, AuctionInterface auctionServer) throws RemoteException {
        return auctionServer.searchItems(itemTypeId, "Gog");
    }

    private static boolean register(String email, String username, String password, AuctionInterface auctionServer) throws RemoteException {
        return auctionServer.register(email, username, password, "Yerevan");
    }

    private static String login(String username, String password, AuctionInterface auctionServer) throws RemoteException {
        return auctionServer.logIn(username, password);
    }

    private static void placeBid(int itemId, String token, int amount, AuctionInterface auctionServer) throws RemoteException {
        auctionServer.placeBid(itemId, token, amount);
    }

    private static void bidDelayed(final int itemId, final String token, final int amount, final AuctionInterface auctionServer) {
        new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);

                    placeBid(itemId, token, amount, auctionServer);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private static void addAuction(int itemId, AuctionInterface auctionServer) throws RemoteException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 5);

        auctionServer.startAuction(new Date(), calendar.getTime(), itemId);
    }

    private static void addCard(String token, AuctionInterface auctionServer) throws RemoteException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 3);

        auctionServer.addCard(calendar.getTime(), 134, CardType.Master, token);
    }

    private static void logout(String token, AuctionInterface auctionServer) throws RemoteException {
        auctionServer.logOut(token);
    }
}
