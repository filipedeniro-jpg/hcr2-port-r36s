package com.integralads.avid.library.inmobi.processing;

import android.view.View;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IAvidNodeProcessor {

    public interface IAvidViewWalker {
        void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject);
    }

    JSONObject getState(View view);

    void iterateChildren(View view, JSONObject jSONObject, IAvidViewWalker iAvidViewWalker, boolean z);
}
