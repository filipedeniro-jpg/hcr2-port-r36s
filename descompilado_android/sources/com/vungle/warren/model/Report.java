package com.vungle.warren.model;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class Report implements Memorable {
    private long adDuration;
    private final long adStartTime;
    private final String adToken;
    private final String adType;
    private final String advertisementID;
    private final String appId;
    private final String campaign;
    private final ArrayList<String> clickedThrough;
    private final ArrayList<String> errors;
    private final boolean incentivized;
    private final int ordinal;
    private final String placementId;
    private final String templateId;
    private final int ttDownload;
    private final String url;
    private final ArrayList<UserAction> userActions;
    private final String userID;
    private int videoViewed;
    private boolean wasCTAClicked;

    public Report(byte[] bArr) {
        if (bArr.length == 0) {
            throw new IllegalArgumentException("Cannot create Report from empty array");
        }
        this.advertisementID = "";
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.placementId = MemoryUtils.extractString(byteBufferWrap);
        this.adToken = MemoryUtils.extractString(byteBufferWrap);
        this.appId = MemoryUtils.extractString(byteBufferWrap);
        this.incentivized = byteBufferWrap.get() == 1;
        this.adStartTime = byteBufferWrap.getLong();
        this.url = MemoryUtils.extractString(byteBufferWrap);
        this.adDuration = byteBufferWrap.getLong();
        this.ttDownload = byteBufferWrap.getInt();
        this.campaign = MemoryUtils.extractString(byteBufferWrap);
        this.videoViewed = byteBufferWrap.getInt();
        this.adType = MemoryUtils.extractString(byteBufferWrap);
        this.templateId = MemoryUtils.extractString(byteBufferWrap);
        this.wasCTAClicked = byteBufferWrap.get() == 1;
        this.clickedThrough = new ArrayList<>(Arrays.asList(MemoryUtils.extractStringArray(byteBufferWrap)));
        this.errors = new ArrayList<>(Arrays.asList(MemoryUtils.extractStringArray(byteBufferWrap)));
        int i = byteBufferWrap.getInt();
        this.userActions = new ArrayList<>(i);
        for (int i2 = 0; i2 < i; i2++) {
            try {
                this.userActions.add((UserAction) MemoryUtils.extractMemorable(byteBufferWrap, UserAction.class));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (InstantiationException e2) {
                e2.printStackTrace();
                throw new RuntimeException(e2);
            } catch (NoSuchMethodException e3) {
                e3.printStackTrace();
                throw new RuntimeException(e3);
            } catch (InvocationTargetException e4) {
                e4.printStackTrace();
                throw new RuntimeException(e4);
            }
        }
        this.userID = MemoryUtils.extractString(byteBufferWrap);
        this.ordinal = byteBufferWrap.getInt();
    }

    public Report(@NonNull Advertisement advertisement, @NonNull Placement placement, long j) {
        this(advertisement, placement, j, null);
    }

    public Report(@NonNull Advertisement advertisement, @NonNull Placement placement, long j, @Nullable String str) {
        this.placementId = placement.getId();
        this.adToken = advertisement.getAdToken();
        this.advertisementID = advertisement.getId();
        this.appId = advertisement.getAppID();
        this.incentivized = placement.isIncentivized();
        this.adStartTime = j;
        this.url = advertisement.getUrl();
        this.ttDownload = -1;
        this.campaign = advertisement.getCampaign();
        int adType = advertisement.getAdType();
        if (adType == 0) {
            this.adType = "vungle_local";
        } else if (adType == 1) {
            this.adType = "vungle_mraid";
        } else {
            throw new IllegalArgumentException("Unknown ad type, cannot process!");
        }
        this.templateId = advertisement.getTemplateId();
        this.clickedThrough = new ArrayList<>();
        this.errors = new ArrayList<>();
        this.userActions = new ArrayList<>();
        if (str == null) {
            this.userID = "";
        } else {
            this.userID = str;
        }
        this.ordinal = advertisement.getAdConfig().getOrdinal();
    }

    public void recordAction(String str, String str2, long j) {
        this.userActions.add(new UserAction(str, str2, j));
        this.clickedThrough.add(str);
        if (str.equals("download")) {
            this.wasCTAClicked = true;
        }
    }

    public void recordError(String str) {
        this.errors.add(str);
    }

    public void recordProgress(int i) {
        this.videoViewed = i;
    }

    public void setAdDuration(int i) {
        this.adDuration = i;
    }

    public String getPlacementId() {
        return this.placementId;
    }

    public String getAdvertisementID() {
        return this.advertisementID;
    }

    public boolean isCTAClicked() {
        return this.wasCTAClicked;
    }

    public String getUserID() {
        return this.userID;
    }

    public JsonObject toReportBody() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("placement_reference_id", this.placementId);
        jsonObject.addProperty("ad_token", this.adToken);
        jsonObject.addProperty("app_id", this.appId);
        jsonObject.addProperty("incentivized", Integer.valueOf(this.incentivized ? 1 : 0));
        jsonObject.addProperty("adStartTime", Long.valueOf(this.adStartTime));
        jsonObject.addProperty("url", this.url);
        jsonObject.addProperty("adDuration", Long.valueOf(this.adDuration));
        jsonObject.addProperty("ttDownload", Integer.valueOf(this.ttDownload));
        jsonObject.addProperty(FirebaseAnalytics.Param.CAMPAIGN, this.campaign);
        jsonObject.addProperty("adType", this.adType);
        jsonObject.addProperty("templateId", this.templateId);
        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.addProperty("startTime", Long.valueOf(this.adStartTime));
        int i = this.videoViewed;
        if (i > 0) {
            jsonObject2.addProperty("videoViewed", Integer.valueOf(i));
            jsonObject2.addProperty("videoLength", Long.valueOf(this.adDuration));
        }
        JsonArray jsonArray2 = new JsonArray();
        Iterator<UserAction> it = this.userActions.iterator();
        while (it.hasNext()) {
            jsonArray2.add(it.next().toJson());
        }
        jsonObject2.add("userActions", jsonArray2);
        jsonArray.add(jsonObject2);
        jsonObject.add("plays", jsonArray);
        JsonArray jsonArray3 = new JsonArray();
        Iterator<String> it2 = this.errors.iterator();
        while (it2.hasNext()) {
            jsonArray3.add(it2.next());
        }
        jsonObject.add("errors", jsonArray3);
        JsonArray jsonArray4 = new JsonArray();
        Iterator<String> it3 = this.clickedThrough.iterator();
        while (it3.hasNext()) {
            jsonArray4.add(it3.next());
        }
        jsonObject.add("clickedThrough", jsonArray4);
        if (this.incentivized && !TextUtils.isEmpty(this.userID)) {
            jsonObject.addProperty("user", this.userID);
        }
        int i2 = this.ordinal;
        if (i2 > 0) {
            jsonObject.addProperty("ordinal_view", Integer.valueOf(i2));
        }
        return jsonObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Report)) {
            return false;
        }
        Report report = (Report) obj;
        if (!report.placementId.equals(this.placementId) || !report.adToken.equals(this.adToken) || !report.appId.equals(this.appId) || report.incentivized != this.incentivized || report.adStartTime != this.adStartTime || !report.url.equals(this.url) || report.adDuration != this.adDuration || report.ttDownload != this.ttDownload || !report.campaign.equals(this.campaign) || !report.adType.equals(this.adType) || !report.templateId.equals(this.templateId) || report.wasCTAClicked != this.wasCTAClicked || !report.userID.equals(this.userID) || report.clickedThrough.size() != this.clickedThrough.size()) {
            return false;
        }
        for (int i = 0; i < this.clickedThrough.size(); i++) {
            if (!report.clickedThrough.get(i).equals(this.clickedThrough.get(i))) {
                return false;
            }
        }
        if (report.errors.size() != this.errors.size()) {
            return false;
        }
        for (int i2 = 0; i2 < this.errors.size(); i2++) {
            if (!report.errors.get(i2).equals(this.errors.get(i2))) {
                return false;
            }
        }
        if (report.userActions.size() != this.userActions.size()) {
            return false;
        }
        for (int i3 = 0; i3 < this.userActions.size(); i3++) {
            if (!report.userActions.get(i3).equals(this.userActions.get(i3))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // com.vungle.warren.persistence.Memorable
    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MemoryUtils.writeString(this.placementId, byteArrayOutputStream);
            MemoryUtils.writeString(this.adToken, byteArrayOutputStream);
            MemoryUtils.writeString(this.appId, byteArrayOutputStream);
            int i = 1;
            byteArrayOutputStream.write(this.incentivized ? 1 : 0);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adStartTime));
            MemoryUtils.writeString(this.url, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.adDuration));
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ttDownload));
            MemoryUtils.writeString(this.campaign, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.videoViewed));
            MemoryUtils.writeString(this.adType, byteArrayOutputStream);
            MemoryUtils.writeString(this.templateId, byteArrayOutputStream);
            if (!this.wasCTAClicked) {
                i = 0;
            }
            byteArrayOutputStream.write(i);
            MemoryUtils.writeStringArray((String[]) this.clickedThrough.toArray(new String[this.clickedThrough.size()]), byteArrayOutputStream);
            MemoryUtils.writeStringArray((String[]) this.errors.toArray(new String[this.errors.size()]), byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.userActions.size()));
            Iterator<UserAction> it = this.userActions.iterator();
            while (it.hasNext()) {
                MemoryUtils.writeMemorable(it.next(), byteArrayOutputStream);
            }
            MemoryUtils.writeString(this.userID, byteArrayOutputStream);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.ordinal));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Report.java", "Failed to write " + this + " to a byte array");
            return new byte[0];
        }
    }

    @Override // com.vungle.warren.persistence.Memorable
    @NonNull
    public String getId() {
        return this.placementId + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + this.adStartTime;
    }

    public long getAdStartTime() {
        return this.adStartTime;
    }

    public static Report restore(int i, int i2, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new Report(Arrays.copyOfRange(bArr, 1, bArr.length));
    }

    public static class UserAction implements Memorable {
        private final String action;
        private final long timestamp;
        private final String value;

        public UserAction(byte[] bArr) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            this.action = MemoryUtils.extractString(byteBufferWrap);
            this.value = MemoryUtils.extractString(byteBufferWrap);
            this.timestamp = byteBufferWrap.getLong();
        }

        public UserAction(String str, String str2, long j) {
            this.action = str;
            this.value = str2;
            this.timestamp = j;
        }

        @Override // com.vungle.warren.persistence.Memorable
        public byte[] toByteArray() {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                MemoryUtils.writeString(this.action, byteArrayOutputStream);
                MemoryUtils.writeString(this.value, byteArrayOutputStream);
                byteArrayOutputStream.write(MemoryUtils.toBytes(this.timestamp));
                return byteArrayOutputStream.toByteArray();
            } catch (IOException unused) {
                Log.e("Report.java", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof UserAction)) {
                return false;
            }
            UserAction userAction = (UserAction) obj;
            return userAction.action.equals(this.action) && userAction.value.equals(this.value) && userAction.timestamp == this.timestamp;
        }

        @Override // com.vungle.warren.persistence.Memorable
        @NonNull
        public String getId() {
            return "" + this.timestamp;
        }

        public JsonObject toJson() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("action", this.action);
            if (!this.value.isEmpty()) {
                jsonObject.addProperty("value", this.value);
            }
            jsonObject.addProperty("timestamp_millis", Long.valueOf(this.timestamp));
            return jsonObject;
        }
    }
}
