package org.prgrmmr.hermes;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private TextView lblMessage;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		lblMessage = (TextView) findViewById(R.id.message);
	}

	@Override
	public void onResume() {
		super.onResume();
		String msg = getIntent().getStringExtra("msg");
		lblMessage.setText(msg);
	}

}
