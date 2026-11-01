package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.media.AudioManager;
import com.facebook.appevents.AppEventsConstants;
import com.my.target.aa;
import com.my.target.bf;
import java.util.Map;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class JO {
    private JO() {
    }

    public static void B(Map<String, String> map, boolean autoplay, boolean inline) {
        map.put(bf.a.fy, autoplay ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put(aa.e.bn, inline ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public static float C(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            int volume = audioManager.getStreamVolume(3);
            int maxVolume = audioManager.getStreamMaxVolume(3);
            if (maxVolume > 0) {
                return (volume * 1.0f) / maxVolume;
            }
        }
        return 0.0f;
    }
}
