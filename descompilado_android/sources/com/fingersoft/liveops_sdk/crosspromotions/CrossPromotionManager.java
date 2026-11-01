package com.fingersoft.liveops_sdk.crosspromotions;

import com.appsflyer.AppsFlyerLib;
import com.facebook.appevents.AppEventsConstants;
import com.fingersoft.liveops_sdk.LiveopsManager;
import com.fingersoft.liveops_sdk.utils.CommonUtils;
import com.fingersoft.liveops_sdk.utils.Log;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.ironsource.sdk.constants.Constants;
import java.util.Locale;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CrossPromotionManager implements ICrossPromotionListener {
    private CrossPromotionListener mCrossPromotionListener;
    private HtmlCrossPromotionDialog mHtmlCrossPromotion;
    private LiveopsManager mLiveopsManager;
    private boolean mHasActivePromotion = false;
    private CrossPromotionPlacement mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_STARTUP;

    public enum CrossPromotionEvent {
        EVENT_GAME_COMPLETED,
        EVENT_RECORD_ACHIEVED,
        EVENT_CUSTOM_EVENT_1,
        EVENT_CUSTOM_EVENT_2,
        EVENT_CUSTOM_EVENT_3,
        EVENT_CUSTOM_EVENT_4,
        EVENT_CUSTOM_EVENT_5,
        EVENT_FORCE_SHOW_PROMOTION
    }

    public enum CrossPromotionPlacement {
        PLACEMENT_SHOW_ON_STARTUP,
        PLACEMENT_SHOW_AFTER_FIRST_GAME,
        PLACEMENT_SHOW_AFTER_RECORD,
        PLACEMENT_SHOW_ON_CUSTOM_EVENT1,
        PLACEMENT_SHOW_ON_CUSTOM_EVENT2,
        PLACEMENT_SHOW_ON_CUSTOM_EVENT3,
        PLACEMENT_SHOW_ON_CUSTOM_EVENT4,
        PLACEMENT_SHOW_ON_CUSTOM_EVENT5
    }

    public CrossPromotionManager(LiveopsManager liveopsManager, CrossPromotionListener crossPromotionListener) {
        this.mLiveopsManager = liveopsManager;
        this.mCrossPromotionListener = crossPromotionListener;
    }

    public void setCrossPromotionPlacement(CrossPromotionPlacement crossPromotionPlacement) {
        this.mCurrentPlacement = crossPromotionPlacement;
    }

    public void setCrossPromotionPlacementInt(int i) {
        if (i == 1) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_STARTUP;
            return;
        }
        if (i == 2) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_AFTER_FIRST_GAME;
            return;
        }
        if (i == 3) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_AFTER_RECORD;
            return;
        }
        if (i == 4) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT1;
            return;
        }
        if (i == 5) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT2;
            return;
        }
        if (i == 6) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT3;
        } else if (i == 7) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT4;
        } else if (i == 8) {
            this.mCurrentPlacement = CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT5;
        }
    }

    public void onEvent(CrossPromotionEvent crossPromotionEvent) {
        CommonUtils.log("Cross promotion event " + crossPromotionEvent + " received");
        CommonUtils.log("Cross promotion placement " + this.mCurrentPlacement + " received");
        switch (crossPromotionEvent) {
            case EVENT_CUSTOM_EVENT_1:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT1) {
                    showCrossPromotion(false);
                }
                break;
            case EVENT_CUSTOM_EVENT_2:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT2) {
                    showCrossPromotion(false);
                }
                break;
            case EVENT_CUSTOM_EVENT_3:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT3) {
                    showCrossPromotion(false);
                }
                break;
            case EVENT_CUSTOM_EVENT_4:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT4) {
                    showCrossPromotion(false);
                }
                break;
            case EVENT_CUSTOM_EVENT_5:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_CUSTOM_EVENT5) {
                    showCrossPromotion(false);
                }
                break;
            case EVENT_GAME_COMPLETED:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_AFTER_FIRST_GAME) {
                    showCrossPromotion(false);
                }
                break;
            case EVENT_RECORD_ACHIEVED:
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_AFTER_RECORD) {
                    showCrossPromotion(false);
                }
                if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_STARTUP) {
                    showCrossPromotion(false);
                }
                break;
        }
    }

    public void loadCrossPromotion() {
        Log.d("LiveOps SDK", "Load cross promotion");
        if (this.mHtmlCrossPromotion != null) {
            this.mHtmlCrossPromotion = null;
        }
        this.mHtmlCrossPromotion = new HtmlCrossPromotionDialog(this.mLiveopsManager, this, false, this);
        this.mHtmlCrossPromotion.execute(10000L, LiveopsManager.getPrefValueInt(this.mLiveopsManager.getActivity(), "startup_counter", 1));
    }

    private void showCrossPromotion(boolean z) {
        if (this.mHtmlCrossPromotion == null || this.mLiveopsManager == null) {
            return;
        }
        Log.d("fsad", "Promo? Is player VIP? " + Boolean.toString(this.mLiveopsManager.getVIPStatus()));
        if (this.mLiveopsManager.getVIPStatus()) {
            Log.d("fsad", "No promos for vip's");
        } else {
            this.mHtmlCrossPromotion.showPromotion(z);
        }
    }

    public boolean checkIfReshowTimeHasPassed(String str, int i) {
        long j = Long.parseLong(LiveopsManager.getPrefValueString(this.mLiveopsManager.getActivity(), getPromotionPreferenceName(str), AppEventsConstants.EVENT_PARAM_VALUE_NO));
        return (j > 0 && i == 0) || System.currentTimeMillis() - j > ((long) (((i * 60) * 60) * 1000));
    }

    public void savePromotionShowTime(String str) {
        LiveopsManager.setPrefValueString(this.mLiveopsManager.getActivity(), getPromotionPreferenceName(str), "" + System.currentTimeMillis());
    }

    private String getPromotionPreferenceName(String str) {
        return "PromotionLastShown_" + str;
    }

    @Override // com.fingersoft.liveops_sdk.crosspromotions.ICrossPromotionListener
    public void onCrossPromotionLoaded() {
        CommonUtils.log("PROMO: on promo loaded, at placement " + this.mCurrentPlacement);
        this.mHasActivePromotion = true;
        if (this.mCurrentPlacement == CrossPromotionPlacement.PLACEMENT_SHOW_ON_STARTUP) {
            showCrossPromotion(false);
        }
    }

    public boolean hasActivePromotion() {
        return this.mHasActivePromotion;
    }

    public boolean hasPromotionBeenShown() {
        CommonUtils.log("hasPromotionBeenShown()");
        HtmlCrossPromotionDialog htmlCrossPromotionDialog = this.mHtmlCrossPromotion;
        if (htmlCrossPromotionDialog != null) {
            String promotionGUID = htmlCrossPromotionDialog.getPromotionGUID();
            int prefValueInt = LiveopsManager.getPrefValueInt(this.mLiveopsManager.getActivity(), "shown_" + promotionGUID, 0);
            CommonUtils.log("Promo not null, showed value " + prefValueInt);
            return prefValueInt == 1;
        }
        CommonUtils.log("promo null, returning false");
        return false;
    }

    public void onCrossPromotionShow() {
        CommonUtils.log("onCrossPromotionShow()");
        HtmlCrossPromotionDialog htmlCrossPromotionDialog = this.mHtmlCrossPromotion;
        if (htmlCrossPromotionDialog != null) {
            String promotionGUID = htmlCrossPromotionDialog.getPromotionGUID();
            LiveopsManager.setPrefValueInt(this.mLiveopsManager.getActivity(), "shown_" + promotionGUID, 1);
            CommonUtils.log("promo not null, saving showed state (shown_" + promotionGUID + ")");
        }
        CrossPromotionListener crossPromotionListener = this.mCrossPromotionListener;
        if (crossPromotionListener != null) {
            crossPromotionListener.onCrossPromotionShow();
        }
    }

    public boolean recordCrosspromotionClick(String str) {
        CommonUtils.log("Starting POST request");
        try {
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(this.mLiveopsManager.getBaseAddress() + "api/incr_promo_clicks");
            httpPost.addHeader("content-type", "application/json");
            CommonUtils.log("POST: " + this.mLiveopsManager.getBaseAddress() + "api/startup_request");
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            Log.e("", "package_name " + this.mLiveopsManager.getPackageName());
            jSONObject2.put("jsonver", 1);
            jSONObject2.put(InMobiNetworkValues.PACKAGE_NAME, this.mLiveopsManager.getPackageName());
            jSONObject2.put("platform", this.mLiveopsManager.getPlatformName());
            jSONObject2.put("locale", Locale.getDefault().getCountry());
            jSONObject2.put(Constants.RequestParameters.NETWORK_MCC, this.mLiveopsManager.getNetworkMcc());
            jSONObject2.put("guid", str);
            jSONObject2.put(AppsFlyerLib.ATTRIBUTION_ID_COLUMN_NAME, "");
            jSONObject.put("data", jSONObject2);
            httpPost.setEntity(new StringEntity(jSONObject.toJSONString()));
            CommonUtils.log("POST: " + jSONObject.toJSONString());
            StatusLine statusLine = defaultHttpClient.execute(httpPost).getStatusLine();
            return statusLine != null && statusLine.getStatusCode() == 200;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionManager$1] */
    public void recordCrosspromotionImpression(final String str) {
        new Thread() { // from class: com.fingersoft.liveops_sdk.crosspromotions.CrossPromotionManager.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                CommonUtils.log("Starting POST request");
                try {
                    DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(CrossPromotionManager.this.mLiveopsManager.getBaseAddress() + "api/incr_promo_impressions");
                    httpPost.addHeader("content-type", "application/json");
                    CommonUtils.log("POST: " + CrossPromotionManager.this.mLiveopsManager.getBaseAddress() + "api/startup_request");
                    JSONObject jSONObject = new JSONObject();
                    JSONObject jSONObject2 = new JSONObject();
                    Log.e("", "package_name " + CrossPromotionManager.this.mLiveopsManager.getPackageName());
                    jSONObject2.put("jsonver", 1);
                    jSONObject2.put(InMobiNetworkValues.PACKAGE_NAME, CrossPromotionManager.this.mLiveopsManager.getPackageName());
                    jSONObject2.put("platform", CrossPromotionManager.this.mLiveopsManager.getPlatformName());
                    jSONObject2.put("locale", Locale.getDefault().getCountry());
                    jSONObject2.put(Constants.RequestParameters.NETWORK_MCC, CrossPromotionManager.this.mLiveopsManager.getNetworkMcc());
                    jSONObject2.put("version", Integer.valueOf(CrossPromotionManager.this.mLiveopsManager.getCurrentVersionCode()));
                    jSONObject2.put("guid", str);
                    jSONObject2.put(AppsFlyerLib.ATTRIBUTION_ID_COLUMN_NAME, "");
                    jSONObject.put("data", jSONObject2);
                    httpPost.setEntity(new StringEntity(jSONObject.toJSONString()));
                    CommonUtils.log("POST: " + jSONObject.toJSONString());
                    StatusLine statusLine = defaultHttpClient.execute(httpPost).getStatusLine();
                    if (statusLine == null || statusLine.getStatusCode() == 200) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
