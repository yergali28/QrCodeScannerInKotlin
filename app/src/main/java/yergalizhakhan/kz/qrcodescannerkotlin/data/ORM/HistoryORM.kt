package yergalizhakhan.kz.qrcodescannerkotlin.data.ORM

import android.content.Context
import android.database.Cursor
import android.util.Log
import yergalizhakhan.kz.qrcodescannerkotlin.data.DatabaseWrapper
import yergalizhakhan.kz.qrcodescannerkotlin.domain.History
import java.util.ArrayList

class HistoryORM: InterfaceORM<History> {

    private val TAG = "HistoryORM"

    private val TABLE_NAME = "history"
    private val COMMA_SEPARATOR = ", "

    private val COLUMN_ID_TYPE = "integer PRIMARY KEY AUTOINCREMENT"
    private val COLUMN_ID = "id"

    private val COLUMN_CONTEXT_TYPE = "TEXT"
    private val COLUMN_CONTEXT = "context"

    private val COLUMN_DATE_TYPE = "TEXT"
    private val COLUMN_DATE = "date"

    val SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
            COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEPARATOR +
            COLUMN_DATE + " " + COLUMN_DATE_TYPE + COMMA_SEPARATOR + COLUMN_CONTEXT + " " + COLUMN_CONTEXT_TYPE + ")"

    val SQL_DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

    override fun cursorToObject(cursor: Cursor): History {
        return History(
                cursor.getInt(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DATE)),
                cursor.getString(cursor.getColumnIndex(COLUMN_CONTEXT)))
    }

    override fun add(context: Context, t: History) {
        val databaseWrapper = DatabaseWrapper(context)
        val database = databaseWrapper.readableDatabase

        val query = "INSERT INTO " + TABLE_NAME + "(" + COLUMN_DATE + ", " + COLUMN_CONTEXT + ") VALUES ( '" + t.date + "', '" + t.context + "' )"
        database.execSQL(query)
        database.close()
    }

    override fun clearAll(context: Context) {
        val databaseWrapper = DatabaseWrapper(context)
        val database = databaseWrapper.getReadableDatabase()
        database.delete(TABLE_NAME, null, null)
    }

    override fun getAll(context: Context): MutableList<History> {
        val databaseWrapper = DatabaseWrapper(context)
        val database = databaseWrapper.readableDatabase
        val historyList = ArrayList<History>()

        val cursor = database.rawQuery("SELECT * FROM $TABLE_NAME", null)
        try {
            if (cursor!!.moveToFirst()) {
                do {
                    val h = cursorToObject(cursor)
                    historyList.add(h)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.d(TAG, "Error while trying to get history from database")
        } finally {
            if (cursor != null && !cursor.isClosed) {
                cursor.close()
            }
        }
        database.close()

        return historyList
    }

}