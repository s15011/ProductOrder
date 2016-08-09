package jp.techinstitute.s15011.productorder;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ProductView extends AppCompatActivity implements View.OnClickListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //menu.add(0, 0, 0, "test");
        Log.d("test", "option");
        return super.onCreateOptionsMenu(menu);
        //return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if  (id == R.id.optLogout) {//ログアウトの処理

        }else if(id == R.id.optChengeAccontInfo) {//ChangeMenberinfoに飛ぶ

        }else if(id == R.id.optDeleateAccount) {//アカウント削除の処理

        }else if(id == R.id.optOrderCancel){//OrderCancelに移行
            return true;

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog1,
                (ViewGroup)findViewById(R.id.layout_root));

        // アラーとダイアログ を生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ダイアログタイトル");
        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // OK ボタンクリック処理
                // ID と PASSWORD を取得
                EditText id
                        = (EditText)layout.findViewById(R.id.customDlg_id);
                EditText pass
                        = (EditText)layout.findViewById(R.id.customDlg_pass);
                String strId   = id.getText().toString();
                String strPass = pass.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Cancel ボタンクリック処理
            }
        });
        builder.create().show();



        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        setContentView(R.layout.activity_product_view);
        Button btn = (Button)findViewById(R.id.btnTransition);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);//
        startActivity(i);

    }
}
