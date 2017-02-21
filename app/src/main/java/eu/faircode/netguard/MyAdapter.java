package eu.faircode.netguard;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mark on 2017/2/19.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> mData;

    MyAdapter(List<String> data) {mData = data;}
    int add=0;

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtItem,Text2;
        private Spinner Spinner_addaon,Spinner_protocol2;
        private Button Btnremove;



        ViewHolder(View itemView){

            super(itemView);
            txtItem = (TextView) itemView.findViewById(R.id.txtItem);
            Spinner_addaon = (Spinner) itemView.findViewById(R.id.spinner_addaon);
            Spinner_protocol2=(Spinner) itemView.findViewById(R.id.spinner_protocol2);
            Text2 = (EditText) itemView.findViewById(R.id.editText_protocol2);
            Btnremove = (Button) itemView.findViewById(R.id.btnremove);

            Spinner_protocol2.setOnItemSelectedListener(spinnerlistener2);


            Btnremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    removeItem(getAdapterPosition());

                }
            });

        }





        AdapterView.OnItemSelectedListener spinnerlistener2 = new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView adapterView,View view,int position,long id){
                TextView textView ;
                //TextView textView = (adapterView.getId() == R.id.spinner)? Text1:Text2;

                if (adapterView.getId() == R.id.spinner_protocol2)
                    //textView = Text2;

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

        add++;

        mData.add (add,text);
        notifyItemInserted(add);

    }


    public void removeItem(int position){


        mData.remove(position);
        notifyItemRemoved(position);

        add--;
    }



}