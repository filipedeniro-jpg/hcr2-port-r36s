package com.vungle.warren.ui;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.facebook.internal.ServerProtocol;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.my.target.bj;
import com.vungle.warren.AdvertisementPresenterFactory;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.utility.ViewUtility;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class VungleActivity extends Activity implements AdView, MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener {
    protected static final double NINE_BY_SIXTEEN_ASPECT_RATIO = 0.5625d;
    public static final String PLACEMENT_EXTRA = "placement";
    private static final String TAG = "VungleActivity";
    private static AdvertisementPresenter.EventListener bus;
    private BroadcastReceiver broadcastReciever;
    private ImageView closeButton;
    private ImageView ctaOverlay;
    private AlertDialog dialog;
    private MediaPlayer mediaPlayer;
    private ImageView muteButton;
    private boolean pendingPause;
    private String placementId;
    private AdvertisementPresenter presenter;
    private AdvertisementPresenterFactory presenterFactory;
    private ImageView privacyOverlay;
    private ProgressBar progressBar;
    private Runnable reportProgress;
    private VideoView videoView;
    private ReactiveVideoTracker viewabilityTracker;
    private WebView webView;
    private Handler handler = new Handler();
    private int videoPosition = 0;
    private boolean muted = false;
    private boolean released = false;

    protected boolean canRotate() {
        return true;
    }

    @Override // com.vungle.warren.ui.AdView
    public void setVisibility(boolean z) {
    }

    public static void setEventListener(AdvertisementPresenter.EventListener eventListener) {
        bus = eventListener;
    }

    @Override // android.app.Activity
    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(16777216, 16777216);
        Resources resources = getResources();
        RelativeLayout relativeLayout = new RelativeLayout(this);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        relativeLayout.setLayoutParams(layoutParams);
        this.videoView = new VideoView(this);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        this.videoView.setLayoutParams(layoutParams2);
        relativeLayout.addView(this.videoView, layoutParams2);
        this.webView = new WebView(this);
        this.webView.setLayoutParams(layoutParams);
        relativeLayout.addView(this.webView, layoutParams);
        this.progressBar = new ProgressBar(this, null, R.attr.progressBarStyleHorizontal);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 4.0f, resources.getDisplayMetrics()));
        layoutParams3.addRule(12);
        this.progressBar.setLayoutParams(layoutParams3);
        this.progressBar.setMax(100);
        this.progressBar.setIndeterminate(false);
        this.progressBar.setVisibility(4);
        relativeLayout.addView(this.progressBar);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 24.0f, resources.getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(iApplyDimension, iApplyDimension);
        int iApplyDimension2 = (int) TypedValue.applyDimension(1, 12.0f, resources.getDisplayMetrics());
        layoutParams4.setMargins(iApplyDimension2, iApplyDimension2, iApplyDimension2, iApplyDimension2);
        this.muteButton = new ImageView(this);
        this.muteButton.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.unMute, this));
        this.muteButton.setLayoutParams(layoutParams4);
        this.muteButton.setVisibility(8);
        relativeLayout.addView(this.muteButton);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(iApplyDimension, iApplyDimension);
        layoutParams5.setMargins(iApplyDimension2, iApplyDimension2, iApplyDimension2, iApplyDimension2);
        this.closeButton = new ImageView(this);
        this.closeButton.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.close, this));
        layoutParams5.addRule(11);
        this.closeButton.setLayoutParams(layoutParams5);
        this.closeButton.setVisibility(8);
        relativeLayout.addView(this.closeButton);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(iApplyDimension, iApplyDimension);
        layoutParams6.addRule(12);
        layoutParams6.addRule(11);
        layoutParams6.setMargins(iApplyDimension2, iApplyDimension2, iApplyDimension2, iApplyDimension2);
        this.ctaOverlay = new ImageView(this);
        this.ctaOverlay.setLayoutParams(layoutParams6);
        this.ctaOverlay.setVisibility(8);
        relativeLayout.addView(this.ctaOverlay);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(iApplyDimension, iApplyDimension);
        layoutParams7.addRule(12);
        layoutParams7.addRule(9);
        layoutParams7.setMargins(iApplyDimension2, iApplyDimension2, iApplyDimension2, iApplyDimension2);
        this.privacyOverlay = new ImageView(this);
        this.privacyOverlay.setLayoutParams(layoutParams7);
        this.privacyOverlay.setVisibility(8);
        relativeLayout.addView(this.privacyOverlay);
        setContentView(relativeLayout, layoutParams);
        if (!Vungle.isInitialized() || bus == null) {
            finish();
            return;
        }
        String stringExtra = getIntent().hasExtra("userID") ? getIntent().getStringExtra("userID") : null;
        this.placementId = getIntent().getStringExtra("placement");
        this.presenterFactory = new AdvertisementPresenterFactory();
        this.presenter = this.presenterFactory.getAdPresenter(this.placementId, bundle, stringExtra);
        AdvertisementPresenter advertisementPresenter = this.presenter;
        if (advertisementPresenter == null) {
            AdvertisementPresenter.EventListener eventListener = bus;
            if (eventListener != null) {
                eventListener.onError(new VungleException(10));
            }
            finish();
            return;
        }
        advertisementPresenter.setEventListener(bus);
        this.presenter.attach(this);
        this.presenter.prepare(bundle);
        prepare(bundle);
    }

    private void prepare(Bundle bundle) {
        this.videoView.setOnPreparedListener(this);
        this.webView.setWebViewClient(this.presenter.getWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(new JavascriptBridge(this.presenter), Constants.JAVASCRIPT_INTERFACE_NAME);
        if (Build.VERSION.SDK_INT >= 17 && Build.VERSION.SDK_INT <= 19) {
            this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.videoView.setVisibility(8);
        this.webView.setVisibility(8);
    }

    private void connectBroadcastReceiver() {
        this.broadcastReciever = new BroadcastReceiver() { // from class: com.vungle.warren.ui.VungleActivity.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String stringExtra = intent.getStringExtra("command");
                if (((stringExtra.hashCode() == -482896367 && stringExtra.equals("closeFlex")) ? (byte) 0 : (byte) -1) == 0) {
                    if (VungleActivity.this.presenter.handleExit(intent.getStringExtra("placement"))) {
                        VungleActivity.this.close();
                    }
                } else {
                    throw new IllegalArgumentException("No such command " + stringExtra);
                }
            }
        };
        registerReceiver(this.broadcastReciever, new IntentFilter("AdvertisementBus"));
    }

    @Override // android.app.Activity
    protected void onStart() {
        connectBroadcastReceiver();
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.seekTo(this.videoPosition);
        }
        setupPlayerProgressBar();
    }

    @Override // android.app.Activity
    protected void onStop() {
        Runnable runnable = this.reportProgress;
        if (runnable != null) {
            this.handler.removeCallbacks(runnable);
        }
        unregisterReceiver(this.broadcastReciever);
        super.onStop();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            resumeAd();
        } else {
            pauseAd();
        }
    }

    private void pauseAd() {
        WebView webView = this.webView;
        if (webView != null) {
            webView.onPause();
        }
        if (!this.released && this.videoView.isPlaying()) {
            this.videoView.pause();
            this.videoPosition = this.videoView.getCurrentPosition();
        }
        this.presenter.stop(isChangingConfigurations(), isFinishing());
    }

    private void resumeAd() {
        setImmersiveMode();
        WebView webView = this.webView;
        if (webView != null) {
            webView.onResume();
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            if (!this.videoView.isPlaying() && this.mediaPlayer != null) {
                this.videoView.requestFocus();
                this.videoView.seekTo(this.videoPosition);
                this.videoView.start();
                return;
            }
            this.presenter.play();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            Log.d(TAG, "landscape");
        } else if (configuration.orientation == 1) {
            Log.d(TAG, "portrait");
        }
        this.presenter.notifyPropertiesChanged();
    }

    @Override // android.app.Activity
    @SuppressLint({"ResourceType"})
    public void onBackPressed() {
        if (this.presenter.handleExit(null)) {
            close();
        }
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onRestoreInstanceState(bundle, persistableBundle);
        if (bundle != null) {
            this.videoPosition = bundle.getInt("videoPosition");
        }
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        AlertDialog alertDialog;
        this.mediaPlayer = mediaPlayer;
        this.muteButton.setVisibility(0);
        if (this.muted) {
            mediaPlayer.setVolume(0.0f, 0.0f);
        }
        mediaPlayer.seekTo(this.videoPosition);
        mediaPlayer.start();
        this.presenter.initializeViewabilityTracker(mediaPlayer.getDuration(), this.videoView);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.vungle.warren.ui.VungleActivity.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer2) {
                Log.d(VungleActivity.TAG, "mediaplayer onCompletion");
                if (VungleActivity.this.reportProgress != null) {
                    VungleActivity.this.handler.removeCallbacks(VungleActivity.this.reportProgress);
                }
                VungleActivity.this.presenter.onProgressUpdate(100);
                VungleActivity.this.runOnUiThread(new Runnable() { // from class: com.vungle.warren.ui.VungleActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        VungleActivity.this.muteButton.setEnabled(false);
                        VungleActivity.this.presenter.stopViewabilityTracker();
                    }
                });
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.vungle.warren.ui.VungleActivity.3
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                return i != 100 ? true : true;
            }
        });
        this.presenter.reportAction("videoLength", String.format(Locale.ENGLISH, "%d", Integer.valueOf(mediaPlayer.getDuration())));
        setupPlayerProgressBar();
        if (this.pendingPause || ((alertDialog = this.dialog) != null && alertDialog.isShowing())) {
            this.pendingPause = false;
            this.videoView.pause();
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d(TAG, "onSaveInstanceState");
        this.presenter.generateSaveState(bundle);
        this.presenterFactory.saveState(bundle);
        if (this.videoView != null) {
            bundle.putInt("videoPosition", this.videoPosition);
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        Log.d(TAG, "onRestoreInstanceState(" + bundle + ")");
        this.presenter.restoreFromSave(bundle);
        if (bundle != null) {
            this.videoPosition = bundle.getInt("videoPosition", 0);
        }
    }

    @Override // com.vungle.warren.ui.AdView
    public void updateWindow(boolean z) {
        RelativeLayout.LayoutParams layoutParams;
        if (z) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.heightPixels;
            int i2 = displayMetrics.widthPixels;
            int i3 = getResources().getConfiguration().orientation;
            if (i3 == 1) {
                getWindow().setGravity(83);
                Window window = getWindow();
                double d = i2;
                Double.isNaN(d);
                double d2 = d * NINE_BY_SIXTEEN_ASPECT_RATIO;
                window.setLayout(i2, (int) Math.round(d2));
                layoutParams = new RelativeLayout.LayoutParams(i2, (int) d2);
            } else if (i3 == 2) {
                Window window2 = getWindow();
                double d3 = i;
                Double.isNaN(d3);
                double d4 = d3 * NINE_BY_SIXTEEN_ASPECT_RATIO;
                window2.setLayout((int) Math.round(d4), i);
                getWindow().setGravity(85);
                layoutParams = new RelativeLayout.LayoutParams((int) Math.round(d4), i);
                layoutParams.addRule(11, -1);
            } else {
                layoutParams = null;
            }
            this.webView.setLayoutParams(layoutParams);
            getWindow().addFlags(288);
            return;
        }
        getWindow().setFlags(1024, 1024);
        getWindow().getDecorView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        if (i == 1) {
            sb.append("MEDIA_ERROR_UNKNOWN");
        } else if (i == 100) {
            sb.append("MEDIA_ERROR_SERVER_DIED");
        } else {
            sb.append("UNKNOWN");
        }
        sb.append(":");
        if (i2 == -1010) {
            sb.append("MEDIA_ERROR_UNSUPPORTED");
        } else if (i2 == -1007) {
            sb.append("MEDIA_ERROR_MALFORMED");
        } else if (i2 == -1004) {
            sb.append("MEDIA_ERROR_IO");
        } else if (i2 == -110) {
            sb.append("MEDIA_ERROR_TIMED_OUT");
        } else if (i2 == 200) {
            sb.append("MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
        } else {
            sb.append("MEDIA_ERROR_SYSTEM");
        }
        this.presenter.reportError(sb.toString());
        return true;
    }

    @Override // com.vungle.warren.ui.AdView
    public void setOrientation(int i) {
        Log.d(TAG, " requested orientation " + i);
        if (canRotate()) {
            setRequestedOrientation(i);
        }
    }

    @Override // com.vungle.warren.ui.AdView
    public void playVideo(Uri uri, boolean z) {
        this.videoView.setVisibility(0);
        this.videoView.setOnErrorListener(this);
        this.videoView.setVideoURI(uri);
        this.muted = z;
        if (this.muted) {
            this.presenter.reportAction(bj.gv, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        final Bitmap bitmap = ViewUtility.getBitmap(ViewUtility.Asset.mute, this);
        final Bitmap bitmap2 = ViewUtility.getBitmap(ViewUtility.Asset.unMute, this);
        this.privacyOverlay.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.privacy, this));
        this.privacyOverlay.setVisibility(0);
        this.privacyOverlay.setOnClickListener(new View.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VungleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://vungle.com/privacy/")));
            }
        });
        this.muteButton.setImageBitmap(this.muted ? bitmap : bitmap2);
        final AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.muteButton.setOnClickListener(new View.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (VungleActivity.this.mediaPlayer != null) {
                    if (!VungleActivity.this.muted) {
                        VungleActivity.this.mediaPlayer.setVolume(0.0f, 0.0f);
                        VungleActivity.this.muted = true;
                        VungleActivity.this.presenter.reportAction(bj.gv, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    } else {
                        float streamVolume = audioManager.getStreamVolume(3) / audioManager.getStreamMaxVolume(3);
                        VungleActivity.this.mediaPlayer.setVolume(streamVolume, streamVolume);
                        VungleActivity.this.muted = false;
                        VungleActivity.this.presenter.reportAction(bj.gw, "false");
                    }
                    VungleActivity.this.muteButton.setImageBitmap(VungleActivity.this.muted ? bitmap : bitmap2);
                }
            }
        });
        this.progressBar.setVisibility(0);
        this.progressBar.setMax(this.videoView.getDuration());
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VungleActivity.this.onBackPressed();
            }
        });
    }

    @Override // com.vungle.warren.ui.AdView
    public void showWebsite(String str) {
        Log.d(TAG, "loadJs: " + str);
        this.webView.loadUrl(str);
        this.webView.setVisibility(0);
        VideoView videoView = this.videoView;
        if (videoView != null) {
            videoView.stopPlayback();
            this.handler.removeCallbacks(this.reportProgress);
            this.mediaPlayer = null;
        }
        this.videoView.setVisibility(4);
        this.progressBar.setVisibility(8);
        this.closeButton.setVisibility(8);
        this.muteButton.setVisibility(8);
        this.ctaOverlay.setVisibility(8);
    }

    @Override // com.vungle.warren.ui.AdView
    public String getWebsiteUrl() {
        return this.webView.getUrl();
    }

    @Override // com.vungle.warren.ui.AdView
    public void close() {
        runOnUiThread(new Runnable() { // from class: com.vungle.warren.ui.VungleActivity.7
            @Override // java.lang.Runnable
            public void run() {
                if (VungleActivity.this.videoView != null && VungleActivity.this.videoView.isPlaying()) {
                    VungleActivity.this.presenter.stopViewabilityTracker();
                }
                VungleActivity.this.webView.removeJavascriptInterface(Constants.JAVASCRIPT_INTERFACE_NAME);
                VungleActivity.this.webView.loadUrl("about:blank");
            }
        });
        finish();
    }

    @Override // com.vungle.warren.ui.AdView
    public void showCloseButton() {
        this.closeButton.setVisibility(0);
    }

    @Override // com.vungle.warren.ui.AdView
    public void showCTAOverlay(boolean z, boolean z2, final int i) {
        if (z) {
            this.videoView.setOnClickListener(new View.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VungleActivity.this.ctaOverlay.setVisibility(0);
                    VungleActivity.this.ctaOverlay.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.cta, VungleActivity.this));
                    VungleActivity.this.ctaOverlay.setEnabled(true);
                }
            });
        } else {
            this.ctaOverlay.setVisibility(0);
            this.ctaOverlay.setImageBitmap(ViewUtility.getBitmap(z2 ? ViewUtility.Asset.cta : ViewUtility.Asset.ctaDisabled, this));
            this.ctaOverlay.setEnabled(z2);
        }
        if (i == 100) {
            this.videoView.setOnClickListener(new View.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VungleActivity.this.presenter.reportAction(InMobiNetworkValues.CTA, "");
                    VungleActivity.this.presenter.handleAction("download");
                }
            });
        } else {
            final View view = (View) this.ctaOverlay.getParent();
            this.ctaOverlay.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.vungle.warren.ui.VungleActivity.10
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    VungleActivity.this.ctaOverlay.getViewTreeObserver().removeOnPreDrawListener(this);
                    final int measuredHeight = VungleActivity.this.ctaOverlay.getMeasuredHeight();
                    final int measuredWidth = VungleActivity.this.ctaOverlay.getMeasuredWidth();
                    view.post(new Runnable() { // from class: com.vungle.warren.ui.VungleActivity.10.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Rect rect = new Rect();
                            VungleActivity.this.ctaOverlay.getHitRect(rect);
                            rect.top -= (measuredHeight / 2) * i;
                            rect.left -= (measuredWidth / 2) * i;
                            rect.bottom += (measuredHeight / 2) * i;
                            rect.right += (measuredWidth / 2) * i;
                            view.setTouchDelegate(new TouchDelegate(rect, VungleActivity.this.ctaOverlay));
                        }
                    });
                    return true;
                }
            });
        }
        this.ctaOverlay.setOnClickListener(new View.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                VungleActivity.this.presenter.reportAction(InMobiNetworkValues.CTA, "");
                VungleActivity.this.presenter.handleAction("download");
            }
        });
    }

    @Override // com.vungle.warren.ui.AdView
    public void open(String str) {
        Log.v(TAG, "Opening " + str);
        try {
            Intent uri = Intent.parseUri(str, 0);
            uri.setFlags(DriveFile.MODE_READ_ONLY);
            startActivity(uri);
        } catch (Exception e) {
            Log.e(TAG, "Unable to start activity " + e.getLocalizedMessage());
        }
    }

    @Override // com.vungle.warren.ui.AdView
    public void showDialog(String str, String str2, String str3, String str4, final DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, getApplicationInfo().theme));
        if (!TextUtils.isEmpty(str)) {
            builder.setTitle(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            builder.setMessage(str2);
        }
        builder.setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.12
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                VungleActivity.this.pendingPause = false;
                VungleActivity.this.dialog.dismiss();
                DialogInterface.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(dialogInterface, i);
                }
            }
        });
        builder.setNegativeButton(str4, new DialogInterface.OnClickListener() { // from class: com.vungle.warren.ui.VungleActivity.13
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                VungleActivity.this.pendingPause = false;
                VungleActivity.this.dialog.dismiss();
                DialogInterface.OnClickListener onClickListener2 = onClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(dialogInterface, i);
                }
            }
        });
        builder.setCancelable(false);
        this.dialog = builder.create();
        if (this.videoView.isPlaying() && this.mediaPlayer != null) {
            this.videoView.pause();
        } else {
            this.pendingPause = true;
        }
        this.videoView.pause();
        this.videoPosition = this.videoView.getCurrentPosition();
        this.dialog.show();
    }

    private void setupPlayerProgressBar() {
        this.reportProgress = new Runnable() { // from class: com.vungle.warren.ui.VungleActivity.14
            float duration = -2.0f;

            @Override // java.lang.Runnable
            public void run() {
                if (VungleActivity.this.mediaPlayer != null) {
                    try {
                        int currentPosition = VungleActivity.this.mediaPlayer.getCurrentPosition();
                        if (this.duration == -2.0f) {
                            this.duration = VungleActivity.this.mediaPlayer.getDuration();
                        }
                        VungleActivity.this.presenter.reportAction("video_viewed", String.format(Locale.ENGLISH, "%d", Integer.valueOf(currentPosition)));
                        VungleActivity.this.presenter.onProgressUpdate((int) ((currentPosition / this.duration) * 100.0f));
                        VungleActivity.this.progressBar.setMax((int) this.duration);
                        VungleActivity.this.progressBar.setProgress(currentPosition);
                        VungleActivity.this.handler.postDelayed(this, 1000L);
                    } catch (IllegalStateException unused) {
                        Log.v(VungleActivity.TAG, "IllegalStateException while reporting progress indicates activity was killed via SIGKILL.");
                    }
                }
            }
        };
        this.handler.post(this.reportProgress);
    }

    private void setImmersiveMode() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        AdvertisementPresenter advertisementPresenter = this.presenter;
        if (advertisementPresenter != null) {
            advertisementPresenter.stop(isChangingConfigurations(), true);
        }
        this.handler.removeCallbacksAndMessages(null);
        VideoView videoView = this.videoView;
        if (videoView != null && videoView.isPlaying()) {
            try {
                this.videoView.stopPlayback();
            } catch (Throwable th) {
                Log.w(TAG, "error on stopping player " + th);
            }
        }
        super.onDestroy();
    }
}
