package com.adcolony.sdk;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AdColonyNativeAdView extends az {
    private EngagementButton c;
    private boolean d;
    private String e;
    private String f;
    private String g;

    @Override // com.adcolony.sdk.az
    public /* bridge */ /* synthetic */ boolean destroy() {
        return super.destroy();
    }

    @Override // com.adcolony.sdk.az
    public /* bridge */ /* synthetic */ String getZoneID() {
        return super.getZoneID();
    }

    @Override // com.adcolony.sdk.az
    public /* bridge */ /* synthetic */ boolean pause() {
        return super.pause();
    }

    @Override // com.adcolony.sdk.az
    public /* bridge */ /* synthetic */ boolean resume() {
        return super.resume();
    }

    @Override // com.adcolony.sdk.az
    public /* bridge */ /* synthetic */ boolean setMuted(boolean z) {
        return super.setMuted(z);
    }

    @Override // com.adcolony.sdk.az
    public /* bridge */ /* synthetic */ boolean setVolume(float f) {
        return super.setVolume(f);
    }

    AdColonyNativeAdView(Context context, af afVar, e eVar) {
        super(context, afVar, eVar);
        JSONObject jSONObjectC = afVar.c();
        setNative(true);
        this.d = y.d(jSONObjectC, "engagement_enabled");
        this.e = y.b(jSONObjectC, "engagement_click_action");
        this.f = y.b(jSONObjectC, "engagement_click_action_type");
        this.g = y.b(jSONObjectC, "engagement_text");
        if (this.d) {
            this.c = new EngagementButton(context);
            this.c.setText(this.g);
            this.c.setOnClickListener(new View.OnClickListener() { // from class: com.adcolony.sdk.AdColonyNativeAdView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (AdColonyNativeAdView.this.c()) {
                        new aa.a().a("Ignoring engagement click as view has been destroyed.").a(aa.e);
                        return;
                    }
                    JSONObject jSONObjectA = y.a();
                    y.a(jSONObjectA, "id", AdColonyNativeAdView.this.getAdSessionId());
                    new af("AdSession.on_native_engagement", AdColonyNativeAdView.this.getContainer().c(), jSONObjectA).b();
                }
            });
        }
    }

    @Override // com.adcolony.sdk.az
    public String getAdvertiserName() {
        if (c()) {
            new aa.a().a("Ignoring call to getAdvertiserName() as view has been destroyed").a(aa.e);
            return "";
        }
        return super.getAdvertiserName();
    }

    @Override // com.adcolony.sdk.az
    public ImageView getIcon() {
        ImageView icon = super.getIcon();
        if (icon == null) {
            return null;
        }
        if (!c()) {
            return icon;
        }
        new aa.a().a("Ignoring call to getIcon() as view has been destroyed").a(aa.e);
        return null;
    }

    @Override // com.adcolony.sdk.az
    public String getTitle() {
        if (c()) {
            new aa.a().a("Ignoring call to getTitle() as view has been destroyed").a(aa.e);
            return "";
        }
        return super.getTitle();
    }

    @Override // com.adcolony.sdk.az
    public String getDescription() {
        if (c()) {
            new aa.a().a("Ignoring call to getDescription() as view has been destroyed").a(aa.e);
            return "";
        }
        return super.getDescription();
    }

    public boolean isEngagementEnabled() {
        if (c()) {
            new aa.a().a("Ignoring call to isEngagementEnabled() as view has been destroyed").a(aa.e);
            return false;
        }
        return this.d;
    }

    public EngagementButton getEngagementButton() {
        if (c()) {
            new aa.a().a("Ignoring call to getEngagementButton() as view has been destroyed").a(aa.e);
            return null;
        }
        return this.c;
    }

    public class EngagementButton extends Button {
        boolean a;
        View.OnClickListener b;

        EngagementButton(Context context) {
            super(context);
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            if (this.a) {
                return;
            }
            super.setOnClickListener(onClickListener);
            this.b = onClickListener;
            this.a = true;
        }

        public View.OnClickListener getOnClickListener() {
            return this.b;
        }
    }
}
