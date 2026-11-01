package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.server.HttpFunctions;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class LogsSender implements Runnable {
    private ArrayList<ServerLogEntry> mLogs;
    private final String LOG_URL = "https://mobilelogs.supersonic.com";
    private final String AUTHO_USERNAME = "mobilelogs";
    private final String AUTHO_PASSWORD = "k@r@puz";

    @Override // java.lang.Runnable
    public void run() {
    }

    public LogsSender(ArrayList<ServerLogEntry> arrayList) {
        this.mLogs = arrayList;
    }

    private JSONObject getJSONToSend() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("general_properties", GeneralProperties.getProperties().toJSON());
            JSONArray jSONArray = new JSONArray();
            Iterator<ServerLogEntry> it = this.mLogs.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().toJSON());
            }
            jSONObject.put("log_data", jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void sendLogs(JSONObject jSONObject) throws Throwable {
        HttpFunctions.getStringFromPostWithAutho("https://mobilelogs.supersonic.com", jSONObject.toString(), "mobilelogs", "k@r@puz");
    }
}
