package com.kb.openapi;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kb.openapi.activity.PublicMaskStatusActivity;
import com.kb.openapi.beach_traffic.SearchForInformationRestrictedTrafficActivity;

import java.util.Set;
import java.util.TreeMap;

public class ListMainActivity extends ListActivity {

    private TreeMap<String, Intent> actions = new TreeMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addActionMap("1.공적마스크 판매처 및 재고 현황(Open API 종료) ", PublicMaskStatusActivity.class);
        addActionMap("2.전국 해수욕장 실시간 혼잡도", SearchForInformationRestrictedTrafficActivity.class);
        Set<String> keys = actions.keySet();
        String [] keyNames = keys.toArray(new String[0]);
        setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, keyNames));
    }
    private void addActionMap(String keyName, Class className) {
        actions.put(keyName, new Intent(this, className));
    }
    @Override
    protected void onListItemClick(ListView listView, View v, int position, long id) {
        String intentName = (String)listView.getItemAtPosition(position);
        startActivity(actions.get(intentName));
    }
}