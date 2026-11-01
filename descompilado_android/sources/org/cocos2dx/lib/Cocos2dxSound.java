package org.cocos2dx.lib;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import com.appsflyer.share.Constants;
import com.chukong.cocosplay.client.CocosPlayClient;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes3.dex */
public class Cocos2dxSound {
    private static final int INVALID_SOUND_ID = -1;
    private static final int INVALID_STREAM_ID = -1;
    private static final int LOAD_TIME_OUT = 500;
    private static final int LOOP_PRIORITY = 2;
    private static final int MAX_SIMULTANEOUS_STREAMS_DEFAULT = 15;
    private static final int MAX_SIMULTANEOUS_STREAMS_I9100 = 3;
    private static final int SOUND_PRIORITY = 1;
    private static final int SOUND_QUALITY = 5;
    private static final float SOUND_RATE = 1.0f;
    private static final String TAG = "hcr2_cocos - Cocos2dxSound";
    private final Context mContext;
    private float mLeftVolume;
    private float mRightVolume;
    private SoundPool mSoundPool;
    private boolean mIsAudioFocus = true;
    private final HashMap<String, ArrayList<Integer>> mPathStreamIDsMap = new HashMap<>();
    private final Object mLockPathStreamIDsMap = new Object();
    private final HashMap<String, Integer> mPathSoundIDMap = new HashMap<>();
    private ConcurrentHashMap<Integer, SoundInfoForLoadedCompleted> mPlayWhenLoadedEffects = new ConcurrentHashMap<>();
    private HashMap<Integer, Boolean> mCompletedLoadOperations = new HashMap<>();
    private final Object mCompletedLoadOperationsLock = new Object();

    public Cocos2dxSound(Context context) {
        this.mContext = context;
        initData();
    }

