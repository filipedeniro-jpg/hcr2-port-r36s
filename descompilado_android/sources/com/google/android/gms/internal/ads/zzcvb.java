package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvb {
    public final String zzaew;
    public final boolean zzblg;
    public final String zzdbn;
    public final String zzdby;
    public final String zzdbz;
    public final List<String> zzdci;
    public final List<String> zzdcj;
    public final String zzdco;
    public final boolean zzdcv;
    public final boolean zzdcw;
    public final boolean zzdcx;
    public final String zzdek;
    public final List<String> zzdkg;
    public final String zzdkj;
    public final String zzdkm;

    @Nullable
    public final zzaqd zzdks;
    public final List<String> zzdkt;
    public final List<String> zzdku;
    public final boolean zzdlc;
    public final boolean zzdlf;
    public final boolean zzdlg;
    public final boolean zzdlx;
    public final boolean zzefr;
    public final String zzehw;
    public final int zzfcq;
    public final int zzfif;
    public final List<String> zzgiq;
    public final int zzgir;
    public final List<String> zzgis;
    public final List<String> zzgit;
    public final List<String> zzgiu;
    public final List<zzcva> zzgiv;

    @Nullable
    public final zzcvf zzgiw;
    public final List<String> zzgix;
    public final List<zzcva> zzgiy;
    public final JSONObject zzgiz;
    public final zzarn zzgja;
    public final JSONObject zzgjb;
    public final JSONObject zzgjc;
    public final boolean zzgjd;
    public final int zzgje;
    public final int zzgjf;
    public final JSONObject zzgjg;
    public final int zzgjh;

    /* JADX WARN: Code duplicated, block: B:147:0x038d A[PHI: r57
  0x038d: PHI (r57v47 java.util.List<java.lang.String>) = 
  (r57v1 java.util.List<java.lang.String>)
  (r57v2 java.util.List<java.lang.String>)
  (r57v3 java.util.List<java.lang.String>)
  (r57v4 java.util.List<java.lang.String>)
  (r57v5 java.util.List<java.lang.String>)
  (r57v6 java.util.List<java.lang.String>)
  (r57v7 java.util.List<java.lang.String>)
  (r57v8 java.util.List<java.lang.String>)
  (r57v9 java.util.List<java.lang.String>)
  (r57v10 java.util.List<java.lang.String>)
  (r57v11 java.util.List<java.lang.String>)
  (r57v12 java.util.List<java.lang.String>)
  (r57v13 java.util.List<java.lang.String>)
  (r57v14 java.util.List<java.lang.String>)
  (r57v15 java.util.List<java.lang.String>)
  (r57v16 java.util.List<java.lang.String>)
  (r57v17 java.util.List<java.lang.String>)
  (r57v18 java.util.List<java.lang.String>)
  (r57v19 java.util.List<java.lang.String>)
  (r57v20 java.util.List<java.lang.String>)
  (r57v21 java.util.List<java.lang.String>)
  (r57v22 java.util.List<java.lang.String>)
  (r57v23 java.util.List<java.lang.String>)
  (r57v24 java.util.List<java.lang.String>)
  (r57v25 java.util.List<java.lang.String>)
  (r57v26 java.util.List<java.lang.String>)
  (r57v27 java.util.List<java.lang.String>)
  (r57v28 java.util.List<java.lang.String>)
  (r57v29 java.util.List<java.lang.String>)
  (r57v30 java.util.List<java.lang.String>)
  (r57v31 java.util.List<java.lang.String>)
  (r57v32 java.util.List<java.lang.String>)
  (r57v33 java.util.List<java.lang.String>)
  (r57v34 java.util.List<java.lang.String>)
  (r57v35 java.util.List<java.lang.String>)
  (r57v36 java.util.List<java.lang.String>)
  (r57v37 java.util.List<java.lang.String>)
  (r57v38 java.util.List<java.lang.String>)
  (r57v39 java.util.List<java.lang.String>)
  (r57v40 java.util.List<java.lang.String>)
  (r57v41 java.util.List<java.lang.String>)
  (r57v42 java.util.List<java.lang.String>)
  (r57v43 java.util.List<java.lang.String>)
  (r57v44 java.util.List<java.lang.String>)
  (r57v45 java.util.List<java.lang.String>)
  (r57v48 java.util.List<java.lang.String>)
 binds: [B:145:0x0388, B:142:0x0379, B:139:0x036a, B:136:0x035b, B:133:0x034c, B:130:0x033d, B:127:0x032e, B:124:0x031e, B:121:0x030e, B:118:0x02fe, B:115:0x02ee, B:112:0x02df, B:109:0x02cf, B:106:0x02bf, B:103:0x02af, B:100:0x029f, B:97:0x028f, B:94:0x027f, B:91:0x0270, B:88:0x0260, B:85:0x0251, B:82:0x0241, B:79:0x0231, B:76:0x0221, B:73:0x0211, B:70:0x0201, B:67:0x01f1, B:64:0x01e1, B:61:0x01d1, B:58:0x01c1, B:55:0x01b2, B:52:0x01a2, B:49:0x0192, B:46:0x0183, B:43:0x0173, B:40:0x0164, B:37:0x0154, B:34:0x0144, B:31:0x0134, B:28:0x0124, B:25:0x0115, B:22:0x0105, B:19:0x00f6, B:16:0x00e6, B:13:0x00d6, B:11:0x00c8] A[DONT_GENERATE, DONT_INLINE]] */
    zzcvb(JsonReader jsonReader) throws IllegalStateException, JSONException, IOException, NumberFormatException {
        List<String> list;
        byte b;
        List<String> listEmptyList = Collections.emptyList();
        List<String> listEmptyList2 = Collections.emptyList();
        List<String> listEmptyList3 = Collections.emptyList();
        List<String> listEmptyList4 = Collections.emptyList();
        List<String> listEmptyList5 = Collections.emptyList();
        Collections.emptyList();
        List<String> listEmptyList6 = Collections.emptyList();
        List<String> listEmptyList7 = Collections.emptyList();
        List<String> listEmptyList8 = Collections.emptyList();
        List<String> listEmptyList9 = Collections.emptyList();
        List<zzcva> listEmptyList10 = Collections.emptyList();
        List<String> listEmptyList11 = Collections.emptyList();
        List<zzcva> listEmptyList12 = Collections.emptyList();
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject();
        jsonReader.beginObject();
        List<String> listZza = listEmptyList11;
        List<zzcva> listZze = listEmptyList12;
        JSONObject jSONObjectZzc = jSONObject;
        JSONObject jSONObjectZzc2 = jSONObject2;
        JSONObject jSONObjectZzc3 = jSONObject3;
        JSONObject jSONObjectZzc4 = jSONObject4;
        zzaqd zzaqdVarZza = null;
        zzcvf zzcvfVar = null;
        zzarn zzarnVarZzg = null;
        String strNextString = "";
        String strNextString2 = strNextString;
        String strNextString3 = strNextString2;
        String strNextString4 = strNextString3;
        String string = strNextString4;
        String strNextString5 = string;
        String strNextString6 = strNextString5;
        String strNextString7 = strNextString6;
        boolean zNextBoolean = false;
        boolean zNextBoolean2 = false;
        boolean zNextBoolean3 = false;
        boolean zNextBoolean4 = false;
        boolean zNextBoolean5 = false;
        boolean zNextBoolean6 = false;
        int i = -1;
        int iNextInt = 0;
        boolean zNextBoolean7 = false;
        boolean zNextBoolean8 = false;
        boolean zNextBoolean9 = false;
        int iNextInt2 = 0;
        boolean zNextBoolean10 = false;
        int iNextInt3 = -1;
        List<String> listZza2 = listEmptyList8;
        List<String> listZza3 = listEmptyList9;
        List<zzcva> listZze2 = listEmptyList10;
        String strNextString8 = strNextString7;
        List<String> listZza4 = listEmptyList6;
        List<String> listZza5 = listEmptyList7;
        int iNextInt4 = 0;
        List<String> listZza6 = listEmptyList5;
        List<String> listZza7 = listEmptyList4;
        List<String> listZza8 = listEmptyList3;
        List<String> listZza9 = listEmptyList2;
        int i2 = 0;
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            String str = strNextName == null ? "" : strNextName;
            List<zzcva> list2 = listZze2;
            switch (str.hashCode()) {
                case -1980587809:
                    list = listZza3;
                    if (!str.equals("debug_signals")) {
                        b = -1;
                    } else {
                        b = 26;
                    }
                    break;
                case -1965512151:
                    list = listZza3;
                    if (!str.equals("omid_settings")) {
                        b = -1;
                    } else {
                        b = 38;
                    }
                    break;
                case -1440104884:
                    list = listZza3;
                    if (!str.equals("is_custom_close_blocked")) {
                        b = -1;
                    } else {
                        b = 32;
                    }
                    break;
                case -1439500848:
                    list = listZza3;
                    if (!str.equals("orientation")) {
                        b = -1;
                    } else {
                        b = 34;
                    }
                    break;
                case -1428969291:
                    list = listZza3;
                    if (!str.equals("enable_omid")) {
                        b = -1;
                    } else {
                        b = 36;
                    }
                    break;
                case -1403779768:
                    list = listZza3;
                    if (!str.equals("showable_impression_type")) {
                        b = -1;
                    } else {
                        b = 41;
                    }
                    break;
                case -1360811658:
                    list = listZza3;
                    if (!str.equals("ad_sizes")) {
                        b = -1;
                    } else {
                        b = 17;
                    }
                    break;
                case -1306015996:
                    list = listZza3;
                    if (!str.equals("adapters")) {
                        b = -1;
                    } else {
                        b = 18;
                    }
                    break;
                case -1289032093:
                    list = listZza3;
                    if (!str.equals("extras")) {
                        b = -1;
                    } else {
                        b = 27;
                    }
                    break;
                case -1234181075:
                    list = listZza3;
                    if (!str.equals("allow_pub_rendered_attribution")) {
                        b = -1;
                    } else {
                        b = 28;
                    }
                    break;
                case -1181000426:
                    list = listZza3;
                    if (!str.equals("is_augmented_reality_ad")) {
                        b = -1;
                    } else {
                        b = 42;
                    }
                    break;
                case -1152230954:
                    list = listZza3;
                    if (!str.equals(AppEventsConstants.EVENT_PARAM_AD_TYPE)) {
                        b = -1;
                    } else {
                        b = 1;
                    }
                    break;
                case -1146534047:
                    list = listZza3;
                    if (!str.equals("is_scroll_aware")) {
                        b = -1;
                    } else {
                        b = 40;
                    }
                    break;
                case -1115838944:
                    list = listZza3;
                    if (!str.equals("fill_urls")) {
                        b = -1;
                    } else {
                        b = 13;
                    }
                    break;
                case -1081936678:
                    list = listZza3;
                    if (!str.equals("allocation_id")) {
                        b = -1;
                    } else {
                        b = 19;
                    }
                    break;
                case -1078050970:
                    list = listZza3;
                    if (!str.equals("video_complete_urls")) {
                        b = -1;
                    } else {
                        b = 9;
                    }
                    break;
                case -1051269058:
                    list = listZza3;
                    if (!str.equals("active_view")) {
                        b = -1;
                    } else {
                        b = 23;
                    }
                    break;
                case -982608540:
                    list = listZza3;
                    if (!str.equals("valid_from_timestamp")) {
                        b = -1;
                    } else {
                        b = 11;
                    }
                    break;
                case -776859333:
                    list = listZza3;
                    if (!str.equals("click_urls")) {
                        b = -1;
                    } else {
                        b = 2;
                    }
                    break;
                case -544216775:
                    list = listZza3;
                    if (!str.equals("safe_browsing")) {
                        b = -1;
                    } else {
                        b = 24;
                    }
                    break;
                case -437057161:
                    list = listZza3;
                    if (!str.equals("imp_urls")) {
                        b = -1;
                    } else {
                        b = 3;
                    }
                    break;
                case -404326515:
                    list = listZza3;
                    if (!str.equals("render_timeout_ms")) {
                        b = -1;
                    } else {
                        b = 35;
                    }
                    break;
                case -397704715:
                    list = listZza3;
                    if (!str.equals("ad_close_time_ms")) {
                        b = -1;
                    } else {
                        b = 43;
                    }
                    break;
                case -213424028:
                    list = listZza3;
                    if (!str.equals("watermark")) {
                        b = -1;
                    } else {
                        b = 44;
                    }
                    break;
                case -29338502:
                    list = listZza3;
                    if (!str.equals("allow_custom_click_gesture")) {
                        b = -1;
                    } else {
                        b = 30;
                    }
                    break;
                case 3107:
                    list = listZza3;
                    if (!str.equals("ad")) {
                        b = -1;
                    } else {
                        b = 16;
                    }
                    break;
                case 3355:
                    list = listZza3;
                    if (!str.equals("id")) {
                        b = -1;
                    } else {
                        b = 21;
                    }
                    break;
                case 3076010:
                    list = listZza3;
                    if (!str.equals("data")) {
                        b = -1;
                    } else {
                        b = 20;
                    }
                    break;
                case 63195984:
                    list = listZza3;
                    if (!str.equals("render_test_label")) {
                        b = -1;
                    } else {
                        b = 31;
                    }
                    break;
                case 107433883:
                    list = listZza3;
                    if (!str.equals("qdata")) {
                        b = -1;
                    } else {
                        b = 22;
                    }
                    break;
                case 230323073:
                    list = listZza3;
                    if (!str.equals("ad_load_urls")) {
                        b = -1;
                    } else {
                        b = 4;
                    }
                    break;
                case 418392395:
                    list = listZza3;
                    if (!str.equals("is_closable_area_disabled")) {
                        b = -1;
                    } else {
                        b = 33;
                    }
                    break;
                case 597473788:
                    list = listZza3;
                    if (!str.equals("debug_dialog_string")) {
                        b = -1;
                    } else {
                        b = 25;
                    }
                    break;
                case 673261304:
                    list = listZza3;
                    if (!str.equals("reward_granted_urls")) {
                        b = -1;
                    } else {
                        b = 7;
                    }
                    break;
                case 754887508:
                    list = listZza3;
                    if (!str.equals("container_sizes")) {
                        b = -1;
                    } else {
                        b = 15;
                    }
                    break;
                case 791122864:
                    list = listZza3;
                    if (!str.equals("impression_type")) {
                        b = -1;
                    } else {
                        b = 5;
                    }
                    break;
                case 1010584092:
                    list = listZza3;
                    if (!str.equals("transaction_id")) {
                        b = -1;
                    } else {
                        b = 10;
                    }
                    break;
                case 1100650276:
                    list = listZza3;
                    if (!str.equals("rewards")) {
                        b = -1;
                    } else {
                        b = 12;
                    }
                    break;
                case 1321720943:
                    list = listZza3;
                    if (!str.equals("allow_pub_owned_ad_view")) {
                        b = -1;
                    } else {
                        b = 29;
                    }
                    break;
                case 1637553475:
                    list = listZza3;
                    if (!str.equals("bid_response")) {
                        b = -1;
                    } else {
                        b = 37;
                    }
                    break;
                case 1638957285:
                    list = listZza3;
                    if (!str.equals("video_start_urls")) {
                        b = -1;
                    } else {
                        b = 6;
                    }
                    break;
                case 1688341040:
                    list = listZza3;
                    if (!str.equals("video_reward_urls")) {
                        b = -1;
                    } else {
                        b = 8;
                    }
                    break;
                case 1839650832:
                    list = listZza3;
                    if (!str.equals("renderers")) {
                        b = -1;
                    } else {
                        b = 0;
                    }
                    break;
                case 1875425491:
                    list = listZza3;
                    if (!str.equals("is_analytics_logging_enabled")) {
                        b = -1;
                    } else {
                        b = 39;
                    }
                    break;
                case 2072888499:
                    list = listZza3;
                    if (!str.equals("manual_tracking_urls")) {
                        b = -1;
                    } else {
                        b = 14;
                    }
                    break;
                default:
                    list = listZza3;
                    b = -1;
                    break;
            }
            switch (b) {
                case 0:
                    listEmptyList = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 1:
                    String strNextString9 = jsonReader.nextString();
                    i2 = ai.a.cW.equals(strNextString9) ? 1 : aa.e.bo.equals(strNextString9) ? 2 : "native_express".equals(strNextString9) ? 3 : AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(strNextString9) ? 4 : Constants.CONVERT_REWARDED.equals(strNextString9) ? 5 : 0;
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 2:
                    listZza9 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 3:
                    listZza8 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 4:
                    listZza7 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 5:
                    iNextInt4 = jsonReader.nextInt();
                    if (iNextInt4 != 0 && iNextInt4 != 1) {
                        iNextInt4 = 0;
                    }
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 6:
                    listZza6 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 7:
                    zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 8:
                    listZza4 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 9:
                    listZza5 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 10:
                    strNextString8 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 11:
                    strNextString = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 12:
                    zzaqdVarZza = zzaqd.zza(zzavm.zzd(jsonReader));
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 13:
                    listZza2 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 14:
                    listZza3 = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    break;
                case 15:
                    listZze2 = zzcva.zze(jsonReader);
                    listZza3 = list;
                    break;
                case 16:
                    zzcvfVar = new zzcvf(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 17:
                    listZze = zzcva.zze(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 18:
                    listZza = zzavm.zza(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 19:
                    strNextString2 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 20:
                    jSONObjectZzc = zzavm.zzc(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 21:
                    strNextString3 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 22:
                    strNextString4 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 23:
                    string = zzavm.zzc(jsonReader).toString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 24:
                    zzarnVarZzg = zzarn.zzg(zzavm.zzc(jsonReader));
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 25:
                    strNextString5 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 26:
                    jSONObjectZzc2 = zzavm.zzc(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 27:
                    jSONObjectZzc3 = zzavm.zzc(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 28:
                    zNextBoolean = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 29:
                    zNextBoolean2 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 30:
                    zNextBoolean3 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 31:
                    zNextBoolean4 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 32:
                    zNextBoolean5 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 33:
                    zNextBoolean6 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 34:
                    String strNextString10 = jsonReader.nextString();
                    if ("landscape".equalsIgnoreCase(strNextString10)) {
                        com.google.android.gms.ads.internal.zzp.zzka();
                        i = 6;
                    } else if ("portrait".equalsIgnoreCase(strNextString10)) {
                        com.google.android.gms.ads.internal.zzp.zzka();
                        i = 7;
                    } else {
                        i = -1;
                    }
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 35:
                    iNextInt = jsonReader.nextInt();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 36:
                    zNextBoolean7 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 37:
                    strNextString6 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 38:
                    jSONObjectZzc4 = zzavm.zzc(jsonReader);
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 39:
                    zNextBoolean8 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 40:
                    zNextBoolean9 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 41:
                    iNextInt2 = jsonReader.nextInt();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 42:
                    zNextBoolean10 = jsonReader.nextBoolean();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 43:
                    iNextInt3 = jsonReader.nextInt();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                case 44:
                    strNextString7 = jsonReader.nextString();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
                default:
                    jsonReader.skipValue();
                    listZze2 = list2;
                    listZza3 = list;
                    break;
            }
        }
        jsonReader.endObject();
        this.zzgiq = listEmptyList;
        this.zzfif = i2;
        this.zzdci = listZza9;
        this.zzdcj = listZza8;
        this.zzgis = listZza7;
        this.zzgir = iNextInt4;
        this.zzdkt = listZza6;
        this.zzdku = listZza4;
        this.zzgit = listZza5;
        this.zzdby = strNextString8;
        this.zzdbz = strNextString;
        this.zzdks = zzaqdVarZza;
        this.zzgiu = listZza2;
        this.zzdkg = listZza3;
        this.zzgiv = listZze2;
        this.zzgiw = zzcvfVar;
        this.zzgix = listZza;
        this.zzgiy = listZze;
        this.zzdbn = strNextString2;
        this.zzgiz = jSONObjectZzc;
        this.zzaew = strNextString3;
        this.zzdco = strNextString4;
        this.zzdkm = string;
        this.zzgja = zzarnVarZzg;
        this.zzdkj = strNextString5;
        this.zzgjb = jSONObjectZzc2;
        this.zzgjc = jSONObjectZzc3;
        this.zzdcv = zNextBoolean;
        this.zzdcw = zNextBoolean2;
        this.zzdcx = zNextBoolean3;
        this.zzdlx = zNextBoolean4;
        this.zzgjd = zNextBoolean5;
        this.zzblg = zNextBoolean6;
        this.zzgje = i;
        this.zzgjf = iNextInt;
        this.zzdlc = zNextBoolean7;
        this.zzehw = strNextString6;
        this.zzgjg = jSONObjectZzc4;
        this.zzdlf = zNextBoolean8;
        this.zzdlg = zNextBoolean9;
        this.zzgjh = iNextInt2;
        this.zzefr = zNextBoolean10;
        this.zzdek = strNextString7;
        this.zzfcq = iNextInt3;
    }
}
