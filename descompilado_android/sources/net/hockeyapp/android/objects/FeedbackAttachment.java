package net.hockeyapp.android.objects;

import java.io.File;
import java.io.FilenameFilter;
import java.io.Serializable;
import net.hockeyapp.android.Constants;

/* JADX INFO: loaded from: classes2.dex */
public class FeedbackAttachment implements Serializable {
    private static final long serialVersionUID = 5059651319640956830L;
    private String createdAt;
    private String filename;
    private int id;
    private int messageId;
    private String updatedAt;
    private String url;

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public int getMessageId() {
        return this.messageId;
    }

    public void setMessageId(int i) {
        this.messageId = i;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String str) {
        this.filename = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    public String getCacheId() {
        return "" + this.messageId + this.id;
    }

    public boolean isAvailableInCache() {
        File[] fileArrListFiles;
        File hockeyAppStorageDir = Constants.getHockeyAppStorageDir();
        return hockeyAppStorageDir.exists() && hockeyAppStorageDir.isDirectory() && (fileArrListFiles = hockeyAppStorageDir.listFiles(new FilenameFilter() { // from class: net.hockeyapp.android.objects.FeedbackAttachment.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str) {
                return str.equals(FeedbackAttachment.this.getCacheId());
            }
        })) != null && fileArrListFiles.length == 1;
    }

    public String toString() {
        return "\n" + FeedbackAttachment.class.getSimpleName() + "\nid         " + this.id + "\nmessage id " + this.messageId + "\nfilename   " + this.filename + "\nurl        " + this.url + "\ncreatedAt  " + this.createdAt + "\nupdatedAt  " + this.updatedAt;
    }
}
