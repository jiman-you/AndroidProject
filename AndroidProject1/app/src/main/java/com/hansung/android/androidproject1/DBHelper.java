package com.hansung.android.androidproject1;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG = "SQLiteDBTest";

    public DBHelper(Context context) {
        super(context, DBContract.DB_NAME, null, DBContract.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, getClass().getName() + ".onCreate()");
        db.execSQL(DBContract.Memos.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG, getClass().getName() + ".onUpgrade()");
        db.execSQL(DBContract.Memos.DELETE_TABLE);
        onCreate(db);
    }
    //추가
    public void insertMemoBySQL(String title, int sh, int sm, int sme, int eh, int em, int eme, String place, String memo, int ye, int mo, int da) {
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (NULL, '%s', %d, %d, %d, %d, %d, %d, '%s', '%s', %d, %d, %d)",
                    DBContract.Memos.TABLE_NAME,
                    DBContract.Memos._ID,
                    DBContract.Memos.KEY_TITLE,
                    DBContract.Memos.KEY_S_HOUR,
                    DBContract.Memos.KEY_S_MIN,
                    DBContract.Memos.KEY_S_MERIDIEM,
                    DBContract.Memos.KEY_E_HOUR,
                    DBContract.Memos.KEY_E_MIN,
                    DBContract.Memos.KEY_E_MERIDIEM,
                    DBContract.Memos.KEY_PLACE,
                    DBContract.Memos.KEY_MEMO,
                    DBContract.Memos.KEY_YEAR,
                    DBContract.Memos.KEY_MONTH,
                    DBContract.Memos.KEY_DAY,
                    title, sh, sm, sme, eh, em, eme, place, memo, ye, mo, da);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }
    //삭제
    public void deleteMemoBySQL(int year, int month, int day) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %s AND %s = %s AND %s = %s",
                    DBContract.Memos.TABLE_NAME,
                    DBContract.Memos.KEY_YEAR,
                    year,
                    DBContract.Memos.KEY_MONTH,
                    month,
                    DBContract.Memos.KEY_DAY,
                    day);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
        }
    }
    //    public Cursor getDayMemosBySQL(String year, String month, String day) {
//        String sql = "Select * FROM " + DBContract.Memos.TABLE_NAME +" WHERE KEY_YEAR=" + year+" AND KEY_MONTH= " + month+" AND KEY_DAY= " + day;
//        return getReadableDatabase().rawQuery(sql,null);
//    }

//    public Cursor getDayMemosBySQL(int scheduleYear, int scheduleMonth, int scheduleDay) {
//        String sql = "Select * FROM " + DBContract.Memos.TABLE_NAME +" WHERE DBContract.Memos.KEY_YEAR= '" + scheduleYear+"' AND DBContract.Memos.KEY_MONTH= '" + scheduleMonth+"' AND DBContract.Memos.KEY_DAY= '" + scheduleDay+"'";
//        return getReadableDatabase().rawQuery(sql,null);
//    }

    public Cursor selectMemosBySQL(String year, String month, String day) {
        try{
            String sql = String.format(
                    "SELECT FROM %s WHERE %s = %s AND %s = %s AND %s = %s",
                    DBContract.Memos.TABLE_NAME,
                    DBContract.Memos.KEY_YEAR,
                    year,
                    DBContract.Memos.KEY_MONTH,
                    month,
                    DBContract.Memos.KEY_DAY,
                    day
            );
            return getReadableDatabase().rawQuery(sql,null);
        } catch(SQLException e) {
            return null;
        }

    }
    public Cursor getAllMemosBySQL() {
        String sql = "Select * FROM " + DBContract.Memos.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }
    public Cursor getAllUsersByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(DBContract.Memos.TABLE_NAME,null,null,null,null,null,null);
    }


}