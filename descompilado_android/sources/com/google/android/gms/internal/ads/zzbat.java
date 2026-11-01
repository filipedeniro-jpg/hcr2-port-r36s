package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.google.android.gms.common.util.Clock;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbat extends zzbah implements zznq<zznc> {
    private String url;
    private ByteBuffer zzakh;
    private final zzayq zzeaw;
    private boolean zzedh;
    private final zzbaq zzedn;
    private final zzazz zzedo;
    private boolean zzedp;
    private final Object zzedq;
    private boolean zzedr;

    public zzbat(zzayt zzaytVar, zzayq zzayqVar) {
        super(zzaytVar);
        this.zzeaw = zzayqVar;
        this.zzedn = new zzbaq();
        this.zzedo = new zzazz();
        this.zzedq = new Object();
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final /* bridge */ /* synthetic */ void zzc(zznc zzncVar, int i) {
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final /* bridge */ /* synthetic */ void zze(zznc zzncVar) {
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean zzyq() {
        return this.zzedr;
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    protected final String zzfe(String str) {
        String strValueOf = String.valueOf(super.zzfe(str));
        return strValueOf.length() != 0 ? "cache:".concat(strValueOf) : new String("cache:");
    }

    private final void zzwz() {
        int iZzyo = (int) this.zzedn.zzyo();
        int iZzl = (int) this.zzedo.zzl(this.zzakh);
        int iPosition = this.zzakh.position();
        int iRound = Math.round(iZzl * (iPosition / iZzyo));
        boolean z = iRound > 0;
        int iZzyl = zzazm.zzyl();
        int iZzym = zzazm.zzym();
        String str = this.url;
        zza(str, zzfe(str), iPosition, iZzyo, iRound, iZzl, z, iZzyl, iZzym);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:65:? -> B:53:0x013a). Please report as a decompilation issue!!! */
    @Override // com.google.android.gms.internal.ads.zzbah
    public final boolean zzfd(String str) throws Throwable {
        this.url = str;
        String strZzfe = zzfe(str);
        String str2 = "error";
        int i = 0;
        try {
            zznc zznjVar = new zznj(this.zzdur, null, this, this.zzeaw.zzdyz, this.zzeaw.zzdzb, true, null);
            if (this.zzeaw.zzdzf) {
                try {
                    zznjVar = new zzazl(this.mContext, zznjVar, null, null);
                } catch (Exception e) {
                    e = e;
                }
            }
            zznjVar.zza(new zznh(Uri.parse(str)));
            zzayt zzaytVar = this.zzecy.get();
            if (zzaytVar != null) {
                zzaytVar.zza(strZzfe, this);
            }
            Clock clockZzkf = com.google.android.gms.ads.internal.zzp.zzkf();
            long jCurrentTimeMillis = clockZzkf.currentTimeMillis();
            long jLongValue = ((Long) zzuo.zzoj().zzd(zzyt.zzche)).longValue();
            long jLongValue2 = ((Long) zzuo.zzoj().zzd(zzyt.zzchd)).longValue();
            this.zzakh = ByteBuffer.allocate(this.zzeaw.zzdyy);
            int i2 = 8192;
            byte[] bArr = new byte[8192];
            long j = jCurrentTimeMillis;
            while (true) {
                int i3 = zznjVar.read(bArr, i, Math.min(this.zzakh.remaining(), i2));
                if (i3 == -1) {
                    this.zzedr = true;
                    zzb(str, strZzfe, (int) this.zzedo.zzl(this.zzakh));
                    return true;
                }
                synchronized (this.zzedq) {
                    try {
                        if (!this.zzedh) {
                            try {
                                this.zzakh.put(bArr, 0, i3);
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                        try {
                            if (this.zzakh.remaining() <= 0) {
                                zzwz();
                                return true;
                            }
                            if (this.zzedh) {
                                int iLimit = this.zzakh.limit();
                                StringBuilder sb = new StringBuilder(35);
                                sb.append("Precache abort at ");
                                sb.append(iLimit);
                                sb.append(" bytes");
                                throw new IOException(sb.toString());
                            }
                            long jCurrentTimeMillis2 = clockZzkf.currentTimeMillis();
                            if (jCurrentTimeMillis2 - j >= jLongValue) {
                                zzwz();
                                j = jCurrentTimeMillis2;
                            }
                            if (jCurrentTimeMillis2 - jCurrentTimeMillis > 1000 * jLongValue2) {
                                StringBuilder sb2 = new StringBuilder(49);
                                sb2.append("Timeout exceeded. Limit: ");
                                sb2.append(jLongValue2);
                                sb2.append(" sec");
                                throw new IOException(sb2.toString());
                            }
                            str2 = str2;
                            i2 = 8192;
                            i = 0;
                        } catch (Exception e2) {
                            e = e2;
                            str2 = str2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        throw th;
                    }
                }
                String canonicalName = e.getClass().getCanonicalName();
                String message = e.getMessage();
                StringBuilder sb3 = new StringBuilder(String.valueOf(canonicalName).length() + 1 + String.valueOf(message).length());
                sb3.append(canonicalName);
                sb3.append(":");
                sb3.append(message);
                String string = sb3.toString();
                StringBuilder sb4 = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(string).length());
                sb4.append("Failed to preload url ");
                sb4.append(str);
                sb4.append(" Exception: ");
                sb4.append(string);
                zzatm.zzeu(sb4.toString());
                zza(str, strZzfe, str2, string);
                return false;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void abort() {
        this.zzedh = true;
    }

    public final ByteBuffer getByteBuffer() {
        synchronized (this.zzedq) {
            if (this.zzakh != null && !this.zzedp) {
                this.zzakh.flip();
                this.zzedp = true;
            }
            this.zzedh = true;
        }
        return this.zzakh;
    }

    @Override // com.google.android.gms.internal.ads.zznq
    public final /* synthetic */ void zza(zznc zzncVar, zznh zznhVar) {
        zznc zzncVar2 = zzncVar;
        if (zzncVar2 instanceof zznj) {
            this.zzedn.zza((zznj) zzncVar2);
        }
    }
}
