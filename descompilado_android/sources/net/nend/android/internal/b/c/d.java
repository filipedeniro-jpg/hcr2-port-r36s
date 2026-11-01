package net.nend.android.internal.b.c;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: VideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class d extends net.nend.android.internal.b.a {
    public static final Parcelable.Creator<d> CREATOR = new Parcelable.Creator<d>() { // from class: net.nend.android.internal.b.c.d.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d createFromParcel(Parcel parcel) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public d[] newArray(int i) {
            return null;
        }
    };
    public final String[] k;
    public final int l;
    public String m;
    public String n;
    public String o;
    public String p;

    @Override // net.nend.android.internal.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public d() {
        this.k = new String[0];
        this.l = 0;
    }

    public d(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        this.m = jSONObject.getString("endcardHtml");
        this.l = jSONObject.getInt("displayType");
        if (!jSONObject.isNull("htmlOnPlaying")) {
            this.n = jSONObject.getString("htmlOnPlaying");
        } else {
            this.n = "";
        }
        JSONArray jSONArray = jSONObject.getJSONArray("assets");
        this.k = new String[jSONArray.length()];
        int i = 0;
        while (true) {
            String[] strArr = this.k;
            if (i >= strArr.length) {
                return;
            }
            strArr[i] = jSONArray.getString(i);
            i++;
        }
    }

    public d(Parcel parcel) {
        super(parcel);
        this.k = parcel.createStringArray();
        this.l = parcel.readInt();
        this.o = parcel.readString();
        this.p = parcel.readString();
    }

    public void b(String str, String str2) {
        this.o = str;
        this.p = str2;
        this.m = null;
        this.n = null;
    }

    @Override // net.nend.android.internal.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.k);
        parcel.writeInt(this.l);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
    }
}
