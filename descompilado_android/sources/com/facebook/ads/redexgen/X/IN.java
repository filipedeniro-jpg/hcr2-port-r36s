package com.facebook.ads.redexgen.X;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class IN {
    private final String B;
    private final JSONObject C;
    private final String D;
    private final Map<IR, List<String>> E = new HashMap();

    public IN(String mAdapter, String dataModelType, JSONObject mData, @Nullable JSONArray trackers) {
        this.B = mAdapter;
        this.D = dataModelType;
        this.C = mData;
        if (trackers == null || trackers.length() == 0) {
            return;
        }
        for (IR ir : IR.values()) {
            this.E.put(ir, new LinkedList());
        }
        for (int i = 0; i < trackers.length(); i++) {
            try {
                JSONObject jSONObject = trackers.getJSONObject(i);
                String string = jSONObject.getString("type");
                String string2 = jSONObject.getString("url");
                IR irValueOf = IR.valueOf(string.toUpperCase(Locale.US));
                if (irValueOf != null && !TextUtils.isEmpty(string2)) {
                    this.E.get(irValueOf).add(string2);
                }
            } catch (Exception e) {
            }
        }
    }

    public final String A() {
        return this.B;
    }

    public final JSONObject B() {
        return this.C;
    }

    public final String C() {
        return this.D;
    }

    public final List<String> D(IR trackerType) {
        return this.E.get(trackerType);
    }
}
