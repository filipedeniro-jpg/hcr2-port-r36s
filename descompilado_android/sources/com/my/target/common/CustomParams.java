package com.my.target.common;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.bm;
import com.my.target.cb;
import com.my.target.g;
import com.my.target.i;

/* JADX INFO: loaded from: classes2.dex */
public final class CustomParams extends bm {

    @Nullable
    private String[] emails;

    @Nullable
    private String[] icqIds;

    @Nullable
    private String[] okIds;

    @Nullable
    private String[] vkIds;

    public interface Gender {
        public static final int FEMALE = 2;
        public static final int MALE = 1;
        public static final int UNKNOWN = 0;
        public static final int UNSPECIFIED = -1;
    }

    @Override // com.my.target.bm
    public void collectData(@NonNull Context context) {
    }

    @Nullable
    public String getMrgsAppId() {
        return getParam(i.ah);
    }

    public void setMrgsAppId(@Nullable String str) {
        addParam(i.ah, str);
    }

    @Nullable
    public String getMrgsUserId() {
        return getParam(i.ag);
    }

    public void setMrgsUserId(@Nullable String str) {
        addParam(i.ag, str);
    }

    @Nullable
    public String getMrgsId() {
        return getParam(i.af);
    }

    public void setMrgsId(@Nullable String str) {
        addParam(i.af, str);
    }

    @Nullable
    public String getEmail() {
        String[] strArr = this.emails;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    public void setEmail(@Nullable String str) {
        if (str == null) {
            this.emails = null;
        } else {
            this.emails = new String[]{str};
        }
        addParam("email", str);
    }

    @Nullable
    public String[] getEmails() {
        String[] strArr = this.emails;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setEmails(@Nullable String[] strArr) {
        if (strArr == null) {
            this.emails = null;
            removeParam("email");
        } else {
            this.emails = new String[strArr.length];
            System.arraycopy(strArr, 0, this.emails, 0, strArr.length);
            addParam("email", cb.a(strArr));
        }
    }

    @Nullable
    public String getIcqId() {
        String[] strArr = this.icqIds;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    public void setIcqId(@Nullable String str) {
        if (str == null) {
            this.icqIds = null;
        } else {
            this.icqIds = new String[]{str};
        }
        addParam(i.ai, str);
    }

    @Nullable
    public String[] getIcqIds() {
        String[] strArr = this.icqIds;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setIcqIds(@Nullable String[] strArr) {
        if (strArr == null) {
            this.icqIds = null;
            removeParam(i.ai);
        } else {
            this.icqIds = new String[strArr.length];
            System.arraycopy(strArr, 0, this.icqIds, 0, strArr.length);
            addParam(i.ai, cb.a(strArr));
        }
    }

    @Nullable
    public String getOkId() {
        String[] strArr = this.okIds;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    public void setOkId(@Nullable String str) {
        if (str == null) {
            this.okIds = null;
        } else {
            this.okIds = new String[]{str};
        }
        addParam(i.aj, str);
    }

    @Nullable
    public String[] getOkIds() {
        String[] strArr = this.okIds;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setOkIds(@Nullable String[] strArr) {
        if (strArr == null) {
            this.okIds = null;
            removeParam(i.aj);
        } else {
            this.okIds = new String[strArr.length];
            System.arraycopy(strArr, 0, this.okIds, 0, strArr.length);
            addParam(i.aj, cb.a(strArr));
        }
    }

    @Nullable
    public String getVKId() {
        String[] strArr = this.vkIds;
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        return strArr[0];
    }

    public void setVKId(@Nullable String str) {
        if (str == null) {
            this.vkIds = null;
        } else {
            this.vkIds = new String[]{str};
        }
        addParam(i.ak, str);
    }

    @Nullable
    public String[] getVKIds() {
        String[] strArr = this.vkIds;
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public void setVKIds(@Nullable String[] strArr) {
        if (strArr == null) {
            this.vkIds = null;
            removeParam(i.ak);
        } else {
            this.vkIds = new String[strArr.length];
            System.arraycopy(strArr, 0, this.vkIds, 0, strArr.length);
            addParam(i.ak, cb.a(strArr));
        }
    }

    public void setLang(@Nullable String str) {
        addParam(i.G, str);
    }

    @Nullable
    public String getLang() {
        return getParam(i.G);
    }

    public int getGender() {
        String param = getParam(i.H);
        if (param == null) {
            return -1;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public void setGender(int i) {
        if (i == 0 || i == 1 || i == 2) {
            g.a("gender param is set to " + i);
            addParam(i.H, String.valueOf(i));
            return;
        }
        removeParam(i.H);
        g.a("gender param removed");
    }

    public int getAge() {
        String param = getParam(i.I);
        if (param == null) {
            return 0;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public void setAge(int i) {
        if (i >= 0) {
            g.a("age param set to " + i);
            addParam(i.I, String.valueOf(i));
            return;
        }
        g.a("age param removed");
        removeParam(i.I);
    }

    public void setCustomParam(@NonNull String str, @Nullable String str2) {
        addParam(str, str2);
    }

    @Nullable
    public String getCustomParam(@NonNull String str) {
        return getParam(str);
    }
}
