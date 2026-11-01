package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.LocationConst;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzayc extends FrameLayout implements zzayb {
    private final zzayt zzdxm;
    private final FrameLayout zzdxn;
    private final zzzh zzdxo;
    private final zzayv zzdxp;
    private final long zzdxq;

    @Nullable
    private zzaya zzdxr;
    private boolean zzdxs;
    private boolean zzdxt;
    private boolean zzdxu;
    private boolean zzdxv;
    private long zzdxw;
    private long zzdxx;
    private String zzdxy;
    private String[] zzdxz;
    private Bitmap zzdya;
    private ImageView zzdyb;
    private boolean zzdyc;

    public static void zza(zzayt zzaytVar) {
        HashMap map = new HashMap();
        map.put("event", "no_video_view");
        zzaytVar.zza("onVideoEvent", map);
    }

    public static void zza(zzayt zzaytVar, Map<String, List<Map<String, Object>>> map) {
        HashMap map2 = new HashMap();
        map2.put("event", "decoderProps");
        map2.put("mimeTypes", map);
        zzaytVar.zza("onVideoEvent", map2);
    }

    public static void zza(zzayt zzaytVar, String str) {
        HashMap map = new HashMap();
        map.put("event", "decoderProps");
        map.put("error", str);
        zzaytVar.zza("onVideoEvent", map);
    }

    public zzayc(Context context, zzayt zzaytVar, int i, boolean z, zzzh zzzhVar, zzayq zzayqVar) {
        super(context);
        this.zzdxm = zzaytVar;
        this.zzdxo = zzzhVar;
        this.zzdxn = new FrameLayout(context);
        addView(this.zzdxn, new FrameLayout.LayoutParams(-1, -1));
        Preconditions.checkNotNull(zzaytVar.zzxm());
        this.zzdxr = zzaytVar.zzxm().zzbkp.zza(context, zzaytVar, i, z, zzzhVar, zzayqVar);
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar != null) {
            this.zzdxn.addView(zzayaVar, new FrameLayout.LayoutParams(-1, -1, 17));
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzchj)).booleanValue()) {
                zzwy();
            }
        }
        this.zzdyb = new ImageView(context);
        this.zzdxq = ((Long) zzuo.zzoj().zzd(zzyt.zzchn)).longValue();
        this.zzdxv = ((Boolean) zzuo.zzoj().zzd(zzyt.zzchl)).booleanValue();
        zzzh zzzhVar2 = this.zzdxo;
        if (zzzhVar2 != null) {
            zzzhVar2.zzj("spinner_used", this.zzdxv ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        }
        this.zzdxp = new zzayv(this);
        zzaya zzayaVar2 = this.zzdxr;
        if (zzayaVar2 != null) {
            zzayaVar2.zza(this);
        }
        if (this.zzdxr == null) {
            zzn("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    public final void zzd(int i, int i2, int i3, int i4) {
        if (i3 == 0 || i4 == 0) {
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(i3, i4);
        layoutParams.setMargins(i, i2, 0, 0);
        this.zzdxn.setLayoutParams(layoutParams);
        requestLayout();
    }

    public final void zzc(String str, String[] strArr) {
        this.zzdxy = str;
        this.zzdxz = strArr;
    }

    public final void zza(float f, float f2) {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar != null) {
            zzayaVar.zza(f, f2);
        }
    }

    public final void zzhh() {
        if (this.zzdxr == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.zzdxy)) {
            this.zzdxr.zzb(this.zzdxy, this.zzdxz);
        } else {
            zzd("no_src", new String[0]);
        }
    }

    public final void pause() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.pause();
    }

    public final void play() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.play();
    }

    public final void seekTo(int i) {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.seekTo(i);
    }

    public final void zzww() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.zzdxl.setMuted(true);
        zzayaVar.zzwq();
    }

    public final void zzwx() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.zzdxl.setMuted(false);
        zzayaVar.zzwq();
    }

    public final void setVolume(float f) {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.zzdxl.setVolume(f);
        zzayaVar.zzwq();
    }

    public final void zzct(int i) {
        this.zzdxr.zzct(i);
    }

    public final void zzcu(int i) {
        this.zzdxr.zzcu(i);
    }

    public final void zzcv(int i) {
        this.zzdxr.zzcv(i);
    }

    public final void zzcw(int i) {
        this.zzdxr.zzcw(i);
    }

    public final void zzcx(int i) {
        this.zzdxr.zzcx(i);
    }

    @TargetApi(14)
    public final void zze(MotionEvent motionEvent) {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        zzayaVar.dispatchTouchEvent(motionEvent);
    }

    @TargetApi(14)
    public final void zzwy() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        TextView textView = new TextView(zzayaVar.getContext());
        String strValueOf = String.valueOf(this.zzdxr.zzwm());
        textView.setText(strValueOf.length() != 0 ? "AdMob - ".concat(strValueOf) : new String("AdMob - "));
        textView.setTextColor(SupportMenu.CATEGORY_MASK);
        textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
        this.zzdxn.addView(textView, new FrameLayout.LayoutParams(-2, -2, 17));
        this.zzdxn.bringChildToFront(textView);
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzwr() {
        this.zzdxp.resume();
        zzatv.zzdsk.post(new zzayh(this));
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzei() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar != null && this.zzdxx == 0) {
            zzd("canplaythrough", "duration", String.valueOf(zzayaVar.getDuration() / 1000.0f), "videoWidth", String.valueOf(this.zzdxr.getVideoWidth()), "videoHeight", String.valueOf(this.zzdxr.getVideoHeight()));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzws() {
        if (this.zzdxm.zzxl() != null && !this.zzdxt) {
            this.zzdxu = (this.zzdxm.zzxl().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzdxu) {
                this.zzdxm.zzxl().getWindow().addFlags(128);
                this.zzdxt = true;
            }
        }
        this.zzdxs = true;
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void onPaused() {
        zzd("pause", new String[0]);
        zzxb();
        this.zzdxs = false;
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzwt() {
        zzd(Constants.ParametersKeys.VIDEO_STATUS_ENDED, new String[0]);
        zzxb();
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzn(String str, @Nullable String str2) {
        zzd("error", "what", str, "extra", str2);
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzwu() {
        if (this.zzdyc && this.zzdya != null && !zzxa()) {
            this.zzdyb.setImageBitmap(this.zzdya);
            this.zzdyb.invalidate();
            this.zzdxn.addView(this.zzdyb, new FrameLayout.LayoutParams(-1, -1));
            this.zzdxn.bringChildToFront(this.zzdyb);
        }
        this.zzdxp.pause();
        this.zzdxx = this.zzdxw;
        zzatv.zzdsk.post(new zzayg(this));
    }

    public final void destroy() {
        this.zzdxp.pause();
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar != null) {
            zzayaVar.stop();
        }
        zzxb();
    }

    public final void finalize() throws Throwable {
        try {
            this.zzdxp.pause();
            if (this.zzdxr != null) {
                zzaya zzayaVar = this.zzdxr;
                zzdcs zzdcsVar = zzawx.zzdwa;
                zzayaVar.getClass();
                zzdcsVar.execute(zzayf.zza(zzayaVar));
            }
        } finally {
            super.finalize();
        }
    }

    final void zzwz() {
        zzaya zzayaVar = this.zzdxr;
        if (zzayaVar == null) {
            return;
        }
        long currentPosition = zzayaVar.getCurrentPosition();
        if (this.zzdxw == currentPosition || currentPosition <= 0) {
            return;
        }
        zzd("timeupdate", LocationConst.TIME, String.valueOf(currentPosition / 1000.0f));
        this.zzdxw = currentPosition;
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzwv() {
        if (this.zzdxs && zzxa()) {
            this.zzdxn.removeView(this.zzdyb);
        }
        if (this.zzdya != null) {
            long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
            if (this.zzdxr.getBitmap(this.zzdya) != null) {
                this.zzdyc = true;
            }
            long jElapsedRealtime2 = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - jElapsedRealtime;
            if (zzatm.zzuq()) {
                StringBuilder sb = new StringBuilder(46);
                sb.append("Spinner frame grab took ");
                sb.append(jElapsedRealtime2);
                sb.append("ms");
                zzatm.zzdy(sb.toString());
            }
            if (jElapsedRealtime2 > this.zzdxq) {
                zzatm.zzeu("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzdxv = false;
                this.zzdya = null;
                zzzh zzzhVar = this.zzdxo;
                if (zzzhVar != null) {
                    zzzhVar.zzj("spinner_jank", Long.toString(jElapsedRealtime2));
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzayb
    public final void zzj(int i, int i2) {
        if (this.zzdxv) {
            int iMax = Math.max(i / ((Integer) zzuo.zzoj().zzd(zzyt.zzchm)).intValue(), 1);
            int iMax2 = Math.max(i2 / ((Integer) zzuo.zzoj().zzd(zzyt.zzchm)).intValue(), 1);
            Bitmap bitmap = this.zzdya;
            if (bitmap != null && bitmap.getWidth() == iMax && this.zzdya.getHeight() == iMax2) {
                return;
            }
            this.zzdya = Bitmap.createBitmap(iMax, iMax2, Bitmap.Config.ARGB_8888);
            this.zzdyc = false;
        }
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(final boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.zzdxp.resume();
        } else {
            this.zzdxp.pause();
            this.zzdxx = this.zzdxw;
        }
        zzatv.zzdsk.post(new Runnable(this, z) { // from class: com.google.android.gms.internal.ads.zzaye
            private final zzayc zzdyd;
            private final boolean zzdye;

            {
                this.zzdyd = this;
                this.zzdye = z;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzdyd.zzan(this.zzdye);
            }
        });
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzayb
    public final void onWindowVisibilityChanged(int i) {
        boolean z;
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.zzdxp.resume();
            z = true;
        } else {
            this.zzdxp.pause();
            this.zzdxx = this.zzdxw;
            z = false;
        }
        zzatv.zzdsk.post(new zzayj(this, z));
    }

    private final boolean zzxa() {
        return this.zzdyb.getParent() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzd(String str, String... strArr) {
        HashMap map = new HashMap();
        map.put("event", str);
        String str2 = null;
        for (String str3 : strArr) {
            if (str2 == null) {
                str2 = str3;
            } else {
                map.put(str2, str3);
                str2 = null;
            }
        }
        this.zzdxm.zza("onVideoEvent", map);
    }

    private final void zzxb() {
        if (this.zzdxm.zzxl() == null || !this.zzdxt || this.zzdxu) {
            return;
        }
        this.zzdxm.zzxl().getWindow().clearFlags(128);
        this.zzdxt = false;
    }

    final /* synthetic */ void zzan(boolean z) {
        zzd("windowFocusChanged", "hasWindowFocus", String.valueOf(z));
    }
}
