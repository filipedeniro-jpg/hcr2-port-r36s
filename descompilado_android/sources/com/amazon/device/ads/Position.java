package com.amazon.device.ads;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class Position {
    private Size size;
    private int x;
    private int y;

    public Position() {
        this.size = new Size(0, 0);
        this.x = 0;
        this.y = 0;
    }

    public Position(Size size, int i, int i2) {
        this.size = size;
        this.x = i;
        this.y = i2;
    }

    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int i) {
        this.x = i;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int i) {
        this.y = i;
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = this.size.toJSONObject();
        JSONUtils.put(jSONObject, "x", this.x);
        JSONUtils.put(jSONObject, "y", this.y);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        Position position = (Position) obj;
        return this.size.equals(position.size) && this.x == position.x && this.y == position.y;
    }
}
