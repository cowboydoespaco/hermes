package org.prgrmmr.hermes;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MessageListActivity extends Activity {

    private MessageDataSource datasource;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        listView = (ListView) findViewById(R.id.messageListView);

        datasource = new MessageDataSource(this);
        datasource.open();

        List<Message> values = datasource.getAllMessages();

        ArrayAdapter<Message> adapter = new ArrayAdapter<Message>(this, R.layout.activity_message, R.id.message, values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String message = ((TextView) view).getText().toString();

                Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
                intent.putExtra("msg", message);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.message_list, menu);
        return true;
    }

}
