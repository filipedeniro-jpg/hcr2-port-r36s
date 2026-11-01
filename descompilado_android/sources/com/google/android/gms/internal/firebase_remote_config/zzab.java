package com.google.android.gms.internal.firebase_remote_config;

import com.ironsource.mediationsdk.utils.IronSourceConstants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public final class zzab {
    private zzv zzao;
    private zzx zzbb;
    private zzs zzbi;
    private zzt zzbj;
    private zzah zzbm;
    private zzci zzk;
    private String zzp;
    private final zzag zzx;
    private zzw zzbc = new zzw();
    private zzw zzbd = new zzw();
    private int zzbe = 10;
    private int zzbf = 16384;
    private boolean zzbg = true;
    private boolean zzbh = true;
    private int zzbk = 20000;
    private int zzbl = 20000;
    private boolean zzbn = true;
    private boolean zzbo = true;

    @Deprecated
    private boolean zzbp = false;
    private zzck zzbq = zzck.zzgf;

    zzab(zzag zzagVar, String str) {
        this.zzx = zzagVar;
        zzw(null);
    }

    public final zzag zzs() {
        return this.zzx;
    }

    public final String getRequestMethod() {
        return this.zzp;
    }

    public final zzab zzw(String str) {
        if (!(str == null || zzy.zzv(str))) {
            throw new IllegalArgumentException();
        }
        this.zzp = str;
        return this;
    }

    public final zzt zzt() {
        return this.zzbj;
    }

    public final zzab zza(zzt zztVar) {
        this.zzbj = (zzt) zzdt.checkNotNull(zztVar);
        return this;
    }

    public final zzs zzu() {
        return this.zzbi;
    }

    public final zzab zza(zzs zzsVar) {
        this.zzbi = zzsVar;
        return this;
    }

    public final zzab zza(zzv zzvVar) {
        this.zzao = zzvVar;
        return this;
    }

    public final int zzv() {
        return this.zzbf;
    }

    public final boolean zzw() {
        return this.zzbg;
    }

    public final zzab zza(int i) {
        if (!(i >= 0)) {
            throw new IllegalArgumentException();
        }
        this.zzbk = i;
        return this;
    }

    public final zzab zzb(int i) {
        if (!(i >= 0)) {
            throw new IllegalArgumentException();
        }
        this.zzbl = i;
        return this;
    }

    public final zzw zzx() {
        return this.zzbc;
    }

    public final zzw zzy() {
        return this.zzbd;
    }

    public final zzab zza(zzx zzxVar) {
        this.zzbb = zzxVar;
        return this;
    }

    public final zzah zzz() {
        return this.zzbm;
    }

    public final zzab zza(zzah zzahVar) {
        this.zzbm = zzahVar;
        return this;
    }

    public final zzab zza(zzci zzciVar) {
        this.zzk = zzciVar;
        return this;
    }

    public final zzci zzaa() {
        return this.zzk;
    }

    public final boolean zzab() {
        return this.zzbo;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x027c  */
    /* JADX WARN: Code duplicated, block: B:105:0x0281 A[Catch: all -> 0x02ac, TRY_LEAVE, TryCatch #2 {all -> 0x02ac, blocks: (B:86:0x021c, B:88:0x0222, B:92:0x0236, B:97:0x0240, B:99:0x0252, B:101:0x025c, B:103:0x027e, B:105:0x0281), top: B:142:0x021c }] */
    /* JADX WARN: Code duplicated, block: B:107:0x0285  */
    /* JADX WARN: Code duplicated, block: B:112:0x028f  */
    /* JADX WARN: Code duplicated, block: B:124:0x02a9 A[LOOP:0: B:8:0x0019->B:124:0x02a9, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:146:0x028b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:30:0x0091  */
    /* JADX WARN: Code duplicated, block: B:31:0x0099  */
    /* JADX WARN: Code duplicated, block: B:34:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:37:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:39:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:40:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:43:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:44:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:46:0x0104  */
    /* JADX WARN: Code duplicated, block: B:48:0x0108  */
    /* JADX WARN: Code duplicated, block: B:50:0x0116  */
    /* JADX WARN: Code duplicated, block: B:51:0x011b  */
    /* JADX WARN: Code duplicated, block: B:54:0x012a  */
    /* JADX WARN: Code duplicated, block: B:55:0x014c  */
    /* JADX WARN: Code duplicated, block: B:56:0x014f  */
    /* JADX WARN: Code duplicated, block: B:58:0x0155  */
    /* JADX WARN: Code duplicated, block: B:60:0x0161  */
    /* JADX WARN: Code duplicated, block: B:61:0x0166  */
    /* JADX WARN: Code duplicated, block: B:64:0x0176  */
    /* JADX WARN: Code duplicated, block: B:67:0x019b  */
    /* JADX WARN: Code duplicated, block: B:68:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:70:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:72:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:74:0x01d5  */
    /* JADX WARN: Code duplicated, block: B:76:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:78:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:81:0x0207  */
    /* JADX WARN: Code duplicated, block: B:82:0x0209  */
    /* JADX WARN: Code duplicated, block: B:88:0x0222 A[Catch: all -> 0x02ac, TryCatch #2 {all -> 0x02ac, blocks: (B:86:0x021c, B:88:0x0222, B:92:0x0236, B:97:0x0240, B:99:0x0252, B:101:0x025c, B:103:0x027e, B:105:0x0281), top: B:142:0x021c }] */
    /* JADX WARN: Code duplicated, block: B:90:0x0232  */
    /* JADX WARN: Code duplicated, block: B:92:0x0236 A[Catch: all -> 0x02ac, TryCatch #2 {all -> 0x02ac, blocks: (B:86:0x021c, B:88:0x0222, B:92:0x0236, B:97:0x0240, B:99:0x0252, B:101:0x025c, B:103:0x027e, B:105:0x0281), top: B:142:0x021c }] */
    /* JADX WARN: Code duplicated, block: B:93:0x0239  */
    /* JADX WARN: Code duplicated, block: B:94:0x023b  */
    public final zzac zzac() throws IOException {
        StringBuilder sb;
        StringBuilder sb2;
        String userAgent;
        zzs zzsVar;
        String str;
        zzcm zzcmVar;
        boolean z;
        zzai zzaiVarZzaj;
        zzac zzacVar;
        boolean z2;
        zzah zzahVar;
        int statusCode;
        boolean z3;
        boolean z4;
        String str2;
        String type;
        zzcm zzcgVar;
        zzv zzvVar;
        String name;
        zzcm zzcmVar2;
        long jZzb;
        String strValueOf;
        String str3;
        String strValueOf2;
        String str4;
        zzab zzabVar = this;
        if (!(zzabVar.zzbe >= 0)) {
            throw new IllegalArgumentException();
        }
        int i = zzabVar.zzbe;
        zzdt.checkNotNull(zzabVar.zzp);
        zzdt.checkNotNull(zzabVar.zzbj);
        int i2 = i;
        zzac zzacVar2 = null;
        while (true) {
            if (zzacVar2 != null) {
                zzacVar2.ignore();
            }
            zzx zzxVar = zzabVar.zzbb;
            if (zzxVar != null) {
                zzxVar.zzb(zzabVar);
            }
            String strZzp = zzabVar.zzbj.zzp();
            zzaj zzajVarZzc = zzabVar.zzx.zzc(zzabVar.zzp, strZzp);
            Logger logger = zzag.zzbz;
            boolean z5 = zzabVar.zzbg && logger.isLoggable(Level.CONFIG);
            try {
                try {
                    try {
                        if (z5) {
                            sb = new StringBuilder();
                            sb.append("-------------- REQUEST  --------------");
                            sb.append(zzcl.zzgg);
                            sb.append(zzabVar.zzp);
                            sb.append(' ');
                            sb.append(strZzp);
                            sb.append(zzcl.zzgg);
                            if (zzabVar.zzbh) {
                                sb2 = new StringBuilder("curl -v --compressed");
                                if (!zzabVar.zzp.equals(HttpRequest.METHOD_GET)) {
                                    sb2.append(" -X ");
                                    sb2.append(zzabVar.zzp);
                                }
                            }
                            userAgent = zzabVar.zzbc.getUserAgent();
                            if (userAgent == null) {
                                zzabVar.zzbc.zzu("Google-HTTP-Java-Client/1.26.0-SNAPSHOT (gzip)");
                            } else {
                                zzw zzwVar = zzabVar.zzbc;
                                StringBuilder sb3 = new StringBuilder(String.valueOf(userAgent).length() + 47);
                                sb3.append(userAgent);
                                sb3.append(" Google-HTTP-Java-Client/1.26.0-SNAPSHOT (gzip)");
                                zzwVar.zzu(sb3.toString());
                            }
                            zzw.zza(zzabVar.zzbc, sb, sb2, logger, zzajVarZzc);
                            zzabVar.zzbc.zzu(userAgent);
                            zzsVar = zzabVar.zzbi;
                            if (zzsVar != null) {
                                zzsVar.zzn();
                            }
                            if (zzsVar != null) {
                                type = zzabVar.zzbi.getType();
                                if (z5) {
                                    zzcgVar = new zzcg(zzsVar, zzag.zzbz, Level.CONFIG, zzabVar.zzbf);
                                } else {
                                    zzcgVar = zzsVar;
                                }
                                zzvVar = zzabVar.zzao;
                                if (zzvVar == null) {
                                    jZzb = zzabVar.zzbi.getLength();
                                    zzcmVar2 = zzcgVar;
                                    name = null;
                                } else {
                                    name = zzvVar.getName();
                                    zzu zzuVar = new zzu(zzcgVar, zzabVar.zzao);
                                    zzcmVar2 = zzuVar;
                                    jZzb = zzca.zzb(zzuVar);
                                }
                                if (z5) {
                                    if (type != null) {
                                        str = HttpRequest.METHOD_GET;
                                        strValueOf2 = String.valueOf(type);
                                        if (strValueOf2.length() != 0) {
                                            str4 = "Content-Type: ".concat(strValueOf2);
                                        } else {
                                            str4 = new String("Content-Type: ");
                                        }
                                        sb.append(str4);
                                        sb.append(zzcl.zzgg);
                                        if (sb2 != null) {
                                            StringBuilder sb4 = new StringBuilder(String.valueOf(str4).length() + 6);
                                            sb4.append(" -H '");
                                            sb4.append(str4);
                                            sb4.append("'");
                                            sb2.append(sb4.toString());
                                        }
                                    } else {
                                        str = HttpRequest.METHOD_GET;
                                    }
                                    if (name != null) {
                                        strValueOf = String.valueOf(name);
                                        if (strValueOf.length() != 0) {
                                            str3 = "Content-Encoding: ".concat(strValueOf);
                                        } else {
                                            str3 = new String("Content-Encoding: ");
                                        }
                                        sb.append(str3);
                                        sb.append(zzcl.zzgg);
                                        if (sb2 != null) {
                                            StringBuilder sb5 = new StringBuilder(String.valueOf(str3).length() + 6);
                                            sb5.append(" -H '");
                                            sb5.append(str3);
                                            sb5.append("'");
                                            sb2.append(sb5.toString());
                                        }
                                    }
                                    if (jZzb >= 0) {
                                        StringBuilder sb6 = new StringBuilder(36);
                                        sb6.append("Content-Length: ");
                                        sb6.append(jZzb);
                                        sb.append(sb6.toString());
                                        sb.append(zzcl.zzgg);
                                    }
                                } else {
                                    i2 = i2;
                                    str = HttpRequest.METHOD_GET;
                                }
                                if (sb2 != null) {
                                    sb2.append(" -d '@-'");
                                }
                                zzajVarZzc.setContentType(type);
                                zzajVarZzc.setContentEncoding(name);
                                zzajVarZzc.setContentLength(jZzb);
                                zzajVarZzc.zza(zzcmVar2);
                                zzcmVar = zzcmVar2;
                            } else {
                                i2 = i2;
                                str = HttpRequest.METHOD_GET;
                            }
                            if (z5) {
                                zzcmVar = zzsVar;
                                logger.logp(Level.CONFIG, "com.google.api.client.http.HttpRequest", "execute", sb.toString());
                                if (sb2 != null) {
                                    sb2.append(" -- '");
                                    sb2.append(strZzp.replaceAll("'", "'\"'\"'"));
                                    sb2.append("'");
                                    if (zzcmVar != null) {
                                        sb2.append(" << $$$");
                                    }
                                    logger.logp(Level.CONFIG, "com.google.api.client.http.HttpRequest", "execute", sb2.toString());
                                }
                            }
                            if (i2 > 0) {
                                z = true;
                            } else {
                                z = false;
                            }
                            zzabVar = this;
                            zzajVarZzc.zza(zzabVar.zzbk, zzabVar.zzbl);
                            zzaiVarZzaj = zzajVarZzc.zzaj();
                            zzacVar = new zzac(zzabVar, zzaiVarZzaj);
                            if (zzacVar.zzad()) {
                                z2 = false;
                            } else {
                                statusCode = zzacVar.getStatusCode();
                                String location = zzacVar.zzx().getLocation();
                                if (zzabVar.zzbn) {
                                    if (statusCode != 307) {
                                        switch (statusCode) {
                                            case 301:
                                            case IronSourceConstants.OFFERWALL_AVAILABLE /* 302 */:
                                            case 303:
                                                z4 = true;
                                                break;
                                            default:
                                                z4 = false;
                                                break;
                                        }
                                    } else {
                                        z4 = true;
                                    }
                                    if (z4 || location == null) {
                                        z3 = false;
                                    } else {
                                        zzabVar.zza(new zzt(zzabVar.zzbj.zzk(location)));
                                        if (statusCode == 303) {
                                            zzabVar.zzw(str);
                                            str2 = null;
                                            zzabVar.zzbi = null;
                                        } else {
                                            str2 = null;
                                        }
                                        zzabVar.zzbc.zzo(str2);
                                        zzabVar.zzbc.zzq(str2);
                                        zzabVar.zzbc.zzr(str2);
                                        zzabVar.zzbc.zzp(str2);
                                        zzabVar.zzbc.zzs(str2);
                                        zzabVar.zzbc.zzt(str2);
                                        z3 = true;
                                    }
                                } else {
                                    z3 = false;
                                }
                                z2 = z & z3;
                                if (z2) {
                                    zzacVar.ignore();
                                }
                            }
                            i2--;
                            if (!z2) {
                                zzahVar = zzabVar.zzbm;
                                if (zzahVar != null) {
                                    zzahVar.zzb(zzacVar);
                                }
                                if (zzabVar.zzbo || zzacVar.zzad()) {
                                    return zzacVar;
                                }
                                try {
                                    throw new zzaf(zzacVar);
                                } catch (Throwable th) {
                                    zzacVar.disconnect();
                                    throw th;
                                }
                            }
                            zzacVar2 = zzacVar;
                        } else {
                            sb = null;
                        }
                        if (zzacVar.zzad()) {
                            statusCode = zzacVar.getStatusCode();
                            String location2 = zzacVar.zzx().getLocation();
                            if (zzabVar.zzbn) {
                                z3 = false;
                            } else {
                                if (statusCode != 307) {
                                    switch (statusCode) {
                                        case 301:
                                        case IronSourceConstants.OFFERWALL_AVAILABLE /* 302 */:
                                        case 303:
                                            z4 = true;
                                            break;
                                        default:
                                            z4 = false;
                                            break;
                                    }
                                } else {
                                    z4 = true;
                                }
                                if (z4) {
                                    z3 = false;
                                } else {
                                    z3 = false;
                                }
                            }
                            z2 = z & z3;
                            if (z2) {
                                zzacVar.ignore();
                            }
                        } else {
                            z2 = false;
                        }
                        i2--;
                        if (!z2) {
                            zzahVar = zzabVar.zzbm;
                            if (zzahVar != null) {
                                zzahVar.zzb(zzacVar);
                            }
                            if (zzabVar.zzbo) {
                            }
                            return zzacVar;
                        }
                        zzacVar2 = zzacVar;
                    } catch (Throwable th2) {
                        zzacVar.disconnect();
                        throw th2;
                    }
                    zzacVar = new zzac(zzabVar, zzaiVarZzaj);
                } catch (Throwable th3) {
                    InputStream content = zzaiVarZzaj.getContent();
                    if (content != null) {
                        content.close();
                    }
                    throw th3;
                }
                zzaiVarZzaj = zzajVarZzc.zzaj();
            } catch (IOException e) {
                throw e;
            }
            sb2 = null;
            userAgent = zzabVar.zzbc.getUserAgent();
            if (userAgent == null) {
                zzabVar.zzbc.zzu("Google-HTTP-Java-Client/1.26.0-SNAPSHOT (gzip)");
            } else {
                zzw zzwVar2 = zzabVar.zzbc;
                StringBuilder sb7 = new StringBuilder(String.valueOf(userAgent).length() + 47);
                sb7.append(userAgent);
                sb7.append(" Google-HTTP-Java-Client/1.26.0-SNAPSHOT (gzip)");
                zzwVar2.zzu(sb7.toString());
            }
            zzw.zza(zzabVar.zzbc, sb, sb2, logger, zzajVarZzc);
            zzabVar.zzbc.zzu(userAgent);
            zzsVar = zzabVar.zzbi;
            if (zzsVar != null) {
                zzsVar.zzn();
            }
            if (zzsVar != null) {
                type = zzabVar.zzbi.getType();
                if (z5) {
                    zzcgVar = new zzcg(zzsVar, zzag.zzbz, Level.CONFIG, zzabVar.zzbf);
                } else {
                    zzcgVar = zzsVar;
                }
                zzvVar = zzabVar.zzao;
                if (zzvVar == null) {
                    jZzb = zzabVar.zzbi.getLength();
                    zzcmVar2 = zzcgVar;
                    name = null;
                } else {
                    name = zzvVar.getName();
                    zzu zzuVar2 = new zzu(zzcgVar, zzabVar.zzao);
                    zzcmVar2 = zzuVar2;
                    jZzb = zzca.zzb(zzuVar2);
                }
                if (z5) {
                    if (type != null) {
                        str = HttpRequest.METHOD_GET;
                        strValueOf2 = String.valueOf(type);
                        if (strValueOf2.length() != 0) {
                            str4 = "Content-Type: ".concat(strValueOf2);
                        } else {
                            str4 = new String("Content-Type: ");
                        }
                        sb.append(str4);
                        sb.append(zzcl.zzgg);
                        if (sb2 != null) {
                            StringBuilder sb8 = new StringBuilder(String.valueOf(str4).length() + 6);
                            sb8.append(" -H '");
                            sb8.append(str4);
                            sb8.append("'");
                            sb2.append(sb8.toString());
                        }
                    } else {
                        str = HttpRequest.METHOD_GET;
                    }
                    if (name != null) {
                        strValueOf = String.valueOf(name);
                        if (strValueOf.length() != 0) {
                            str3 = "Content-Encoding: ".concat(strValueOf);
                        } else {
                            str3 = new String("Content-Encoding: ");
                        }
                        sb.append(str3);
                        sb.append(zzcl.zzgg);
                        if (sb2 != null) {
                            StringBuilder sb9 = new StringBuilder(String.valueOf(str3).length() + 6);
                            sb9.append(" -H '");
                            sb9.append(str3);
                            sb9.append("'");
                            sb2.append(sb9.toString());
                        }
                    }
                    if (jZzb >= 0) {
                        StringBuilder sb10 = new StringBuilder(36);
                        sb10.append("Content-Length: ");
                        sb10.append(jZzb);
                        sb.append(sb10.toString());
                        sb.append(zzcl.zzgg);
                    }
                } else {
                    i2 = i2;
                    str = HttpRequest.METHOD_GET;
                }
                if (sb2 != null) {
                    sb2.append(" -d '@-'");
                }
                zzajVarZzc.setContentType(type);
                zzajVarZzc.setContentEncoding(name);
                zzajVarZzc.setContentLength(jZzb);
                zzajVarZzc.zza(zzcmVar2);
                zzcmVar = zzcmVar2;
            } else {
                i2 = i2;
                str = HttpRequest.METHOD_GET;
            }
            if (z5) {
                zzcmVar = zzsVar;
                logger.logp(Level.CONFIG, "com.google.api.client.http.HttpRequest", "execute", sb.toString());
                if (sb2 != null) {
                    sb2.append(" -- '");
                    sb2.append(strZzp.replaceAll("'", "'\"'\"'"));
                    sb2.append("'");
                    if (zzcmVar != null) {
                        sb2.append(" << $$$");
                    }
                    logger.logp(Level.CONFIG, "com.google.api.client.http.HttpRequest", "execute", sb2.toString());
                }
            }
            if (i2 > 0) {
                z = true;
            } else {
                z = false;
            }
            zzabVar = this;
            zzajVarZzc.zza(zzabVar.zzbk, zzabVar.zzbl);
        }
    }
}
