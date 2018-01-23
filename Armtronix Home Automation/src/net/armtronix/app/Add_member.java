package net.armtronix.app;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Add_member extends Activity implements OnClickListener {
	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	Spinner dropdown;
	String drop_down_string;
	Button add_bt, read_bt;
	SQLController dbcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_member);
		et1 = (EditText) findViewById(R.id.member_et_id);
		et2 = (EditText) findViewById(R.id.member_et_ip);
		et3 = (EditText) findViewById(R.id.member_et_port);
		et4 = (EditText) findViewById(R.id.member_et_board);
		et4.setEnabled(false);
		
	    dropdown = (Spinner) findViewById(R.id.spinner1);
		//create a list of items for the spinner.
		String[] items = new String[]{"Wifi Single Relay", "Wifi Three Relay", "Wifi Four Relay","Wifi Eight Relay"};
		//create an adapter to describe how the items are displayed, adapters are used in several places in android.
		//There are multiple variations of this, but this is the basic variant.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
		//set the spinners adapter to the previously created one.
		dropdown.setAdapter(adapter);
		//dropdown.setSelected(this);
		dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {
	        @Override
	        public void onItemSelected(AdapterView<?> parent, View view,
	                int position, long id) {
	        	 switch (position) {
	            case 0:
	            	drop_down_string="Wifi_Single_Relay";
	            	et4.setText(drop_down_string);
	                // Whatever you want to happen when the first item gets selected
	                break;
	            case 1:
	            	drop_down_string="Wifi_Three_Relay";
	            	et4.setText(drop_down_string);
	                // Whatever you want to happen when the second item gets selected
	                break;
	            case 2:
	            	drop_down_string="Wifi_Four_Relay";
	            	et4.setText(drop_down_string);
	                // Whatever you want to happen when the thrid item gets selected
	                break;
	            case 3:
	            	drop_down_string="Wifi_Eight_Relay";
	            	et4.setText(drop_down_string);
	                // Whatever you want to happen when the thrid item gets selected
	                break;
	
	        }
	           
	        }

	        @Override
	        public void onNothingSelected(AdapterView<?> parent) {
	            // TODO Auto-generated method stub
	        }});
		add_bt = (Button) findViewById(R.id.add_bt_id);
        
		dbcon = new SQLController(this);
		dbcon.open();
		add_bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.add_bt_id:
			String name = et1.getText().toString();
			String ip = et2.getText().toString();
			String port = et3.getText().toString();
			String board =drop_down_string;
			//String board = et4.getText().toString();
			dbcon.insertData(name,ip,port,board);
			Intent main = new Intent(Add_member.this, MainActivity.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(main);
			break;

		default:
			break;
		}
	}

}
