package com.facebook.ads;

import androidx.annotation.Keep;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class RewardData implements Serializable {
    public static final long serialVersionUID = 1;
    private String currency;
    private String userID;

    public RewardData(String str, String str2) {
        this.userID = str;
        this.currency = str2;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getCurrency() {
        return this.currency;
    }
}
