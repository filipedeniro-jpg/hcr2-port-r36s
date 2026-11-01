package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzak implements zzn {
    private static final boolean DEBUG = zzag.DEBUG;

    @Deprecated
    private final zzas zzbu;
    private final zzah zzbv;
    private final zzaj zzbw;

    public zzak(zzah zzahVar) {
        this(zzahVar, new zzaj(4096));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private zzak(zzah zzahVar, zzaj zzajVar) {
        this.zzbv = zzahVar;
        this.zzbu = zzahVar;
        this.zzbw = zzajVar;
    }

    /* JADX WARN: Code duplicated, block: B:115:0x0202 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:116:0x01f6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:119:0x01b0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x0197  */
    @Override // com.google.android.gms.internal.ads.zzn
    public zzo zzc(zzq<?> zzqVar) throws zzae {
        zzap zzapVarZza;
        byte[] bArr;
        int statusCode;
        zzo zzoVar;
        Map<String, String> mapEmptyMap;
        byte[] bArrZza;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            List<zzk> listEmptyList = Collections.emptyList();
            try {
                try {
                    zzd zzdVarZze = zzqVar.zze();
                    if (zzdVarZze == null) {
                        mapEmptyMap = Collections.emptyMap();
                    } else {
                        HashMap map = new HashMap();
                        if (zzdVarZze.zzg != null) {
                            map.put(HttpRequest.HEADER_IF_NONE_MATCH, zzdVarZze.zzg);
                        }
                        if (zzdVarZze.zzi > 0) {
                            map.put("If-Modified-Since", zzaq.zzb(zzdVarZze.zzi));
                        }
                        mapEmptyMap = map;
                    }
                    zzapVarZza = this.zzbv.zza(zzqVar, mapEmptyMap);
                    try {
                        int statusCode2 = zzapVarZza.getStatusCode();
                        List<zzk> listZzo = zzapVarZza.zzo();
                        if (statusCode2 == 304) {
                            zzd zzdVarZze2 = zzqVar.zze();
                            if (zzdVarZze2 == null) {
                                return new zzo(304, (byte[]) null, true, SystemClock.elapsedRealtime() - jElapsedRealtime, listZzo);
                            }
                            TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
                            if (!listZzo.isEmpty()) {
                                Iterator<zzk> it = listZzo.iterator();
                                while (it.hasNext()) {
                                    treeSet.add(it.next().getName());
                                }
                            }
                            ArrayList arrayList = new ArrayList(listZzo);
                            if (zzdVarZze2.zzm != null) {
                                if (!zzdVarZze2.zzm.isEmpty()) {
                                    for (zzk zzkVar : zzdVarZze2.zzm) {
                                        if (!treeSet.contains(zzkVar.getName())) {
                                            arrayList.add(zzkVar);
                                        }
                                    }
                                }
                            } else if (!zzdVarZze2.zzl.isEmpty()) {
                                for (Map.Entry<String, String> entry : zzdVarZze2.zzl.entrySet()) {
                                    if (!treeSet.contains(entry.getKey())) {
                                        arrayList.add(new zzk(entry.getKey(), entry.getValue()));
                                    }
                                }
                            }
                            return new zzo(304, zzdVarZze2.data, true, SystemClock.elapsedRealtime() - jElapsedRealtime, (List<zzk>) arrayList);
                        }
                        InputStream content = zzapVarZza.getContent();
                        if (content != null) {
                            bArrZza = zza(content, zzapVarZza.getContentLength());
                        } else {
                            bArrZza = new byte[0];
                        }
                        byte[] bArr2 = bArrZza;
                        try {
                            long jElapsedRealtime2 = SystemClock.elapsedRealtime() - jElapsedRealtime;
                            if (DEBUG || jElapsedRealtime2 > 3000) {
                                Object[] objArr = new Object[5];
                                objArr[0] = zzqVar;
                                objArr[1] = Long.valueOf(jElapsedRealtime2);
                                objArr[2] = bArr2 != null ? Integer.valueOf(bArr2.length) : "null";
                                objArr[3] = Integer.valueOf(statusCode2);
                                objArr[4] = Integer.valueOf(zzqVar.zzi().zzb());
                                zzag.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
                            }
                            if (statusCode2 < 200 || statusCode2 > 299) {
                                throw new IOException();
                            }
                            return new zzo(statusCode2, bArr2, false, SystemClock.elapsedRealtime() - jElapsedRealtime, listZzo);
                        } catch (IOException e) {
                            e = e;
                            listEmptyList = listZzo;
                            bArr = bArr2;
                            if (zzapVarZza != null) {
                                statusCode = zzapVarZza.getStatusCode();
                                zzag.e("Unexpected response code %d for %s", Integer.valueOf(statusCode), zzqVar.getUrl());
                                if (bArr != null) {
                                    zzoVar = new zzo(statusCode, bArr, false, SystemClock.elapsedRealtime() - jElapsedRealtime, listEmptyList);
                                    if (statusCode == 401 && statusCode != 403) {
                                        if (statusCode >= 400 && statusCode <= 499) {
                                            throw new zzh(zzoVar);
                                        }
                                        if (statusCode >= 500 && statusCode <= 599) {
                                            throw new zzac(zzoVar);
                                        }
                                        throw new zzac(zzoVar);
                                    }
                                    zza("auth", zzqVar, new zzb(zzoVar));
                                } else {
                                    zza("network", zzqVar, new zzp());
                                }
                            } else {
                                throw new zzr(e);
                            }
                        }
                    } catch (IOException e2) {
                        e = e2;
                        bArr = null;
                        if (zzapVarZza != null) {
                            statusCode = zzapVarZza.getStatusCode();
                            zzag.e("Unexpected response code %d for %s", Integer.valueOf(statusCode), zzqVar.getUrl());
                            if (bArr != null) {
                                zzoVar = new zzo(statusCode, bArr, false, SystemClock.elapsedRealtime() - jElapsedRealtime, listEmptyList);
                                if (statusCode == 401) {
                                }
                                zza("auth", zzqVar, new zzb(zzoVar));
                            } else {
                                zza("network", zzqVar, new zzp());
                            }
                        } else {
                            throw new zzr(e);
                        }
                    }
                } catch (IOException e3) {
                    e = e3;
                    zzapVarZza = null;
                }
            } catch (MalformedURLException e4) {
                String strValueOf = String.valueOf(zzqVar.getUrl());
                throw new RuntimeException(strValueOf.length() != 0 ? "Bad URL ".concat(strValueOf) : new String("Bad URL "), e4);
            } catch (SocketTimeoutException unused) {
                zza("socket", zzqVar, new zzaf());
            }
        }
    }

    private static void zza(String str, zzq<?> zzqVar, zzae zzaeVar) throws zzae {
        zzad zzadVarZzi = zzqVar.zzi();
        int iZzh = zzqVar.zzh();
        try {
            zzadVarZzi.zza(zzaeVar);
            zzqVar.zzb(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(iZzh)));
        } catch (zzae e) {
            zzqVar.zzb(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(iZzh)));
            throw e;
        }
    }

    private final byte[] zza(InputStream inputStream, int i) throws IOException, zzac {
        zzaw zzawVar = new zzaw(this.zzbw, i);
        try {
            if (inputStream == null) {
                throw new zzac();
            }
            byte[] bArrZzc = this.zzbw.zzc(1024);
            while (true) {
                int i2 = inputStream.read(bArrZzc);
                if (i2 == -1) {
                    break;
                }
                zzawVar.write(bArrZzc, 0, i2);
            }
            byte[] byteArray = zzawVar.toByteArray();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                    zzag.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.zzbw.zza(bArrZzc);
            zzawVar.close();
            return byteArray;
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException unused2) {
                    zzag.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            this.zzbw.zza(null);
            zzawVar.close();
            throw th;
        }
    }
}
