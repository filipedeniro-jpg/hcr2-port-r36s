package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzws {
    private final int zzabj;
    private final int zzabk;
    private final String zzabl;
    private final boolean zzbke;
    private final int zzcbo;
    private final String zzcbr;
    private final String zzcbt;
    private final Bundle zzcbv;
    private final String zzcbx;
    private final boolean zzcbz;
    private final Bundle zzcdu;
    private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzcdy;
    private final SearchAdRequest zzcdz;
    private final Set<String> zzcea;
    private final Set<String> zzceb;
    private final zzcyd zzcec;
    private final Date zznc;
    private final Set<String> zzne;
    private final Location zzng;

    public zzws(zzwr zzwrVar) {
        this(zzwrVar, null);
    }

    public zzws(zzwr zzwrVar, SearchAdRequest searchAdRequest) {
        this.zznc = zzwrVar.zznc;
        this.zzcbt = zzwrVar.zzcbt;
        this.zzcbo = zzwrVar.zzcbo;
        this.zzne = Collections.unmodifiableSet(zzwrVar.zzcdt);
        this.zzng = zzwrVar.zzng;
        this.zzbke = zzwrVar.zzbke;
        this.zzcdu = zzwrVar.zzcdu;
        this.zzcdy = Collections.unmodifiableMap(zzwrVar.zzcdv);
        this.zzcbr = zzwrVar.zzcbr;
        this.zzcbx = zzwrVar.zzcbx;
        this.zzcdz = searchAdRequest;
        this.zzabj = zzwrVar.zzabj;
        this.zzcea = Collections.unmodifiableSet(zzwrVar.zzcdw);
        this.zzcbv = zzwrVar.zzcbv;
        this.zzceb = Collections.unmodifiableSet(zzwrVar.zzcdx);
        this.zzcbz = zzwrVar.zzcbz;
        this.zzcec = null;
        this.zzabk = zzwrVar.zzabk;
        this.zzabl = zzwrVar.zzabl;
    }

    @Deprecated
    public final Date getBirthday() {
        return this.zznc;
    }

    public final String getContentUrl() {
        return this.zzcbt;
    }

    @Deprecated
    public final int getGender() {
        return this.zzcbo;
    }

    public final Set<String> getKeywords() {
        return this.zzne;
    }

    public final Location getLocation() {
        return this.zzng;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbke;
    }

    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return (T) this.zzcdy.get(cls);
    }

    public final Bundle getNetworkExtrasBundle(Class<? extends MediationExtrasReceiver> cls) {
        return this.zzcdu.getBundle(cls.getName());
    }

    public final Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> cls) {
        Bundle bundle = this.zzcdu.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
        if (bundle != null) {
            return bundle.getBundle(cls.getName());
        }
        return null;
    }

    public final String getPublisherProvidedId() {
        return this.zzcbr;
    }

    public final String zzow() {
        return this.zzcbx;
    }

    public final SearchAdRequest zzox() {
        return this.zzcdz;
    }

    public final boolean isTestDevice(Context context) {
        Set<String> set = this.zzcea;
        zzuo.zzof();
        return set.contains(zzawe.zzbh(context));
    }

    public final Map<Class<? extends NetworkExtras>, NetworkExtras> zzoy() {
        return this.zzcdy;
    }

    public final Bundle zzoz() {
        return this.zzcdu;
    }

    public final int zzpa() {
        return this.zzabj;
    }

    public final Bundle getCustomTargeting() {
        return this.zzcbv;
    }

    public final Set<String> zzpb() {
        return this.zzceb;
    }

    @Deprecated
    public final boolean isDesignedForFamilies() {
        return this.zzcbz;
    }

    public final int zzpc() {
        return this.zzabk;
    }

    @Nullable
    public final String getMaxAdContentRating() {
        return this.zzabl;
    }
}
