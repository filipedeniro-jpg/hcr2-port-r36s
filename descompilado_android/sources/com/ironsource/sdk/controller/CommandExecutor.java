package com.ironsource.sdk.controller;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class CommandExecutor {
    private CommandExecutorState mCommandExecutorState = CommandExecutorState.NOT_READY;
    private ArrayList mCommandExecutorQueue = new ArrayList();

    enum CommandExecutorState {
        NOT_READY,
        READY
    }

    public synchronized void setReady() {
        this.mCommandExecutorState = CommandExecutorState.READY;
    }

    public synchronized void executeCommand(Runnable runnable) {
        if (this.mCommandExecutorState != CommandExecutorState.READY) {
            this.mCommandExecutorQueue.add(runnable);
        } else {
            runnable.run();
        }
    }

    public synchronized void purgeDelayedCommands() {
        Object[] array = this.mCommandExecutorQueue.toArray();
        for (int i = 0; i < array.length; i++) {
            ((Runnable) array[i]).run();
            array[i] = null;
        }
        this.mCommandExecutorQueue.clear();
    }
}
