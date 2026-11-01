package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.annotation.GuardedBy;
import com.google.android.gms.drive.DriveFile;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzapf extends ContextWrapper {

    @GuardedBy("this")
    private Context zzdni;

    @GuardedBy("this")
    private WeakReference<Activity> zzdnj;

    private zzapf(Context context) {
        super(context);
        this.zzdnj = new WeakReference<>(null);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Context getApplicationContext() {
        return this;
    }

    public final synchronized void setAppPackageName(String str) throws PackageManager.NameNotFoundException {
        this.zzdni = super.createPackageContext(str, 0);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final synchronized ApplicationInfo getApplicationInfo() {
        if (this.zzdni != null) {
            return this.zzdni.getApplicationInfo();
        }
        return super.getApplicationInfo();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final synchronized String getPackageName() {
        if (this.zzdni != null) {
            return this.zzdni.getPackageName();
        }
        return super.getPackageName();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final synchronized String getPackageResourcePath() {
        if (this.zzdni != null) {
            return this.zzdni.getPackageResourcePath();
        }
        return super.getPackageResourcePath();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final synchronized void startActivity(Intent intent) {
        zze(zzd(intent));
    }

    private final synchronized Intent zzd(Intent intent) {
        if (this.zzdni != null && intent.getComponent() != null && intent.getComponent().getPackageName().equals(this.zzdni.getPackageName())) {
            Intent intent2 = (Intent) intent.clone();
            intent2.setClassName(super.getPackageName(), intent.getComponent().getClassName());
            return intent2;
        }
        return intent;
    }

    private final synchronized void zze(Intent intent) {
        Activity activity = this.zzdnj.get();
        if (activity == null) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            super.startActivity(intent);
            return;
        }
        try {
            Intent intent2 = (Intent) intent.clone();
            intent2.setFlags(intent.getFlags() & (-268435457));
            activity.startActivity(intent2);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "");
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            super.startActivity(intent);
        }
    }

    public static zzapf zzy(Context context) {
        return new zzapf(zzk(context));
    }

    public static Context zzz(Context context) {
        if (context instanceof zzapf) {
            return ((zzapf) context).getBaseContext();
        }
        return zzk(context);
    }

    private static Context zzk(Context context) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext == null ? context : applicationContext;
    }
}
