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

    private static final int REQUEST_PCAPudp = 1;
    private static final int REQUEST_PCAPtcp = 2;
    private static final int REQUEST_PCAPother= 3;
    private CheckBox CheckBox_UDP;
    private Button Button_UDP;
    private CheckBox CheckBox_TCP;
    private Button Button_TCP;
    private CheckBox CheckBox_Other;
    private Button Button_Other;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro1);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);



        File pcap_file = new File(getCacheDir(), "netguardudp.pcap");

        boolean export = (getPackageManager().resolveActivity(getIntentPCAPDocument(), 0) != null);



        CheckBox_UDP= (CheckBox)findViewById(R.id.checkBox_UDP);
        CheckBox_UDP.setChecked(prefs.getBoolean("UDP", false));

        CheckBox_TCP= (CheckBox)findViewById(R.id.checkBox_TCP);
        CheckBox_TCP.setChecked(prefs.getBoolean("TCP", false));

        CheckBox_Other= (CheckBox)findViewById(R.id.checkBox_Other);
        CheckBox_Other.setChecked(prefs.getBoolean("Other", false));

        Button_UDP = (Button)findViewById(R.id.button_UDP);
        Button_TCP = (Button)findViewById(R.id.button_TCP);
        Button_Other = (Button)findViewById(R.id.button_Other);

        if ((CheckBox_UDP.isChecked())==true)
            Button_UDP.setEnabled(true);
        else
            Button_UDP.setEnabled(false);


        if ((CheckBox_TCP.isChecked())==true)
            Button_TCP.setEnabled(true);
        else
            Button_TCP.setEnabled(false);

        if ((CheckBox_Other.isChecked())==true)
            Button_Other.setEnabled(true);
        else
            Button_Other.setEnabled(false);



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
                final File pcap_file_udp = new File(getCacheDir(), "netguardudp.pcap");
                SinkholeService.setPcapudp(pcap_file_udp);
                startActivityForResult(getIntentPCAPDocument(), REQUEST_PCAPudp);

            }


        });




        CheckBox_TCP.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {

                    Button_TCP.setEnabled(true);

                } else {

                    Button_TCP.setEnabled(false);

                }


            }
        });



        Button_TCP.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final File pcap_file_tcp = new File(getCacheDir(), "netguardtcp.pcap");
                SinkholeService.setPcaptcp(pcap_file_tcp);
                startActivityForResult(getIntentPCAPtcpDocument(), REQUEST_PCAPtcp);

            }


        });




        CheckBox_Other.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {

                    Button_Other.setEnabled(true);

                } else {

                    Button_Other.setEnabled(false);

                }


            }
        });



        Button_Other.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                final File pcap_file_other = new File(getCacheDir(), "netguardother.pcap");
                SinkholeService.setPcapother(pcap_file_other);
                startActivityForResult(getIntentPCAPotherDocument(), REQUEST_PCAPother);

            }


        });





    }


    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        prefs.edit().putBoolean("UDP", CheckBox_UDP.isChecked()).apply();
        prefs.edit().putBoolean("TCP", CheckBox_TCP.isChecked()).apply();
        prefs.edit().putBoolean("Other", CheckBox_Other.isChecked()).apply();

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
            intent.putExtra(Intent.EXTRA_TITLE, "netguardudp_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
        }
        return intent;
    }

    private Intent getIntentPCAPtcpDocument() {
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
            intent.putExtra(Intent.EXTRA_TITLE, "netguardtcp_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
        }
        return intent;
    }

    private Intent getIntentPCAPotherDocument() {
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
            intent.putExtra(Intent.EXTRA_TITLE, "netguardother_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");
        }
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        //Log.i(TAG, "onActivityResult request=" + requestCode + " result=" + requestCode + " ok=" + (resultCode == RESULT_OK));

        if (requestCode == REQUEST_PCAPudp) {
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAP(data);

        } else {
          //  Log.w(TAG, "Unknown activity result request=" + requestCode);
            super.onActivityResult(requestCode, resultCode, data);
        }


        if (requestCode == REQUEST_PCAPtcp) {
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPtcp(data);

        } else {
            //  Log.w(TAG, "Unknown activity result request=" + requestCode);
            super.onActivityResult(requestCode, resultCode, data);
        }

        if (requestCode == REQUEST_PCAPother) {
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPother(data);

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
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro1.this);
                    if (prefs.getBoolean("UDP", false)) {
                        File pcap_file_udp = new File(getCacheDir(), "netguardudp.pcap");
                        SinkholeService.setPcapudp(pcap_file_udp);
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
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro1.this);
                    if (prefs.getBoolean("TCP", false)) {
                        File pcap_file_tcp = new File(getCacheDir(), "netguardtcp.pcap");
                        SinkholeService.setPcaptcp(pcap_file_tcp);
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


    private void handleExportPCAPother(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcapother(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguardother.pcap");
                    //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcapother = new File(getCacheDir(), "netguardother.pcap");
                    in = new FileInputStream(pcapother);

                    int len;
                    long total = 0;
                    byte[] bufother = new byte[4096];
                    while ((len = in.read(bufother)) > 0) {
                        out.write(bufother, 0, len);
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
                    if (prefs.getBoolean("Other", false)) {
                        File pcap_file_other = new File(getCacheDir(), "netguardother.pcap");
                        SinkholeService.setPcapother(pcap_file_other);
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





}
