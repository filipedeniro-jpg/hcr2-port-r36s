package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.my.target.aq;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzbta implements zzbuf {
    private final zzawv zzblh;
    private final Clock zzbmd;
    private final zzdf zzefm;
    private final zzcxw zzfas;
    private final zzbui zzfdc;
    private final zzcvb zzfef;
    private final zzcvk zzffn;
    private final JSONObject zzfja;
    private final zzbxn zzfjb;
    private final zzbtx zzfjc;
    private final zzbmo zzfjd;
    private final zzbmf zzfje;
    private final zzbgu zzfjf;
    private final zzbuw zzfjg;
    private final zzbqb zzfjh;
    private boolean zzfjj;
    private zzvx zzfjq;
    private final Context zzlk;
    private boolean zzfji = false;
    private boolean zzfjk = false;
    private boolean zzfjl = false;
    private Point zzfjm = new Point();
    private Point zzfjn = new Point();
    private long zzfjo = 0;
    private long zzfjp = 0;

    public zzbta(Context context, zzbui zzbuiVar, JSONObject jSONObject, zzbxn zzbxnVar, zzbtx zzbtxVar, zzdf zzdfVar, zzbmo zzbmoVar, zzbmf zzbmfVar, zzcvb zzcvbVar, zzawv zzawvVar, zzcvk zzcvkVar, zzbgu zzbguVar, zzbuw zzbuwVar, Clock clock, zzbqb zzbqbVar, zzcxw zzcxwVar) {
        this.zzlk = context;
        this.zzfdc = zzbuiVar;
        this.zzfja = jSONObject;
        this.zzfjb = zzbxnVar;
        this.zzfjc = zzbtxVar;
        this.zzefm = zzdfVar;
        this.zzfjd = zzbmoVar;
        this.zzfje = zzbmfVar;
        this.zzfef = zzcvbVar;
        this.zzblh = zzawvVar;
        this.zzffn = zzcvkVar;
        this.zzfjf = zzbguVar;
        this.zzfjg = zzbuwVar;
        this.zzbmd = clock;
        this.zzfjh = zzbqbVar;
        this.zzfas = zzcxwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, View.OnTouchListener onTouchListener, View.OnClickListener onClickListener) {
        this.zzfjm = new Point();
        this.zzfjn = new Point();
        if (!this.zzfjj) {
            this.zzfjh.zzq(view);
            this.zzfjj = true;
        }
        view.setOnTouchListener(onTouchListener);
        view.setClickable(true);
        view.setOnClickListener(onClickListener);
        this.zzfjf.zzo(this);
        boolean zZzcq = zzavs.zzcq(this.zzblh.zzdvt);
        if (map != null) {
            Iterator<Map.Entry<String, WeakReference<View>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                View view2 = it.next().getValue().get();
                if (view2 != null) {
                    if (zZzcq) {
                        view2.setOnTouchListener(onTouchListener);
                    }
                    view2.setClickable(true);
                    view2.setOnClickListener(onClickListener);
                }
            }
        }
        if (map2 != null) {
            Iterator<Map.Entry<String, WeakReference<View>>> it2 = map2.entrySet().iterator();
            while (it2.hasNext()) {
                View view3 = it2.next().getValue().get();
                if (view3 != null) {
                    if (zZzcq) {
                        view3.setOnTouchListener(onTouchListener);
                    }
                    view3.setClickable(false);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, @Nullable Map<String, WeakReference<View>> map) {
        this.zzfjm = new Point();
        this.zzfjn = new Point();
        this.zzfjh.zzr(view);
        this.zzfjj = false;
    }

    private final boolean zzfo(String str) {
        JSONObject jSONObjectOptJSONObject = this.zzfja.optJSONObject("allow_pub_event_reporting");
        return jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.optBoolean(str, false);
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(View view, @Nullable View view2, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, boolean z) {
        JSONObject jSONObjectZza = zza(map, map2, view2);
        JSONObject jSONObjectZzs = zzs(view2);
        JSONObject jSONObjectZzt = zzt(view2);
        JSONObject jSONObjectZzu = zzu(view2);
        String strZzb = zzb(view, map);
        zza(view, jSONObjectZzs, jSONObjectZza, jSONObjectZzt, jSONObjectZzu, strZzb, zzfq(strZzb), null, z, false);
    }

    @Nullable
    private final String zzb(@Nullable View view, @Nullable Map<String, WeakReference<View>> map) {
        if (map != null && view != null) {
            for (Map.Entry<String, WeakReference<View>> entry : map.entrySet()) {
                if (view.equals(entry.getValue().get())) {
                    return entry.getKey();
                }
            }
        }
        int iZzahl = this.zzfjc.zzahl();
        if (iZzahl == 1) {
            return "1099";
        }
        if (iZzahl == 2) {
            return "2099";
        }
        if (iZzahl == 3 || iZzahl != 6) {
            return null;
        }
        return "3099";
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzfp(String str) {
        zza(null, null, null, null, null, str, null, null, false, false);
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzf(@Nullable Bundle bundle) {
        if (bundle == null) {
            zzatm.zzdv("Click data is null. No click is reported.");
        } else if (!zzfo("click_reporting")) {
            zzatm.zzes("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        } else {
            Bundle bundle2 = bundle.getBundle("click_signal");
            zza(null, null, null, null, null, bundle2 != null ? bundle2.getString("asset_id") : null, null, com.google.android.gms.ads.internal.zzp.zzjy().zza(bundle, (JSONObject) null), false, false);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(@Nullable View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, boolean z) {
        if (!this.zzfjl) {
            zzatm.zzdv("Custom click reporting failed. enableCustomClickGesture is not set.");
            return;
        }
        if (!zzagy()) {
            zzatm.zzdv("Custom click reporting failed. Ad unit id not whitelisted.");
            return;
        }
        JSONObject jSONObjectZza = zza(map, map2, view);
        JSONObject jSONObjectZzs = zzs(view);
        JSONObject jSONObjectZzt = zzt(view);
        JSONObject jSONObjectZzu = zzu(view);
        String strZzb = zzb(null, map);
        zza(view, jSONObjectZzs, jSONObjectZza, jSONObjectZzt, jSONObjectZzu, strZzb, zzfq(strZzb), null, z, true);
    }

    private final boolean zzagy() {
        return this.zzfja.optBoolean("allow_custom_click_gesture", false);
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzqs() {
        this.zzfjl = true;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final boolean isCustomClickGestureEnabled() {
        return zzagy();
    }

    private final void zza(@Nullable View view, @Nullable JSONObject jSONObject, @Nullable JSONObject jSONObject2, @Nullable JSONObject jSONObject3, @Nullable JSONObject jSONObject4, @Nullable String str, @Nullable JSONObject jSONObject5, @Nullable JSONObject jSONObject6, boolean z, boolean z2) {
        Preconditions.checkMainThread("performClick must be called on the main UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzfja);
            jSONObject7.put("asset_view_signal", jSONObject2);
            jSONObject7.put("ad_view_signal", jSONObject);
            jSONObject7.put("click_signal", jSONObject5);
            jSONObject7.put("scroll_view_signal", jSONObject3);
            jSONObject7.put("lock_screen_signal", jSONObject4);
            jSONObject7.put("has_custom_click_handler", this.zzfdc.zzfv(this.zzfjc.getCustomTemplateId()) != null);
            jSONObject7.put("provided_signals", jSONObject6);
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.zzfjc.zzahl());
            jSONObject8.put("view_aware_api_used", z);
            jSONObject8.put("custom_mute_requested", this.zzffn.zzddy != null && this.zzffn.zzddy.zzbjy);
            jSONObject8.put("custom_mute_enabled", (this.zzfjc.getMuteThisAdReasons().isEmpty() || this.zzfjc.zzaho() == null) ? false : true);
            if (this.zzfjg.zzais() != null && this.zzfja.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject8.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject8.put("timestamp", this.zzbmd.currentTimeMillis());
            if (this.zzfjl && zzagy()) {
                jSONObject8.put("custom_click_gesture_eligible", true);
            }
            if (z2) {
                jSONObject8.put("is_custom_click_gesture", true);
            }
            jSONObject8.put("has_custom_click_handler", this.zzfdc.zzfv(this.zzfjc.getCustomTemplateId()) != null);
            jSONObject8.put("click_signals", zzv(view));
            jSONObject7.put(aq.a.dF, jSONObject8);
            JSONObject jSONObject9 = new JSONObject();
            long jCurrentTimeMillis = this.zzbmd.currentTimeMillis();
            jSONObject9.put("time_from_last_touch_down", jCurrentTimeMillis - this.zzfjo);
            jSONObject9.put("time_from_last_touch", jCurrentTimeMillis - this.zzfjp);
            jSONObject7.put("touch_signal", jSONObject9);
            zzaxb.zza(this.zzfjb.zzc("google.afma.nativeAds.handleClick", jSONObject7), "Error during performing handleClick");
        } catch (JSONException e) {
            zzatm.zzc("Unable to create click JSON.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(@Nullable View view, MotionEvent motionEvent, @Nullable View view2) {
        int[] iArrZzw = zzw(view2);
        this.zzfjm = new Point(((int) motionEvent.getRawX()) - iArrZzw[0], ((int) motionEvent.getRawY()) - iArrZzw[1]);
        long jCurrentTimeMillis = this.zzbmd.currentTimeMillis();
        this.zzfjp = jCurrentTimeMillis;
        if (motionEvent.getAction() == 0) {
            this.zzfjo = jCurrentTimeMillis;
            this.zzfjn = this.zzfjm;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
        motionEventObtain.setLocation(this.zzfjm.x, this.zzfjm.y);
        this.zzefm.zzb(motionEventObtain);
        motionEventObtain.recycle();
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzg(@Nullable Bundle bundle) {
        if (bundle == null) {
            zzatm.zzdv("Touch event data is null. No touch event is reported.");
            return;
        }
        if (!zzfo("touch_reporting")) {
            zzatm.zzes("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
            return;
        }
        this.zzefm.zzcc().zza((int) bundle.getFloat("x"), (int) bundle.getFloat("y"), bundle.getInt("duration_ms"));
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzagz() {
        zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null);
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void setClickConfirmingView(View view) {
        if (!this.zzfja.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzatm.zzeu("setClickConfirmingView: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            return;
        }
        zzbuw zzbuwVar = this.zzfjg;
        if (view != null) {
            view.setOnClickListener(zzbuwVar);
            view.setClickable(true);
            zzbuwVar.zzfmx = new WeakReference<>(view);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(zzacl zzaclVar) {
        if (!this.zzfja.optBoolean("custom_one_point_five_click_enabled", false)) {
            zzatm.zzeu("setUnconfirmedClickListener: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
        } else {
            this.zzfjg.zza(zzaclVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void cancelUnconfirmedClick() {
        if (this.zzfja.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzfjg.cancelUnconfirmedClick();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(@Nullable zzwb zzwbVar) {
        try {
            if (this.zzfjk) {
                return;
            }
            if (zzwbVar == null && this.zzfjc.zzaho() != null) {
                this.zzfjk = true;
                this.zzfas.zzei(this.zzfjc.zzaho().zzor());
                zzaha();
            } else {
                this.zzfjk = true;
                this.zzfas.zzei(zzwbVar.zzor());
                zzaha();
            }
        } catch (RemoteException e) {
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(zzvx zzvxVar) {
        this.zzfjq = zzvxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzaha() {
        try {
            if (this.zzfjq != null) {
                this.zzfjq.onAdMuted();
            }
        } catch (RemoteException e) {
            zzatm.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zza(@Nullable View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2) {
        zza(zzs(view), zza(map, map2, view), zzt(view), zzu(view), (JSONObject) null);
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void zzahb() {
        Preconditions.checkMainThread("recordDownloadedImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ad", this.zzfja);
            zzaxb.zza(this.zzfjb.zzc("google.afma.nativeAds.handleDownloadedImpression", jSONObject), "Error during performing handleDownloadedImpression");
        } catch (JSONException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final boolean zzh(Bundle bundle) {
        if (!zzfo("impression_reporting")) {
            zzatm.zzes("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, com.google.android.gms.ads.internal.zzp.zzjy().zza(bundle, (JSONObject) null));
    }

    private final boolean zza(@Nullable JSONObject jSONObject, @Nullable JSONObject jSONObject2, @Nullable JSONObject jSONObject3, @Nullable JSONObject jSONObject4, @Nullable JSONObject jSONObject5) {
        Preconditions.checkMainThread("recordImpression must be called on the main UI thread.");
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzfja);
            jSONObject6.put("asset_view_signal", jSONObject2);
            jSONObject6.put("ad_view_signal", jSONObject);
            jSONObject6.put("scroll_view_signal", jSONObject3);
            jSONObject6.put("lock_screen_signal", jSONObject4);
            jSONObject6.put("provided_signals", jSONObject5);
            zzbsz zzbszVar = null;
            this.zzfjb.zza("/logScionEvent", new zzbtc(this));
            this.zzfjb.zza("/nativeImpression", new zzbtb(this));
            zzaxb.zza(this.zzfjb.zzc("google.afma.nativeAds.handleImpression", jSONObject6), "Error during performing handleImpression");
            if (this.zzfji || this.zzfef.zzgjb == null) {
                return true;
            }
            this.zzfji |= com.google.android.gms.ads.internal.zzp.zzki().zzb(this.zzlk, this.zzblh.zzbnh, this.zzfef.zzgjb.toString(), this.zzffn.zzgjt);
            return true;
        } catch (JSONException e) {
            zzatm.zzc("Unable to create impression JSON.", e);
            return false;
        }
    }

    private final JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", zzdg(rect.right - rect.left));
        jSONObject.put("height", zzdg(rect.bottom - rect.top));
        jSONObject.put("x", zzdg(rect.left));
        jSONObject.put("y", zzdg(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private final JSONObject zzs(@Nullable View view) {
        String str;
        JSONObject jSONObjectZzb;
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            int[] iArrZzw = zzw(view);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", zzdg(view.getMeasuredWidth()));
            jSONObject2.put("height", zzdg(view.getMeasuredHeight()));
            jSONObject2.put("x", zzdg(iArrZzw[0]));
            jSONObject2.put("y", zzdg(iArrZzw[1]));
            jSONObject2.put("relative_to", "window");
            jSONObject.put("frame", jSONObject2);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObjectZzb = zzb(rect);
            } else {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("width", 0);
                jSONObject3.put("height", 0);
                jSONObject3.put("x", zzdg(iArrZzw[0]));
                jSONObject3.put("y", zzdg(iArrZzw[1]));
                jSONObject3.put("relative_to", "window");
                jSONObjectZzb = jSONObject3;
            }
            jSONObject.put("visible_bounds", jSONObjectZzb);
        } catch (Exception unused) {
            zzatm.zzeu("Unable to get native ad view bounding box");
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcsw)).booleanValue()) {
            ViewParent parent = view.getParent();
            if (parent != null) {
                try {
                    str = (String) parent.getClass().getMethod("getTemplateTypeName", new Class[0]).invoke(parent, new Object[0]);
                } catch (IllegalAccessException e) {
                    zzatm.zzc("Cannot access method getTemplateTypeName: ", e);
                    str = "";
                } catch (NoSuchMethodException unused2) {
                    str = "";
                } catch (SecurityException e2) {
                    zzatm.zzc("Cannot access method getTemplateTypeName: ", e2);
                    str = "";
                } catch (InvocationTargetException e3) {
                    zzatm.zzc("Cannot access method getTemplateTypeName: ", e3);
                    str = "";
                }
            } else {
                str = "";
            }
            byte b = -1;
            try {
                int iHashCode = str.hashCode();
                if (iHashCode != -2066603854) {
                    if (iHashCode == 2019754500 && str.equals("medium_template")) {
                        b = 1;
                    }
                } else if (str.equals("small_template")) {
                    b = 0;
                }
                if (b == 0) {
                    jSONObject.put("native_template_type", 1);
                } else if (b == 1) {
                    jSONObject.put("native_template_type", 2);
                } else {
                    jSONObject.put("native_template_type", 0);
                }
            } catch (JSONException e4) {
                zzatm.zzc("Could not log native template signal to JSON", e4);
            }
        }
        return jSONObject;
    }

    private static JSONObject zzt(@Nullable View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            com.google.android.gms.ads.internal.zzp.zzjy();
            jSONObject.put("contained_in_scroll_view", zzatv.zzp(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject zzu(@Nullable View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            com.google.android.gms.ads.internal.zzp.zzjy();
            jSONObject.put("can_show_on_lock_screen", zzatv.zzo(view));
            com.google.android.gms.ads.internal.zzp.zzjy();
            jSONObject.put("is_keyguard_locked", zzatv.zzaw(this.zzlk));
        } catch (JSONException unused) {
            zzatm.zzeu("Unable to get lock screen information");
        }
        return jSONObject;
    }

    private final JSONObject zza(@Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, @Nullable View view) {
        String str;
        String str2;
        JSONObject jSONObject;
        String str3 = "ad_view";
        String str4 = "relative_to";
        JSONObject jSONObject2 = new JSONObject();
        if (map != null && view != null) {
            int[] iArrZzw = zzw(view);
            Iterator<Map.Entry<String, WeakReference<View>>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, WeakReference<View>> next = it.next();
                View view2 = next.getValue().get();
                if (view2 != null) {
                    int[] iArrZzw2 = zzw(view2);
                    JSONObject jSONObject3 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    Iterator<Map.Entry<String, WeakReference<View>>> it2 = it;
                    try {
                        jSONObject4.put("width", zzdg(view2.getMeasuredWidth()));
                        jSONObject4.put("height", zzdg(view2.getMeasuredHeight()));
                        jSONObject4.put("x", zzdg(iArrZzw2[0] - iArrZzw[0]));
                        jSONObject4.put("y", zzdg(iArrZzw2[1] - iArrZzw[1]));
                        jSONObject4.put(str4, str3);
                        jSONObject3.put("frame", jSONObject4);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            jSONObject = zzb(rect);
                        } else {
                            jSONObject = new JSONObject();
                            jSONObject.put("width", 0);
                            jSONObject.put("height", 0);
                            jSONObject.put("x", zzdg(iArrZzw2[0] - iArrZzw[0]));
                            jSONObject.put("y", zzdg(iArrZzw2[1] - iArrZzw[1]));
                            jSONObject.put(str4, str3);
                        }
                        jSONObject3.put("visible_bounds", jSONObject);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject3.put("text_color", textView.getCurrentTextColor());
                            str = str3;
                            str2 = str4;
                            try {
                                jSONObject3.put("font_size", textView.getTextSize());
                                jSONObject3.put(MimeTypes.BASE_TYPE_TEXT, textView.getText());
                            } catch (JSONException unused) {
                                zzatm.zzeu("Unable to get asset views information");
                            }
                        } else {
                            str = str3;
                            str2 = str4;
                        }
                        jSONObject3.put("is_clickable", map2 != null && map2.containsKey(next.getKey()) && view2.isClickable());
                        jSONObject2.put(next.getKey(), jSONObject3);
                    } catch (JSONException unused2) {
                        str = str3;
                        str2 = str4;
                    }
                    it = it2;
                    str3 = str;
                    str4 = str2;
                }
            }
        }
        return jSONObject2;
    }

    private final String zzv(View view) {
        try {
            JSONObject jSONObjectOptJSONObject = this.zzfja.optJSONObject("tracking_urls_and_actions");
            if (jSONObjectOptJSONObject == null) {
                jSONObjectOptJSONObject = new JSONObject();
            }
            return this.zzefm.zzcc().zza(this.zzlk, jSONObjectOptJSONObject.optString("click_string"), view);
        } catch (Exception e) {
            zzatm.zzc("Exception obtaining click signals", e);
            return null;
        }
    }

    private final JSONObject zzfq(@Nullable String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("click_point", zzahc());
                jSONObject.put("asset_id", str);
            } catch (Exception e) {
                e = e;
                zzatm.zzc("Error occurred while grabbing click signals.", e);
            }
        } catch (Exception e2) {
            e = e2;
            jSONObject = null;
        }
        return jSONObject;
    }

    @Nullable
    private final JSONObject zzahc() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("x", zzdg(this.zzfjm.x));
            jSONObject.put("y", zzdg(this.zzfjm.y));
            jSONObject.put("start_x", zzdg(this.zzfjn.x));
            jSONObject.put("start_y", zzdg(this.zzfjn.y));
            return jSONObject;
        } catch (JSONException e) {
            zzatm.zzc("Error occurred while putting signals into JSON object.", e);
            return null;
        }
    }

    private static int[] zzw(@Nullable View view) {
        int[] iArr = new int[2];
        if (view != null) {
            view.getLocationOnScreen(iArr);
        }
        return iArr;
    }

    private final int zzdg(int i) {
        return zzuo.zzof().zzb(this.zzlk, i);
    }

    @Override // com.google.android.gms.internal.ads.zzbuf
    public final void destroy() {
        this.zzfjb.destroy();
    }
}
