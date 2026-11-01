package net.hockeyapp.android;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import net.hockeyapp.android.adapters.MessagesAdapter;
import net.hockeyapp.android.objects.ErrorObject;
import net.hockeyapp.android.objects.FeedbackMessage;
import net.hockeyapp.android.objects.FeedbackResponse;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import net.hockeyapp.android.tasks.ParseFeedbackTask;
import net.hockeyapp.android.tasks.SendFeedbackTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.utils.PrefsUtil;
import net.hockeyapp.android.utils.Util;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.views.AttachmentView;
import net.hockeyapp.android.views.FeedbackView;

/* JADX INFO: loaded from: classes2.dex */
public class FeedbackActivity extends Activity implements FeedbackActivityInterface, View.OnClickListener {
    private static final int MAX_ATTACHMENTS_PER_MSG = 3;
    private Button addAttachmentButton;
    private Button addResponseButton;
    private Context context;
    private EditText emailInput;
    private ErrorObject error;
    private Handler feedbackHandler;
    private ArrayList<FeedbackMessage> feedbackMessages;
    private ScrollView feedbackScrollView;
    private boolean feedbackViewInitialized;
    private boolean inSendFeedback;
    private List<Uri> initialAttachments;
    private TextView lastUpdatedTextView;
    private MessagesAdapter messagesAdapter;
    private ListView messagesListView;
    private EditText nameInput;
    private Handler parseFeedbackHandler;
    private ParseFeedbackTask parseFeedbackTask;
    private Button refreshButton;
    private Button sendFeedbackButton;
    private SendFeedbackTask sendFeedbackTask;
    private EditText subjectInput;
    private EditText textInput;
    private String token;
    private String url;
    private LinearLayout wrapperLayoutFeedbackAndMessages;
    private final int DIALOG_ERROR_ID = 0;
    private final int ATTACH_PICTURE = 1;
    private final int ATTACH_FILE = 2;
    private final int PAINT_IMAGE = 3;

    protected void onSendFeedbackResult(boolean z) {
    }

    public void enableDisableSendFeedbackButton(boolean z) {
        Button button = this.sendFeedbackButton;
        if (button != null) {
            button.setEnabled(z);
        }
    }

