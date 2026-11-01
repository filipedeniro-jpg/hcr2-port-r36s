package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzazm implements zzgd, zzlr, zznq<zznc>, zzou {

    @VisibleForTesting
    private static int zzebp;

    @VisibleForTesting
    private static int zzebq;
    private int bytesTransferred;
    private final zzayq zzeaw;
    private final zzgv zzebs;
    private zzge zzebv;
    private ByteBuffer zzebw;
    private boolean zzebx;
    private zzazu zzeby;
    private final Context zzlk;
    private Set<WeakReference<zzazj>> zzebz = new HashSet();
    private final zzazn zzebr = new zzazn();
    private final zzgv zzebt = new zzhz(zzkp.zzazb);
    private final zzmq zzebu = new zzmp();

    public zzazm(Context context, zzayq zzayqVar) {
        this.zzlk = context;
        this.zzeaw = zzayqVar;
        this.zzebs = new zzoo(this.zzlk, zzkp.zzazb, 0L, zzatv.zzdsk, this, -1);
        if (zzatm.zzuq()) {
            String strValueOf = String.valueOf(this);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 28);
            sb.append("ExoPlayerAdapter initialize ");
            sb.append(strValueOf);
            zzatm.zzdy(sb.toString());
        }
        zzebp++;
        this.zzebv = zzgi.zza(new zzgv[]{this.zzebt, this.zzebs}, this.zzebu, this.zzebr);
        this.zzebv.zza(this);
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zza(Surface surface) {
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zza(zzgw zzgwVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zza(zzha zzhaVar, Object obj) {
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zza(zzmi zzmiVar, zzmx zzmxVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zzd(String str, long j, long j2) {
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zzdr() {
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zze(int i, long j) {
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zze(zzij zzijVar) {
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final /* bridge */ /* synthetic */ void zze(zznc zzncVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zze(boolean z) {
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zzf(zzij zzijVar) {
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zzk(zzgq zzgqVar) {
    }

    public final zzge zzyk() {
        return this.zzebv;
    }

    public static int zzyl() {
        return zzebp;
    }

    public static int zzym() {
        return zzebq;
    }

    public final void zza(zzazu zzazuVar) {
        this.zzeby = zzazuVar;
    }

    public final zzazn zzyn() {
        return this.zzebr;
    }

    public final void zza(Uri[] uriArr, String str) {
        zza(uriArr, str, ByteBuffer.allocate(0), false);
    }

    public final void zza(Uri[] uriArr, String str, ByteBuffer byteBuffer, boolean z) {
        zzls zzlxVar;
        if (this.zzebv == null) {
            return;
        }
        this.zzebw = byteBuffer;
        this.zzebx = z;
        if (uriArr.length == 1) {
            zzlxVar = zzb(uriArr[0], str);
        } else {
            zzls[] zzlsVarArr = new zzls[uriArr.length];
            for (int i = 0; i < uriArr.length; i++) {
                zzlsVarArr[i] = zzb(uriArr[i], str);
            }
            zzlxVar = new zzlx(zzlsVarArr);
        }
        this.zzebv.zza(zzlxVar);
        zzebq++;
    }

    public final void release() {
        zzge zzgeVar = this.zzebv;
        if (zzgeVar != null) {
            zzgeVar.zzb(this);
            this.zzebv.release();
            this.zzebv = null;
            zzebq--;
        }
    }

    public final long getBytesTransferred() {
        return this.bytesTransferred;
    }

    @Override // com.google.android.gms.internal.ads.zzlr
    public final void zzb(IOException iOException) {
        zzazu zzazuVar = this.zzeby;
        if (zzazuVar != null) {
            zzazuVar.zza("onLoadError", iOException);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzou
    public final void zza(int i, int i2, int i3, float f) {
        zzazu zzazuVar = this.zzeby;
        if (zzazuVar != null) {
            zzazuVar.zzm(i, i2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zza(boolean z, int i) {
        zzazu zzazuVar = this.zzeby;
        if (zzazuVar != null) {
            zzazuVar.zzcy(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgd
    public final void zza(zzgb zzgbVar) {
        zzazu zzazuVar = this.zzeby;
        if (zzazuVar != null) {
            zzazuVar.zza("onPlayerError", zzgbVar);
        }
    }

    public final void zzcx(int i) {
        Iterator<WeakReference<zzazj>> it = this.zzebz.iterator();
        while (it.hasNext()) {
            zzazj zzazjVar = it.next().get();
            if (zzazjVar != null) {
                zzazjVar.setReceiveBufferSize(i);
            }
        }
    }

    final void zza(Surface surface, boolean z) {
        if (this.zzebv == null) {
            return;
        }
        zzgf zzgfVar = new zzgf(this.zzebs, 1, surface);
        if (z) {
            this.zzebv.zzb(zzgfVar);
        } else {
            this.zzebv.zza(zzgfVar);
        }
    }

    final void zzb(float f, boolean z) {
        if (this.zzebv == null) {
            return;
        }
        zzgf zzgfVar = new zzgf(this.zzebt, 2, Float.valueOf(f));
        if (z) {
            this.zzebv.zzb(zzgfVar);
        } else {
            this.zzebv.zza(zzgfVar);
        }
    }

    final void zzap(boolean z) {
        if (this.zzebv == null) {
            return;
        }
        for (int i = 0; i < this.zzebv.zzdt(); i++) {
            this.zzebu.zzf(i, !z);
        }
    }

    @VisibleForTesting
    private final zzls zzb(Uri uri, final String str) {
        final zznf zznfVar;
        zznf zznfVar2;
        if (this.zzebx && this.zzebw.limit() > 0) {
            final byte[] bArr = new byte[this.zzebw.limit()];
            this.zzebw.get(bArr);
            zznfVar2 = new zznf(bArr) { // from class: com.google.android.gms.internal.ads.zzazp
                private final byte[] zzdvr;

                {
                    this.zzdvr = bArr;
                }

                @Override // com.google.android.gms.internal.ads.zznf
                public final zznc zzhy() {
                    return new zznd(this.zzdvr);
                }
            };
        } else {
            if (this.zzeaw.zzdze > 0) {
                zznfVar = new zznf(this, str) { // from class: com.google.android.gms.internal.ads.zzazo
                    private final String zzcyl;
                    private final zzazm zzecg;

                    {
                        this.zzecg = this;
                        this.zzcyl = str;
                    }

                    @Override // com.google.android.gms.internal.ads.zznf
                    public final zznc zzhy() {
                        return this.zzecg.zzfc(this.zzcyl);
                    }
                };
            } else {
                zznfVar = new zznf(this, str) { // from class: com.google.android.gms.internal.ads.zzazr
                    private final String zzcyl;
                    private final zzazm zzecg;

                    {
                        this.zzecg = this;
                        this.zzcyl = str;
                    }

                    @Override // com.google.android.gms.internal.ads.zznf
                    public final zznc zzhy() {
                        return this.zzecg.zzfb(this.zzcyl);
                    }
                };
            }
            final zznf zznfVar3 = this.zzeaw.zzdzf ? new zznf(this, zznfVar) { // from class: com.google.android.gms.internal.ads.zzazq
                private final zzazm zzecg;
                private final zznf zzech;

                {
                    this.zzecg = this;
                    this.zzech = zznfVar;
                }

                @Override // com.google.android.gms.internal.ads.zznf
                public final zznc zzhy() {
                    return this.zzecg.zza(this.zzech);
                }
            } : zznfVar;
            if (this.zzebw.limit() > 0) {
                final byte[] bArr2 = new byte[this.zzebw.limit()];
                this.zzebw.get(bArr2);
                zznfVar3 = new zznf(zznfVar3, bArr2) { // from class: com.google.android.gms.internal.ads.zzazt
                    private final byte[] zzdlk;
                    private final zznf zzecj;

                    {
                        this.zzecj = zznfVar3;
                        this.zzdlk = bArr2;
                    }

                    @Override // com.google.android.gms.internal.ads.zznf
                    public final zznc zzhy() {
                        zznf zznfVar4 = this.zzecj;
                        byte[] bArr3 = this.zzdlk;
                        return new zzazx(new zznd(bArr3), bArr3.length, zznfVar4.zzhy());
                    }
                };
            }
            zznfVar2 = zznfVar3;
        }
        return new zzlo(uri, zznfVar2, zzazs.zzeci, this.zzeaw.zzdzg, zzatv.zzdsk, this, null, this.zzeaw.zzdzc);
    }

    public final void finalize() throws Throwable {
        zzebp--;
        if (zzatm.zzuq()) {
            String strValueOf = String.valueOf(this);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 26);
            sb.append("ExoPlayerAdapter finalize ");
            sb.append(strValueOf);
            zzatm.zzdy(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final /* synthetic */ void zzc(zznc zzncVar, int i) {
        this.bytesTransferred += i;
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final /* synthetic */ void zza(zznc zzncVar, zznh zznhVar) {
        this.bytesTransferred = 0;
    }

    final /* synthetic */ zznc zza(zznf zznfVar) {
        return new zzazl(this.zzlk, zznfVar.zzhy(), this, new zzazk(this) { // from class: com.google.android.gms.internal.ads.zzazv
            private final zzazm zzecg;

            {
                this.zzecg = this;
            }

            @Override // com.google.android.gms.internal.ads.zzazk
            public final void zzb(boolean z, long j) {
                this.zzecg.zzd(z, j);
            }
        });
    }

    final /* synthetic */ void zzd(boolean z, long j) {
        zzazu zzazuVar = this.zzeby;
        if (zzazuVar != null) {
            zzazuVar.zzb(z, j);
        }
    }

    final /* synthetic */ zznc zzfb(String str) {
        return new zznj(str, null, this.zzeaw.zzdzf ? null : this, this.zzeaw.zzdyz, this.zzeaw.zzdzb, true, null);
    }

    final /* synthetic */ zznc zzfc(String str) {
        zzazj zzazjVar = new zzazj(str, this.zzeaw.zzdzf ? null : this, this.zzeaw.zzdyz, this.zzeaw.zzdzb, this.zzeaw.zzdze);
        this.zzebz.add(new WeakReference<>(zzazjVar));
        return zzazjVar;
    }
}
