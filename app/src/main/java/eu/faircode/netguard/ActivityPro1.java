package eu.faircode.netguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityPro1 extends AppCompatActivity {

    private static final int REQUEST_PCAP = 1;
    private Button Button_UDP;
    private CheckBox CheckBox_UDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro1);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);



        File pcap_file = new File(getCacheDir(), "netguard.pcap");

        boolean export = (getPackageManager().resolveActivity(getIntentPCAPDocument(), 0) != null);



        CheckBox_UDP= (CheckBox)findViewById(R.id.checkBox_UDP);
        CheckBox_UDP.setChecked(prefs.getBoolean("UDP", false));

        Button_UDP = (Button)findViewById(R.id.button_UDP);

        if ((CheckBox_UDP.isChecked())==true)
            Button_UDP.setEnabled(true);
        else
            Button_UDP.setEnabled(false);



        CheckBox_UDP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



                if (isChecked) {

                    Button_UDP.setEnabled(true);

                } else {

                    Button_UDP.setEnabled(false);

                }


            }
        });



        Button_UDP.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final File pcap_file = new File(getCacheDir(), "netguard.pcap");
                SinkholeService.setPcap( pcap_file );
                startActivityForResult(getIntentPCAPDocument(), REQUEST_PCAP);

            }


        });


    }


    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs.edit().putBoolean("UDP", CheckBox_UDP.isChecked()).apply();
        //prefs.edit().putBoolean("UDP_Button",Button_UDP.isChecked()).apply();


    }



    private Intent getIntentPCAPDocument() {
        Intent intent;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Util.isPackageInstalled("org.openintents.filemanager", this)) {
                intent = new Intent("org.openintents.action.PICK_DIRECTORY");
            } else {
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=org.openintents.filemanager"));
            }
        } else {
            intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/octet-stream");
            intent.putExtra(Intent.EXTRA_TITLE, "netguard_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
        }
        return intent;
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        //Log.i(TAG, "onActivityResult request=" + requestCode + " result=" + requestCode + " ok=" + (resultCode == RESULT_OK));

        if (requestCode == REQUEST_PCAP) {
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAP(data);

        } else {
          //  Log.w(TAG, "Unknown activity result request=" + requestCode);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleExportPCAP(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcap(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguard.pcap");
                  //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcap = new File(getCacheDir(), "netguard.pcap");
                    in = new FileInputStream(pcap);

                    int len;
                    long total = 0;
                    byte[] buf = new byte[4096];
                    while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
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
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro1.this);
                    if (prefs.getBoolean("pcap", false)) {
                        File pcap_file = new File(getCacheDir(), "netguard.pcap");
                        SinkholeService.setPcap(pcap_file);
                    }
                }
            }

            @Override
            protected void onPostExecute(Throwable ex) {
                if (ex == null)
                    Toast.makeText(ActivityPro1.this, R.string.msg_completed, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActivityPro1.this, ex.toString(), Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

    /*{
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);



        File pcap_file = new File(getCacheDir(), "netguard.pcap");

        boolean export = (getPackageManager().resolveActivity(getIntentPCAPDocument(), 0) != null);


        menu.findItem(R.id.menu_pcap_enabled1).setEnabled(prefs.getBoolean("filter", false));
        menu.findItem(R.id.menu_pcap_enabled1).setChecked(prefs.getBoolean("pcap", false));
        menu.findItem(R.id.menu_pcap_export1).setEnabled(pcap_file.exists() && export);


        case R.id.menu_pcap_enabled1:
            item.setChecked(!item.isChecked());
            prefs.edit().putBoolean("pcap", item.isChecked()).apply();
            SinkholeService.setPcap(item.isChecked() ? pcap_file : null);
            return true;

        case R.id.menu_pcap_export1:
            startActivityForResult(getIntentPCAPDocument(), REQUEST_PCAP);
            return true;

        case R.id.menu_log_clear1:
            new AsyncTask<Object, Object, Object>() {
                @Override
                protected Object doInBackground(Object... objects) {
                    dh.clearLog();
                    if (prefs.getBoolean("pcap", false)) {
                        SinkholeService.setPcap(null);
                        if (pcap_file.exists() && !pcap_file.delete())
                            Log.w(TAG, "Delete PCAP failed");
                        SinkholeService.setPcap(pcap_file);
                    } else {
                        if (pcap_file.exists() && !pcap_file.delete())
                            Log.w(TAG, "Delete PCAP failed");
                    }
                    return null;
                }

                    /*@Override
                    protected void onPostExecute(Object result) {
                        if (running)
                            updateAdapter();
                    }*/
        //    }.execute();*/
        //    return true;*/







   // }*/


}
