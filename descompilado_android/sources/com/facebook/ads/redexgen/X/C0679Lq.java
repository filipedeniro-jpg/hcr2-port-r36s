package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Lq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
@SuppressLint({"HardcodedIPAddressUse"})
public final class C0679Lq {

    @Nullable
    private static HA J;
    private final Object B;
    private final Map<String, C0681Ls> C;
    private final C0668Lf D;
    private boolean E;
    private final int F;
    private final ServerSocket G;
    private final ExecutorService H;
    private final Thread I;

    public C0679Lq(Context context) {
        this(new C0674Ll(context).C());
    }

    private C0679Lq(C0668Lf config) {
        this.B = new Object();
        this.H = Executors.newFixedThreadPool(8);
        this.C = new ConcurrentHashMap();
        this.D = (C0668Lf) C0684Lv.C(config);
        try {
            this.G = new ServerSocket(0, 8, InetAddress.getByName("127.0.0.1"));
            this.F = this.G.getLocalPort();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.I = new Thread(new RunnableC0678Lp(this, countDownLatch));
            this.I.start();
            countDownLatch.await();
            Log.i("ProxyCache", "Proxy cache server started. Ping it...");
            O();
        } catch (IOException | InterruptedException e) {
            this.H.shutdown();
            throw new IllegalStateException("Error starting local proxy server", e);
        }
    }

    public final String A(String url) {
        if (!this.E) {
            Log.e("ProxyCache", "Proxy server isn't pinged. Caching doesn't work.");
        }
        return (!this.E || url.startsWith("asset://")) ? url : I(url);
    }

    public final boolean B(String url, HD onPrecacheAttemptListener) {
        int i = 300;
        int i2 = 0;
        while (i2 < 3) {
            try {
                onPrecacheAttemptListener.tE(i2);
                if (!((Boolean) this.H.submit(new CallableC0676Ln(this, url)).get()).booleanValue()) {
                    SystemClock.sleep(i);
                } else {
                    return true;
                }
            } catch (InterruptedException | ExecutionException e) {
                Log.e("ProxyCache", "Error precaching url [attempt: " + i2 + ", url: " + url + "]. ", e);
            }
            i2++;
            i *= 2;
        }
        Log.e("ProxyCache", "Shutdown server... Error precaching url [attempts: " + i2 + ", url: " + url + "].");
        W();
        return false;
    }

    public static HA F() {
        return J == null ? new C0673Lk() : J;
    }