    @Override // net.hockeyapp.android.FeedbackActivityInterface
    public ViewGroup getLayoutView() {
        return new FeedbackView(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == 8201) {
            sendFeedback();
        }
        if (id == 8208) {
            if (((ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS)).getChildCount() >= 3) {
                Toast.makeText(this, String.format("", 3), 1000).show();
                return;
            } else {
                openContextMenu(view);
                return;
            }
        }
        switch (id) {
            case FeedbackView.ADD_RESPONSE_BUTTON_ID /* 131088 */:
                configureFeedbackView(false);
                this.inSendFeedback = true;
                break;
            case FeedbackView.REFRESH_BUTTON_ID /* 131089 */:
                sendFetchFeedback(this.url, null, null, null, null, null, PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this.context), this.feedbackHandler, true);
                break;
        }
    }

    @Override // android.app.Activity
    public boolean onContextItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1 || itemId == 2) {
            return addAttachment(menuItem.getItemId());
        }
        return super.onContextItemSelected(menuItem);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutView());
        setTitle(Strings.get(Strings.FEEDBACK_TITLE_ID));
        this.context = this;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.url = extras.getString("url");
            Parcelable[] parcelableArray = extras.getParcelableArray("initialAttachments");
            if (parcelableArray != null) {
                this.initialAttachments = new ArrayList();
                for (Parcelable parcelable : parcelableArray) {
                    this.initialAttachments.add((Uri) parcelable);
                }
            }
        }
        if (bundle != null) {
            this.feedbackViewInitialized = bundle.getBoolean("feedbackViewInitialized");
            this.inSendFeedback = bundle.getBoolean("inSendFeedback");
        } else {
            this.inSendFeedback = false;
            this.feedbackViewInitialized = false;
        }
        ((NotificationManager) getSystemService("notification")).cancel(2);
        initFeedbackHandler();
        initParseFeedbackHandler();
        configureAppropriateView();
    }

    @Override // android.app.Activity, android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        contextMenu.add(0, 2, 0, Strings.get(Strings.FEEDBACK_ATTACH_FILE_ID));
        contextMenu.add(0, 1, 0, Strings.get(Strings.FEEDBACK_ATTACH_PICTURE_ID));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.inSendFeedback) {
                this.inSendFeedback = false;
                configureAppropriateView();
                return true;
            }
            finish();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        SendFeedbackTask sendFeedbackTask = this.sendFeedbackTask;
        if (sendFeedbackTask != null) {
            sendFeedbackTask.detach();
        }
        return this.sendFeedbackTask;
    }

    protected void configureFeedbackView(boolean z) {
        this.feedbackScrollView = (ScrollView) findViewById(FeedbackView.FEEDBACK_SCROLLVIEW_ID);
        this.wrapperLayoutFeedbackAndMessages = (LinearLayout) findViewById(FeedbackView.WRAPPER_LAYOUT_FEEDBACK_AND_MESSAGES_ID);
        this.messagesListView = (ListView) findViewById(FeedbackView.MESSAGES_LISTVIEW_ID);
        if (z) {
            this.wrapperLayoutFeedbackAndMessages.setVisibility(0);
            this.feedbackScrollView.setVisibility(8);
            this.lastUpdatedTextView = (TextView) findViewById(8192);
            this.addResponseButton = (Button) findViewById(FeedbackView.ADD_RESPONSE_BUTTON_ID);
            this.addResponseButton.setOnClickListener(this);
            this.refreshButton = (Button) findViewById(FeedbackView.REFRESH_BUTTON_ID);
            this.refreshButton.setOnClickListener(this);
            return;
        }
        this.wrapperLayoutFeedbackAndMessages.setVisibility(8);
        this.feedbackScrollView.setVisibility(0);
        this.nameInput = (EditText) findViewById(8194);
        this.emailInput = (EditText) findViewById(FeedbackView.EMAIL_EDIT_TEXT_ID);
        this.subjectInput = (EditText) findViewById(FeedbackView.SUBJECT_EDIT_TEXT_ID);
        this.textInput = (EditText) findViewById(FeedbackView.TEXT_EDIT_TEXT_ID);
        if (!this.feedbackViewInitialized) {
            String nameEmailFromPrefs = PrefsUtil.getInstance().getNameEmailFromPrefs(this.context);
            if (nameEmailFromPrefs != null) {
                String[] strArrSplit = nameEmailFromPrefs.split("\\|");
                if (strArrSplit != null && strArrSplit.length >= 2) {
                    this.nameInput.setText(strArrSplit[0]);
                    this.emailInput.setText(strArrSplit[1]);
                    if (strArrSplit.length >= 3) {
                        this.subjectInput.setText(strArrSplit[2]);
                        this.textInput.requestFocus();
                    } else {
                        this.subjectInput.requestFocus();
                    }
                }
            } else {
                this.nameInput.setText("");
                this.emailInput.setText("");
                this.subjectInput.setText("");
                this.nameInput.requestFocus();
            }
            this.feedbackViewInitialized = true;
        }
        this.textInput.setText("");
        if (PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this.context) != null) {
            this.subjectInput.setVisibility(8);
        } else {
            this.subjectInput.setVisibility(0);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
        viewGroup.removeAllViews();
        List<Uri> list = this.initialAttachments;
        if (list != null) {
            Iterator<Uri> it = list.iterator();
            while (it.hasNext()) {
                viewGroup.addView(new AttachmentView((Context) this, viewGroup, it.next(), true));
            }
        }
        this.addAttachmentButton = (Button) findViewById(FeedbackView.ADD_ATTACHMENT_BUTTON_ID);
        this.addAttachmentButton.setOnClickListener(this);
        registerForContextMenu(this.addAttachmentButton);
        this.sendFeedbackButton = (Button) findViewById(FeedbackView.SEND_FEEDBACK_BUTTON_ID);
        this.sendFeedbackButton.setOnClickListener(this);
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        Uri uri;
        if (i2 != -1) {
            return;
        }
        if (i == 2) {
            Uri data = intent.getData();
            if (data != null) {
                ViewGroup viewGroup = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
                viewGroup.addView(new AttachmentView((Context) this, viewGroup, data, true));
                return;
            }
            return;
        }
        if (i != 1) {
            if (i != 3 || (uri = (Uri) intent.getParcelableExtra("imageUri")) == null) {
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
            viewGroup2.addView(new AttachmentView((Context) this, viewGroup2, uri, true));
            return;
        }
        Uri data2 = intent.getData();
        if (data2 != null) {
            try {
                Intent intent2 = new Intent(this, (Class<?>) PaintActivity.class);
                intent2.putExtra("imageUri", data2);
                startActivityForResult(intent2, 3);
            } catch (ActivityNotFoundException e) {
                Log.e("HockeyApp", "Paint activity not declared!", e);
            }
        }
    }

    @Override // android.app.Activity
    protected Dialog onCreateDialog(int i) {
        if (i != 0) {
            return null;
        }
        return new AlertDialog.Builder(this).setMessage(Strings.get(Strings.DIALOG_ERROR_MESSAGE_ID)).setCancelable(false).setTitle(Strings.get(Strings.DIALOG_ERROR_TITLE_ID)).setIcon(R.drawable.ic_dialog_alert).setPositiveButton(Strings.get(2048), new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.FeedbackActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                FeedbackActivity.this.error = null;
                dialogInterface.cancel();
            }
        }).create();
    }

    @Override // android.app.Activity
    protected void onPrepareDialog(int i, Dialog dialog) {
        if (i != 0) {
            return;
        }
        AlertDialog alertDialog = (AlertDialog) dialog;
        ErrorObject errorObject = this.error;
        if (errorObject != null) {
            alertDialog.setMessage(errorObject.getMessage());
        } else {
            alertDialog.setMessage(Strings.get(Strings.FEEDBACK_GENERIC_ERROR_ID));
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            ViewGroup viewGroup = (ViewGroup) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS);
            Iterator it = bundle.getParcelableArrayList("attachments").iterator();
            while (it.hasNext()) {
                viewGroup.addView(new AttachmentView((Context) this, viewGroup, (Uri) it.next(), true));
            }
            this.feedbackViewInitialized = bundle.getBoolean("feedbackViewInitialized");
        }
        super.onRestoreInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelableArrayList("attachments", ((AttachmentListView) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS)).getAttachments());
        bundle.putBoolean("feedbackViewInitialized", this.feedbackViewInitialized);
        bundle.putBoolean("inSendFeedback", this.inSendFeedback);
        super.onSaveInstanceState(bundle);
    }

    private boolean addAttachment(int i) {
        if (i == 2) {
            Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            startActivityForResult(Intent.createChooser(intent, Strings.get(Strings.FEEDBACK_SELECT_FILE_ID)), 2);
            return true;
        }
        if (i != 1) {
            return false;
        }
        Intent intent2 = new Intent();
        intent2.setType("image/*");
        intent2.setAction("android.intent.action.GET_CONTENT");
        startActivityForResult(Intent.createChooser(intent2, Strings.get(Strings.FEEDBACK_SELECT_PICTURE_ID)), 1);
        return true;
    }

    private void configureAppropriateView() {
        this.token = PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this);
        if (this.token == null || this.inSendFeedback) {
            configureFeedbackView(false);
        } else {
            configureFeedbackView(true);
            sendFetchFeedback(this.url, null, null, null, null, null, this.token, this.feedbackHandler, true);
        }
    }

    private void createParseFeedbackTask(String str, String str2) {
        this.parseFeedbackTask = new ParseFeedbackTask(this, str, this.parseFeedbackHandler, str2);
    }

    private void hideKeyboard() {
        if (this.textInput != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.textInput.getWindowToken(), 0);
        }
    }

    private void initFeedbackHandler() {
        this.feedbackHandler = new Handler() { // from class: net.hockeyapp.android.FeedbackActivity.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                FeedbackActivity.this.error = new ErrorObject();
                boolean z = false;
                if (message == null || message.getData() == null) {
                    FeedbackActivity.this.error.setMessage(Strings.get(Strings.FEEDBACK_SEND_GENERIC_ERROR_ID));
                } else {
                    Bundle data = message.getData();
                    String string = data.getString("feedback_response");
                    String string2 = data.getString("feedback_status");
                    String string3 = data.getString("request_type");
                    if (string3.equals("send") && (string == null || Integer.parseInt(string2) != 201)) {
                        FeedbackActivity.this.error.setMessage(Strings.get(Strings.FEEDBACK_SEND_GENERIC_ERROR_ID));
                    } else {
                        if (string3.equals("fetch") && string2 != null && (Integer.parseInt(string2) == 404 || Integer.parseInt(string2) == 422)) {
                            FeedbackActivity.this.resetFeedbackView();
                        } else if (string != null) {
                            FeedbackActivity.this.startParseFeedbackTask(string, string3);
                        } else {
                            FeedbackActivity.this.error.setMessage(Strings.get(Strings.FEEDBACK_SEND_NETWORK_ERROR_ID));
                        }
                        z = true;
                    }
                }
                if (!z) {
                    FeedbackActivity.this.runOnUiThread(new Runnable() { // from class: net.hockeyapp.android.FeedbackActivity.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            FeedbackActivity.this.enableDisableSendFeedbackButton(true);
                            FeedbackActivity.this.showDialog(0);
                        }
                    });
                }
                FeedbackActivity.this.onSendFeedbackResult(z);
            }
        };
    }

    private void initParseFeedbackHandler() {
        this.parseFeedbackHandler = new Handler() { // from class: net.hockeyapp.android.FeedbackActivity.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                FeedbackResponse feedbackResponse;
                FeedbackActivity.this.error = new ErrorObject();
                boolean z = false;
                if (message != null && message.getData() != null && (feedbackResponse = (FeedbackResponse) message.getData().getSerializable("parse_feedback_response")) != null && feedbackResponse.getStatus().equalsIgnoreCase("success")) {
                    if (feedbackResponse.getToken() != null) {
                        PrefsUtil.getInstance().saveFeedbackTokenToPrefs(FeedbackActivity.this.context, feedbackResponse.getToken());
                        FeedbackActivity.this.loadFeedbackMessages(feedbackResponse);
                        FeedbackActivity.this.inSendFeedback = false;
                    }
                    z = true;
                }
                if (!z) {
                    FeedbackActivity.this.runOnUiThread(new Runnable() { // from class: net.hockeyapp.android.FeedbackActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            FeedbackActivity.this.showDialog(0);
                        }
                    });
                }
                FeedbackActivity.this.enableDisableSendFeedbackButton(true);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFeedbackMessages(final FeedbackResponse feedbackResponse) {
        runOnUiThread(new Runnable() { // from class: net.hockeyapp.android.FeedbackActivity.4
            @Override // java.lang.Runnable
            public void run() {
                FeedbackActivity.this.configureFeedbackView(true);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM h:mm a");
                FeedbackResponse feedbackResponse2 = feedbackResponse;
                if (feedbackResponse2 == null || feedbackResponse2.getFeedback() == null || feedbackResponse.getFeedback().getMessages() == null || feedbackResponse.getFeedback().getMessages().size() <= 0) {
                    return;
                }
                FeedbackActivity.this.feedbackMessages = feedbackResponse.getFeedback().getMessages();
                Collections.reverse(FeedbackActivity.this.feedbackMessages);
                try {
                    Date date = simpleDateFormat.parse(((FeedbackMessage) FeedbackActivity.this.feedbackMessages.get(0)).getCreatedAt());
                    FeedbackActivity.this.lastUpdatedTextView.setText(String.format(Strings.get(1030) + " %s", simpleDateFormat2.format(date)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (FeedbackActivity.this.messagesAdapter != null) {
                    FeedbackActivity.this.messagesAdapter.clear();
                    Iterator it = FeedbackActivity.this.feedbackMessages.iterator();
                    while (it.hasNext()) {
                        FeedbackActivity.this.messagesAdapter.add((FeedbackMessage) it.next());
                    }
                    FeedbackActivity.this.messagesAdapter.notifyDataSetChanged();
                } else {
                    FeedbackActivity feedbackActivity = FeedbackActivity.this;
                    feedbackActivity.messagesAdapter = new MessagesAdapter(feedbackActivity.context, FeedbackActivity.this.feedbackMessages);
                }
                FeedbackActivity.this.messagesListView.setAdapter((ListAdapter) FeedbackActivity.this.messagesAdapter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetFeedbackView() {
        runOnUiThread(new Runnable() { // from class: net.hockeyapp.android.FeedbackActivity.5
            @Override // java.lang.Runnable
            public void run() {
                PrefsUtil.getInstance().saveFeedbackTokenToPrefs(FeedbackActivity.this, null);
                PrefsUtil.applyChanges(FeedbackActivity.this.getSharedPreferences(ParseFeedbackTask.PREFERENCES_NAME, 0).edit().remove(ParseFeedbackTask.ID_LAST_MESSAGE_SEND).remove(ParseFeedbackTask.ID_LAST_MESSAGE_PROCESSED));
                FeedbackActivity.this.configureFeedbackView(false);
            }
        });
    }

    private void sendFeedback() {
        enableDisableSendFeedbackButton(false);
        hideKeyboard();
        String feedbackTokenFromPrefs = PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this.context);
        String strTrim = this.nameInput.getText().toString().trim();
        String strTrim2 = this.emailInput.getText().toString().trim();
        String strTrim3 = this.subjectInput.getText().toString().trim();
        String strTrim4 = this.textInput.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim3)) {
            this.subjectInput.setVisibility(0);
            setError(this.subjectInput, Strings.FEEDBACK_VALIDATE_SUBJECT_ERROR_ID);
            return;
        }
        if (FeedbackManager.getRequireUserName() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty(strTrim)) {
            setError(this.nameInput, Strings.FEEDBACK_VALIDATE_NAME_ERROR_ID);
            return;
        }
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty(strTrim2)) {
            setError(this.emailInput, Strings.FEEDBACK_VALIDATE_EMAIL_EMPTY_ID);
            return;
        }
        if (TextUtils.isEmpty(strTrim4)) {
            setError(this.textInput, Strings.FEEDBACK_VALIDATE_TEXT_ERROR_ID);
            return;
        }
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.REQUIRED && !Util.isValidEmail(strTrim2)) {
            setError(this.emailInput, Strings.FEEDBACK_VALIDATE_EMAIL_ERROR_ID);
            return;
        }
        PrefsUtil.getInstance().saveNameEmailSubjectToPrefs(this.context, strTrim, strTrim2, strTrim3);
        sendFetchFeedback(this.url, strTrim, strTrim2, strTrim3, strTrim4, ((AttachmentListView) findViewById(FeedbackView.WRAPPER_LAYOUT_ATTACHMENTS)).getAttachments(), feedbackTokenFromPrefs, this.feedbackHandler, false);
    }

    private void setError(EditText editText, int i) {
        editText.setError(Strings.get(i));
        enableDisableSendFeedbackButton(true);
    }

    private void sendFetchFeedback(String str, String str2, String str3, String str4, String str5, List<Uri> list, String str6, Handler handler, boolean z) {
        this.sendFeedbackTask = new SendFeedbackTask(this.context, str, str2, str3, str4, str5, list, str6, handler, z);
        AsyncTaskUtils.execute(this.sendFeedbackTask);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startParseFeedbackTask(String str, String str2) {
        createParseFeedbackTask(str, str2);
        AsyncTaskUtils.execute(this.parseFeedbackTask);
    }
}
