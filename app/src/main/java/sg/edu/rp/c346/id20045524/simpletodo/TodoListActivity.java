package sg.edu.rp.c346.id20045524.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoListActivity extends AppCompatActivity {
    EditText etNewTodo;
    Button btnAdd, btnClearAll, btnDelete, btnBack;
    ListView lvToDo;
    ArrayList<String> alTodo;
    ArrayAdapter<String> aaTodo;
    Spinner spnTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        etNewTodo = findViewById(R.id.editNewTodo);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClearAll = findViewById(R.id.buttonClearItem);
        btnDelete = findViewById(R.id.buttonDeleteItem);
        lvToDo = findViewById(R.id.listViewTodo);
        spnTodo = findViewById(R.id.spinnerTodo);
        btnBack = findViewById(R.id.buttonBack);

        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter<>(TodoListActivity.this,
                android.R.layout.simple_list_item_1, alTodo);
        lvToDo.setAdapter(aaTodo);



        spnTodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position){
                    case 0:
                        //Your code for item 1 selected
                        etNewTodo.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        //Your code for item 2 selected
                        etNewTodo.setHint("Type in the index of the task to be removed");
                        btnDelete.setEnabled(true);
                        btnAdd.setEnabled(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = etNewTodo.getText().toString();
                if (newTodo.equals("")){ //check if input is empty
                    Toast.makeText(TodoListActivity.this,
                            "Input a task to add", Toast.LENGTH_SHORT).show();
                } else {
                    alTodo.add(newTodo);
                }
                aaTodo.notifyDataSetChanged();
                etNewTodo.setText(null);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTodo.isEmpty()){ //checks if arraylist is empty
                    Toast.makeText(TodoListActivity.this,
                            "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                } else {
                    if (etNewTodo.getText().toString().isEmpty()){ //check if input is empty
                        Toast.makeText(TodoListActivity.this,
                                "Input a task index to remove", Toast.LENGTH_SHORT).show();
                    } else {
                        int pos = Integer.parseInt(etNewTodo.getText().toString());
                        if(pos > alTodo.size()-1 || pos < alTodo.size()-alTodo.size()){ //check if the input is within range of arraylist
                            Toast.makeText(TodoListActivity.this,
                                    "Wrong index number", Toast.LENGTH_SHORT).show();
                        } else {
                            alTodo.remove(pos);
                        }
                    }
                    aaTodo.notifyDataSetChanged();
                }
            }
        });

        btnClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}