package com.google.android.gms.internal.ads;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.util.IOUtils;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzceh implements zzcwu<zzcek, zzcej> {
    private final String zzdjd;
    private final zzapb zzfur;
    private final String zzfvc;
    private final Context zzlk;

    public zzceh(Context context, String str, zzapb zzapbVar, String str2) {
        this.zzlk = context;
        this.zzfvc = str;
        this.zzfur = zzapbVar;
        this.zzdjd = str2;
    }

    private final zzcej zza(String str, zzaoq zzaoqVar, JSONObject jSONObject, String str2) throws zzcdu {
        byte[] bytes;
        InputStreamReader inputStreamReader;
        BufferedOutputStream bufferedOutputStream;
        String str3 = "doritos_v2";
        String str4 = "doritos";
        String str5 = "";
        try {
            boolean z = false;
            if (zzaoqVar.getErrorCode() != -2) {
                if (zzaoqVar.getErrorCode() == 1) {
                    if (zzaoqVar.zzsz() != null) {
                        zzatm.zzes(TextUtils.join(", ", zzaoqVar.zzsz()));
                    }
                    throw new zzcdu("Error building request URL.", zzaoqVar.getErrorCode());
                }
                throw new zzcdu("Internal error.", 0);
            }
            zzcej zzcejVar = new zzcej();
            String strValueOf = String.valueOf(this.zzfvc);
            zzatm.zzet(strValueOf.length() != 0 ? "SDK version: ".concat(strValueOf) : new String("SDK version: "));
            String strValueOf2 = String.valueOf(str);
            zzatm.zzdv(strValueOf2.length() != 0 ? "AdRequestServiceImpl: Sending request: ".concat(strValueOf2) : new String("AdRequestServiceImpl: Sending request: "));
            URL url = new URL(str);
            HashMap map = new HashMap();
            long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
            int i = 0;
            while (true) {
                this.zzfur.zztg();
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    com.google.android.gms.ads.internal.zzp.zzjy().zza(this.zzlk, this.zzfvc, z, httpURLConnection);
                    if (!TextUtils.isEmpty(str2)) {
                        httpURLConnection.addRequestProperty("Cookie", str2);
                    }
                    if (zzaoqVar.zztc()) {
                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("pii");
                        if (jSONObjectOptJSONObject != null) {
                            if (!TextUtils.isEmpty(jSONObjectOptJSONObject.optString(str4, str5))) {
                                httpURLConnection.addRequestProperty("x-afma-drt-cookie", jSONObjectOptJSONObject.optString(str4, str5));
                            }
                            if (!TextUtils.isEmpty(jSONObjectOptJSONObject.optString(str3, str5))) {
                                httpURLConnection.addRequestProperty("x-afma-drt-v2-cookie", jSONObjectOptJSONObject.optString(str3, str5));
                            }
                        } else {
                            zzatm.zzdy("DSID signal does not exist.");
                        }
                    }
                    if (zzaoqVar == null || TextUtils.isEmpty(zzaoqVar.zztb())) {
                        bytes = null;
                    } else {
                        httpURLConnection.setDoOutput(true);
                        bytes = zzaoqVar.zztb().getBytes();
                        httpURLConnection.setFixedLengthStreamingMode(bytes.length);
                        try {
                            bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                            try {
                                bufferedOutputStream.write(bytes);
                                IOUtils.closeQuietly(bufferedOutputStream);
                            } catch (Throwable th) {
                                th = th;
                                IOUtils.closeQuietly(bufferedOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedOutputStream = null;
                        }
                    }
                    zzawi zzawiVar = new zzawi();
                    zzawiVar.zza(httpURLConnection, bytes);
                    int responseCode = httpURLConnection.getResponseCode();
                    for (Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
                        String str6 = str3;
                        String key = entry.getKey();
                        String str7 = str4;
                        List<String> value = entry.getValue();
                        if (map.containsKey(key)) {
                            ((List) map.get(key)).addAll(value);
                        } else {
                            map.put(key, new ArrayList(value));
                            str5 = str5;
                        }
                        str4 = str7;
                        str3 = str6;
                    }
                    String str8 = str3;
                    String str9 = str4;
                    String str10 = str5;
                    zzawiVar.zza(httpURLConnection, responseCode);
                    if (responseCode >= 200 && responseCode < 300) {
                        try {
                            inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
                            try {
                                com.google.android.gms.ads.internal.zzp.zzjy();
                                String strZza = zzatv.zza(inputStreamReader);
                                IOUtils.closeQuietly(inputStreamReader);
                                zzawiVar.zzep(strZza);
                                zzcejVar.zzfvg = responseCode;
                                zzcejVar.zzdkc = strZza;
                                zzcejVar.zzab = map;
                                if (TextUtils.isEmpty(strZza)) {
                                    if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcrf)).booleanValue()) {
                                        throw new zzcdu("No Fill", 3);
                                    }
                                }
                                zzcejVar.zzfvh = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - jElapsedRealtime;
                                httpURLConnection.disconnect();
                                this.zzfur.zzth();
                                return zzcejVar;
                            } catch (Throwable th3) {
                                th = th3;
                                IOUtils.closeQuietly(inputStreamReader);
                                throw th;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            inputStreamReader = null;
                        }
                    } else if (responseCode >= 300 && responseCode < 400) {
                        String headerField = httpURLConnection.getHeaderField("Location");
                        if (TextUtils.isEmpty(headerField)) {
                            zzatm.zzeu("No location header to follow redirect.");
                            throw new zzcdu("No location header to follow redirect");
                        }
                        URL url2 = new URL(headerField);
                        i++;
                        if (i > ((Integer) zzuo.zzoj().zzd(zzyt.zzcql)).intValue()) {
                            zzatm.zzeu("Too many redirects.");
                            throw new zzcdu("Too many redirects");
                        }
                        httpURLConnection.disconnect();
                        this.zzfur.zzth();
                        url = url2;
                        str5 = str10;
                        str4 = str9;
                        str3 = str8;
                        z = false;
                    } else {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append("Received error HTTP response code: ");
                        sb.append(responseCode);
                        zzatm.zzeu(sb.toString());
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Received error HTTP response code: ");
                        sb2.append(responseCode);
                        throw new zzcdu(sb2.toString());
                    }
                } catch (Throwable th5) {
                    httpURLConnection.disconnect();
                    this.zzfur.zzth();
                    throw th5;
                }
            }
        } catch (IOException e) {
            String strValueOf3 = String.valueOf(e.getMessage());
            zzatm.zzeu(strValueOf3.length() != 0 ? "Error while connecting to ad server: ".concat(strValueOf3) : new String("Error while connecting to ad server: "));
            String strValueOf4 = String.valueOf(e.getMessage());
            throw new zzcdu(strValueOf4.length() != 0 ? "Error connecting to ad server:".concat(strValueOf4) : new String("Error connecting to ad server:"), 2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzcwu
    public final /* synthetic */ zzcej apply(zzcek zzcekVar) throws Exception {
        zzcek zzcekVar2 = zzcekVar;
        return zza(zzcekVar2.zzfva.getUrl(), zzcekVar2.zzfva, zzcekVar2.zzfuz, this.zzdjd);
    }
}
