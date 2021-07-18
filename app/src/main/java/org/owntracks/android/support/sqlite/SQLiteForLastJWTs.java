package org.owntracks.android.support.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import java.util.ArrayList;

public class SQLiteForLastJWTs extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "last_qr_codes_database";
    public static final String QR_TABLE_NAME = "qr_codes";
    public static final String QR_COLUMN_ID = "_id";
    public static final String QR_COLUMN_JWT = "json_web_token";


    public SQLiteForLastJWTs(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + QR_TABLE_NAME + " (" +
                QR_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QR_COLUMN_JWT + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + QR_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertLastJWT(String jsonWebToken) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QR_COLUMN_JWT, jsonWebToken);
        db.insert(QR_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean removeLastJWT(String jsonWebToken){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(QR_TABLE_NAME, QR_COLUMN_JWT + "=?", new String[]{jsonWebToken}) > 0;
    }

    public ArrayList<String> getAllLastJWTs() {
        ArrayList<String> array_list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + QR_TABLE_NAME, null);
        res.moveToFirst();

        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex(QR_COLUMN_JWT)));
            res.moveToNext();
        }
        return array_list;
    }
}