package org.prgrmmr.hermes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {

    private long id;
    private String content;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return new SimpleDateFormat("dd/MM/yy - HH:mm").format(date);
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return getFormattedDate();
    }
}