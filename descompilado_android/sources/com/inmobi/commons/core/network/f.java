package com.inmobi.commons.core.network;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.HashMap;

/* JADX INFO: compiled from: VastBitRateNetworkConnection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends b {
    @Override // com.inmobi.commons.core.network.b
    public final /* bridge */ /* synthetic */ d a() {
        return super.a();
    }

    public f(c cVar) {
        super(cVar);
    }

    @Override // com.inmobi.commons.core.network.b
    protected final d b() {
        d dVar = new d();
        try {
            int responseCode = this.c.getResponseCode();
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.q);
            sb.append("Response code: ");
            sb.append(responseCode);
            try {
                dVar.c = this.c.getContentLength();
            } finally {
                this.c.disconnect();
            }
        } catch (SocketTimeoutException unused) {
            dVar.b = new NetworkError(NetworkError.ErrorCode.HTTP_GATEWAY_TIMEOUT, NetworkError.ErrorCode.HTTP_GATEWAY_TIMEOUT.toString());
        } catch (IOException unused2) {
            dVar.b = new NetworkError(NetworkError.ErrorCode.NETWORK_IO_ERROR, NetworkError.ErrorCode.NETWORK_IO_ERROR.toString());
        } catch (Exception e) {
            dVar.b = new NetworkError(NetworkError.ErrorCode.UNKNOWN_ERROR, NetworkError.ErrorCode.UNKNOWN_ERROR.toString());
            try {
                HashMap map = new HashMap();
                map.put("type", "GenericException");
                map.put("message", e.getMessage());
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", map);
            } catch (Exception unused3) {
                StringBuilder sb2 = new StringBuilder("Error in submitting telemetry event : (");
                sb2.append(e.getMessage());
                sb2.append(")");
            }
        } catch (OutOfMemoryError unused4) {
            dVar.b = new NetworkError(NetworkError.ErrorCode.OUT_OF_MEMORY_ERROR, NetworkError.ErrorCode.OUT_OF_MEMORY_ERROR.toString());
        }
        return dVar;
    }
}
