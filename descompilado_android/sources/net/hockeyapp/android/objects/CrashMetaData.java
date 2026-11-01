package net.hockeyapp.android.objects;

/* JADX INFO: loaded from: classes2.dex */
public class CrashMetaData {
    private String userDescription;
    private String userEmail;
    private String userID;

    public String getUserDescription() {
        return this.userDescription;
    }

    public void setUserDescription(String str) {
        this.userDescription = str;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String str) {
        this.userEmail = str;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String str) {
        this.userID = str;
    }

    public String toString() {
        return "\n" + CrashMetaData.class.getSimpleName() + "\nuserDescription " + this.userDescription + "\nuserEmail       " + this.userEmail + "\nuserID          " + this.userID;
    }
}
