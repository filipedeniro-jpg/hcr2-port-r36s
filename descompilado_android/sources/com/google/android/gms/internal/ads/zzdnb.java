package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdna;
import com.google.android.gms.security.ProviderInstaller;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.Mac;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdnb<T_WRAPPER extends zzdna<T_ENGINE>, T_ENGINE> {
    private static final Logger logger = Logger.getLogger(zzdnb.class.getName());
    private static final List<Provider> zzhdk;
    public static final zzdnb<zzdnd, Cipher> zzhdl;
    public static final zzdnb<zzdnh, Mac> zzhdm;
    public static final zzdnb<zzdnj, Signature> zzhdn;
    public static final zzdnb<zzdng, MessageDigest> zzhdo;
    public static final zzdnb<zzdnc, KeyAgreement> zzhdp;
    public static final zzdnb<zzdne, KeyPairGenerator> zzhdq;
    public static final zzdnb<zzdnf, KeyFactory> zzhdr;
    private T_WRAPPER zzhds;
    private List<Provider> zzhdt = zzhdk;
    private boolean zzhdu = true;

    private zzdnb(T_WRAPPER t_wrapper) {
        this.zzhds = t_wrapper;
    }

    public final T_ENGINE zzhf(String str) throws GeneralSecurityException {
        Iterator<Provider> it = this.zzhdt.iterator();
        Exception exc = null;
        while (it.hasNext()) {
            try {
                return (T_ENGINE) this.zzhds.zza(str, it.next());
            } catch (Exception e) {
                if (exc == null) {
                    exc = e;
                }
            }
        }
        if (this.zzhdu) {
            return (T_ENGINE) this.zzhds.zza(str, null);
        }
        throw new GeneralSecurityException("No good Provider found.", exc);
    }

    static {
        if (zzdnz.zzawy()) {
            String[] strArr = {ProviderInstaller.PROVIDER_NAME, "AndroidOpenSSL"};
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                String str = strArr[i];
                Provider provider = Security.getProvider(str);
                if (provider != null) {
                    arrayList.add(provider);
                } else {
                    logger.logp(Level.INFO, "com.google.crypto.tink.subtle.EngineFactory", "toProviderList", String.format("Provider %s not available", str));
                }
            }
            zzhdk = arrayList;
        } else {
            zzhdk = new ArrayList();
        }
        zzhdl = new zzdnb<>(new zzdnd());
        zzhdm = new zzdnb<>(new zzdnh());
        zzhdn = new zzdnb<>(new zzdnj());
        zzhdo = new zzdnb<>(new zzdng());
        zzhdp = new zzdnb<>(new zzdnc());
        zzhdq = new zzdnb<>(new zzdne());
        zzhdr = new zzdnb<>(new zzdnf());
    }
}
