package com.inmobi.ads;

import androidx.annotation.Nullable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class NativeTracker {
    private static final String e = "NativeTracker";
    String a;
    TrackerEventType b;
    Map<String, String> c;
    Map<String, Object> d;
    private String f;
    private int g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public enum TrackerEventType {
        TRACKER_EVENT_TYPE_UNKNOWN,
        TRACKER_EVENT_TYPE_LOAD,
        TRACKER_EVENT_TYPE_CLIENT_FILL,
        TRACKER_EVENT_TYPE_RENDER,
        TRACKER_EVENT_TYPE_PAGE_VIEW,
        TRACKER_EVENT_TYPE_CLICK,
        TRACKER_EVENT_TYPE_VIDEO_RENDER,
        TRACKER_EVENT_TYPE_FALLBACK_URL,
        TRACKER_EVENT_TYPE_PLAY,
        TRACKER_EVENT_TYPE_Q1,
        TRACKER_EVENT_TYPE_Q2,
        TRACKER_EVENT_TYPE_Q3,
        TRACKER_EVENT_TYPE_Q4,
        TRACKER_EVENT_TYPE_CREATIVE_VIEW,
        TRACKER_EVENT_TYPE_FULLSCREEN,
        TRACKER_EVENT_TYPE_EXIT_FULLSCREEN,
        TRACKER_EVENT_TYPE_MUTE,
        TRACKER_EVENT_TYPE_UNMUTE,
        TRACKER_EVENT_TYPE_PAUSE,
        TRACKER_EVENT_TYPE_RESUME,
        TRACKER_EVENT_TYPE_ERROR,
        TRACKER_EVENT_TYPE_END_CARD_CLOSE,
        TRACKER_EVENT_TYPE_CUSTOM_EVENT_VIDEO,
        TRACKER_EVENT_TYPE_IAS,
        TRACKER_EVENT_TYPE_MOAT,
        TRACKER_EVENT_TYPE_DOWNLOADER_INIT,
        TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADING,
        TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADED,
        TRACKER_EVENT_TYPE_DOWNLOADER_ERROR
    }

    public NativeTracker(String str, int i, TrackerEventType trackerEventType, Map<String, String> map) {
        this("url_ping", str, i, trackerEventType, map);
    }

    private NativeTracker(String str, String str2, int i, TrackerEventType trackerEventType, Map<String, String> map) {
        this.f = str;
        this.a = str2.trim();
        this.g = i;
        this.b = trackerEventType;
        this.c = map;
    }

    @Nullable
    static NativeTracker a(JSONObject jSONObject) {
        String str;
        try {
            String string = jSONObject.getString("type");
            if (string != null && string.length() != 0) {
                String lowerCase = string.toLowerCase(Locale.US);
                switch (lowerCase.hashCode()) {
                    case -1918378017:
                        str = "html_script";
                        lowerCase.equals(str);
                        break;
                    case -970292670:
                        str = "url_ping";
                        lowerCase.equals(str);
                        break;
                    case -284840886:
                        str = "unknown";
                        lowerCase.equals(str);
                        break;
                    case 2015859192:
                        lowerCase.equals("webview_ping");
                        break;
                }
            }
            return new NativeTracker(jSONObject.getString("url"), jSONObject.optInt("eventId", -1), a(jSONObject.getString("eventType")), new HashMap());
        } catch (JSONException e2) {
            new StringBuilder("Error building tracker from JSONObject; ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return null;
        }
    }

    static TrackerEventType a(String str) {
        if (str == null || str.length() == 0) {
            return TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
        }
        switch (str) {
            case "load":
                return TrackerEventType.TRACKER_EVENT_TYPE_LOAD;
            case "client_fill":
                return TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL;
            case "Impression":
                return TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
            case "VideoImpression":
                return TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER;
            case "page_view":
                return TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW;
            case "click":
                return TrackerEventType.TRACKER_EVENT_TYPE_CLICK;
            case "start":
                return TrackerEventType.TRACKER_EVENT_TYPE_PLAY;
            case "firstQuartile":
                return TrackerEventType.TRACKER_EVENT_TYPE_Q1;
            case "midpoint":
                return TrackerEventType.TRACKER_EVENT_TYPE_Q2;
            case "thirdQuartile":
                return TrackerEventType.TRACKER_EVENT_TYPE_Q3;
            case "complete":
                return TrackerEventType.TRACKER_EVENT_TYPE_Q4;
            case "creativeView":
                return TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW;
            case "fullscreen":
                return TrackerEventType.TRACKER_EVENT_TYPE_FULLSCREEN;
            case "exitFullscreen":
                return TrackerEventType.TRACKER_EVENT_TYPE_EXIT_FULLSCREEN;
            case "mute":
                return TrackerEventType.TRACKER_EVENT_TYPE_MUTE;
            case "unmute":
                return TrackerEventType.TRACKER_EVENT_TYPE_UNMUTE;
            case "pause":
                return TrackerEventType.TRACKER_EVENT_TYPE_PAUSE;
            case "resume":
                return TrackerEventType.TRACKER_EVENT_TYPE_RESUME;
            case "error":
                return TrackerEventType.TRACKER_EVENT_TYPE_ERROR;
            case "IAS_VIEWABILITY":
                return TrackerEventType.TRACKER_EVENT_TYPE_IAS;
            case "zMoatVASTIDs":
                return TrackerEventType.TRACKER_EVENT_TYPE_MOAT;
            case "closeEndCard":
                return TrackerEventType.TRACKER_EVENT_TYPE_END_CARD_CLOSE;
            case "unknown":
            default:
                return TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
        }
    }

    public final String toString() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.f);
            jSONObject.put("url", this.a);
            switch (this.b) {
                case TRACKER_EVENT_TYPE_LOAD:
                    str = "load";
                    break;
                case TRACKER_EVENT_TYPE_CLIENT_FILL:
                    str = "client_fill";
                    break;
                case TRACKER_EVENT_TYPE_RENDER:
                    str = com.my.target.bj.fX;
                    break;
                case TRACKER_EVENT_TYPE_VIDEO_RENDER:
                    str = "VideoImpression";
                    break;
                case TRACKER_EVENT_TYPE_PAGE_VIEW:
                    str = "page_view";
                    break;
                case TRACKER_EVENT_TYPE_CLICK:
                    str = com.my.target.aq.a.dF;
                    break;
                case TRACKER_EVENT_TYPE_PLAY:
                    str = "start";
                    break;
                case TRACKER_EVENT_TYPE_Q1:
                    str = com.my.target.bj.gq;
                    break;
                case TRACKER_EVENT_TYPE_Q2:
                    str = com.my.target.bj.gr;
                    break;
                case TRACKER_EVENT_TYPE_Q3:
                    str = com.my.target.bj.gs;
                    break;
                case TRACKER_EVENT_TYPE_Q4:
                    str = com.my.target.bj.gt;
                    break;
                case TRACKER_EVENT_TYPE_CREATIVE_VIEW:
                    str = "creativeView";
                    break;
                case TRACKER_EVENT_TYPE_FULLSCREEN:
                    str = "fullscreen";
                    break;
                case TRACKER_EVENT_TYPE_EXIT_FULLSCREEN:
                    str = com.my.target.bj.gA;
                    break;
                case TRACKER_EVENT_TYPE_MUTE:
                    str = com.my.target.bj.gv;
                    break;
                case TRACKER_EVENT_TYPE_UNMUTE:
                    str = com.my.target.bj.gw;
                    break;
                case TRACKER_EVENT_TYPE_PAUSE:
                    str = "pause";
                    break;
                case TRACKER_EVENT_TYPE_RESUME:
                    str = "resume";
                    break;
                case TRACKER_EVENT_TYPE_ERROR:
                    str = "error";
                    break;
                case TRACKER_EVENT_TYPE_IAS:
                    str = "IAS_VIEWABILITY";
                    break;
                case TRACKER_EVENT_TYPE_MOAT:
                    str = "zMoatVASTIDs";
                    break;
                case TRACKER_EVENT_TYPE_END_CARD_CLOSE:
                    str = "closeEndCard";
                    break;
                default:
                    str = "unknown";
                    break;
            }
            jSONObject.put("eventType", str);
            jSONObject.put("eventId", this.g);
            jSONObject.put("extras", com.inmobi.commons.core.utilities.d.a((Map<String, String>) (this.c == null ? new HashMap() : this.c), ","));
            return jSONObject.toString();
        } catch (JSONException e2) {
            StringBuilder sb = new StringBuilder("Error serializing an ");
            sb.append(e);
            sb.append(" instance (");
            sb.append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return "";
        }
    }
}
