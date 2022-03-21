package by.bsuir.mobile.cat.service

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast
import by.bsuir.mobile.cat.model.CatStatistic

class DatabaseManager(var context: Context) {
    private val databaseHelper = DatabaseHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = databaseHelper.writableDatabase
    }

    fun insert(statistic: CatStatistic) {
        var cv = ContentValues().apply {
            put(COL_TIME, statistic.time)
            put(COL_SATIETY, statistic.satiety)
        }
        var result = db?.insert(TABLE_NAME, null, cv)
        if (result == (-1).toLong())
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    @SuppressWarnings("Range")
    fun read(): ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(TABLE_NAME, null,null,null,null,null,null)
        while (cursor?.moveToNext()!!) {
            val time = cursor.getString(cursor.getColumnIndex(COL_TIME))
            val satiety = cursor.getInt(cursor.getColumnIndex(COL_SATIETY))
            dataList.add("Time: $time  Satiety: $satiety\n")
        }
        cursor.close()
        return dataList
    }

    fun close() {
        databaseHelper.close()
    }
}