package eu.faircode.netguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

public class ActivityPro3 extends AppCompatActivity {

    private Button Add_condition;
    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private ArrayList<String> mData = new ArrayList<>();
    private Spinner Spinner_protocol1;
    private EditText Text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro3);


        Spinner_protocol1 = (Spinner) findViewById(R.id.spinner_protocol1);

        Text1 = (EditText) findViewById(R.id.editText_protocol1);

        Spinner_protocol1.setOnItemSelectedListener(spinnerlistener1);

        Add_condition = (Button) findViewById(R.id.add_condition);

        //for (int i=0;i<10;i++){}

        mData.add("");


        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);

        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        //recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


        adapter = new MyAdapter(mData);

        recycler_view.setAdapter(adapter);


        Add_condition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                adapter.addItem("");

            }
        });


    }


    AdapterView.OnItemSelectedListener spinnerlistener1 = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id){
            TextView textView;
            //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

            if (adapterView.getId() == R.id.spinner_protocol1)
                //textView = Text;

                switch (adapterView.getSelectedItemPosition()){
                    case 0:
                        Text1.setEnabled(false);

                        break;

                    case 1:
                        Text1.setEnabled(false);
                        break;

                    case 2:
                        Text1.setEnabled(false);


                        break;

                    case 3:
                        Text1.setEnabled(false);

                        break;

                    case 4:
                        Text1.setEnabled(false);
                        break;

                    case 5:
                        Text1.setEnabled(true);
                        break;

                    case 6:
                        Text1.setEnabled(true);
                        break;

                    case 7:
                        Text1.setEnabled(true);
                        break;

                    case 8:
                        Text1.setEnabled(true);
                        break;

                    case 9:
                        Text1.setEnabled(true);
                        break;


                }



        }

        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };








    private void setAction() {
        Add_condition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                adapter.addItem("new item");

            }
        });
    }
}