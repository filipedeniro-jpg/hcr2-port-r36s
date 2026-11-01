package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbcq extends MutableContextWrapper {
    private Activity zzdvh;
    private Context zzehl;
    private Context zzzc;

    public zzbcq(Context context) {
        super(context);
        setBaseContext(context);
    }

    @Override // android.content.MutableContextWrapper
    public final void setBaseContext(Context context) {
        this.zzzc = context.getApplicationContext();
        this.zzdvh = context instanceof Activity ? (Activity) context : null;
        this.zzehl = context;
        super.setBaseContext(this.zzzc);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final void startActivity(Intent intent) {
        Activity activity = this.zzdvh;
        if (activity != null) {
            activity.startActivity(intent);
        } else {
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            this.zzzc.startActivity(intent);
        }
    }

    public final Activity zzxl() {
        return this.zzdvh;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        return this.zzehl.getSystemService(str);
    }

    public final Context zzyt() {
        return this.zzehl;
    }
}
