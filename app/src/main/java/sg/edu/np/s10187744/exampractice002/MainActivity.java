package sg.edu.np.s10187744.exampractice002;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> data;
    ArrayAdapter<String> adapter;
    String inputText;
    MyDBHandler db = new MyDBHandler((this), null, null, 2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        data = db.getAllNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    public void onNoteDialog(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        View dialog = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.notedialog, null, false);
        alert.setView(dialog);
        final EditText newnote = dialog.findViewById(R.id.note);
        data = db.getAllNotes();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
        alert.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                inputText = newnote.getText().toString();
                data.add(inputText);
                db.addNote(inputText);
                adapter.notifyDataSetChanged();
                dialogInterface.dismiss();
            }
        });
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                dialogInterface.cancel();
            }
        });

        alert.show();
    }
}


/*FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                View dialog = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.notedialog, null, false);
                alert.setView(dialog);
                final EditText newnote = dialog.findViewById(R.id.note);
                alert.setPositiveButton("ADD", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        inputText = newnote.getText().toString();
                        data.add(inputText);
                        db.addNote(inputText);
                        adapter.notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });
                alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });

                alert.show();
            }
        });*/

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }*/