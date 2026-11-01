package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzal implements zza {
    private final Map<String, zzao> zzbx;
    private long zzby;
    private final File zzbz;
    private final int zzca;

    public zzal(File file, int i) {
        this.zzbx = new LinkedHashMap(16, 0.75f, true);
        this.zzby = 0L;
        this.zzbz = file;
        this.zzca = i;
    }

    public zzal(File file) {
        this(file, 5242880);
    }

    @Override // com.google.android.gms.internal.ads.zza
    public final synchronized zzd zza(String str) {
        zzao zzaoVar = this.zzbx.get(str);
        if (zzaoVar == null) {
            return null;
        }
        File fileZze = zze(str);
        try {
            zzan zzanVar = new zzan(new BufferedInputStream(zza(fileZze)), fileZze.length());
            try {
                zzao zzaoVarZzc = zzao.zzc(zzanVar);
                if (!TextUtils.equals(str, zzaoVarZzc.zzce)) {
                    zzag.d("%s: key=%s, found=%s", fileZze.getAbsolutePath(), str, zzaoVarZzc.zzce);
                    removeEntry(str);
                    zzanVar.close();
                    return null;
                }
                byte[] bArrZza = zza(zzanVar, zzanVar.zzn());
                zzd zzdVar = new zzd();
                zzdVar.data = bArrZza;
                zzdVar.zzg = zzaoVar.zzg;
                zzdVar.zzh = zzaoVar.zzh;
                zzdVar.zzi = zzaoVar.zzi;
                zzdVar.zzj = zzaoVar.zzj;
                zzdVar.zzk = zzaoVar.zzk;
                List<zzk> list = zzaoVar.zzm;
                TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                for (zzk zzkVar : list) {
                    treeMap.put(zzkVar.getName(), zzkVar.getValue());
                }
                zzdVar.zzl = treeMap;
                zzdVar.zzm = Collections.unmodifiableList(zzaoVar.zzm);
                zzanVar.close();
                return zzdVar;
            } catch (Throwable th) {
                zzanVar.close();
                throw th;
            }
        } catch (IOException e) {
            zzag.d("%s: %s", fileZze.getAbsolutePath(), e.toString());
            remove(str);
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zza
    public final synchronized void initialize() {
        try {
            if (!this.zzbz.exists()) {
                if (!this.zzbz.mkdirs()) {
                    zzag.e("Unable to create cache dir %s", this.zzbz.getAbsolutePath());
                }
                return;
            }
            File[] fileArrListFiles = this.zzbz.listFiles();
            if (fileArrListFiles == null) {
                return;
            }
            for (File file : fileArrListFiles) {
                try {
                    long length = file.length();
                    zzan zzanVar = new zzan(new BufferedInputStream(zza(file)), length);
                    try {
                        zzao zzaoVarZzc = zzao.zzc(zzanVar);
                        zzaoVarZzc.zzcd = length;
                        zza(zzaoVarZzc.zzce, zzaoVarZzc);
                        zzanVar.close();
                    } catch (Throwable th) {
                        zzanVar.close();
                        throw th;
                    }
                } catch (IOException unused) {
                    file.delete();
                }
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zza
    public final synchronized void zza(String str, zzd zzdVar) {
        if (this.zzby + ((long) zzdVar.data.length) <= this.zzca || zzdVar.data.length <= this.zzca * 0.9f) {
            File fileZze = zze(str);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileZze));
                zzao zzaoVar = new zzao(str, zzdVar);
                if (!zzaoVar.zza(bufferedOutputStream)) {
                    bufferedOutputStream.close();
                    zzag.d("Failed to write header for %s", fileZze.getAbsolutePath());
                    throw new IOException();
                }
                bufferedOutputStream.write(zzdVar.data);
                bufferedOutputStream.close();
                zzaoVar.zzcd = fileZze.length();
                zza(str, zzaoVar);
                if (this.zzby >= this.zzca) {
                    if (zzag.DEBUG) {
                        zzag.v("Pruning old cache entries.", new Object[0]);
                    }
                    long j = this.zzby;
                    long jElapsedRealtime = SystemClock.elapsedRealtime();
                    Iterator<Map.Entry<String, zzao>> it = this.zzbx.entrySet().iterator();
                    int i = 0;
                    while (true) {
                        if (!it.hasNext()) {
                            j = j;
                            break;
                        }
                        zzao value = it.next().getValue();
                        if (zze(value.zzce).delete()) {
                            this.zzby -= value.zzcd;
                        } else {
                            zzag.d("Could not delete cache entry for key=%s, filename=%s", value.zzce, zzd(value.zzce));
                        }
                        it.remove();
                        i++;
                        if (this.zzby < this.zzca * 0.9f) {
                            break;
                        } else {
                            j = j;
                        }
                    }
                    if (zzag.DEBUG) {
                        zzag.v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i), Long.valueOf(this.zzby - j), Long.valueOf(SystemClock.elapsedRealtime() - jElapsedRealtime));
                    }
                }
            } catch (IOException unused) {
                if (fileZze.delete()) {
                    return;
                }
                zzag.d("Could not clean up file %s", fileZze.getAbsolutePath());
            }
        }
    }

    private final synchronized void remove(String str) {
        boolean zDelete = zze(str).delete();
        removeEntry(str);
        if (!zDelete) {
            zzag.d("Could not delete cache entry for key=%s, filename=%s", str, zzd(str));
        }
    }

    private static String zzd(String str) {
        int length = str.length() / 2;
        String strValueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        String strValueOf2 = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    private final File zze(String str) {
        return new File(this.zzbz, zzd(str));
    }

    private final void zza(String str, zzao zzaoVar) {
        if (!this.zzbx.containsKey(str)) {
            this.zzby += zzaoVar.zzcd;
        } else {
            this.zzby += zzaoVar.zzcd - this.zzbx.get(str).zzcd;
        }
        this.zzbx.put(str, zzaoVar);
    }

    private final void removeEntry(String str) {
        zzao zzaoVarRemove = this.zzbx.remove(str);
        if (zzaoVarRemove != null) {
            this.zzby -= zzaoVarRemove.zzcd;
        }
    }

    @VisibleForTesting
    private static byte[] zza(zzan zzanVar, long j) throws IOException {
        long jZzn = zzanVar.zzn();
        if (j >= 0 && j <= jZzn) {
            int i = (int) j;
            if (i == j) {
                byte[] bArr = new byte[i];
                new DataInputStream(zzanVar).readFully(bArr);
                return bArr;
            }
        }
        StringBuilder sb = new StringBuilder(73);
        sb.append("streamToBytes length=");
        sb.append(j);
        sb.append(", maxLength=");
        sb.append(jZzn);
        throw new IOException(sb.toString());
    }

    @VisibleForTesting
    private static InputStream zza(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    private static int zza(InputStream inputStream) throws IOException {
        int i = inputStream.read();
        if (i != -1) {
            return i;
        }
        throw new EOFException();
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write(i >>> 24);
    }

    static int zzb(InputStream inputStream) throws IOException {
        return (zza(inputStream) << 24) | zza(inputStream) | 0 | (zza(inputStream) << 8) | (zza(inputStream) << 16);
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) j);
        outputStream.write((byte) (j >>> 8));
        outputStream.write((byte) (j >>> 16));
        outputStream.write((byte) (j >>> 24));
        outputStream.write((byte) (j >>> 32));
        outputStream.write((byte) (j >>> 40));
        outputStream.write((byte) (j >>> 48));
        outputStream.write((byte) (j >>> 56));
    }

    static long zzc(InputStream inputStream) throws IOException {
        return (((long) zza(inputStream)) & 255) | 0 | ((((long) zza(inputStream)) & 255) << 8) | ((((long) zza(inputStream)) & 255) << 16) | ((((long) zza(inputStream)) & 255) << 24) | ((((long) zza(inputStream)) & 255) << 32) | ((((long) zza(inputStream)) & 255) << 40) | ((((long) zza(inputStream)) & 255) << 48) | ((255 & ((long) zza(inputStream))) << 56);
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    static String zza(zzan zzanVar) throws IOException {
        return new String(zza(zzanVar, zzc(zzanVar)), "UTF-8");
    }

    static List<zzk> zzb(zzan zzanVar) throws IOException {
        int iZzb = zzb((InputStream) zzanVar);
        if (iZzb < 0) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("readHeaderList size=");
            sb.append(iZzb);
            throw new IOException(sb.toString());
        }
        List<zzk> listEmptyList = iZzb == 0 ? Collections.emptyList() : new ArrayList<>();
        for (int i = 0; i < iZzb; i++) {
            listEmptyList.add(new zzk(zza(zzanVar).intern(), zza(zzanVar).intern()));
        }
        return listEmptyList;
    }
}
