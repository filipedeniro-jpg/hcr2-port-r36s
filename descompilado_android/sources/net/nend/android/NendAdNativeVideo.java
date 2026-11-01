package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.nend.android.internal.c.f.d;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.k;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNativeVideo implements Parcelable {
    public static final Parcelable.Creator<NendAdNativeVideo> CREATOR = new Parcelable.Creator<NendAdNativeVideo>() { // from class: net.nend.android.NendAdNativeVideo.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NendAdNativeVideo createFromParcel(Parcel parcel) {
            return new NendAdNativeVideo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NendAdNativeVideo[] newArray(int i) {
            return new NendAdNativeVideo[i];
        }
    };
    private static final ArrayList<VideoClickOption> n = new ArrayList<VideoClickOption>() { // from class: net.nend.android.NendAdNativeVideo.6
        {
            add(VideoClickOption.FullScreen);
            add(VideoClickOption.LP);
        }
    };
    private static final ArrayList<c> o = new ArrayList<c>() { // from class: net.nend.android.NendAdNativeVideo.7
        {
            add(c.STANDBY);
            add(c.ACTIVATED);
            add(c.VIEWED);
            add(c.COMPLETED);
        }
    };

    @VisibleForTesting
    Set<b> a;
    private final VideoClickOption b;
    private final int c;
    private WeakReference<Context> d;
    private WeakReference<d> e;
    private net.nend.android.internal.b.c.b f;
    private Bitmap g;
    private NendAdNative h;
    private ArrayList<View> i;
    private NendAdNativeVideoListener j;
    private boolean k;
    private int l;
    private c m;

    @VisibleForTesting
    interface b {
        void a();
    }

    enum c {
        STANDBY,
        ACTIVATED,
        VIEWED,
        COMPLETED
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected NendAdNativeVideo(a aVar) {
        this.d = new WeakReference<>(null);
        this.e = new WeakReference<>(null);
        this.a = new HashSet();
        this.f = aVar.a;
        this.b = aVar.b;
        this.h = aVar.c;
        this.c = aVar.d;
        this.g = aVar.e;
        this.m = c.STANDBY;
    }

    void a(int i, int i2) {
        if (this.m.ordinal() < c.ACTIVATED.ordinal()) {
            this.m = c.ACTIVATED;
            a(this.d.get(), i, i2);
            NendAdNativeVideoListener nendAdNativeVideoListener = this.j;
            if (nendAdNativeVideoListener != null) {
                nendAdNativeVideoListener.onImpression(this);
            }
        }
    }

    public void deactivate() {
        Iterator<b> it = this.a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.a.clear();
        if (hasVideo()) {
            d dVar = this.e.get();
            if (dVar != null) {
                dVar.b(this.f);
            }
            this.f = null;
            this.g = null;
        } else {
            this.h = null;
        }
        this.j = null;
        unregisterInteractionViews();
    }

    public static final class a {
        private net.nend.android.internal.b.c.b a;
        private VideoClickOption b;
        private NendAdNative c;
        private int d;
        private Bitmap e;

        a a(net.nend.android.internal.b.c.b bVar) {
            this.a = bVar;
            return this;
        }

        a a(VideoClickOption videoClickOption) {
            this.b = videoClickOption;
            return this;
        }

        a a(NendAdNative nendAdNative) {
            this.c = nendAdNative;
            return this;
        }

        a a(int i) {
            this.d = i;
            return this;
        }

        a a(Bitmap bitmap) {
            this.e = bitmap;
            return this;
        }

        public NendAdNativeVideo a() {
            return new NendAdNativeVideo(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f, 0);
        parcel.writeInt(this.b.ordinal());
        parcel.writeInt(this.l);
        parcel.writeInt(this.m.ordinal());
        parcel.writeInt(this.c);
    }

    private NendAdNativeVideo(Parcel parcel) {
        this.d = new WeakReference<>(null);
        this.e = new WeakReference<>(null);
        this.a = new HashSet();
        this.f = (net.nend.android.internal.b.c.b) parcel.readParcelable(net.nend.android.internal.b.c.b.class.getClassLoader());
        this.b = n.get(parcel.readInt());
        this.l = parcel.readInt();
        this.m = o.get(parcel.readInt());
        this.c = parcel.readInt();
    }

    void a(WeakReference<Context> weakReference) {
        this.d = weakReference;
    }

    void b(WeakReference<d> weakReference) {
        this.e = weakReference;
    }

    net.nend.android.internal.b.c.b a() {
        return this.f;
    }

    int b() {
        return this.l;
    }

    void a(int i) {
        this.l = i;
    }

    public boolean hasVideo() {
        return this.f != null;
    }

    VideoClickOption c() {
        return this.b;
    }

    @Nullable
    public Bitmap getLogoImageBitmap() {
        if (this.g == null) {
            this.g = net.nend.android.internal.utilities.a.a.a(this.f.i);
        }
        return this.g;
    }

    public void downloadLogoImageBitmap(final NendAdNative.Callback callback) {
        Bitmap logoImageBitmap = getLogoImageBitmap();
        if (logoImageBitmap != null) {
            callback.onSuccess(logoImageBitmap);
        } else {
            d.a(this.f).a(new net.nend.android.internal.d.a()).a(new net.nend.android.internal.d.d<Bitmap>() { // from class: net.nend.android.NendAdNativeVideo.3
                @Override // net.nend.android.internal.d.d
                public void a(Bitmap bitmap) {
                    NendAdNativeVideo.this.g = bitmap;
                    callback.onSuccess(bitmap);
                }
            }).b(new net.nend.android.internal.d.d<Throwable>() { // from class: net.nend.android.NendAdNativeVideo.2
                @Override // net.nend.android.internal.d.d
                public void a(Throwable th) {
                    callback.onFailure(new net.nend.android.internal.a.b(k.ERR_UNEXPECTED));
                }
            });
        }
    }

    public boolean isMutePlayingFullscreen() {
        return this.k;
    }

    public void setMutePlayingFullscreen(boolean z) {
        this.k = z;
    }

    public String getLogoImageUrl() {
        return this.f.i;
    }

    String d() {
        return this.f.b;
    }

    public String getTitleText() {
        return this.f.j;
    }

    public String getDescriptionText() {
        return this.f.l;
    }

    public String getCallToActionText() {
        return this.f.o;
    }

    public String getAdvertiserName() {
        return this.f.k;
    }

    public int getVideoOrientation() {
        return this.f.c;
    }

    public float getUserRating() {
        return this.f.m;
    }

    public int getUserRatingCount() {
        return this.f.n;
    }

    public NendAdNative getFallbackAd() {
        return this.h;
    }

    public void registerInteractionViews(ArrayList<View> arrayList) {
        this.i = new ArrayList<>(arrayList);
        for (View view : this.i) {
            if (!(view instanceof NendAdNativeMediaView)) {
                view.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.NendAdNativeVideo.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NendAdNativeVideo nendAdNativeVideo = NendAdNativeVideo.this;
                        nendAdNativeVideo.a((Context) nendAdNativeVideo.d.get(), NendAdNativeVideo.this.d(), false);
                    }
                });
            }
        }
    }

    public void unregisterInteractionViews() {
        ArrayList<View> arrayList = this.i;
        if (arrayList != null) {
            for (View view : arrayList) {
                if (!(view instanceof NendAdNativeMediaView)) {
                    view.setOnClickListener(null);
                }
            }
            this.i.clear();
        }
    }

    void a(Context context, String str, boolean z) {
        a(context, z);
        net.nend.android.internal.utilities.d.a(context, str);
        NendAdNativeVideoListener nendAdNativeVideoListener = this.j;
        if (nendAdNativeVideoListener != null) {
            nendAdNativeVideoListener.onClickAd(this);
        }
    }

    void a(final Context context) {
        g.a().a(new g.d(context), new g.a<String>() { // from class: net.nend.android.NendAdNativeVideo.5
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str, Exception exc) {
                net.nend.android.internal.utilities.d.a(context, "https://www.nend.net/privacy/optsdkgate?uid=" + net.nend.android.internal.utilities.c.c(context) + "&spot=" + NendAdNativeVideo.this.c + "&gaid=" + str);
            }
        });
        NendAdNativeVideoListener nendAdNativeVideoListener = this.j;
        if (nendAdNativeVideoListener != null) {
            nendAdNativeVideoListener.onClickInformation(this);
        }
    }

    public void setListener(NendAdNativeVideoListener nendAdNativeVideoListener) {
        this.j = nendAdNativeVideoListener;
    }

    public NendAdNativeVideoListener getListener() {
        return this.j;
    }

    public enum VideoClickOption {
        FullScreen(1),
        LP(2);

        private final int a;

        VideoClickOption(int i) {
            this.a = i;
        }

        public int intValue() {
            return this.a;
        }
    }

    private void a(Context context, int i, int i2) {
        net.nend.android.internal.c.f.c.a(context, this.f.a, i, i2);
    }

    void b(Context context) {
        this.m = c.VIEWED;
        net.nend.android.internal.c.f.c.b(context, this.f.a);
    }

    boolean a(int i, boolean z) {
        return net.nend.android.internal.c.f.c.a(this.f, e(), i, z);
    }

    void a(Context context, int i, boolean z) {
        if (!z || this.m == c.COMPLETED) {
            return;
        }
        this.m = c.COMPLETED;
        net.nend.android.internal.c.f.c.a(context, this.f.a, true, false, i);
    }

    private void a(Context context, boolean z) {
        net.nend.android.internal.c.f.c.a(context, this.f.a, z);
    }

    private boolean e() {
        return this.m.ordinal() >= c.VIEWED.ordinal();
    }

    void a(b bVar) {
        this.a.add(bVar);
    }
}
