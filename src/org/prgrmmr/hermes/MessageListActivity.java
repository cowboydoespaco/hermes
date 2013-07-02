package org.prgrmmr.hermes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MessageListActivity extends ListActivity {

    List<Message> messages;

    protected void populateListView() {
        messages = MessageDataSource.getAllMessages(getApplicationContext());

        ArrayList<Map<String, String>> mapList = buildMapList(messages);

        String[] data = { "sender", "date" ,"message" }; //these must be in order
        int[] views = { R.id.txtSender, R.id.txtDate, R.id.txtPreviewText };

        SimpleAdapter adapter = new SimpleAdapter(this, mapList, R.layout.list_item , data, views);
        setListAdapter(adapter);
    }

    private ArrayList<Map<String, String>> buildMapList(List<Message> messages) {
        return null; //TODO
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
        Message message = messages.get(position);
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
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_list_show_id:
                Intent mainIntent = new Intent(this, MainActivity.class);
                startActivity(mainIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
