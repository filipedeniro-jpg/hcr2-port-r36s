package com.ironsource.sdk.data;

import androidx.core.app.NotificationCompat;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.my.target.m;

/* JADX INFO: loaded from: classes2.dex */
public class SSAEventCalendar extends SSAObj {
    private String DAILY;
    private String DAYS_IN_MONTH;
    private String DAYS_IN_WEEK;
    private String DAYS_IN_YEAR;
    private String DESCRIPTION;
    private String END;
    private String EXCEPTIONDATES;
    private String EXPIRES;
    private String FREQUENCY;
    private String ID;
    private String INTERVAL;
    private String MONTHLY;
    private String MONTHS_IN_YEAR;
    private String RECURRENCE;
    private String REMINDER;
    private String START;
    private String STATUS;
    private String WEEKLY;
    private String WEEKS_IN_MONTH;
    private String YEARLY;
    private String mDescription;
    private String mEnd;
    private String mStart;

    public SSAEventCalendar(String str) {
        super(str);
        this.ID = "id";
        this.DESCRIPTION = "description";
        this.START = m.aq;
        this.END = TtmlNode.END;
        this.STATUS = "status";
        this.RECURRENCE = "recurrence";
        this.REMINDER = NotificationCompat.CATEGORY_REMINDER;
        this.FREQUENCY = "frequency";
        this.INTERVAL = "interval";
        this.EXPIRES = "expires";
        this.EXCEPTIONDATES = "exceptionDates";
        this.DAYS_IN_WEEK = "daysInWeek";
        this.DAYS_IN_MONTH = "daysInMonth";
        this.DAYS_IN_YEAR = "daysInYear";
        this.WEEKS_IN_MONTH = "weeksInMonth";
        this.MONTHS_IN_YEAR = "monthsInYear";
        this.DAILY = "daily";
        this.WEEKLY = "weekly";
        this.MONTHLY = "monthly";
        this.YEARLY = "yearly";
        if (containsKey(this.DESCRIPTION)) {
            setDescription(getString(this.DESCRIPTION));
        }
        if (containsKey(this.START)) {
            setStart(getString(this.START));
        }
        if (containsKey(this.END)) {
            setEnd(getString(this.END));
        }
    }

    public String getDescription() {
        return this.mDescription;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public String getStart() {
        return this.mStart;
    }

    public void setStart(String str) {
        this.mStart = str;
    }

    public String getEnd() {
        return this.mEnd;
    }

    public void setEnd(String str) {
        this.mEnd = str;
    }
}
