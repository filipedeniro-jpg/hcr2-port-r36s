package com.ironsource.mediationsdk.logger;

import android.util.Log;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.sdk.constants.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class ServerLogger extends IronSourceLogger {
    public static final String NAME = "server";
    private final int SERVER_LOGS_SIZE_LIMIT;
    private ArrayList<ServerLogEntry> mLogs;

    public ServerLogger() {
        super(NAME);
        this.SERVER_LOGS_SIZE_LIMIT = 1000;
        this.mLogs = new ArrayList<>();
    }

    public ServerLogger(int i) {
        super(NAME, i);
        this.SERVER_LOGS_SIZE_LIMIT = 1000;
        this.mLogs = new ArrayList<>();
    }

    private synchronized void addLogEntry(ServerLogEntry serverLogEntry) {
        try {
            this.mLogs.add(serverLogEntry);
            if (shouldSendLogs()) {
                send();
            } else if (this.mLogs.size() > 1000) {
                try {
                    ArrayList<ServerLogEntry> arrayList = new ArrayList<>();
                    for (int i = 500; i < this.mLogs.size(); i++) {
                        arrayList.add(this.mLogs.get(i));
                    }
                    this.mLogs = arrayList;
                } catch (Exception unused) {
                    this.mLogs = new ArrayList<>();
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    private boolean shouldSendLogs() {
        ArrayList<ServerLogEntry> arrayList = this.mLogs;
        return arrayList.get(arrayList.size() - 1).getLogLevel() == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void send() {
        IronSourceUtils.createAndStartWorker(new LogsSender(this.mLogs), "LogsSender");
        this.mLogs = new ArrayList<>();
    }

    private String getTimestamp() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }

    @Override // com.ironsource.mediationsdk.logger.IronSourceLogger
    public synchronized void log(IronSourceLogger.IronSourceTag ironSourceTag, String str, int i) {
        addLogEntry(new ServerLogEntry(ironSourceTag, getTimestamp(), str, i));
    }

    @Override // com.ironsource.mediationsdk.logger.IronSourceLogger
    public synchronized void logException(IronSourceLogger.IronSourceTag ironSourceTag, String str, Throwable th) {
        StringBuilder sb = new StringBuilder(str);
        if (th != null) {
            sb.append(":stacktrace[");
            sb.append(Log.getStackTraceString(th));
            sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        }
        addLogEntry(new ServerLogEntry(ironSourceTag, getTimestamp(), sb.toString(), 3));
    }

    private class SendingCalc {
        private int DEFAULT_SIZE = 1;
        private int DEFAULT_TIME = 1;
        private int DEFAULT_DEBUG_LEVEL = 3;

        private boolean error(int i) {
            return i == 3;
        }

        private void initDefaults() {
        }

        private boolean size() {
            return false;
        }

        private boolean time() {
            return false;
        }

        public SendingCalc() {
            initDefaults();
        }

        public void notifyEvent(int i) {
            if (calc(i)) {
                ServerLogger.this.send();
            }
        }

        private boolean calc(int i) {
            return error(i) || size() || time();
        }
    }
}
