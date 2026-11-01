package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import java.util.WeakHashMap;
import net.nend.android.internal.a.b;
import net.nend.android.internal.ui.a.f;
import net.nend.android.internal.utilities.c;
import net.nend.android.internal.utilities.d;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNative implements Parcelable {
    public static final Parcelable.Creator<NendAdNative> CREATOR = new Parcelable.Creator<NendAdNative>() { // from class: net.nend.android.NendAdNative.2
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NendAdNative createFromParcel(Parcel parcel) {
            return new NendAdNative(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public NendAdNative[] newArray(int i) {
            return new NendAdNative[i];
        }
    };
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private int k;
    private boolean l;
    private WeakHashMap<String, Bitmap> m;
    private OnClickListener n;
    private f o;

    public interface Callback {
        void onFailure(Exception exc);

        void onSuccess(Bitmap bitmap);
    }

    public interface OnClickListener {
        void onClick(NendAdNative nendAdNative);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private NendAdNative(a aVar) {
        this.l = false;
        this.m = new WeakHashMap<>();
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.o = new f();
    }

    public void intoView(View view, NendAdNativeViewBinder nendAdNativeViewBinder) {
        intoView(new NendAdNativeViewHolder(view, nendAdNativeViewBinder));
    }

    public void intoView(NendAdNativeViewHolder nendAdNativeViewHolder) {
        this.o.a(nendAdNativeViewHolder, this);
    }

    public void onClick() {
        OnClickListener onClickListener = this.n;
        if (onClickListener != null) {
            onClickListener.onClick(this);
        }
    }

    public void onClickInformation(final Context context) {
        g.a().a(new g.d(context), new g.a<String>() { // from class: net.nend.android.NendAdNative.1
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str, Exception exc) {
                d.a(context, "https://www.nend.net/privacy/optsdkgate?uid=" + c.c(context) + "&spot=" + NendAdNative.this.k + "&gaid=" + str);
            }
        });
    }

    public void onImpression() {
        if (this.l) {
            return;
        }
        this.l = true;
        g.a().a(new g.f(a()));
        j.b("send impression");
    }

    public Bitmap getCache(String str) {
        return this.m.get(str);
    }

    public void setCache(String str, Bitmap bitmap) {
        this.m.put(str, bitmap);
    }

    public String getCampaignId() {
        return this.j;
    }

    public String getAdImageUrl() {
        return this.a;
    }

    public String getLogoImageUrl() {
        return this.b;
    }

    public String getClickUrl() {
        return this.c;
    }

    private String a() {
        return this.d;
    }

    public String getTitleText() {
        return this.e;
    }

    public String getContentText() {
        return this.f;
    }

    public String getPromotionUrl() {
        return this.g;
    }

    public String getPromotionName() {
        return this.h;
    }

    public String getActionText() {
        return this.i;
    }

    public void setSpotId(int i) {
        this.k = i;
    }

    public boolean isSentImpression() {
        return this.l;
    }

    public void downloadAdImage(Callback callback) {
        if (getAdImageUrl() != null) {
            this.o.a(getAdImageUrl(), this, callback);
        } else {
            callback.onFailure(new b(k.ERR_NO_AD_IMAGE));
        }
    }

    public void downloadLogoImage(Callback callback) {
        if (getLogoImageUrl() != null) {
            this.o.a(getLogoImageUrl(), this, callback);
        } else {
            callback.onFailure(new b(k.ERR_NO_LOGO_IMAGE));
        }
    }

    public void activate(View view, TextView textView) {
        if (view == null || textView == null) {
            throw new NullPointerException("adContainer and prTextView is required.");
        }
        this.o.a(view, textView, this);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.n = onClickListener;
    }

    public enum AdvertisingExplicitly {
        PR("PR"),
        SPONSORED("Sponsored"),
        AD("広告"),
        PROMOTION("プロモーション");

        private String a;

        AdvertisingExplicitly(String str) {
            this.a = str;
        }

        public String getText() {
            return this.a;
        }
    }

    public static final class a {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;

        public a a(String str) {
            if (str != null) {
                this.a = str.replaceAll(" ", "%20");
            } else {
                this.a = null;
            }
            return this;
        }

        public a b(String str) {
            if (str != null) {
                this.b = str.replaceAll(" ", "%20");
            } else {
                this.b = null;
            }
            return this;
        }

        public a c(String str) {
            if (str != null) {
                this.c = str.replaceAll(" ", "%20");
            } else {
                this.c = null;
            }
            return this;
        }

        public a d(String str) {
            if (str != null) {
                this.d = str.replaceAll(" ", "%20");
            } else {
                this.d = null;
            }
            return this;
        }

        public a e(String str) {
            this.e = str;
            return this;
        }

        public a f(String str) {
            this.f = str;
            return this;
        }

        public a g(String str) {
            this.g = str;
            return this;
        }

        public a h(String str) {
            this.h = str;
            return this;
        }

        public a i(String str) {
            this.i = str;
            return this;
        }

        public a j(String str) {
            this.j = str;
            return this;
        }

        public NendAdNative a() {
            return new NendAdNative(this);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeInt(this.k);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
    }

    protected NendAdNative(Parcel parcel) {
        this.l = false;
        this.m = new WeakHashMap<>();
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readInt();
        this.l = parcel.readByte() != 0;
    }
}
