package com.android.demo.bmi_3_1.menu;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		findViews();
		setListeners();
		registerForContextMenu(calcbutton);
	}

	private Button calcbutton;
	private EditText fieldheight;
	private EditText fieldweight;
	private TextView view_result;
	private TextView view_suggest;

	private void findViews() {
		calcbutton = (Button) findViewById(R.id.submit);
		fieldheight = (EditText) findViewById(R.id.height);
		fieldweight = (EditText) findViewById(R.id.weight);
		view_result = (TextView) findViewById(R.id.result);
		view_suggest = (TextView) findViewById(R.id.suggest);
	}

	// Listen for button clicks
	private void setListeners() {
		calcbutton.setOnClickListener(calcBMI);
	}

	private Button.OnClickListener calcBMI = new Button.OnClickListener() {
		public void onClick(View v) {
			DecimalFormat nf = new DecimalFormat("0.00");

			double height = Double
					.parseDouble(fieldheight.getText().toString()) / 100;
			double weight = Double
					.parseDouble(fieldweight.getText().toString());
			double BMI = weight / (height * height);
			// Present result
			view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));

			// Give health advice
			view_suggest = (TextView) findViewById(R.id.suggest);
			if (BMI > 25) {
				view_suggest.setText(R.string.advice_heavy);
			} else if (BMI < 20) {
				view_suggest.setText(R.string.advice_light);
			} else {
				view_suggest.setText(R.string.advice_average);
			}
		}
	};
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		onCreateMenu(menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		onItemSelected(item);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo)
	{
		onCreateMenu(menu);
		super.onCreateContextMenu(menu, v, menuInfo);
		
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		onItemSelected(item);
		return super.onContextItemSelected(item);
	}
	
	protected static final int MENU_ABOUT = Menu.FIRST;
	protected static final int MENU_QUIT = Menu.FIRST+1;
	protected static final int SUBMENU_ITEM1 = Menu.FIRST+11;
	protected static final int SUBMENU_ITEM2 = Menu.FIRST+12;
	protected static final int SUBMENU_ITEM3 = Menu.FIRST+13;
	
	private void onCreateMenu(Menu menu){
		menu.add(0,MENU_ABOUT,0,"關於...");
		menu.add(0,MENU_QUIT,0,"結束");
		SubMenu submenu = menu.addSubMenu("子選單");
		submenu.add(0, SUBMENU_ITEM1, 0,"子項目1");
		submenu.add(0, SUBMENU_ITEM2, 0,"子項目2");
		submenu.add(0, SUBMENU_ITEM3, 0,"子項目3");
	}
	
	private void onItemSelected(MenuItem item){
		
		switch(item.getItemId()){
		case MENU_ABOUT:
			Toast.makeText(Bmi.this, "關於...", Toast.LENGTH_SHORT).show();
			break;
		case MENU_QUIT:
			finish();
			break;
		case SUBMENU_ITEM1:
		case SUBMENU_ITEM2:
		case SUBMENU_ITEM3:
			Toast.makeText(Bmi.this, item.getTitle(), Toast.LENGTH_SHORT).show();
			break;
		}
	}
	
}
