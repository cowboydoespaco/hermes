package org.prgrmmr.hermes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent intent = getIntent();
        if ((keyCode == KeyEvent.KEYCODE_BACK) && intent.getBooleanExtra("forceReturn", true)) {
           Intent messageListIntent = new Intent(this, MessageListActivity.class);
           this.finish();
           startActivity(messageListIntent);
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_show_id:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
