package eu.faircode.netguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
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
    private static final int REQUEST_PCAPip = 2;
    private static final int REQUEST_PCAPport = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

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


        //prefs.getInt("Spinner_0", Spinner_0.getSelectedItemPosition());

        Spinner_0.setSelection(prefs.getInt("Spinner_0", 0));
        input1.setText(prefs.getString("input1", " "));
        position = prefs.getInt("position", 0);


        //prefs.edit().putString("input1", input1.getText().toString()).commit();

        MyArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listview.setAdapter(MyArrayAdapter);


        String listviewSet = prefs.getString("listview", " ");
        MyArrayAdapter.add(listviewSet);
        MyArrayAdapter.notifyDataSetChanged();


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

                        //String input_ip1 =  input1.getText().toString();
                        //Toast.makeText(ActivityPro4.this, input_ip1, Toast.LENGTH_LONG).show();

                        setprotocol(0);

                        break;

                    case 1:
                        textView.setEnabled(false);
                        textView.setText("");

                        //input_ip1 =  input1.getText().toString();
                        //Toast.makeText(ActivityPro4.this, input_ip1, Toast.LENGTH_LONG).show();

                        setprotocol(1);

                        break;

                    case 2:
                        textView.setEnabled(true);
                        //textView.setText("");
                        /*input1.setOnEditorActionListener(new TextView.OnEditorActionListener(){
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event){

                                String input_ip1 =  input1.getText().toString();

                                Toast.makeText(ActivityPro4.this, input_ip1, Toast.LENGTH_LONG).show();

                                return false;
                            }


                        });*/

                        String input_1 =  input1.getText().toString();

                        Toast.makeText(ActivityPro4.this, input_1, Toast.LENGTH_LONG).show();




                        setprotocol(2);

                        break;


                    case 3:
                        textView.setEnabled(true);
                        textView.setText("");
                        break;

                    case 4:
                        textView.setEnabled(true);
                        setprotocol(3);
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



    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs.edit().putInt("Spinner_0", Spinner_0.getSelectedItemPosition()).apply();
        prefs.edit().putString("input1", input1.getText().toString()).commit();
        prefs.edit().putInt("position", position).apply();
        //prefs.edit().putString("listview", newInput).commit();
    }





    private Button.OnClickListener addOnClickListener= new Button.OnClickListener(){

        @Override
    public void onClick(View arg0){

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro4.this);

            position++;
            String newInput;
            String input_1= "";


            if (position ==1) {

                if ((Spinner_0.getSelectedItemPosition()== 0) ||(Spinner_0.getSelectedItemPosition()== 1)){
                    input_1 = "";
                    InputIp(input_1);
                } else if (Spinner_0.getSelectedItemPosition()== 2){
                    input_1 = input1.getText().toString();
                    InputIp(input_1);
                } else if (Spinner_0.getSelectedItemPosition()== 4){
                    input_1 = input1.getText().toString();
                    InputPort(input_1);
                }


                Toast.makeText(ActivityPro4.this, input_1, Toast.LENGTH_LONG).show();
                newInput = Spinner_0.getSelectedItem().toString() + "  " + input_1;

            }

            else /*if (position >1)*/
            newInput = Spinner_1.getSelectedItem().toString() +"  "+ Spinner_2.getSelectedItem().toString() + input2.getText().toString();

            MyArrayAdapter.add(newInput);

            MyArrayAdapter.notifyDataSetChanged();
            prefs.edit().putString("listview", newInput).commit();

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
                else if (pro == 2){

                    final File pcap_file_ip = new File(getCacheDir(), "netguardip.pcap");
                    SinkholeService.setPcapip(pcap_file_ip);
                    startActivityForResult(getIntentPCAPDocument(pro), REQUEST_PCAPip);

                    //Toast.makeText(ActivityPro4.this, input_ip1 , Toast.LENGTH_LONG).show();

                }

                else if (pro == 3){

                    final File pcap_file_port = new File(getCacheDir(), "netguardport.pcap");
                    SinkholeService.setPcapport(pcap_file_port);
                    startActivityForResult(getIntentPCAPDocument(pro), REQUEST_PCAPport);

                    //Toast.makeText(ActivityPro4.this, input_ip1 , Toast.LENGTH_LONG).show();

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
            else if (pro == 1){
                intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/octet-stream");
                intent.putExtra(Intent.EXTRA_TITLE, "netguardudp_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
            }
            else if (pro == 2){
                intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/octet-stream");
                intent.putExtra(Intent.EXTRA_TITLE, "netguardip_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
            }
            else {
                intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/octet-stream");
                intent.putExtra(Intent.EXTRA_TITLE, "netguardport_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
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
        else if (requestCode == REQUEST_PCAPip){
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPip(data);
        }
        else if (requestCode == REQUEST_PCAPport){
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPport(data);
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

    private void handleExportPCAPip(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcapip(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguardip.pcap");
                    //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcapip = new File(getCacheDir(), "netguardip.pcap");
                    in = new FileInputStream(pcapip);

                    int len;
                    long total = 0;
                    byte[] bufip = new byte[4096];
                    while ((len = in.read(bufip)) > 0) {
                        out.write(bufip, 0, len);
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
                    if (prefs.getBoolean("Ip", false)) {
                        File pcap_file_ip = new File(getCacheDir(), "netguardip.pcap");
                        SinkholeService.setPcapip(pcap_file_ip);
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

    private void handleExportPCAPport(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcapport(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguardport.pcap");
                    //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcapport = new File(getCacheDir(), "netguardport.pcap");
                    in = new FileInputStream(pcapport);

                    int len;
                    long total = 0;
                    byte[] bufport = new byte[4096];
                    while ((len = in.read(bufport)) > 0) {
                        out.write(bufport, 0, len);
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
                    if (prefs.getBoolean("Port", false)) {
                        File pcap_file_port = new File(getCacheDir(), "netguardport.pcap");
                        SinkholeService.setPcapport(pcap_file_port);
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



    static String InputIp_edittext1="";
    static int InputPort_edittext1=0;

    void InputIp(String inputip){
        InputIp_edittext1= inputip;
    }
    String InputIp_edittext(){
        return InputIp_edittext1 ;
    }


    void InputPort(String inputip){
        InputPort_edittext1 = Integer.parseInt(inputip);
    }
    int InputPort_edittext(){
        return InputPort_edittext1;
    }


}
