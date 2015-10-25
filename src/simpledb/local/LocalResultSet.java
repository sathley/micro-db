package simpledb.local;

import simpledb.remote.RemoteMetaData;

import java.rmi.RemoteException;

/**
 * Created by sushantathley on 10/25/15.
 */
public interface LocalResultSet {

    public boolean next();
    public int getInt(String fldname);
    public String getString(String fldname);
    public LocalMetaData getMetaData();
    public void close();
}
