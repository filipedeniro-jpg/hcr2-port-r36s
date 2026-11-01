package com.my.target.nativeads.views;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.StateSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ck;
import com.my.target.nativeads.NativeAppwallAd;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class AppwallAdView extends FrameLayout implements ViewTreeObserver.OnGlobalLayoutListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {
    private static final int BACKGROUND_COLOR = -1118482;

    @Nullable
    private AppwallAdViewListener appwallAdViewListener;

    @NonNull
    private final ListView listView;

    @NonNull
    private final ck uiUtils;

    @NonNull
    private final HashMap<NativeAppwallBanner, Boolean> viewMap;

    public interface AppwallAdViewListener {
        void onBannerClick(@NonNull NativeAppwallBanner nativeAppwallBanner);

        void onBannersShow(@NonNull List<NativeAppwallBanner> list);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public AppwallAdView(@NonNull Context context) {
        super(context);
        this.viewMap = new HashMap<>();
        setVerticalFadingEdgeEnabled(false);
        setBackgroundColor(-1);
        this.uiUtils = ck.x(context);
        this.listView = new ListView(context);
        initLayout();
    }

    public void setAppwallAdViewListener(@Nullable AppwallAdViewListener appwallAdViewListener) {
        this.appwallAdViewListener = appwallAdViewListener;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        countVisibleBanners();
    }

    public void setupView(@NonNull NativeAppwallAd nativeAppwallAd) {
        this.listView.setAdapter((ListAdapter) new AppwallAdapter(getContext(), nativeAppwallAd.getBanners()));
    }

    public void notifyDataSetChanged() {
        ((AppwallAdapter) this.listView.getAdapter()).notifyDataSetChanged();
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        NativeAppwallBanner nativeAppwallBanner = (NativeAppwallBanner) this.listView.getAdapter().getItem(i);
        AppwallAdViewListener appwallAdViewListener = this.appwallAdViewListener;
        if (appwallAdViewListener != null) {
            appwallAdViewListener.onBannerClick(nativeAppwallBanner);
        }
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public void onGlobalLayout() {
        countVisibleBanners();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        countVisibleBanners();
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
    }

    private void initLayout() {
        int iL = this.uiUtils.l(4);
        int iL2 = this.uiUtils.l(4);
        this.listView.setDividerHeight(0);
        this.listView.setVerticalFadingEdgeEnabled(false);
        this.listView.setOnItemClickListener(this);
        this.listView.setOnScrollListener(this);
        this.listView.setPadding(0, iL, 0, iL2);
        this.listView.setClipToPadding(false);
        addView(this.listView, -1, -1);
        this.listView.setBackgroundColor(-1118482);
    }

    private void countVisibleBanners() {
        AppwallAdViewListener appwallAdViewListener;
        if (this.listView.getAdapter() == null) {
            return;
        }
        int lastVisiblePosition = this.listView.getLastVisiblePosition();
        ArrayList arrayList = new ArrayList();
        for (int firstVisiblePosition = this.listView.getFirstVisiblePosition(); firstVisiblePosition <= lastVisiblePosition; firstVisiblePosition++) {
            NativeAppwallBanner nativeAppwallBanner = (NativeAppwallBanner) this.listView.getAdapter().getItem(firstVisiblePosition);
            if (this.viewMap.get(nativeAppwallBanner) == null) {
                arrayList.add(nativeAppwallBanner);
                this.viewMap.put(nativeAppwallBanner, Boolean.TRUE);
            }
        }
        if (arrayList.size() <= 0 || (appwallAdViewListener = this.appwallAdViewListener) == null) {
            return;
        }
        appwallAdViewListener.onBannersShow(arrayList);
    }

    public static class AppwallAdapter extends ArrayAdapter<NativeAppwallBanner> {
        AppwallAdapter(@NonNull Context context, @NonNull List<NativeAppwallBanner> list) {
            super(context, 0, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        @NonNull
        public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
            NativeAppwallBanner item = getItem(i);
            if (view == null) {
                view = new AppwallCardPlaceholder(new AppwallAdTeaserView(getContext()), getContext());
            }
            if (item != null) {
                ((AppwallCardPlaceholder) view).getView().setNativeAppwallBanner(item);
            }
            return view;
        }
    }

    @SuppressLint({"ViewConstructor"})
    public static class AppwallCardPlaceholder extends FrameLayout {

        @NonNull
        private final LinearLayout rootLayout;

        @NonNull
        private final AppwallAdTeaserView view;

        public AppwallCardPlaceholder(@NonNull AppwallAdTeaserView appwallAdTeaserView, Context context) {
            super(context);
            ck ckVarX = ck.x(context);
            this.view = appwallAdTeaserView;
            int iL = ckVarX.l(9);
            int iL2 = ckVarX.l(4);
            int iL3 = ckVarX.l(2);
            this.rootLayout = new LinearLayout(context);
            this.rootLayout.setOrientation(1);
            this.rootLayout.setBackgroundColor(-1118482);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(iL, iL2, iL, iL2);
            appwallAdTeaserView.setLayoutParams(layoutParams);
            this.rootLayout.addView(appwallAdTeaserView);
            if (Build.VERSION.SDK_INT >= 21) {
                appwallAdTeaserView.setElevation(iL3);
                GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1, -1});
                GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1118482, -1118482});
                StateListDrawable stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(new int[]{R.attr.state_pressed}, gradientDrawable2);
                stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
                appwallAdTeaserView.setBackground(stateListDrawable);
            } else {
                View view = new View(context);
                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, iL2);
                view.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-3355444, -1118482}));
                view.setLayoutParams(layoutParams2);
                this.rootLayout.addView(view);
                GradientDrawable gradientDrawable3 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1, -1});
                gradientDrawable3.setStroke(1, -3355444);
                GradientDrawable gradientDrawable4 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-1118482, -1118482});
                gradientDrawable4.setStroke(1, -3355444);
                StateListDrawable stateListDrawable2 = new StateListDrawable();
                stateListDrawable2.addState(new int[]{R.attr.state_pressed}, gradientDrawable4);
                stateListDrawable2.addState(StateSet.WILD_CARD, gradientDrawable3);
                appwallAdTeaserView.setBackgroundDrawable(stateListDrawable2);
                layoutParams.setMargins(0, iL2, 0, 0);
                this.rootLayout.setPadding(iL, 0, iL, 0);
            }
            addView(this.rootLayout, -2, -2);
        }

        @NonNull
        public AppwallAdTeaserView getView() {
            return this.view;
        }
    }
}
