package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import com.google.android.exoplayer2.C;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzlo implements zzls, zzlv {
    private final Uri uri;
    private final Handler zzacp;
    private zzha zzada;
    private final int zzazp;
    private final zzlr zzazq;
    private zzlv zzazr;
    private final zznf zzbaz;
    private final zziz zzbba;
    private final int zzbbb;
    private boolean zzbbc;
    private final String zzazt = null;
    private final zzhc zzact = new zzhc();

    public zzlo(Uri uri, zznf zznfVar, zziz zzizVar, int i, Handler handler, zzlr zzlrVar, String str, int i2) {
        this.uri = uri;
        this.zzbaz = zznfVar;
        this.zzbba = zzizVar;
        this.zzazp = i;
        this.zzacp = handler;
        this.zzazq = zzlrVar;
        this.zzbbb = i2;
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zzhi() throws IOException {
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zza(zzge zzgeVar, boolean z, zzlv zzlvVar) {
        this.zzazr = zzlvVar;
        this.zzada = new zzmg(C.TIME_UNSET, false);
        zzlvVar.zzb(this.zzada, null);
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final zzlq zza(int i, zzna zznaVar) {
        zznt.checkArgument(i == 0);
        return new zzlg(this.uri, this.zzbaz.zzhy(), this.zzbba.zzgc(), this.zzazp, this.zzacp, this.zzazq, this, zznaVar, null, this.zzbbb);
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zzb(zzlq zzlqVar) {
        ((zzlg) zzlqVar).release();
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zzhj() {
        this.zzazr = null;
    }

    @Override // com.google.android.gms.internal.ads.zzlv
    public final void zzb(zzha zzhaVar, Object obj) {
        boolean z = zzhaVar.zza(0, this.zzact, false).zzagm != C.TIME_UNSET;
        if (!this.zzbbc || z) {
            this.zzada = zzhaVar;
            this.zzbbc = z;
            this.zzazr.zzb(this.zzada, null);
        }
    }
}
