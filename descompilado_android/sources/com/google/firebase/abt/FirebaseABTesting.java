package com.google.firebase.abt;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@KeepForSdk
public class FirebaseABTesting {
    private final AnalyticsConnector zzj;
    private final String zzk;

    @Nullable
    private Integer zzl = null;

    @KeepForSdk
    public FirebaseABTesting(Context context, AnalyticsConnector analyticsConnector, String str) {
        this.zzj = analyticsConnector;
        this.zzk = str;
    }

    @KeepForSdk
    @WorkerThread
    public void replaceAllExperiments(List<Map<String, String>> list) throws AbtException {
        zzg();
        if (list == null) {
            throw new IllegalArgumentException("The replacementExperiments list is null.");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Map<String, String>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(zza.zza(it.next()));
        }
        if (arrayList.isEmpty()) {
            removeAllExperiments();
            return;
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i2 < size) {
            Object obj = arrayList2.get(i2);
            i2++;
            hashSet.add(((zza) obj).zza());
        }
        List<AnalyticsConnector.ConditionalUserProperty> listZzh = zzh();
        HashSet hashSet2 = new HashSet();
        Iterator<AnalyticsConnector.ConditionalUserProperty> it2 = listZzh.iterator();
        while (it2.hasNext()) {
            hashSet2.add(it2.next().name);
        }
        ArrayList arrayList3 = new ArrayList();
        for (AnalyticsConnector.ConditionalUserProperty conditionalUserProperty : listZzh) {
            if (!hashSet.contains(conditionalUserProperty.name)) {
                arrayList3.add(conditionalUserProperty);
            }
        }
        zza(arrayList3);
        ArrayList arrayList4 = new ArrayList();
        int size2 = arrayList2.size();
        int i3 = 0;
        while (i3 < size2) {
            Object obj2 = arrayList2.get(i3);
            i3++;
            zza zzaVar = (zza) obj2;
            if (!hashSet2.contains(zzaVar.zza())) {
                arrayList4.add(zzaVar);
            }
        }
        ArrayDeque arrayDeque = new ArrayDeque(zzh());
        if (this.zzl == null) {
            this.zzl = Integer.valueOf(this.zzj.getMaxUserProperties(this.zzk));
        }
        int iIntValue = this.zzl.intValue();
        ArrayList arrayList5 = arrayList4;
        int size3 = arrayList5.size();
        while (i < size3) {
            Object obj3 = arrayList5.get(i);
            i++;
            zza zzaVar2 = (zza) obj3;
            while (arrayDeque.size() >= iIntValue) {
                zza(((AnalyticsConnector.ConditionalUserProperty) arrayDeque.pollFirst()).name);
            }
            AnalyticsConnector.ConditionalUserProperty conditionalUserProperty2 = new AnalyticsConnector.ConditionalUserProperty();
            conditionalUserProperty2.origin = this.zzk;
            conditionalUserProperty2.creationTimestamp = zzaVar2.zzd();
            conditionalUserProperty2.name = zzaVar2.zza();
            conditionalUserProperty2.value = zzaVar2.zzb();
            conditionalUserProperty2.triggerEventName = TextUtils.isEmpty(zzaVar2.zzc()) ? null : zzaVar2.zzc();
            conditionalUserProperty2.triggerTimeout = zzaVar2.zze();
            conditionalUserProperty2.timeToLive = zzaVar2.zzf();
            this.zzj.setConditionalUserProperty(conditionalUserProperty2);
            arrayDeque.offer(conditionalUserProperty2);
        }
    }

    @KeepForSdk
    @WorkerThread
    public void removeAllExperiments() throws AbtException {
        zzg();
        zza(zzh());
    }

    private final void zza(Collection<AnalyticsConnector.ConditionalUserProperty> collection) {
        Iterator<AnalyticsConnector.ConditionalUserProperty> it = collection.iterator();
        while (it.hasNext()) {
            zza(it.next().name);
        }
    }

    private final void zzg() throws AbtException {
        if (this.zzj == null) {
            throw new AbtException("The Analytics SDK is not available. Please check that the Analytics SDK is included in your app dependencies.");
        }
    }

    @VisibleForTesting
    private final void zza(String str) {
        this.zzj.clearConditionalUserProperty(str, null, null);
    }

    @WorkerThread
    private final List<AnalyticsConnector.ConditionalUserProperty> zzh() {
        return this.zzj.getConditionalUserProperties(this.zzk, "");
    }
}