    public static void G(@Nullable HA proxyCacheLogger) {
        J = proxyCacheLogger;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void H() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Socket socketAccept = this.G.accept();
                Log.d("ProxyCache", "Accept new socket " + socketAccept);
                this.H.submit(new RunnableC0677Lo(this, socketAccept));
            } catch (IOException e) {
                P(new C0666Ld("Error during waiting connection", e));
                return;
            }
        }
    }

    private String I(String url) {
        return String.format(Locale.US, "http://%s:%d/%s", "127.0.0.1", Integer.valueOf(this.F), C0687Ly.F(url));
    }

    private void J(Socket socket) {
        try {
            if (!socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            P(new C0666Ld("Error closing socket", e));
        }
    }

    private void K(Socket socket) {
        try {
            if (!socket.isInputShutdown()) {
                socket.shutdownInput();
            }
        } catch (SocketException e) {
            Log.d("ProxyCache", "Releasing input stream... Socket is closed by client.");
        } catch (IOException e2) {
            P(new C0666Ld("Error closing socket input stream", e2));
        }
    }

    private void L(Socket socket) {
        try {
            if (socket.isOutputShutdown()) {
                socket.shutdownOutput();
            }
        } catch (IOException e) {
            P(new C0666Ld("Error closing socket output stream", e));
        }
    }

    private C0681Ls M(String url) throws C0666Ld {
        C0681Ls c0681Ls;
        synchronized (this.B) {
            c0681Ls = this.C.get(url);
            if (c0681Ls == null) {
                c0681Ls = new C0681Ls(url, this.D);
                this.C.put(url, c0681Ls);
            }
        }
        return c0681Ls;
    }

    private int N() {
        int iA;
        synchronized (this.B) {
            iA = 0;
            Iterator<C0681Ls> it = this.C.values().iterator();
            while (it.hasNext()) {
                iA += it.next().A();
            }
        }
        return iA;
    }

    private void O() {
        int maxPingAttempts = 300;
        int pingAttempts = 0;
        while (pingAttempts < 3) {
            try {
                this.E = ((Boolean) this.H.submit(new CallableC0675Lm(this, null)).get(maxPingAttempts, TimeUnit.MILLISECONDS)).booleanValue();
                if (!this.E) {
                    SystemClock.sleep(maxPingAttempts);
                } else {
                    return;
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                Log.e("ProxyCache", "Error pinging server [attempt: " + pingAttempts + ", timeout: " + maxPingAttempts + "]. ", e);
            }
            pingAttempts++;
            maxPingAttempts *= 2;
        }
        Log.e("ProxyCache", "Shutdown server... Error pinging server [attempts: " + pingAttempts + ", max timeout: " + (maxPingAttempts / 2) + "].");
        W();
    }

    private void P(Throwable e) {
        Log.e("ProxyCache", "HttpProxyCacheServer error", e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Q() throws C0666Ld {
        String pingUrl = I("ping");
        C0682Lt c0682Lt = new C0682Lt(pingUrl);
        try {
            byte[] bytes = "ping ok".getBytes();
            c0682Lt.SF(0);
            byte[] bArr = new byte[bytes.length];
            c0682Lt.read(bArr);
            boolean zEquals = Arrays.equals(bytes, bArr);
            Log.d("ProxyCache", "Ping response: `" + new String(bArr) + "`, pinged? " + zEquals);
            return zEquals;
        } catch (C0666Ld e) {
            Log.e("ProxyCache", "Error reading ping response", e);
            return false;
        } finally {
            c0682Lt.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R(Socket socket) {
        try {
            try {
                C0670Lh c0670LhB = C0670Lh.B(socket.getInputStream());
                Log.i("ProxyCache", "Request to cache proxy:" + c0670LhB);
                String strE = C0687Ly.E(c0670LhB.D);
                if ("ping".equals(strE)) {
                    V(socket);
                } else {
                    M(strE).B(c0670LhB, socket);
                }
                U(socket);
                Log.d("ProxyCache", "Opened connections: " + N());
            } catch (C0666Ld e) {
                e = e;
                P(new C0666Ld("Error processing request", e));
                U(socket);
                Log.d("ProxyCache", "Opened connections: " + N());
            } catch (SocketException e2) {
                Log.d("ProxyCache", "Closing socket... Socket is closed by client.");
                U(socket);
                Log.d("ProxyCache", "Opened connections: " + N());
            } catch (IOException e3) {
                e = e3;
                P(new C0666Ld("Error processing request", e));
                U(socket);
                Log.d("ProxyCache", "Opened connections: " + N());
            }
        } catch (Throwable th) {
            U(socket);
            Log.d("ProxyCache", "Opened connections: " + N());
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean S(String url) throws C0666Ld {
        C0682Lt c0682Lt = new C0682Lt(I(url));
        try {
            c0682Lt.SF(0);
            do {
            } while (c0682Lt.read(new byte[8192]) != -1);
            return true;
        } catch (C0666Ld e) {
            Log.e("ProxyCache", "Error reading url", e);
            return false;
        } finally {
            c0682Lt.close();
        }
    }

    private void T() {
        synchronized (this.B) {
            Iterator<C0681Ls> it = this.C.values().iterator();
            while (it.hasNext()) {
                it.next().m261C();
            }
            this.C.clear();
        }
    }

    private void U(Socket socket) {
        K(socket);
        L(socket);
        J(socket);
    }

    private void V(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("HTTP/1.1 200 OK\n\n".getBytes());
        outputStream.write("ping ok".getBytes());
    }

    private final void W() {
        Log.i("ProxyCache", "Shutdown proxy server");
        T();
        this.I.interrupt();
        try {
            if (!this.G.isClosed()) {
                this.G.close();
            }
        } catch (IOException e) {
            P(new C0666Ld("Error shutting down proxy server", e));
        }
    }
}
