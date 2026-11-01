package com.my.target;

import androidx.annotation.NonNull;
import org.json.JSONObject;

/* JADX INFO: compiled from: JsCall.java */
/* JADX INFO: loaded from: classes2.dex */
public interface m {
    public static final String START = "start";
    public static final int ap = 20;
    public static final String aq = "init";
    public static final String ar = "expand";
    public static final String as = "collapse";
    public static final String at = "stop";
    public static final String au = "pause";
    public static final String av = "resume";
    public static final String aw = "updateBanners";

    @NonNull
    JSONObject g();

    @NonNull
    String getType();
}
