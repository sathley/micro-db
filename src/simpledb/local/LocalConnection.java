package simpledb.local;

import simpledb.remote.RemoteStatement;

import java.rmi.RemoteException;

/**
 * Created by sushantathley on 10/25/15.
 */
public interface LocalConnection {

    public LocalStatement createStatement();
    public void close();
}
