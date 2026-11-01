package net.nend.android.internal.b.c;

import android.os.Parcel;
import android.os.Parcelable;
import net.nend.android.NendAdFullBoard;
import org.json.JSONObject;

/* JADX INFO: compiled from: InterstitialVideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
public class a extends d {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: net.nend.android.internal.b.c.a.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }
    };
    public final int h;
    public final String i;
    public NendAdFullBoard j;

    @Override // net.nend.android.internal.b.c.d, net.nend.android.internal.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private a(JSONObject jSONObject) {
        super(jSONObject);
        this.h = jSONObject.getInt("skipOffset");
        this.i = jSONObject.getString("ctaButtonText");
    }

    private a() {
        this.h = 0;
        this.i = "";
    }

    private a(Parcel parcel) {
        super(parcel);
        this.h = parcel.readInt();
        this.i = parcel.readString();
    }

    public static a a(JSONObject jSONObject) {
        return new a(jSONObject);
    }

    public static a a(NendAdFullBoard nendAdFullBoard) {
        a aVar = new a();
        aVar.a("", "");
        aVar.b("", "");
        aVar.j = nendAdFullBoard;
        return aVar;
    }

    @Override // net.nend.android.internal.b.c.d, net.nend.android.internal.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.h);
        parcel.writeString(this.i);
    }
}
