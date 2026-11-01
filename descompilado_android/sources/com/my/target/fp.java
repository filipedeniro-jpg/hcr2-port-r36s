package com.my.target;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InstreamResearchResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fp extends d<fu> {
    private final int duration;

    @NonNull
    public static d<fu> a(int i) {
        return new fp(i);
    }

    private fp(int i) {
        this.duration = i;
    }

    @Override // com.my.target.d
    @Nullable
    public final /* synthetic */ al a(@NonNull String str, @NonNull af afVar, @Nullable al alVar, @NonNull b bVar, @NonNull Context context) {
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectOptJSONObject2;
        JSONObject jSONObjectA = a(str, context);
        if (jSONObjectA == null || (jSONObjectOptJSONObject = jSONObjectA.optJSONObject(bVar.getFormat())) == null || (jSONArrayOptJSONArray = jSONObjectOptJSONObject.optJSONArray("banners")) == null || jSONArrayOptJSONArray.length() <= 0 || (jSONObjectOptJSONObject2 = jSONArrayOptJSONArray.optJSONObject(0)) == null) {
            return null;
        }
        ft ftVarNewBanner = ft.newBanner();
        String strOptString = jSONObjectOptJSONObject2.optString("id");
        if (TextUtils.isEmpty(strOptString)) {
            strOptString = jSONObjectOptJSONObject2.optString(be.a.eq, ftVarNewBanner.getId());
        }
        ftVarNewBanner.setId(strOptString);
        String strOptString2 = jSONObjectOptJSONObject2.optString("type");
        if (!TextUtils.isEmpty(strOptString2)) {
            ftVarNewBanner.setType(strOptString2);
        }
        if (jSONObjectOptJSONObject2.optJSONArray("statistics") != null) {
            fx.a(afVar, bVar, context).a(ftVarNewBanner.getStatHolder(), jSONObjectOptJSONObject2, strOptString, this.duration);
        }
        if (!ftVarNewBanner.getStatHolder().af()) {
            return null;
        }
        fu fuVarE = fu.e();
        fuVarE.a(ftVarNewBanner);
        return fuVarE;
    }
}
