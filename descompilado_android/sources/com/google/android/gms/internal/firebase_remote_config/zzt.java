package com.google.android.gms.internal.firebase_remote_config;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class zzt extends zzby {
    private static final zzcr zzai = new zzcu("=&-_.!~*'()@:$,;/?:", false);
    private String fragment;
    private int port;
    private String zzaj;
    private String zzak;
    private String zzal;
    private List<String> zzam;

    public zzt() {
        this.port = -1;
    }

    public zzt(String str) {
        this(zzn(str));
    }

    public zzt(URL url) {
        this(url.getProtocol(), url.getHost(), url.getPort(), url.getPath(), url.getRef(), url.getQuery(), url.getUserInfo());
    }

    private zzt(String str, String str2, int i, String str3, String str4, String str5, String str6) {
        this.port = -1;
        this.zzaj = str.toLowerCase(Locale.US);
        this.zzak = str2;
        this.port = i;
        this.zzam = zzm(str3);
        this.fragment = str4 != null ? zzcs.zzai(str4) : null;
        if (str5 != null) {
            zzam.zze(str5, this);
        }
        this.zzal = str6 != null ? zzcs.zzai(str6) : null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return zzp().hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (super.equals(obj) && (obj instanceof zzt)) {
            return zzp().equals(((zzt) obj).zzp());
        }
        return false;
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return zzp();
    }

    public final String zzp() {
        StringBuilder sb = new StringBuilder();
        sb.append((String) zzdt.checkNotNull(this.zzaj));
        sb.append("://");
        String str = this.zzal;
        if (str != null) {
            sb.append(zzcs.zzal(str));
            sb.append('@');
        }
        sb.append((String) zzdt.checkNotNull(this.zzak));
        int i = this.port;
        if (i != -1) {
            sb.append(':');
            sb.append(i);
        }
        String strValueOf = String.valueOf(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        List<String> list = this.zzam;
        if (list != null) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                String str2 = this.zzam.get(i2);
                if (i2 != 0) {
                    sb2.append('/');
                }
                if (str2.length() != 0) {
                    sb2.append(zzcs.zzaj(str2));
                }
            }
        }
        zza(entrySet(), sb2);
        String str3 = this.fragment;
        if (str3 != null) {
            sb2.append('#');
            sb2.append(zzai.zzag(str3));
        }
        String strValueOf2 = String.valueOf(sb2.toString());
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public final URL zzk(String str) {
        try {
            return new URL(zzn(zzp()), str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public final void zzl(String str) {
        this.zzam = zzm(null);
    }

    private static List<String> zzm(String str) {
        String strSubstring;
        if (str == null || str.length() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        boolean z = true;
        int i = 0;
        while (z) {
            int iIndexOf = str.indexOf(47, i);
            boolean z2 = iIndexOf != -1;
            if (z2) {
                strSubstring = str.substring(i, iIndexOf);
            } else {
                strSubstring = str.substring(i);
            }
            arrayList.add(zzcs.zzai(strSubstring));
            i = iIndexOf + 1;
            z = z2;
        }
        return arrayList;
    }

    static void zza(Set<Map.Entry<String, Object>> set, StringBuilder sb) {
        boolean zZza = true;
        for (Map.Entry<String, Object> entry : set) {
            Object value = entry.getValue();
            if (value != null) {
                String strZzam = zzcs.zzam(entry.getKey());
                if (value instanceof Collection) {
                    Iterator it = ((Collection) value).iterator();
                    while (it.hasNext()) {
                        zZza = zza(zZza, sb, strZzam, it.next());
                    }
                } else {
                    zZza = zza(zZza, sb, strZzam, value);
                }
            }
        }
    }

    private static boolean zza(boolean z, StringBuilder sb, String str, Object obj) {
        if (z) {
            z = false;
            sb.append('?');
        } else {
            sb.append('&');
        }
        sb.append(str);
        String strZzam = zzcs.zzam(obj.toString());
        if (strZzam.length() != 0) {
            sb.append('=');
            sb.append(strZzam);
        }
        return z;
    }

    private static URL zzn(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzb */
    public final /* synthetic */ zzby clone() {
        return (zzt) clone();
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    public final /* synthetic */ zzby zzb(String str, Object obj) {
        return (zzt) super.zzb(str, obj);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby, java.util.AbstractMap
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzt zztVar = (zzt) super.clone();
        List<String> list = this.zzam;
        if (list != null) {
            zztVar.zzam = new ArrayList(list);
        }
        return zztVar;
    }
}
