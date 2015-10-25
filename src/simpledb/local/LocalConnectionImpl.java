package simpledb.local;

import simpledb.tx.Transaction;


/**
 * Created by sushantathley on 10/25/15.
 */
public class LocalConnectionImpl implements LocalConnection {
    private Transaction tx;

    /**
     * Creates a remote connection
     * and begins a new transaction for it.
     */
    public LocalConnectionImpl(){
        tx = new Transaction();
    }

    /**
     * Creates a new RemoteStatement for this connection.
     * @see simpledb.remote.RemoteConnection#createStatement()
     */
    public LocalStatement createStatement() {
        return new LocalStatementImpl(this);
    }

    /**
     * Closes the connection.
     * The current transaction is committed.
     * @see simpledb.remote.RemoteConnection#close()
     */
    public void close() {
        tx.commit();
    }

// The following methods are used by the server-side classes.

    /**
     * Returns the transaction currently associated with
     * this connection.
     * @return the transaction associated with this connection
     */
    Transaction getTransaction() {
        return tx;
    }

    /**
     * Commits the current transaction,
     * and begins a new one.
     */
    void commit() {
        tx.commit();
        tx = new Transaction();
    }

    /**
     * Rolls back the current transaction,
     * and begins a new one.
     */
    void rollback() {
        tx.rollback();
        tx = new Transaction();
    }
}
