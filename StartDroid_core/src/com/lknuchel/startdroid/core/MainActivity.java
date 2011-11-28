package com.lknuchel.startdroid.core;

import com.knuchel.startdroid.core.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/*
 * http://www.iconarchive.com/show/softdimension-icons-by-benjigarner/MS-Word-2-icon.html
 * http://www.iconarchive.com/show/softdimension-icons-by-benjigarner/Excel-icon.html
 * http://www.iconarchive.com/show/softdimension-icons-by-benjigarner/PowerPoint-icon.html
 * http://www.iconarchive.com/show/softdimension-icons-by-benjigarner/Outlook-icon.html
 */
public class MainActivity extends Activity {
	private Context c;
	private Button sqliteButton;
	private Button contentproviderButton;
	private Button customListViewButton;

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
		customListViewButton = (Button) findViewById(R.activity_main.customlistview);
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

		customListViewButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(c, Ui_List_ListViewCustom.class);
				startActivity(i);
			}
		});
	}
}