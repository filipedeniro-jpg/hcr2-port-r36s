package net.nend.android.internal.b.c;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: RewardedVideoAd.java */
/* JADX INFO: loaded from: classes2.dex */
public class c extends d {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: net.nend.android.internal.b.c.c.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i) {
            return new c[i];
        }
    };
    public final String h;
    public final int i;

    @Override // net.nend.android.internal.b.c.d, net.nend.android.internal.b.a, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private c(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("reward");
        this.h = jSONObject2.getString("currName");
        this.i = jSONObject2.getInt("currAmount");
    }

    private c(Parcel parcel) {
        super(parcel);
        this.h = parcel.readString();
        this.i = parcel.readInt();
    }

    public static c a(JSONObject jSONObject) {
        return new c(jSONObject);
    }

    @Override // net.nend.android.internal.b.c.d, net.nend.android.internal.b.a, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
    }
}
