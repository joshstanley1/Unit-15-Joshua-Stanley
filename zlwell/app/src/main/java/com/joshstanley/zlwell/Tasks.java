package com.joshstanley.zlwell;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Tasks extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final String FILENAME = "tasks.dat";

    Button btnAdd;
    EditText itemET;
    ListView itemLV;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter<String> adapter;
    Button btnHelp;


    public Tasks() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tasks, container, false);

        items = readData(getContext());
        btnAdd = view.findViewById(R.id.add_btn);
        btnAdd.setOnClickListener(this);
        itemET = view.findViewById(R.id.item_edit_text);
        itemLV = view.findViewById(R.id.items_list);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, items);
        itemLV.setAdapter(adapter);
        itemLV.setOnItemClickListener(this);

        btnHelp = view.findViewById(R.id.help_btn);
        btnHelp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
               builder.setTitle("Need some help?").setMessage(R.string.tasks_help_text);
               AlertDialog dialog = builder.create();
               dialog.show();
           }
        });


        return view;
    }

    @Override
    public void onClick(View view) {
        items.add(itemET.getText().toString());
        writeData(items, getContext());
        adapter.notifyDataSetChanged();
        itemET.setText("");
    }

    private ArrayList<String> readData(Context context){
        ArrayList<String> items = null;
        try{
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            items = (ArrayList<String>)ois.readObject();
        }
        catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            items = new ArrayList<>();
        }
        return items;
    }

    private void writeData(ArrayList<String> items, Context context){
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
        items.remove(position);
        adapter.notifyDataSetChanged();
        writeData(items, getContext());
        Toast.makeText(getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
    }
}