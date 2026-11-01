package net.nend.android.internal.b.c;

import android.os.Parcel;
import android.os.Parcelable;
import net.nend.android.NendAdNative;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeAd.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends net.nend.android.internal.b.a {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: net.nend.android.internal.b.c.b.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }
    };
    public final int h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final float m;
    public final int n;
    public final String o;
    public NendAdNative p;

    @Override // net.nend.android.internal.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private b(JSONObject jSONObject) {
        super(jSONObject);
        this.h = jSONObject.getInt("acquiredId");
        this.i = jSONObject.getString("logoImageUrl");
        this.j = jSONObject.getString("title");
        this.k = jSONObject.getString("advertiserName");
        this.l = jSONObject.getString("description");
        if (!jSONObject.isNull("userRating")) {
            this.m = (float) jSONObject.getDouble("userRating");
        } else {
            this.m = -1.0f;
        }
        if (!jSONObject.isNull("userRatingCount")) {
            this.n = jSONObject.getInt("userRatingCount");
        } else {
            this.n = -1;
        }
        this.o = jSONObject.getString("ctaButtonText");
    }

    private b() {
        this.h = 0;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = 0.0f;
        this.n = 0;
        this.o = null;
    }

    private b(Parcel parcel) {
        super(parcel);
        this.h = parcel.readInt();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readFloat();
        this.n = parcel.readInt();
        this.o = parcel.readString();
    }

    public static b a(JSONObject jSONObject) {
        return new b(jSONObject);
    }

    public static b a(NendAdNative nendAdNative) {
        b bVar = new b();
        bVar.a("", "");
        bVar.p = nendAdNative;
        return bVar;
    }

    @Override // net.nend.android.internal.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeFloat(this.m);
        parcel.writeInt(this.n);
        parcel.writeString(this.o);
    }
}
