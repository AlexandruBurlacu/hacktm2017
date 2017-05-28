package forsec.paxra.com.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import forsec.paxra.com.myapplication.R;

public class SignInActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.email_sign_in_button)
    public void login(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @OnClick(R.id.btn_permission)
    public void accessPermission(View view) {
        startActivity(new Intent(this, CarPermissionActivity.class));
    }

}
