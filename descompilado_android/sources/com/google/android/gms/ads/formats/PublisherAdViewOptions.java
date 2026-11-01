package com.google.android.gms.ads.formats;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.ads.zzacj;
import com.google.android.gms.internal.ads.zzack;
import com.google.android.gms.internal.ads.zzty;
import com.google.android.gms.internal.ads.zzvl;
import com.google.android.gms.internal.ads.zzvm;
import com.google.android.gms.internal.ads.zzya;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "PublisherAdViewOptionsCreator")
public final class PublisherAdViewOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<PublisherAdViewOptions> CREATOR = new zzc();

    @SafeParcelable.Field(getter = "getManualImpressionsEnabled", id = 1)
    private final boolean zzbke;

    @Nullable
    @SafeParcelable.Field(getter = "getAppEventListenerBinder", id = 2, type = "android.os.IBinder")
    private final zzvm zzbkf;

    @Nullable
    private AppEventListener zzbkg;

    @Nullable
    @SafeParcelable.Field(getter = "getDelayedBannerAdListenerBinder", id = 3)
    private final IBinder zzbkh;

    private PublisherAdViewOptions(Builder builder) {
        this.zzbke = builder.zzbke;
        this.zzbkg = builder.zzbkg;
        AppEventListener appEventListener = this.zzbkg;
        this.zzbkf = appEventListener != null ? new zzty(appEventListener) : null;
        this.zzbkh = builder.zzbki != null ? new zzya(builder.zzbki) : null;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static final class Builder {
        private boolean zzbke = false;

        @Nullable
        private AppEventListener zzbkg;

        @Nullable
        private ShouldDelayBannerRenderingListener zzbki;

        public final Builder setManualImpressionsEnabled(boolean z) {
            this.zzbke = z;
            return this;
        }

        public final Builder setAppEventListener(AppEventListener appEventListener) {
            this.zzbkg = appEventListener;
            return this;
        }

        @KeepForSdk
        public final Builder setShouldDelayBannerRenderingListener(ShouldDelayBannerRenderingListener shouldDelayBannerRenderingListener) {
            this.zzbki = shouldDelayBannerRenderingListener;
            return this;
        }

        public final PublisherAdViewOptions build() {
            return new PublisherAdViewOptions(this);
        }
    }

    @SafeParcelable.Constructor
    PublisherAdViewOptions(@SafeParcelable.Param(id = 1) boolean z, @Nullable @SafeParcelable.Param(id = 2) IBinder iBinder, @Nullable @SafeParcelable.Param(id = 3) IBinder iBinder2) {
        this.zzbke = z;
        this.zzbkf = iBinder != null ? zzvl.zze(iBinder) : null;
        this.zzbkh = iBinder2;
    }

    @Nullable
    public final AppEventListener getAppEventListener() {
        return this.zzbkg;
    }

    public final boolean getManualImpressionsEnabled() {
        return this.zzbke;
    }

    @Nullable
    public final zzvm zzjd() {
        return this.zzbkf;
    }

    @Nullable
    public final zzack zzje() {
        return zzacj.zzv(this.zzbkh);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, getManualImpressionsEnabled());
        zzvm zzvmVar = this.zzbkf;
        SafeParcelWriter.writeIBinder(parcel, 2, zzvmVar == null ? null : zzvmVar.asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 3, this.zzbkh, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
