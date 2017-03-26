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
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActivityPro5 extends AppCompatActivity {

    Button filter,capture;


    private static final int REQUEST_PCAPuid = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_pro5);


        capture = (Button) findViewById(R.id.capture_uid);


        capture.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {


                final File pcap_file_uid = new File(getCacheDir(), "netguarduid.pcap");
                SinkholeService.setPcapuid(pcap_file_uid);
                startActivityForResult(getIntentPCAPDocument(), REQUEST_PCAPuid);



            }


        });


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

                intent.putExtra(Intent.EXTRA_TITLE, "netguarduid_" + new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()) + ".pcap");

        }
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        //Log.i(TAG, "onActivityResult request=" + requestCode + " result=" + requestCode + " ok=" + (resultCode == RESULT_OK));


        if (requestCode == REQUEST_PCAPuid) {
            if (resultCode == RESULT_OK && data != null)
                handleExportPCAPuid(data);
        }
        else{
            //  Log.w(TAG, "Unknown activity result request=" + requestCode);
            super.onActivityResult(requestCode, resultCode, data);
        }


    }


    private void handleExportPCAPuid(final Intent data) {
        new AsyncTask<Object, Object, Throwable>() {
            @Override
            protected Throwable doInBackground(Object... objects) {
                OutputStream out = null;
                FileInputStream in = null;
                try {
                    // Stop capture
                    SinkholeService.setPcapuid(null);

                    Uri target = data.getData();
                    if (data.hasExtra("org.openintents.extra.DIR_PATH"))
                        target = Uri.parse(target + "/netguarduid.pcap");
                    //  Log.i(TAG, "Export PCAP URI=" + target);
                    out = getContentResolver().openOutputStream(target);

                    File pcapuid = new File(getCacheDir(), "netguarduid.pcap");
                    in = new FileInputStream(pcapuid);

                    int len;
                    long total = 0;
                    byte[] bufuid = new byte[4096];
                    while ((len = in.read(bufuid)) > 0) {
                        out.write(bufuid, 0, len);
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
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ActivityPro5.this);
                    if (prefs.getBoolean("Uid", false)) {
                        File pcap_file_uid = new File(getCacheDir(), "netguarduid.pcap");
                        SinkholeService.setPcapuid(pcap_file_uid);
                    }
                }
            }

            @Override
            protected void onPostExecute(Throwable ex) {
                if (ex == null)
                    Toast.makeText(ActivityPro5.this, R.string.msg_completed, Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ActivityPro5.this, ex.toString(), Toast.LENGTH_LONG).show();
            }
        }.execute();
    }

}
