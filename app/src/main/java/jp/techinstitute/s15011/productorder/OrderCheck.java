package jp.techinstitute.s15011.productorder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

public class OrderCheck extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_check);


	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_confirm_);
		dialog.setTitle("購入確認画");
		return dialog;
	}

}
