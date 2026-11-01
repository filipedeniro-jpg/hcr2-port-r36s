package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzabp implements NativeCustomTemplateAd {
    private static WeakHashMap<IBinder, zzabp> zzcvz = new WeakHashMap<>();
    private final VideoController zzceg = new VideoController();
    private final zzabo zzcwa;
    private final MediaView zzcwb;
    private NativeCustomTemplateAd.DisplayOpenMeasurement zzcwc;

    @VisibleForTesting
    private zzabp(zzabo zzaboVar) {
        Context context;
        this.zzcwa = zzaboVar;
        MediaView mediaView = null;
        try {
            context = (Context) ObjectWrapper.unwrap(zzaboVar.zzqn());
        } catch (RemoteException | NullPointerException e) {
            zzawo.zzc("", e);
            context = null;
        }
        if (context != null) {
            MediaView mediaView2 = new MediaView(context);
            try {
                if (this.zzcwa.zzt(ObjectWrapper.wrap(mediaView2))) {
                    mediaView = mediaView2;
                }
            } catch (RemoteException e2) {
                zzawo.zzc("", e2);
            }
        }
        this.zzcwb = mediaView;
    }

    public static zzabp zza(zzabo zzaboVar) {
        synchronized (zzcvz) {
            zzabp zzabpVar = zzcvz.get(zzaboVar.asBinder());
            if (zzabpVar != null) {
                return zzabpVar;
            }
            zzabp zzabpVar2 = new zzabp(zzaboVar);
            zzcvz.put(zzaboVar.asBinder(), zzabpVar2);
            return zzabpVar2;
        }
    }

    public final zzabo zzqr() {
        return this.zzcwa;
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final CharSequence getText(String str) {
        try {
            return this.zzcwa.zzco(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final NativeAd.Image getImage(String str) {
        try {
            zzaas zzaasVarZzcp = this.zzcwa.zzcp(str);
            if (zzaasVarZzcp != null) {
                return new zzaat(zzaasVarZzcp);
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final VideoController getVideoController() {
        try {
            zzwk videoController = this.zzcwa.getVideoController();
            if (videoController != null) {
                this.zzceg.zza(videoController);
            }
        } catch (RemoteException e) {
            zzawo.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzceg;
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final MediaView getVideoMediaView() {
        return this.zzcwb;
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final List<String> getAvailableAssetNames() {
        try {
            return this.zzcwa.getAvailableAssetNames();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final String getCustomTemplateId() {
        try {
            return this.zzcwa.getCustomTemplateId();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void performClick(String str) {
        try {
            this.zzcwa.performClick(str);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void recordImpression() {
        try {
            this.zzcwa.recordImpression();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final NativeCustomTemplateAd.DisplayOpenMeasurement getDisplayOpenMeasurement() {
        try {
            if (this.zzcwc == null && this.zzcwa.zzqo()) {
                this.zzcwc = new zzaao(this.zzcwa);
            }
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
        return this.zzcwc;
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd
    public final void destroy() {
        try {
            this.zzcwa.destroy();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
