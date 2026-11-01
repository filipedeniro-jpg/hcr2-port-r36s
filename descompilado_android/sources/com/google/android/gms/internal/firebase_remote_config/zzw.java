package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class zzw extends zzby {

    @zzcc("Accept")
    private List<String> accept;

    @zzcc(HttpRequest.HEADER_ACCEPT_ENCODING)
    private List<String> acceptEncoding;

    @zzcc("Age")
    private List<Long> age;

    @zzcc("WWW-Authenticate")
    private List<String> authenticate;

    @zzcc(HttpRequest.HEADER_AUTHORIZATION)
    private List<String> authorization;

    @zzcc(HttpRequest.HEADER_CACHE_CONTROL)
    private List<String> cacheControl;

    @zzcc(HttpRequest.HEADER_CONTENT_ENCODING)
    private List<String> contentEncoding;

    @zzcc(HttpRequest.HEADER_CONTENT_LENGTH)
    private List<Long> contentLength;

    @zzcc("Content-MD5")
    private List<String> contentMD5;

    @zzcc("Content-Range")
    private List<String> contentRange;

    @zzcc(HttpRequest.HEADER_CONTENT_TYPE)
    private List<String> contentType;

    @zzcc("Cookie")
    private List<String> cookie;

    @zzcc(HttpRequest.HEADER_DATE)
    private List<String> date;

    @zzcc(HttpRequest.HEADER_ETAG)
    private List<String> etag;

    @zzcc(HttpRequest.HEADER_EXPIRES)
    private List<String> expires;

    @zzcc("If-Match")
    private List<String> ifMatch;

    @zzcc("If-Modified-Since")
    private List<String> ifModifiedSince;

    @zzcc(HttpRequest.HEADER_IF_NONE_MATCH)
    private List<String> ifNoneMatch;

    @zzcc("If-Range")
    private List<String> ifRange;

    @zzcc("If-Unmodified-Since")
    private List<String> ifUnmodifiedSince;

    @zzcc(HttpRequest.HEADER_LAST_MODIFIED)
    private List<String> lastModified;

    @zzcc("Location")
    private List<String> location;

    @zzcc("MIME-Version")
    private List<String> mimeVersion;

    @zzcc("Range")
    private List<String> range;

    @zzcc("Retry-After")
    private List<String> retryAfter;

    @zzcc("User-Agent")
    private List<String> userAgent;

    public zzw() {
        super(EnumSet.of(zzby.zzc.IGNORE_CASE));
        this.acceptEncoding = new ArrayList(Collections.singleton(HttpRequest.ENCODING_GZIP));
    }

    public final zzw zzo(String str) {
        this.authorization = zza((Object) null);
        return this;
    }

    public final String getContentType() {
        return (String) zza((List) this.contentType);
    }

    public final String zzq() {
        return (String) zza((List) this.etag);
    }

    public final zzw zzp(String str) {
        this.ifModifiedSince = zza((Object) null);
        return this;
    }

    public final zzw zzq(String str) {
        this.ifMatch = zza((Object) null);
        return this;
    }

    public final zzw zzr(String str) {
        this.ifNoneMatch = zza(str);
        return this;
    }

    public final zzw zzs(String str) {
        this.ifUnmodifiedSince = zza((Object) null);
        return this;
    }

    public final zzw zzt(String str) {
        this.ifRange = zza((Object) null);
        return this;
    }

    public final String getLocation() {
        return (String) zza((List) this.location);
    }

    public final String getUserAgent() {
        return (String) zza((List) this.userAgent);
    }

    public final zzw zzu(String str) {
        this.userAgent = zza(str);
        return this;
    }

    private static void zza(Logger logger, StringBuilder sb, StringBuilder sb2, zzaj zzajVar, String str, Object obj, Writer writer) throws IOException {
        if (obj == null || zzbt.isNull(obj)) {
            return;
        }
        String name = obj instanceof Enum ? zzbz.zza((Enum<?>) obj).getName() : obj.toString();
        String str2 = ((HttpRequest.HEADER_AUTHORIZATION.equalsIgnoreCase(str) || "Cookie".equalsIgnoreCase(str)) && (logger == null || !logger.isLoggable(Level.ALL))) ? "<Not Logged>" : name;
        if (sb != null) {
            sb.append(str);
            sb.append(": ");
            sb.append(str2);
            sb.append(zzcl.zzgg);
        }
        if (sb2 != null) {
            sb2.append(" -H '");
            sb2.append(str);
            sb2.append(": ");
            sb2.append(str2);
            sb2.append("'");
        }
        if (zzajVar != null) {
            zzajVar.addHeader(str, name);
        }
        if (writer != null) {
            writer.write(str);
            writer.write(": ");
            writer.write(name);
            writer.write("\r\n");
        }
    }

    static void zza(zzw zzwVar, StringBuilder sb, StringBuilder sb2, Logger logger, zzaj zzajVar) throws IOException {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, Object> entry : zzwVar.entrySet()) {
            String key = entry.getKey();
            Object[] objArr = {key};
            if (!hashSet.add(key)) {
                throw new IllegalArgumentException(zzdy.zza("multiple headers of the same name (headers are case insensitive): %s", objArr));
            }
            Object value = entry.getValue();
            if (value != null) {
                zzbz zzbzVarZzae = zzwVar.zzby().zzae(key);
                if (zzbzVarZzae != null) {
                    key = zzbzVarZzae.getName();
                }
                Class<?> cls = value.getClass();
                if ((value instanceof Iterable) || cls.isArray()) {
                    Iterator it = zzco.zzi(value).iterator();
                    while (it.hasNext()) {
                        zza(logger, sb, sb2, zzajVar, key, it.next(), null);
                    }
                } else {
                    zza(logger, sb, sb2, zzajVar, key, value, null);
                }
            }
        }
    }

    public final void zza(zzai zzaiVar, StringBuilder sb) throws IOException {
        clear();
        zzz zzzVar = new zzz(this, sb);
        int iZzah = zzaiVar.zzah();
        for (int i = 0; i < iZzah; i++) {
            String strZzc = zzaiVar.zzc(i);
            String strZzd = zzaiVar.zzd(i);
            List<Type> list = zzzVar.zzaz;
            zzbr zzbrVar = zzzVar.zzay;
            zzbn zzbnVar = zzzVar.zzaw;
            StringBuilder sb2 = zzzVar.zzax;
            if (sb2 != null) {
                StringBuilder sb3 = new StringBuilder(String.valueOf(strZzc).length() + 2 + String.valueOf(strZzd).length());
                sb3.append(strZzc);
                sb3.append(": ");
                sb3.append(strZzd);
                sb2.append(sb3.toString());
                sb2.append(zzcl.zzgg);
            }
            zzbz zzbzVarZzae = zzbrVar.zzae(strZzc);
            if (zzbzVarZzae != null) {
                Type typeZza = zzbt.zza(list, zzbzVarZzae.getGenericType());
                if (zzco.zzc(typeZza)) {
                    Class<?> clsZzb = zzco.zzb(list, zzco.zzd(typeZza));
                    zzbnVar.zza(zzbzVarZzae.zzbz(), clsZzb, zza(clsZzb, list, strZzd));
                } else if (zzco.zza(zzco.zzb(list, typeZza), (Class<?>) Iterable.class)) {
                    Collection<Object> collectionZzb = (Collection) zzbzVarZzae.zzh(this);
                    if (collectionZzb == null) {
                        collectionZzb = zzbt.zzb(typeZza);
                        zzbzVarZzae.zzb(this, collectionZzb);
                    }
                    collectionZzb.add(zza(typeZza == Object.class ? null : zzco.zze(typeZza), list, strZzd));
                } else {
                    zzbzVarZzae.zzb(this, zza(typeZza, list, strZzd));
                }
            } else {
                ArrayList arrayList = (ArrayList) get(strZzc);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    zzb(strZzc, arrayList);
                }
                arrayList.add(strZzd);
            }
        }
        zzzVar.zzaw.zzbu();
    }

    private static <T> T zza(List<T> list) {
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    private static <T> List<T> zza(T t) {
        if (t == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(t);
        return arrayList;
    }

    private static Object zza(Type type, List<Type> list, String str) {
        return zzbt.zza(zzbt.zza(list, type), str);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzb */
    public final /* synthetic */ zzby clone() {
        return (zzw) clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    public final /* synthetic */ zzby zzb(String str, Object obj) {
        return (zzw) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return (zzw) super.clone();
    }
}
