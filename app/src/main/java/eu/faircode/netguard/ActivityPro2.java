package eu.faircode.netguard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class ActivityPro2 extends AppCompatActivity {

    private Spinner Spinner1,Spinner2;

    private Button Enter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro2);


        Spinner1 = (Spinner) findViewById(R.id.spinner1);

        Spinner2 = (Spinner) findViewById(R.id.spinner2);

        Enter = (Button) findViewById(R.id.button);

        Enter.setOnClickListener(EnterOnClickListener);

    }



    private View.OnClickListener EnterOnClickListener = new View.OnClickListener(){

    @Override
    public void onClick(View v) {
        String spin_array[]= getResources().getStringArray(R.array.spinner_list);
        int index_spinner1 = Spinner1.getSelectedItemPosition();
        int index_spinner2 = Spinner2.getSelectedItemPosition();



    }



    };

}
