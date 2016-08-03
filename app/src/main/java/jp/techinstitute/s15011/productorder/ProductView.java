package jp.techinstitute.s15011.productorder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        if  (id == R.id.optCreateAccount) {
            Intent intent = new Intent(this, CreateMenber.class);
            startActivity(intent);

        }else if(id == R.id.optLogin){
            Toast.makeText(this, "ヘルプ！", Toast.LENGTH_SHORT).show();

            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CONTEXT_MENU);
        setContentView(R.layout.activity_product_view);



    }

    @Override
    public void onClick(View view) {

    }
}
