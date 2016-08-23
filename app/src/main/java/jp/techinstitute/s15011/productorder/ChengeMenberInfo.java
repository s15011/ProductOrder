package jp.techinstitute.s15011.productorder;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ChengeMenberInfo extends AppCompatActivity {

    private MyHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chenge_menber_info);
        Button button = (Button) findViewById(R.id.btnConfirm);
        myHelper = new MyHelper(this);


        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                String err = "";

                TextView Last_name = (TextView) findViewById(R.id.editLastName);
                String last_name = Last_name.getText().toString();

                TextView First_name = (TextView) findViewById(R.id.editFirstName);
                String first_name = First_name.getText().toString();

                TextView Home_address = (TextView) findViewById(R.id.editAddress);
                String address = Home_address.getText().toString();

                TextView Password = (TextView) findViewById(R.id.editPassword);
                String password = Password.getText().toString();

                TextView Confirm_Password = (TextView) findViewById(R.id.Confirm_Password);
                String confirm_password = Confirm_Password.getText().toString();

                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                String prefectureid = spinner.getSelectedItem().toString();

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChengeMenberInfo.this);

                if (last_name.equals(err)) {
                    alertDialog.setMessage("未入力の項目があります");
                } else if (first_name.equals(err)) {
                    alertDialog.setMessage("未入力の項目があります");
                } else if (address.equals(err)) {
                    alertDialog.setMessage("未入力の項目があります");
                } else if (password.equals(err)) {
                    alertDialog.setMessage("未入力の項目があります");
                } else if (confirm_password.equals(err)) {
                    alertDialog.setMessage("未入力の項目があります");
                } else if (!password.equals(confirm_password)) {
                    alertDialog.setMessage("パスワードが一致していません");
                } else {
                    alertDialog.setTitle("以下の内容でよろしいですか?");
                    alertDialog.setMessage("名前: " + last_name + first_name + "\n"
                            + "都道府県: " + prefectureid + "\n"
                            + "住所: " + address);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    alertDialog.setNegativeButton("CANCEL", null);
                }
                alertDialog.create().show();
            }
        });
    }

    /*private String check(){
        String check_msg = "";
        ProDate old_data = new ProDate();
        old_data = serchDBdata(old_data);
        ProDate
    }*/

    private void set_Prodata(){
        ProDate data = new ProDate();
        data = serchDBdata(data);

        EditText set_lastname = (EditText)findViewById(R.id.editLastName);
        EditText set_firstname = (EditText)findViewById(R.id.editLastName);
        EditText set_prefectureId = (EditText)findViewById(R.id.spinner);
        EditText set_address = (EditText)findViewById(R.id.editAddress);
        EditText set_password = (EditText)findViewById(R.id.editPassword);

        set_lastname.setText(data.last_name);
        set_firstname.setText(data.last_name);
        set_prefectureId.setText(data.prefectureId);
        set_address.setText(data.address);
        set_password.setText(data.password);
    }

    private ProDate serchDBdata(ProDate data){
        String target = getEmail();

        String[] targetcolumns = {MyHelper.AccountColumns.lastName, MyHelper.AccountColumns.firstName, MyHelper.AccountColumns.prefectureId,
        MyHelper.AccountColumns.address, MyHelper.AccountColumns.password};
        String serachcond = MyHelper.AccountColumns.mailAddress + " = ?";
        String[] serachargs = {target};

        SQLiteDatabase db = myHelper.getReadableDatabase();

        Cursor cursor = db.query(MyHelper.ACCOUNT_TABLE_NAME, targetcolumns, serachcond, serachargs, null, null, null);

        while(cursor.moveToNext()){
            data.last_name = cursor.getString(0);
            data.first_name = cursor.getString(1);
            data.prefectureId = cursor.getString(2);
            data.address = cursor.getString(3);
            data.password = cursor.getString(4);
        }
        return data;
    }

    private boolean UpdataAccount(ProDate Accountdata){
        SQLiteDatabase db = myHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyHelper.AccountColumns.lastName,Accountdata.last_name);
        values.put(MyHelper.AccountColumns.firstName,Accountdata.first_name);
        values.put(MyHelper.AccountColumns.prefectureId,Accountdata.prefectureId);
        values.put(MyHelper.AccountColumns.address,Accountdata.address);
        values.put(MyHelper.AccountColumns.password,Accountdata.password);

        String target = getEmail();
        String serch = MyHelper.AccountColumns.mailAddress + " = ?";
        String[] args = {target};

        long ret;

        try{
            ret = db.update(myHelper.ACCOUNT_TABLE_NAME, values, serch, args);
        }finally {
            db.close();
        }
        if(ret == -1){
            return false;
        }else{
            return true;
        }
    }

    private ProDate getconf_ET(){
        ProDate data = new ProDate();

        EditText set_lastname = (EditText)findViewById(R.id.editLastName);
        EditText set_firstname = (EditText)findViewById(R.id.editFirstName);
        EditText set_prefectureid = (EditText)findViewById(R.id.spinner);
        EditText set_address = (EditText)findViewById(R.id.editAddress);
        EditText set_password = (EditText)findViewById(R.id.editPassword);

        data.last_name = set_lastname.getText().toString();
        data.first_name = set_firstname.getText().toString();
        data.address = set_address.getText().toString();
        data.password = set_password.getText().toString();
        data.prefectureId = set_prefectureid.getText().toString();

        return data;
    }

    private class ProDate{
        String last_name;
        String first_name;
        String address;
        String password;
        String prefectureId;
    }

    private String getEmail(){
        SharedPreferences Data = getSharedPreferences("Maildata", Context.MODE_PRIVATE);
        String Getdata = Data.getString("mail", "");
        return Getdata;
    }

}