package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import com.amazon.device.ads.WebRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaun {
    private Handler handler;
    private int state;
    private String zzbnh;
    private String zzccm;
    private String zzdhs;

    @Nullable
    private String zzdky;
    private int zzdsu;
    private PointF zzdsv;
    private PointF zzdsw;
    private Runnable zzdsx;
    private final Context zzlk;

    public zzaun(Context context) {
        this.state = 0;
        this.zzdsx = new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzaum
            private final zzaun zzdst;

            {
                this.zzdst = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzdst.zzvu();
            }
        };
        this.zzlk = context;
        this.zzdsu = ViewConfiguration.get(context).getScaledTouchSlop();
        com.google.android.gms.ads.internal.zzp.zzkm().zzvz();
        this.handler = com.google.android.gms.ads.internal.zzp.zzkm().getHandler();
    }

    public zzaun(Context context, String str) {
        this(context);
        this.zzdhs = str;
    }

    public final void zzd(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        int historySize = motionEvent.getHistorySize();
        int pointerCount = motionEvent.getPointerCount();
        if (actionMasked == 0) {
            this.state = 0;
            this.zzdsv = new PointF(motionEvent.getX(0), motionEvent.getY(0));
            return;
        }
        int i = this.state;
        if (i == -1) {
            return;
        }
        boolean z = true;
        if (i == 0 && actionMasked == 5) {
            this.state = 5;
            this.zzdsw = new PointF(motionEvent.getX(1), motionEvent.getY(1));
            this.handler.postDelayed(this.zzdsx, ((Long) zzuo.zzoj().zzd(zzyt.zzcps)).longValue());
            return;
        }
        if (this.state == 5) {
            if (pointerCount == 2) {
                if (actionMasked == 2) {
                    boolean z2 = false;
                    for (int i2 = 0; i2 < historySize; i2++) {
                        if (!zza(motionEvent.getHistoricalX(0, i2), motionEvent.getHistoricalY(0, i2), motionEvent.getHistoricalX(1, i2), motionEvent.getHistoricalY(1, i2))) {
                            z2 = true;
                        }
                    }
                    if (zza(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1))) {
                        z = z2;
                    }
                } else {
                    z = false;
                }
            }
            if (z) {
                this.state = -1;
                this.handler.removeCallbacks(this.zzdsx);
            }
        }
    }

    private final boolean zza(float f, float f2, float f3, float f4) {
        return Math.abs(this.zzdsv.x - f) < ((float) this.zzdsu) && Math.abs(this.zzdsv.y - f2) < ((float) this.zzdsu) && Math.abs(this.zzdsw.x - f3) < ((float) this.zzdsu) && Math.abs(this.zzdsw.y - f4) < ((float) this.zzdsu);
    }

    public final void showDialog() {
        try {
            if (!(this.zzlk instanceof Activity)) {
                zzatm.zzet("Can not create dialog without Activity Context");
                return;
            }
            String str = !TextUtils.isEmpty(com.google.android.gms.ads.internal.zzp.zzki().zzvv()) ? "Creative Preview (Enabled)" : "Creative Preview";
            String str2 = com.google.android.gms.ads.internal.zzp.zzki().zzvw() ? "Troubleshooting (Enabled)" : "Troubleshooting";
            ArrayList arrayList = new ArrayList();
            final int iZza = zza((List<String>) arrayList, "Ad Information", true);
            final int iZza2 = zza((List<String>) arrayList, str, true);
            final int iZza3 = zza((List<String>) arrayList, str2, true);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.zzlk, com.google.android.gms.ads.internal.zzp.zzka().zzvp());
            builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new DialogInterface.OnClickListener(this, iZza, iZza2, iZza3) { // from class: com.google.android.gms.internal.ads.zzaup
                private final zzaun zzdst;
                private final int zzdsy;
                private final int zzdsz;
                private final int zzdta;

                {
                    this.zzdst = this;
                    this.zzdsy = iZza;
                    this.zzdsz = iZza2;
                    this.zzdta = iZza3;
                }

                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.zzdst.zza(this.zzdsy, this.zzdsz, this.zzdta, dialogInterface, i);
                }
            });
            builder.create().show();
        } catch (WindowManager.BadTokenException e) {
            zzatm.zza("", e);
        }
    }

    public final void setAdUnitId(String str) {
        this.zzccm = str;
    }

    public final void zzr(String str) {
        this.zzbnh = str;
    }

    public final void zzej(String str) {
        this.zzdhs = str;
    }

    public final void zzek(String str) {
        this.zzdky = str;
    }

    private static int zza(List<String> list, String str, boolean z) {
        list.add(str);
        return list.size() - 1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("{Dialog: ");
        sb.append(this.zzdhs);
        sb.append(",DebugSignal: ");
        sb.append(this.zzdky);
        sb.append(",AFMA Version: ");
        sb.append(this.zzbnh);
        sb.append(",Ad Unit ID: ");
        sb.append(this.zzccm);
        sb.append("}");
        return sb.toString();
    }

    final /* synthetic */ void zzvs() {
        com.google.android.gms.ads.internal.zzp.zzki().zza(this.zzlk, this.zzccm, this.zzbnh, this.zzdky);
    }

    final /* synthetic */ void zzvt() {
        com.google.android.gms.ads.internal.zzp.zzki().zze(this.zzlk, this.zzccm, this.zzbnh);
    }

    final /* synthetic */ void zza(String str, DialogInterface dialogInterface, int i) {
        com.google.android.gms.ads.internal.zzp.zzjy();
        zzatv.zza(this.zzlk, Intent.createChooser(new Intent("android.intent.action.SEND").setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT).putExtra("android.intent.extra.TEXT", str), "Share via"));
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0071  */
    final /* synthetic */ void zza(int i, int i2, int i3, DialogInterface dialogInterface, int i4) {
        final String strTrim;
        if (i4 != i) {
            if (i4 == i2) {
                zzatm.zzdv("Debug mode [Creative Preview] selected.");
                zzawx.zzdvx.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzauq
                    private final zzaun zzdst;

                    {
                        this.zzdst = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzdst.zzvt();
                    }
                });
                return;
            } else {
                if (i4 == i3) {
                    zzatm.zzdv("Debug mode [Troubleshooting] selected.");
                    zzawx.zzdvx.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzaut
                        private final zzaun zzdst;

                        {
                            this.zzdst = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zzdst.zzvs();
                        }
                    });
                    return;
                }
                return;
            }
        }
        if (!(this.zzlk instanceof Activity)) {
            zzatm.zzet("Can not create dialog without Activity Context");
            return;
        }
        String str = this.zzdhs;
        if (TextUtils.isEmpty(str)) {
            strTrim = "No debug information";
        } else {
            Uri uriBuild = new Uri.Builder().encodedQuery(str.replaceAll("\\+", "%20")).build();
            StringBuilder sb = new StringBuilder();
            com.google.android.gms.ads.internal.zzp.zzjy();
            Map<String, String> mapZzi = zzatv.zzi(uriBuild);
            for (String str2 : mapZzi.keySet()) {
                sb.append(str2);
                sb.append(" = ");
                sb.append(mapZzi.get(str2));
                sb.append("\n\n");
            }
            strTrim = sb.toString().trim();
            if (TextUtils.isEmpty(strTrim)) {
                strTrim = "No debug information";
            }
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.zzlk);
        builder.setMessage(strTrim);
        builder.setTitle("Ad Information");
        builder.setPositiveButton("Share", new DialogInterface.OnClickListener(this, strTrim) { // from class: com.google.android.gms.internal.ads.zzauo
            private final String zzcyl;
            private final zzaun zzdst;

            {
                this.zzdst = this;
                this.zzcyl = strTrim;
            }

            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface2, int i5) {
                this.zzdst.zza(this.zzcyl, dialogInterface2, i5);
            }
        });
        builder.setNegativeButton("Close", zzaur.zzdtb);
        builder.create().show();
    }

    final /* synthetic */ void zzvu() {
        this.state = 4;
        showDialog();
    }
}
