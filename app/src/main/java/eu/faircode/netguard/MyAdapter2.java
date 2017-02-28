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
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by mark on 2017/2/23.
 */
public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder2> {


    private List<String> mData2;

    MyAdapter2(List<String> data) {mData2 = data;}
    int add = 0;

    class ViewHolder2 extends RecyclerView.ViewHolder{

        private TextView textview;




        ViewHolder2(View itemView){

            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.textview);



        }









    }





    @Override
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item2,parent,false);

        return new ViewHolder2(view);
    }



    @Override
    public void onBindViewHolder(ViewHolder2 holder, int position) {
        holder.textview.setText(mData2.get(position));

    }


    @Override
    public int getItemCount(){

        return mData2.size();


    }


    public void addItem(String text){

        mData2.add (add,text);
        notifyItemInserted(add);
        add++;
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
