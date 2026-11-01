package net.nend.android.internal.c.f;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.ArrayList;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNativeClient;
import net.nend.android.NendAdNativeVideo;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.d.m;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAdLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public class d extends net.nend.android.internal.c.a {
    private int d;
    private String e;
    private int f;
    private String g;
    private String h;
    private String i;
    private NendAdNativeVideo.VideoClickOption j;
    private ArrayList<Integer> k;
    private final net.nend.android.internal.utilities.g.c<net.nend.android.internal.b.c.b> l;

    public d(Context context, int i, String str, NendAdNativeVideo.VideoClickOption videoClickOption) {
        super(context);
        this.k = new ArrayList<>();
        this.l = new net.nend.android.internal.c.a.AbstractC0107a<net.nend.android.internal.b.c.b>() { // from class: net.nend.android.internal.c.f.d.1
            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return b.c;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // net.nend.android.internal.c.a.AbstractC0107a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public net.nend.android.internal.b.c.b a(JSONObject jSONObject) {
                net.nend.android.internal.utilities.b.a("JsonResponseEvent", jSONObject);
                return net.nend.android.internal.b.c.b.a(jSONObject);
            }
        };
        this.f = i;
        this.g = str;
        this.j = videoClickOption;
    }

    public void a(String str) {
        this.h = str;
    }

    public void a(int i) {
        this.k.add(Integer.valueOf(i));
        if (this.k.size() > 4) {
            this.k.remove(0);
        }
    }

    public NendAdNativeVideo.VideoClickOption a() {
        return this.j;
    }

    public k<net.nend.android.internal.b.c.b> b() {
        k kVarB = b(this.f, this.g, this.h, this.i, this.l).b(new net.nend.android.internal.d.g<net.nend.android.internal.b.c.b, k<? extends m<net.nend.android.internal.b.c.b, Bitmap>>>() { // from class: net.nend.android.internal.c.f.d.2
            @Override // net.nend.android.internal.d.g
            public k<? extends m<net.nend.android.internal.b.c.b, Bitmap>> a(net.nend.android.internal.b.c.b bVar) {
                return l.a(l.a(bVar), d.a(bVar));
            }
        });
        if (this.d > 0 && !TextUtils.isEmpty(this.e)) {
            return kVarB.a(new net.nend.android.internal.d.g<Throwable, m<net.nend.android.internal.b.c.b, Bitmap>>() { // from class: net.nend.android.internal.c.f.d.4
                @Override // net.nend.android.internal.d.g
                public m<net.nend.android.internal.b.c.b, Bitmap> a(Throwable th) {
                    j.b("Failed to load Native Video Ad. Fallback normal Native ad.");
                    return null;
                }
            }).b(new net.nend.android.internal.d.g<m<net.nend.android.internal.b.c.b, Bitmap>, k<? extends net.nend.android.internal.b.c.b>>() { // from class: net.nend.android.internal.c.f.d.3
                @Override // net.nend.android.internal.d.g
                public k<? extends net.nend.android.internal.b.c.b> a(m<net.nend.android.internal.b.c.b, Bitmap> mVar) {
                    if (mVar == null) {
                        return d.this.c();
                    }
                    return l.a(mVar.a);
                }
            });
        }
        j.b("You can use fallback option at Native Video Ad. Let's check the wiki.");
        return kVarB.b(new net.nend.android.internal.d.g<m<net.nend.android.internal.b.c.b, Bitmap>, k<? extends net.nend.android.internal.b.c.b>>() { // from class: net.nend.android.internal.c.f.d.5
            @Override // net.nend.android.internal.d.g
            public k<? extends net.nend.android.internal.b.c.b> a(m<net.nend.android.internal.b.c.b, Bitmap> mVar) {
                return l.a(mVar.a);
            }
        });
    }

    private <V extends net.nend.android.internal.b.a> k<V> b(int i, String str, String str2, String str3, net.nend.android.internal.utilities.g.c<V> cVar) {
        final Context context = this.b.get();
        return (k<V>) a(i, str, str2, str3, cVar).b((net.nend.android.internal.d.g<? super V, ? extends k<? extends R>>) new net.nend.android.internal.d.g<V, k<? extends V>>() { // from class: net.nend.android.internal.c.f.d.6
            /* JADX WARN: Incorrect types in method signature: (TV;)Lnet/nend/android/internal/d/k<+TV;>; */
            @Override // net.nend.android.internal.d.g
            public k a(net.nend.android.internal.b.a aVar) {
                net.nend.android.internal.utilities.b.a("ApiResponseTimeEvent", Long.valueOf(System.currentTimeMillis() - d.this.c));
                return d.this.a.a(aVar, context);
            }
        });
    }

    public static k<Bitmap> a(net.nend.android.internal.b.c.b bVar) {
        final String str = bVar.i;
        final Bitmap bitmapA = net.nend.android.internal.utilities.a.a.a(str);
        if (bitmapA == null || bitmapA.isRecycled()) {
            return l.a(net.nend.android.internal.utilities.g.a().b(), net.nend.android.internal.utilities.l.a(str)).b(new net.nend.android.internal.d.g<Bitmap, k<? extends Bitmap>>() { // from class: net.nend.android.internal.c.f.d.7
                @Override // net.nend.android.internal.d.g
                public k<? extends Bitmap> a(Bitmap bitmap) {
                    if (bitmap != null) {
                        net.nend.android.internal.utilities.a.a.a(str, bitmap);
                        return l.a(bitmapA);
                    }
                    return l.a((Throwable) new net.nend.android.internal.a.a(NendVideoAdClientError.FAILED_AD_DOWNLOAD));
                }
            });
        }
        return l.a(bitmapA);
    }

    public void b(net.nend.android.internal.b.c.b bVar) {
        if (TextUtils.isEmpty(bVar.f)) {
            return;
        }
        this.a.a(bVar.f);
    }

    public void b(String str) {
        this.i = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public k<net.nend.android.internal.b.c.b> c() {
        Context context = this.b.get();
        final net.nend.android.internal.d.e eVarA = l.a();
        new NendAdNativeClient(context, this.d, this.e).loadAd(new NendAdNativeClient.Callback() { // from class: net.nend.android.internal.c.f.d.8
            @Override // net.nend.android.NendAdNativeClient.Callback
            public void onSuccess(NendAdNative nendAdNative) {
                eVarA.a(net.nend.android.internal.b.c.b.a(nendAdNative));
            }

            @Override // net.nend.android.NendAdNativeClient.Callback
            public void onFailure(NendAdNativeClient.NendError nendError) {
                eVarA.a((Throwable) new net.nend.android.internal.a.a(NendVideoAdClientError.FAILED_AD_FALLBACK));
            }
        });
        return eVarA.a();
    }

    public void a(int i, String str) {
        this.d = i;
        this.e = str;
    }

    @Override // net.nend.android.internal.c.a
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e.a a(int i, String str, String str2) {
        return new e.a().a(i).a(str).b(str2).a(this.k).b(this.j.intValue());
    }
}
