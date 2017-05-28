package forsec.paxra.com.myapplication.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import forsec.paxra.com.myapplication.R;

/**
 * Created by iuriegaitur on 5/27/17.
 */

public class CarPermissionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_permission);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_allow)
    public void allowToDrive(View view) {
        Toast.makeText(this, "Access granted", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_restrict)
    public void dismissDriving(View view) {
        Toast.makeText(this, "Access dismissed", Toast.LENGTH_SHORT).show();
        showCallPoliceDialog(this);

    }

    public void showCallPoliceDialog(final Context context) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Secure your car")
                .setMessage("Would you like Police to help you?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Police will be at your car and check!", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Okey", Toast.LENGTH_SHORT).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


}
