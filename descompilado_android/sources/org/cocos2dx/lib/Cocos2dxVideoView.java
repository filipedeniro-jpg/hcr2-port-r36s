package org.cocos2dx.lib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import com.appsflyer.share.Constants;
import com.chukong.cocosplay.client.CocosPlayClient;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class Cocos2dxVideoView extends SurfaceView implements MediaController.MediaPlayerControl {
    private static final String AssetResourceRoot = "assets/";
    private static final int EVENT_COMPLETED = 3;
    private static final int EVENT_PAUSED = 1;
    private static final int EVENT_PLAYING = 0;
    private static final int EVENT_STOPPED = 2;
    private static final int STATE_ERROR = -1;
    private static final int STATE_IDLE = 0;
    private static final int STATE_PAUSED = 4;
    private static final int STATE_PLAYBACK_COMPLETED = 5;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_PREPARED = 2;
    private static final int STATE_PREPARING = 1;
    private String TAG;
    private MediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    protected Cocos2dxActivity mCocos2dxActivity;
    private MediaPlayer.OnCompletionListener mCompletionListener;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private int mDuration;
    private MediaPlayer.OnErrorListener mErrorListener;
    protected boolean mFullScreenEnabled;
    protected int mFullScreenHeight;
    protected int mFullScreenWidth;
    private boolean mIsAssetRouse;
    private boolean mKeepRatio;
    private MediaPlayer mMediaPlayer;
    private boolean mNeedResume;
    private MediaPlayer.OnErrorListener mOnErrorListener;
    private MediaPlayer.OnPreparedListener mOnPreparedListener;
    private OnVideoEventListener mOnVideoEventListener;
    MediaPlayer.OnPreparedListener mPreparedListener;
    SurfaceHolder.Callback mSHCallback;
    private int mSeekWhenPrepared;
    protected MediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private SurfaceHolder mSurfaceHolder;
    private int mTargetState;
    private String mVideoFilePath;
    private int mVideoHeight;
    private Uri mVideoUri;
    private int mVideoWidth;
    protected int mViewHeight;
    protected int mViewLeft;
    private int mViewTag;
    protected int mViewTop;
    protected int mViewWidth;
    protected int mVisibleHeight;
    protected int mVisibleLeft;
    protected int mVisibleTop;
    protected int mVisibleWidth;

    public interface OnVideoEventListener {
        void onVideoEvent(int i, int i2);
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return true;
    }

    public Cocos2dxVideoView(Cocos2dxActivity cocos2dxActivity, int i) {
        super(cocos2dxActivity);
        this.TAG = "hcr2_cocos - Cocos2dxVideoView";
        this.mCurrentState = 0;
        this.mTargetState = 0;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        this.mCocos2dxActivity = null;
        this.mViewLeft = 0;
        this.mViewTop = 0;
        this.mViewWidth = 0;
        this.mViewHeight = 0;
        this.mVisibleLeft = 0;
        this.mVisibleTop = 0;
        this.mVisibleWidth = 0;
        this.mVisibleHeight = 0;
        this.mFullScreenEnabled = false;
        this.mFullScreenWidth = 0;
        this.mFullScreenHeight = 0;
        this.mViewTag = 0;
        this.mNeedResume = false;
        this.mIsAssetRouse = false;
        this.mVideoFilePath = null;
        this.mKeepRatio = false;
        this.mSizeChangedListener = new MediaPlayer.OnVideoSizeChangedListener() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                Cocos2dxVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                Cocos2dxVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                if (Cocos2dxVideoView.this.mVideoWidth == 0 || Cocos2dxVideoView.this.mVideoHeight == 0) {
                    return;
                }
                Cocos2dxVideoView.this.getHolder().setFixedSize(Cocos2dxVideoView.this.mVideoWidth, Cocos2dxVideoView.this.mVideoHeight);
            }
        };
        this.mPreparedListener = new MediaPlayer.OnPreparedListener() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                Cocos2dxVideoView.this.mCurrentState = 2;
                if (Cocos2dxVideoView.this.mOnPreparedListener != null) {
                    Cocos2dxVideoView.this.mOnPreparedListener.onPrepared(Cocos2dxVideoView.this.mMediaPlayer);
                }
                Cocos2dxVideoView.this.mVideoWidth = mediaPlayer.getVideoWidth();
                Cocos2dxVideoView.this.mVideoHeight = mediaPlayer.getVideoHeight();
                int i2 = Cocos2dxVideoView.this.mSeekWhenPrepared;
                if (i2 != 0) {
                    Cocos2dxVideoView.this.seekTo(i2);
                }
                if (Cocos2dxVideoView.this.mVideoWidth != 0 && Cocos2dxVideoView.this.mVideoHeight != 0) {
                    Cocos2dxVideoView.this.fixSize();
                }
                if (Cocos2dxVideoView.this.mTargetState == 3) {
                    Cocos2dxVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                Cocos2dxVideoView.this.mCurrentState = 5;
                Cocos2dxVideoView.this.mTargetState = 5;
                Cocos2dxVideoView.this.release(true);
                if (Cocos2dxVideoView.this.mOnVideoEventListener != null) {
                    Cocos2dxVideoView.this.mOnVideoEventListener.onVideoEvent(Cocos2dxVideoView.this.mViewTag, 3);
                }
            }
        };
        this.mErrorListener = new MediaPlayer.OnErrorListener() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                int identifier;
                Log.d(Cocos2dxVideoView.this.TAG, "Error: " + i2 + "," + i3);
                Cocos2dxVideoView.this.mCurrentState = -1;
                Cocos2dxVideoView.this.mTargetState = -1;
                if ((Cocos2dxVideoView.this.mOnErrorListener == null || !Cocos2dxVideoView.this.mOnErrorListener.onError(Cocos2dxVideoView.this.mMediaPlayer, i2, i3)) && Cocos2dxVideoView.this.getWindowToken() != null) {
                    Resources resources = Cocos2dxVideoView.this.mCocos2dxActivity.getResources();
                    if (i2 == 200) {
                        identifier = resources.getIdentifier("VideoView_error_text_invalid_progressive_playback", "string", "android");
                    } else {
                        identifier = resources.getIdentifier("VideoView_error_text_unknown", "string", "android");
                    }
                    new AlertDialog.Builder(Cocos2dxVideoView.this.mCocos2dxActivity).setTitle(resources.getString(resources.getIdentifier("VideoView_error_title", "string", "android"))).setMessage(identifier).setPositiveButton(resources.getString(resources.getIdentifier("VideoView_error_button", "string", "android")), new DialogInterface.OnClickListener() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.4.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i4) {
                            if (Cocos2dxVideoView.this.mOnVideoEventListener != null) {
                                Cocos2dxVideoView.this.mOnVideoEventListener.onVideoEvent(Cocos2dxVideoView.this.mViewTag, 3);
                            }
                        }
                    }).setCancelable(false).show();
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new MediaPlayer.OnBufferingUpdateListener() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.5
            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                Cocos2dxVideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.mSHCallback = new SurfaceHolder.Callback() { // from class: org.cocos2dx.lib.Cocos2dxVideoView.6
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
                boolean z = Cocos2dxVideoView.this.mTargetState == 3;
                boolean z2 = Cocos2dxVideoView.this.mVideoWidth == i3 && Cocos2dxVideoView.this.mVideoHeight == i4;
                if (Cocos2dxVideoView.this.mMediaPlayer != null && z && z2) {
                    if (Cocos2dxVideoView.this.mSeekWhenPrepared != 0) {
                        Cocos2dxVideoView cocos2dxVideoView = Cocos2dxVideoView.this;
                        cocos2dxVideoView.seekTo(cocos2dxVideoView.mSeekWhenPrepared);
                    }
                    Cocos2dxVideoView.this.start();
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                Cocos2dxVideoView.this.mSurfaceHolder = surfaceHolder;
                Cocos2dxVideoView.this.openVideo();
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                Cocos2dxVideoView.this.mSurfaceHolder = null;
                Cocos2dxVideoView.this.release(true);
            }
        };
        this.mViewTag = i;
        this.mCocos2dxActivity = cocos2dxActivity;
        initVideoView();
    }

    @Override // android.view.SurfaceView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            setMeasuredDimension(this.mViewWidth, this.mViewHeight);
            Log.i(this.TAG, "" + this.mViewWidth + ":" + this.mViewHeight);
            return;
        }
        setMeasuredDimension(this.mVisibleWidth, this.mVisibleHeight);
        Log.i(this.TAG, "" + this.mVisibleWidth + ":" + this.mVisibleHeight);
    }

    public void setVideoRect(int i, int i2, int i3, int i4) {
        this.mViewLeft = i;
        this.mViewTop = i2;
        this.mViewWidth = i3;
        this.mViewHeight = i4;
        fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
    }

    public void setFullScreenEnabled(boolean z, int i, int i2) {
        if (this.mFullScreenEnabled != z) {
            this.mFullScreenEnabled = z;
            if (i != 0 && i2 != 0) {
                this.mFullScreenWidth = i;
                this.mFullScreenHeight = i2;
            }
            fixSize();
        }
    }

    public int resolveAdjustedSize(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE) {
            return (mode == 0 || mode != 1073741824) ? i : size;
        }
        return Math.min(i, size);
    }

    @Override // android.view.SurfaceView, android.view.View
    public void setVisibility(int i) {
        if (i == 4) {
            this.mNeedResume = isPlaying();
            if (this.mNeedResume) {
                this.mSeekWhenPrepared = getCurrentPosition();
            }
        } else if (this.mNeedResume) {
            start();
            this.mNeedResume = false;
        }
        super.setVisibility(i);
    }

    private void initVideoView() {
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        getHolder().addCallback(this.mSHCallback);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.mCurrentState = 0;
        this.mTargetState = 0;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1) {
            if (isPlaying()) {
                pause();
            } else if (this.mCurrentState == 4) {
                resume();
            }
        }
        return true;
    }

    public void setVideoFileName(String str) {
        if (str.startsWith(AssetResourceRoot)) {
            str = str.substring(7);
        }
        if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
            CocosPlayClient.updateAssets(str);
        }
        CocosPlayClient.notifyFileLoaded(str);
        if (str.startsWith(Constants.URL_PATH_DELIMITER)) {
            this.mIsAssetRouse = false;
            setVideoURI(Uri.parse(str), null);
        } else {
            this.mVideoFilePath = str;
            this.mIsAssetRouse = true;
            setVideoURI(Uri.parse(str), null);
        }
    }

    public void setVideoURL(String str) {
        this.mIsAssetRouse = false;
        setVideoURI(Uri.parse(str), null);
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mVideoUri = uri;
        this.mSeekWhenPrepared = 0;
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        openVideo();
        requestLayout();
        invalidate();
    }

    public void stopPlayback() {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            this.mTargetState = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openVideo() {
        if (this.mSurfaceHolder == null) {
            return;
        }
        if (this.mIsAssetRouse) {
            if (this.mVideoFilePath == null) {
                return;
            }
        } else if (this.mVideoUri == null) {
            return;
        }
        Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        this.mCocos2dxActivity.sendBroadcast(intent);
        release(false);
        try {
            this.mMediaPlayer = new MediaPlayer();
            this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
            this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
            this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
            this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
            this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
            this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
            this.mMediaPlayer.setAudioStreamType(3);
            this.mMediaPlayer.setScreenOnWhilePlaying(true);
            this.mDuration = -1;
            this.mCurrentBufferPercentage = 0;
            if (this.mIsAssetRouse) {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.mCocos2dxActivity.getAssets().openFd(this.mVideoFilePath);
                this.mMediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            } else {
                this.mMediaPlayer.setDataSource(this.mCocos2dxActivity, this.mVideoUri);
            }
            this.mMediaPlayer.prepareAsync();
            this.mCurrentState = 1;
        } catch (IOException e) {
            Log.w(this.TAG, "Unable to open content: " + this.mVideoUri, e);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        } catch (IllegalArgumentException e2) {
            Log.w(this.TAG, "Unable to open content: " + this.mVideoUri, e2);
            this.mCurrentState = -1;
            this.mTargetState = -1;
            this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
        }
    }

    public void setKeepRatio(boolean z) {
        this.mKeepRatio = z;
        fixSize();
    }

    public void fixSize() {
        if (this.mFullScreenEnabled) {
            fixSize(0, 0, this.mFullScreenWidth, this.mFullScreenHeight);
        } else {
            fixSize(this.mViewLeft, this.mViewTop, this.mViewWidth, this.mViewHeight);
        }
    }

    public void fixSize(int i, int i2, int i3, int i4) {
        int i5;
        int i6 = this.mVideoWidth;
        if (i6 == 0 || (i5 = this.mVideoHeight) == 0) {
            this.mVisibleLeft = i;
            this.mVisibleTop = i2;
            this.mVisibleWidth = i3;
            this.mVisibleHeight = i4;
        } else if (i3 != 0 && i4 != 0) {
            if (this.mKeepRatio) {
                if (i6 * i4 > i3 * i5) {
                    this.mVisibleWidth = i3;
                    this.mVisibleHeight = (i5 * i3) / i6;
                } else if (i6 * i4 < i3 * i5) {
                    this.mVisibleWidth = (i6 * i4) / i5;
                    this.mVisibleHeight = i4;
                }
                this.mVisibleLeft = i + ((i3 - this.mVisibleWidth) / 2);
                this.mVisibleTop = i2 + ((i4 - this.mVisibleHeight) / 2);
            } else {
                this.mVisibleLeft = i;
                this.mVisibleTop = i2;
                this.mVisibleWidth = i3;
                this.mVisibleHeight = i4;
            }
        } else {
            this.mVisibleLeft = i;
            this.mVisibleTop = i2;
            this.mVisibleWidth = this.mVideoWidth;
            this.mVisibleHeight = this.mVideoHeight;
        }
        getHolder().setFixedSize(this.mVisibleWidth, this.mVisibleHeight);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = this.mVisibleLeft;
        layoutParams.topMargin = this.mVisibleTop;
        layoutParams.gravity = 51;
        setLayoutParams(layoutParams);
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(OnVideoEventListener onVideoEventListener) {
        this.mOnVideoEventListener = onVideoEventListener;
    }

    public void setOnErrorListener(MediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release(boolean z) {
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = 0;
            if (z) {
                this.mTargetState = 0;
            }
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
        this.mTargetState = 3;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = 4;
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 1);
            }
        }
        this.mTargetState = 4;
    }

    public void stop() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            stopPlayback();
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 2);
            }
        }
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        if (isInPlaybackState() && this.mCurrentState == 4) {
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            OnVideoEventListener onVideoEventListener = this.mOnVideoEventListener;
            if (onVideoEventListener != null) {
                onVideoEventListener.onVideoEvent(this.mViewTag, 0);
            }
        }
    }

    public void restart() {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(0);
            this.mMediaPlayer.start();
            this.mCurrentState = 3;
            this.mTargetState = 3;
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            int i = this.mDuration;
            if (i > 0) {
                return i;
            }
            this.mDuration = this.mMediaPlayer.getDuration();
            return this.mDuration;
        }
        this.mDuration = -1;
        return this.mDuration;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(i);
            this.mSeekWhenPrepared = 0;
        } else {
            this.mSeekWhenPrepared = i;
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    public boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentState) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return this.mMediaPlayer.getAudioSessionId();
    }
}
