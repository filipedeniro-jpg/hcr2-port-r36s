package com.amazon.device.ads;

import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
abstract class AAXParameterGroup {
    public static final UserIdAAXParameterGroup USER_ID = new UserIdAAXParameterGroup();
    public static final PublisherExtraParametersAAXParameterGroup PUBLISHER_EXTRA_PARAMETERS = new PublisherExtraParametersAAXParameterGroup();

    public abstract void evaluate(AAXParameter.ParameterData parameterData, JSONObject jSONObject);

    AAXParameterGroup() {
    }

    static final class UserIdAAXParameterGroup extends AAXParameterGroup {
        private final SISDeviceIdentifierAAXParameter adIdParameter;
        private final DirectedIdAAXParameter directedIdParameter;
        private final AdvertisingIdentifierAAXParameter idfaParameter;
        private final SHA1UDIDAAXParameter sha1udidParameter;

        UserIdAAXParameterGroup() {
            this(AAXParameterGroupParameter.DIRECTED_ID, AAXParameterGroupParameter.ADVERTISING_IDENTIFIER, AAXParameterGroupParameter.SIS_DEVICE_IDENTIFIER, AAXParameterGroupParameter.SHA1_UDID);
        }

        UserIdAAXParameterGroup(DirectedIdAAXParameter directedIdAAXParameter, AdvertisingIdentifierAAXParameter advertisingIdentifierAAXParameter, SISDeviceIdentifierAAXParameter sISDeviceIdentifierAAXParameter, SHA1UDIDAAXParameter sHA1UDIDAAXParameter) {
            this.directedIdParameter = directedIdAAXParameter;
            this.idfaParameter = advertisingIdentifierAAXParameter;
            this.adIdParameter = sISDeviceIdentifierAAXParameter;
            this.sha1udidParameter = sHA1UDIDAAXParameter;
        }

        @Override // com.amazon.device.ads.AAXParameterGroup
        public void evaluate(AAXParameter.ParameterData parameterData, JSONObject jSONObject) {
            if (this.directedIdParameter.evaluate(parameterData, jSONObject)) {
                return;
            }
            if (!this.idfaParameter.evaluate(parameterData, jSONObject)) {
                this.sha1udidParameter.evaluate(parameterData, jSONObject);
            }
            this.adIdParameter.evaluate(parameterData, jSONObject);
        }
    }

    static final class PublisherExtraParametersAAXParameterGroup extends AAXParameterGroup {
        private static final String LOGTAG = "PublisherExtraParametersAAXParameterGroup";
        private final DebugProperties debugProperties;
        private final MobileAdsLogger logger;

        public PublisherExtraParametersAAXParameterGroup() {
            this(new MobileAdsLogger(new LogcatLogger()).withLogTag(LOGTAG), DebugProperties.getInstance());
        }

        PublisherExtraParametersAAXParameterGroup(MobileAdsLogger mobileAdsLogger, DebugProperties debugProperties) {
            this.logger = mobileAdsLogger;
            this.debugProperties = debugProperties;
        }

        @Override // com.amazon.device.ads.AAXParameterGroup
        public void evaluate(AAXParameter.ParameterData parameterData, JSONObject jSONObject) {
            JSONObject jSONObjectProcessPJInputOrDebugParameterIfPresent = processPJInputOrDebugParameterIfPresent(parameterData);
            if (jSONObjectProcessPJInputOrDebugParameterIfPresent == null) {
                jSONObjectProcessPJInputOrDebugParameterIfPresent = new JSONObject();
                JSONArray valueDoNotRemove = AAXParameter.PUBLISHER_ASINS.getValueDoNotRemove(parameterData);
                if (valueDoNotRemove != null && valueDoNotRemove.length() > 0) {
                    try {
                        jSONObjectProcessPJInputOrDebugParameterIfPresent.put("asins", valueDoNotRemove.join(",").replaceAll("\"", ""));
                    } catch (JSONException e) {
                        this.logger.e("Error putting asins into pj, continuing but not including asins with pj", e);
                    }
                }
                JSONArray valueDoNotRemove2 = AAXParameter.PUBLISHER_KEYWORDS.getValueDoNotRemove(parameterData);
                if (valueDoNotRemove2 != null && valueDoNotRemove2.length() > 0) {
                    try {
                        jSONObjectProcessPJInputOrDebugParameterIfPresent.put("tk", valueDoNotRemove2);
                        jSONObjectProcessPJInputOrDebugParameterIfPresent.put("q", valueDoNotRemove2.join(" ").replaceAll("\"", ""));
                    } catch (JSONException e2) {
                        this.logger.e("Error putting either tk or q into pj, continuing but not including keywords with pj", e2);
                    }
                }
            }
            if (jSONObjectProcessPJInputOrDebugParameterIfPresent.length() > 0) {
                try {
                    jSONObject.put("pj", jSONObjectProcessPJInputOrDebugParameterIfPresent);
                } catch (JSONException e3) {
                    this.logger.e("Error storing pj created from asins and keywords, not including pj in request", e3);
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:12:0x002e  */
        private JSONObject processPJInputOrDebugParameterIfPresent(AAXParameter.ParameterData parameterData) {
            JSONObject jSONObject;
            Map<String, String> internalAdvancedOptions = parameterData.getInternalAdvancedOptions();
            if (internalAdvancedOptions == null || !internalAdvancedOptions.containsKey("pj")) {
                jSONObject = null;
            } else {
                String strRemove = internalAdvancedOptions.remove("pj");
                if (StringUtils.isNullOrEmpty(strRemove)) {
                    jSONObject = null;
                } else {
                    try {
                        jSONObject = new JSONObject(strRemove);
                    } catch (JSONException e) {
                        this.logger.e("Error creating JSON object for pj from advanced option. Ignoring advanced option.", e);
                        jSONObject = null;
                    }
                }
            }
            return this.debugProperties.getDebugPropertyAsJSONObject(DebugProperties.DEBUG_PJ, jSONObject);
        }
    }
}
