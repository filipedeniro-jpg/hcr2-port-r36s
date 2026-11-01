package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NotificationHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fd {

    @NonNull
    private final b adConfig;

    @NonNull
    private final com.my.target.core.models.sections.e section;

    @NonNull
    public static fd a(@NonNull com.my.target.core.models.sections.e eVar, @NonNull b bVar) {
        return new fd(eVar, bVar);
    }

    private fd(@NonNull com.my.target.core.models.sections.e eVar, @NonNull b bVar) {
        this.section = eVar;
        this.adConfig = bVar;
    }

    public final void a(@NonNull final com.my.target.core.models.banners.j jVar, @NonNull Context context) {
        if (jVar.isHasNotification()) {
            jVar.setHasNotification(false);
            final Context applicationContext = context.getApplicationContext();
            h.a(new Runnable() { // from class: com.my.target.fd.1
                @Override // java.lang.Runnable
                public final void run() {
                    String strB = fd.this.b(jVar);
                    if (strB == null) {
                        return;
                    }
                    cd cdVarT = cd.t(applicationContext);
                    if (cdVarT != null) {
                        cdVarT.a(fd.this.adConfig.getSlotId(), strB, true);
                        return;
                    }
                    g.a("unable to open disk cache and save text data for slotId: " + fd.this.adConfig.getSlotId());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String b(@NonNull com.my.target.core.models.banners.j jVar) {
        String id = jVar.getId();
        try {
            JSONObject rawData = this.section.getRawData();
            if (rawData == null) {
                g.a("unable to change cached notification for banner " + id + ": no raw data in section");
                return null;
            }
            JSONObject jSONObject = rawData.getJSONObject(this.section.getName());
            if (jSONObject == null) {
                g.a("unable to change cached notification for banner " + id + ": no section object in raw data");
                return null;
            }
            JSONArray jSONArray = jSONObject.getJSONArray("banners");
            if (jSONArray == null) {
                g.a("unable to change cached notification for banner " + id + ": no banners array in section object");
                return null;
            }
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                String string = jSONObject2.getString(be.a.eq);
                if (string != null && string.equals(id)) {
                    jSONObject2.put("hasNotification", jVar.isHasNotification());
                    g.a("notification changed in raw data for banner ".concat(String.valueOf(id)));
                    return rawData.toString();
                }
            }
            return null;
        } catch (JSONException e) {
            g.a("error updating cached notification for section " + this.section.getName() + " and banner " + id + ": " + e);
        }
    }
}
