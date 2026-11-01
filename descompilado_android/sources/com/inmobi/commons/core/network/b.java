package com.inmobi.commons.core.network;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NetworkConnection.java */
/* JADX INFO: loaded from: classes2.dex */
class b {
    protected static final String a = "com.inmobi.commons.core.network.b";
    protected c b;
    protected HttpURLConnection c;

    public b(c cVar) {
        this.b = cVar;
    }

    public d a() throws Throwable {
        d dVar;
        this.b.a();
        if (this.b.x != 1) {
            d dVar2 = new d();
            dVar2.b = new NetworkError(NetworkError.ErrorCode.GDPR_COMPLIANCE_ENFORCED, "Network Request dropped as current request is not GDPR compliant.");
            return dVar2;
        }
        if (!com.inmobi.commons.core.utilities.d.a()) {
            d dVar3 = new d();
            dVar3.b = new NetworkError(NetworkError.ErrorCode.NETWORK_UNAVAILABLE_ERROR, "Network not reachable currently. Please try again.");
            return dVar3;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.b.e()).openConnection();
            a(httpURLConnection);
            this.c = httpURLConnection;
            if (!this.b.t) {
                this.c.setInstanceFollowRedirects(false);
            }
            if (HttpRequest.METHOD_POST.equals(this.b.p)) {
                String strF = this.b.f();
                this.c.setRequestProperty(HttpRequest.HEADER_CONTENT_LENGTH, Integer.toString(strF.length()));
                this.c.setRequestProperty(HttpRequest.HEADER_CONTENT_TYPE, HttpRequest.CONTENT_TYPE_FORM);
                BufferedWriter bufferedWriter = null;
                try {
                    BufferedWriter bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(this.c.getOutputStream()));
                    try {
                        bufferedWriter2.write(strF);
                        com.inmobi.commons.core.utilities.d.a(bufferedWriter2);
                    } catch (Throwable th) {
                        th = th;
                        bufferedWriter = bufferedWriter2;
                        com.inmobi.commons.core.utilities.d.a(bufferedWriter);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            return b();
        } catch (IOException e) {
            dVar = new d();
            dVar.b = new NetworkError(NetworkError.ErrorCode.NETWORK_IO_ERROR, e.getLocalizedMessage());
            return dVar;
        } catch (Exception e2) {
            dVar = new d();
            dVar.b = new NetworkError(NetworkError.ErrorCode.UNKNOWN_ERROR, e2.getLocalizedMessage());
            try {
                HashMap map = new HashMap();
                map.put("type", "GenericException");
                map.put("message", e2.getMessage());
                com.inmobi.commons.core.e.b.a();
                com.inmobi.commons.core.e.b.a("root", "ExceptionCaught", map);
            } catch (Exception unused) {
                StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
                sb.append(e2.getMessage());
                sb.append(")");
            }
            return dVar;
        }
    }

    private void a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(this.b.r);
        httpURLConnection.setReadTimeout(this.b.s);
        httpURLConnection.setUseCaches(false);
        Map<String, String> mapD = this.b.d();
        if (mapD != null) {
            for (String str : mapD.keySet()) {
                httpURLConnection.setRequestProperty(str, mapD.get(str));
            }
        }
        String str2 = this.b.p;
        httpURLConnection.setRequestMethod(str2);
        if (HttpRequest.METHOD_GET.equals(str2)) {
            return;
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
    }

    protected d b() {
        d dVar = new d();
        try {
            int responseCode = this.c.getResponseCode();
            StringBuilder sb = new StringBuilder();
            sb.append(this.b.q);
            sb.append("Response code: ");
            sb.append(responseCode);
            try {
                if (responseCode == 200) {
                    a(dVar, false);
                } else {
                    NetworkError.ErrorCode errorCodeFromValue = NetworkError.ErrorCode.fromValue(responseCode);
                    if (errorCodeFromValue == NetworkError.ErrorCode.BAD_REQUEST) {
                        a(dVar, true);
                        dVar.b = new NetworkError(errorCodeFromValue, a(dVar.b()));
                    } else {
                        if (errorCodeFromValue == null) {
                            errorCodeFromValue = NetworkError.ErrorCode.UNKNOWN_ERROR;
                        }
                        dVar.b = new NetworkError(errorCodeFromValue, "HTTP:" + responseCode);
                        dVar.d = this.c.getHeaderFields();
                    }
                }
                this.c.disconnect();
            } catch (Throwable th) {
                this.c.disconnect();
                throw th;
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

    private void a(d dVar, boolean z) throws IOException {
        if (!(this.b.v != -1) || this.c.getContentLength() <= this.b.v) {
            byte[] bArrA = com.inmobi.commons.core.utilities.d.a(z ? this.c.getErrorStream() : this.c.getInputStream());
            if (bArrA.length != 0) {
                if (this.b.b() && (bArrA = this.b.a(bArrA)) == null) {
                    dVar.b = new NetworkError(NetworkError.ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response.");
                }
                if (bArrA != null && this.b.w && (bArrA = com.inmobi.commons.core.utilities.d.a(bArrA)) == null) {
                    dVar.b = new NetworkError(NetworkError.ErrorCode.GZIP_DECOMPRESSION_FAILED, "Failed to uncompress gzip response");
                }
                if (bArrA != null) {
                    dVar.b(bArrA);
                }
            }
            dVar.d = this.c.getHeaderFields();
            return;
        }
        dVar.b = new NetworkError(NetworkError.ErrorCode.RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT, "Response size greater than specified max response size");
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has(NendAdNativeMediaView.RESULT_ERROR_MESSAGE)) {
                return jSONObject.getString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE);
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }
}
