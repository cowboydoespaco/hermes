package org.prgrmmr.hermes;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MessageListActivity extends ListActivity {

    private MessageDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        datasource = new MessageDataSource(this);
        datasource.open();

        List<Message> values = datasource.getAllMessages();

        datasource.close();

        ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this, R.layout.list_item , values);
        setListAdapter(adapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String message = ((TextView) v).getText().toString();
        Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
        intent.putExtra("msg", message);
        intent.putExtra("forceReturn", false);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_list, menu);
        return true;
    }

}
