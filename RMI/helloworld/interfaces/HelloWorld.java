package helloworld.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloWorld extends Remote {
    public String helloWorld(String name) throws RemoteException;
}
