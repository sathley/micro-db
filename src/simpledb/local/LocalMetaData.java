package simpledb.local;

import java.rmi.RemoteException;

/**
 * Created by sushantathley on 10/25/15.
 */
public interface LocalMetaData {

    public int    getColumnCount();
    public String getColumnName(int column);
    public int    getColumnType(int column);
    public int getColumnDisplaySize(int column);
}
