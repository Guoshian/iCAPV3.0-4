package eu.faircode.netguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityPro2 extends AppCompatActivity {

    private Spinner Spinner,Spinner1,Spinner2;

    private Button Enter;

    private EditText Text1, Text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro2);

        Spinner = (Spinner) findViewById(R.id.spinner);
        Spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner2 = (Spinner) findViewById(R.id.spinner2);

        Text1 = (EditText) findViewById(R.id.editText);
        Text2 = (EditText) findViewById(R.id.editText2);
        //Spinner2.setAdapter();

        Spinner.setOnItemSelectedListener(spinnerlistener);
        Spinner2.setOnItemSelectedListener(spinnerlistener);
    }


    AdapterView.OnItemSelectedListener spinnerlistener = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id){
            TextView textView ;
            //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

            if (adapterView.getId() == R.id.spinner)
                textView = Text1;
            else textView = Text2;

            switch (adapterView.getSelectedItemPosition()){
                case 0:
                    textView.setEnabled(false);
                    break;

                case 1:
                    textView.setEnabled(false);
                    break;

                case 2:
                    textView.setEnabled(false);
                    break;

                case 3:
                    textView.setEnabled(false);
                    break;

                case 4:
                    textView.setEnabled(false);
                    break;

                case 5:
                    textView.setEnabled(true);
                    break;

                case 6:
                    textView.setEnabled(true);
                    break;

                case 7:
                    textView.setEnabled(true);
                    break;

                case 8:
                    textView.setEnabled(true);
                    break;

                case 9:
                    textView.setEnabled(true);
                    break;


            }



        }






        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };






    /*private View.OnClickListener EnterOnClickListener = new View.OnClickListener(){

    @Override
    public void onClick(View v) {

    String spin_array1[]= getResources().getStringArray(R.array.spinner_list1);
    String spin_array2[]= getResources().getStringArray(R.array.spinner_list2);




    }



    };*/

}
