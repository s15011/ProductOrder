package jp.techinstitute.s15011.productorder;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {


	private class ProductItem {
		int _id;
		String id;
		String name;
		int price;
		int stock;
		String prefecture;
		boolean flag;

		void setCheckFlag(boolean checkflg) {
			this.flag = checkflg;
		}

		boolean getCheckFlag() {
			return flag;
		}
	}

	private List<ProductItem> itemList;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_check);

		MyHelper myHelper = new MyHelper(this);
		SQLiteDatabase db = myHelper.getWritableDatabase();

		//Cursor cursor = db.query("Columns", new String[]{"id", "name"},null,
		//      null, null, null, null);


		//Cursor cursor = db.query(MyHelper.TABLE_NAME, null, null,null, null, null, null);

		String text = "";
		String colm = MyHelper.Columns.ID;
		Cursor cursor = db.query(MyHelper.TABLE_NAME, new String[]{"id"},
				null, null, null, null, "id DESC");
		if (!cursor.moveToFirst()) {
			cursor.close();
			db.close();
			return;
		}
		int _idIndex = cursor.getColumnIndex(MyHelper.Columns._ID);
		int idindex = cursor.getColumnIndex(String.valueOf(MyHelper.Columns.ID));
		int nameindex = cursor.getColumnIndex(MyHelper.Columns.productName);
		int priceindex = cursor.getColumnIndex(MyHelper.Columns.PRICE);
		int stockindex = cursor.getColumnIndex(MyHelper.Columns.STOCK);
		int prefectureindex = cursor.getColumnIndex(MyHelper.Columns.PREFECTURE);


		itemList.removeAll(itemList);
		do {
			ProductItem item = new ProductItem();
			item._id = cursor.getInt(_idIndex);
			item.id = cursor.getString(idindex);
			item.name = cursor.getString(nameindex);
			item.price = cursor.getInt(priceindex);
			item.stock = cursor.getInt(stockindex);
			item.prefecture = cursor.getString(prefectureindex);

			itemList.add(item);

		} while (cursor.moveToNext());

		Cursor c = db.query(MyHelper.ACCOUNT_TABLE_NAME, new String[]{
				MyHelper.AccountColumns.firstName,
				MyHelper.AccountColumns.lastName,
				MyHelper.AccountColumns.prefectureId,
				MyHelper.AccountColumns.address,
				MyHelper.AccountColumns.mailAddress,
				MyHelper.AccountColumns.password
		}, null, null, null, null, null);

		if (!cursor.moveToFirst()) {
			cursor.close();
			db.close();
			return;
		}


			TextView textView1 = (TextView) findViewById(R.id.textView4);
			textView1.setText(text);
			if (!cursor.moveToFirst()) {
				cursor.close();
				db.close();
				return;
			}


	}
}




	//myHelper db = new MyHelper(getApplicationContext());

        /*Cursor cursor = db.query(
                "list_table", new String[] {"ID","productname", "price", "stock","prefecture PREFECTURE"},
                null, null, null, null, "_id DESC");



        if(!cursor.moveToFirst()){
            cursor.close();
            db.close();
            return;
        }

        int ProductId = cursor.getColumnIndex(MyHelper.Columns.ID);
        int ProductName = cursor.getColumnIndex(MyHelper.Columns.productName);
        int ProductPrice = cursor.getColumnIndex(MyHelper.Columns.PRICE);
        int ProductStock = cursor.getColumnIndex(MyHelper.Columns.STOCK);
        int productPrefecture = cursor.getColumnIndex(MyHelper.Columns.PREFECTURE);

        // 6. Cursorを閉じる
        cursor.close();

        // 7. データベースを閉じる
        db.close();*/



        /*Button btn = (Button) findViewById(R.id.btnBuy);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView editView = (TextView) findViewById(R.id.editText);
                TextView editView2 = (TextView) findViewById(R.id.editText2);
                TextView editView3 = (TextView) findViewById(R.id.editText3);
               // TextView editView4 = (TextView) findViewById(R.id.editText4);

                TextView book_price = (TextView) findViewById(R.id.textView5);
                TextView pen_price = (TextView) findViewById(R.id.textView7);
                TextView note_price = (TextView) findViewById(R.id.textView9);
                TextView pencil_price = (TextView) findViewById(R.id.textView11);
                String getBook_p = book_price.getText().toString();
                String getpen_p = pen_price.getText().toString();
                String getnote_p = note_price.getText().toString();
                String getpencil_p = pencil_price.getText().toString();


                String a = editView.getText().toString();
                String b = editView2.getText().toString();
                String c = editView3.getText().toString();
               // String d = editView4.getText().toString();

                String err = "";

                /*if (a.equals(err)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" errer");
                    builder.setMessage("個数を入力してください。");
                    builder.setNegativeButton("戻る", null);
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" 購入しますか？");
                    builder.setMessage("本 " + getBook_p + ": " + a + "個" + "\n" +
                            "鉛筆 " + getpen_p + ": " + b + "個" + "\n" +
                            "ノート " + getnote_p + ": " + c + "個" + "\n" +
                            "筆箱 " + getpencil_p + ": " + d + "個");
                    builder.setPositiveButton("OK", null);
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }


                if (b.equals(err)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" errer");
                    builder.setMessage("個数を入力してください。");
                    builder.setNegativeButton("戻る", null);
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" 購入しますか？");
                    builder.setMessage("本 " + getBook_p + ": " + a + "個" + "\n" +
                            "鉛筆 " + getpen_p + ": " + b + "個" + "\n" +
                            "ノート " + getnote_p + ": " + c + "個" + "\n" +
                            "筆箱 " + getpencil_p + ": " + d + "個");
                    builder.setPositiveButton("OK", null);
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }


                if (c.equals(err)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" errer");
                    builder.setMessage("個数を入力してください。");
                    builder.setNegativeButton("戻る", null);
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" 購入しますか？");
                    builder.setMessage("本 " + getBook_p + ": " + a + "個" + "\n" +
                            "鉛筆 " + getpen_p + ": " + b + "個" + "\n" +
                            "ノート " + getnote_p + ": " + c + "個" + "\n" +
                            "筆箱 " + getpencil_p + ": " + d + "個");
                    builder.setPositiveButton("OK", null);
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }

                if (d.equals(err)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" errer");
                    builder.setMessage("個数を入力してください。");
                    builder.setNegativeButton("戻る", null);
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle(" 以下の注文をキャンセルします");
                    builder.setMessage("本 " + getBook_p + ": " + a + "個" + "\n" +
                            "鉛筆 " + getpen_p + ": " + b + "個" + "\n" +
                            "ノート " + getnote_p + ": " + c + "個" + "\n" +
                            "筆箱 " + getpencil_p + ": " + d + "個");
                    builder.setPositiveButton("OK", null);
                    builder.setNegativeButton("Cancel", null);
                    builder.show();
                }


            }


        });*/



    //}


//}
