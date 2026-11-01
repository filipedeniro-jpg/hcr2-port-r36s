package net.hockeyapp.android.objects;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class Feedback implements Serializable {
    private static final long serialVersionUID = 2590172806951065320L;
    private String createdAt;
    private String email;
    private int id;
    private ArrayList<FeedbackMessage> messages;
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public ArrayList<FeedbackMessage> getMessages() {
        return this.messages;
    }

    public void setMessages(ArrayList<FeedbackMessage> arrayList) {
        this.messages = arrayList;
    }
}
