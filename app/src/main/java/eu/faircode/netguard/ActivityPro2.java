package eu.faircode.netguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ActivityPro2 extends AppCompatActivity {

    private Spinner Spinner,Spinner1,Spinner2;

    private Button newList,confirm;

    private EditText Text, Text2, editIPandPORT;

    LinearLayout pro2;

    ArrayList<HashMap> objectList;
    ArrayAdapter<CharSequence> adapter;
    View buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro2);

        buttonView = LayoutInflater.from(ActivityPro2.this).inflate(R.layout.activity_activity_pro2_object_button, null);


        adapter = ArrayAdapter.createFromResource(this, R.array.spinner_list2, R.layout.spinnertest);

        Spinner = (Spinner) findViewById(R.id.spinner);
        Spinner.setOnItemSelectedListener(spinnerlistener);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        //adapter.setDropDownViewResource(R.layout.spinnertest);
        Spinner.setPrompt("Choose Protocol or Ip");
        Spinner.setAdapter(adapter);



        Text = (EditText) findViewById(R.id.editText);
        Text2 = (EditText) findViewById(R.id.editText2);

        Spinner1 = (Spinner) findViewById(R.id.spinner1);

        Spinner2 = (Spinner) findViewById(R.id.spinner2);
        Spinner2.setAdapter(adapter);
        Spinner2.setOnItemSelectedListener(spinnerlistener2);


        pro2 = (LinearLayout) findViewById(R.id.pro2);

        newList = (Button)buttonView.findViewById(R.id.add);

        confirm = (Button) findViewById(R.id.dialog_capture);






        addListView();
        setAction();

    }


    AdapterView.OnItemSelectedListener spinnerlistener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id){
            TextView textView;
            //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

            if (adapterView.getId() == R.id.spinner)
                //textView = Text;

            switch (adapterView.getSelectedItemPosition()){
                case 0:
                    Text.setEnabled(false);
                    break;

                case 1:
                    Text.setEnabled(false);

                    break;

                case 2:
                    Text.setEnabled(false);


                    break;

                case 3:
                    Text.setEnabled(false);

                    break;

                case 4:
                    Text.setEnabled(false);
                    break;

                case 5:
                    Text.setEnabled(true);
                    break;

                case 6:
                    Text.setEnabled(true);
                    break;

                case 7:
                    Text.setEnabled(true);
                    break;

                case 8:
                    Text.setEnabled(true);
                    break;

                case 9:
                    Text.setEnabled(true);
                    break;


            }



        }

        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };



    AdapterView.OnItemSelectedListener spinnerlistener2 = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id){
            TextView textView ;
            //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

            if (adapterView.getId() == R.id.spinner2)
                textView = Text2;

            switch (adapterView.getSelectedItemPosition()){
                case 0:
                    Text2.setEnabled(false);
                    break;

                case 1:
                    Text2.setEnabled(false);

                    break;

                case 2:
                    Text2.setEnabled(false);

                    break;

                case 3:
                    Text2.setEnabled(false);

                    break;

                case 4:
                    Text2.setEnabled(false);
                    break;

                case 5:
                    Text2.setEnabled(true);
                    break;

                case 6:
                    Text2.setEnabled(true);
                    break;

                case 7:
                    Text2.setEnabled(true);
                    break;

                case 8:
                    Text2.setEnabled(true);
                    break;

                case 9:
                    Text2.setEnabled(true);
                    break;

            }

        }

        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };





    public void addListView(){
        objectList = new ArrayList<HashMap>();
        int btnid=1;
        pro2.removeAllViews();


        for (int i=0;i<1;i++){

            HashMap<String,EditText> editMap = new HashMap();

            //View view = LayoutInflater.from(ActivityPro2.this).inflate(R.layout.activity_activity_pro2,null);
            //LinearLayout III=(LinearLayout) view.findViewById(R.id.III);
            //editIPandPORT = (EditText)III.findViewById(R.id.editText3);

            View view = LayoutInflater.from(ActivityPro2.this).inflate(R.layout.activity_activity_pro2_object,null);
            LinearLayout II=(LinearLayout) view.findViewById(R.id.II);

            editIPandPORT = (EditText)II.findViewById(R.id.editText1);
            editIPandPORT.setText("");

            btnid++;

            editMap.put("IP and PORT", editIPandPORT);
            objectList.add(editMap);


            pro2.addView(view);
        }

        pro2.addView(buttonView);
    }


    private void setAction(){


        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //Log.i("msg","");

                finish();

            }

        });

        newList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){



               // editIPandPORT = (EditText)II.findViewById(R.id.editText1);
               //Log.i("msg","新增");

               for (HashMap<String,EditText> editMap:objectList){

                   String ipandport = editMap.get("IP and PORT").getText().toString();

               }
               addListView();
            }

       });


    }





}
