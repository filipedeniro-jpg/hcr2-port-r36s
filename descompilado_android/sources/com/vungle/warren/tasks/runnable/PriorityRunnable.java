package com.vungle.warren.tasks.runnable;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PriorityRunnable implements Comparable, Runnable {
    public abstract Integer getPriority();

    @Override // java.lang.Comparable
    public int compareTo(@NonNull Object obj) {
        if (!(obj instanceof PriorityRunnable)) {
            return -1;
        }
        return ((PriorityRunnable) obj).getPriority().compareTo(getPriority());
    }
}
