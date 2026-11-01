package net.nend.android.internal.b;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import java.io.File;
import java.util.Arrays;
import org.json.JSONObject;

/* JADX INFO: compiled from: Nend2Ad.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: net.nend.android.internal.b.a.1
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
    public final String a;
    public final String b;
    public final int c;
    public final String d;
    public final int e;
    public String f;
    public String g;
    private final long h;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public a() {
        this.a = "";
        this.b = "";
        this.c = 0;
        this.d = "";
        this.e = 0;
        this.h = Long.MAX_VALUE;
    }

    public a(JSONObject jSONObject) {
        this.a = jSONObject.getString("id");
        this.d = jSONObject.getString("videoUrl");
        this.b = jSONObject.getString("clickUrl");
        this.c = jSONObject.getInt("orientation");
        if (!jSONObject.isNull("viewEventTime")) {
            this.e = jSONObject.getInt("viewEventTime");
        } else {
            this.e = -1;
        }
        this.h = System.currentTimeMillis();
    }

    public a(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readInt();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readLong();
    }

    public boolean a() {
        return b() && !c();
    }

    public void a(String str, String str2) {
        this.f = str;
        this.g = str2;
    }

    @VisibleForTesting
    public boolean b() {
        for (String str : Arrays.asList(this.f, this.g)) {
            if (!TextUtils.isEmpty(str) && !new File(str).exists()) {
                return false;
            }
        }
        return true;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeLong(this.h);
    }

    public boolean c() {
        return System.currentTimeMillis() - this.h >= 259200000;
    }
}
