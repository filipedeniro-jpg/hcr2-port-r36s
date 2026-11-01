package net.nend.android.internal.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.SparseArray;
import com.facebook.internal.ServerProtocol;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AbsNendAdResponseParser.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class c<T> {
    static final /* synthetic */ boolean a = !c.class.desiredAssertionStatus();
    private final PackageManager b;

    protected T a(int i) {
        return null;
    }

    public abstract T a(a aVar, JSONObject jSONObject);

    public c(Context context) {
        if (context == null) {
            throw new NullPointerException(k.ERR_INVALID_CONTEXT.b());
        }
        this.b = context.getPackageManager();
    }

    public T a(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    JSONObject jSONObject = new JSONObject(URLDecoder.decode(str, "UTF-8"));
                    int i = jSONObject.getInt("status_code");
                    if (i != k.SUCCESS.a()) {
                        T tA = a(i);
                        if (tA != null) {
                            return tA;
                        }
                        throw new net.nend.android.internal.a.b(k.ERR_INVALID_AD_STATUS, "Ad status : " + jSONObject.getInt("status_code") + ", Message : " + jSONObject.getString("message"));
                    }
                    return a(a.a(jSONObject.getInt(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE)), jSONObject);
                }
            } catch (UnsupportedEncodingException unused) {
                if (a) {
                    return null;
                }
                throw new AssertionError();
            } catch (IllegalArgumentException e) {
                j.b(k.ERR_FAILED_TO_PARSE, e);
                return null;
            } catch (net.nend.android.internal.a.b e2) {
                j.b(k.ERR_FAILED_TO_PARSE, e2);
                return null;
            } catch (JSONException e3) {
                j.b(k.ERR_FAILED_TO_PARSE, e3);
                return null;
            }
        }
        throw new IllegalArgumentException(k.ERR_INVALID_RESPONSE.b());
    }

    protected boolean a(JSONArray jSONArray) throws JSONException {
        if (!a && jSONArray == null) {
            throw new AssertionError();
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            int i2 = jSONObject.getInt("logical_operator");
            if (i2 != 1) {
                if (i2 != 2) {
                    return false;
                }
                try {
                    this.b.getPackageInfo(jSONObject.getString("url_scheme"), 1);
                } catch (PackageManager.NameNotFoundException unused) {
                    continue;
                } catch (RuntimeException unused2) {
                }
                return false;
            }
            try {
                this.b.getPackageInfo(jSONObject.getString("url_scheme"), 1);
            } catch (PackageManager.NameNotFoundException | RuntimeException unused3) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: compiled from: AbsNendAdResponseParser.java */
    public enum a {
        UNSUPPORTED(0),
        BANNER_NORMAL(1),
        BANNER_WEB_VIEW(2),
        BANNER_APP_TARGETING(3),
        BANNER_DYNAMIC_RETARGETING(4),
        ICON_NORMAL(11),
        ICON_APP_TARGETING(13),
        INTERSTITIAL_NORMAL(21),
        INTERSTITIAL_APP_TARGETING(23),
        INTERSTITIAL_APP_TARGETING_ICON(24),
        INTERSTITIAL_APP_TARGETING_RECT(25),
        NATIVE_NORMAL(31),
        NATIVE_APP_TARGETING(32);

        private static final SparseArray<a> n = new SparseArray<>();
        private int o;

        static {
            for (a aVar : values()) {
                n.put(aVar.o, aVar);
            }
        }

        a(int i) {
            this.o = i;
        }

        protected static a a(int i) {
            return n.get(i, UNSUPPORTED);
        }
    }
}
