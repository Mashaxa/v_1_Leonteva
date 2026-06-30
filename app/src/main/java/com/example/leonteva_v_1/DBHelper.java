package com.example.leonteva_v_1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "app.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (login TEXT PRIMARY KEY, password TEXT)");
        // Демо-логин для теста (как на макете)
        db.execSQL("INSERT OR IGNORE INTO users VALUES ('Андрей', 'Пароль')");
    }

    @Override public void onUpgrade(SQLiteDatabase db, int old, int newV) {}

    public boolean checkUser(String login, String password) {
        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM users WHERE login=? AND password=?", new String[]{login, password});
            boolean exists = c.getCount() > 0;
            c.close();
            return exists;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean registerUser(String login, String password) {
        try {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login", login);
            cv.put("password", password);
            long result = db.insert("users", null, cv);
            return result != -1;
        } catch (Exception e) {
            return false;
        }
    }
}