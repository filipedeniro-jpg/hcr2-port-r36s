package net.nend.android;

import com.ironsource.mediationsdk.IronSourceSegment;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdUserFeature {
    private final Gender a;
    private final String b;
    private final int c;
    private final JSONObject d;

    /* synthetic */ NendAdUserFeature(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private NendAdUserFeature(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
    }

    private boolean a() {
        return this.a == null && this.b == null && this.c < 0 && this.d.length() == 0;
    }

    public JSONObject toJson() {
        if (a()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("gender", this.a != null ? this.a.toString() : null);
            if (this.b != null) {
                jSONObject.put("birthday", this.b);
            }
            if (this.c >= 0 && this.b == null) {
                jSONObject.put(IronSourceSegment.AGE, this.c);
            }
            if (this.d.length() > 0) {
                jSONObject.put("custom", this.d);
            }
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: net.nend.android.NendAdUserFeature$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[Gender.values().length];

        static {
            try {
                a[Gender.MALE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[Gender.FEMALE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public enum Gender {
        MALE,
        FEMALE;

        @Override // java.lang.Enum
        public String toString() {
            int i = AnonymousClass1.a[ordinal()];
            if (i == 1) {
                return "male";
            }
            if (i != 2) {
                return null;
            }
            return "female";
        }
    }

    public static class Builder {
        private Gender a = null;
        private String b = null;
        private int c = -1;
        private JSONObject d = new JSONObject();
        private SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        public Builder setGender(Gender gender) {
            this.a = gender;
            return this;
        }

        public Builder setBirthday(int i, int i2, int i3) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2 - 1, i3);
            gregorianCalendar.setLenient(false);
            try {
                this.b = this.e.format(gregorianCalendar.getTime());
            } catch (IllegalArgumentException unused) {
                this.b = null;
            }
            return this;
        }

        public Builder setAge(int i) {
            this.c = i;
            return this;
        }

        public Builder addCustomFeature(String str, int i) {
            try {
                this.d.put(str, i);
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder addCustomFeature(String str, double d) {
            try {
                this.d.put(str, d);
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder addCustomFeature(String str, String str2) {
            try {
                this.d.put(str, str2);
            } catch (JSONException unused) {
            }
            return this;
        }

        public Builder addCustomFeature(String str, boolean z) {
            try {
                this.d.put(str, z);
            } catch (JSONException unused) {
            }
            return this;
        }

        public NendAdUserFeature build() {
            return new NendAdUserFeature(this, null);
        }
    }
}