    @TargetApi(21)
    protected void createSoundPoolWithBuilder() {
        this.mSoundPool = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(14).setContentType(4).build()).setMaxStreams(15).build();
    }

    protected void createSoundPoolWithConstructor() {
        this.mSoundPool = new SoundPool(15, 3, 5);
    }

    private void initData() {
        if (Cocos2dxHelper.getDeviceModel().contains("GT-I9100")) {
            this.mSoundPool = new SoundPool(3, 3, 5);
        } else if (Build.VERSION.SDK_INT >= 21) {
            createSoundPoolWithBuilder();
        } else {
            createSoundPoolWithConstructor();
        }
        this.mSoundPool.setOnLoadCompleteListener(new OnLoadCompletedListener());
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
    }

    public int preloadEffect(String str) {
        if (CocosPlayClient.isEnabled() && !CocosPlayClient.isDemo()) {
            CocosPlayClient.updateAssets(str);
        }
        CocosPlayClient.notifyFileLoaded(str);
        Integer numValueOf = this.mPathSoundIDMap.get(str);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(createSoundIDFromAsset(str));
            if (numValueOf.intValue() != -1) {
                this.mPathSoundIDMap.put(str, numValueOf);
            }
        }
        return numValueOf.intValue();
    }

    public void unloadEffect(String str) {
        synchronized (this.mLockPathStreamIDsMap) {
            ArrayList<Integer> arrayList = this.mPathStreamIDsMap.get(str);
            if (arrayList != null) {
                Iterator<Integer> it = arrayList.iterator();
                while (it.hasNext()) {
                    this.mSoundPool.stop(it.next().intValue());
                }
            }
            this.mPathStreamIDsMap.remove(str);
        }
        Integer num = this.mPathSoundIDMap.get(str);
        if (num != null) {
            this.mSoundPool.unload(num.intValue());
            this.mPathSoundIDMap.remove(str);
            synchronized (this.mCompletedLoadOperationsLock) {
                this.mCompletedLoadOperations.remove(num);
            }
        }
    }

    public int playEffect(String str, boolean z, float f, float f2, float f3) {
        Integer num;
        SoundInfoForLoadedCompleted soundInfoForLoadedCompleted;
        Integer num2 = this.mPathSoundIDMap.get(str);
        Boolean bool = false;
        SoundInfoForLoadedCompleted soundInfoForLoadedCompleted2 = null;
        if (num2 != null) {
            synchronized (this.mCompletedLoadOperationsLock) {
                if (!this.mCompletedLoadOperations.containsKey(num2)) {
                    SoundInfoForLoadedCompleted soundInfoForLoadedCompleted3 = new SoundInfoForLoadedCompleted(str, z, f, f2, f3);
                    this.mPlayWhenLoadedEffects.putIfAbsent(num2, soundInfoForLoadedCompleted3);
                    bool = true;
                    soundInfoForLoadedCompleted2 = soundInfoForLoadedCompleted3;
                }
            }
        }
        if (num2 != null && !bool.booleanValue()) {
            return doPlayEffect(str, num2.intValue(), z, f, f2, f3);
        }
        if (bool.booleanValue()) {
            num = num2;
            soundInfoForLoadedCompleted = soundInfoForLoadedCompleted2;
        } else {
            Integer numValueOf = Integer.valueOf(preloadEffect(str));
            if (numValueOf.intValue() == -1) {
                return -1;
            }
            soundInfoForLoadedCompleted = new SoundInfoForLoadedCompleted(str, z, f, f2, f3);
            this.mPlayWhenLoadedEffects.putIfAbsent(numValueOf, soundInfoForLoadedCompleted);
            num = numValueOf;
        }
        synchronized (soundInfoForLoadedCompleted) {
            try {
                soundInfoForLoadedCompleted.wait(500L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = soundInfoForLoadedCompleted.effectID;
        this.mPlayWhenLoadedEffects.remove(num);
        return i;
    }

    public void stopEffect(int i) {
        this.mSoundPool.stop(i);
        synchronized (this.mLockPathStreamIDsMap) {
            for (String str : this.mPathStreamIDsMap.keySet()) {
                if (this.mPathStreamIDsMap.get(str).contains(Integer.valueOf(i))) {
                    this.mPathStreamIDsMap.get(str).remove(this.mPathStreamIDsMap.get(str).indexOf(Integer.valueOf(i)));
                    break;
                }
            }
        }
    }

    public void pauseEffect(int i) {
        this.mSoundPool.pause(i);
    }

    public void resumeEffect(int i) {
        this.mSoundPool.resume(i);
    }

    public void pauseAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
                while (it.hasNext()) {
                    Iterator<Integer> it2 = it.next().getValue().iterator();
                    while (it2.hasNext()) {
                        this.mSoundPool.pause(it2.next().intValue());
                    }
                }
            }
        }
    }

    public void resumeAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
                while (it.hasNext()) {
                    Iterator<Integer> it2 = it.next().getValue().iterator();
                    while (it2.hasNext()) {
                        this.mSoundPool.resume(it2.next().intValue());
                    }
                }
            }
        }
    }

    public void stopAllEffects() {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
                while (it.hasNext()) {
                    Iterator<Integer> it2 = it.next().getValue().iterator();
                    while (it2.hasNext()) {
                        this.mSoundPool.stop(it2.next().intValue());
                    }
                }
            }
            this.mPathStreamIDsMap.clear();
        }
    }

    public float getEffectsVolume() {
        return (this.mLeftVolume + this.mRightVolume) / 2.0f;
    }

    public void setEffectsVolume(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > SOUND_RATE) {
            f = SOUND_RATE;
        }
        this.mRightVolume = f;
        this.mLeftVolume = f;
        if (this.mIsAudioFocus) {
            setEffectsVolumeInternal(this.mLeftVolume, this.mRightVolume);
        }
    }

    private void setEffectsVolumeInternal(float f, float f2) {
        synchronized (this.mLockPathStreamIDsMap) {
            if (!this.mPathStreamIDsMap.isEmpty()) {
                Iterator<Map.Entry<String, ArrayList<Integer>>> it = this.mPathStreamIDsMap.entrySet().iterator();
                while (it.hasNext()) {
                    Iterator<Integer> it2 = it.next().getValue().iterator();
                    while (it2.hasNext()) {
                        this.mSoundPool.setVolume(it2.next().intValue(), f, f2);
                    }
                }
            }
        }
    }

    public void end() {
        this.mSoundPool.release();
        synchronized (this.mLockPathStreamIDsMap) {
            this.mPathStreamIDsMap.clear();
        }
        this.mPathSoundIDMap.clear();
        this.mPlayWhenLoadedEffects.clear();
        this.mLeftVolume = 0.5f;
        this.mRightVolume = 0.5f;
        initData();
    }

    private int createSoundIDFromAsset(String str) {
        int iLoad;
        try {
            if (str.startsWith(Constants.URL_PATH_DELIMITER)) {
                iLoad = this.mSoundPool.load(str, 0);
            } else {
                iLoad = this.mSoundPool.load(this.mContext.getAssets().openFd(str), 0);
            }
        } catch (Exception e) {
            Log.e(TAG, "error: " + e.getMessage(), e);
            iLoad = -1;
        }
        if (iLoad == 0) {
            return -1;
        }
        return iLoad;
    }

    private float clamp(float f, float f2, float f3) {
        return Math.max(f2, Math.min(f, f3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int doPlayEffect(String str, int i, boolean z, float f, float f2, float f3) {
        int iPlay;
        float fClamp = this.mLeftVolume * f3 * (SOUND_RATE - clamp(f2, 0.0f, SOUND_RATE));
        float fClamp2 = this.mRightVolume * f3 * (SOUND_RATE - clamp(-f2, 0.0f, SOUND_RATE));
        float fClamp3 = clamp(f * SOUND_RATE, 0.5f, 2.0f);
        iPlay = this.mSoundPool.play(i, clamp(fClamp, 0.0f, SOUND_RATE), clamp(fClamp2, 0.0f, SOUND_RATE), z ? 2 : 1, z ? -1 : 0, fClamp3);
        synchronized (this.mLockPathStreamIDsMap) {
            ArrayList<Integer> arrayList = this.mPathStreamIDsMap.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                this.mPathStreamIDsMap.put(str, arrayList);
            }
            arrayList.add(Integer.valueOf(iPlay));
        }
        return iPlay;
    }

    public void onEnterBackground() {
        this.mSoundPool.autoPause();
    }

    public void onEnterForeground() {
        this.mSoundPool.autoResume();
    }

    void setAudioFocus(boolean z) {
        this.mIsAudioFocus = z;
        setEffectsVolumeInternal(this.mIsAudioFocus ? this.mLeftVolume : 0.0f, this.mIsAudioFocus ? this.mRightVolume : 0.0f);
    }

    public class SoundInfoForLoadedCompleted {
        public int effectID = -1;
        public float gain;
        public boolean isLoop;
        public float pan;
        public String path;
        public float pitch;

        public SoundInfoForLoadedCompleted(String str, boolean z, float f, float f2, float f3) {
            this.path = str;
            this.isLoop = z;
            this.pitch = f;
            this.pan = f2;
            this.gain = f3;
        }
    }

    public class OnLoadCompletedListener implements SoundPool.OnLoadCompleteListener {
        public OnLoadCompletedListener() {
        }

        @Override // android.media.SoundPool.OnLoadCompleteListener
        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            SoundInfoForLoadedCompleted soundInfoForLoadedCompleted;
            if (i2 == 0) {
                synchronized (Cocos2dxSound.this.mCompletedLoadOperationsLock) {
                    soundInfoForLoadedCompleted = (SoundInfoForLoadedCompleted) Cocos2dxSound.this.mPlayWhenLoadedEffects.get(Integer.valueOf(i));
                    Cocos2dxSound.this.mCompletedLoadOperations.put(Integer.valueOf(i), true);
                }
                if (soundInfoForLoadedCompleted != null) {
                    soundInfoForLoadedCompleted.effectID = Cocos2dxSound.this.doPlayEffect(soundInfoForLoadedCompleted.path, i, soundInfoForLoadedCompleted.isLoop, soundInfoForLoadedCompleted.pitch, soundInfoForLoadedCompleted.pan, soundInfoForLoadedCompleted.gain);
                    synchronized (soundInfoForLoadedCompleted) {
                        soundInfoForLoadedCompleted.notifyAll();
                    }
                }
            }
        }
    }

    public void setEffectVolume(int i, float f) {
        this.mSoundPool.setVolume(i, f, f);
    }

    public void setEffectVolumeAndPan(int i, float f, float f2) {
        this.mSoundPool.setVolume(i, Math.min(SOUND_RATE - f2, SOUND_RATE) * f, f * Math.min(f2 + SOUND_RATE, SOUND_RATE));
    }

    public void setEffectRate(int i, float f) {
        this.mSoundPool.setRate(i, f);
    }
}
