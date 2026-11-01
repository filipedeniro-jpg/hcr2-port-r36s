package net.nend.android.internal.ui.activities.formats;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.HashMap;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.b.c.b;
import net.nend.android.internal.c.f.d;
import net.nend.android.internal.ui.a.e;
import net.nend.android.internal.ui.views.formats.FontFitTextView;
import net.nend.android.internal.ui.views.formats.RoundedImageView;
import net.nend.android.internal.ui.views.video.NendAdVideoView;
import net.nend.android.internal.utilities.a.a;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.n;
import net.nend.android.internal.utilities.o;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import net.nend.android.internal.utilities.video.f;

/* JADX INFO: loaded from: classes2.dex */
public class FullscreenVideoPlayingActivity extends Activity {
    private static final ArrayList<f> p = new ArrayList<f>() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.3
        {
            add(f.PREPARING);
            add(f.PLAYING);
            add(f.PAUSING);
            add(f.COMPLETED);
        }
    };

    @VisibleForTesting
    f a;
    private ResultReceiver b;
    private NendAdVideoView c;
    private b d;
    private View e;
    private ToggleButton f;
    private boolean g;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private int h = 0;
    private View.OnClickListener m = new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.10
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FullscreenVideoPlayingActivity.this.d();
            FullscreenVideoPlayingActivity.this.f();
        }
    };
    private View.OnClickListener n = new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.11
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            FullscreenVideoPlayingActivity.this.e();
        }
    };
    private NendAdVideoView.a o = new NendAdVideoView.a() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.2
        @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
        public void onStartPlay() {
        }

        @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
        public void onPrepared() {
            FullscreenVideoPlayingActivity.this.f();
        }

        @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
        public void onCompletion(int i, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putInt(NendAdNativeMediaView.RESULT_CURRENT_MSEC, i);
            bundle.putBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION, z);
            FullscreenVideoPlayingActivity.this.b.send(12, bundle);
            FullscreenVideoPlayingActivity.this.h = i;
            if (z) {
                FullscreenVideoPlayingActivity.this.a = f.COMPLETED;
                FullscreenVideoPlayingActivity.this.e.setVisibility(0);
                FullscreenVideoPlayingActivity.this.f.setVisibility(4);
                return;
            }
            FullscreenVideoPlayingActivity.this.a = f.PAUSING;
        }

        @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
        public void onProgress(int i, int i2) {
            FullscreenVideoPlayingActivity.this.h = i - i2;
            Bundle bundle = new Bundle();
            bundle.putInt(NendAdNativeMediaView.RESULT_TOTAL_MSEC, i);
            bundle.putInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC, i2);
            FullscreenVideoPlayingActivity.this.b.send(11, bundle);
        }

        @Override // net.nend.android.internal.ui.views.video.NendAdVideoView.a
        public void onError(int i, String str) {
            Bundle bundle = new Bundle();
            bundle.putInt("errorCode", i);
            bundle.putString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE, str);
            FullscreenVideoPlayingActivity.this.b.send(13, bundle);
            if (!FullscreenVideoPlayingActivity.this.l) {
                FullscreenVideoPlayingActivity.this.l = true;
                FullscreenVideoPlayingActivity.this.c.e();
                FullscreenVideoPlayingActivity.this.c.f();
                FullscreenVideoPlayingActivity.this.c = null;
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.2.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FullscreenVideoPlayingActivity.this.a();
                    }
                });
                return;
            }
            FullscreenVideoPlayingActivity.this.finish();
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a(bundle);
        if (this.d.c == 1) {
            setContentView(n.c(this, "activity_native_port_video_fullscreen_playing"));
        } else {
            setContentView(n.c(this, "activity_native_land_video_fullscreen_playing"));
        }
    }

    private void a(Bundle bundle) {
        if (bundle == null) {
            Intent intent = getIntent();
            this.d = (b) intent.getParcelableExtra("key_ad_unit");
            this.b = (ResultReceiver) intent.getParcelableExtra("key_receiver");
            this.h = intent.getIntExtra("key_video_playing_time", 0);
            this.g = intent.getBooleanExtra("key_mute", false);
            this.a = f.PREPARING;
        } else {
            this.d = (b) bundle.getParcelable("key_ad_unit");
            this.b = (ResultReceiver) bundle.getParcelable("key_receiver");
            this.h = bundle.getInt("key_video_playing_time");
            this.g = bundle.getBoolean("key_mute");
            this.a = p.get(bundle.getInt("key_playing_status"));
            if (this.a == f.COMPLETED) {
                d();
            }
        }
        b bVar = this.d;
        if (bVar == null || !bVar.a()) {
            j.d("Failed to play video. Because required loader data is not found or loader is expired.");
            Bundle bundle2 = new Bundle();
            bundle2.putInt("errorCode", NendVideoAdClientError.INVALID_AD_DATA.getCode());
            bundle2.putString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE, NendVideoAdClientError.INVALID_AD_DATA.getMessage());
            this.b.send(13, bundle2);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        View view = this.e;
        if (view == null) {
            a();
            b();
            c();
        } else if (view.getVisibility() != 0) {
            this.c.c();
            this.a = f.PLAYING;
        }
        a(getResources().getConfiguration().orientation);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.e.getVisibility() == 0) {
            d();
        } else {
            this.c.d();
            this.a = f.PAUSING;
        }
    }

    public static Bundle newBundle(int i, b bVar, boolean z, ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putInt("key_video_playing_time", i);
        bundle.putParcelable("key_ad_unit", bVar);
        bundle.putParcelable("key_receiver", resultReceiver);
        bundle.putBoolean("key_mute", z);
        return bundle;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration.orientation);
    }

    private void a(int i) {
        if (this.i == 0) {
            this.i = n.b(this, "native_video_fullscreen_card");
        }
        if (i == this.d.c) {
            findViewById(this.i).setVisibility(8);
            findViewById(this.j).setVisibility(0);
        } else {
            findViewById(this.i).setVisibility(0);
            findViewById(this.j).setVisibility(8);
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        NendAdVideoView nendAdVideoView = this.c;
        if (nendAdVideoView != null) {
            nendAdVideoView.e();
            this.c.f();
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(this.i);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(4);
        }
        View viewFindViewById = findViewById(this.j);
        if (viewFindViewById != null) {
            viewFindViewById.setVisibility(8);
        }
        Button button = (Button) findViewById(this.k);
        if (button != null) {
            button.setVisibility(4);
        }
        this.b.send(1, null);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("key_ad_unit", this.d);
        bundle.putParcelable("key_receiver", this.b);
        bundle.putBoolean("key_mute", this.g);
        bundle.putInt("key_playing_status", this.a.ordinal());
        bundle.putInt("key_video_playing_time", this.h);
    }

    private String a(String str) {
        return o.a(str, new HashMap<String, String>() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.1
            {
                put("ifs", "1");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.c = (NendAdVideoView) findViewById(n.b(this, "native_video_fullscreen_videoview"));
        this.c.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.c.a(this.d.g, true);
        this.c.setCallback(this.o);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (FullscreenVideoPlayingActivity.this.a != f.COMPLETED) {
                    if (FullscreenVideoPlayingActivity.this.f.getVisibility() == 0) {
                        FullscreenVideoPlayingActivity.this.f.setVisibility(4);
                    } else {
                        FullscreenVideoPlayingActivity.this.f.setVisibility(0);
                    }
                }
            }
        });
        ImageView imageView = (ImageView) findViewById(n.b(this, "native_video_fullscreen_action_optout"));
        imageView.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullscreenVideoPlayingActivity.this.b.send(3, null);
            }
        });
        e.a(this.c.getWidth(), this.c.getHeight(), imageView);
    }

    private void b() {
        final RoundedImageView roundedImageView = (RoundedImageView) findViewById(n.b(this, "native_video_fullscreen_card_image"));
        Bitmap bitmapA = a.a(this.d.i);
        if (bitmapA == null) {
            d.a(this.d).a(new net.nend.android.internal.d.a()).a(new net.nend.android.internal.d.d<Bitmap>() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.7
                @Override // net.nend.android.internal.d.d
                public void a(Bitmap bitmap) {
                    roundedImageView.setImageBitmap(bitmap);
                    roundedImageView.setBackgroundColor(0);
                }
            }).b(new net.nend.android.internal.d.d<Throwable>() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.6
                @Override // net.nend.android.internal.d.d
                public void a(Throwable th) {
                    roundedImageView.setBackgroundColor(-3355444);
                }
            });
        } else {
            roundedImageView.setImageBitmap(bitmapA);
            roundedImageView.setBackgroundColor(0);
        }
        Button button = (Button) findViewById(n.b(this, "native_video_fullscreen_card_cta"));
        a(button);
        button.setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
        ((TextView) findViewById(n.b(this, "native_video_fullscreen_card_title"))).setText(this.d.j);
        ((TextView) findViewById(n.b(this, "native_video_fullscreen_card_advertiser"))).setText(this.d.k);
        TextView textView = (TextView) findViewById(n.b(this, "native_video_fullscreen_card_description"));
        if (this.d.l != null && this.d.l.length() > 45) {
            textView.setText(this.d.l.substring(0, 45) + "...");
        } else {
            textView.setText(this.d.l);
        }
        int[] iArr = {n.b(this, "native_video_fullscreen_card_star001"), n.b(this, "native_video_fullscreen_card_star002"), n.b(this, "native_video_fullscreen_card_star003"), n.b(this, "native_video_fullscreen_card_star004"), n.b(this, "native_video_fullscreen_card_star005")};
        int iB = n.b(this, "native_video_fullscreen_card_rating_count");
        if (this.d.m != -1.0f && this.d.n != -1) {
            e.a(this, this.d, iArr);
            ((TextView) findViewById(iB)).setText(e.a(this.d.n));
            return;
        }
        for (int i : iArr) {
            findViewById(i).setVisibility(8);
        }
        findViewById(iB).setVisibility(8);
    }

    private void c() {
        this.e = findViewById(n.b(this, "native_video_fullscreen_replay_area"));
        this.e.setVisibility(8);
        ((ImageButton) findViewById(n.b(this, "media_view_button_replay"))).setOnClickListener(this.m);
        ((FontFitTextView) findViewById(n.b(this, "description_media_view_button_replay"))).setOnClickListener(this.m);
        ((ImageButton) findViewById(n.b(this, "media_view_button_cta"))).setOnClickListener(this.n);
        FontFitTextView fontFitTextView = (FontFitTextView) findViewById(n.b(this, "description_media_view_button_cta"));
        fontFitTextView.setOnClickListener(this.n);
        fontFitTextView.setText(this.d.o);
        if (this.k == 0) {
            this.k = n.b(this, "native_video_fullscreen_close");
        }
        ((Button) findViewById(this.k)).setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FullscreenVideoPlayingActivity.this.finish();
            }
        });
        if (this.j == 0) {
            this.j = n.b(this, "native_video_fullscreen_action_cta");
        }
        Button button = (Button) findViewById(this.j);
        a(button);
        button.setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
        this.f = (ToggleButton) findViewById(n.b(this, "native_video_fullscreen_action_toggle_volume"));
        this.f.setTextOff("");
        this.f.setTextOn("");
        this.f.setChecked(!this.g);
        this.f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity.9
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FullscreenVideoPlayingActivity.this.g = !z;
                FullscreenVideoPlayingActivity.this.c.setMute(FullscreenVideoPlayingActivity.this.g);
            }
        });
        this.f.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        this.a = f.PREPARING;
        this.h = 0;
        NendAdVideoView nendAdVideoView = this.c;
        if (nendAdVideoView != null) {
            nendAdVideoView.a(this.h);
        }
        View view = this.e;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Bundle bundle = new Bundle();
        bundle.putString(NendAdNativeMediaView.RESULT_CLICK_URL, a(this.d.b));
        this.b.send(2, bundle);
        finish();
    }

    private void a(Button button) {
        button.setText(this.d.o);
        button.setOnClickListener(this.n);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.c.a(this.h);
        this.c.setMute(this.g);
        this.c.c();
        this.a = f.PLAYING;
    }
}
