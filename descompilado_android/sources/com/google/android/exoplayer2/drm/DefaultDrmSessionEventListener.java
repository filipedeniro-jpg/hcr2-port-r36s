package com.google.android.exoplayer2.drm;

import android.os.Handler;
import com.google.android.exoplayer2.util.Assertions;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public interface DefaultDrmSessionEventListener {
    void onDrmKeysLoaded();

    void onDrmKeysRemoved();

    void onDrmKeysRestored();

    void onDrmSessionManagerError(Exception exc);

    public static final class EventDispatcher {
        private final CopyOnWriteArrayList<HandlerAndListener> listeners = new CopyOnWriteArrayList<>();

        public void addListener(Handler handler, DefaultDrmSessionEventListener defaultDrmSessionEventListener) {
            Assertions.checkArgument((handler == null || defaultDrmSessionEventListener == null) ? false : true);
            this.listeners.add(new HandlerAndListener(handler, defaultDrmSessionEventListener));
        }

        public void removeListener(DefaultDrmSessionEventListener defaultDrmSessionEventListener) {
            for (HandlerAndListener handlerAndListener : this.listeners) {
                if (handlerAndListener.listener == defaultDrmSessionEventListener) {
                    this.listeners.remove(handlerAndListener);
                }
            }
        }

        public void drmKeysLoaded() {
            for (HandlerAndListener handlerAndListener : this.listeners) {
                final DefaultDrmSessionEventListener defaultDrmSessionEventListener = handlerAndListener.listener;
                handlerAndListener.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener.EventDispatcher.1
                    @Override // java.lang.Runnable
                    public void run() {
                        defaultDrmSessionEventListener.onDrmKeysLoaded();
                    }
                });
            }
        }

        public void drmSessionManagerError(final Exception exc) {
            for (HandlerAndListener handlerAndListener : this.listeners) {
                final DefaultDrmSessionEventListener defaultDrmSessionEventListener = handlerAndListener.listener;
                handlerAndListener.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener.EventDispatcher.2
                    @Override // java.lang.Runnable
                    public void run() {
                        defaultDrmSessionEventListener.onDrmSessionManagerError(exc);
                    }
                });
            }
        }

        public void drmKeysRestored() {
            for (HandlerAndListener handlerAndListener : this.listeners) {
                final DefaultDrmSessionEventListener defaultDrmSessionEventListener = handlerAndListener.listener;
                handlerAndListener.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener.EventDispatcher.3
                    @Override // java.lang.Runnable
                    public void run() {
                        defaultDrmSessionEventListener.onDrmKeysRestored();
                    }
                });
            }
        }

        public void drmKeysRemoved() {
            for (HandlerAndListener handlerAndListener : this.listeners) {
                final DefaultDrmSessionEventListener defaultDrmSessionEventListener = handlerAndListener.listener;
                handlerAndListener.handler.post(new Runnable() { // from class: com.google.android.exoplayer2.drm.DefaultDrmSessionEventListener.EventDispatcher.4
                    @Override // java.lang.Runnable
                    public void run() {
                        defaultDrmSessionEventListener.onDrmKeysRemoved();
                    }
                });
            }
        }

        private static final class HandlerAndListener {
            public final Handler handler;
            public final DefaultDrmSessionEventListener listener;

            public HandlerAndListener(Handler handler, DefaultDrmSessionEventListener defaultDrmSessionEventListener) {
                this.handler = handler;
                this.listener = defaultDrmSessionEventListener;
            }
        }
    }
}
