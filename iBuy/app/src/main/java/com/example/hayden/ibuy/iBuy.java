package com.example.hayden.ibuy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class iBuy extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_buy);

        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("Item 1", 1));
        items.add(new Item("Item 2", 3));
        items.add(new Item("Item 3", 2));

        ListView yourListView = (ListView) findViewById(R.id.listView);
        ItemAdapter customAdapter = new ItemAdapter(this, R.layout.item_layout, items);
        yourListView .setAdapter(customAdapter);

        Spinner ratingSpinner = (Spinner) findViewById(R.id.spinner);
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_i_buy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class Item {

        private String label;
        private Integer quantity;
        private Integer rating;

        public Item(String label, int quantity) {
            this.label = label;
            this.quantity = quantity;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getQuantity() {
            return this.quantity.toString();
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }

    private class ItemAdapter extends ArrayAdapter<Item> {

        public ItemAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }

        public ItemAdapter(Context context, int resource, List<Item> items) {
            super(context, resource, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.item_layout, null);
            }

            Item item = getItem(position);

            if(item != null) {
                TextView label = (TextView) v.findViewById(R.id.label);
                TextView quantity = (TextView) v.findViewById((R.id.quantity));

                if(label != null) {
                    label.setText(item.getLabel());
                }

                if(quantity != null) {
                    quantity.setText(item.getQuantity());
                }
            }

            return v;
        }
        public void buttonAboutClicked(View v) {
            startActivity(new Intent(getApplicationContext(), About.class));
        }

        public void buttonStartClicked(View v) {
            startActivity(new Intent(getApplicationContext(), iBuy.class));
        }
    }
}
