package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Internal;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpCodec;
import okhttp3.internal.http2.ConnectionShutdownException;
import okhttp3.internal.http2.ErrorCode;
import okhttp3.internal.http2.StreamResetException;

/* JADX INFO: loaded from: classes3.dex */
public final class StreamAllocation {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public final Address address;
    private final Object callStackTrace;
    private boolean canceled;
    private HttpCodec codec;
    private RealConnection connection;
    private final ConnectionPool connectionPool;
    private int refusedStreamCount;
    private boolean released;
    private Route route;
    private final RouteSelector routeSelector;

    public StreamAllocation(ConnectionPool connectionPool, Address address, Object obj) {
        this.connectionPool = connectionPool;
        this.address = address;
        this.routeSelector = new RouteSelector(address, routeDatabase());
        this.callStackTrace = obj;
    }

    public HttpCodec newStream(OkHttpClient okHttpClient, boolean z) {
        try {
            HttpCodec httpCodecNewCodec = findHealthyConnection(okHttpClient.connectTimeoutMillis(), okHttpClient.readTimeoutMillis(), okHttpClient.writeTimeoutMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec(okHttpClient, this);
            synchronized (this.connectionPool) {
                this.codec = httpCodecNewCodec;
            }
            return httpCodecNewCodec;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    private RealConnection findHealthyConnection(int i, int i2, int i3, boolean z, boolean z2) throws Throwable {
        while (true) {
            RealConnection realConnectionFindConnection = findConnection(i, i2, i3, z);
            synchronized (this.connectionPool) {
                if (realConnectionFindConnection.successCount == 0) {
                    return realConnectionFindConnection;
                }
                if (realConnectionFindConnection.isHealthy(z2)) {
                    return realConnectionFindConnection;
                }
                noNewStreams();
            }
        }
    }

    private RealConnection findConnection(int i, int i2, int i3, boolean z) throws Throwable {
        synchronized (this.connectionPool) {
            if (this.released) {
                throw new IllegalStateException("released");
            }
            if (this.codec != null) {
                throw new IllegalStateException("codec != null");
            }
            if (this.canceled) {
                throw new IOException("Canceled");
            }
            RealConnection realConnection = this.connection;
            if (realConnection != null && !realConnection.noNewStreams) {
                return realConnection;
            }
            Socket socketDeduplicate = null;
            Internal.instance.get(this.connectionPool, this.address, this, null);
            if (this.connection != null) {
                return this.connection;
            }
            Route next = this.route;
            if (next == null) {
                next = this.routeSelector.next();
            }
            synchronized (this.connectionPool) {
                if (this.canceled) {
                    throw new IOException("Canceled");
                }
                Internal.instance.get(this.connectionPool, this.address, this, next);
                if (this.connection != null) {
                    return this.connection;
                }
                this.route = next;
                this.refusedStreamCount = 0;
                RealConnection realConnection2 = new RealConnection(this.connectionPool, next);
                acquire(realConnection2);
                realConnection2.connect(i, i2, i3, z);
                routeDatabase().connected(realConnection2.route());
                synchronized (this.connectionPool) {
                    Internal.instance.put(this.connectionPool, realConnection2);
                    if (realConnection2.isMultiplexed()) {
                        socketDeduplicate = Internal.instance.deduplicate(this.connectionPool, this.address, this);
                        realConnection2 = this.connection;
                    }
                }
                Util.closeQuietly(socketDeduplicate);
                return realConnection2;
            }
        }
    }

    public void streamFinished(boolean z, HttpCodec httpCodec) {
        Socket socketDeallocate;
        synchronized (this.connectionPool) {
            if (httpCodec != null) {
                if (httpCodec == this.codec) {
                    if (!z) {
                        this.connection.successCount++;
                    }
                    socketDeallocate = deallocate(z, false, true);
                }
            }
            throw new IllegalStateException("expected " + this.codec + " but was " + httpCodec);
        }
        Util.closeQuietly(socketDeallocate);
    }

    public HttpCodec codec() {
        HttpCodec httpCodec;
        synchronized (this.connectionPool) {
            httpCodec = this.codec;
        }
        return httpCodec;
    }

    private RouteDatabase routeDatabase() {
        return Internal.instance.routeDatabase(this.connectionPool);
    }

    public synchronized RealConnection connection() {
        return this.connection;
    }

    public void release() {
        Socket socketDeallocate;
        synchronized (this.connectionPool) {
            socketDeallocate = deallocate(false, true, false);
        }
        Util.closeQuietly(socketDeallocate);
    }

    public void noNewStreams() {
        Socket socketDeallocate;
        synchronized (this.connectionPool) {
            socketDeallocate = deallocate(true, false, false);
        }
        Util.closeQuietly(socketDeallocate);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    private Socket deallocate(boolean z, boolean z2, boolean z3) {
        Socket socket;
        if (z3) {
            this.codec = null;
        }
        if (z2) {
            this.released = true;
        }
        RealConnection realConnection = this.connection;
        if (realConnection != null) {
            if (z) {
                realConnection.noNewStreams = true;
            }
            if (this.codec == null && (this.released || this.connection.noNewStreams)) {
                release(this.connection);
                if (this.connection.allocations.isEmpty()) {
                    this.connection.idleAtNanos = System.nanoTime();
                    if (Internal.instance.connectionBecameIdle(this.connectionPool, this.connection)) {
                        socket = this.connection.socket();
                    } else {
                        socket = null;
                    }
                } else {
                    socket = null;
                }
                this.connection = null;
                return socket;
            }
        }
        return null;
    }

    public void cancel() {
        HttpCodec httpCodec;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            httpCodec = this.codec;
            realConnection = this.connection;
        }
        if (httpCodec != null) {
            httpCodec.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    /* JADX WARN: Code duplicated, block: B:28:0x004b  */
    public void streamFailed(IOException iOException) {
        boolean z;
        Socket socketDeallocate;
        synchronized (this.connectionPool) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.refusedStreamCount++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.refusedStreamCount > 1) {
                    this.route = null;
                    z = true;
                } else {
                    z = false;
                }
            } else if (this.connection == null || (this.connection.isMultiplexed() && !(iOException instanceof ConnectionShutdownException))) {
                z = false;
            } else {
                if (this.connection.successCount == 0) {
                    if (this.route != null && iOException != null) {
                        this.routeSelector.connectFailed(this.route, iOException);
                    }
                    this.route = null;
                }
                z = true;
            }
            socketDeallocate = deallocate(z, false, true);
        }
        Util.closeQuietly(socketDeallocate);
    }

    public void acquire(RealConnection realConnection) {
        if (this.connection != null) {
            throw new IllegalStateException();
        }
        this.connection = realConnection;
        realConnection.allocations.add(new StreamAllocationReference(this, this.callStackTrace));
    }

    private void release(RealConnection realConnection) {
        int size = realConnection.allocations.size();
        for (int i = 0; i < size; i++) {
            if (realConnection.allocations.get(i).get() == this) {
                realConnection.allocations.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    public Socket releaseAndAcquire(RealConnection realConnection) {
        if (this.codec != null || this.connection.allocations.size() != 1) {
            throw new IllegalStateException();
        }
        Reference<StreamAllocation> reference = this.connection.allocations.get(0);
        Socket socketDeallocate = deallocate(true, false, false);
        this.connection = realConnection;
        realConnection.allocations.add(reference);
        return socketDeallocate;
    }

    public boolean hasMoreRoutes() {
        return this.route != null || this.routeSelector.hasNext();
    }

    public String toString() {
        RealConnection realConnectionConnection = connection();
        return realConnectionConnection != null ? realConnectionConnection.toString() : this.address.toString();
    }

    public static final class StreamAllocationReference extends WeakReference<StreamAllocation> {
        public final Object callStackTrace;

        StreamAllocationReference(StreamAllocation streamAllocation, Object obj) {
            super(streamAllocation);
            this.callStackTrace = obj;
        }
    }
}
