package jp.techinstitute.s15011.productorder;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateMenber extends AppCompatActivity {

    private CreateDatebase createDatebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createDatebase = new CreateDatebase(this);



        setContentView(R.layout.activity_create_menber);

        Button button = (Button)findViewById(R.id.btnEntry);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String err = "";

                final CreateMenberStr account = new CreateMenberStr();

                TextView Last_name = (TextView)findViewById(R.id.editLastName);
                account.last_name = Last_name.getText().toString();
                //Log.d("aaaa", last_name);

                TextView First_name = (TextView)findViewById(R.id.editFirstName);
                account.first_name = First_name.getText().toString();

                TextView Mail = (TextView)findViewById(R.id.editMail);
                account.mailaddress = Mail.getText().toString();

                TextView Home_Adress = (TextView)findViewById(R.id.editAdress);
                account.address = Home_Adress.getText().toString();

                TextView Password = (TextView)findViewById(R.id.editPassword);
                String password = Password.getText().toString();

                TextView Confirm_Password = (TextView)findViewById(R.id.Confirm_Password);
                String confirm_password = Confirm_Password.getText().toString();

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                account.prefectureid = spinner.getSelectedItem().toString();
                //Log.d("aaaa", item);


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(CreateMenber.this);

                if(account.last_name.equals(err)){
                    alertDialog.setMessage("未入力の項目があります");
                }else if(account.first_name.equals(err)){
                    alertDialog.setMessage("未入力の項目があります");
                }else if(account.mailaddress.equals(err)){
                    alertDialog.setMessage("未入力の項目があります");
                }else if(account.address.equals(err)){
                    alertDialog.setMessage("未入力の項目があります");
                }else if(password.equals(err)){
                    alertDialog.setMessage("未入力の項目があります");
                }else if(confirm_password.equals(err)){
                    alertDialog.setMessage("未入力の項目があります");
                } else if(!password.equals(confirm_password)){
                    alertDialog.setMessage("パスワードが一致していません");
                }else {
                    alertDialog.setTitle("以下の内容でよろしいですか?");
                    alertDialog.setMessage("名前: " + account.last_name + account.first_name + "\n"
                            + "Email: " + account.mailaddress + "\n"
                            + "都道府県: " + account.prefectureid + "\n"
                            + "住所: " + account.address);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            insertMenber(account);
                        }
                    });
                    alertDialog.setNegativeButton("CANCEL", null);
                }
                alertDialog.create().show();
            }
        });


    }
    private class CreateMenberStr{
        String first_name;
        String last_name;
        String prefectureid;
        String address;
        String mailaddress;
        String password;
    }

    private String insertMenber(CreateMenberStr item) {
        String err_msg = "";

        //SQLiteDatabaseオブジェクト取得
        SQLiteDatabase db_q = createDatebase.getWritableDatabase();


        // queryを呼び、検索を行う
        String where = CreateDatebase.AccountColumns.MailAddress + "=?";
        String[] args = {item.mailaddress};
        Cursor cursor = db_q.query(
                CreateDatebase.TABLE_NAME, null, where, args, null, null, null);


        //読込位置を先頭にする。trueの場合は結果1件以上
        if(cursor.moveToFirst()) {
            cursor.close();
            db_q.close();
            err_msg = "そのアドレスはすでに存在してます";
            return err_msg;
        }else{
            cursor.close();
            db_q.close();
        }

        SQLiteDatabase db = createDatebase.getWritableDatabase();

        //列に対応する値をセット
        ContentValues values = new ContentValues();

        values.put(CreateDatebase.AccountColumns.FirstName, item.first_name);
        values.put(CreateDatebase.AccountColumns.LastName, item.last_name);
        values.put(CreateDatebase.AccountColumns.PrefectureId, item.prefectureid);
        values.put(CreateDatebase.AccountColumns.Address, item.address);
        values.put(CreateDatebase.AccountColumns.MailAddress, item.mailaddress);
        values.put(CreateDatebase.AccountColumns.Password, item.password);

        // データベースに行を追加する
        long id = db.insert(CreateDatebase.TABLE_NAME, null, values);
        if (id == -1) {
            Log.d("Database", "行の追加に失敗");
        }
        db.close();

        return err_msg;
    }
}
