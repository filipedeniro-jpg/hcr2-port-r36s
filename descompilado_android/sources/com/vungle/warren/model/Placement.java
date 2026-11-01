package com.vungle.warren.model;

import android.util.Log;
import androidx.annotation.NonNull;
import com.google.gson.JsonObject;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.persistence.MemoryUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class Placement implements Memorable, Cloneable {
    private static final String TAG = "Placement";
    private final Set<String> advertisementIDs;
    private final boolean autoCached;
    private final String identifier;
    private final boolean incentivized;
    private long wakeupTime;

    public Placement(String str) {
        this.identifier = str;
        this.autoCached = false;
        this.incentivized = false;
        this.advertisementIDs = new LinkedHashSet();
    }

    public Placement(byte[] bArr) {
        if (bArr.length == 0) {
            throw new IllegalArgumentException("Cannot recreate from empty array!");
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        this.identifier = MemoryUtils.extractString(byteBufferWrap);
        this.advertisementIDs = new LinkedHashSet(Arrays.asList(MemoryUtils.extractStringArray(byteBufferWrap)));
        this.incentivized = byteBufferWrap.get() == 1;
        this.autoCached = byteBufferWrap.get() == 1;
        this.wakeupTime = byteBufferWrap.getLong();
    }

    public Placement(JsonObject jsonObject) throws IllegalArgumentException {
        if (jsonObject.has("reference_id")) {
            this.identifier = jsonObject.get("reference_id").getAsString();
            this.autoCached = jsonObject.has("is_auto_cached") && jsonObject.get("is_auto_cached").getAsBoolean();
            this.incentivized = jsonObject.has("is_incentivized") && jsonObject.get("is_incentivized").getAsBoolean();
            this.advertisementIDs = new LinkedHashSet();
            return;
        }
        throw new IllegalArgumentException("Missing placement reference ID, cannot use placement!");
    }

    public void addAdvertisementID(String str) {
        this.advertisementIDs.add(str);
    }

    public boolean removeAdvertisementID(String str) {
        return this.advertisementIDs.remove(str);
    }

    public List<String> getAdvertisementIDs() {
        return new ArrayList(this.advertisementIDs);
    }

    public void snooze(long j) {
        this.wakeupTime = System.currentTimeMillis() + (j * 1000);
    }

    public long getWakeupTime() {
        return this.wakeupTime;
    }

    @Override // com.vungle.warren.persistence.Memorable
    public byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            MemoryUtils.writeString(this.identifier, byteArrayOutputStream);
            MemoryUtils.writeStringArray((String[]) this.advertisementIDs.toArray(new String[this.advertisementIDs.size()]), byteArrayOutputStream);
            int i = 1;
            byteArrayOutputStream.write(this.incentivized ? 1 : 0);
            if (!this.autoCached) {
                i = 0;
            }
            byteArrayOutputStream.write(i);
            byteArrayOutputStream.write(MemoryUtils.toBytes(this.wakeupTime));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            Log.e("Placement#toByteArray()", "Failed to write " + this + " to a byte array.");
            return new byte[0];
        }
    }

    @Override // com.vungle.warren.persistence.Memorable
    @NonNull
    public String getId() {
        return this.identifier;
    }

    public boolean equalsIgnoreAdvertisements(Object obj) {
        if (!(obj instanceof Placement)) {
            return false;
        }
        Placement placement = (Placement) obj;
        return placement.identifier.equals(this.identifier) && placement.incentivized == this.incentivized && placement.autoCached == this.autoCached;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Placement placement = (Placement) obj;
        if (this.autoCached != placement.autoCached || this.incentivized != placement.incentivized || this.wakeupTime != placement.wakeupTime) {
            return false;
        }
        String str = this.identifier;
        if (str == null ? placement.identifier != null : !str.equals(placement.identifier)) {
            return false;
        }
        Set<String> set = this.advertisementIDs;
        if (set != null) {
            return set.equals(placement.advertisementIDs);
        }
        return placement.advertisementIDs == null;
    }

    public int hashCode() {
        String str = this.identifier;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        Set<String> set = this.advertisementIDs;
        int iHashCode2 = (((((iHashCode + (set != null ? set.hashCode() : 0)) * 31) + (this.autoCached ? 1 : 0)) * 31) + (this.incentivized ? 1 : 0)) * 31;
        long j = this.wakeupTime;
        return iHashCode2 + ((int) (j ^ (j >>> 32)));
    }

    public boolean isAutoCached() {
        return this.autoCached;
    }

    public boolean isIncentivized() {
        return this.incentivized;
    }

    public Placement copy() {
        try {
            return (Placement) clone();
        } catch (CloneNotSupportedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
            return null;
        }
    }

    public static Placement restore(int i, int i2, byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return new Placement(Arrays.copyOfRange(bArr, 1, bArr.length));
    }

    public String toString() {
        return "Placement{identifier='" + this.identifier + "', advertisementIDs=" + this.advertisementIDs + ", autoCached=" + this.autoCached + ", incentivized=" + this.incentivized + ", wakeupTime=" + this.wakeupTime + '}';
    }
}
