package com.my.target.nativeads.factories;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.nativeads.NativeAd;
import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.views.ChatListAdView;
import com.my.target.nativeads.views.ContentStreamAdView;
import com.my.target.nativeads.views.ContentStreamCardView;
import com.my.target.nativeads.views.ContentWallAdView;
import com.my.target.nativeads.views.MediaAdView;
import com.my.target.nativeads.views.NewsFeedAdView;
import com.my.target.nativeads.views.PromoCardRecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public class NativeViewsFactory {
    @NonNull
    public static ChatListAdView getChatListView(@NonNull NativeAd nativeAd, @NonNull Context context) {
        return getChatListView(nativeAd.getBanner(), context);
    }

    @NonNull
    public static ChatListAdView getChatListView(@NonNull Context context) {
        return new ChatListAdView(context);
    }

    @NonNull
    public static ContentStreamCardView getContentStreamCardView(@NonNull Context context) {
        return new ContentStreamCardView(context);
    }

    @NonNull
    public static ContentStreamAdView getContentStreamView(@NonNull NativeAd nativeAd, @NonNull Context context) {
        return getContentStreamView(nativeAd.getBanner(), context);
    }

    @NonNull
    public static ContentStreamAdView getContentStreamView(@Nullable NativePromoBanner nativePromoBanner, @NonNull Context context) {
        ContentStreamAdView contentStreamView = getContentStreamView(context);
        if (nativePromoBanner != null) {
            contentStreamView.setupView(nativePromoBanner);
        }
        return contentStreamView;
    }

    @NonNull
    public static ContentStreamAdView getContentStreamView(@NonNull Context context) {
        return new ContentStreamAdView(context);
    }

    @NonNull
    public static ContentWallAdView getContentWallView(@NonNull NativeAd nativeAd, @NonNull Context context) {
        return getContentWallView(nativeAd.getBanner(), context);
    }

    @NonNull
    public static ContentWallAdView getContentWallView(@NonNull Context context) {
        return new ContentWallAdView(context);
    }

    @NonNull
    public static MediaAdView getMediaAdView(@NonNull Context context) {
        return new MediaAdView(context);
    }

    @NonNull
    public static NewsFeedAdView getNewsFeedView(@NonNull NativeAd nativeAd, @NonNull Context context) {
        return getNewsFeedView(nativeAd.getBanner(), context);
    }

    @NonNull
    public static NewsFeedAdView getNewsFeedView(@NonNull Context context) {
        return new NewsFeedAdView(context);
    }

    @NonNull
    public static PromoCardRecyclerView getPromoCardRecyclerView(@NonNull Context context) {
        return new PromoCardRecyclerView(context);
    }

    @NonNull
    private static ChatListAdView getChatListView(@Nullable NativePromoBanner nativePromoBanner, @NonNull Context context) {
        ChatListAdView chatListView = getChatListView(context);
        chatListView.setupView(nativePromoBanner);
        return chatListView;
    }

    @NonNull
    private static ContentWallAdView getContentWallView(@Nullable NativePromoBanner nativePromoBanner, @NonNull Context context) {
        ContentWallAdView contentWallView = getContentWallView(context);
        contentWallView.setupView(nativePromoBanner);
        return contentWallView;
    }

    @NonNull
    private static NewsFeedAdView getNewsFeedView(@Nullable NativePromoBanner nativePromoBanner, @NonNull Context context) {
        NewsFeedAdView newsFeedView = getNewsFeedView(context);
        newsFeedView.setupView(nativePromoBanner);
        return newsFeedView;
    }
}
