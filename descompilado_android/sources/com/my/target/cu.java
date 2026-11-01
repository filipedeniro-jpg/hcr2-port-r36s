package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import com.my.target.common.MyTargetPrivacy;
import java.util.Map;

/* JADX INFO: compiled from: NativeAdServiceBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cu extends f.a {
    @NonNull
    public static cu f() {
        return new cu();
    }

    private cu() {
    }

    @Override // com.my.target.f.a
    @NonNull
    protected final Map<String, String> b(@NonNull b bVar, @NonNull Context context) {
        Map<String, String> mapSnapshot;
        Map<String, String> mapB = super.b(bVar, context);
        if ((!MyTargetPrivacy.isConsentSpecified() || MyTargetPrivacy.isUserConsent()) && (mapSnapshot = com.my.target.core.models.sections.a.G().snapshot()) != null && mapSnapshot.size() > 0) {
            StringBuilder sb = new StringBuilder();
            boolean z = false;
            for (String str : mapSnapshot.keySet()) {
                if (z) {
                    sb.append(",");
                } else {
                    z = true;
                }
                sb.append(str);
            }
            String string = sb.toString();
            mapB.put(i.X, string);
            g.a("Exclude list: ".concat(String.valueOf(string)));
        }
        return mapB;
    }
}
