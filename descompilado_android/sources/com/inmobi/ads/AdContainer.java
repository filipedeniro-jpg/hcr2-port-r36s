package com.inmobi.ads;

import android.app.Activity;
import android.view.View;
import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public interface AdContainer {

    public interface a {
        void a();

        void a(Object obj);

        void b(Object obj);
    }

    void a();

    void a(int i, Map<String, String> map);

    void b();

    boolean c();

    void destroy();

    o getApkDownloader();

    Object getDataModel();

    a getFullScreenEventsListener();

    String getMarkupType();

    RenderingProperties getRenderingProperties();

    @Nullable
    View getVideoContainerView();

    ca getViewableAd();

    void setFullScreenActivityContext(Activity activity);

    void setRequestedScreenOrientation();

    public static class RenderingProperties {
        public PlacementType a;

        public enum PlacementType {
            PLACEMENT_TYPE_INLINE,
            PLACEMENT_TYPE_FULLSCREEN
        }

        public RenderingProperties(PlacementType placementType) {
            this.a = placementType;
        }
    }
}
