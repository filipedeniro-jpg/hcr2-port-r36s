package com.fingersoft.liveops_sdk.news;

import android.app.Activity;
import com.facebook.appevents.AppEventsConstants;
import com.fingersoft.liveops_sdk.LiveopsManager;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GameNews {
    private static final String ON_NEWS_POPUP_SHOWN_PREF = "GAME_NEWS_POPUP_LAST_SHOWN";
    private static final String ON_NEWS_READ_PREF = "GAME_NEWS_LAST_READ";
    private static GameNews mInstance;
    private Activity mActivity;
    private GameNewsListener mListener;
    private JSONObject mNewsData;

    public GameNews(Activity activity) {
        this.mActivity = activity;
    }

    public static GameNews getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new GameNews(activity);
        }
        return mInstance;
    }

    private long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    public void onNewsRead() {
        LiveopsManager.setPrefValueString(this.mActivity, ON_NEWS_READ_PREF, "" + getCurrentTime());
    }

    public void onNewsPopupShown() {
        LiveopsManager.setPrefValueString(this.mActivity, ON_NEWS_POPUP_SHOWN_PREF, "" + getCurrentTime());
    }

    public long getNewsReadTimestamp() {
        return Long.parseLong(LiveopsManager.getPrefValueString(this.mActivity, ON_NEWS_READ_PREF, AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public long getNewsPopupShownTimestamp() {
        return Long.parseLong(LiveopsManager.getPrefValueString(this.mActivity, ON_NEWS_POPUP_SHOWN_PREF, AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public void setNewsData(JSONObject jSONObject) {
        this.mNewsData = jSONObject;
        GameNewsListener gameNewsListener = this.mListener;
        if (gameNewsListener != null) {
            gameNewsListener.onNewsDataAvailable(jSONObject);
        }
    }

    public JSONObject getNewsData() {
        return this.mNewsData;
    }

    public void setListener(GameNewsListener gameNewsListener) {
        this.mListener = gameNewsListener;
    }
}
