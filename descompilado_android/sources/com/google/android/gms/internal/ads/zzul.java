package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzul<T> {

    @Nullable
    private static final zzvn zzcde = zzoc();

    zzul() {
    }

    @Nullable
    protected abstract T zza(zzvn zzvnVar) throws RemoteException;

    @NonNull
    protected abstract T zzoa();

    @Nullable
    protected abstract T zzob() throws RemoteException;

    private static zzvn zzoc() {
        try {
            Object objNewInstance = zzuc.class.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (!(objNewInstance instanceof IBinder)) {
                zzawo.zzeu("ClientApi class is not an instance of IBinder.");
                return null;
            }
            IBinder iBinder = (IBinder) objNewInstance;
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
            return iInterfaceQueryLocalInterface instanceof zzvn ? (zzvn) iInterfaceQueryLocalInterface : new zzvp(iBinder);
        } catch (Exception unused) {
            zzawo.zzeu("Failed to instantiate ClientApi class.");
            return null;
        }
    }

    @Nullable
    private final T zzod() {
        zzvn zzvnVar = zzcde;
        if (zzvnVar == null) {
            zzawo.zzeu("ClientApi class cannot be loaded.");
            return null;
        }
        try {
            return zza(zzvnVar);
        } catch (RemoteException e) {
            zzawo.zzd("Cannot invoke local loader using ClientApi class.", e);
            return null;
        }
    }

    @Nullable
    private final T zzoe() {
        try {
            return zzob();
        } catch (RemoteException e) {
            zzawo.zzd("Cannot invoke remote loader.", e);
            return null;
        }
    }

    public final T zzd(Context context, boolean z) {
        T tZzod;
        boolean z2 = z;
        if (!z2) {
            zzuo.zzof();
            if (!zzawe.zzc(context, 12451000)) {
                zzawo.zzdv("Google Play Services is not available.");
                z2 = true;
            }
        }
        if (DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID) > DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID)) {
            z2 = true;
        }
        zzyt.initialize(context);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqj)).booleanValue()) {
            z2 = false;
        }
        if (z2) {
            tZzod = zzod();
            if (tZzod == null) {
                tZzod = zzoe();
            }
        } else {
            T tZzoe = zzoe();
            int i = tZzoe == null ? 1 : 0;
            if (i != 0) {
                if (zzuo.zzom().nextInt(((Integer) zzuo.zzoj().zzd(zzyt.zzcsr)).intValue()) == 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("action", "dynamite_load");
                    bundle.putInt("is_missing", i);
                    zzuo.zzof().zza(context, zzuo.zzol().zzbnh, "gmob-apps", bundle, true);
                }
            }
            tZzod = tZzoe == null ? zzod() : tZzoe;
        }
        return tZzod == null ? zzoa() : tZzod;
    }
}
