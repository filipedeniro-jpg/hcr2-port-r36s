package com.vungle.warren.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/* JADX INFO: loaded from: classes2.dex */
public class JsonUtil {
    public static boolean hasNonNull(JsonElement jsonElement, String str) {
        if (jsonElement == null || jsonElement.isJsonNull() || !jsonElement.isJsonObject()) {
            return false;
        }
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        return (!asJsonObject.has(str) || asJsonObject.get(str) == null || asJsonObject.get(str).isJsonNull()) ? false : true;
    }
}
