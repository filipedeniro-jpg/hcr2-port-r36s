package com.google.android.gms.internal.ads;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.exoplayer2.upstream.cache.CacheDataSink;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.sdk.constants.Constants;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.LinkedList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzhq {
    private static boolean zzaho = false;
    private static boolean zzahp = false;
    private int streamType;
    private zzgw zzade;
    private int zzafm;
    private final zzhx zzahr;
    private final zzig zzahs;
    private final zzhg[] zzaht;
    private final zzhw zzahu;
    private final long[] zzahw;
    private final zzhs zzahx;
    private final LinkedList<zzhv> zzahy;
    private AudioTrack zzahz;
    private int zzaia;
    private int zzaib;
    private int zzaic;
    private boolean zzaid;
    private int zzaie;
    private long zzaif;
    private zzgw zzaig;
    private long zzaih;
    private long zzaii;
    private ByteBuffer zzaij;
    private int zzaik;
    private int zzail;
    private int zzaim;
    private long zzain;
    private long zzaio;
    private boolean zzaip;
    private long zzaiq;
    private Method zzair;
    private int zzais;
    private long zzait;
    private long zzaiu;
    private int zzaiv;
    private long zzaiw;
    private long zzaix;
    private int zzaiy;
    private int zzaiz;
    private long zzaja;
    private long zzajb;
    private long zzajc;
    private zzhg[] zzajd;
    private ByteBuffer[] zzaje;
    private ByteBuffer zzajf;
    private ByteBuffer zzajg;
    private byte[] zzajh;
    private int zzaji;
    private int zzajj;
    private boolean zzajk;
    private boolean zzajl;
    private int zzajm;
    private boolean zzajn;
    private boolean zzajo;
    private long zzajp;
    private float zzcw;
    private final zzhd zzahq = null;
    private final ConditionVariable zzahv = new ConditionVariable(true);

    public zzhq(zzhd zzhdVar, zzhg[] zzhgVarArr, zzhw zzhwVar) {
        zzhp zzhpVar = null;
        this.zzahu = zzhwVar;
        if (zzoh.SDK_INT >= 18) {
            try {
                this.zzair = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        if (zzoh.SDK_INT >= 19) {
            this.zzahx = new zzhr();
        } else {
            this.zzahx = new zzhs(zzhpVar);
        }
        this.zzahr = new zzhx();
        this.zzahs = new zzig();
        this.zzaht = new zzhg[zzhgVarArr.length + 3];
        this.zzaht[0] = new zzie();
        zzhg[] zzhgVarArr2 = this.zzaht;
        zzhgVarArr2[1] = this.zzahr;
        System.arraycopy(zzhgVarArr, 0, zzhgVarArr2, 2, zzhgVarArr.length);
        this.zzaht[zzhgVarArr.length + 2] = this.zzahs;
        this.zzahw = new long[10];
        this.zzcw = 1.0f;
        this.zzaiz = 0;
        this.streamType = 3;
        this.zzajm = 0;
        this.zzade = zzgw.zzafx;
        this.zzajj = -1;
        this.zzajd = new zzhg[0];
        this.zzaje = new ByteBuffer[0];
        this.zzahy = new LinkedList<>();
    }

    public final boolean zzav(String str) {
        zzhd zzhdVar = this.zzahq;
        return zzhdVar != null && zzhdVar.zzp(zzaw(str));
    }

    public final long zzi(boolean z) {
        long jZzfj;
        long j;
        long jZza;
        long j2;
        if (!(isInitialized() && this.zzaiz != 0)) {
            return Long.MIN_VALUE;
        }
        if (this.zzahz.getPlayState() == 3) {
            long jZzfj2 = this.zzahx.zzfj();
            if (jZzfj2 != 0) {
                long jNanoTime = System.nanoTime() / 1000;
                if (jNanoTime - this.zzaio >= 30000) {
                    long[] jArr = this.zzahw;
                    int i = this.zzail;
                    jArr[i] = jZzfj2 - jNanoTime;
                    this.zzail = (i + 1) % 10;
                    int i2 = this.zzaim;
                    if (i2 < 10) {
                        this.zzaim = i2 + 1;
                    }
                    this.zzaio = jNanoTime;
                    this.zzain = 0L;
                    int i3 = 0;
                    while (true) {
                        int i4 = this.zzaim;
                        if (i3 >= i4) {
                            break;
                        }
                        this.zzain += this.zzahw[i3] / ((long) i4);
                        i3++;
                    }
                }
                if (!zzfe() && jNanoTime - this.zzaiq >= 500000) {
                    this.zzaip = this.zzahx.zzff();
                    if (this.zzaip) {
                        long jZzfg = this.zzahx.zzfg() / 1000;
                        long jZzfh = this.zzahx.zzfh();
                        if (jZzfg < this.zzajb) {
                            this.zzaip = false;
                        } else if (Math.abs(jZzfg - jNanoTime) > 5000000) {
                            StringBuilder sb = new StringBuilder(136);
                            sb.append("Spurious audio timestamp (system clock mismatch): ");
                            sb.append(jZzfh);
                            sb.append(", ");
                            sb.append(jZzfg);
                            sb.append(", ");
                            sb.append(jNanoTime);
                            sb.append(", ");
                            sb.append(jZzfj2);
                            Log.w("AudioTrack", sb.toString());
                            this.zzaip = false;
                        } else if (Math.abs(zzdq(jZzfh) - jZzfj2) > 5000000) {
                            StringBuilder sb2 = new StringBuilder(TsExtractor.TS_STREAM_TYPE_DTS);
                            sb2.append("Spurious audio timestamp (frame position mismatch): ");
                            sb2.append(jZzfh);
                            sb2.append(", ");
                            sb2.append(jZzfg);
                            sb2.append(", ");
                            sb2.append(jNanoTime);
                            sb2.append(", ");
                            sb2.append(jZzfj2);
                            Log.w("AudioTrack", sb2.toString());
                            this.zzaip = false;
                        }
                    }
                    Method method = this.zzair;
                    if (method != null && !this.zzaid) {
                        try {
                            this.zzajc = (((long) ((Integer) method.invoke(this.zzahz, null)).intValue()) * 1000) - this.zzaif;
                            this.zzajc = Math.max(this.zzajc, 0L);
                            if (this.zzajc > 5000000) {
                                long j3 = this.zzajc;
                                StringBuilder sb3 = new StringBuilder(61);
                                sb3.append("Ignoring impossibly large audio latency: ");
                                sb3.append(j3);
                                Log.w("AudioTrack", sb3.toString());
                                this.zzajc = 0L;
                            }
                        } catch (Exception unused) {
                            this.zzair = null;
                        }
                    }
                    this.zzaiq = jNanoTime;
                }
            }
        }
        long jNanoTime2 = System.nanoTime() / 1000;
        if (this.zzaip) {
            jZzfj = zzdq(this.zzahx.zzfh() + zzdr(jNanoTime2 - (this.zzahx.zzfg() / 1000)));
        } else {
            if (this.zzaim == 0) {
                jZzfj = this.zzahx.zzfj();
            } else {
                jZzfj = jNanoTime2 + this.zzain;
            }
            if (!z) {
                jZzfj -= this.zzajc;
            }
        }
        long j4 = this.zzaja;
        while (!this.zzahy.isEmpty() && jZzfj >= this.zzahy.getFirst().zzaeg) {
            zzhv zzhvVarRemove = this.zzahy.remove();
            this.zzade = zzhvVarRemove.zzade;
            this.zzaii = zzhvVarRemove.zzaeg;
            this.zzaih = zzhvVarRemove.zzakc - this.zzaja;
        }
        if (this.zzade.zzafy == 1.0f) {
            j2 = (jZzfj + this.zzaih) - this.zzaii;
        } else {
            if (this.zzahy.isEmpty() && this.zzahs.zzfs() >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                j = this.zzaih;
                jZza = zzoh.zza(jZzfj - this.zzaii, this.zzahs.zzfr(), this.zzahs.zzfs());
            } else {
                j = this.zzaih;
                double d = this.zzade.zzafy;
                double d2 = jZzfj - this.zzaii;
                Double.isNaN(d);
                Double.isNaN(d2);
                jZza = (long) (d * d2);
            }
            j2 = jZza + j;
        }
        return j4 + j2;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00ad  */
    public final void zza(String str, int i, int i2, int i3, int i4, int[] iArr) throws zzhu {
        boolean z;
        int i5;
        int iMax;
        boolean z2 = !MimeTypes.AUDIO_RAW.equals(str);
        int iZzaw = z2 ? zzaw(str) : i3;
        if (z2) {
            z = false;
        } else {
            this.zzais = zzoh.zzf(i3, i);
            this.zzahr.zzb(iArr);
            int iZzer = iZzaw;
            int iZzeq = i;
            boolean zZzb = false;
            for (zzhg zzhgVar : this.zzaht) {
                try {
                    zZzb |= zzhgVar.zzb(i2, iZzeq, iZzer);
                    if (zzhgVar.isActive()) {
                        iZzeq = zzhgVar.zzeq();
                        iZzer = zzhgVar.zzer();
                    }
                } catch (zzhf e) {
                    throw new zzhu(e);
                }
            }
            if (zZzb) {
                zzeu();
            }
            z = zZzb;
            i = iZzeq;
            iZzaw = iZzer;
        }
        int i6 = 252;
        switch (i) {
            case 1:
                i5 = 4;
                break;
            case 2:
                i5 = 12;
                break;
            case 3:
                i5 = 28;
                break;
            case 4:
                i5 = 204;
                break;
            case 5:
                i5 = 220;
                break;
            case 6:
                i5 = 252;
                break;
            case 7:
                i5 = 1276;
                break;
            case 8:
                i5 = zzgc.CHANNEL_OUT_7POINT1_SURROUND;
                break;
            default:
                StringBuilder sb = new StringBuilder(38);
                sb.append("Unsupported channel count: ");
                sb.append(i);
                throw new zzhu(sb.toString());
        }
        if (zzoh.SDK_INT > 23 || !"foster".equals(zzoh.DEVICE) || !"NVIDIA".equals(zzoh.MANUFACTURER)) {
            i6 = i5;
        } else if (i != 3 && i != 5) {
            if (i != 7) {
                i6 = i5;
            } else {
                i6 = zzgc.CHANNEL_OUT_7POINT1_SURROUND;
            }
        }
        if (zzoh.SDK_INT <= 25 && "fugu".equals(zzoh.DEVICE) && z2 && i == 1) {
            i6 = 12;
        }
        if (!z && isInitialized() && this.zzaib == iZzaw && this.zzafm == i2 && this.zzaia == i6) {
            return;
        }
        reset();
        this.zzaib = iZzaw;
        this.zzaid = z2;
        this.zzafm = i2;
        this.zzaia = i6;
        if (!z2) {
            iZzaw = 2;
        }
        this.zzaic = iZzaw;
        this.zzaiv = zzoh.zzf(2, i);
        if (z2) {
            int i7 = this.zzaic;
            iMax = (i7 == 5 || i7 == 6) ? CacheDataSink.DEFAULT_BUFFER_SIZE : 49152;
        } else {
            int minBufferSize = AudioTrack.getMinBufferSize(i2, i6, this.zzaic);
            zznt.checkState(minBufferSize != -2);
            int i8 = minBufferSize << 2;
            int iZzdr = this.zzaiv * ((int) zzdr(250000L));
            iMax = (int) Math.max(minBufferSize, zzdr(750000L) * ((long) this.zzaiv));
            if (i8 < iZzdr) {
                iMax = iZzdr;
            } else if (i8 <= iMax) {
                iMax = i8;
            }
        }
        this.zzaie = iMax;
        this.zzaif = z2 ? C.TIME_UNSET : zzdq(this.zzaie / this.zzaiv);
        zzb(this.zzade);
    }

    private final void zzeu() {
        ArrayList arrayList = new ArrayList();
        for (zzhg zzhgVar : this.zzaht) {
            if (zzhgVar.isActive()) {
                arrayList.add(zzhgVar);
            } else {
                zzhgVar.flush();
            }
        }
        int size = arrayList.size();
        this.zzajd = (zzhg[]) arrayList.toArray(new zzhg[size]);
        this.zzaje = new ByteBuffer[size];
        for (int i = 0; i < size; i++) {
            zzhg zzhgVar2 = this.zzajd[i];
            zzhgVar2.flush();
            this.zzaje[i] = zzhgVar2.zzet();
        }
    }

    public final void play() {
        this.zzajl = true;
        if (isInitialized()) {
            this.zzajb = System.nanoTime() / 1000;
            this.zzahz.play();
        }
    }

    public final void zzev() {
        if (this.zzaiz == 1) {
            this.zzaiz = 2;
        }
    }

    public final boolean zza(ByteBuffer byteBuffer, long j) throws zzht, zzhy {
        int i;
        int iZzj;
        ByteBuffer byteBuffer2 = this.zzajf;
        zznt.checkArgument(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (!isInitialized()) {
            this.zzahv.block();
            if (this.zzajn) {
                this.zzahz = new AudioTrack(new AudioAttributes.Builder().setUsage(1).setContentType(3).setFlags(16).build(), new AudioFormat.Builder().setChannelMask(this.zzaia).setEncoding(this.zzaic).setSampleRate(this.zzafm).build(), this.zzaie, 1, this.zzajm);
            } else {
                int i2 = this.zzajm;
                if (i2 == 0) {
                    this.zzahz = new AudioTrack(this.streamType, this.zzafm, this.zzaia, this.zzaic, this.zzaie, 1);
                } else {
                    this.zzahz = new AudioTrack(this.streamType, this.zzafm, this.zzaia, this.zzaic, this.zzaie, 1, i2);
                }
            }
            int state = this.zzahz.getState();
            if (state != 1) {
                try {
                    this.zzahz.release();
                } catch (Exception unused) {
                } finally {
                    this.zzahz = null;
                }
                throw new zzht(state, this.zzafm, this.zzaia, this.zzaie);
            }
            int audioSessionId = this.zzahz.getAudioSessionId();
            if (this.zzajm != audioSessionId) {
                this.zzajm = audioSessionId;
                this.zzahu.zzr(audioSessionId);
            }
            this.zzahx.zza(this.zzahz, zzfe());
            zzfb();
            this.zzajo = false;
            if (this.zzajl) {
                play();
            }
        }
        if (zzfe()) {
            if (this.zzahz.getPlayState() == 2) {
                this.zzajo = false;
                return false;
            }
            if (this.zzahz.getPlayState() == 1 && this.zzahx.zzfi() != 0) {
                return false;
            }
        }
        boolean z = this.zzajo;
        this.zzajo = zzey();
        if (z && !this.zzajo && this.zzahz.getPlayState() != 1) {
            this.zzahu.zzc(this.zzaie, zzgc.zzdi(this.zzaif), SystemClock.elapsedRealtime() - this.zzajp);
        }
        if (this.zzajf == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (this.zzaid && this.zzaiy == 0) {
                int i3 = this.zzaic;
                if (i3 == 7 || i3 == 8) {
                    iZzj = zzia.zzj(byteBuffer);
                } else if (i3 == 5) {
                    iZzj = zzhe.zzep();
                } else if (i3 == 6) {
                    iZzj = zzhe.zzh(byteBuffer);
                } else {
                    StringBuilder sb = new StringBuilder(38);
                    sb.append("Unexpected audio encoding: ");
                    sb.append(i3);
                    throw new IllegalStateException(sb.toString());
                }
                this.zzaiy = iZzj;
            }
            if (this.zzaig != null) {
                if (!zzex()) {
                    return false;
                }
                this.zzahy.add(new zzhv(this.zzaig, Math.max(0L, j), zzdq(zzfc()), null));
                this.zzaig = null;
                zzeu();
            }
            if (this.zzaiz == 0) {
                this.zzaja = Math.max(0L, j);
                this.zzaiz = 1;
            } else {
                long jZzdq = this.zzaja + zzdq(this.zzaid ? this.zzaiu : this.zzait / ((long) this.zzais));
                if (this.zzaiz != 1 || Math.abs(jZzdq - j) <= 200000) {
                    i = 2;
                } else {
                    StringBuilder sb2 = new StringBuilder(80);
                    sb2.append("Discontinuity detected [expected ");
                    sb2.append(jZzdq);
                    sb2.append(", got ");
                    sb2.append(j);
                    sb2.append(Constants.RequestParameters.RIGHT_BRACKETS);
                    Log.e("AudioTrack", sb2.toString());
                    i = 2;
                    this.zzaiz = 2;
                }
                if (this.zzaiz == i) {
                    this.zzaja += j - jZzdq;
                    this.zzaiz = 1;
                    this.zzahu.zzdr();
                }
            }
            if (this.zzaid) {
                this.zzaiu += (long) this.zzaiy;
            } else {
                this.zzait += (long) byteBuffer.remaining();
            }
            this.zzajf = byteBuffer;
        }
        if (this.zzaid) {
            zzb(this.zzajf, j);
        } else {
            zzdp(j);
        }
        if (this.zzajf.hasRemaining()) {
            return false;
        }
        this.zzajf = null;
        return true;
    }

    private final void zzdp(long j) throws zzhy {
        ByteBuffer byteBuffer;
        int length = this.zzajd.length;
        int i = length;
        while (i >= 0) {
            if (i > 0) {
                byteBuffer = this.zzaje[i - 1];
            } else {
                byteBuffer = this.zzajf;
                if (byteBuffer == null) {
                    byteBuffer = zzhg.zzagz;
                }
            }
            if (i == length) {
                zzb(byteBuffer, j);
            } else {
                zzhg zzhgVar = this.zzajd[i];
                zzhgVar.zzi(byteBuffer);
                ByteBuffer byteBufferZzet = zzhgVar.zzet();
                this.zzaje[i] = byteBufferZzet;
                if (byteBufferZzet.hasRemaining()) {
                    i++;
                }
            }
            if (byteBuffer.hasRemaining()) {
                return;
            } else {
                i--;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0076  */
    /* JADX WARN: Code duplicated, block: B:46:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:48:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:49:0x00e5  */
    private final boolean zzb(ByteBuffer byteBuffer, long j) throws zzhy {
        int iWrite;
        int iWrite2;
        if (!byteBuffer.hasRemaining()) {
            return true;
        }
        ByteBuffer byteBuffer2 = this.zzajg;
        if (byteBuffer2 != null) {
            zznt.checkArgument(byteBuffer2 == byteBuffer);
        } else {
            this.zzajg = byteBuffer;
            if (zzoh.SDK_INT < 21) {
                int iRemaining = byteBuffer.remaining();
                byte[] bArr = this.zzajh;
                if (bArr == null || bArr.length < iRemaining) {
                    this.zzajh = new byte[iRemaining];
                }
                int iPosition = byteBuffer.position();
                byteBuffer.get(this.zzajh, 0, iRemaining);
                byteBuffer.position(iPosition);
                this.zzaji = 0;
            }
        }
        int iRemaining2 = byteBuffer.remaining();
        if (zzoh.SDK_INT < 21) {
            int iZzfi = this.zzaie - ((int) (this.zzaiw - (this.zzahx.zzfi() * ((long) this.zzaiv))));
            if (iZzfi > 0) {
                iWrite = this.zzahz.write(this.zzajh, this.zzaji, Math.min(iRemaining2, iZzfi));
                if (iWrite > 0) {
                    this.zzaji += iWrite;
                    byteBuffer.position(byteBuffer.position() + iWrite);
                }
            } else {
                iWrite = 0;
            }
        } else if (this.zzajn) {
            zznt.checkState(j != C.TIME_UNSET);
            AudioTrack audioTrack = this.zzahz;
            if (this.zzaij == null) {
                this.zzaij = ByteBuffer.allocate(16);
                this.zzaij.order(ByteOrder.BIG_ENDIAN);
                this.zzaij.putInt(1431633921);
            }
            if (this.zzaik == 0) {
                this.zzaij.putInt(4, iRemaining2);
                this.zzaij.putLong(8, j * 1000);
                this.zzaij.position(0);
                this.zzaik = iRemaining2;
            }
            int iRemaining3 = this.zzaij.remaining();
            if (iRemaining3 > 0) {
                int iWrite3 = audioTrack.write(this.zzaij, iRemaining3, 1);
                if (iWrite3 < 0) {
                    this.zzaik = 0;
                    iWrite = iWrite3;
                } else if (iWrite3 < iRemaining3) {
                    iWrite = 0;
                } else {
                    iWrite2 = audioTrack.write(byteBuffer, iRemaining2, 1);
                    if (iWrite2 < 0) {
                        this.zzaik = 0;
                    } else {
                        this.zzaik -= iWrite2;
                    }
                    iWrite = iWrite2;
                }
            } else {
                iWrite2 = audioTrack.write(byteBuffer, iRemaining2, 1);
                if (iWrite2 < 0) {
                    this.zzaik = 0;
                } else {
                    this.zzaik -= iWrite2;
                }
                iWrite = iWrite2;
            }
        } else {
            iWrite = this.zzahz.write(byteBuffer, iRemaining2, 1);
        }
        this.zzajp = SystemClock.elapsedRealtime();
        if (iWrite < 0) {
            throw new zzhy(iWrite);
        }
        if (!this.zzaid) {
            this.zzaiw += (long) iWrite;
        }
        if (iWrite != iRemaining2) {
            return false;
        }
        if (this.zzaid) {
            this.zzaix += (long) this.zzaiy;
        }
        this.zzajg = null;
        return true;
    }

    public final void zzew() throws zzhy {
        if (!this.zzajk && isInitialized() && zzex()) {
            this.zzahx.zzds(zzfc());
            this.zzaik = 0;
            this.zzajk = true;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0021  */
    /* JADX WARN: Code duplicated, block: B:15:0x0025  */
    /* JADX WARN: Code duplicated, block: B:18:0x0031 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:19:0x0032  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0032 -> B:9:0x0012). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    private final boolean zzex() throws com.google.android.gms.internal.ads.zzhy {
        /*
            r9 = this;
            int r0 = r9.zzajj
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L14
            boolean r0 = r9.zzaid
            if (r0 == 0) goto Lf
            com.google.android.gms.internal.ads.zzhg[] r0 = r9.zzajd
            int r0 = r0.length
            goto L10
        Lf:
            r0 = 0
        L10:
            r9.zzajj = r0
        L12:
            r0 = 1
            goto L15
        L14:
            r0 = 0
        L15:
            int r4 = r9.zzajj
            com.google.android.gms.internal.ads.zzhg[] r5 = r9.zzajd
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L38
            r4 = r5[r4]
            if (r0 == 0) goto L28
            r4.zzes()
        L28:
            r9.zzdp(r7)
            boolean r0 = r4.zzel()
            if (r0 != 0) goto L32
            return r3
        L32:
            int r0 = r9.zzajj
            int r0 = r0 + r2
            r9.zzajj = r0
            goto L12
        L38:
            java.nio.ByteBuffer r0 = r9.zzajg
            if (r0 == 0) goto L44
            r9.zzb(r0, r7)
            java.nio.ByteBuffer r0 = r9.zzajg
            if (r0 == 0) goto L44
            return r3
        L44:
            r9.zzajj = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzhq.zzex():boolean");
    }

    public final boolean zzel() {
        if (isInitialized()) {
            return this.zzajk && !zzey();
        }
        return true;
    }

    public final boolean zzey() {
        if (isInitialized()) {
            if (zzfc() <= this.zzahx.zzfi()) {
                if (zzfe() && this.zzahz.getPlayState() == 2 && this.zzahz.getPlaybackHeadPosition() == 0) {
                }
            }
            return true;
        }
        return false;
    }

    public final zzgw zzb(zzgw zzgwVar) {
        if (this.zzaid) {
            this.zzade = zzgw.zzafx;
            return this.zzade;
        }
        zzgw zzgwVar2 = new zzgw(this.zzahs.zzb(zzgwVar.zzafy), this.zzahs.zzc(zzgwVar.zzafz));
        zzgw zzgwVar3 = this.zzaig;
        if (zzgwVar3 == null) {
            if (!this.zzahy.isEmpty()) {
                zzgwVar3 = this.zzahy.getLast().zzade;
            } else {
                zzgwVar3 = this.zzade;
            }
        }
        if (!zzgwVar2.equals(zzgwVar3)) {
            if (isInitialized()) {
                this.zzaig = zzgwVar2;
            } else {
                this.zzade = zzgwVar2;
            }
        }
        return this.zzade;
    }

    public final zzgw zzez() {
        return this.zzade;
    }

    public final void setStreamType(int i) {
        if (this.streamType == i) {
            return;
        }
        this.streamType = i;
        if (this.zzajn) {
            return;
        }
        reset();
        this.zzajm = 0;
    }

    public final void zzs(int i) {
        zznt.checkState(zzoh.SDK_INT >= 21);
        if (this.zzajn && this.zzajm == i) {
            return;
        }
        this.zzajn = true;
        this.zzajm = i;
        reset();
    }

    public final void zzfa() {
        if (this.zzajn) {
            this.zzajn = false;
            this.zzajm = 0;
            reset();
        }
    }

    public final void setVolume(float f) {
        if (this.zzcw != f) {
            this.zzcw = f;
            zzfb();
        }
    }

    private final void zzfb() {
        if (isInitialized()) {
            if (zzoh.SDK_INT >= 21) {
                this.zzahz.setVolume(this.zzcw);
                return;
            }
            AudioTrack audioTrack = this.zzahz;
            float f = this.zzcw;
            audioTrack.setStereoVolume(f, f);
        }
    }

    public final void pause() {
        this.zzajl = false;
        if (isInitialized()) {
            zzfd();
            this.zzahx.pause();
        }
    }

    public final void reset() {
        if (isInitialized()) {
            this.zzait = 0L;
            this.zzaiu = 0L;
            this.zzaiw = 0L;
            this.zzaix = 0L;
            this.zzaiy = 0;
            zzgw zzgwVar = this.zzaig;
            if (zzgwVar != null) {
                this.zzade = zzgwVar;
                this.zzaig = null;
            } else if (!this.zzahy.isEmpty()) {
                this.zzade = this.zzahy.getLast().zzade;
            }
            this.zzahy.clear();
            this.zzaih = 0L;
            this.zzaii = 0L;
            this.zzajf = null;
            this.zzajg = null;
            int i = 0;
            while (true) {
                zzhg[] zzhgVarArr = this.zzajd;
                if (i >= zzhgVarArr.length) {
                    break;
                }
                zzhg zzhgVar = zzhgVarArr[i];
                zzhgVar.flush();
                this.zzaje[i] = zzhgVar.zzet();
                i++;
            }
            this.zzajk = false;
            this.zzajj = -1;
            this.zzaij = null;
            this.zzaik = 0;
            this.zzaiz = 0;
            this.zzajc = 0L;
            zzfd();
            if (this.zzahz.getPlayState() == 3) {
                this.zzahz.pause();
            }
            AudioTrack audioTrack = this.zzahz;
            this.zzahz = null;
            this.zzahx.zza(null, false);
            this.zzahv.close();
            new zzhp(this, audioTrack).start();
        }
    }

    public final void release() {
        reset();
        for (zzhg zzhgVar : this.zzaht) {
            zzhgVar.reset();
        }
        this.zzajm = 0;
        this.zzajl = false;
    }

    private final boolean isInitialized() {
        return this.zzahz != null;
    }

    private final long zzdq(long j) {
        return (j * 1000000) / ((long) this.zzafm);
    }

    private final long zzdr(long j) {
        return (j * ((long) this.zzafm)) / 1000000;
    }

    private final long zzfc() {
        return this.zzaid ? this.zzaix : this.zzaiw / ((long) this.zzaiv);
    }

    private final void zzfd() {
        this.zzain = 0L;
        this.zzaim = 0;
        this.zzail = 0;
        this.zzaio = 0L;
        this.zzaip = false;
        this.zzaiq = 0L;
    }

    private final boolean zzfe() {
        if (zzoh.SDK_INT >= 23) {
            return false;
        }
        int i = this.zzaic;
        return i == 5 || i == 6;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0034  */
    private static int zzaw(String str) {
        byte b;
        switch (str) {
            case "audio/vnd.dts":
                b = 2;
                break;
            case "audio/ac3":
                b = 0;
                break;
            case "audio/eac3":
                b = 1;
                break;
            case "audio/vnd.dts.hd":
                b = 3;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return 5;
        }
        if (b == 1) {
            return 6;
        }
        if (b != 2) {
            return b != 3 ? 0 : 8;
        }
        return 7;
    }
}
