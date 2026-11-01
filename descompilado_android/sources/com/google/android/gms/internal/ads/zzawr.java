package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzawr {
    public static <T> T zza(Context context, String str, zzawq<IBinder, T> zzawqVar) throws zzawt {
        try {
            return zzawqVar.apply(zzbp(context).instantiate(str));
        } catch (Exception e) {
            throw new zzawt(e);
        }
    }

    public static Context zzbo(Context context) throws zzawt {
        return zzbp(context).getModuleContext();
    }

    private static DynamiteModule zzbp(Context context) throws zzawt {
        try {
            return DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID);
        } catch (Exception e) {
            throw new zzawt(e);
        }
    }
}
