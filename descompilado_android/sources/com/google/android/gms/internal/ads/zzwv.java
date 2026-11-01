package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.AdapterStatus;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.mediation.rtb.RtbAdapter;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzwv {
    private static final Object lock = new Object();

    @GuardedBy("lock")
    private static zzwv zzcem;
    private zzvu zzcen;
    private RewardedVideoAd zzceo;

    @NonNull
    private RequestConfiguration zzcep = new RequestConfiguration.Builder().build();
    private InitializationStatus zzceq;

    private zzwv() {
    }

    public static zzwv zzpd() {
        zzwv zzwvVar;
        synchronized (lock) {
            if (zzcem == null) {
                zzcem = new zzwv();
            }
            zzwvVar = zzcem;
        }
        return zzwvVar;
    }

    public final void zza(final Context context, String str, zzxe zzxeVar, final OnInitializationCompleteListener onInitializationCompleteListener) {
        synchronized (lock) {
            if (this.zzcen != null) {
                return;
            }
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null.");
            }
            try {
                zzaiv.zzrj().zzc(context, str);
                boolean z = false;
                this.zzcen = new zzuh(zzuo.zzog(), context).zzd(context, false);
                if (onInitializationCompleteListener != null) {
                    this.zzcen.zza(new zzxc(this, onInitializationCompleteListener, null));
                }
                this.zzcen.zza(new zzaje());
                this.zzcen.initialize();
                this.zzcen.zzb(str, ObjectWrapper.wrap(new Runnable(this, context) { // from class: com.google.android.gms.internal.ads.zzwy
                    private final zzwv zzcet;
                    private final Context zzcev;

                    {
                        this.zzcet = this;
                        this.zzcev = context;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzcet.getRewardedVideoAdInstance(this.zzcev);
                    }
                }));
                if (this.zzcep.getTagForChildDirectedTreatment() != -1 || this.zzcep.getTagForUnderAgeOfConsent() != -1) {
                    zza(this.zzcep);
                }
                zzyt.initialize(context);
                if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcqp)).booleanValue()) {
                    if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqx)).booleanValue()) {
                        z = true;
                    }
                }
                if (!z && !zzpe()) {
                    zzawo.zzes("Google Mobile Ads SDK initialization functionality unavailable for this session. Ad requests can be made at any time.");
                    this.zzceq = new InitializationStatus(this) { // from class: com.google.android.gms.internal.ads.zzxa
                        private final zzwv zzcet;

                        {
                            this.zzcet = this;
                        }

                        @Override // com.google.android.gms.ads.initialization.InitializationStatus
                        public final Map getAdapterStatusMap() {
                            zzwv zzwvVar = this.zzcet;
                            HashMap map = new HashMap();
                            map.put("com.google.android.gms.ads.MobileAds", new zzwz(zzwvVar));
                            return map;
                        }
                    };
                    if (onInitializationCompleteListener != null) {
                        zzawe.zzzb.post(new Runnable(this, onInitializationCompleteListener) { // from class: com.google.android.gms.internal.ads.zzwx
                            private final zzwv zzcet;
                            private final OnInitializationCompleteListener zzceu;

                            {
                                this.zzcet = this;
                                this.zzceu = onInitializationCompleteListener;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzcet.zza(this.zzceu);
                            }
                        });
                    }
                }
            } catch (RemoteException e) {
                zzawo.zzd("MobileAdsSettingManager initialization failed", e);
            }
        }
    }

    public final RewardedVideoAd getRewardedVideoAdInstance(Context context) {
        synchronized (lock) {
            if (this.zzceo != null) {
                return this.zzceo;
            }
            this.zzceo = new zzapw(context, new zzum(zzuo.zzog(), context, new zzaje()).zzd(context, false));
            return this.zzceo;
        }
    }

    public final void setAppVolume(float f) {
        Preconditions.checkArgument(0.0f <= f && f <= 1.0f, "The app volume must be a value between 0 and 1 inclusive.");
        Preconditions.checkState(this.zzcen != null, "MobileAds.initialize() must be called prior to setting the app volume.");
        try {
            this.zzcen.setAppVolume(f);
        } catch (RemoteException e) {
            zzawo.zzc("Unable to set app volume.", e);
        }
    }

    public final float zzoo() {
        zzvu zzvuVar = this.zzcen;
        if (zzvuVar == null) {
            return 1.0f;
        }
        try {
            return zzvuVar.zzoo();
        } catch (RemoteException e) {
            zzawo.zzc("Unable to get app volume.", e);
            return 1.0f;
        }
    }

    public final void setAppMuted(boolean z) {
        Preconditions.checkState(this.zzcen != null, "MobileAds.initialize() must be called prior to setting app muted state.");
        try {
            this.zzcen.setAppMuted(z);
        } catch (RemoteException e) {
            zzawo.zzc("Unable to set app mute state.", e);
        }
    }

    public final boolean zzop() {
        zzvu zzvuVar = this.zzcen;
        if (zzvuVar == null) {
            return false;
        }
        try {
            return zzvuVar.zzop();
        } catch (RemoteException e) {
            zzawo.zzc("Unable to get app mute state.", e);
            return false;
        }
    }

    public final void openDebugMenu(Context context, String str) {
        Preconditions.checkState(this.zzcen != null, "MobileAds.initialize() must be called prior to opening debug menu.");
        try {
            this.zzcen.zzc(ObjectWrapper.wrap(context), str);
        } catch (RemoteException e) {
            zzawo.zzc("Unable to open debug menu.", e);
        }
    }

    public final String getVersionString() {
        Preconditions.checkState(this.zzcen != null, "MobileAds.initialize() must be called prior to getting version string.");
        try {
            return this.zzcen.getVersionString();
        } catch (RemoteException e) {
            zzawo.zzc("Unable to get version string.", e);
            return "";
        }
    }

    public final void registerRtbAdapter(Class<? extends RtbAdapter> cls) {
        try {
            this.zzcen.zzbz(cls.getCanonicalName());
        } catch (RemoteException e) {
            zzawo.zzc("Unable to register RtbAdapter", e);
        }
    }

    public final InitializationStatus getInitializationStatus() {
        Preconditions.checkState(this.zzcen != null, "MobileAds.initialize() must be called prior to getting initialization status.");
        try {
            if (this.zzceq != null) {
                return this.zzceq;
            }
            return zzb(this.zzcen.zzoq());
        } catch (RemoteException unused) {
            zzawo.zzes("Unable to get Initialization status.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InitializationStatus zzb(List<zzaex> list) {
        HashMap map = new HashMap();
        for (zzaex zzaexVar : list) {
            map.put(zzaexVar.zzcyb, new zzaff(zzaexVar.zzcyc ? AdapterStatus.State.READY : AdapterStatus.State.NOT_READY, zzaexVar.description, zzaexVar.zzcyd));
        }
        return new zzafi(map);
    }

    @NonNull
    public final RequestConfiguration getRequestConfiguration() {
        return this.zzcep;
    }

    public final void setRequestConfiguration(@NonNull RequestConfiguration requestConfiguration) {
        Preconditions.checkArgument(requestConfiguration != null, "Null passed to setRequestConfiguration.");
        RequestConfiguration requestConfiguration2 = this.zzcep;
        this.zzcep = requestConfiguration;
        if (this.zzcen == null) {
            return;
        }
        if (requestConfiguration2.getTagForChildDirectedTreatment() == requestConfiguration.getTagForChildDirectedTreatment() && requestConfiguration2.getTagForUnderAgeOfConsent() == requestConfiguration.getTagForUnderAgeOfConsent()) {
            return;
        }
        zza(requestConfiguration);
    }

    private final void zza(@NonNull RequestConfiguration requestConfiguration) {
        try {
            this.zzcen.zza(new zzxw(requestConfiguration));
        } catch (RemoteException e) {
            zzawo.zzc("Unable to set request configuration parcel.", e);
        }
    }

    private final boolean zzpe() throws RemoteException {
        try {
            return this.zzcen.getVersionString().endsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } catch (RemoteException unused) {
            zzawo.zzes("Unable to get version string.");
            return true;
        }
    }

    final /* synthetic */ void zza(OnInitializationCompleteListener onInitializationCompleteListener) {
        onInitializationCompleteListener.onInitializationComplete(this.zzceq);
    }
}
