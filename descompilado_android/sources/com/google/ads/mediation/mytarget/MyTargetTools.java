package com.google.ads.mediation.mytarget;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
class MyTargetTools {
    private static final String KEY_SLOT_ID = "slotId";

    @NonNull
    static final String PARAM_MEDIATION_KEY = "mediation";

    @NonNull
    static final String PARAM_MEDIATION_VALUE = "1";

    MyTargetTools() {
    }

    static int checkAndGetSlotId(@Nullable Context context, @Nullable Bundle bundle) {
        if (context == null) {
            Log.w(MyTargetMediationAdapter.TAG, "Failed to request ad from MyTarget: Context is null.");
            return -1;
        }
        if (bundle == null) {
            Log.w(MyTargetMediationAdapter.TAG, "Failed to request ad from MyTarget: serverParameters is null.");
            return -1;
        }
        String string = bundle.getString(KEY_SLOT_ID);
        if (TextUtils.isEmpty(string)) {
            Log.w(MyTargetMediationAdapter.TAG, "Failed to request ad from MyTarget: Missing or Invalid Slot ID.");
            return -1;
        }
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            Log.w(MyTargetMediationAdapter.TAG, "Failed to request ad from MyTarget.", e);
            return -1;
        }
    }
}
