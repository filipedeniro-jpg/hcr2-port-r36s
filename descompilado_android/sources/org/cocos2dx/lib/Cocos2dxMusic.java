package org.cocos2dx.lib;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.chukong.cocosplay.client.CocosPlayClient;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes3.dex */
public class Cocos2dxMusic {
    private static final String TAG = Cocos2dxActivity.APP_TAG + Cocos2dxMusic.class.getSimpleName();
    private MediaPlayer mBackgroundMediaPlayer;
    private final Context mContext;
    private String mCurrentPath;
    private float mLeftVolume;
    private boolean mPaused;
    private float mRightVolume;
    private boolean mIsLoop = false;
    private boolean mManualPaused = false;

    public Cocos2dxMusic(Context context) {
        this.mContext = context;
        initData();
    }

    public void preloadBackgroundMusic(String str) {
        String str2 = this.mCurrentPath;
        if (str2 == null || !str2.equals(str)) {
            MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        }
    }

    public void playBackgroundMusic(String str, boolean z) {
        String str2 = this.mCurrentPath;
        if (str2 == null) {
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        } else if (!str2.equals(str)) {
            MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            this.mBackgroundMediaPlayer = createMediaplayer(str);
            this.mCurrentPath = str;
        }
        MediaPlayer mediaPlayer2 = this.mBackgroundMediaPlayer;
        if (mediaPlayer2 == null) {
            Log.e(TAG, "playBackgroundMusic: background media player is null");
            return;
        }
        try {
            if (this.mPaused) {
                mediaPlayer2.seekTo(0);
                this.mBackgroundMediaPlayer.start();
            } else if (mediaPlayer2.isPlaying()) {
                this.mBackgroundMediaPlayer.seekTo(0);
            } else {
                this.mBackgroundMediaPlayer.start();
            }
            this.mBackgroundMediaPlayer.setLooping(z);
            this.mPaused = false;
            this.mIsLoop = z;
        } catch (Exception unused) {
            Log.e(TAG, "playBackgroundMusic: error state");
        }
    }

    public void stopBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mBackgroundMediaPlayer = createMediaplayer(this.mCurrentPath);
            this.mPaused = false;
        }
    }

    public void pauseBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mBackgroundMediaPlayer.pause();
        this.mPaused = true;
        this.mManualPaused = true;
    }

    public void resumeBackgroundMusic() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null || !this.mPaused) {
            return;
        }
        mediaPlayer.start();
        this.mPaused = false;
        this.mManualPaused = false;
    }

    public void rewindBackgroundMusic() {
        if (this.mBackgroundMediaPlayer != null) {
            playBackgroundMusic(this.mCurrentPath, this.mIsLoop);
        }
    }

    public boolean isBackgroundMusicPlaying() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.isPlaying();
    }

    public void end() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        initData();
    }

    public float getBackgroundVolume() {
        if (this.mBackgroundMediaPlayer != null) {
            return (this.mLeftVolume + this.mRightVolume) / 2.0f;
        }
        return 0.0f;
    }

    public void setBackgroundVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
        }
    }

    public int getBackgroundMusicPosition() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void setBackgroundMusicPosition(int i) {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(i);
        }
    }

    public void onEnterBackground() {
        MediaPlayer mediaPlayer = this.mBackgroundMediaPlayer;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.mBackgroundMediaPlayer.pause();
        this.mPaused = true;
    }

    public void onEnterForeground() {
        MediaPlayer mediaPlayer;
        if (this.mManualPaused || (mediaPlayer = this.mBackgroundMediaPlayer) == null || !this.mPaused) {
            return;
        }
        mediaPlayer.start();
        this.mPaused = false;
    }

    private void initData() {
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        this.mBackgroundMediaPlayer = null;
        this.mPaused = false;
        this.mCurrentPath = null;
    }

    private MediaPlayer createMediaplayer(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
                CocosPlayClient.updateAssets(str);
            }
            CocosPlayClient.notifyFileLoaded(str);
            if (str.startsWith(Constants.URL_PATH_DELIMITER)) {
                FileInputStream fileInputStream = new FileInputStream(str);
                mediaPlayer.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
            } else {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.mContext.getAssets().openFd(str);
                mediaPlayer.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            }
            mediaPlayer.prepare();
            mediaPlayer.setVolume(this.mLeftVolume, this.mRightVolume);
            return mediaPlayer;
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            return null;
        }
    }
}
