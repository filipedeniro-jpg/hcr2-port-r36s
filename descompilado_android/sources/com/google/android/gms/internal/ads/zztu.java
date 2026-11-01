package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztu {
    public static final zztu zzccd = new zztu();

    @VisibleForTesting
    protected zztu() {
    }

    public static zztp zza(Context context, zzws zzwsVar) {
        String strZza;
        Date birthday = zzwsVar.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1L;
        String contentUrl = zzwsVar.getContentUrl();
        int gender = zzwsVar.getGender();
        Set<String> keywords = zzwsVar.getKeywords();
        List listUnmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean zIsTestDevice = zzwsVar.isTestDevice(context);
        Location location = zzwsVar.getLocation();
        Bundle networkExtrasBundle = zzwsVar.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = zzwsVar.getManualImpressionsEnabled();
        String publisherProvidedId = zzwsVar.getPublisherProvidedId();
        SearchAdRequest searchAdRequestZzox = zzwsVar.zzox();
        zzxy zzxyVar = searchAdRequestZzox != null ? new zzxy(searchAdRequestZzox) : null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            String packageName = applicationContext.getPackageName();
            zzuo.zzof();
            strZza = zzawe.zza(Thread.currentThread().getStackTrace(), packageName);
        } else {
            strZza = null;
        }
        boolean zIsDesignedForFamilies = zzwsVar.isDesignedForFamilies();
        RequestConfiguration requestConfiguration = zzwv.zzpd().getRequestConfiguration();
        return new zztp(8, time, networkExtrasBundle, gender, listUnmodifiableList, zIsTestDevice, Math.max(zzwsVar.zzpa(), requestConfiguration.getTagForChildDirectedTreatment()), manualImpressionsEnabled, publisherProvidedId, zzxyVar, location, contentUrl, zzwsVar.zzoz(), zzwsVar.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(zzwsVar.zzpb())), zzwsVar.zzow(), strZza, zIsDesignedForFamilies, null, Math.max(zzwsVar.zzpc(), requestConfiguration.getTagForUnderAgeOfConsent()), (String) Collections.max(Arrays.asList(zzwsVar.getMaxAdContentRating(), requestConfiguration.getMaxAdContentRating()), zztt.zzccc));
    }

    public static zzapu zza(Context context, zzws zzwsVar, String str) {
        return new zzapu(zza(context, zzwsVar), str);
    }

    static final /* synthetic */ int zzf(String str, String str2) {
        return RequestConfiguration.zzabm.indexOf(str) - RequestConfiguration.zzabm.indexOf(str2);
    }
}
