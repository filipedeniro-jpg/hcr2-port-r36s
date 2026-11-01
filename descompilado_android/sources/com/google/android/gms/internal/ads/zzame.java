package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.CalendarContract;
import android.text.TextUtils;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.drive.DriveFile;
import com.my.target.ab;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzame extends zzamp {
    private final Map<String, String> zzcue;
    private String zzdex;
    private long zzdey;
    private long zzdez;
    private String zzdfa;
    private String zzdfb;
    private final Context zzlk;

    public zzame(zzbbc zzbbcVar, Map<String, String> map) {
        super(zzbbcVar, ab.bG);
        this.zzcue = map;
        this.zzlk = zzbbcVar.zzxl();
        this.zzdex = zzdl("description");
        this.zzdfa = zzdl("summary");
        this.zzdey = zzdm("start_ticks");
        this.zzdez = zzdm("end_ticks");
        this.zzdfb = zzdl("location");
    }

    private final String zzdl(String str) {
        return TextUtils.isEmpty(this.zzcue.get(str)) ? "" : this.zzcue.get(str);
    }

    private final long zzdm(String str) {
        String str2 = this.zzcue.get(str);
        if (str2 == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str2);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public final void execute() {
        if (this.zzlk == null) {
            zzdn("Activity context is not available.");
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzjy();
        if (!zzatv.zzar(this.zzlk).zzpl()) {
            zzdn("This feature is not available on the device.");
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzjy();
        AlertDialog.Builder builderZzaq = zzatv.zzaq(this.zzlk);
        Resources resources = com.google.android.gms.ads.internal.zzp.zzkc().getResources();
        builderZzaq.setTitle(resources != null ? resources.getString(R.string.s5) : "Create calendar event");
        builderZzaq.setMessage(resources != null ? resources.getString(R.string.s6) : "Allow Ad to create a calendar event?");
        builderZzaq.setPositiveButton(resources != null ? resources.getString(R.string.s3) : "Accept", new zzamd(this));
        builderZzaq.setNegativeButton(resources != null ? resources.getString(R.string.s4) : "Decline", new zzamg(this));
        builderZzaq.create().show();
    }

    @TargetApi(14)
    final Intent createIntent() {
        Intent data = new Intent("android.intent.action.EDIT").setData(CalendarContract.Events.CONTENT_URI);
        data.putExtra("title", this.zzdex);
        data.putExtra("eventLocation", this.zzdfb);
        data.putExtra("description", this.zzdfa);
        long j = this.zzdey;
        if (j > -1) {
            data.putExtra("beginTime", j);
        }
        long j2 = this.zzdez;
        if (j2 > -1) {
            data.putExtra("endTime", j2);
        }
        data.setFlags(DriveFile.MODE_READ_ONLY);
        return data;
    }
}
