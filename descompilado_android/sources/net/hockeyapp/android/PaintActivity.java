package net.hockeyapp.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.File;
import java.io.FileOutputStream;
import net.hockeyapp.android.views.PaintView;

/* JADX INFO: loaded from: classes2.dex */
public class PaintActivity extends Activity {
    private static final int MENU_CLEAR_ID = 3;
    private static final int MENU_SAVE_ID = 1;
    private static final int MENU_UNDO_ID = 2;
    private String imageName;
    private PaintView paintView;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri uri = (Uri) getIntent().getExtras().getParcelable("imageUri");
        this.imageName = determineFilename(uri, uri.getLastPathSegment());
        int i = getResources().getDisplayMetrics().widthPixels;
        int i2 = getResources().getDisplayMetrics().heightPixels;
        int i3 = i > i2 ? 0 : 1;
        int iDetermineOrientation = PaintView.determineOrientation(getContentResolver(), uri);
        setRequestedOrientation(iDetermineOrientation);
        if (i3 != iDetermineOrientation) {
            Log.d("HockeyApp", "Image loading skipped because activity will be destroyed for orientation change.");
            return;
        }
        this.paintView = new PaintView(this, uri, i, i2);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout.setGravity(17);
        linearLayout.setOrientation(1);
        LinearLayout linearLayout2 = new LinearLayout(this);
        linearLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        linearLayout2.setGravity(17);
        linearLayout2.setOrientation(0);
        linearLayout.addView(linearLayout2);
        linearLayout2.addView(this.paintView);
        setContentView(linearLayout);
        Toast.makeText(this, Strings.get(Strings.PAINT_INDICATOR_TOAST_ID), 1000).show();
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, Strings.get(Strings.PAINT_MENU_SAVE_ID));
        menu.add(0, 2, 0, Strings.get(Strings.PAINT_MENU_UNDO_ID));
        menu.add(0, 3, 0, Strings.get(Strings.PAINT_MENU_CLEAR_ID));
        return true;
    }

    @Override // android.app.Activity
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 1) {
            makeResult();
            return true;
        }
        if (itemId == 2) {
            this.paintView.undo();
            return true;
        }
        if (itemId == 3) {
            this.paintView.clearImage();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && !this.paintView.isClear()) {
            DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: net.hockeyapp.android.PaintActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    if (i2 != -1) {
                        return;
                    }
                    PaintActivity.this.finish();
                }
            };
            new AlertDialog.Builder(this).setMessage(Strings.get(Strings.PAINT_DIALOG_MESSAGE_ID)).setPositiveButton(Strings.get(Strings.PAINT_DIALOG_POSITIVE_BUTTON_ID), onClickListener).setNegativeButton(Strings.get(Strings.PAINT_DIALOG_NEGATIVE_BUTTON_ID), onClickListener).show();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX WARN: Type inference failed for: r2v3, types: [net.hockeyapp.android.PaintActivity$2] */
    private void makeResult() {
        File file = new File(getCacheDir(), "HockeyApp");
        file.mkdir();
        File file2 = new File(file, this.imageName + ".jpg");
        int i = 1;
        while (file2.exists()) {
            file2 = new File(file, this.imageName + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + i + ".jpg");
            i++;
        }
        this.paintView.setDrawingCacheEnabled(true);
        final Bitmap drawingCache = this.paintView.getDrawingCache();
        new AsyncTask<File, Void, Void>() { // from class: net.hockeyapp.android.PaintActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(File... fileArr) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(fileArr[0]);
                    drawingCache.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.close();
                    return null;
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("HockeyApp", "Could not save image.", e);
                    return null;
                }
            }
        }.execute(file2);
        Intent intent = new Intent();
        intent.putExtra("imageUri", Uri.fromFile(file2));
        if (getParent() == null) {
            setResult(-1, intent);
        } else {
            getParent().setResult(-1, intent);
        }
        finish();
    }

    private String determineFilename(Uri uri, String str) {
        Cursor cursorQuery = getApplicationContext().getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
        String string = null;
        if (cursorQuery != null) {
            try {
                string = cursorQuery.moveToFirst() ? cursorQuery.getString(0) : null;
                cursorQuery.close();
            } catch (Throwable th) {
                cursorQuery.close();
                throw th;
            }
        }
        return string == null ? str : new File(string).getName();
    }
}
