package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdef {
    private static final Logger logger = Logger.getLogger(zzdef.class.getName());
    private static final ConcurrentMap<String, zza> zzgsj = new ConcurrentHashMap();
    private static final ConcurrentMap<String, Boolean> zzgsk = new ConcurrentHashMap();
    private static final ConcurrentMap<String, zzddm<?>> zzgsl = new ConcurrentHashMap();
    private static final ConcurrentMap<Class<?>, zzdee<?>> zzgsm = new ConcurrentHashMap();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    interface zza {
        zzddu<?> zzapr();

        Class<?> zzaps();

        Set<Class<?>> zzapt();

        <P> zzddu<P> zzb(Class<P> cls) throws GeneralSecurityException;
    }

    private static synchronized zza zzgs(String str) throws GeneralSecurityException {
        if (!zzgsj.containsKey(str)) {
            String strValueOf = String.valueOf(str);
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "No key manager found for key type ".concat(strValueOf) : new String("No key manager found for key type "));
        }
        return zzgsj.get(str);
    }

    public static synchronized void zza(String str, zzddm<?> zzddmVar) throws GeneralSecurityException {
        if (zzgsl.containsKey(str.toLowerCase())) {
            if (!zzddmVar.getClass().equals(zzgsl.get(str.toLowerCase()).getClass())) {
                Logger logger2 = logger;
                Level level = Level.WARNING;
                String strValueOf = String.valueOf(str);
                logger2.logp(level, "com.google.crypto.tink.Registry", "addCatalogue", strValueOf.length() != 0 ? "Attempted overwrite of a catalogueName catalogue for name ".concat(strValueOf) : new String("Attempted overwrite of a catalogueName catalogue for name "));
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 47);
                sb.append("catalogue for name ");
                sb.append(str);
                sb.append(" has been already registered");
                throw new GeneralSecurityException(sb.toString());
            }
        }
        zzgsl.put(str.toLowerCase(), zzddmVar);
    }

    public static zzddm<?> zzgt(String str) throws GeneralSecurityException {
        if (str == null) {
            throw new IllegalArgumentException("catalogueName must be non-null.");
        }
        zzddm<?> zzddmVar = zzgsl.get(str.toLowerCase());
        if (zzddmVar != null) {
            return zzddmVar;
        }
        String strConcat = String.format("no catalogue found for %s. ", str);
        if (str.toLowerCase().startsWith("tinkaead")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call AeadConfig.register().");
        }
        if (str.toLowerCase().startsWith("tinkdeterministicaead")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call DeterministicAeadConfig.register().");
        } else if (str.toLowerCase().startsWith("tinkstreamingaead")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call StreamingAeadConfig.register().");
        } else if (str.toLowerCase().startsWith("tinkhybriddecrypt") || str.toLowerCase().startsWith("tinkhybridencrypt")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call HybridConfig.register().");
        } else if (str.toLowerCase().startsWith("tinkmac")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call MacConfig.register().");
        } else if (str.toLowerCase().startsWith("tinkpublickeysign") || str.toLowerCase().startsWith("tinkpublickeyverify")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call SignatureConfig.register().");
        } else if (str.toLowerCase().startsWith("tink")) {
            strConcat = String.valueOf(strConcat).concat("Maybe call TinkConfig.register().");
        }
        throw new GeneralSecurityException(strConcat);
    }

    private static <T> T checkNotNull(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    public static synchronized <P> void zza(zzddu<P> zzdduVar) throws GeneralSecurityException {
        zza((zzddu) zzdduVar, true);
    }

    public static synchronized <P> void zza(zzddu<P> zzdduVar, boolean z) throws GeneralSecurityException {
        try {
            if (zzdduVar == null) {
                throw new IllegalArgumentException("key manager must be non-null.");
            }
            String keyType = zzdduVar.getKeyType();
            if (zzgsj.containsKey(keyType)) {
                zza zzaVar = zzgsj.get(keyType);
                boolean zBooleanValue = zzgsk.get(keyType).booleanValue();
                if (!zzdduVar.getClass().equals(zzaVar.zzaps()) || (!zBooleanValue && z)) {
                    Logger logger2 = logger;
                    Level level = Level.WARNING;
                    String strValueOf = String.valueOf(keyType);
                    logger2.logp(level, "com.google.crypto.tink.Registry", "registerKeyManager", strValueOf.length() != 0 ? "Attempted overwrite of a registered key manager for key type ".concat(strValueOf) : new String("Attempted overwrite of a registered key manager for key type "));
                    throw new GeneralSecurityException(String.format("typeUrl (%s) is already registered with %s, cannot be re-registered with %s", keyType, zzaVar.zzaps().getName(), zzdduVar.getClass().getName()));
                }
            }
            zzgsj.put(keyType, new zzdeh(zzdduVar));
            zzgsk.put(keyType, Boolean.valueOf(z));
        } catch (Throwable th) {
            throw th;
        }
    }

    public static synchronized <P> void zza(zzdee<P> zzdeeVar) throws GeneralSecurityException {
        try {
            if (zzdeeVar == null) {
                throw new IllegalArgumentException("wrapper must be non-null");
            }
            Class<P> clsZzapj = zzdeeVar.zzapj();
            if (zzgsm.containsKey(clsZzapj)) {
                zzdee<?> zzdeeVar2 = zzgsm.get(clsZzapj);
                if (!zzdeeVar.getClass().equals(zzdeeVar2.getClass())) {
                    Logger logger2 = logger;
                    Level level = Level.WARNING;
                    String strValueOf = String.valueOf(clsZzapj.toString());
                    logger2.logp(level, "com.google.crypto.tink.Registry", "registerPrimitiveWrapper", strValueOf.length() != 0 ? "Attempted overwrite of a registered SetWrapper for type ".concat(strValueOf) : new String("Attempted overwrite of a registered SetWrapper for type "));
                    throw new GeneralSecurityException(String.format("SetWrapper for primitive (%s) is already registered to be %s, cannot be re-registered with %s", clsZzapj.getName(), zzdeeVar2.getClass().getName(), zzdeeVar.getClass().getName()));
                }
            }
            zzgsm.put(clsZzapj, zzdeeVar);
        } catch (Throwable th) {
            throw th;
        }
    }

    private static zzddu<?> zzgu(String str) throws GeneralSecurityException {
        return zzgs(str).zzapr();
    }

    private static <P> zzddu<P> zza(String str, Class<P> cls) throws GeneralSecurityException {
        zza zzaVarZzgs = zzgs(str);
        if (cls == null) {
            return (zzddu<P>) zzaVarZzgs.zzapr();
        }
        if (zzaVarZzgs.zzapt().contains(cls)) {
            return zzaVarZzgs.zzb(cls);
        }
        String name = cls.getName();
        String strValueOf = String.valueOf(zzaVarZzgs.zzaps());
        Set<Class<?>> setZzapt = zzaVarZzgs.zzapt();
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (Class<?> cls2 : setZzapt) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(cls2.getCanonicalName());
            z = false;
        }
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder(String.valueOf(name).length() + 77 + String.valueOf(strValueOf).length() + String.valueOf(string).length());
        sb2.append("Primitive type ");
        sb2.append(name);
        sb2.append(" not supported by key manager of type ");
        sb2.append(strValueOf);
        sb2.append(", supported primitives: ");
        sb2.append(string);
        throw new GeneralSecurityException(sb2.toString());
    }

    public static synchronized zzdiu zza(zzdja zzdjaVar) throws GeneralSecurityException {
        zzddu<?> zzdduVarZzgu;
        zzdduVarZzgu = zzgu(zzdjaVar.zzatp());
        if (!zzgsk.get(zzdjaVar.zzatp()).booleanValue()) {
            String strValueOf = String.valueOf(zzdjaVar.zzatp());
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(strValueOf) : new String("newKey-operation not permitted for key type "));
        }
        return zzdduVarZzgu.zzr(zzdjaVar.zzatq());
    }

    public static synchronized zzdrn zzb(zzdja zzdjaVar) throws GeneralSecurityException {
        zzddu<?> zzdduVarZzgu;
        zzdduVarZzgu = zzgu(zzdjaVar.zzatp());
        if (!zzgsk.get(zzdjaVar.zzatp()).booleanValue()) {
            String strValueOf = String.valueOf(zzdjaVar.zzatp());
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(strValueOf) : new String("newKey-operation not permitted for key type "));
        }
        return zzdduVarZzgu.zzq(zzdjaVar.zzatq());
    }

    public static synchronized zzdrn zza(String str, zzdrn zzdrnVar) throws GeneralSecurityException {
        zzddu zzdduVarZza;
        zzdduVarZza = zza(str, (Class) null);
        if (!zzgsk.get(str).booleanValue()) {
            String strValueOf = String.valueOf(str);
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "newKey-operation not permitted for key type ".concat(strValueOf) : new String("newKey-operation not permitted for key type "));
        }
        return zzdduVarZza.zzb(zzdrnVar);
    }

    public static <P> P zza(String str, zzdrn zzdrnVar, Class<P> cls) throws GeneralSecurityException {
        return (P) zza(str, (Class) checkNotNull(cls)).zza(zzdrnVar);
    }

    private static <P> P zza(String str, zzdot zzdotVar, Class<P> cls) throws GeneralSecurityException {
        return (P) zza(str, cls).zzp(zzdotVar);
    }

    public static <P> P zza(String str, byte[] bArr, Class<P> cls) throws GeneralSecurityException {
        return (P) zza(str, zzdot.zzy(bArr), (Class) checkNotNull(cls));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <P> zzdec<P> zza(zzddw zzddwVar, zzddu<P> zzdduVar, Class<P> cls) throws GeneralSecurityException {
        Class cls2 = (Class) checkNotNull(cls);
        zzdei.zzd(zzddwVar.zzapl());
        zzdec<P> zzdecVar = (zzdec<P>) zzdec.zza(cls2);
        for (zzdje.zza zzaVar : zzddwVar.zzapl().zzaue()) {
            if (zzaVar.zzapn() == zzdiy.ENABLED) {
                zzdeb zzdebVarZza = zzdecVar.zza(zza(zzaVar.zzauj().zzatp(), zzaVar.zzauj().zzatq(), cls2), zzaVar);
                if (zzaVar.zzauk() == zzddwVar.zzapl().zzaud()) {
                    zzdecVar.zza(zzdebVarZza);
                }
            }
        }
        return zzdecVar;
    }

    public static <P> P zza(zzdec<P> zzdecVar) throws GeneralSecurityException {
        zzdee<?> zzdeeVar = zzgsm.get(zzdecVar.zzapj());
        if (zzdeeVar == null) {
            String strValueOf = String.valueOf(zzdecVar.zzapj().getName());
            throw new GeneralSecurityException(strValueOf.length() != 0 ? "No wrapper found for ".concat(strValueOf) : new String("No wrapper found for "));
        }
        return (P) zzdeeVar.zza(zzdecVar);
    }
}
