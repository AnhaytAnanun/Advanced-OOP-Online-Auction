package helloworld.server;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import helloworld.interfaces.HelloWorld;

public class Server {
    public static void main (String[] args) {
        new Server();
    }

    public Server() {
        System.out.println("Starting Server");
        try {
            HelloWorldImpl helloServer = new HelloWorldImpl();
            Naming.rebind("helloworld", helloServer);
            System.out.println("helloServer started and registered");
        } catch(MalformedURLException e) {
            System.out.println("Incorrect server name");
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("Could not create helloServer");
            e.printStackTrace();
        }
    }
}
