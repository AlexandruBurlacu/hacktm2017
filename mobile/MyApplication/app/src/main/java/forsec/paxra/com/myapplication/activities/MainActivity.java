package forsec.paxra.com.myapplication.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import forsec.paxra.com.myapplication.R;

/**
 * Created by iuriegaitur on 5/27/17.
 */

public class MainActivity extends AppCompatActivity {


    @Bind(R.id.text_car) TextView mCarTxtView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        Spannable wordtoSpan = new SpannableString(mCarTxtView.getText().toString());
        wordtoSpan.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 12, mCarTxtView.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mCarTxtView.setText(wordtoSpan);

    }





}
