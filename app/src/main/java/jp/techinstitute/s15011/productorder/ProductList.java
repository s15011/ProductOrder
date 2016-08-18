package jp.techinstitute.s15011.productorder;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

/**
 * Created by s15004 on 16/08/16.
 */
/*public class ProductList extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {
	private MyHelper myHelper;


	private class ProductItem {
		int _id;
		String id;
		String name;
		int price;
		int stock;
	}

/*	private List<ProductItem> itemList;
	private ItemAdapter adapter;

	private void setProductData(){


		selectProductList();



	}

	private void selectProductList(){

		// 1. SQLiteDatabaseオブジェクトを取得
		SQLiteDatabase db = myHelper.getReadableDatabase();

		// 2. query()を呼び、検索を行う
		Cursor cursor =
				db.query(MyHelper.TABLE_NAME, null, null, null, null, null,
						MyHelper.Columns._ID + " ASC");

		// 3. 読込位置を先頭にする。falseの場合は結果0件
		if(!cursor.moveToFirst()){
			cursor.close();
			db.close();
			return;
		}
}*/
