package helloworld.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import helloworld.interfaces.HelloWorld;

public class HelloWorldImpl extends UnicastRemoteObject implements HelloWorld {
    public HelloWorldImpl() throws RemoteException {
        System.out.println("Making server");
    }

    public String helloWorld(String name) {
        System.out.println("Calling HelloWorld: ");
        return "Hello " + name + "!";
    }
}
