package com.chartboost.sdk.Model;

/* JADX INFO: loaded from: classes.dex */
public final class CBError {
    private final a a;
    private final String b;
    private boolean c = true;

    public enum CBClickError {
        URI_INVALID,
        URI_UNRECOGNIZED,
        AGE_GATE_FAILURE,
        NO_HOST_ACTIVITY,
        INTERNAL
    }

    public enum CBImpressionError {
        INTERNAL,
        INTERNET_UNAVAILABLE,
        TOO_MANY_CONNECTIONS,
        WRONG_ORIENTATION,
        FIRST_SESSION_INTERSTITIALS_DISABLED,
        NETWORK_FAILURE,
        NO_AD_FOUND,
        SESSION_NOT_STARTED,
        IMPRESSION_ALREADY_VISIBLE,
        NO_HOST_ACTIVITY,
        USER_CANCELLATION,
        INVALID_LOCATION,
        VIDEO_UNAVAILABLE,
        VIDEO_ID_MISSING,
        ERROR_PLAYING_VIDEO,
        INVALID_RESPONSE,
        ASSETS_DOWNLOAD_FAILURE,
        ERROR_CREATING_VIEW,
        ERROR_DISPLAYING_VIEW,
        INCOMPATIBLE_API_VERSION,
        ERROR_LOADING_WEB_VIEW,
        ASSET_PREFETCH_IN_PROGRESS,
        ACTIVITY_MISSING_IN_MANIFEST,
        EMPTY_LOCAL_VIDEO_LIST,
        END_POINT_DISABLED,
        HARDWARE_ACCELERATION_DISABLED,
        PENDING_IMPRESSION_ERROR,
        VIDEO_UNAVAILABLE_FOR_CURRENT_ORIENTATION,
        ASSET_MISSING,
        WEB_VIEW_PAGE_LOAD_TIMEOUT,
        WEB_VIEW_CLIENT_RECEIVED_ERROR,
        INTERNET_UNAVAILABLE_AT_SHOW
    }

    public enum a {
        MISCELLANEOUS,
        INTERNET_UNAVAILABLE,
        INVALID_RESPONSE,
        UNEXPECTED_RESPONSE,
        NETWORK_FAILURE,
        PUBLIC_KEY_MISSING,
        HTTP_NOT_FOUND
    }

    public CBError(a aVar, String str) {
        this.a = aVar;
        this.b = str;
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    /* JADX INFO: renamed from: com.chartboost.sdk.Model.CBError$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.MISCELLANEOUS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[a.UNEXPECTED_RESPONSE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[a.PUBLIC_KEY_MISSING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[a.INTERNET_UNAVAILABLE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[a.HTTP_NOT_FOUND.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[a.NETWORK_FAILURE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[a.INVALID_RESPONSE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    public CBImpressionError c() {
        int i = AnonymousClass1.a[this.a.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return CBImpressionError.INTERNAL;
        }
        if (i == 4) {
            return CBImpressionError.INTERNET_UNAVAILABLE;
        }
        if (i == 5) {
            return CBImpressionError.NO_AD_FOUND;
        }
        return CBImpressionError.NETWORK_FAILURE;
    }
}
