package com.google.ads.mediation.customevent;

import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.plus.PlusShare;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class CustomEventServerParameters extends MediationServerParameters {

    @MediationServerParameters.Parameter(name = "class_name", required = true)
    public String className;

    @MediationServerParameters.Parameter(name = PlusShare.KEY_CALL_TO_ACTION_LABEL, required = true)
    public String label;

    @MediationServerParameters.Parameter(name = MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD, required = false)
    public String parameter = null;
}
