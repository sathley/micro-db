package simpledb.local;

import simpledb.query.Plan;
import simpledb.remote.RemoteResultSet;
import simpledb.server.SimpleDB;
import simpledb.tx.Transaction;

import java.rmi.RemoteException;

/**
 * Created by sushantathley on 10/25/15.
 */
public class LocalStatementImpl implements LocalStatement {

    private LocalConnectionImpl rconn;

    public LocalStatementImpl(LocalConnectionImpl rconn) {
        this.rconn = rconn;
    }

    /**
     * Executes the specified SQL query string.
     * The method calls the query planner to create a plan
     * for the query. It then sends the plan to the
     * RemoteResultSetImpl constructor for processing.
     * @see simpledb.remote.RemoteStatement#executeQuery(java.lang.String)
     */
    public LocalResultSet executeQuery(String qry) {
        try {
            Transaction tx = rconn.getTransaction();
            Plan pln = SimpleDB.planner().createQueryPlan(qry, tx);
            return new LocalResultSetImpl(pln, rconn);
        }
        catch(RuntimeException e) {
            rconn.rollback();
            throw e;
        }
    }

    /**
     * Executes the specified SQL update command.
     * The method sends the command to the update planner,
     * which executes it.
     * @see simpledb.remote.RemoteStatement#executeUpdate(java.lang.String)
     */
    public int executeUpdate(String cmd) {
        try {
            Transaction tx = rconn.getTransaction();
            int result = SimpleDB.planner().executeUpdate(cmd, tx);
            rconn.commit();
            return result;
        }
        catch(RuntimeException e) {
            rconn.rollback();
            throw e;
        }
    }
}
