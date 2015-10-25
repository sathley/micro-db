package simpledb.local;

import simpledb.remote.RemoteResultSet;

import java.rmi.RemoteException;

/**
 * Created by sushantathley on 10/25/15.
 */
public interface LocalStatement {

    public LocalResultSet executeQuery(String qry);
    public int            executeUpdate(String cmd);
}
