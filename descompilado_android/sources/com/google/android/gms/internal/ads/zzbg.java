package com.google.android.gms.internal.ads;

import androidx.core.view.MotionEventCompat;
import com.ironsource.sdk.constants.Constants;
import java.nio.ByteBuffer;
import java.util.Date;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbg extends zzdut {
    private Date zzcr;
    private Date zzcs;
    private long zzct;
    private long zzcu;
    private double zzcv;
    private float zzcw;
    private zzdvd zzcx;
    private long zzcy;
    private int zzcz;
    private int zzda;
    private int zzdb;
    private int zzdc;
    private int zzdd;
    private int zzde;

    public zzbg() {
        super("mvhd");
        this.zzcv = 1.0d;
        this.zzcw = 1.0f;
        this.zzcx = zzdvd.zzhwt;
    }

    public final long zzq() {
        return this.zzct;
    }

    public final long getDuration() {
        return this.zzcu;
    }

    @Override // com.google.android.gms.internal.ads.zzdur
    public final void zzg(ByteBuffer byteBuffer) {
        zzo(byteBuffer);
        if (getVersion() == 1) {
            this.zzcr = zzduw.zzfp(zzbc.zzc(byteBuffer));
            this.zzcs = zzduw.zzfp(zzbc.zzc(byteBuffer));
            this.zzct = zzbc.zza(byteBuffer);
            this.zzcu = zzbc.zzc(byteBuffer);
        } else {
            this.zzcr = zzduw.zzfp(zzbc.zza(byteBuffer));
            this.zzcs = zzduw.zzfp(zzbc.zza(byteBuffer));
            this.zzct = zzbc.zza(byteBuffer);
            this.zzcu = zzbc.zza(byteBuffer);
        }
        this.zzcv = zzbc.zzd(byteBuffer);
        byte[] bArr = new byte[2];
        byteBuffer.get(bArr);
        this.zzcw = ((short) ((bArr[1] & 255) | ((short) (0 | ((bArr[0] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))))) / 256.0f;
        zzbc.zzb(byteBuffer);
        zzbc.zza(byteBuffer);
        zzbc.zza(byteBuffer);
        this.zzcx = zzdvd.zzp(byteBuffer);
        this.zzcz = byteBuffer.getInt();
        this.zzda = byteBuffer.getInt();
        this.zzdb = byteBuffer.getInt();
        this.zzdc = byteBuffer.getInt();
        this.zzdd = byteBuffer.getInt();
        this.zzde = byteBuffer.getInt();
        this.zzcy = zzbc.zza(byteBuffer);
    }

    public final String toString() {
        return "MovieHeaderBox[creationTime=" + this.zzcr + ";modificationTime=" + this.zzcs + ";timescale=" + this.zzct + ";duration=" + this.zzcu + ";rate=" + this.zzcv + ";volume=" + this.zzcw + ";matrix=" + this.zzcx + ";nextTrackId=" + this.zzcy + Constants.RequestParameters.RIGHT_BRACKETS;
    }
}
