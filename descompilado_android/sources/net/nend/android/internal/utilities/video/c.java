package net.nend.android.internal.utilities.video;

import android.content.Context;
import android.location.Location;
import androidx.annotation.NonNull;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NendLocationSensorUtility.java */
/* JADX INFO: loaded from: classes2.dex */
class c {
    private OnCompleteListener<Location> a;

    c() {
    }

    k<JSONObject> a(Context context) {
        final net.nend.android.internal.d.e eVarA = l.a();
        if (e.a(context, "android.permission.ACCESS_FINE_LOCATION") || e.a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            this.a = new OnCompleteListener<Location>() { // from class: net.nend.android.internal.utilities.video.c.1
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public void onComplete(@NonNull Task<Location> task) {
                    boolean zIsSuccessful = task.isSuccessful();
                    Location result = task.getResult();
                    if (zIsSuccessful && result != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("lat", result.getLatitude());
                            jSONObject.put("lng", result.getLongitude());
                            eVarA.a(jSONObject);
                        } catch (JSONException e) {
                            eVarA.a(e.getCause());
                        }
                    } else {
                        eVarA.a((Throwable) new Exception("Failed to get location."));
                    }
                    c.this.a = null;
                }
            };
            LocationServices.getFusedLocationProviderClient(context).getLastLocation().addOnCompleteListener(this.a);
        } else {
            eVarA.a((Throwable) new Exception("Permission denied."));
        }
        return eVarA.a();
    }
}
