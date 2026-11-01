package com.my.target;

import androidx.annotation.NonNull;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AbstractJsCall.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class k implements m {

    @NonNull
    private final JSONObject an = new JSONObject();

    @NonNull
    JSONObject ao = new JSONObject();

    @NonNull
    private final String type;

    public k(@NonNull String str) throws JSONException {
        this.type = str;
        this.an.put("method", str);
        this.an.put("data", this.ao);
    }

    @Override // com.my.target.m
    @NonNull
    public String getType() {
        return this.type;
    }

    @Override // com.my.target.m
    @NonNull
    public JSONObject g() {
        return this.an;
    }
}
