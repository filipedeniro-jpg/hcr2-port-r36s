package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public final class zze extends zzaf {
    private final transient zzc zzn;

    private zze(zzae zzaeVar, zzc zzcVar) {
        super(zzaeVar);
        this.zzn = zzcVar;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0079  */
    /* JADX WARN: Code duplicated, block: B:50:0x00a9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x00ab A[Catch: IOException -> 0x00b3, TryCatch #1 {IOException -> 0x00b3, blocks: (B:51:0x00ab, B:53:0x00b2, B:52:0x00af, B:44:0x009a, B:46:0x00a0), top: B:68:0x002b }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00af A[Catch: IOException -> 0x00b3, TryCatch #1 {IOException -> 0x00b3, blocks: (B:51:0x00ab, B:53:0x00b2, B:52:0x00af, B:44:0x009a, B:46:0x00a0), top: B:68:0x002b }] */
    /* JADX WARN: Code duplicated, block: B:56:0x00b7 A[Catch: IOException -> 0x00bd, TRY_ENTER, TRY_LEAVE, TryCatch #6 {IOException -> 0x00bd, blocks: (B:3:0x0015, B:5:0x001b, B:7:0x0027, B:56:0x00b7), top: B:72:0x0015 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.google.android.gms.internal.firebase_remote_config.zzc] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    public static zze zza(zzaw zzawVar, zzac zzacVar) throws Throwable {
        String strZzae;
        zzba zzbaVarZza;
        zzc zzcVar;
        zzae zzaeVar = new zzae(zzacVar.getStatusCode(), zzacVar.getStatusMessage(), zzacVar.zzx());
        zzdt.checkNotNull(zzawVar);
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        try {
            if (!zzacVar.zzad()) {
                String contentType = zzacVar.getContentType();
                if (zzy.zzb("application/json; charset=UTF-8", contentType)) {
                    try {
                        if (zzacVar.getContent() != null) {
                            try {
                                zzbaVarZza = zzawVar.zza(zzacVar.getContent());
                                try {
                                    zzbg zzbgVarZzba = zzbaVarZza.zzba();
                                    if (zzbgVarZzba == null) {
                                        zzbgVarZzba = zzbaVarZza.zzaz();
                                    }
                                    if (zzbgVarZzba != null) {
                                        zzbaVarZza.zza(Collections.singleton("error"));
                                        if (zzbaVarZza.zzba() == zzbg.VALUE_STRING) {
                                            strZzae = zzbaVarZza.getText();
                                        } else if (zzbaVarZza.zzba() == zzbg.START_OBJECT) {
                                            zzc zzcVar2 = (zzc) zzbaVarZza.zza(zzc.class, null);
                                            try {
                                                strZzae = zzcVar2.zzaq();
                                                r1 = zzcVar2;
                                            } catch (IOException e) {
                                                zzcVar = zzcVar2;
                                                e = e;
                                                try {
                                                    zzea.zza(e);
                                                    if (zzbaVarZza == null) {
                                                        zzacVar.ignore();
                                                    } else if (zzcVar == null) {
                                                        zzbaVarZza.close();
                                                    }
                                                    strZzae = null;
                                                    r1 = zzcVar;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    if (zzbaVarZza != null) {
                                                        zzacVar.ignore();
                                                    } else if (zzcVar == null) {
                                                        zzbaVarZza.close();
                                                    }
                                                    throw th;
                                                }
                                            } catch (Throwable th2) {
                                                zzcVar = zzcVar2;
                                                th = th2;
                                                if (zzbaVarZza != null) {
                                                    zzacVar.ignore();
                                                } else if (zzcVar == null) {
                                                    zzbaVarZza.close();
                                                }
                                                throw th;
                                            }
                                        } else {
                                            strZzae = null;
                                        }
                                    } else {
                                        strZzae = null;
                                    }
                                    try {
                                        if (zzbaVarZza == null) {
                                            zzacVar.ignore();
                                        } else if (r1 == 0) {
                                            zzbaVarZza.close();
                                        }
                                    } catch (IOException e2) {
                                        e = e2;
                                        r1 = r1;
                                        zzea.zza(e);
                                    }
                                } catch (IOException e3) {
                                    e = e3;
                                    zzcVar = null;
                                } catch (Throwable th3) {
                                    th = th3;
                                    zzcVar = null;
                                }
                            } catch (IOException e4) {
                                e = e4;
                                zzbaVarZza = null;
                                zzcVar = null;
                            } catch (Throwable th4) {
                                th = th4;
                                zzbaVarZza = null;
                                zzcVar = null;
                            }
                        } else {
                            strZzae = zzacVar.zzae();
                        }
                    } catch (IOException e5) {
                        e = e5;
                        strZzae = null;
                        r1 = contentType;
                    }
                } else {
                    strZzae = zzacVar.zzae();
                }
            } else {
                strZzae = zzacVar.zzae();
            }
        } catch (IOException e6) {
            e = e6;
            strZzae = null;
        }
        StringBuilder sbZzc = zzaf.zzc(zzacVar);
        if (!zzdy.zzbc(strZzae)) {
            sbZzc.append(zzcl.zzgg);
            sbZzc.append(strZzae);
            zzaeVar.zzy(strZzae);
        }
        zzaeVar.zzx(sbZzc.toString());
        return new zze(zzaeVar, r1);
    }
}
