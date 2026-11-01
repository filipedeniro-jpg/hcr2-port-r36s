package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacr extends UnifiedNativeAd {
    private final zzaat zzcvv;
    private final NativeAd.AdChoicesInfo zzcvw;
    private final zzacq zzcwd;
    private final List<NativeAd.Image> zzcvu = new ArrayList();
    private final VideoController zzceg = new VideoController();
    private final List<MuteThisAdReason> zzcwe = new ArrayList();

    public zzacr(zzacq zzacqVar) {
        zzaat zzaatVar;
        zzaas zzaauVar;
        IBinder iBinder;
        this.zzcwd = zzacqVar;
        zzaal zzaalVar = null;
        try {
            List images = this.zzcwd.getImages();
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
            List muteThisAdReasons = this.zzcwd.getMuteThisAdReasons();
            if (muteThisAdReasons != null) {
                for (Object obj2 : muteThisAdReasons) {
                    zzwb zzwbVarZzg = obj2 instanceof IBinder ? zzwe.zzg((IBinder) obj2) : null;
                    if (zzwbVarZzg != null) {
                        this.zzcwe.add(new zzwg(zzwbVarZzg));
                    }
                }
            }
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
        try {
            zzaas zzaasVarZzqj = this.zzcwd.zzqj();
            zzaatVar = zzaasVarZzqj != null ? new zzaat(zzaasVarZzqj) : null;
        } catch (RemoteException e3) {
            zzawo.zzc("", e3);
        }
        this.zzcvv = zzaatVar;
        try {
            if (this.zzcwd.zzqk() != null) {
                zzaalVar = new zzaal(this.zzcwd.zzqk());
            }
        } catch (RemoteException e4) {
            zzawo.zzc("", e4);
        }
        this.zzcvw = zzaalVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    /* JADX INFO: renamed from: zzqi, reason: merged with bridge method [inline-methods] */
    public final IObjectWrapper zzja() {
        try {
            return this.zzcwd.zzqi();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Object zzjf() {
        try {
            IObjectWrapper iObjectWrapperZzql = this.zzcwd.zzql();
            if (iObjectWrapperZzql != null) {
                return ObjectWrapper.unwrap(iObjectWrapperZzql);
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void performClick(Bundle bundle) {
        try {
            this.zzcwd.performClick(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzcwd.recordImpression(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzcwd.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getHeadline() {
        try {
            return this.zzcwd.getHeadline();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List<NativeAd.Image> getImages() {
        return this.zzcvu;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getBody() {
        try {
            return this.zzcwd.getBody();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final NativeAd.Image getIcon() {
        return this.zzcvv;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getCallToAction() {
        try {
            return this.zzcwd.getCallToAction();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Double getStarRating() {
        try {
            double starRating = this.zzcwd.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getStore() {
        try {
            return this.zzcwd.getStore();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getPrice() {
        try {
            return this.zzcwd.getPrice();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final VideoController getVideoController() {
        try {
            if (this.zzcwd.getVideoController() != null) {
                this.zzceg.zza(this.zzcwd.getVideoController());
            }
        } catch (RemoteException e) {
            zzawo.zzc("Exception occurred while getting video controller", e);
        }
        return this.zzceg;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzcvw;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getMediationAdapterClassName() {
        try {
            return this.zzcwd.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final Bundle getExtras() {
        try {
            Bundle extras = this.zzcwd.getExtras();
            if (extras != null) {
                return extras;
            }
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
        return new Bundle();
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void enableCustomClickGesture() {
        try {
            this.zzcwd.zzqs();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean isCustomClickGestureEnabled() {
        try {
            return this.zzcwd.isCustomClickGestureEnabled();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void recordCustomClickGesture() {
        try {
            this.zzcwd.recordCustomClickGesture();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final List<MuteThisAdReason> getMuteThisAdReasons() {
        return this.zzcwe;
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zzcwd.isCustomMuteThisAdEnabled();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void destroy() {
        try {
            this.zzcwd.destroy();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setUnconfirmedClickListener(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzcwd.zza(new zzade(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzawo.zzc("Failed to setUnconfirmedClickListener", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void muteThisAd(MuteThisAdReason muteThisAdReason) {
        try {
            if (!isCustomMuteThisAdEnabled()) {
                zzawo.zzes("Ad is not custom mute enabled");
                return;
            }
            if (muteThisAdReason == null) {
                this.zzcwd.zza((zzwb) null);
            } else if (muteThisAdReason instanceof zzwg) {
                this.zzcwd.zza(((zzwg) muteThisAdReason).zzos());
            } else {
                zzawo.zzes("Use mute reason from UnifiedNativeAd.getMuteThisAdReasons() or null");
            }
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener) {
        try {
            this.zzcwd.zza(new zzwc(muteThisAdListener));
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final void cancelUnconfirmedClick() {
        try {
            this.zzcwd.cancelUnconfirmedClick();
        } catch (RemoteException e) {
            zzawo.zzc("Failed to cancelUnconfirmedClick", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final String getAdvertiser() {
        try {
            return this.zzcwd.getAdvertiser();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }

    @Override // com.google.android.gms.ads.formats.UnifiedNativeAd
    public final UnifiedNativeAd.MediaContent getMediaContent() {
        try {
            if (this.zzcwd.zzqt() != null) {
                return new zzacu(this.zzcwd.zzqt());
            }
            return null;
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return null;
        }
    }
}
