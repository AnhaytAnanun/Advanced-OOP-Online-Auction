package auctionRMI.server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server {
    public static void main (String[] args) {
        new Server();
    }

    public Server() {
        System.out.println("Starting Auction Server");
        try {
            AuctionImplementation auctionServer = new AuctionImplementation();

            Naming.rebind("auctionRMI", auctionServer);
//            Naming.rebind("addItemType", auctionServer);
//            Naming.rebind("addItem", auctionServer);
//            Naming.rebind("searchItems", auctionServer);
//            Naming.rebind("register", auctionServer);
//            Naming.rebind("logIn", auctionServer);
//            Naming.rebind("logOut", auctionServer);
//            Naming.rebind("addCard", auctionServer);
//            Naming.rebind("placeBid", auctionServer);
//            Naming.rebind("startAuction", auctionServer);

            System.out.println("Auction Server started and registered");
        } catch(MalformedURLException e) {
            System.out.println("Incorrect server name");
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("Could not create Auction Server");
            e.printStackTrace();
        }
    }
}
