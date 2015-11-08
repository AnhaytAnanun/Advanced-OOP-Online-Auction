package helloworld.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Properties;
import java.rmi.registry.LocateRegistry;

import helloworld.interfaces.HelloWorld;

public class Client{

    public static void main(String[] args) throws Exception {
        try {
            HelloWorld helloServer = (HelloWorld)Naming.lookup("rmi://localhost/helloworld");
            String result = helloServer.helloWorld("Mr. Raymond");
            System.out.println(result);
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
}

