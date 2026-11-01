package com.amazon.device.ads;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
class ThreadUtils {
    private static ThreadRunner threadRunner = new ThreadRunner();

    public enum ExecutionStyle {
        RUN_ASAP,
        SCHEDULE
    }

    public enum ExecutionThread {
        MAIN_THREAD,
        BACKGROUND_THREAD
    }

    ThreadUtils() {
    }

    public static ThreadRunner getThreadRunner() {
        return threadRunner;
    }

    static void setThreadRunner(ThreadRunner threadRunner2) {
        threadRunner = threadRunner2;
    }

    public static boolean isOnMainThread() {
        return ThreadVerify.getInstance().isOnMainThread();
    }

    public static final <T> void executeAsyncTask(MobileAdsAsyncTask<T, ?, ?> mobileAdsAsyncTask, T... tArr) {
        threadRunner.executeAsyncTask(ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD, mobileAdsAsyncTask, tArr);
    }

    public static final <T> void executeAsyncTask(ThreadRunner threadRunner2, ExecutionStyle executionStyle, ExecutionThread executionThread, MobileAdsAsyncTask<T, ?, ?> mobileAdsAsyncTask, T... tArr) {
        threadRunner2.executeAsyncTask(executionStyle, executionThread, mobileAdsAsyncTask, tArr);
    }

    public static void scheduleRunnable(Runnable runnable) {
        scheduleRunnable(runnable, threadRunner);
    }

