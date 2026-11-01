package com.fingersoft.ads_sdk.advertising.json;

import android.util.Log;
import com.fingersoft.ads_sdk.advertising.AdManager;
import com.fingersoft.ads_sdk.advertising.utils.AdUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SettingCollection {
    ArrayList<Setting> settings = new ArrayList<>();

    public SettingCollection(JSONArray jSONArray) {
        parseJson(jSONArray);
    }

    public SettingCollection(JSONArray jSONArray, int i) {
        if (i >= 3) {
            parseJsonV3(jSONArray);
        } else if (i == 2) {
            parseJsonV2(jSONArray);
        } else {
            parseJson(jSONArray);
        }
    }

    private void parseJson(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                addSetting(new Setting(jSONArray.getJSONObject(i)));
            } catch (JSONException e) {
                Log.d(AdManager.TAG, e.getMessage());
                return;
            }
        }
    }

    private void parseJsonV2(JSONArray jSONArray) {
        AdUtils.log("Parsing JsonV2 " + jSONArray);
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                addSetting(new Setting(jSONObject.getString("name"), jSONObject.getString("value")));
            } catch (JSONException e) {
                AdUtils.log("Exception in parseJsonV2");
                Log.d(AdManager.TAG, e.getMessage());
                return;
            }
        }
        AdUtils.log("JsonV2 parse done");
    }

    private void parseJsonV3(JSONArray jSONArray) {
        AdUtils.log("Parsing JsonV3 " + jSONArray);
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    addSetting(new Setting(next, jSONObject.getString(next)));
                }
            } catch (JSONException e) {
                AdUtils.log("Exception in parseJsonV3");
                Log.d(AdManager.TAG, e.getMessage());
                return;
            }
        }
        AdUtils.log("JsonV3 parse done");
    }

    public void addSetting(Setting setting) {
        this.settings.add(setting);
    }

    public List<Setting> getSettings() {
        return this.settings;
    }

    public Setting getSettingByName(String str) {
        for (Setting setting : this.settings) {
            if (setting.name.equalsIgnoreCase(str)) {
                return setting;
            }
        }
        return new Setting("", "");
    }

    public boolean settingExistsWithName(String str) {
        Iterator<Setting> it = this.settings.iterator();
        while (it.hasNext()) {
            if (it.next().name.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public class Setting {
        private String name;
        private String value;

        public Setting(JSONObject jSONObject) {
            parseJson(jSONObject);
        }

        protected Setting(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        private void parseJson(JSONObject jSONObject) {
            try {
                this.name = jSONObject.getString("name");
                this.value = jSONObject.getString("value");
            } catch (JSONException e) {
                Log.d(AdManager.TAG, e.getMessage());
            }
        }

        public String getName() {
            return this.name;
        }

        public String getValue() {
            return this.value;
        }
    }
}
