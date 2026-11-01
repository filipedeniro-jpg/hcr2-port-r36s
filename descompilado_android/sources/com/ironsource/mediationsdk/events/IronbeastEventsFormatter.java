package com.ironsource.mediationsdk.events;

import com.ironsource.eventsmodule.EventData;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class IronbeastEventsFormatter extends AbstractEventsFormatter {
    private final String DEFAULT_IB_EVENTS_URL = "https://outcome-ssp.supersonicads.com/mediation?adUnit=2";
    private final String IB_TABLE_NAME = "super.dwh.mediation_events";
    private final String IB_KEY_TABLE = "table";
    private final String IB_KEY_DATA = "data";

    @Override // com.ironsource.mediationsdk.events.AbstractEventsFormatter
    public String getDefaultEventsUrl() {
        return "https://outcome-ssp.supersonicads.com/mediation?adUnit=2";
    }

    @Override // com.ironsource.mediationsdk.events.AbstractEventsFormatter
    public String getFormatterType() {
        return "ironbeast";
    }

    IronbeastEventsFormatter(int i) {
        this.mAdUnit = i;
    }

    @Override // com.ironsource.mediationsdk.events.AbstractEventsFormatter
    public String format(ArrayList<EventData> arrayList, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        if (jSONObject == null) {
            this.mGeneralProperties = new JSONObject();
        } else {
            this.mGeneralProperties = jSONObject;
        }
        try {
            JSONArray jSONArray = new JSONArray();
            if (arrayList != null && !arrayList.isEmpty()) {
                Iterator<EventData> it = arrayList.iterator();
                while (it.hasNext()) {
                    JSONObject jSONObjectCreateJSONForEvent = createJSONForEvent(it.next());
                    if (jSONObjectCreateJSONForEvent != null) {
                        jSONArray.put(jSONObjectCreateJSONForEvent);
                    }
                }
            }
            jSONObject2.put("table", "super.dwh.mediation_events");
            jSONObject2.put("data", createDataToSend(jSONArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject2.toString();
    }
}
