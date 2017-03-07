package eu.faircode.netguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
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
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityPro4 extends AppCompatActivity {

    EditText input1,input2;
    Button add,clear,capture;
    ListView listview;
    ArrayAdapter<String> MyArrayAdapter;
    ArrayAdapter<CharSequence> adapter_spinner_list1,adapter_spinner_list2;
    Spinner Spinner_0,Spinner_1,Spinner_2;
    int position=0;


    private static final int REQUEST_PCAPtcp = 0;
    private static final int REQUEST_PCAPudp = 1;



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
        capture = (Button) findViewById(R.id.capture_pcap);

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

                        setprotocol(0);

                        break;

                    case 1:
                        textView.setEnabled(false);
                        textView.setText("");

                        setprotocol(1);

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
            newInput = Spinner_0.getSelectedItem().toString() +"  "+ input1.getText().toString();

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



    private void setprotocol(final int pro){

        capture.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (pro == 0){
                    final File pcap_file_tcp = new File(getCacheDir(), "netguardtcp.pcap");
                    SinkholeService.setPcaptcp(pcap_file_tcp);
                    startActivityForResult(getIntentPCAPDocument(pro), REQUEST_PCAPtcp);
                }
                else if (pro == 1){
                    final File pcap_file_udp = new File(getCacheDir(), "netguardudp.pcap");
                    SinkholeService.setPcapudp(pcap_file_udp);
                    startActivityForResult(getIntentPCAPDocument(pro), REQUEST_PCAPudp);
                }
            }


        });
    }


    private Intent getIntentPCAPDocument(int pro) {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Util.isPackageInstalled("org.openintents.filemanager", this)) {
                intent = new Intent("org.openintents.action.PICK_DIRECTORY");
            } else {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=org.openintents.filemanager"));
            }
        } else {
            if (pro == 0){
                intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/octet-stream");

                intent.putExtra(Intent.EXTRA_TITLE, "netguardtcp_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
            }
            else {
                intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/octet-stream");
                intent.putExtra(Intent.EXTRA_TITLE, "netguardudp_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
            }


        }
        return intent;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        //Log.i(TAG, "onActivityResult request=" + requestCode + " result=" + requestCode + " ok=" + (resultCode == RESULT_OK));


        if (requestCode == REQUEST_PCAPtcp) {
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPtcp(data);

        } else if (requestCode == REQUEST_PCAPudp){
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPudp(data);
        }
        else{
            //  Log.w(TAG, "Unknown activity result request=" + requestCode);
            super.onActivityResult(requestCode, resultCode, data);
        }


    }


    private void handleExportPCAPtcp(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcaptcp(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguardtcp.pcap");
                    //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcaptcp = new File(getCacheDir(), "netguardtcp.pcap");
                    in = new FileInputStream(pcaptcp);

                    int len;
                    long total = 0;
                    byte[] buftcp = new byte[4096];
                    while ((len = in.read(buftcp)) > 0) {
                        out.write(buftcp, 0, len);
                        total += len;
                    }
                    //Log.i(TAG, "Copied bytes=" + total);

                    return null;
                } catch (Throwable ex) {
                    // Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
                    // Util.sendCrashReport(ex, ActivityLog.this);
                    return ex;
                } finally {
                    if (out != null)
                        try {
                            out.close();
                        } catch (IOException ex) {
                            // Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
                        }
                    if (in != null)
                        try {
                            in.close();
                        } catch (IOException ex) {
                            // Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
                        }

                    // Resume capture
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro4.this);
                    if (prefs.getBoolean("TCP", false)) {
                        File pcap_file_tcp = new File(getCacheDir(), "netguardtcp.pcap");
                        SinkholeService.setPcaptcp(pcap_file_tcp);
                    }
                }
            }

            @Override
            protected void onPostExecute(Throwable ex) {
                if (ex == null)
                    Toast.makeText(ActivityPro4.this, R.string.msg_completed, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActivityPro4.this, ex.toString(), Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    private void handleExportPCAPudp(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcapudp(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguardudp.pcap");
                    //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcapudp = new File(getCacheDir(), "netguardudp.pcap");
                    in = new FileInputStream(pcapudp);

                    int len;
                    long total = 0;
                    byte[] bufudp = new byte[4096];
                    while ((len = in.read(bufudp)) > 0) {
                        out.write(bufudp, 0, len);
                        total += len;
                    }
                    //Log.i(TAG, "Copied bytes=" + total);

                    return null;
                } catch (Throwable ex) {
                    // Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
                    // Util.sendCrashReport(ex, ActivityLog.this);
                    return ex;
                } finally {
                    if (out != null)
                        try {
                            out.close();
                        } catch (IOException ex) {
                            // Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
                        }
                    if (in != null)
                        try {
                            in.close();
                        } catch (IOException ex) {
                            // Log.e(TAG, ex.toString() + "\n" + Log.getStackTraceString(ex));
                        }

                    // Resume capture
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro4.this);
                    if (prefs.getBoolean("UDP", false)) {
                        File pcap_file_udp = new File(getCacheDir(), "netguardudp.pcap");
                        SinkholeService.setPcapudp(pcap_file_udp);
                    }
                }
            }

            @Override
            protected void onPostExecute(Throwable ex) {
                if (ex == null)
                    Toast.makeText(ActivityPro4.this, R.string.msg_completed, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActivityPro4.this, ex.toString(), Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

}
