package com.google.android.gms.internal.firebase_remote_config;

import com.appsflyer.share.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public final class zzal {
    static final Map<Character, zzak> zzcr = new HashMap();

    private static Map<String, Object> zzb(Object obj) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry<String, Object> entry : zzbt.zzf(obj).entrySet()) {
            Object value = entry.getValue();
            if (value != null && !zzbt.isNull(value)) {
                linkedHashMap.put(entry.getKey(), value);
            }
        }
        return linkedHashMap;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x006a  */
    /* JADX WARN: Code duplicated, block: B:23:0x007c  */
    /* JADX WARN: Code duplicated, block: B:25:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:29:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:31:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:32:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:35:0x00df  */
    /* JADX WARN: Code duplicated, block: B:39:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:40:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:43:0x0101  */
    /* JADX WARN: Code duplicated, block: B:44:0x0109  */
    /* JADX WARN: Code duplicated, block: B:84:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:90:0x0073 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x00eb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x00bb A[SYNTHETIC] */
    public static String zza(String str, String str2, Object obj, boolean z) {
        String strConcat;
        Map<String, Object> mapZzb;
        StringBuilder sb;
        int length;
        int i;
        int iIndexOf;
        zzak zzakVar;
        ListIterator<String> listIterator;
        boolean z2;
        boolean zEndsWith;
        int iZzan;
        int length2;
        String strSubstring;
        Object objRemove;
        Object objZza;
        if (str2.startsWith(Constants.URL_PATH_DELIMITER)) {
            zzt zztVar = new zzt(str);
            zztVar.zzl(null);
            String strValueOf = String.valueOf(zztVar.zzp());
            String strValueOf2 = String.valueOf(str2);
            if (strValueOf2.length() != 0) {
                strConcat = strValueOf.concat(strValueOf2);
            } else {
                str2 = new String(strValueOf);
            }
            mapZzb = zzb(obj);
            sb = new StringBuilder();
            length = strConcat.length();
            i = 0;
            while (i < length) {
                iIndexOf = strConcat.indexOf(123, i);
                if (iIndexOf == -1) {
                    sb.append(strConcat.substring(i));
                    break;
                }
                sb.append(strConcat.substring(i, iIndexOf));
                int iIndexOf2 = strConcat.indexOf(125, iIndexOf + 2);
                int i2 = iIndexOf2 + 1;
                String strSubstring2 = strConcat.substring(iIndexOf + 1, iIndexOf2);
                zzakVar = zzcr.get(Character.valueOf(strSubstring2.charAt(0)));
                if (zzakVar == null) {
                    zzakVar = zzak.SIMPLE;
                }
                listIterator = zzds.zza(zzdj.zza(',')).zza(strSubstring2).listIterator();
                z2 = true;
                while (listIterator.hasNext()) {
                    String next = listIterator.next();
                    zEndsWith = next.endsWith("*");
                    if (listIterator.nextIndex() == 1) {
                        iZzan = zzakVar.zzan();
                    } else {
                        iZzan = 0;
                    }
                    length2 = next.length();
                    if (zEndsWith) {
                        length2--;
                    }
                    strSubstring = next.substring(iZzan, length2);
                    objRemove = mapZzb.remove(strSubstring);
                    if (objRemove == null) {
                        if (!z2) {
                            sb.append(zzakVar.zzal());
                        } else {
                            sb.append(zzakVar.zzak());
                            z2 = false;
                        }
                        if (objRemove instanceof Iterator) {
                            objZza = zza(strSubstring, (Iterator<?>) objRemove, zEndsWith, zzakVar);
                        } else if (!(objRemove instanceof Iterable) || objRemove.getClass().isArray()) {
                            objZza = zza(strSubstring, (Iterator<?>) zzco.zzi(objRemove).iterator(), zEndsWith, zzakVar);
                        } else if (objRemove.getClass().isEnum()) {
                            if (zzbz.zza((Enum<?>) objRemove).getName() != null) {
                                if (zzakVar.zzam()) {
                                    objRemove = String.format("%s=%s", strSubstring, objRemove);
                                }
                                objRemove = zzcs.zzaj(objRemove.toString());
                            }
                            objZza = objRemove;
                        } else if (!zzbt.zzg(objRemove)) {
                            Map<String, Object> mapZzb2 = zzb(objRemove);
                            if (mapZzb2.isEmpty()) {
                                objZza = "";
                            } else {
                                StringBuilder sb2 = new StringBuilder();
                                String str3 = com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL;
                                String strZzal = ",";
                                if (zEndsWith) {
                                    strZzal = zzakVar.zzal();
                                } else {
                                    if (zzakVar.zzam()) {
                                        sb2.append(zzcs.zzaj(strSubstring));
                                        sb2.append(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
                                    }
                                    str3 = ",";
                                }
                                Iterator<Map.Entry<String, Object>> it = mapZzb2.entrySet().iterator();
                                while (it.hasNext()) {
                                    Map.Entry<String, Object> next2 = it.next();
                                    String strZzaa = zzakVar.zzaa(next2.getKey());
                                    String strZzaa2 = zzakVar.zzaa(next2.getValue().toString());
                                    sb2.append(strZzaa);
                                    sb2.append(str3);
                                    sb2.append(strZzaa2);
                                    if (it.hasNext()) {
                                        sb2.append(strZzal);
                                    }
                                }
                                objZza = sb2.toString();
                            }
                        } else {
                            if (zzakVar.zzam()) {
                                objRemove = String.format("%s=%s", strSubstring, objRemove);
                            }
                            if (zzakVar.zzao()) {
                                objZza = zzcs.zzak(objRemove.toString());
                            } else {
                                objZza = zzcs.zzaj(objRemove.toString());
                            }
                        }
                        sb.append(objZza);
                    }
                }
                i = i2;
            }
            zzt.zza(mapZzb.entrySet(), sb);
            return sb.toString();
        }
        if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
            String strValueOf3 = String.valueOf(str);
            String strValueOf4 = String.valueOf(str2);
            str2 = strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3);
        }
        strConcat = str2;
        mapZzb = zzb(obj);
        sb = new StringBuilder();
        length = strConcat.length();
        i = 0;
        while (i < length) {
            iIndexOf = strConcat.indexOf(123, i);
            if (iIndexOf == -1) {
                sb.append(strConcat.substring(i));
                break;
            }
            sb.append(strConcat.substring(i, iIndexOf));
            int iIndexOf3 = strConcat.indexOf(125, iIndexOf + 2);
            int i3 = iIndexOf3 + 1;
            String strSubstring3 = strConcat.substring(iIndexOf + 1, iIndexOf3);
            zzakVar = zzcr.get(Character.valueOf(strSubstring3.charAt(0)));
            if (zzakVar == null) {
                zzakVar = zzak.SIMPLE;
            }
            listIterator = zzds.zza(zzdj.zza(',')).zza(strSubstring3).listIterator();
            z2 = true;
            while (listIterator.hasNext()) {
                String next3 = listIterator.next();
                zEndsWith = next3.endsWith("*");
                if (listIterator.nextIndex() == 1) {
                    iZzan = zzakVar.zzan();
                } else {
                    iZzan = 0;
                }
                length2 = next3.length();
                if (zEndsWith) {
                    length2--;
                }
                strSubstring = next3.substring(iZzan, length2);
                objRemove = mapZzb.remove(strSubstring);
                if (objRemove == null) {
                    if (!z2) {
                        sb.append(zzakVar.zzal());
                    } else {
                        sb.append(zzakVar.zzak());
                        z2 = false;
                    }
                    if (objRemove instanceof Iterator) {
                        objZza = zza(strSubstring, (Iterator<?>) objRemove, zEndsWith, zzakVar);
                    } else if (!(objRemove instanceof Iterable)) {
                        objZza = zza(strSubstring, (Iterator<?>) zzco.zzi(objRemove).iterator(), zEndsWith, zzakVar);
                    } else {
                        objZza = zza(strSubstring, (Iterator<?>) zzco.zzi(objRemove).iterator(), zEndsWith, zzakVar);
                    }
                    sb.append(objZza);
                }
            }
            i = i3;
        }
        zzt.zza(mapZzb.entrySet(), sb);
        return sb.toString();
    }

    private static String zza(String str, Iterator<?> it, boolean z, zzak zzakVar) {
        String strZzal;
        if (!it.hasNext()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (z) {
            strZzal = zzakVar.zzal();
        } else {
            if (zzakVar.zzam()) {
                sb.append(zzcs.zzaj(str));
                sb.append(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
            }
            strZzal = ",";
        }
        while (it.hasNext()) {
            if (z && zzakVar.zzam()) {
                sb.append(zzcs.zzaj(str));
                sb.append(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
            }
            sb.append(zzakVar.zzaa(it.next().toString()));
            if (it.hasNext()) {
                sb.append(strZzal);
            }
        }
        return sb.toString();
    }

    static {
        zzak.values();
    }
}
