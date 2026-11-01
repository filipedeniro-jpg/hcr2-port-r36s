package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.ads.MediaView;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.sdk.constants.Constants;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.16, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class AnonymousClass16 extends C0Q {
    private View B;
    private List<View> C;

    @Nullable
    private String D;
    private boolean E;
    private AnonymousClass14 F;
    private boolean G;
    private AnonymousClass15 H;

    @Nullable
    private View I;
    private final C01360p J;
    private KJ K;
    private boolean L;
    private boolean M;
    private boolean N;

    public AnonymousClass16(Context context, C0R impressionHelper, C5I viewabilityChecker, C01360p nativeAdapter) {
        super(context, impressionHelper, viewabilityChecker);
        this.F = AnonymousClass14.ALL;
        this.H = null;
        this.J = nativeAdapter;
    }

    private String B(View mAdView) {
        if (mAdView.getWidth() <= 0 || mAdView.getHeight() <= 0) {
            return "";
        }
        try {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(mAdView.getWidth(), mAdView.getHeight(), Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.setDensity(mAdView.getResources().getDisplayMetrics().densityDpi);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            mAdView.draw(canvas);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, this.J.N(), byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        } catch (Exception e) {
            return "";
        }
    }

    @Override // com.facebook.ads.redexgen.X.C0Q
    public final void B(Map<String, String> map) {
        if (this.J == null) {
            return;
        }
        if (this.K != null) {
            map.put("nti", String.valueOf(this.K.B()));
        }
        if (this.E) {
            map.put("nhs", Boolean.TRUE.toString());
        }
        if (this.N) {
            map.put("nmv", Boolean.TRUE.toString());
        }
        if (this.G) {
            map.put("nmvap", Boolean.TRUE.toString());
        }
        if (this.B != null && this.J.Y()) {
            map.put(Constants.ParametersKeys.VIEW, D(this.B));
        }
        if (this.B != null && this.J.Z()) {
            map.put("snapshot", B(this.B));
        }
        if (this.M) {
            map.put("niv", Boolean.TRUE.toString());
        }
        if (this.F != null) {
            map.put("precache_media", this.F.toString());
        }
        if (this.L) {
            map.put("ucvr", Boolean.TRUE.toString());
        }
        if (this.I != null) {
            map.put("namw", String.valueOf((int) (this.I.getWidth() / J6.B)));
            map.put("namh", String.valueOf((int) (this.I.getHeight() / J6.B)));
        }
        if (this.H != null) {
            map.put("narar", this.H.toString());
        }
        if (this.D != null) {
            map.put("extra_hints", this.D);
        }
        this.J.d(map);
    }

    private JSONObject C(View mAdView) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.putOpt("id", Integer.valueOf(mAdView.getId()));
        jSONObject.putOpt("class", mAdView.getClass());
        jSONObject.putOpt("origin", String.format(Locale.US, "{x:%d, y:%d}", Integer.valueOf(mAdView.getTop()), Integer.valueOf(mAdView.getLeft())));
        jSONObject.putOpt("size", String.format(Locale.US, "{h:%d, w:%d}", Integer.valueOf(mAdView.getHeight()), Integer.valueOf(mAdView.getWidth())));
        jSONObject.putOpt("clickable", Boolean.valueOf(this.C != null && this.C.contains(mAdView)));
        String str = "unknown";
        if (mAdView instanceof Button) {
            str = "button";
        } else if (mAdView instanceof TextView) {
            str = MimeTypes.BASE_TYPE_TEXT;
        } else if (mAdView instanceof ImageView) {
            str = MessengerShareContentUtility.MEDIA_IMAGE;
        } else if (mAdView instanceof MediaView) {
            str = "mediaview";
        } else if (mAdView instanceof ViewGroup) {
            str = "viewgroup";
        }
        jSONObject.putOpt("type", str);
        if (mAdView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) mAdView;
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                jSONArray.put(C(viewGroup.getChildAt(i)));
            }
            jSONObject.putOpt("list", jSONArray);
        }
        return jSONObject;
    }

    public final void C(List<View> clickableViews) {
        this.C = clickableViews;
    }

    private String D(View mAdView) {
        try {
            return C(mAdView).toString();
        } catch (JSONException e) {
            return "Json exception";
        }
    }

    public final void D(@Nullable String extraHints) {
        this.D = extraHints;
    }

    public final void E(boolean isHScroll) {
        this.E = isHScroll;
    }

    public final void F(AnonymousClass14 mediaCacheFlagLog) {
        this.F = mediaCacheFlagLog;
    }

    public final void G(boolean mediaViewAutoplay) {
        this.G = mediaViewAutoplay;
    }

    public final void H(AnonymousClass15 reason) {
        this.H = reason;
    }

    public final void I(@Nullable View nativeAdView) {
        this.I = nativeAdView;
    }

    public final void J(KJ nativeViewType) {
        this.K = nativeViewType;
    }

    public final void K(boolean usedByCustomVideoRenderer) {
        this.L = usedByCustomVideoRenderer;
    }

    public final void L(boolean usedByIconView) {
        this.M = usedByIconView;
    }

    public final void M(boolean usedByMediaView) {
        this.N = usedByMediaView;
    }

    public final void N(View adView) {
        this.B = adView;
    }
}
