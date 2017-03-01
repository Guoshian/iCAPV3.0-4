package eu.faircode.netguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityPro4 extends AppCompatActivity {

    EditText input1,input2;
    Button add,clear;
    ListView listview;
    ArrayAdapter<String> MyArrayAdapter;
    ArrayAdapter<CharSequence> adapter_spinner_list1,adapter_spinner_list2;
    Spinner Spinner_0,Spinner_1,Spinner_2;
    int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro4);

        adapter_spinner_list1 = ArrayAdapter.createFromResource(this, R.array.spinner_list1, R.layout.spinnertest);
        adapter_spinner_list2 = ArrayAdapter.createFromResource(this, R.array.spinner_list2, R.layout.spinnertest);


        Spinner_0 = (Spinner) findViewById(R.id.sp_protocol1);
        Spinner_0.setOnItemSelectedListener(spinner_listener_protocol);

        Spinner_1 = (Spinner) findViewById(R.id.sp_aon);
        Spinner_1.setOnItemSelectedListener(spinner_listener_aon);

        Spinner_2 = (Spinner) findViewById(R.id.sp_protocol2);
        Spinner_2.setOnItemSelectedListener(spinner_listener_protocol);

        adapter_spinner_list1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        adapter_spinner_list2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        Spinner_0.setPrompt("Choose Protocol or Ip");
        Spinner_0.setAdapter(adapter_spinner_list1);
        Spinner_1.setPrompt("Choose Condition");
        Spinner_1.setAdapter(adapter_spinner_list2);
        Spinner_2.setPrompt("Choose Protocol or Ip");
        Spinner_2.setAdapter(adapter_spinner_list1);


        input1 = (EditText) findViewById(R.id.ipport_1);
        input2 = (EditText) findViewById(R.id.ipport_2);

        add = (Button) findViewById(R.id.add_button);
        clear = (Button) findViewById(R.id.clear_button);
        listview = (ListView) findViewById(R.id.listView);


        //sptvinput




        MyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(MyArrayAdapter);

        add.setOnClickListener(addOnClickListener);
        clear.setOnClickListener(clearOnClickListener);
    }



    AdapterView.OnItemSelectedListener spinner_listener_aon = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id){
            //TextView textView ;
            //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

            if (adapterView.getId() == R.id.sp_aon)

                switch (adapterView.getSelectedItemPosition()){
                    case 0:

                        break;

                    case 1:

                        break;

                    case 2:

                        break;

                }

        }

        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };



    AdapterView.OnItemSelectedListener spinner_listener_protocol = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id) {
            //TextView textView;
            TextView textView = (adapterView.getId() == R.id.sp_protocol1)? input1:input2;

          //  if (adapterView.getId() == R.id.sp_protocol)
                //textView = Text;

                switch (adapterView.getSelectedItemPosition()) {
                    case 0:
                        textView.setEnabled(false);
                        textView.setText("");
                        break;

                    case 1:
                        textView.setEnabled(false);
                        textView.setText("");
                        break;

                    case 2:
                        textView.setEnabled(true);
                        textView.setText("");
                        break;


                    case 3:
                        textView.setEnabled(true);
                        textView.setText("");
                        break;

                    case 4:
                        textView.setEnabled(true);
                        textView.setText("");
                        break;

                    case 5:
                        textView.setEnabled(true);
                        textView.setText("");
                        break;



                }

        }

        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };




    private Button.OnClickListener addOnClickListener= new Button.OnClickListener(){

        @Override
    public void onClick(View arg0){
            position++;
            String newInput ="";

            if (position ==1)
            newInput = Spinner_0.getSelectedItem().toString() +"  " + input1.getText().toString();

            else if (position >1)
            newInput = Spinner_1.getSelectedItem().toString() +"  "+ Spinner_2.getSelectedItem().toString() + input2.getText().toString();

            MyArrayAdapter.add(newInput);
            MyArrayAdapter.notifyDataSetChanged();

        }
    };

    private Button.OnClickListener clearOnClickListener= new Button.OnClickListener(){

        @Override
        public void onClick(View arg0){

            MyArrayAdapter.clear();
            MyArrayAdapter.notifyDataSetChanged();
            position = 0;
        }
    };






}
