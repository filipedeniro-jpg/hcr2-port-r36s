package com.my.target.core.models.sections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.al;
import org.json.JSONObject;

/* JADX INFO: compiled from: StandardAdSection.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b extends al {

    @Nullable
    private com.my.target.core.models.banners.c O;
    private boolean P = true;

    @Nullable
    private String Q;
    private JSONObject R;

    @NonNull
    public static b D() {
        return new b();
    }

    private b() {
    }

    public final void n(boolean z) {
        this.P = z;
    }

    public final boolean E() {
        return this.P;
    }

    public final void c(@Nullable String str) {
        this.Q = str;
    }

    @Nullable
    public final String a_() {
        return this.Q;
    }

    public final void setRawData(JSONObject jSONObject) {
        this.R = jSONObject;
    }

    public final JSONObject getRawData() {
        return this.R;
    }

    @Nullable
    public final com.my.target.core.models.banners.c G() {
        return this.O;
    }

    public final void a(@Nullable com.my.target.core.models.banners.c cVar) {
        this.O = cVar;
    }

    @Override // com.my.target.al
    public final int getBannersCount() {
        return this.O == null ? 0 : 1;
    }
}
