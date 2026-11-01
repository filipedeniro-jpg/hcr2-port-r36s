package com.fingersoft.liveops_sdk.crosspromotions;

import com.fingersoft.liveops_sdk.utils.CommonUtils;
import com.fingersoft.liveops_sdk.utils.DeviceCapabilities;
import com.fingersoft.liveops_sdk.utils.Log;
import com.ironsource.sdk.constants.Constants;
import com.my.target.ai;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.nend.android.NendAdNativeMediaView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CrossPromotionCollection {
    ArrayList<CrossPromotion> promotions = new ArrayList<>();

    public enum PromotionType {
        NORMAL,
        REWARD,
        HTML
    }

    public CrossPromotionCollection(JSONArray jSONArray, int i) {
        parseJson(jSONArray, i);
    }

    private void parseJson(JSONArray jSONArray, int i) {
        try {
            Log.e("", "parsing json array, length " + jSONArray.length());
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                this.promotions.add(new CrossPromotion(jSONArray.getJSONObject(i2), i));
            }
        } catch (JSONException e) {
            CommonUtils.log(e.getMessage());
        }
    }

    public void addPromotion(CrossPromotion crossPromotion) {
        this.promotions.add(crossPromotion);
    }

    public List<CrossPromotion> getCrossPromotions() {
        Collections.sort(this.promotions);
        return this.promotions;
    }

    public boolean hasPromotions() {
        return this.promotions.size() > 0;
    }

    public class CrossPromotion implements Comparable<CrossPromotion> {
        private static final int HTML_CROSS_PROMOTION_JSON_VERSION = 3;
        private String htmlString;
        private String title = "";
        private String description = "";
        private String clickUrl = "";
        private String image = "";
        private String guid = "";
        private String identifier = "";
        private String dialogTitle = "";
        private String cpuArchitecture = "";
        private String imageData = "";
        private PromotionType promotionType = PromotionType.NORMAL;
        private boolean active = false;
        private String targetPackageName = null;
        private int versionNumber = 0;
        private int resolutionWidth = 0;
        private int resolutionHeight = 0;
        private int ramAmount = 0;
        private int rewardAmount = 0;
        private int position = 0;
        private int startCount = 0;
        private int promotionPlacement = 0;
        private int reshowTime = 0;
        private boolean unitySupport = false;
        private boolean notNow = false;

        public void setNotNow() {
            this.notNow = true;
        }

        public boolean notNow() {
            return this.notNow;
        }

        protected CrossPromotion(JSONObject jSONObject, int i) {
            Log.e("", "promotion created, doing parse with version " + i);
            parseJson(jSONObject, i);
        }

        private void parseJson(JSONObject jSONObject, int i) {
            try {
                if (i < 3) {
                    this.title = jSONObject.getString("title");
                    this.dialogTitle = jSONObject.getString("dialog_title");
                    this.description = jSONObject.getString("description");
                    this.clickUrl = jSONObject.getString(NendAdNativeMediaView.RESULT_CLICK_URL);
                    this.image = jSONObject.getString("promotion_image");
                    this.guid = jSONObject.getString("guid");
                    this.identifier = jSONObject.getString(SettingsJsonConstants.APP_IDENTIFIER_KEY);
                    this.imageData = jSONObject.getString("image_data");
                    this.active = jSONObject.getBoolean("active");
                    this.position = jSONObject.getInt(Constants.ParametersKeys.POSITION);
                    this.startCount = jSONObject.getInt("start_count");
                    this.targetPackageName = jSONObject.getString("targetPackageName");
                    if (jSONObject.has("reward")) {
                        this.rewardAmount = jSONObject.getInt("reward");
                    }
                    String string = jSONObject.getString("type");
                    if (string.equals(null)) {
                        return;
                    }
                    this.promotionType = PromotionType.valueOf(string.toUpperCase());
                    return;
                }
                this.guid = jSONObject.getString("guid");
                this.position = jSONObject.getInt(Constants.ParametersKeys.POSITION);
                this.startCount = jSONObject.getInt("start_count");
                this.targetPackageName = jSONObject.getString("targetPackageName");
                this.htmlString = jSONObject.getString(ai.a.da);
                this.clickUrl = jSONObject.getString(NendAdNativeMediaView.RESULT_CLICK_URL);
                this.promotionPlacement = jSONObject.getInt("promo_placement");
                this.reshowTime = jSONObject.getInt("reshow");
                if (jSONObject.has("reward")) {
                    this.rewardAmount = jSONObject.getInt("reward");
                }
                String string2 = jSONObject.getString("type");
                if (string2.equals(null)) {
                    return;
                }
                this.promotionType = PromotionType.valueOf(string2.toUpperCase());
            } catch (JSONException e) {
                Log.e("LiveOpsSDK", "Json parse error " + e);
            }
        }

        public String getTitle() {
            return this.title;
        }

        public String getDescription() {
            return this.description;
        }

        public String getClickUrl() {
            return this.clickUrl;
        }

        public String getImage() {
            return this.image;
        }

        public String getGuid() {
            return this.guid;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public String getCpuArchitecture() {
            return this.cpuArchitecture;
        }

        public int getRamAmount() {
            return this.ramAmount;
        }

        public int getResolutionHeight() {
            return this.resolutionHeight;
        }

        public int getResolutionWidth() {
            return this.resolutionWidth;
        }

        public int getVersionNumber() {
            return this.versionNumber;
        }

        public boolean getUnitySupport() {
            return this.unitySupport;
        }

        public String getDialogTitle() {
            return this.dialogTitle;
        }

        public String getImageData() {
            return this.imageData;
        }

        public String getHtmlString() {
            return this.htmlString;
        }

        public boolean matchesCapabilities() {
            return !this.unitySupport || DeviceCapabilities.isUnitySupported();
        }

        public PromotionType getPromotionType() {
            return this.promotionType;
        }

        public int getRewardAmount() {
            return this.rewardAmount;
        }

        public boolean isActive() {
            return this.active;
        }

        public int getStartCount() {
            return this.startCount;
        }

        public int getPromotionPlacement() {
            return this.promotionPlacement;
        }

        public int getReshowTime() {
            return this.reshowTime;
        }

        public String getTargetPackageName() {
            return this.targetPackageName;
        }

        @Override // java.lang.Comparable
        public int compareTo(CrossPromotion crossPromotion) {
            int i = this.position;
            int i2 = crossPromotion.position;
            if (i < i2) {
                return -1;
            }
            return i == i2 ? 0 : 1;
        }
    }
}
