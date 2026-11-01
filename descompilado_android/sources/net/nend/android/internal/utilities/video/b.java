package net.nend.android.internal.utilities.video;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import net.nend.android.internal.utilities.i;
import net.nend.android.internal.utilities.j;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EventTransmitUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class b extends i {
    public static void a(Context context, JSONObject jSONObject) {
        a(context, String.valueOf(System.currentTimeMillis()) + ".json", ".nend_sdk_queue_video_event", jSONObject.toString());
    }

    public static ArrayList<JSONObject> a(Context context) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        File fileA = a(context, ".nend_sdk_queue_video_event");
        if (fileA.isDirectory()) {
            for (File file : fileA.listFiles()) {
                try {
                    arrayList.add(new JSONObject(a(file)));
                } catch (JSONException e) {
                    j.b("Failed to query queued video event.", e);
                }
            }
        }
        return arrayList;
    }

    public static void b(Context context) {
        try {
            b(context, ".nend_sdk_queue_video_event");
        } catch (Exception e) {
            j.b("Failed to delete file.", e);
        }
    }

    public static int a(int i) {
        return Math.round(i / 1000.0f);
    }
}
