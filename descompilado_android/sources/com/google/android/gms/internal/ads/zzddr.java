package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzddr implements zzddv {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final InputStream zzbfe;
    private boolean zzgrw = false;
    private final JSONObject zzgrv = null;

    private zzddr(InputStream inputStream) {
        this.zzbfe = inputStream;
    }

    public static zzddv zzf(InputStream inputStream) throws IOException {
        return new zzddr(inputStream);
    }

    @Override // com.google.android.gms.internal.ads.zzddv
    public final zzdje zzapi() throws IOException {
        zzdiy zzdiyVar;
        zzdjq zzdjqVar;
        zzdiu.zzb zzbVar;
        try {
            JSONObject jSONObject = new JSONObject(new String(zzdei.zzg(this.zzbfe), UTF_8));
            if (!jSONObject.has(Constants.ParametersKeys.KEY) || jSONObject.getJSONArray(Constants.ParametersKeys.KEY).length() == 0) {
                throw new JSONException("invalid keyset");
            }
            zzdje.zzb zzbVarZzaug = zzdje.zzaug();
            if (jSONObject.has("primaryKeyId")) {
                zzbVarZzaug.zzeu(jSONObject.getInt("primaryKeyId"));
            }
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.ParametersKeys.KEY);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (!jSONObject2.has("keyData") || !jSONObject2.has("status") || !jSONObject2.has("keyId") || !jSONObject2.has("outputPrefixType")) {
                    throw new JSONException("invalid key");
                }
                zzdje.zza.C0045zza c0045zzaZzaul = zzdje.zza.zzaul();
                String string = jSONObject2.getString("status");
                if (string.equals("ENABLED")) {
                    zzdiyVar = zzdiy.ENABLED;
                } else if (string.equals("DISABLED")) {
                    zzdiyVar = zzdiy.DISABLED;
                } else {
                    String strValueOf = String.valueOf(string);
                    throw new JSONException(strValueOf.length() != 0 ? "unknown status: ".concat(strValueOf) : new String("unknown status: "));
                }
                zzdje.zza.C0045zza c0045zzaZzev = c0045zzaZzaul.zzb(zzdiyVar).zzev(jSONObject2.getInt("keyId"));
                String string2 = jSONObject2.getString("outputPrefixType");
                if (string2.equals("TINK")) {
                    zzdjqVar = zzdjq.TINK;
                } else if (string2.equals("RAW")) {
                    zzdjqVar = zzdjq.RAW;
                } else if (string2.equals("LEGACY")) {
                    zzdjqVar = zzdjq.LEGACY;
                } else if (string2.equals("CRUNCHY")) {
                    zzdjqVar = zzdjq.CRUNCHY;
                } else {
                    String strValueOf2 = String.valueOf(string2);
                    throw new JSONException(strValueOf2.length() != 0 ? "unknown output prefix type: ".concat(strValueOf2) : new String("unknown output prefix type: "));
                }
                zzdje.zza.C0045zza c0045zzaZzb = c0045zzaZzev.zzb(zzdjqVar);
                JSONObject jSONObject3 = jSONObject2.getJSONObject("keyData");
                if (!jSONObject3.has("typeUrl") || !jSONObject3.has("value") || !jSONObject3.has("keyMaterialType")) {
                    throw new JSONException("invalid keyData");
                }
                zzdiu.zza zzaVarZzbo = zzdiu.zzats().zzgw(jSONObject3.getString("typeUrl")).zzbo(zzdot.zzy(zzdlt.decode(jSONObject3.getString("value"))));
                String string3 = jSONObject3.getString("keyMaterialType");
                if (string3.equals("SYMMETRIC")) {
                    zzbVar = zzdiu.zzb.SYMMETRIC;
                } else if (string3.equals("ASYMMETRIC_PRIVATE")) {
                    zzbVar = zzdiu.zzb.ASYMMETRIC_PRIVATE;
                } else if (string3.equals("ASYMMETRIC_PUBLIC")) {
                    zzbVar = zzdiu.zzb.ASYMMETRIC_PUBLIC;
                } else if (string3.equals("REMOTE")) {
                    zzbVar = zzdiu.zzb.REMOTE;
                } else {
                    String strValueOf3 = String.valueOf(string3);
                    throw new JSONException(strValueOf3.length() != 0 ? "unknown key material type: ".concat(strValueOf3) : new String("unknown key material type: "));
                }
                zzbVarZzaug.zzb((zzdje.zza) ((zzdqd) c0045zzaZzb.zzb((zzdiu) ((zzdqd) zzaVarZzbo.zzb(zzbVar).zzazm())).zzazm()));
            }
            return (zzdje) ((zzdqd) zzbVarZzaug.zzazm());
        } catch (JSONException e) {
            throw new IOException(e);
        }
    }
}
