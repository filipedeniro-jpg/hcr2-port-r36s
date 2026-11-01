package net.hockeyapp.android.views;

import android.content.Context;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes2.dex */
public class FeedbackMessageView extends LinearLayout {
    public static final int ATTACHMENT_LIST_VIEW_ID = 12292;
    public static final int AUTHOR_TEXT_VIEW_ID = 12289;
    public static final int DATE_TEXT_VIEW_ID = 12290;
    public static final int MESSAGE_TEXT_VIEW_ID = 12291;
    private AttachmentListView attachmentListView;
    private TextView authorTextView;
    private TextView dateTextView;
    private TextView messageTextView;
    private boolean ownMessage;

    public FeedbackMessageView(Context context) {
        this(context, true);
    }

    public FeedbackMessageView(Context context, boolean z) {
        super(context);
        this.ownMessage = z;
        loadLayoutParams(context);
        loadAuthorLabel(context);
        loadDateLabel(context);
        loadMessageLabel(context);
        loadAttachmentList(context);
    }

    private void loadLayoutParams(Context context) {
        setOrientation(1);
        setGravity(3);
        setBackgroundColor(-3355444);
    }

    private void loadAuthorLabel(Context context) {
        this.authorTextView = new TextView(context);
        this.authorTextView.setId(12289);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(iApplyDimension, iApplyDimension, iApplyDimension, 0);
        this.authorTextView.setLayoutParams(layoutParams);
        this.authorTextView.setShadowLayer(1.0f, 0.0f, 1.0f, -1);
        this.authorTextView.setSingleLine(true);
        this.authorTextView.setTextColor(-7829368);
        this.authorTextView.setTextSize(2, 15.0f);
        this.authorTextView.setTypeface(null, 0);
        addView(this.authorTextView);
    }

    public void setAuthorLabelText(String str) {
        TextView textView = this.authorTextView;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    private void setAuthorLaberColor(int i) {
        TextView textView = this.authorTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    private void loadDateLabel(Context context) {
        this.dateTextView = new TextView(context);
        this.dateTextView.setId(12290);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(iApplyDimension, 0, iApplyDimension, 0);
        this.dateTextView.setLayoutParams(layoutParams);
        this.dateTextView.setShadowLayer(1.0f, 0.0f, 1.0f, -1);
        this.dateTextView.setSingleLine(true);
        this.dateTextView.setTextColor(-7829368);
        this.dateTextView.setTextSize(2, 15.0f);
        this.dateTextView.setTypeface(null, 2);
        addView(this.dateTextView);
    }

    public void setDateLabelText(String str) {
        TextView textView = this.dateTextView;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    private void setDateLaberColor(int i) {
        TextView textView = this.dateTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    private void loadMessageLabel(Context context) {
        this.messageTextView = new TextView(context);
        this.messageTextView.setId(12291);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(iApplyDimension, 0, iApplyDimension, iApplyDimension);
        this.messageTextView.setLayoutParams(layoutParams);
        this.messageTextView.setShadowLayer(1.0f, 0.0f, 1.0f, -1);
        this.messageTextView.setSingleLine(false);
        this.messageTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.messageTextView.setTextSize(2, 18.0f);
        this.messageTextView.setTypeface(null, 0);
        addView(this.messageTextView);
    }

    public void setMessageLabelText(String str) {
        TextView textView = this.messageTextView;
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    private void setMessageLaberColor(int i) {
        TextView textView = this.messageTextView;
        if (textView != null) {
            textView.setTextColor(i);
        }
    }

    private void loadAttachmentList(Context context) {
        this.attachmentListView = new AttachmentListView(context);
        this.attachmentListView.setId(12292);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(iApplyDimension, 0, iApplyDimension, iApplyDimension);
        this.attachmentListView.setLayoutParams(layoutParams);
        addView(this.attachmentListView);
    }

    public void setFeedbackMessageViewBgAndTextColor(int i) {
        if (i == 0) {
            setBackgroundColor(-3355444);
            setAuthorLaberColor(-1);
            setDateLaberColor(-1);
        } else if (i == 1) {
            setBackgroundColor(-1);
            setAuthorLaberColor(-3355444);
            setDateLaberColor(-3355444);
        }
        setMessageLaberColor(ViewCompat.MEASURED_STATE_MASK);
    }
}
