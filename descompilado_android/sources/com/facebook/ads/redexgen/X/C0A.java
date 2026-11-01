package com.facebook.ads.redexgen.X;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.applovin.sdk.AppLovinEventParameters;
import com.google.android.gms.drive.DriveFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.0A, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0A {
    private static final String B = C0A.class.getSimpleName();

    private C0A() {
    }

    @Nullable
    public static AnonymousClass09 B(Context context, JV adEventManager, String clientToken, Uri uri, Map<String, String> extraData) {
        return C(context, adEventManager, clientToken, uri, extraData, true, false);
    }

    @Nullable
    public static AnonymousClass09 C(final Context context, final JV adEventManager, final String clientToken, final Uri uri, final Map<String, String> map, final boolean actionEnabled, boolean useWatchAndXBrowser) {
        if (uri == null || uri.getAuthority() == null) {
            return null;
        }
        String authority = uri.getAuthority();
        String action = uri.getQueryParameter("video_url");
        String videoURL = uri.getQueryParameter("data");
        if (!TextUtils.isEmpty(videoURL)) {
            try {
                String dataJson = uri.getQueryParameter("data");
                JSONObject jSONObject = new JSONObject(dataJson);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.getString(next));
                }
            } catch (JSONException e) {
                Log.w(B, "Unable to parse json data in AdActionFactory.", e);
            }
        }
        final C0M c0mB = C0M.B(adEventManager, MI.B());
        switch (authority) {
            case "store":
                if (action == null) {
                    return new C0E(context, adEventManager, clientToken, uri, map, c0mB, actionEnabled) { // from class: com.facebook.ads.redexgen.X.0F
                        private static final String D = C0F.class.getSimpleName();
                        private final Map<String, String> B;
                        private final Uri C;

                        {
                            this.C = uri;
                            this.B = map;
                        }

                        private Intent B(C0G appSiteData) {
                            if (!TextUtils.isEmpty(appSiteData.C()) && C0D.B(this.D, appSiteData.C())) {
                                String strA = appSiteData.A();
                                if (!TextUtils.isEmpty(strA) && (strA.startsWith("tel:") || strA.startsWith("telprompt:"))) {
                                    return new Intent("android.intent.action.CALL", Uri.parse(strA));
                                }
                                PackageManager packageManager = this.D.getPackageManager();
                                if (TextUtils.isEmpty(appSiteData.B()) && TextUtils.isEmpty(strA)) {
                                    return packageManager.getLaunchIntentForPackage(appSiteData.C());
                                }
                                Intent intentD = D(appSiteData);
                                List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intentD, 65536);
                                if (intentD.getComponent() == null) {
                                    for (ResolveInfo resolveInfo : listQueryIntentActivities) {
                                        if (resolveInfo.activityInfo.packageName.equals(appSiteData.C())) {
                                            intentD.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                                            break;
                                        }
                                    }
                                }
                                if (listQueryIntentActivities.isEmpty() || intentD.getComponent() == null) {
                                    return null;
                                }
                                return intentD;
                            }
                            return null;
                        }

                        @Override // com.facebook.ads.redexgen.X.AnonymousClass09
                        @Nullable
                        public final AnonymousClass08 B() {
                            AnonymousClass08 anonymousClass08 = null;
                            String str = "opened_deeplink";
                            if (!H()) {
                                try {
                                    str = G() ? "opened_store_url" : "opened_store_fallback_url";
                                } catch (Exception e2) {
                                    Log.d(D, "Failed to open all options including fallback url, can't open anything");
                                    anonymousClass08 = AnonymousClass08.CANNOT_OPEN;
                                }
                            }
                            Map<String, String> map2 = this.B;
                            String redirectionAction = String.valueOf(true);
                            map2.put(str, redirectionAction);
                            return anonymousClass08;
                        }

                        private final List<Intent> C() {
                            List<C0G> listE = E();
                            ArrayList arrayList = new ArrayList();
                            if (listE != null) {
                                Iterator<C0G> it = listE.iterator();
                                while (it.hasNext()) {
                                    Intent intentB = B(it.next());
                                    if (intentB != null) {
                                        arrayList.add(intentB);
                                    }
                                }
                            }
                            return arrayList;
                        }

                        private Intent D(C0G appSiteData) {
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addFlags(DriveFile.MODE_READ_ONLY);
                            if (!TextUtils.isEmpty(appSiteData.C()) && !TextUtils.isEmpty(appSiteData.B())) {
                                intent.setComponent(new ComponentName(appSiteData.C(), appSiteData.B()));
                            }
                            if (!TextUtils.isEmpty(appSiteData.A())) {
                                intent.setData(Uri.parse(appSiteData.A()));
                            }
                            return intent;
                        }

                        @Override // com.facebook.ads.redexgen.X.C0E
                        public final void D() {
                            AnonymousClass08 anonymousClass08B = null;
                            if (((C0E) this).B) {
                                anonymousClass08B = B();
                            } else {
                                this.B.put("opened_store_url", String.valueOf(true));
                            }
                            C(this.B, anonymousClass08B);
                        }

                        private List<C0G> E() {
                            String appsiteDataString = this.C.getQueryParameter("appsite_data");
                            if (TextUtils.isEmpty(appsiteDataString) || "[]".equals(appsiteDataString)) {
                                return null;
                            }
                            ArrayList arrayList = new ArrayList();
                            try {
                                JSONArray jSONArrayOptJSONArray = new JSONObject(appsiteDataString).optJSONArray("android");
                                if (jSONArrayOptJSONArray == null) {
                                    return arrayList;
                                }
                                for (int i = 0; i < i; i++) {
                                    C0G c0gB = C0G.B(jSONArrayOptJSONArray.optJSONObject(i));
                                    if (c0gB != null) {
                                        arrayList.add(c0gB);
                                    }
                                }
                                return arrayList;
                            } catch (JSONException e2) {
                                Log.w(D, "Error parsing appsite_data", e2);
                                return arrayList;
                            }
                        }

                        private final Uri F() {
                            String storeUrl = this.C.getQueryParameter("store_url");
                            if (!TextUtils.isEmpty(storeUrl)) {
                                return Uri.parse(storeUrl);
                            }
                            return Uri.parse(String.format(Locale.US, "market://details?id=%s", this.C.getQueryParameter(AppLovinEventParameters.IN_APP_PURCHASE_TRANSACTION_IDENTIFIER)));
                        }

                        private boolean G() {
                            I7 i7 = new I7();
                            try {
                                I7.B(i7, this.D, F(), ((AnonymousClass09) this).C);
                                return true;
                            } catch (Exception e2) {
                                Log.d(D, "Failed to open market url: " + this.C.toString(), e2);
                                String queryParameter = this.C.getQueryParameter("store_url_web_fallback");
                                if (queryParameter != null && queryParameter.length() > 0) {
                                    I7.B(i7, this.D, Uri.parse(queryParameter), ((AnonymousClass09) this).C);
                                }
                                return false;
                            }
                        }

                        private boolean H() {
                            List<Intent> listC = C();
                            if (listC == null) {
                                return false;
                            }
                            Iterator<Intent> it = listC.iterator();
                            while (it.hasNext()) {
                                try {
                                    this.D.startActivity(it.next());
                                    return true;
                                } catch (Exception e2) {
                                    Log.d(D, "Failed to open app intent, falling back", e2);
                                }
                            }
                            return false;
                        }
                    };
                }
                return null;
            case "open_link":
                return useWatchAndXBrowser ? new C0H(context, adEventManager, clientToken, uri, map) : new C0E(context, adEventManager, clientToken, uri, map, c0mB, actionEnabled) { // from class: com.facebook.ads.redexgen.X.0I
                    private static final String D = C0I.class.getSimpleName();
                    private final Map<String, String> B;
                    private final Uri C;

                    {
                        this.C = uri;
                        this.B = map;
                    }

                    @Override // com.facebook.ads.redexgen.X.AnonymousClass09
                    @Nullable
                    public final AnonymousClass08 B() {
                        try {
                            I7.B(new I7(), this.D, Uri.parse(this.C.getQueryParameter("link")), ((AnonymousClass09) this).C);
                            return null;
                        } catch (Exception e2) {
                            Log.d(D, "Failed to open link url: " + this.C.toString(), e2);
                            return AnonymousClass08.CANNOT_OPEN;
                        }
                    }

                    @Override // com.facebook.ads.redexgen.X.C0E
                    public final void D() {
                        AnonymousClass08 anonymousClass08B = null;
                        if (((C0E) this).B) {
                            anonymousClass08B = B();
                        }
                        C(this.B, anonymousClass08B);
                    }
                };
            case "passthrough":
                return new AnonymousClass09(context, adEventManager, clientToken, uri, map) { // from class: com.facebook.ads.redexgen.X.0J
                    private static final String D = C0J.class.getSimpleName();
                    private final Map<String, String> B;
                    private final Uri C;

                    {
                        this.C = uri;
                        this.B = map;
                    }

                    @Override // com.facebook.ads.redexgen.X.AnonymousClass09
                    public final void A() {
                        JZ jz = JZ.IMMEDIATE;
                        String queryParameter = this.C.getQueryParameter("priority");
                        if (!TextUtils.isEmpty(queryParameter)) {
                            try {
                                jz = JZ.values()[Integer.valueOf(queryParameter).intValue()];
                            } catch (Exception e2) {
                            }
                        }
                        super.B.qC(super.C, this.B, this.C.getQueryParameter("type"), jz);
                    }
                };
            default:
                return new AnonymousClass09(context, adEventManager, clientToken, uri) { // from class: com.facebook.ads.redexgen.X.0K
                    private static final String C = C0K.class.getSimpleName();
                    private final Uri B;

                    {
                        this.B = uri;
                    }

                    @Override // com.facebook.ads.redexgen.X.AnonymousClass09
                    public final void A() {
                        try {
                            Log.w("REDIRECTACTION: ", this.B.toString());
                            I7.B(new I7(), this.D, this.B, this.C);
                        } catch (Exception e2) {
                            Log.d(C, "Failed to open link url: " + this.B.toString(), e2);
                        }
                    }
                };
        }
    }

    public static boolean D(String uriAuthority) {
        return "store".equalsIgnoreCase(uriAuthority) || "open_link".equalsIgnoreCase(uriAuthority);
    }
}
