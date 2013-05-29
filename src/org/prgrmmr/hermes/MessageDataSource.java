package org.prgrmmr.hermes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class MessageDataSource {

    // Database fields
    private SQLiteDatabase database;
    private HermesOpenHelper dbHelper;
    private String[] allColumns = { HermesOpenHelper.COLUMN_ID,
            HermesOpenHelper.COLUMN_CONTENT, HermesOpenHelper.COLUMN_DATE };

    public MessageDataSource(Context context) {
        dbHelper = new HermesOpenHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    private Message create(String message, Date date) {
        ContentValues values = new ContentValues();
        values.put(HermesOpenHelper.COLUMN_CONTENT, message);
        values.put(HermesOpenHelper.COLUMN_DATE, date.getTime());
        long insertId = database.insert(HermesOpenHelper.TABLE_MESSAGE, null,
                values);
        Cursor cursor = database.query(HermesOpenHelper.TABLE_MESSAGE,
                allColumns, HermesOpenHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Message newMessage = cursorToMessage(cursor);
        cursor.close();
        return newMessage;
    }

    private void delete(Message message) {
        long id = message.getId();
        System.out.println("Message deleted with id: " + id);
        database.delete(HermesOpenHelper.TABLE_MESSAGE, HermesOpenHelper.COLUMN_ID
                + " = " + id, null);
    }

    private void delete(Long id) {
        System.out.println("Message deleted with id: " + id);
        database.delete(HermesOpenHelper.TABLE_MESSAGE, HermesOpenHelper.COLUMN_ID
                + " = " + id, null);
    }

    private List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<Message>();

        Cursor cursor = database.query(HermesOpenHelper.TABLE_MESSAGE,
                allColumns, null, null, null, null, "date DESC");

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Message message = cursorToMessage(cursor);
            messages.add(message);
            cursor.moveToNext();
        }
        cursor.close();
        return messages;
    }

    private Message cursorToMessage(Cursor cursor) {
        Message message = new Message();
        message.setId(cursor.getLong(0));
        message.setContent(cursor.getString(1));
        message.setDate(new Date(cursor.getLong(2)));
        return message;
    }

    public static void saveMessage(Context context, String msg) {
        MessageDataSource dataSource = new MessageDataSource(context);
        dataSource.open();
        dataSource.create(msg, new Date());
        dataSource.close();
    }

    public static void deleteMessage(Context context, long id) {
        MessageDataSource dataSource = new MessageDataSource(context);
        dataSource.open();
        dataSource.delete(id);
        dataSource.close();
    }

    public static List<Message> getAllMessages(Context context) {
        MessageDataSource dataSource = new MessageDataSource(context);
        dataSource.open();
        List<Message> messages = dataSource.getAllMessages();
        dataSource.close();
        return messages;
    }
}