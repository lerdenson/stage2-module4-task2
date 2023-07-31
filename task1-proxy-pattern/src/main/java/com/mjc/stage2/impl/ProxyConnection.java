package com.mjc.stage2.impl;


import com.mjc.stage2.Connection;

public class ProxyConnection implements Connection {
    private RealConnection realConnection;
    private boolean isClosed;
    private ConnectionPool pool = ConnectionPool.getInstance();

    public ProxyConnection(RealConnection realConnection) {
        this.realConnection = realConnection;
        this.isClosed = false;
    }

    public void reallyClose() {
        realConnection.close();
    }

    @Override
    public void close() {
        pool.releaseConnection(this);
        isClosed = true;
    }

    @Override
    public boolean isClosed() {
        return isClosed;
    }
}
