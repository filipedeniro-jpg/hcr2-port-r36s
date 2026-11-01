package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.util.JsonWriter;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.games.GamesStatusCodes;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzawi {
    private final List<String> zzdvn;
    private static Object lock = new Object();

    @GuardedBy("lock")
    private static boolean zzdvl = false;

    @GuardedBy("lock")
    private static boolean enabled = false;
    private static Clock zzbmd = DefaultClock.getInstance();
    private static final Set<String> zzdvm = new HashSet(Arrays.asList(new String[0]));

    public zzawi() {
        this(null);
    }

    public zzawi(@Nullable String str) {
        List<String> listAsList;
        if (!isEnabled()) {
            listAsList = new ArrayList<>();
        } else {
            String[] strArr = new String[1];
            String strValueOf = String.valueOf(UUID.randomUUID().toString());
            strArr[0] = strValueOf.length() != 0 ? "network_request_".concat(strValueOf) : new String("network_request_");
            listAsList = Arrays.asList(strArr);
        }
        this.zzdvn = listAsList;
    }

    public final void zza(HttpURLConnection httpURLConnection, @Nullable byte[] bArr) {
        if (isEnabled()) {
            zzb(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), httpURLConnection.getRequestProperties() == null ? null : new HashMap(httpURLConnection.getRequestProperties()), bArr);
        }
    }

    public final void zza(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        if (isEnabled()) {
            zzb(str, str2, map, bArr);
        }
    }

    private final void zzb(final String str, final String str2, @Nullable final Map<String, ?> map, @Nullable final byte[] bArr) {
        zza("onNetworkRequest", new zzawp(str, str2, map, bArr) { // from class: com.google.android.gms.internal.ads.zzawl
            private final String zzcyl;
            private final String zzcys;
            private final Map zzcyv;
            private final byte[] zzdvq;

            {
                this.zzcys = str;
                this.zzcyl = str2;
                this.zzcyv = map;
                this.zzdvq = bArr;
            }

            @Override // com.google.android.gms.internal.ads.zzawp
            public final void zzb(JsonWriter jsonWriter) throws IOException {
                zzawi.zza(this.zzcys, this.zzcyl, this.zzcyv, this.zzdvq, jsonWriter);
            }
        });
    }

    public final void zza(HttpURLConnection httpURLConnection, int i) {
        if (isEnabled()) {
            String responseMessage = null;
            zzb(httpURLConnection.getHeaderFields() == null ? null : new HashMap(httpURLConnection.getHeaderFields()), i);
            if (i < 200 || i >= 300) {
                try {
                    responseMessage = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    String strValueOf = String.valueOf(e.getMessage());
                    zzawo.zzeu(strValueOf.length() != 0 ? "Can not get error message from error HttpURLConnection\n".concat(strValueOf) : new String("Can not get error message from error HttpURLConnection\n"));
                }
                zzeq(responseMessage);
            }
        }
    }

    public final void zza(@Nullable Map<String, ?> map, int i) {
        if (isEnabled()) {
            zzb(map, i);
            if (i < 200 || i >= 300) {
                zzeq(null);
            }
        }
    }

    private final void zzb(@Nullable final Map<String, ?> map, final int i) {
        zza("onNetworkResponse", new zzawp(i, map) { // from class: com.google.android.gms.internal.ads.zzawk
            private final int zzdvo;
            private final Map zzdvp;

            {
                this.zzdvo = i;
                this.zzdvp = map;
            }

            @Override // com.google.android.gms.internal.ads.zzawp
            public final void zzb(JsonWriter jsonWriter) throws IOException {
                zzawi.zza(this.zzdvo, this.zzdvp, jsonWriter);
            }
        });
    }

    public final void zzep(@Nullable String str) {
        if (isEnabled() && str != null) {
            zzi(str.getBytes());
        }
    }

    public final void zzi(final byte[] bArr) {
        zza("onNetworkResponseBody", new zzawp(bArr) { // from class: com.google.android.gms.internal.ads.zzawn
            private final byte[] zzdvr;

            {
                this.zzdvr = bArr;
            }

            @Override // com.google.android.gms.internal.ads.zzawp
            public final void zzb(JsonWriter jsonWriter) throws IOException {
                zzawi.zza(this.zzdvr, jsonWriter);
            }
        });
    }

    private final void zzeq(@Nullable final String str) {
        zza("onNetworkRequestError", new zzawp(str) { // from class: com.google.android.gms.internal.ads.zzawm
            private final String zzcys;

            {
                this.zzcys = str;
            }

            @Override // com.google.android.gms.internal.ads.zzawp
            public final void zzb(JsonWriter jsonWriter) throws IOException {
                zzawi.zza(this.zzcys, jsonWriter);
            }
        });
    }

    private static void zza(JsonWriter jsonWriter, @Nullable Map<String, ?> map) throws IOException {
        if (map == null) {
            return;
        }
        jsonWriter.name("headers").beginArray();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (!zzdvm.contains(key)) {
                if (entry.getValue() instanceof List) {
                    for (String str : (List) entry.getValue()) {
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(key);
                        jsonWriter.name("value").value(str);
                        jsonWriter.endObject();
                    }
                } else if (entry.getValue() instanceof String) {
                    jsonWriter.beginObject();
                    jsonWriter.name("name").value(key);
                    jsonWriter.name("value").value((String) entry.getValue());
                    jsonWriter.endObject();
                } else {
                    zzawo.zzes("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                    break;
                }
            }
        }
        jsonWriter.endArray();
    }

    private final void zza(String str, zzawp zzawpVar) {
        StringWriter stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name("timestamp").value(zzbmd.currentTimeMillis());
            jsonWriter.name("event").value(str);
            jsonWriter.name("components").beginArray();
            Iterator<String> it = this.zzdvn.iterator();
            while (it.hasNext()) {
                jsonWriter.value(it.next());
            }
            jsonWriter.endArray();
            zzawpVar.zzb(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (IOException e) {
            zzawo.zzc("unable to log", e);
        }
        zzer(stringWriter.toString());
    }

    private static synchronized void zzer(String str) {
        zzawo.zzet("GMA Debug BEGIN");
        int i = 0;
        while (i < str.length()) {
            int i2 = i + GamesStatusCodes.STATUS_SNAPSHOT_NOT_FOUND;
            String strValueOf = String.valueOf(str.substring(i, Math.min(i2, str.length())));
            zzawo.zzet(strValueOf.length() != 0 ? "GMA Debug CONTENT ".concat(strValueOf) : new String("GMA Debug CONTENT "));
            i = i2;
        }
        zzawo.zzet("GMA Debug FINISH");
    }

    public static void zzwi() {
        synchronized (lock) {
            zzdvl = false;
            enabled = false;
            zzawo.zzeu("Ad debug logging enablement is out of date.");
        }
    }

    public static void zzak(boolean z) {
        synchronized (lock) {
            zzdvl = true;
            enabled = z;
        }
    }

    public static boolean zzwj() {
        boolean z;
        synchronized (lock) {
            z = zzdvl;
        }
        return z;
    }

    public static boolean isEnabled() {
        boolean z;
        synchronized (lock) {
            z = zzdvl && enabled;
        }
        return z;
    }

    public static boolean zzbn(Context context) {
        if (Build.VERSION.SDK_INT < 17) {
            return false;
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcll)).booleanValue()) {
            return false;
        }
        try {
            return Settings.Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
        } catch (Exception e) {
            zzawo.zzd("Fail to determine debug setting.", e);
            return false;
        }
    }

    static final /* synthetic */ void zza(String str, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        if (str != null) {
            jsonWriter.name(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION).value(str);
        }
        jsonWriter.endObject();
    }

    static final /* synthetic */ void zza(byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        int length = bArr.length;
        String strEncode = Base64Utils.encode(bArr);
        if (length < 10000) {
            jsonWriter.name(TtmlNode.TAG_BODY).value(strEncode);
        } else {
            String strZzen = zzawe.zzen(strEncode);
            if (strZzen != null) {
                jsonWriter.name("bodydigest").value(strZzen);
            }
        }
        jsonWriter.name("bodylength").value(length);
        jsonWriter.endObject();
    }

    static final /* synthetic */ void zza(int i, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("code").value(i);
        jsonWriter.endObject();
        zza(jsonWriter, (Map<String, ?>) map);
        jsonWriter.endObject();
    }

    static final /* synthetic */ void zza(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name(ShareConstants.MEDIA_URI).value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        zza(jsonWriter, (Map<String, ?>) map);
        if (bArr != null) {
            jsonWriter.name(TtmlNode.TAG_BODY).value(Base64Utils.encode(bArr));
        }
        jsonWriter.endObject();
    }
}
