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
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class iBuy extends Activity {

    private ArrayList<Item> items;
    private Integer current = 0;
    private Integer points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_buy);

        items = new ArrayList<>();

        addItem();
        addItem();
        addItem();

        ListView yourListView = (ListView) findViewById(R.id.listView);
        ItemAdapter customAdapter = new ItemAdapter(this, R.layout.item_layout, items);
        yourListView.setAdapter(customAdapter);

        Button addItem = (Button) findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addItem();

                ListView listView = (ListView) findViewById(R.id.listView);
                ((ItemAdapter) listView.getAdapter()).notifyDataSetChanged();
            }
        });
    }

    void addItem() {
        Item item = new Item(current++, "New Item", 1, 100);
        items.add(item);
    }

    void removeItem(Integer itemId) {
        Item item = getItemById(itemId);
        items.remove(item);

        points += item.rank;
    }

    Item getItemById(int id) {
        for(Item item : items) {
            if(item.id == id) {
                return item;
            }
        }
        return null;
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
        private Integer id;
        private Integer rank;

        public Item(Integer id, String label, int quantity, int rank) {
            this.label = label;
            this.quantity = quantity;
            this.id = id;
            this.rank = rank;
        }

        public String getLabel() {
            return this.label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Integer getQuantity() {
            if(quantity == null) {
                return 0;
            }
            return this.quantity;
        }

        public Integer getRank() {
            if(rank == null) {
                return 0;
            }
            return this.rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public void incrementQuantity() {
            this.quantity++;
        }

        public void decrementQuantity() {
            this.quantity--;
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
                TextView points = (TextView) v.findViewById(R.id.points);

                if(label != null) {
                    label.setText(item.getLabel());
                }

                if(quantity != null) {
                    quantity.setText(String.valueOf(item.getQuantity()));
                }

                if(points != null) {
                    points.setText(String.valueOf(item.getRank()));
                }

                Button increment = (Button) v.findViewById(R.id.incrementQuantity);
                increment.setTag(position);
                increment.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer itemId = (Integer) v.getTag();
                        Item item = getItemById(itemId);
                        item.incrementQuantity();

                        ListView listView = (ListView) findViewById(R.id.listView);
                        ((ItemAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });

                Button decrement = (Button) v.findViewById(R.id.decrementQuantity);
                decrement.setTag(position);
                decrement.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer itemId = (Integer) v.getTag();
                        Item item = getItemById(itemId);
                        item.decrementQuantity();

                        ListView listView = (ListView) findViewById(R.id.listView);
                        ((ItemAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });

                Button delete = (Button) v.findViewById(R.id.delete);
                delete.setTag(position);
                delete.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Integer itemId = (Integer) v.getTag();
                        removeItem(itemId);

                        ListView listView = (ListView) findViewById(R.id.listView);
                        ((ItemAdapter) listView.getAdapter()).notifyDataSetChanged();
                    }
                });
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
