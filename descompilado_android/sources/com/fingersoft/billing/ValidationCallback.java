package com.fingersoft.billing;

import com.fingersoft.billing.util.Purchase;

/* JADX INFO: loaded from: classes.dex */
public interface ValidationCallback {
    void onValidateError(Purchase purchase, String str);

    void onValidateFailed(Purchase purchase, String str);

    void onValidateSuccess(Purchase purchase);
}
