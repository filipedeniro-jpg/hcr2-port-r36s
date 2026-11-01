package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.my.target.common.models.ImageData;
import com.my.target.common.models.VideoData;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: CommonVideoParser.java */
/* JADX INFO: loaded from: classes2.dex */
public class bf {

    @NonNull
    private final b adConfig;

    @Nullable
    private String ax;

    @NonNull
    private final af eO;

    @NonNull
    private final Context ez;

    @NonNull
    private final be fs;

    /* JADX INFO: compiled from: CommonVideoParser.java */
    interface a {
        public static final String HEIGHT = "height";
        public static final String WIDTH = "width";
        public static final String eF = "pointP";
        public static final String eG = "point";
        public static final String eH = "allowClose";
        public static final String eI = "allowCloseDelay";
        public static final String eM = "hasPause";
        public static final String fA = "previewLink";
        public static final String fB = "previewWidth";
        public static final String fC = "previewHeight";
        public static final String fD = "mediafiles";
        public static final String fE = "src";
        public static final String fF = "bitrate";
        public static final String fG = "allowReplay";
        public static final String fH = "allowBackButton";
        public static final String ft = "showPlayerControls";
        public static final String fu = "replayActionText";
        public static final String fv = "closeDelayActionText";
        public static final String fw = "closeActionText";
        public static final String fx = "automute";
        public static final String fy = "autoplay";
        public static final String fz = "hasCtaButton";
    }

    @NonNull
    public static bf c(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        return new bf(afVar, bVar, context);
    }

    private bf(@NonNull af afVar, @NonNull b bVar, @NonNull Context context) {
        this.eO = afVar;
        this.adConfig = bVar;
        this.ez = context;
        this.fs = be.b(afVar, bVar, context);
    }

    public boolean a(@NonNull JSONObject jSONObject, @NonNull ak<VideoData> akVar) {
        VideoData videoDataChooseBest;
        VideoData videoDataD;
        this.fs.a(jSONObject, akVar);
        if ("statistics".equals(akVar.getType())) {
            c(jSONObject, akVar);
            return true;
        }
        this.ax = akVar.getId();
        float duration = akVar.getDuration();
        if (duration <= 0.0f) {
            e(az.a.ei, "wrong videoBanner duration " + duration);
            return false;
        }
        akVar.setCloseActionText(jSONObject.optString(a.fw, "Close"));
        akVar.setReplayActionText(jSONObject.optString(a.fu, akVar.getReplayActionText()));
        akVar.setCloseDelayActionText(jSONObject.optString(a.fv, akVar.getCloseDelayActionText()));
        akVar.setAllowReplay(jSONObject.optBoolean(a.fG, akVar.isAllowReplay()));
        akVar.setAutoMute(jSONObject.optBoolean(a.fx, akVar.isAutoMute()));
        akVar.setAllowBackButton(jSONObject.optBoolean(a.fH, akVar.isAllowBackButton()));
        akVar.setAllowClose(jSONObject.optBoolean("allowClose", akVar.isAllowClose()));
        akVar.setAllowCloseDelay((float) jSONObject.optDouble("allowCloseDelay", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        akVar.setShowPlayerControls(jSONObject.optBoolean(a.ft, akVar.isShowPlayerControls()));
        akVar.setAutoPlay(jSONObject.optBoolean(a.fy, akVar.isAutoPlay()));
        akVar.setHasCtaButton(jSONObject.optBoolean(a.fz, akVar.isHasCtaButton()));
        akVar.setAllowPause(jSONObject.optBoolean("hasPause", akVar.isAllowPause()));
        String strOptString = jSONObject.optString(a.fA);
        if (!TextUtils.isEmpty(strOptString)) {
            akVar.setPreview(ImageData.newImageData(strOptString, jSONObject.optInt(a.fB), jSONObject.optInt(a.fC)));
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(a.fD);
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() == 0) {
            g.a("mediafiles array is empty");
            e(az.a.ej, "unable to find mediaFiles in MediaBanner");
            return false;
        }
        b(jSONObject, akVar);
        ArrayList arrayList = new ArrayList();
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && (videoDataD = d(jSONObjectOptJSONObject)) != null) {
                arrayList.add(videoDataD);
            }
        }
        if (arrayList.size() <= 0 || (videoDataChooseBest = VideoData.chooseBest(arrayList, this.adConfig.getVideoQuality())) == null) {
            return false;
        }
        akVar.setMediaData(videoDataChooseBest);
        return true;
    }

    private void b(@NonNull JSONObject jSONObject, @NonNull ak<VideoData> akVar) {
        c(jSONObject, akVar);
        Boolean boolI = this.eO.I();
        if (boolI != null) {
            akVar.setAllowClose(boolI.booleanValue());
        }
        Boolean boolJ = this.eO.J();
        if (boolJ != null) {
            akVar.setAllowPause(boolJ.booleanValue());
        }
        float allowCloseDelay = this.eO.getAllowCloseDelay();
        if (allowCloseDelay >= 0.0f) {
            akVar.setAllowCloseDelay(allowCloseDelay);
        }
    }

    private void c(@NonNull JSONObject jSONObject, @NonNull ak<VideoData> akVar) {
        double point = this.eO.getPoint();
        if (point < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            point = jSONObject.optDouble("point");
        }
        if (Double.isNaN(point)) {
            point = -1.0d;
        } else if (point < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            e(az.a.ei, "Wrong value " + point + " for point");
        }
        double pointP = this.eO.getPointP();
        if (pointP < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            pointP = jSONObject.optDouble("pointP");
        }
        if (Double.isNaN(pointP)) {
            pointP = -1.0d;
        } else if (pointP < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            e(az.a.ei, "Wrong value " + pointP + " for pointP");
        }
        if (point < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && pointP < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            pointP = 50.0d;
            point = -1.0d;
        }
        akVar.setPoint((float) point);
        akVar.setPointP((float) pointP);
    }

    @Nullable
    private VideoData d(@NonNull JSONObject jSONObject) {
        String strOptString = jSONObject.optString(a.fE);
        int iOptInt = jSONObject.optInt("width");
        int iOptInt2 = jSONObject.optInt("height");
        if (!TextUtils.isEmpty(strOptString) && iOptInt > 0 && iOptInt2 > 0) {
            VideoData videoDataNewVideoData = VideoData.newVideoData(strOptString, iOptInt, iOptInt2);
            videoDataNewVideoData.setBitrate(jSONObject.optInt("bitrate"));
            if (!videoDataNewVideoData.getUrl().endsWith(VideoData.M3U8) || cg.bm()) {
                return videoDataNewVideoData;
            }
            g.a("HLS Video does not supported, add com.google.android.exoplayer:exoplayer-hls dependency to play HLS video ");
            return null;
        }
        e(az.a.ei, "bad mediafile object, src = " + strOptString + ", width = " + iOptInt + ", height = " + iOptInt2);
        return null;
    }

    private void e(String str, String str2) {
        az.z(str).A(str2).g(this.adConfig.getSlotId()).C(this.ax).B(this.eO.getUrl()).e(this.ez);
    }
}
