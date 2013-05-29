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
    List<Message> values;

    protected void populateListView() {
        values = MessageDataSource.getAllMessages(getApplicationContext());

        ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this, R.layout.list_item , values);
        setListAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateListView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        populateListView();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Message message = values.get(position);
        Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
        intent.putExtra("id", message.getId());
        intent.putExtra("msg", message.getContent());
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
