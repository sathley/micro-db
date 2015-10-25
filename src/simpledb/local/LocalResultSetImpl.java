package simpledb.local;

import simpledb.query.Plan;
import simpledb.query.Scan;
import simpledb.record.Schema;

import java.rmi.RemoteException;

/**
 * Created by sushantathley on 10/25/15.
 */
public class LocalResultSetImpl implements LocalResultSet {

    private Scan s;
    private Schema sch;
    private LocalConnectionImpl rconn;

    /**
     * Creates a RemoteResultSet object.
     * The specified plan is opened, and the scan is saved.
     * @param plan the query plan
     * @param rconn TODO
     * @throws RemoteException
     */
    public LocalResultSetImpl(Plan plan, LocalConnectionImpl rconn) {
        s = plan.open();
        sch = plan.schema();
        this.rconn = rconn;
    }

    /**
     * Moves to the next record in the result set,
     * by moving to the next record in the saved scan.
     * @see simpledb.remote.RemoteResultSet#next()
     */
    public boolean next() {
        try {
            return s.next();
        }
        catch(RuntimeException e) {
            rconn.rollback();
            throw e;
        }
    }

    /**
     * Returns the integer value of the specified field,
     * by returning the corresponding value on the saved scan.
     * @see simpledb.remote.RemoteResultSet#getInt(java.lang.String)
     */
    public int getInt(String fldname) {
        try {
            fldname = fldname.toLowerCase(); // to ensure case-insensitivity
            return s.getInt(fldname);
        }
        catch(RuntimeException e) {
            rconn.rollback();
            throw e;
        }
    }

    /**
     * Returns the integer value of the specified field,
     * by returning the corresponding value on the saved scan.
     * @see simpledb.remote.RemoteResultSet#getInt(java.lang.String)
     */
    public String getString(String fldname) {
        try {
            fldname = fldname.toLowerCase(); // to ensure case-insensitivity
            return s.getString(fldname);
        }
        catch(RuntimeException e) {
            rconn.rollback();
            throw e;
        }
    }

    /**
     * Returns the result set's metadata,
     * by passing its schema into the RemoteMetaData constructor.
     * @see simpledb.remote.RemoteResultSet#getMetaData()
     */
    public LocalMetaData getMetaData() {
        return new LocalMetaDataImpl(sch);
    }

    /**
     * Closes the result set by closing its scan.
     * @see simpledb.remote.RemoteResultSet#close()
     */
    public void close() {
        s.close();
        rconn.commit();
    }
}
