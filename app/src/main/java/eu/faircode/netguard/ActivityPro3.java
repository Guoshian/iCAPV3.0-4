package eu.faircode.netguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

public class ActivityPro3 extends AppCompatActivity {

    private Button Add_condition;
    private RecyclerView recycler_view;
    private MyAdapter adapter;
    private ArrayList<String> mData = new ArrayList<>();
    private Spinner Spinner_protocol1,Spinner_protocol2;
    private EditText Text1;
    private TextView Showtext1,Showtext2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro3);


        Spinner_protocol1 = (Spinner) findViewById(R.id.spinner_protocol1);

        Showtext1 = (TextView) findViewById(R.id.showtext1);
        //TextViewtest = (TextView) findViewById(R.id.textViewtest);
        Showtext2 = (TextView) findViewById(R.id.showtext2);
        Text1 = (EditText) findViewById(R.id.editText_protocol1);

        Spinner_protocol1.setOnItemSelectedListener(spinner_listener1);

        Add_condition = (Button) findViewById(R.id.add_condition);

        //for (int i=0;i<10;i++){}

        //mData.add("");


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



        Text1.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                String protocol1 = Spinner_protocol1.getSelectedItem().toString();
                String iporport1 = Text1.getText().toString();
                String ip1="",port1="";

                int x=iporport1.length();

                if (x >6) {
                     ip1 = iporport1;
                     port1 = " ";
                }
                else if (x <6) {
                     port1 = iporport1;
                     ip1 = " ";
                }


                Showtext1.setText( "Condition: "+ protocol1 +"  "+ip1 + port1 );
                //Showtext.setText(Text1.getText());
                return false;


            }


        });

        //TextViewtest.setText("choose" );





    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MyAdapter.MessageEvent event){

       // String msg1= "choose" + event.getMessage().toString();

               // Showtext.setText("choose" + event.getMessage().toString());

        Showtext2.setText( event.getMessage().toString() );

    };










    AdapterView.OnItemSelectedListener spinner_listener1 = new AdapterView.OnItemSelectedListener(){
        @Override
        public void onItemSelected(AdapterView adapterView,View view,int position,long id){
            TextView textView;
            //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

            if (adapterView.getId() == R.id.spinner_protocol1)
                //textView = Text;

                switch (adapterView.getSelectedItemPosition()){
                    case 0:
                        Text1.setEnabled(false);
                        Text1.setText("");
                        break;

                    case 1:
                        Text1.setEnabled(false);
                        Text1.setText("");
                        break;

                    case 2:
                        Text1.setEnabled(false);
                        Text1.setText("");
                        break;


                    case 3:
                        Text1.setEnabled(true);
                        Text1.setText("");
                        break;

                    case 4:
                        Text1.setEnabled(true);
                        Text1.setText("");
                        break;

                    case 5:
                        Text1.setEnabled(true);
                        Text1.setText("");
                        break;

                    case 6:
                        Text1.setEnabled(true);
                        Text1.setText("");
                        break;



                }


            String protocol1 = Spinner_protocol1.getSelectedItem().toString();
            Showtext1.setText("Condition: "+ protocol1);

        }

        @Override
        public void onNothingSelected(AdapterView arg0){

        }

    };



    @Override
    public void onStart(){
        super.onStart();

        EventBus.getDefault().register(this);

    }


    @Override
    public void onStop(){
        super.onStop();

        EventBus.getDefault().unregister(this);

    }







    private void setAction() {
        Add_condition.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                adapter.addItem("new item");

            }
        });
    }


   /* public class MessageEvent{

        private String Message;

        public MessageEvent(String message){

            this.Message = message;

        }

        public String getMessage(){

            return Message;

        }
    }*/



}