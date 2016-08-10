package jp.techinstitute.s15011.productorder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by s15004 on 16/08/09.
 */
public class MySQLiteOpenHelper  extends SQLiteOpenHelper{

	public MySQLiteOpenHelper(Context context) {
		// 任意のデータベースファイル名と、バージョンを指定する
		super(context, "itemname.db", null, 1);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table item_table ("
						+ "_id  integer primary key autoincrement not null, "
						+ "text_column text not null, "
						+ "num_column integer not null)" );
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

	}
}
