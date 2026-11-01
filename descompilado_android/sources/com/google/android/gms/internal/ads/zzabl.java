package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzabl extends NativeContentAd {
    private final NativeAd.AdChoicesInfo zzcvw;
    private final zzabk zzcvx;
    private final zzaat zzcvy;
    private final List<NativeAd.Image> zzcvu = new ArrayList();
    private final VideoController zzceg = new VideoController();

    public zzabl(zzabk zzabkVar) {
        zzaat zzaatVar;
        zzaas zzaauVar;
        IBinder iBinder;
        this.zzcvx = zzabkVar;
        zzaal zzaalVar = null;
        try {
            List images = this.zzcvx.getImages();
            if (images != null) {
                for (Object obj : images) {
                    if (!(obj instanceof IBinder) || (iBinder = (IBinder) obj) == null) {
                        zzaauVar = null;
                    } else {
                        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                        zzaauVar = iInterfaceQueryLocalInterface instanceof zzaas ? (zzaas) iInterfaceQueryLocalInterface : new zzaau(iBinder);
                    }
                    if (zzaauVar != null) {
                        this.zzcvu.add(new zzaat(zzaauVar));
                    }
                }
            }
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
        try {
            zzaas zzaasVarZzqm = this.zzcvx.zzqm();
            zzaatVar = zzaasVarZzqm != null ? new zzaat(zzaasVarZzqm) : null;
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        this.zzcvy = zzaatVar;
        try {
            if (this.zzcvx.zzqk() != null) {
                zzaalVar = new zzaal(this.zzcvx.zzqk());
            }
        } catch (RemoteException e3) {
            zzawo.zzc("", e3);
        }
        this.zzcvw = zzaalVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.formats.NativeAd
    /* JADX INFO: renamed from: zzqi, reason: merged with bridge method [inline-methods] */
    public final IObjectWrapper zzja() {
        try {
            return this.zzcvx.zzqi();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zzcvx.performClick(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzcvx.recordImpression(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzcvx.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getHeadline() {
        try {
            return this.zzcvx.getHeadline();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final List<NativeAd.Image> getImages() {
        return this.zzcvu;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getBody() {
        try {
            return this.zzcvx.getBody();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final NativeAd.Image getLogo() {
        return this.zzcvy;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getCallToAction() {
        try {
            return this.zzcvx.getCallToAction();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getAdvertiser() {
        try {
            return this.zzcvx.getAdvertiser();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final VideoController getVideoController() {
        try {
            if (this.zzcvx.getVideoController() != null) {
                this.zzceg.zza(this.zzcvx.getVideoController());
            }
        } catch (RemoteException e) {
            zzawo.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzceg;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final Bundle getExtras() {
        try {
            return this.zzcvx.getExtras();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzcvw;
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzcvx.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeContentAd
    public final void destroy() {
        try {
            this.zzcvx.destroy();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
