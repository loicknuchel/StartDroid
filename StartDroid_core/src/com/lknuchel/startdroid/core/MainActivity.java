package com.lknuchel.startdroid.core;

import com.knuchel.startdroid.core.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Context c;
	private Button sqliteButton;
	private Button contentproviderButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		c = getApplicationContext();
		setUp();
		onCLickValidate();
	}

	protected void setUp() {
		sqliteButton = (Button) findViewById(R.activity_main.sqlite);
		contentproviderButton = (Button) findViewById(R.activity_main.contentprovider);
	}

	private void onCLickValidate() {
		sqliteButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(c, Persistance_Sqlite_HostActivity.class);
				startActivity(i);
			}
		});

		contentproviderButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(c, Data_ContentProvider_Activity.class);
				startActivity(i);
			}
		});
	}
}