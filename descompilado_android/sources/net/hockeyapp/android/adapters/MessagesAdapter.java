package net.hockeyapp.android.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.tasks.AttachmentDownloader;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;
import net.hockeyapp.android.views.FeedbackMessageView;

/* JADX INFO: loaded from: classes2.dex */
public class MessagesAdapter extends BaseAdapter {
    private AttachmentListView attachmentListView;
    private TextView authorTextView;
    private Context context;
    private Date date;
    private TextView dateTextView;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    private SimpleDateFormat formatNew = new SimpleDateFormat("d MMM h:mm a");
    private TextView messageTextView;
    private ArrayList<FeedbackMessage> messagesList;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public MessagesAdapter(Context context, ArrayList<FeedbackMessage> arrayList) {
        this.context = context;
        this.messagesList = arrayList;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.messagesList.size();
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        FeedbackMessageView feedbackMessageView;
        FeedbackMessage feedbackMessage = this.messagesList.get(i);
        if (view == null) {
            feedbackMessageView = new FeedbackMessageView(this.context);
        } else {
            feedbackMessageView = (FeedbackMessageView) view;
        }
        if (feedbackMessage != null) {
            this.authorTextView = (TextView) feedbackMessageView.findViewById(12289);
            this.dateTextView = (TextView) feedbackMessageView.findViewById(12290);
            this.messageTextView = (TextView) feedbackMessageView.findViewById(12291);
            this.attachmentListView = (AttachmentListView) feedbackMessageView.findViewById(12292);
            try {
                this.date = this.format.parse(feedbackMessage.getCreatedAt());
                this.dateTextView.setText(this.formatNew.format(this.date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            this.authorTextView.setText(feedbackMessage.getName());
            this.messageTextView.setText(feedbackMessage.getText());
            this.attachmentListView.removeAllViews();
            for (FeedbackAttachment feedbackAttachment : feedbackMessage.getFeedbackAttachments()) {
                AttachmentView attachmentView = new AttachmentView(this.context, (ViewGroup) this.attachmentListView, feedbackAttachment, false);
                AttachmentDownloader.getInstance().download(feedbackAttachment, attachmentView);
                this.attachmentListView.addView(attachmentView);
            }
        }
        feedbackMessageView.setFeedbackMessageViewBgAndTextColor(i % 2 != 0 ? 1 : 0);
        return feedbackMessageView;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.messagesList.get(i);
    }

    public void clear() {
        ArrayList<FeedbackMessage> arrayList = this.messagesList;
        if (arrayList != null) {
            arrayList.clear();
        }
    }

    public void add(FeedbackMessage feedbackMessage) {
        ArrayList<FeedbackMessage> arrayList;
        if (feedbackMessage == null || (arrayList = this.messagesList) == null) {
            return;
        }
        arrayList.add(feedbackMessage);
    }
}
