package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.upstream.DataSourceInputStream;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
@TargetApi(18)
public final class HttpMediaDrmCallback implements MediaDrmCallback {
    private static final int MAX_MANUAL_REDIRECTS = 5;
    private final HttpDataSource.Factory dataSourceFactory;
    private final String defaultLicenseUrl;
    private final boolean forceDefaultLicenseUrl;
    private final Map<String, String> keyRequestProperties;

    public HttpMediaDrmCallback(String str, HttpDataSource.Factory factory) {
        this(str, false, factory);
    }

    public HttpMediaDrmCallback(String str, boolean z, HttpDataSource.Factory factory) {
        this.dataSourceFactory = factory;
        this.defaultLicenseUrl = str;
        this.forceDefaultLicenseUrl = z;
        this.keyRequestProperties = new HashMap();
    }

    public void setKeyRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.put(str, str2);
        }
    }

    public void clearKeyRequestProperty(String str) {
        Assertions.checkNotNull(str);
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.remove(str);
        }
    }

    public void clearAllKeyRequestProperties() {
        synchronized (this.keyRequestProperties) {
            this.keyRequestProperties.clear();
        }
    }

    @Override // com.google.android.exoplayer2.drm.MediaDrmCallback
    public byte[] executeProvisionRequest(UUID uuid, ExoMediaDrm.ProvisionRequest provisionRequest) throws IOException {
        return executePost(this.dataSourceFactory, provisionRequest.getDefaultUrl() + "&signedRequest=" + new String(provisionRequest.getData()), new byte[0], null);
    }

    @Override // com.google.android.exoplayer2.drm.MediaDrmCallback
    public byte[] executeKeyRequest(UUID uuid, ExoMediaDrm.KeyRequest keyRequest) throws Exception {
        String str;
        String defaultUrl = keyRequest.getDefaultUrl();
        if (this.forceDefaultLicenseUrl || TextUtils.isEmpty(defaultUrl)) {
            defaultUrl = this.defaultLicenseUrl;
        }
        HashMap map = new HashMap();
        if (C.PLAYREADY_UUID.equals(uuid)) {
            str = "text/xml";
        } else {
            str = C.CLEARKEY_UUID.equals(uuid) ? "application/json" : "application/octet-stream";
        }
        map.put(HttpRequest.HEADER_CONTENT_TYPE, str);
        if (C.PLAYREADY_UUID.equals(uuid)) {
            map.put("SOAPAction", "http://schemas.microsoft.com/DRM/2007/03/protocols/AcquireLicense");
        }
        synchronized (this.keyRequestProperties) {
            map.putAll(this.keyRequestProperties);
        }
        return executePost(this.dataSourceFactory, defaultUrl, keyRequest.getData(), map);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x006c A[Catch: all -> 0x004f, TRY_LEAVE, TryCatch #1 {all -> 0x004f, blocks: (B:10:0x0047, B:17:0x0053, B:19:0x0059, B:28:0x006c, B:33:0x007d, B:23:0x0062), top: B:37:0x0047, inners: #0 }] */
    /* JADX WARN: Code duplicated, block: B:30:0x0071  */
    /* JADX WARN: Code duplicated, block: B:32:0x0074 A[LOOP:1: B:9:0x002e->B:32:0x0074, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:42:0x007d A[SYNTHETIC] */
    private static byte[] executePost(HttpDataSource.Factory factory, String str, byte[] bArr, Map<String, String> map) throws IOException {
        int i;
        boolean z;
        String redirectUrl;
        HttpDataSource httpDataSourceCreateDataSource = factory.createDataSource();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                httpDataSourceCreateDataSource.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        String str2 = str;
        int i2 = 0;
        while (true) {
            DataSourceInputStream dataSourceInputStream = new DataSourceInputStream(httpDataSourceCreateDataSource, new DataSpec(Uri.parse(str2), bArr, 0L, 0L, -1L, null, 1));
            try {
                try {
                    byte[] byteArray = Util.toByteArray(dataSourceInputStream);
                    Util.closeQuietly(dataSourceInputStream);
                    return byteArray;
                } catch (HttpDataSource.InvalidResponseCodeException e) {
                    if (e.responseCode == 307 || e.responseCode == 308) {
                        i = i2 + 1;
                        z = i2 < 5;
                        if (z) {
                            redirectUrl = getRedirectUrl(e);
                        } else {
                            redirectUrl = null;
                        }
                        if (redirectUrl != null) {
                            throw e;
                        }
                        Util.closeQuietly(dataSourceInputStream);
                        String str3 = redirectUrl;
                        i2 = i;
                        str2 = str3;
                    } else {
                        i = i2;
                    }
                    if (z) {
                        redirectUrl = getRedirectUrl(e);
                    } else {
                        redirectUrl = null;
                    }
                    if (redirectUrl != null) {
                        throw e;
                    }
                    Util.closeQuietly(dataSourceInputStream);
                    String str4 = redirectUrl;
                    i2 = i;
                    str2 = str4;
                }
            } catch (Throwable th) {
                Util.closeQuietly(dataSourceInputStream);
                throw th;
            }
        }
    }

    private static String getRedirectUrl(HttpDataSource.InvalidResponseCodeException invalidResponseCodeException) {
        List<String> list;
        Map<String, List<String>> map = invalidResponseCodeException.headerFields;
        if (map == null || (list = map.get("Location")) == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
