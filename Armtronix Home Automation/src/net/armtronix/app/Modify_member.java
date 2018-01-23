package net.armtronix.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Modify_member extends Activity implements OnClickListener {

	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	Button edit_bt, delete_bt;
	Spinner dropdown;
	String drop_down_string;
	long member_id;

	SQLController dbcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_member);

		dbcon = new SQLController(this);
		dbcon.open();

		et1 = (EditText) findViewById(R.id.edit_mem_id);
		et2 = (EditText) findViewById(R.id.edit_mem_ip);
		et3 = (EditText) findViewById(R.id.edit_mem_port);
		et4 = (EditText) findViewById(R.id.edit_mem_board);
		et4.setEnabled(false);
		edit_bt = (Button) findViewById(R.id.update_bt_id);
		delete_bt = (Button) findViewById(R.id.delete_bt_id);
		
		Intent i = getIntent();
		String memberID = i.getStringExtra("memberID");
		String memberName = i.getStringExtra("memberName");
		String memberIp = i.getStringExtra("memberIp");
		String memberPort = i.getStringExtra("memberPort");
		String memberBoard = i.getStringExtra("memberBoard");
		
         member_id = Long.parseLong(memberID);

		
		et1.setText(memberName);
		et2.setText(memberIp);
		et3.setText(memberPort);
		et4.setText(memberBoard);
		edit_bt.setOnClickListener(this);
		delete_bt.setOnClickListener(this);
	    dropdown = (Spinner) findViewById(R.id.spinner2);
		//create a list of items for the spinner.
		String[] items = new String[]{"Wifi Single Relay", "Wifi Three Relay", "Wifi Four Relay","Wifi Eight Relay"};
		//create an adapter to describe how the items are displayed, adapters are used in several places in android.
		//There are multiple variations of this, but this is the basic variant.
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
		//set the spinners adapter to the previously created one.
		dropdown.setAdapter(adapter);
		//dropdown.setSelection(2);
		//dropdown.setSelected(this);
		String item_1="Wifi_Single_Relay";
		String item_2="Wifi_Three_Relay";
		String item_3="Wifi_Four_Relay";
		String item_4="Wifi_Eight_Relay";
		
		if(item_1.equals(memberBoard))
		{
	    dropdown.setSelection(0);
		}
		else if(item_2.equals(memberBoard))
		{
		dropdown.setSelection(1);
		}
		else if(item_3.equals(memberBoard))
		{
		dropdown.setSelection(2);
		}
		else if(item_4.equals(memberBoard))
		{
		dropdown.setSelection(3);
		}
		
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
		

		
		
		
		

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.update_bt_id:
			String memName_upd = et1.getText().toString();
			String memIp_upd = et2.getText().toString();
			String memPort_upd = et3.getText().toString();
			String memBoard_upd = et4.getText().toString();
			dbcon.updateData(member_id, memName_upd,memIp_upd,memPort_upd,memBoard_upd);
			this.returnHome();
			break;

		case R.id.delete_bt_id:
			dbcon.deleteData(member_id);
			this.returnHome();
			break;
		}
	}


	public void returnHome() {

		Intent home_intent = new Intent(getApplicationContext(),
				MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

		startActivity(home_intent);
	}

}