    public static void scheduleRunnable(Runnable runnable, ThreadRunner threadRunner2) {
        threadRunner2.execute(runnable, ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
    }

    public static void executeRunnableWithThreadCheck(Runnable runnable) {
        executeRunnableWithThreadCheck(runnable, threadRunner);
    }

    public static void executeRunnableWithThreadCheck(Runnable runnable, ThreadRunner threadRunner2) {
        threadRunner2.execute(runnable, ExecutionStyle.RUN_ASAP, ExecutionThread.BACKGROUND_THREAD);
    }

    public static void scheduleOnMainThread(Runnable runnable) {
        scheduleOnMainThread(runnable, threadRunner);
    }

    public static void scheduleOnMainThread(Runnable runnable, ThreadRunner threadRunner2) {
        threadRunner2.execute(runnable, ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
    }

    public static void executeOnMainThread(Runnable runnable) {
        executeOnMainThread(runnable, threadRunner);
    }

    public static void executeOnMainThread(Runnable runnable, ThreadRunner threadRunner2) {
        threadRunner2.execute(runnable, ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD);
    }

    public static class ThreadRunner {
        private static final String LOGTAG = "ThreadRunner";
        private final HashMap<ExecutionStyle, HashMap<ExecutionThread, RunnableExecutor>> executors;
        private final MobileAdsLogger logger;

        ThreadRunner() {
            this(new MobileAdsLoggerFactory());
            ThreadVerify threadVerify = new ThreadVerify();
            withExecutor(new ThreadPoolScheduler());
            withExecutor(new BackgroundThreadRunner(threadVerify));
            withExecutor(new MainThreadScheduler());
            withExecutor(new MainThreadRunner(threadVerify));
        }

        ThreadRunner(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
            this.executors = new HashMap<>();
            this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        }

        public ThreadRunner withExecutor(RunnableExecutor runnableExecutor) {
            HashMap<ExecutionThread, RunnableExecutor> map = this.executors.get(runnableExecutor.getExecutionStyle());
            if (map == null) {
                map = new HashMap<>();
                this.executors.put(runnableExecutor.getExecutionStyle(), map);
            }
            map.put(runnableExecutor.getExecutionThread(), runnableExecutor);
            return this;
        }

        public void execute(Runnable runnable, ExecutionStyle executionStyle, ExecutionThread executionThread) {
            HashMap<ExecutionThread, RunnableExecutor> map = this.executors.get(executionStyle);
            if (map == null) {
                this.logger.e("No executor available for %s execution style.", executionStyle);
                return;
            }
            RunnableExecutor runnableExecutor = map.get(executionThread);
            if (runnableExecutor == null) {
                this.logger.e("No executor available for %s execution style on % execution thread.", executionStyle, executionThread);
            }
            runnableExecutor.execute(runnable);
        }

        public <T> void executeAsyncTask(MobileAdsAsyncTask<T, ?, ?> mobileAdsAsyncTask, T... tArr) {
            executeAsyncTask(ExecutionStyle.RUN_ASAP, ExecutionThread.MAIN_THREAD, mobileAdsAsyncTask, tArr);
        }

        public <T> void executeAsyncTask(ExecutionStyle executionStyle, ExecutionThread executionThread, final MobileAdsAsyncTask<T, ?, ?> mobileAdsAsyncTask, final T... tArr) {
            ThreadUtils.threadRunner.execute(new Runnable() { // from class: com.amazon.device.ads.ThreadUtils.ThreadRunner.1
                @Override // java.lang.Runnable
                public void run() {
                    AndroidTargetUtils.executeAsyncTask(mobileAdsAsyncTask, tArr);
                }
            }, executionStyle, executionThread);
        }
    }

    public static abstract class RunnableExecutor {
        private final ExecutionStyle executionStyle;
        private final ExecutionThread executionThread;

        public abstract void execute(Runnable runnable);

        public RunnableExecutor(ExecutionStyle executionStyle, ExecutionThread executionThread) {
            this.executionStyle = executionStyle;
            this.executionThread = executionThread;
        }

        public ExecutionStyle getExecutionStyle() {
            return this.executionStyle;
        }

        public ExecutionThread getExecutionThread() {
            return this.executionThread;
        }
    }

    public static class MainThreadScheduler extends RunnableExecutor {
        public MainThreadScheduler() {
            super(ExecutionStyle.SCHEDULE, ExecutionThread.MAIN_THREAD);
        }

        @Override // com.amazon.device.ads.ThreadUtils.RunnableExecutor
        public void execute(Runnable runnable) {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static class ThreadPoolScheduler extends RunnableExecutor {
        private static final int keepAliveTimeSeconds = 30;
        private static final int maxNumberThreads = 3;
        private static final int numberThreads = 1;
        private final ExecutorService executorService;

        public ThreadPoolScheduler() {
            super(ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
            this.executorService = new ThreadPoolExecutor(1, 3, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        }

        @Override // com.amazon.device.ads.ThreadUtils.RunnableExecutor
        public void execute(Runnable runnable) {
            this.executorService.submit(runnable);
        }
    }

    public static class SingleThreadScheduler extends RunnableExecutor {
        private ExecutorService executorService;

        public SingleThreadScheduler() {
            super(ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
            this.executorService = Executors.newSingleThreadExecutor();
        }

        @Override // com.amazon.device.ads.ThreadUtils.RunnableExecutor
        public void execute(Runnable runnable) {
            this.executorService.submit(runnable);
        }
    }

    public static class MainThreadRunner extends ThreadExecutor {
        public MainThreadRunner(ThreadVerify threadVerify) {
            super(threadVerify, new MainThreadScheduler());
        }
    }

    public static class BackgroundThreadRunner extends ThreadExecutor {
        public BackgroundThreadRunner(ThreadVerify threadVerify) {
            super(threadVerify, new ThreadPoolScheduler());
        }
    }

    public static class ThreadExecutor extends RunnableExecutor {
        private final RunnableExecutor threadScheduler;
        private final ThreadVerify threadVerify;

        public ThreadExecutor(ThreadVerify threadVerify, RunnableExecutor runnableExecutor) {
            super(ExecutionStyle.RUN_ASAP, runnableExecutor.executionThread);
            this.threadVerify = threadVerify;
            this.threadScheduler = runnableExecutor;
        }

        @Override // com.amazon.device.ads.ThreadUtils.RunnableExecutor
        public void execute(Runnable runnable) {
            boolean zIsOnMainThread;
            int i = AnonymousClass1.$SwitchMap$com$amazon$device$ads$ThreadUtils$ExecutionThread[this.threadScheduler.getExecutionThread().ordinal()];
            if (i == 1) {
                zIsOnMainThread = !this.threadVerify.isOnMainThread();
            } else {
                zIsOnMainThread = i != 2 ? false : this.threadVerify.isOnMainThread();
            }
            if (zIsOnMainThread) {
                this.threadScheduler.execute(runnable);
            } else {
                runnable.run();
            }
        }
    }

    /* JADX INFO: renamed from: com.amazon.device.ads.ThreadUtils$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$ThreadUtils$ExecutionThread = new int[ExecutionThread.values().length];

        static {
            try {
                $SwitchMap$com$amazon$device$ads$ThreadUtils$ExecutionThread[ExecutionThread.MAIN_THREAD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$ThreadUtils$ExecutionThread[ExecutionThread.BACKGROUND_THREAD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    static class ThreadVerify {
        private static ThreadVerify instance = new ThreadVerify();

        ThreadVerify() {
        }

        static ThreadVerify getInstance() {
            return instance;
        }

        boolean isOnMainThread() {
            return Looper.getMainLooper().getThread() == Thread.currentThread();
        }
    }

    static abstract class MobileAdsAsyncTask<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> {
        @Override // android.os.AsyncTask
        protected abstract Result doInBackground(Params... paramsArr);

        MobileAdsAsyncTask() {
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Result result) {
            super.onPostExecute(result);
        }
    }
}
