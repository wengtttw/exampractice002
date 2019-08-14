package sg.edu.np.s10187744.exampractice002;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final String TAG = "MyDBHandler";
    public static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "database.db";
    public static final String TABLE_NOTES = "Note";
    public static final String COLUMN_NOTEID = "NoteID";
    public static final String COLUMN_NOTETEXT = "NoteText";

    public MyDBHandler(Context c, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(c, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NOTES + " ("
                +  COLUMN_NOTEID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTETEXT + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public void addNote(String note){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTETEXT, note);

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    public ArrayList<String> getAllNotes()
    {
        ArrayList<String> list = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NOTES,
                null);

        while(c.moveToNext())
        {
            String notetext;
            notetext = (c.getString(1));
            list.add(notetext);
        }

        c.close();
        db.close();
        return list;
    }
}
