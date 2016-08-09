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
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(
                LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.dialog1,
                (ViewGroup)findViewById(R.id.layout_root));

        // アラーとダイアログ を生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ログイン");
        builder.setView(layout);


        builder.create().show();

        Button btn1 =(Button)findViewById(R.id.optCreateAccount);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("YOGI", "GIYO");

            }
        });


        //requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        setContentView(R.layout.activity_product_view);
        Button btn = (Button)findViewById(R.id.btnTransition);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);//OrderCheckに移動
        startActivity(i);

    }
}
