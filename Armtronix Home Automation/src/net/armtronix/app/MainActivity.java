package net.armtronix.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button addmem_bt;
	ListView lv;
	SQLController dbcon;
	TextView memID_tv, memName_tv, memIp_tv, memPort_tv, memBoard_tv;
	String Class_Name="net.armtronix.app.";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbcon = new SQLController(this);
		dbcon.open();
		addmem_bt = (Button) findViewById(R.id.addmem_bt_id);
		lv = (ListView) findViewById(R.id.memberList_id);
		// onClickListiner for addmember Button
		addmem_bt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent add_mem = new Intent(MainActivity.this, Add_member.class);
				startActivity(add_mem);
			}
		});

		// Attach The Data From DataBase Into ListView Using Crusor Adapter
		Cursor cursor = dbcon.readData();
		String[] from = new String[] { DBhelper.MEMBER_ID, DBhelper.MEMBER_NAME, DBhelper.MEMBER_IP,DBhelper.MEMBER_PORT,DBhelper.MEMBER_BOARD };
		int[] to = new int[] { R.id.member_id, R.id.member_name, R.id.member_ip, R.id.member_port, R.id.member_board};
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				MainActivity.this, R.layout.view_member_entry, cursor, from, to);

		adapter.notifyDataSetChanged();
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				memID_tv = (TextView) view.findViewById(R.id.member_id);
				memName_tv = (TextView) view.findViewById(R.id.member_name);
				memIp_tv = (TextView) view.findViewById(R.id.member_ip);
				memPort_tv = (TextView) view.findViewById(R.id.member_port);
				memBoard_tv = (TextView) view.findViewById(R.id.member_board);
				String memberID_val = memID_tv.getText().toString();
				String memberName_val = memName_tv.getText().toString();
				String memberIp_val=memIp_tv.getText().toString();;
				String memberPort_val=memPort_tv.getText().toString();;
				String memberBoard_val=Class_Name+(memBoard_tv.getText().toString());
				Intent wifi_intent = null;
				try {
				  Class<?> c = Class.forName(memberBoard_val);
				  wifi_intent = new Intent(getApplicationContext(),c);
				  wifi_intent.putExtra("memberPort", memberPort_val);
					wifi_intent.putExtra("memberIp", memberIp_val);
				    startActivity(wifi_intent);
				} catch (ClassNotFoundException ignored) {
				}
			}
		});

		// OnCLongLickListiner For List Items
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				memID_tv = (TextView) view.findViewById(R.id.member_id);
				memName_tv = (TextView) view.findViewById(R.id.member_name);
				memIp_tv = (TextView) view.findViewById(R.id.member_ip);
				memPort_tv = (TextView) view.findViewById(R.id.member_port);
				memBoard_tv = (TextView) view.findViewById(R.id.member_board);
				String memberID_val = memID_tv.getText().toString();
				String memberName_val = memName_tv.getText().toString();
				String memberIp_val=memIp_tv.getText().toString();;
				String memberPort_val=memPort_tv.getText().toString();;
				String memberBoard_val=memBoard_tv.getText().toString();;
				Intent modify_intent = new Intent(getApplicationContext(),
						Modify_member.class);
				modify_intent.putExtra("memberBoard", memberBoard_val);
				modify_intent.putExtra("memberPort", memberPort_val);
				modify_intent.putExtra("memberIp", memberIp_val);
				modify_intent.putExtra("memberName", memberName_val);
				modify_intent.putExtra("memberID", memberID_val);

				startActivity(modify_intent);
				return true;
			}
		});

	} // create end
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.option_menu, menu);
	        return super.onCreateOptionsMenu(menu);
	        //return true;
	    }
 public boolean onOptionsItemSelected(MenuItem item) 
 {
 	switch (item.getItemId()) 
 	{
     case R.id.about:
         // Ensure this device is discoverable by others
        // ensureDiscoverable();
     	Intent aboutus=new Intent(this, About.class);
     	startActivity(aboutus);
         return true;
     }
     return super.onOptionsItemSelected(item);
     //return false;
 }
}// class end
