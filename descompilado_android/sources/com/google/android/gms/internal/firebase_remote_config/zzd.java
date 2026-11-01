package com.google.android.gms.internal.firebase_remote_config;

import com.appsflyer.share.Constants;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes2.dex */
public class zzd {
    private static final Logger logger = Logger.getLogger(zzd.class.getName());
    private final zzaa zze;
    private final zzi zzf;
    private final String zzg;
    private final String zzh;
    private final String zzi;
    private final String zzj;
    private final zzci zzk;
    private final boolean zzl;
    private final boolean zzm;

    protected zzd(zza zzaVar) {
        this.zzf = zzaVar.zzf;
        this.zzg = zza(zzaVar.zzg);
        this.zzh = zzb(zzaVar.zzh);
        this.zzi = zzaVar.zzi;
        if (zzdy.zzbc(null)) {
            logger.logp(Level.WARNING, "com.google.api.client.googleapis.services.AbstractGoogleClient", "<init>", "Application name is not set. Call Builder#setApplicationName.");
        }
        this.zzj = null;
        this.zze = zzaVar.zzy == null ? zzaVar.zzx.zza(null) : zzaVar.zzx.zza(zzaVar.zzy);
        this.zzk = zzaVar.zzk;
        this.zzl = false;
        this.zzm = false;
    }

    public static abstract class zza {
        zzi zzf;
        String zzg;
        String zzh;
        String zzi;
        final zzci zzk;
        final zzag zzx;
        zzad zzy;

        protected zza(zzag zzagVar, String str, String str2, zzci zzciVar, zzad zzadVar) {
            this.zzx = (zzag) zzdt.checkNotNull(zzagVar);
            this.zzk = zzciVar;
            zzc(str);
            zzd(str2);
            this.zzy = zzadVar;
        }

        public zza zzc(String str) {
            this.zzg = zzd.zza(str);
            return this;
        }

        public zza zzd(String str) {
            this.zzh = zzd.zzb(str);
            return this;
        }

        public zza zze(String str) {
            this.zzi = str;
            return this;
        }

        public zza zza(zzi zziVar) {
            this.zzf = zziVar;
            return this;
        }
    }

    public final String zzc() {
        String strValueOf = String.valueOf(this.zzg);
        String strValueOf2 = String.valueOf(this.zzh);
        return strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf);
    }

    public final zzaa zzd() {
        return this.zze;
    }

    public zzci zze() {
        return this.zzk;
    }

    protected void zza(zzf<?> zzfVar) throws IOException {
        zzi zziVar = this.zzf;
        if (zziVar != null) {
            zziVar.zza(zzfVar);
        }
    }

    static String zza(String str) {
        if (str != null) {
            return !str.endsWith(Constants.URL_PATH_DELIMITER) ? String.valueOf(str).concat(Constants.URL_PATH_DELIMITER) : str;
        }
        throw new NullPointerException("root URL cannot be null.");
    }

    static String zzb(String str) {
        if (str == null) {
            throw new NullPointerException("service path cannot be null");
        }
        if (str.length() == 1) {
            if (Constants.URL_PATH_DELIMITER.equals(str)) {
                return "";
            }
            throw new IllegalArgumentException("service path must equal \"/\" if it is of length 1.");
        }
        if (str.length() <= 0) {
            return str;
        }
        if (!str.endsWith(Constants.URL_PATH_DELIMITER)) {
            str = String.valueOf(str).concat(Constants.URL_PATH_DELIMITER);
        }
        return str.startsWith(Constants.URL_PATH_DELIMITER) ? str.substring(1) : str;
    }
}
