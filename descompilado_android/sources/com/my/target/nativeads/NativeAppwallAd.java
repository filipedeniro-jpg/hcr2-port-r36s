package com.my.target.nativeads;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.aq;
import com.my.target.b;
import com.my.target.cc;
import com.my.target.cf;
import com.my.target.cj;
import com.my.target.common.BaseAd;
import com.my.target.common.models.ImageData;
import com.my.target.core.engines.h;
import com.my.target.core.models.banners.j;
import com.my.target.core.models.sections.e;
import com.my.target.ex;
import com.my.target.fd;
import com.my.target.g;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import com.my.target.nativeads.views.AppwallAdView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class NativeAppwallAd extends BaseAd {
    private static final String DEFAULT_TITLE = "Apps";
    private static final int DEFAULT_TITLE_BACKGROUND_COLOR = -12232093;
    private static final int DEFAULT_TITLE_SUPPLEMENTARY_COLOR = -13220531;
    private static final int DEFAULT_TITLE_TEXT_COLOR = -1;

    @NonNull
    private final ArrayList<NativeAppwallBanner> banners;

    @NonNull
    private final HashMap<NativeAppwallBanner, j> bannersMap;

    @NonNull
    private final cc clickHandler;

    @NonNull
    private final Context context;

    @Nullable
    private h engine;
    private boolean hideStatusBarInDialog;

    @Nullable
    private AppwallAdListener listener;

    @Nullable
    private e section;

    @NonNull
    private String title;
    private int titleBackgroundColor;
    private int titleSupplementaryColor;
    private int titleTextColor;

    @Nullable
    private WeakReference<AppwallAdView> viewWeakReference;

    public interface AppwallAdListener {
        void onClick(@NonNull NativeAppwallBanner nativeAppwallBanner, @NonNull NativeAppwallAd nativeAppwallAd);

        void onDismiss(@NonNull NativeAppwallAd nativeAppwallAd);

        void onDisplay(@NonNull NativeAppwallAd nativeAppwallAd);

        void onLoad(@NonNull NativeAppwallAd nativeAppwallAd);

        void onNoAd(@NonNull String str, @NonNull NativeAppwallAd nativeAppwallAd);
    }

    public static void loadImageToView(@NonNull ImageData imageData, @NonNull ImageView imageView) {
        cf.a(imageData, imageView);
    }

    public NativeAppwallAd(int i, @NonNull Context context) {
        super(i, b.a.g);
        this.clickHandler = cc.bg();
        this.bannersMap = new HashMap<>();
        this.banners = new ArrayList<>();
        this.title = DEFAULT_TITLE;
        this.titleBackgroundColor = DEFAULT_TITLE_BACKGROUND_COLOR;
        this.titleSupplementaryColor = DEFAULT_TITLE_SUPPLEMENTARY_COLOR;
        this.titleTextColor = -1;
        this.hideStatusBarInDialog = false;
        this.context = context;
        this.adConfig.setAutoLoadImages(true);
        g.c("NativeAppwallAd created. Version: 5.3.9");
    }

    @Nullable
    public final AppwallAdListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable AppwallAdListener appwallAdListener) {
        this.listener = appwallAdListener;
    }

    public final boolean isHideStatusBarInDialog() {
        return this.hideStatusBarInDialog;
    }

    public final void setHideStatusBarInDialog(boolean z) {
        this.hideStatusBarInDialog = z;
    }

    public final void setAutoLoadImages(boolean z) {
        this.adConfig.setAutoLoadImages(z);
    }

    public final boolean isAutoLoadImages() {
        return this.adConfig.isAutoLoadImages();
    }

    public final long getCachePeriod() {
        return this.adConfig.getCachePeriod();
    }

    public final void setCachePeriod(long j) {
        this.adConfig.setCachePeriod(j);
    }

    @NonNull
    public final ArrayList<NativeAppwallBanner> getBanners() {
        return this.banners;
    }

    @NonNull
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@NonNull String str) {
        this.title = str;
    }

    public final int getTitleBackgroundColor() {
        return this.titleBackgroundColor;
    }

    public final void setTitleBackgroundColor(int i) {
        this.titleBackgroundColor = i;
    }

    public final int getTitleSupplementaryColor() {
        return this.titleSupplementaryColor;
    }

    public final void setTitleSupplementaryColor(int i) {
        this.titleSupplementaryColor = i;
    }

    public final int getTitleTextColor() {
        return this.titleTextColor;
    }

    public final void setTitleTextColor(int i) {
        this.titleTextColor = i;
    }

    public final void load() {
        ex.newFactory(this.adConfig).a(new ex.b() { // from class: com.my.target.nativeads.NativeAppwallAd.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable e eVar, @Nullable String str) {
                NativeAppwallAd.this.handleResult(eVar, str);
            }
        }).a(this.context);
    }

    public final boolean hasNotifications() {
        Iterator<NativeAppwallBanner> it = this.bannersMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().isHasNotification()) {
                return true;
            }
        }
        return false;
    }

    public final void registerAppwallAdView(@NonNull AppwallAdView appwallAdView) {
        unregisterAppwallAdView();
        this.viewWeakReference = new WeakReference<>(appwallAdView);
        appwallAdView.setAppwallAdViewListener(new AppwallAdView.AppwallAdViewListener() { // from class: com.my.target.nativeads.NativeAppwallAd.2
            @Override // com.my.target.nativeads.views.AppwallAdView.AppwallAdViewListener
            public void onBannersShow(@NonNull List<NativeAppwallBanner> list) {
                NativeAppwallAd.this.handleBannersShow(list);
            }

            @Override // com.my.target.nativeads.views.AppwallAdView.AppwallAdViewListener
            public void onBannerClick(@NonNull NativeAppwallBanner nativeAppwallBanner) {
                AppwallAdView appwallAdView2;
                NativeAppwallAd.this.handleBannerClick(nativeAppwallBanner);
                if (NativeAppwallAd.this.viewWeakReference == null || (appwallAdView2 = (AppwallAdView) NativeAppwallAd.this.viewWeakReference.get()) == null) {
                    return;
                }
                appwallAdView2.notifyDataSetChanged();
            }
        });
    }

    public final void unregisterAppwallAdView() {
        WeakReference<AppwallAdView> weakReference = this.viewWeakReference;
        if (weakReference != null) {
            AppwallAdView appwallAdView = weakReference.get();
            if (appwallAdView != null) {
                appwallAdView.setAppwallAdViewListener(null);
            }
            this.viewWeakReference.clear();
            this.viewWeakReference = null;
        }
    }

    public final void show() {
        if (this.section != null && this.banners.size() > 0) {
            if (this.engine == null) {
                this.engine = h.a(this);
            }
            this.engine.s(this.context);
            return;
        }
        g.c("NativeAppwallAd.show: No ad");
    }

    public final void showDialog() {
        if (this.section != null && this.banners.size() > 0) {
            if (this.engine == null) {
                this.engine = h.a(this);
            }
            this.engine.showDialog(this.context);
            return;
        }
        g.c("NativeAppwallAd.show: No ad");
    }

    public final void dismiss() {
        h hVar = this.engine;
        if (hVar != null) {
            hVar.dismiss();
        }
    }

    public final void destroy() {
        unregisterAppwallAdView();
        h hVar = this.engine;
        if (hVar != null) {
            hVar.destroy();
            this.engine = null;
        }
        this.listener = null;
    }

    public final void handleBannerClick(NativeAppwallBanner nativeAppwallBanner) {
        j jVar = this.bannersMap.get(nativeAppwallBanner);
        if (jVar != null) {
            this.clickHandler.a(jVar, this.context);
            if (this.section != null) {
                nativeAppwallBanner.setHasNotification(false);
                fd.a(this.section, this.adConfig).a(jVar, this.context);
            }
            AppwallAdListener appwallAdListener = this.listener;
            if (appwallAdListener != null) {
                appwallAdListener.onClick(nativeAppwallBanner, this);
                return;
            }
            return;
        }
        g.a("unable to handle banner click: no internal banner for id " + nativeAppwallBanner.getId());
    }

    @Nullable
    public final String prepareBannerClickLink(NativeAppwallBanner nativeAppwallBanner) {
        j jVar = this.bannersMap.get(nativeAppwallBanner);
        if (jVar != null) {
            cj.a(jVar.getStatHolder().x(aq.a.dF), this.context);
            e eVar = this.section;
            if (eVar != null) {
                fd.a(eVar, this.adConfig).a(jVar, this.context);
            }
            return jVar.getTrackingLink();
        }
        g.a("unable to handle banner click: no internal banner for id " + nativeAppwallBanner.getId());
        return null;
    }

    public final void handleBannersShow(@NonNull List<NativeAppwallBanner> list) {
        ArrayList arrayList = new ArrayList();
        for (NativeAppwallBanner nativeAppwallBanner : list) {
            j jVar = this.bannersMap.get(nativeAppwallBanner);
            if (jVar != null) {
                arrayList.addAll(jVar.getStatHolder().x(aq.a.du));
            } else {
                g.a("unable to handle banner show: no internal banner for id " + nativeAppwallBanner.getId());
            }
        }
        if (arrayList.size() > 0) {
            cj.a(arrayList, this.context);
        }
    }

    public final void handleBannerShow(@NonNull NativeAppwallBanner nativeAppwallBanner) {
        j jVar = this.bannersMap.get(nativeAppwallBanner);
        if (jVar != null) {
            cj.a(jVar.getStatHolder().x(aq.a.du), this.context);
            return;
        }
        g.a("unable to handle banner show: no internal banner for id " + nativeAppwallBanner.getId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable e eVar, @Nullable String str) {
        AppwallAdListener appwallAdListener = this.listener;
        if (appwallAdListener != null) {
            if (eVar == null) {
                if (str == null) {
                    str = "no ad";
                }
                appwallAdListener.onNoAd(str, this);
                return;
            }
            this.section = eVar;
            for (j jVar : eVar.R()) {
                NativeAppwallBanner nativeAppwallBannerNewBanner = NativeAppwallBanner.newBanner(jVar);
                this.banners.add(nativeAppwallBannerNewBanner);
                this.bannersMap.put(nativeAppwallBannerNewBanner, jVar);
            }
            this.listener.onLoad(this);
        }
    }
}
