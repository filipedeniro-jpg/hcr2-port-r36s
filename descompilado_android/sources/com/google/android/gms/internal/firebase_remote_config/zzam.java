package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzam implements zzci {
    public static final String MEDIA_TYPE = new zzy(HttpRequest.CONTENT_TYPE_FORM).zza(zzbo.UTF_8).zzp();

    public static void zze(String str, Object obj) {
        if (str == null) {
            return;
        }
        try {
            StringReader stringReader = new StringReader(str);
            Class<?> cls = obj.getClass();
            zzbr zzbrVarZzc = zzbr.zzc(cls);
            List listAsList = Arrays.asList(cls);
            zzby zzbyVar = zzby.class.isAssignableFrom(cls) ? (zzby) obj : null;
            Map map = Map.class.isAssignableFrom(cls) ? (Map) obj : null;
            zzbn zzbnVar = new zzbn(obj);
            StringWriter stringWriter = new StringWriter();
            StringWriter stringWriter2 = new StringWriter();
            StringWriter stringWriter3 = stringWriter;
            boolean z = true;
            while (true) {
                int i = stringReader.read();
                if (i == -1 || i == 38) {
                    String strZzai = zzcs.zzai(stringWriter3.toString());
                    if (strZzai.length() != 0) {
                        String strZzai2 = zzcs.zzai(stringWriter2.toString());
                        zzbz zzbzVarZzae = zzbrVarZzc.zzae(strZzai);
                        if (zzbzVarZzae != null) {
                            Type typeZza = zzbt.zza((List<Type>) listAsList, zzbzVarZzae.getGenericType());
                            if (zzco.zzc(typeZza)) {
                                Class<?> clsZzb = zzco.zzb(listAsList, zzco.zzd(typeZza));
                                zzbnVar.zza(zzbzVarZzae.zzbz(), clsZzb, zza(clsZzb, (List<Type>) listAsList, strZzai2));
                            } else if (zzco.zza(zzco.zzb(listAsList, typeZza), (Class<?>) Iterable.class)) {
                                Collection<Object> collectionZzb = (Collection) zzbzVarZzae.zzh(obj);
                                if (collectionZzb == null) {
                                    collectionZzb = zzbt.zzb(typeZza);
                                    zzbzVarZzae.zzb(obj, collectionZzb);
                                }
                                collectionZzb.add(zza(typeZza == Object.class ? null : zzco.zze(typeZza), (List<Type>) listAsList, strZzai2));
                            } else {
                                zzbzVarZzae.zzb(obj, zza(typeZza, (List<Type>) listAsList, strZzai2));
                            }
                        } else if (map != null) {
                            ArrayList arrayList = (ArrayList) map.get(strZzai);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                if (zzbyVar != null) {
                                    zzbyVar.zzb(strZzai, arrayList);
                                } else {
                                    map.put(strZzai, arrayList);
                                }
                            }
                            arrayList.add(strZzai2);
                        }
                    }
                    StringWriter stringWriter4 = new StringWriter();
                    StringWriter stringWriter5 = new StringWriter();
                    if (i == -1) {
                        zzbnVar.zzbu();
                        return;
                    } else {
                        stringWriter3 = stringWriter4;
                        stringWriter2 = stringWriter5;
                        z = true;
                    }
                } else if (i != 61) {
                    if (z) {
                        stringWriter3.write(i);
                    } else {
                        stringWriter2.write(i);
                    }
                } else if (z) {
                    z = false;
                } else {
                    stringWriter2.write(i);
                }
            }
        } catch (IOException e) {
            throw zzeb.zzb(e);
        }
    }

    private static Object zza(Type type, List<Type> list, String str) {
        return zzbt.zza(zzbt.zza(list, type), str);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzci
    public final <T> T zza(InputStream inputStream, Charset charset, Class<T> cls) throws IOException {
        throw new NoSuchMethodError();
    }
}
