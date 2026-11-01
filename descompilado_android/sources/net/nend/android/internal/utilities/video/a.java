package net.nend.android.internal.utilities.video;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;

/* JADX INFO: compiled from: DisplayTimeManager.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements Parcelable {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: net.nend.android.internal.utilities.video.a.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return null;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }
    };
    private long a;
    private long b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public a() {
        this.a = 0L;
        this.b = 0L;
    }

    private a(Parcel parcel) {
        this.a = parcel.readLong();
        this.b = parcel.readLong();
    }

    public void a() {
        this.a = SystemClock.elapsedRealtime();
    }

    public void b() {
        if (d()) {
            this.b += SystemClock.elapsedRealtime() - this.a;
        }
        this.a = 0L;
    }

    public long c() {
        return this.b;
    }

    public boolean d() {
        return this.a != 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.a);
        parcel.writeLong(this.b);
    }
}
