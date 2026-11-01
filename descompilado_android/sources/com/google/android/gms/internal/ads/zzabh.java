package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzabh extends NativeAppInstallAd {
    private final zzabg zzcvt;
    private final zzaat zzcvv;
    private final NativeAd.AdChoicesInfo zzcvw;
    private final List<NativeAd.Image> zzcvu = new ArrayList();
    private final VideoController zzceg = new VideoController();

    public zzabh(zzabg zzabgVar) {
        zzaat zzaatVar;
        zzaas zzaauVar;
        IBinder iBinder;
        this.zzcvt = zzabgVar;
        zzaal zzaalVar = null;
        try {
            List images = this.zzcvt.getImages();
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
            zzaas zzaasVarZzqj = this.zzcvt.zzqj();
            zzaatVar = zzaasVarZzqj != null ? new zzaat(zzaasVarZzqj) : null;
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        this.zzcvv = zzaatVar;
        try {
            if (this.zzcvt.zzqk() != null) {
                zzaalVar = new zzaal(this.zzcvt.zzqk());
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
            return this.zzcvt.zzqi();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zzcvt.performClick(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzcvt.recordImpression(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzcvt.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getHeadline() {
        try {
            return this.zzcvt.getHeadline();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final List<NativeAd.Image> getImages() {
        return this.zzcvu;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getBody() {
        try {
            return this.zzcvt.getBody();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final NativeAd.Image getIcon() {
        return this.zzcvv;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getCallToAction() {
        try {
            return this.zzcvt.getCallToAction();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final Double getStarRating() {
        try {
            double starRating = this.zzcvt.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getStore() {
        try {
            return this.zzcvt.getStore();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getPrice() {
        try {
            return this.zzcvt.getPrice();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final VideoController getVideoController() {
        try {
            if (this.zzcvt.getVideoController() != null) {
                this.zzceg.zza(this.zzcvt.getVideoController());
            }
        } catch (RemoteException e) {
            zzawo.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzceg;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzcvw;
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final CharSequence getMediationAdapterClassName() {
        try {
            return this.zzcvt.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final Bundle getExtras() {
        try {
            return this.zzcvt.getExtras();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAppInstallAd
    public final void destroy() {
        try {
            this.zzcvt.destroy();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
