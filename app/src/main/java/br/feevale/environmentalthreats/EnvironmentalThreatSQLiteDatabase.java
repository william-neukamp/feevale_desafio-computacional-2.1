package br.feevale.environmentalthreats;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentalThreatSQLiteDatabase {
    Context context;
    public static final String DATABASE_NAME = "et.db";
    public static final Integer DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public EnvironmentalThreatSQLiteDatabase( Context context){
        this.context = context;
        this.db = new EnvironmentalThreatSQLiteDatabaseHelper().getWritableDatabase();
    }

    public static class EnvironmentalThreatsTable implements BaseColumns{
        public static final String TABLE_NAME = "EnvironmentalThreats";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_ADDRESS = "address";

        public static String createTable(){
            String sql = "CREATE TABLE " + TABLE_NAME + " ( "    +
                    _ID                  + " INTEGER PRIMARY KEY AUTOINCREMENT, " + //teste
                    COLUMN_ADDRESS       + " TEXT, "             +
                    COLUMN_DATE          + " TEXT, "             +
                    COLUMN_DESCRIPTION   + " TEXT )";
            return sql;
        }
    }

    public Long insertThreat(EnvironmentalThreat threat){
        ContentValues values = new ContentValues();
        values.put(EnvironmentalThreatsTable.COLUMN_ADDRESS, threat.getAddress());
        values.put(EnvironmentalThreatsTable.COLUMN_DATE, threat.getDate());
        values.put(EnvironmentalThreatsTable.COLUMN_DESCRIPTION, threat.getDescription());

        return this.db.insert(EnvironmentalThreatsTable.TABLE_NAME, null, values);
    }

    public Integer updateThreat(EnvironmentalThreat threat){
        Long id = threat.getId();
        String args[] = {id.toString()};

        ContentValues values = new ContentValues();
        values.put(EnvironmentalThreatsTable.COLUMN_ADDRESS, threat.getAddress());
        values.put(EnvironmentalThreatsTable.COLUMN_DATE, threat.getDate());
        values.put(EnvironmentalThreatsTable.COLUMN_DESCRIPTION, threat.getDescription());

        return this.db.update(EnvironmentalThreatsTable.TABLE_NAME, values, EnvironmentalThreatsTable._ID + "=?", args);
    }

    public EnvironmentalThreat selectThreat(Long id){
        String cols[] = {EnvironmentalThreatsTable._ID, EnvironmentalThreatsTable.COLUMN_ADDRESS, EnvironmentalThreatsTable.COLUMN_DATE, EnvironmentalThreatsTable.COLUMN_DESCRIPTION};
        String args[] = {id.toString()};
        Cursor cursor = db.query(EnvironmentalThreatsTable.TABLE_NAME, cols, EnvironmentalThreatsTable._ID+"=?", args, null, null, EnvironmentalThreatsTable._ID);

        if (cursor.getCount() != 1)
            return null;

        cursor.moveToNext();
        EnvironmentalThreat threat = new EnvironmentalThreat();
        threat.setId(cursor.getLong(cursor.getColumnIndex(EnvironmentalThreatsTable._ID)));
        threat.setAddress(cursor.getString(cursor.getColumnIndex(EnvironmentalThreatsTable.COLUMN_ADDRESS)));
        threat.setDate(cursor.getString(cursor.getColumnIndex(EnvironmentalThreatsTable.COLUMN_DATE)));
        threat.setDescription(cursor.getString(cursor.getColumnIndex(EnvironmentalThreatsTable.COLUMN_DESCRIPTION)));

        return threat;
    }

    public List<EnvironmentalThreat> selectThreats(){
        String cols[] = {EnvironmentalThreatsTable._ID, EnvironmentalThreatsTable.COLUMN_ADDRESS, EnvironmentalThreatsTable.COLUMN_DATE, EnvironmentalThreatsTable.COLUMN_DESCRIPTION};
        Cursor cursor = db.query(EnvironmentalThreatsTable.TABLE_NAME, cols, null, null, null, null, null/*EnvironmentalThreatsTable._ID*/);

        List<EnvironmentalThreat> threats = new ArrayList<>();
        EnvironmentalThreat threat;

        while (cursor.moveToNext()){
            threat = new EnvironmentalThreat();
            threat.setId(cursor.getLong(cursor.getColumnIndex(EnvironmentalThreatsTable._ID)));
            threat.setAddress(cursor.getString(cursor.getColumnIndex(EnvironmentalThreatsTable.COLUMN_ADDRESS)));
            threat.setDate(cursor.getString(cursor.getColumnIndex(EnvironmentalThreatsTable.COLUMN_DATE)));
            threat.setDescription(cursor.getString(cursor.getColumnIndex(EnvironmentalThreatsTable.COLUMN_DESCRIPTION)));
            threats.add(threat);
        }

        return threats;
    }

    public Integer deleteThreat(EnvironmentalThreat threat){
        Long id = threat.getId();
        String args[] = {id.toString()};
        return db.delete(EnvironmentalThreatsTable.TABLE_NAME, EnvironmentalThreatsTable._ID + "=?", args);
    }

    private class EnvironmentalThreatSQLiteDatabaseHelper extends SQLiteOpenHelper{

        public EnvironmentalThreatSQLiteDatabaseHelper(){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(EnvironmentalThreatsTable.createTable());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + EnvironmentalThreatsTable.TABLE_NAME);
            onCreate(db);
        }
    }

}
