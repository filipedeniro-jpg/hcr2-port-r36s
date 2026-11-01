package com.amazon.device.ads;

import com.my.target.aa;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class OrientationProperties {
    private Boolean allowOrientationChange;
    private ForceOrientation forceOrientation;
    private final JSONUtils.JSONUtilities jsonUtils;

    public OrientationProperties() {
        this(new JSONUtils.JSONUtilities());
    }

    OrientationProperties(JSONUtils.JSONUtilities jSONUtilities) {
        this.allowOrientationChange = true;
        this.forceOrientation = ForceOrientation.NONE;
        this.jsonUtils = jSONUtilities;
    }

    public Boolean isAllowOrientationChange() {
        return this.allowOrientationChange;
    }

    public void setAllowOrientationChange(Boolean bool) {
        this.allowOrientationChange = bool;
    }

    public ForceOrientation getForceOrientation() {
        return this.forceOrientation;
    }

    public void setForceOrientation(ForceOrientation forceOrientation) {
        this.forceOrientation = forceOrientation;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject() {
        JSONObject jSONObject = new JSONObject();
        this.jsonUtils.put(jSONObject, aa.d.bg, this.forceOrientation.toString());
        this.jsonUtils.put(jSONObject, aa.d.bf, this.allowOrientationChange.booleanValue());
        return jSONObject;
    }

    public void fromJSONObject(JSONObject jSONObject) {
        this.allowOrientationChange = Boolean.valueOf(this.jsonUtils.getBooleanFromJSON(jSONObject, aa.d.bf, this.allowOrientationChange.booleanValue()));
        this.forceOrientation = ForceOrientation.valueOf(this.jsonUtils.getStringFromJSON(jSONObject, aa.d.bg, this.forceOrientation.toString()).toUpperCase(Locale.US));
    }
}
