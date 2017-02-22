package eu.faircode.netguard;

import android.app.usage.UsageEvents;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by mark on 2017/2/19.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mData;

    MyAdapter(List<String> data) {mData = data;}
    int add = 0;

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtItem,Text2;
        private Spinner Spinner_addaon,Spinner_protocol2;//
        private Button Btnremove;



        ViewHolder(View itemView){

            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);

            Spinner_addaon = (Spinner) itemView.findViewById(R.id.spinner_addaon);
            Spinner_protocol2=(Spinner) itemView.findViewById(R.id.spinner_protocol2);
            Text2 = (EditText) itemView.findViewById(R.id.editText_protocol2);

            Btnremove = (Button) itemView.findViewById(R.id.btnremove);

            Spinner_addaon.setOnItemSelectedListener(spinner_listener_aon);
            Spinner_protocol2.setOnItemSelectedListener(spinner_listener2);


            /*itemView.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    String msg = Spinner_addaon.getSelectedItem().toString();
                    EventBus.getDefault().post(new MessageEvent(msg));

                    return false;
                }

            });*/



                Text2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String aon = Spinner_addaon.getSelectedItem().toString();
                        String protocol2 = Spinner_protocol2.getSelectedItem().toString();
                        String iporport2 = Text2.getText().toString();

                        Toast.makeText(view.getContext(), "click  " + aon + " " + protocol2 + " " + iporport2, Toast.LENGTH_SHORT).show();

                        String msg0 = "123456";
                        EventBus.getDefault().post(new MessageEvent(aon + " " + protocol2 + " " + iporport2));


                    }

                });





            Btnremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    removeItem(getAdapterPosition());

                }
            });

         }



        AdapterView.OnItemSelectedListener spinner_listener_aon = new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView,View view,int position,long id){
                TextView textView ;
                //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

                if (adapterView.getId() == R.id.spinner_addaon)

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



        AdapterView.OnItemSelectedListener spinner_listener2 = new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView,View view,int position,long id){
                TextView textView ;
                //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

                if (adapterView.getId() == R.id.spinner_protocol2)
                    //textView = Text2;

                switch (adapterView.getSelectedItemPosition()){
                    case 0:
                        Text2.setEnabled(false);
                        Text2.setText("");
                        break;

                    case 1:
                        Text2.setEnabled(false);
                        Text2.setText("");
                        break;

                    case 2:
                        Text2.setEnabled(false);
                        Text2.setText("");
                        break;

                    case 3:
                        Text2.setEnabled(true);
                        Text2.setText("");
                        break;

                    case 4:
                        Text2.setEnabled(true);
                        Text2.setText("");
                        break;

                    case 5:
                        Text2.setEnabled(true);
                        Text2.setText("");
                        break;

                    case 6:
                        Text2.setEnabled(true);
                        Text2.setText("");
                        break;



                }

                String aon = Spinner_addaon.getSelectedItem().toString();
                String protocol2 = Spinner_protocol2.getSelectedItem().toString();
                EventBus.getDefault().post(new MessageEvent(aon+" "+protocol2));
                //Text2.setText("Condition: "+ Spinner_protocol2.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView arg0){

            }

        };




    }





    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtItem.setText(mData.get(position));

    }

    @Override
    public int getItemCount(){

        return mData.size();


    }


    public void addItem(String text){

        mData.add (add,text);
        notifyItemInserted(add);
        add++;
    }


    public void removeItem(int position){

        mData.remove(position);
        notifyItemRemoved(position);
        add--;
    }


    public class MessageEvent{

        private String Message;

        public MessageEvent(String message){

            this.Message = message;

        }

        public String getMessage(){

            return Message;

        }
    }


}