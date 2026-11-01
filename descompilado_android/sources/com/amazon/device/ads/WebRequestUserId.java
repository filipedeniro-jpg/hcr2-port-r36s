package com.amazon.device.ads;

/* JADX INFO: loaded from: classes.dex */
class WebRequestUserId {
    private final AdvertisingIdParameter adIdParam;
    private final Settings settings;
    private UserIdParameter userIdParam;

    public WebRequestUserId() {
        this(Settings.getInstance(), new AdvertisingIdParameter());
    }

    WebRequestUserId(Settings settings, AdvertisingIdParameter advertisingIdParameter) {
        this.settings = settings;
        this.adIdParam = advertisingIdParameter;
    }

    private void setupUserIdParam() {
        if (this.userIdParam == null) {
            this.userIdParam = (UserIdParameter) this.settings.getObject(UserIdParameter.SETTINGS_KEY, this.adIdParam, UserIdParameter.class);
        }
    }

    public boolean populateWebRequestUserId(WebRequest webRequest) {
        setupUserIdParam();
        boolean zEvaluate = this.userIdParam.evaluate(webRequest);
        if (zEvaluate) {
            return zEvaluate;
        }
        UserIdParameter userIdParameter = this.userIdParam;
        AdvertisingIdParameter advertisingIdParameter = this.adIdParam;
        return userIdParameter != advertisingIdParameter ? advertisingIdParameter.evaluate(webRequest) : zEvaluate;
    }
}
