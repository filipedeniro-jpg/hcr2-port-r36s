package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzawa {
    private boolean zzduu;
    private Context zzzc;
    private boolean zzye = false;

    @GuardedBy("this")
    private final Map<BroadcastReceiver, IntentFilter> zzdut = new WeakHashMap();

    @GuardedBy("this")
    private final BroadcastReceiver zzdus = new zzawd(this);

    public final synchronized void initialize(Context context) {
        if (this.zzye) {
            return;
        }
        this.zzzc = context.getApplicationContext();
        if (this.zzzc == null) {
            this.zzzc = context;
        }
        zzyt.initialize(this.zzzc);
        this.zzduu = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcoc)).booleanValue();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        this.zzzc.registerReceiver(this.zzdus, intentFilter);
        this.zzye = true;
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        if (this.zzduu) {
            this.zzdut.put(broadcastReceiver, intentFilter);
        } else {
            context.registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    public final synchronized void zza(Context context, BroadcastReceiver broadcastReceiver) {
        if (this.zzduu) {
            this.zzdut.remove(broadcastReceiver);
        } else {
            context.unregisterReceiver(broadcastReceiver);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final synchronized void zzc(Context context, Intent intent) {
        for (Map.Entry<BroadcastReceiver, IntentFilter> entry : this.zzdut.entrySet()) {
            if (entry.getValue().hasAction(intent.getAction())) {
                entry.getKey().onReceive(context, intent);
            }
        }
    }
}
