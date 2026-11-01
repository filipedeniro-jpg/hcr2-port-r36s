package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import androidx.annotation.Nullable;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Collections;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzbde {
    private final String method;
    public final Uri uri;
    public final String url;
    public final Map<String, String> zzab;

    @TargetApi(21)
    public zzbde(WebResourceRequest webResourceRequest) {
        this(webResourceRequest.getUrl().toString(), webResourceRequest.getUrl(), webResourceRequest.getMethod(), webResourceRequest.getRequestHeaders());
    }

    public zzbde(String str) {
        this(str, Uri.parse(str), null, null);
    }

    private zzbde(String str, Uri uri, @Nullable String str2, @Nullable Map<String, String> map) {
        this.url = str;
        this.uri = uri;
        this.method = str2 == null ? HttpRequest.METHOD_GET : str2;
        this.zzab = map == null ? Collections.emptyMap() : map;
    }
}
