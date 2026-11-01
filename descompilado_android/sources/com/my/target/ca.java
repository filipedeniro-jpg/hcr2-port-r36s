package com.my.target;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.gms.drive.DriveFile;
import java.net.URI;
import java.net.URISyntaxException;

/* JADX INFO: compiled from: WebViewBrowser.java */
/* JADX INFO: loaded from: classes2.dex */
public class ca extends LinearLayout {
    public static final int jh = ck.br();
    public static final int ji = ck.br();

    @NonNull
    public static final String jj = "Open outside";

    @NonNull
    public static final String jk = "Close";

    @NonNull
    private final ck ca;

    @NonNull
    private final ImageButton jl;

    @NonNull
    private final LinearLayout jm;

    @NonNull
    private final TextView jn;

    @NonNull
    private final TextView jo;

    @NonNull
    private final FrameLayout jp;

    @NonNull
    private final View jq;

    @NonNull
    private final FrameLayout jr;

    @NonNull
    private final ImageButton js;

    @NonNull
    private final RelativeLayout jt;

    @NonNull
    private final WebView ju;

    @NonNull
    private final ProgressBar jv;

    @Nullable
    private b jw;

    /* JADX INFO: compiled from: WebViewBrowser.java */
    public interface b {
        void bf();
    }

    @Nullable
    public b getListener() {
        return this.jw;
    }

    public void setListener(@Nullable b bVar) {
        this.jw = bVar;
    }

    public ca(@NonNull Context context) {
        super(context);
        this.jt = new RelativeLayout(context);
        this.ju = new WebView(context);
        this.jl = new ImageButton(context);
        this.jm = new LinearLayout(context);
        this.jn = new TextView(context);
        this.jo = new TextView(context);
        this.jp = new FrameLayout(context);
        this.jr = new FrameLayout(context);
        this.js = new ImageButton(context);
        this.jv = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        this.jq = new View(context);
        this.ca = ck.x(context);
    }

    public boolean canGoBack() {
        return this.ju.canGoBack();
    }

    public void goBack() {
        this.ju.goBack();
    }

    public void destroy() {
        this.ju.setWebChromeClient(null);
        this.ju.setWebViewClient(null);
        this.ju.destroy();
    }

    public void setUrl(@NonNull String str) {
        this.ju.loadUrl(str);
        this.jn.setText(K(str));
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void bb() {
        WebSettings settings = this.ju.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= 16) {
            settings.setAllowFileAccessFromFileURLs(false);
            settings.setAllowUniversalAccessFromFileURLs(false);
        }
        settings.setDomStorageEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        this.ju.setWebViewClient(new WebViewClient() { // from class: com.my.target.ca.1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                webView.loadUrl(str);
                ca.this.jn.setText(ca.this.K(str));
                return true;
            }
        });
        this.ju.setWebChromeClient(new WebChromeClient() { // from class: com.my.target.ca.2
            @Override // android.webkit.WebChromeClient
            public void onReceivedTitle(WebView webView, String str) {
                super.onReceivedTitle(webView, str);
                ca.this.jo.setText(webView.getTitle());
                ca.this.jo.setVisibility(0);
            }

            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView webView, int i) {
                if (i < 100 && ca.this.jv.getVisibility() == 8) {
                    ca.this.jv.setVisibility(0);
                    ca.this.jq.setVisibility(8);
                }
                ca.this.jv.setProgress(i);
                if (i >= 100) {
                    ca.this.jv.setVisibility(8);
                    ca.this.jq.setVisibility(0);
                }
            }
        });
        bd();
    }

    private void bd() {
        setOrientation(1);
        setGravity(16);
        a aVar = new a();
        this.ju.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        TypedValue typedValue = new TypedValue();
        int iL = this.ca.l(50);
        if (getContext().getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {
            iL = TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics());
        }
        this.jt.setLayoutParams(new LinearLayout.LayoutParams(-1, iL));
        this.jp.setLayoutParams(new LinearLayout.LayoutParams(iL, iL));
        this.jp.setId(jh);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        this.jl.setLayoutParams(layoutParams);
        this.jl.setImageBitmap(bq.b(iL / 4, this.ca.l(2)));
        this.jl.setContentDescription("Close");
        this.jl.setOnClickListener(aVar);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(iL, iL);
        if (Build.VERSION.SDK_INT >= 18) {
            layoutParams2.addRule(21);
        } else {
            layoutParams2.addRule(11);
        }
        this.jr.setLayoutParams(layoutParams2);
        this.jr.setId(ji);
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(-1, -1);
        layoutParams3.gravity = 17;
        this.js.setLayoutParams(layoutParams3);
        this.js.setImageBitmap(bq.q(getContext()));
        this.js.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.js.setContentDescription(jj);
        this.js.setOnClickListener(aVar);
        ck.a(this.jl, 0, -3355444);
        ck.a(this.js, 0, -3355444);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(1, jh);
        layoutParams4.addRule(0, ji);
        this.jm.setLayoutParams(layoutParams4);
        this.jm.setOrientation(1);
        this.jm.setPadding(this.ca.l(4), this.ca.l(4), this.ca.l(4), this.ca.l(4));
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(-2, -2);
        this.jo.setVisibility(8);
        this.jo.setLayoutParams(layoutParams5);
        this.jo.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.jo.setTextSize(2, 18.0f);
        this.jo.setSingleLine();
        this.jo.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        this.jn.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        this.jn.setSingleLine();
        this.jn.setTextSize(2, 12.0f);
        this.jn.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        ClipDrawable clipDrawable = new ClipDrawable(new ColorDrawable(-16537100), GravityCompat.START, 1);
        ColorDrawable colorDrawable = new ColorDrawable(-1968642);
        LayerDrawable layerDrawable = (LayerDrawable) this.jv.getProgressDrawable();
        layerDrawable.setDrawableByLayerId(android.R.id.background, colorDrawable);
        layerDrawable.setDrawableByLayerId(android.R.id.progress, clipDrawable);
        this.jv.setProgressDrawable(layerDrawable);
        this.jv.setLayoutParams(new LinearLayout.LayoutParams(-1, this.ca.l(2)));
        this.jv.setProgress(0);
        this.jm.addView(this.jo);
        this.jm.addView(this.jn);
        this.jp.addView(this.jl);
        this.jr.addView(this.js);
        this.jt.addView(this.jp);
        this.jt.addView(this.jm);
        this.jt.addView(this.jr);
        addView(this.jt);
        this.jq.setBackgroundColor(-5592406);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-1, 1);
        this.jq.setVisibility(8);
        this.jq.setLayoutParams(layoutParams6);
        addView(this.jv);
        addView(this.jq);
        addView(this.ju);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String K(String str) {
        try {
            URI uri = new URI(str);
            return uri.getScheme() + "://" + uri.getHost();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void be() {
        String url = this.ju.getUrl();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            if (!(getContext() instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            getContext().startActivity(intent);
        } catch (Exception unused) {
            g.a("unable to open url " + url);
        }
    }

    /* JADX INFO: compiled from: WebViewBrowser.java */
    private class a implements View.OnClickListener {
        private a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (view == ca.this.jl) {
                if (ca.this.jw != null) {
                    ca.this.jw.bf();
                }
            } else if (view == ca.this.js) {
                ca.this.be();
            }
        }
    }
}
