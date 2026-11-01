package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzpv {
    private int score;
    private final int zzboi;
    private final int zzboj;
    private final int zzbok;
    private final boolean zzbol;
    private final zzqg zzbom;
    private final zzqr zzbon;
    private final Object lock = new Object();
    private ArrayList<String> zzboo = new ArrayList<>();
    private ArrayList<String> zzbop = new ArrayList<>();
    private ArrayList<zzqe> zzboq = new ArrayList<>();
    private int zzbor = 0;
    private int zzbos = 0;
    private int zzbot = 0;
    private String zzbou = "";
    private String zzbov = "";
    private String zzbow = "";

    public zzpv(int i, int i2, int i3, int i4, int i5, int i6, int i7, boolean z) {
        this.zzboi = i;
        this.zzboj = i2;
        this.zzbok = i3;
        this.zzbol = z;
        this.zzbom = new zzqg(i4);
        this.zzbon = new zzqr(i5, i6, i7);
    }

    public final boolean zzlh() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzbot == 0;
        }
        return z;
    }

    public final String zzli() {
        return this.zzbou;
    }

    public final String zzlj() {
        return this.zzbov;
    }

    public final String zzlk() {
        return this.zzbow;
    }

    public final void zzll() {
        synchronized (this.lock) {
            this.score -= 100;
        }
    }

    public final void zzlm() {
        synchronized (this.lock) {
            this.zzbot--;
        }
    }

    public final void zzln() {
        synchronized (this.lock) {
            this.zzbot++;
        }
    }

    public final void zza(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
        synchronized (this.lock) {
            if (this.zzbot < 0) {
                zzatm.zzdv("ActivityContent: negative number of WebViews.");
            }
            zzlp();
        }
    }

    public final void zzb(String str, boolean z, float f, float f2, float f3, float f4) {
        zzc(str, z, f, f2, f3, f4);
    }

    private final void zzc(@Nullable String str, boolean z, float f, float f2, float f3, float f4) {
        if (str == null || str.length() < this.zzbok) {
            return;
        }
        synchronized (this.lock) {
            this.zzboo.add(str);
            this.zzbor += str.length();
            if (z) {
                this.zzbop.add(str);
                this.zzboq.add(new zzqe(f, f2, f3, f4, this.zzbop.size() - 1));
            }
        }
    }

    public final void zzlo() {
        synchronized (this.lock) {
            int iZzg = zzg(this.zzbor, this.zzbos);
            if (iZzg > this.score) {
                this.score = iZzg;
            }
        }
    }

    public final void zzlp() {
        synchronized (this.lock) {
            int iZzg = zzg(this.zzbor, this.zzbos);
            if (iZzg > this.score) {
                this.score = iZzg;
                if (!com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzus()) {
                    this.zzbou = this.zzbom.zza(this.zzboo);
                    this.zzbov = this.zzbom.zza(this.zzbop);
                }
                if (!com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuu()) {
                    this.zzbow = this.zzbon.zza(this.zzbop, this.zzboq);
                }
            }
        }
    }

    @VisibleForTesting
    private final int zzg(int i, int i2) {
        if (this.zzbol) {
            return this.zzboj;
        }
        return (i * this.zzboi) + (i2 * this.zzboj);
    }

    public final int getScore() {
        return this.score;
    }

    public final void zzbo(int i) {
        this.zzbos = i;
    }

    private static String zza(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i2 < size) {
            String str = arrayList2.get(i2);
            i2++;
            sb.append(str);
            sb.append(' ');
            if (sb.length() > 100) {
                break;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        String string = sb.toString();
        return string.length() < 100 ? string : string.substring(0, 100);
    }

    public final String toString() {
        int i = this.zzbos;
        int i2 = this.score;
        int i3 = this.zzbor;
        String strZza = zza(this.zzboo, 100);
        String strZza2 = zza(this.zzbop, 100);
        String str = this.zzbou;
        String str2 = this.zzbov;
        String str3 = this.zzbow;
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 165 + String.valueOf(strZza2).length() + String.valueOf(str).length() + String.valueOf(str2).length() + String.valueOf(str3).length());
        sb.append("ActivityContent fetchId: ");
        sb.append(i);
        sb.append(" score:");
        sb.append(i2);
        sb.append(" total_length:");
        sb.append(i3);
        sb.append("\n text: ");
        sb.append(strZza);
        sb.append("\n viewableText");
        sb.append(strZza2);
        sb.append("\n signture: ");
        sb.append(str);
        sb.append("\n viewableSignture: ");
        sb.append(str2);
        sb.append("\n viewableSignatureForVertical: ");
        sb.append(str3);
        return sb.toString();
    }

    @VisibleForTesting
    final int zzlq() {
        return this.zzbor;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzpv)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        String str = ((zzpv) obj).zzbou;
        return str != null && str.equals(this.zzbou);
    }

    public final int hashCode() {
        return this.zzbou.hashCode();
    }
}
