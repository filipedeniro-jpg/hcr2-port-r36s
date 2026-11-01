package com.my.target;

import android.content.Context;
import androidx.annotation.NonNull;
import com.appsflyer.share.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.my.target.common.MyTargetPrivacy;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: AdServiceBuilder.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class f {
    @NonNull
    public abstract af a(@NonNull b bVar, @NonNull Context context);

    @NonNull
    public static f e() {
        return new a();
    }

    /* JADX INFO: compiled from: AdServiceBuilder.java */
    public static class a extends f {

        @NonNull
        private static String v = "https://ad.mail.ru/mobile/";

        protected a() {
        }

        @Override // com.my.target.f
        @NonNull
        public af a(@NonNull b bVar, @NonNull Context context) {
            return af.n(c(bVar, context));
        }

        @NonNull
        protected Map<String, String> b(@NonNull b bVar, @NonNull Context context) {
            HashMap map = new HashMap();
            map.put(i.U, bVar.getFormat());
            map.put(i.W, "5.3.9");
            if (MyTargetPrivacy.isConsentSpecified()) {
                map.put(i.ab, MyTargetPrivacy.isUserConsent() ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
            if (MyTargetPrivacy.isUserAgeRestricted()) {
                map.put(i.ac, "1");
            }
            if (bVar.isAutoLoadVideo()) {
                map.put(i.Z, "1");
            }
            int bannersCount = bVar.getBannersCount();
            if (bannersCount > 0) {
                map.put(i.aa, Integer.toString(bannersCount));
            }
            String bidId = bVar.getBidId();
            if (bidId != null) {
                map.put(i.ae, bidId);
            }
            if (MyTargetPrivacy.isConsentSpecified() && !MyTargetPrivacy.isUserConsent()) {
                return map;
            }
            bVar.getCustomParams().putDataTo(map);
            try {
                bn.aO().aQ().g(bVar.isTrackingEnvironmentEnabled());
                bn.aO().aQ().h(bVar.isTrackingLocationEnabled());
                bn.aO().collectData(context);
            } catch (Throwable th) {
                g.a("Error collecting data: " + th);
            }
            bn.aO().putDataTo(map);
            return map;
        }

        @NonNull
        private String c(@NonNull b bVar, @NonNull Context context) {
            Map<String, String> mapB = b(bVar, context);
            StringBuilder sb = new StringBuilder(v + bVar.getSlotId() + Constants.URL_PATH_DELIMITER);
            boolean z = true;
            for (Map.Entry<String, String> entry : mapB.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    String key = entry.getKey();
                    try {
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        g.a(e.getMessage());
                    }
                    if (z) {
                        sb.append("?");
                        sb.append(key);
                        sb.append(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
                        sb.append(value);
                        z = false;
                    } else {
                        sb.append(com.ironsource.sdk.constants.Constants.RequestParameters.AMPERSAND);
                        sb.append(key);
                        sb.append(com.ironsource.sdk.constants.Constants.RequestParameters.EQUAL);
                        sb.append(value);
                    }
                }
            }
            return sb.toString();
        }
    }
}
