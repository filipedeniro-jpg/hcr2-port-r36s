package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.my.target.common.models.AudioData;
import com.my.target.common.models.ShareButtonData;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AudioBannerParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fh {

    @NonNull
    private final af F;

    @NonNull
    private final be G;

    @NonNull
    private final b adConfig;

    @NonNull
    private final Context context;

    public static fh a(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new fh(afVar, bVar, context);
    }

    private fh(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.F = afVar;
        this.adConfig = bVar;
        this.context = context;
        this.G = be.b(afVar, bVar, context);
    }

    public final boolean a(@NonNull JSONObject jSONObject, @NonNull ak<AudioData> akVar) {
        this.G.a(jSONObject, akVar);
        if (akVar.getType().equals("statistics")) {
            b(jSONObject, akVar);
            return true;
        }
        float fOptDouble = (float) jSONObject.optDouble("duration", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        if (fOptDouble <= 0.0f) {
            a(az.a.ej, "unable to set duration ".concat(String.valueOf(fOptDouble)), akVar.getId());
            return false;
        }
        akVar.setAutoPlay(jSONObject.optBoolean(bf.a.fy, akVar.isAutoPlay()));
        akVar.setHasCtaButton(jSONObject.optBoolean(bf.a.fz, akVar.isHasCtaButton()));
        akVar.setAdText(jSONObject.optString("adText", akVar.getAdText()));
        b(jSONObject, akVar);
        Boolean boolI = this.F.I();
        if (boolI != null) {
            akVar.setAllowClose(boolI.booleanValue());
        } else {
            akVar.setAllowClose(jSONObject.optBoolean("allowClose", akVar.isAllowClose()));
        }
        Boolean boolK = this.F.K();
        if (boolK != null) {
            akVar.setAllowSeek(boolK.booleanValue());
        } else {
            akVar.setAllowSeek(jSONObject.optBoolean(bd.a.eJ, akVar.isAllowSeek()));
        }
        Boolean boolL = this.F.L();
        if (boolL != null) {
            akVar.setAllowSkip(boolL.booleanValue());
        } else {
            akVar.setAllowSkip(jSONObject.optBoolean(bd.a.eK, akVar.isAllowSkip()));
        }
        Boolean boolM = this.F.M();
        if (boolM != null) {
            akVar.setAllowTrackChange(boolM.booleanValue());
        } else {
            akVar.setAllowTrackChange(jSONObject.optBoolean(bd.a.eL, akVar.isAllowTrackChange()));
        }
        Boolean boolJ = this.F.J();
        if (boolJ != null) {
            akVar.setAllowPause(boolJ.booleanValue());
        } else {
            akVar.setAllowPause(jSONObject.optBoolean("hasPause", akVar.isAllowPause()));
        }
        float allowCloseDelay = this.F.getAllowCloseDelay();
        if (allowCloseDelay >= 0.0f) {
            akVar.setAllowCloseDelay(allowCloseDelay);
        } else {
            akVar.setAllowCloseDelay((float) jSONObject.optDouble("allowCloseDelay", akVar.getAllowCloseDelay()));
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("companionAds");
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject != null) {
                    String id = akVar.getId();
                    aj ajVarNewBanner = aj.newBanner();
                    this.G.a(jSONObjectOptJSONObject, ajVarNewBanner);
                    if (ajVarNewBanner.getWidth() == 0 || ajVarNewBanner.getHeight() == 0) {
                        a(az.a.ej, "Unable to add companion banner with width " + ajVarNewBanner.getWidth() + " and height " + ajVarNewBanner.getHeight(), id);
                        ajVarNewBanner = null;
                    } else {
                        ajVarNewBanner.setAssetWidth(jSONObjectOptJSONObject.optInt(bj.gN));
                        ajVarNewBanner.setAssetHeight(jSONObjectOptJSONObject.optInt(bj.gO));
                        ajVarNewBanner.setExpandedWidth(jSONObjectOptJSONObject.optInt(bj.gP));
                        ajVarNewBanner.setExpandedHeight(jSONObjectOptJSONObject.optInt(bj.gQ));
                        ajVarNewBanner.setStaticResource(jSONObjectOptJSONObject.optString("staticResource"));
                        ajVarNewBanner.setIframeResource(jSONObjectOptJSONObject.optString("iframeResource"));
                        ajVarNewBanner.setHtmlResource(jSONObjectOptJSONObject.optString("htmlResource"));
                        ajVarNewBanner.setApiFramework(jSONObjectOptJSONObject.optString(bj.apiFramework));
                        ajVarNewBanner.setAdSlotID(jSONObjectOptJSONObject.optString(bj.adSlotID));
                        String strOptString = jSONObjectOptJSONObject.optString(bj.required);
                        if (strOptString != null) {
                            if (!bj.gK.equals(strOptString) && !bj.gJ.equals(strOptString) && !"none".equals(strOptString)) {
                                a(az.a.ei, "Wrong companion required attribute:".concat(String.valueOf(strOptString)), id);
                            } else {
                                ajVarNewBanner.setRequired(strOptString);
                            }
                        }
                    }
                    if (ajVarNewBanner != null) {
                        akVar.addCompanion(ajVarNewBanner);
                    }
                }
            }
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("shareButtons");
        if (jSONArrayOptJSONArray2 != null) {
            int length2 = jSONArrayOptJSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                JSONObject jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i2);
                if (jSONObjectOptJSONObject2 != null) {
                    ShareButtonData shareButtonDataNewData = ShareButtonData.newData();
                    shareButtonDataNewData.setName(jSONObjectOptJSONObject2.optString("name"));
                    shareButtonDataNewData.setUrl(jSONObjectOptJSONObject2.optString("url"));
                    shareButtonDataNewData.setImageUrl(jSONObjectOptJSONObject2.optString("imageUrl"));
                    akVar.addShareButtonData(shareButtonDataNewData);
                }
            }
        }
        return c(jSONObject, akVar);
    }

    private void b(@NonNull JSONObject jSONObject, @NonNull ak<AudioData> akVar) {
        double point = this.F.getPoint();
        if (point < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            point = jSONObject.optDouble("point");
        }
        if (Double.isNaN(point)) {
            point = -1.0d;
        } else if (point < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            a(az.a.ei, "Wrong value " + point + " for point", akVar.getId());
        }
        double pointP = this.F.getPointP();
        if (pointP < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            pointP = jSONObject.optDouble("pointP");
        }
        if (Double.isNaN(pointP)) {
            pointP = -1.0d;
        } else if (pointP < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            a(az.a.ei, "Wrong value " + pointP + " for pointP", akVar.getId());
        }
        if (point < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && pointP < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            pointP = 50.0d;
            point = -1.0d;
        }
        akVar.setPoint((float) point);
        akVar.setPointP((float) pointP);
    }

    private boolean c(@NonNull JSONObject jSONObject, @NonNull ak<AudioData> akVar) {
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(bf.a.fD);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            g.a("mediafiles array is empty");
            return false;
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString(bf.a.fE);
                if (!TextUtils.isEmpty(strOptString)) {
                    AudioData audioDataNewAudioData = AudioData.newAudioData(strOptString);
                    audioDataNewAudioData.setBitrate(jSONObjectOptJSONObject.optInt("bitrate"));
                    akVar.setMediaData(audioDataNewAudioData);
                    return true;
                }
                a(az.a.ei, "bad mediafile object, src = ".concat(String.valueOf(strOptString)), akVar.getId());
            }
        }
        return false;
    }

    private void a(@NonNull String str, @NonNull String str2, @Nullable String str3) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).C(str3).B(this.F.getUrl()).e(this.context);
    }
}
