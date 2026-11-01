package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzazi extends SSLSocketFactory {
    private SSLSocketFactory zzebg = (SSLSocketFactory) SSLSocketFactory.getDefault();
    private final /* synthetic */ zzazj zzebh;

    zzazi(zzazj zzazjVar) {
        this.zzebh = zzazjVar;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getDefaultCipherSuites() {
        return this.zzebg.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final String[] getSupportedCipherSuites() {
        return this.zzebg.getSupportedCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return zza(this.zzebg.createSocket(socket, str, i, z));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i) throws IOException {
        return zza(this.zzebg.createSocket(str, i));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return zza(this.zzebg.createSocket(str, i, inetAddress, i2));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return zza(this.zzebg.createSocket(inetAddress, i));
    }

    @Override // javax.net.SocketFactory
    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return zza(this.zzebg.createSocket(inetAddress, i, inetAddress2, i2));
    }

    private final Socket zza(Socket socket) throws SocketException {
        if (this.zzebh.zzebj > 0) {
            socket.setReceiveBufferSize(this.zzebh.zzebj);
        }
        this.zzebh.zzb(socket);
        return socket;
    }
}
