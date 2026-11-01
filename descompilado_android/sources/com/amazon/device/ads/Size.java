package com.amazon.device.ads;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class Size {
    private int height;
    private int width;

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public Size(String str) {
        int iMax;
        String[] strArrSplit;
        int i = 0;
        if (str == null || (strArrSplit = str.split("x")) == null || strArrSplit.length != 2) {
            iMax = 0;
        } else {
            int iMax2 = Math.max(parseInt(strArrSplit[0], 0), 0);
            iMax = Math.max(parseInt(strArrSplit[1], 0), 0);
            i = iMax2;
        }
        this.width = i;
        this.height = iMax;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    private static int parseInt(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        JSONUtils.put(jSONObject, "width", this.width);
        JSONUtils.put(jSONObject, "height", this.height);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.width == size.width && this.height == size.height;
    }
}
