package com.applovin.mediation.adapter.parameters;

import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public interface MaxAdapterParameters {
    Bundle getServerParameters();

    boolean hasUserConsent();

    boolean isAgeRestrictedUser();

    boolean isTesting();
}
