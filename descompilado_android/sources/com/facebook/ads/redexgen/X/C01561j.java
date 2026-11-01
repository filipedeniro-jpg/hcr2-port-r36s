package com.facebook.ads.redexgen.X;

import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.1j, reason: invalid class name and case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C01561j {
    private C01561j() {
    }

    public static C01521f B(JSONObject dataObject) {
        return new C01511e().E(H(dataObject)).D(F(dataObject)).B(E(dataObject)).C(G(dataObject)).A();
    }

    public static String C(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Sponsored" : dataObject2.optString("sponsored", "Sponsored");
    }

    public static C01631q D(JSONObject dataObject) {
        return new C01621p().B(I(dataObject)).C(J(dataObject)).A();
    }

    private static String E(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Close Ad" : dataObject2.optString("close_ad", "Close Ad");
    }

    private static String F(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Continue watching to receive {reward}" : dataObject2.optString("continue_watching_for_reward", "Continue watching to receive {reward}");
    }

    private static String G(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Continue watching to receive an in-game reward" : dataObject2.optString("generic_reward", "Continue watching to receive an in-game reward");
    }

    private static String H(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Continue Watching" : dataObject2.optString("continue_watching", "Continue Watching");
    }

    private static String I(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Reward in [secs]s" : dataObject2.optString("reward_in_x", "Reward in [secs]s");
    }

    private static String J(JSONObject dataObject) {
        JSONObject dataObject2 = dataObject.optJSONObject("generic_text");
        return dataObject2 == null ? "Skip" : dataObject2.optString("skip", "Skip");
    }
}
