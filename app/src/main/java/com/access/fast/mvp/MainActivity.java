package com.access.fast.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.access.fast.mvp.helper.PrefHelper;
import com.access.fast.mvp.login.LoginActivity;

/**
 * Created by Kosh on 10/17/2015. copyrights are reserved
 */
public class MainActivity extends AppCompatActivity {

    private Button logout;
    private TextView userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String email = PrefHelper.getUserEmail(this);
        if (email == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }
        logout = (Button) findViewById(R.id.logout);
        userEmail = (TextView) findViewById(R.id.userEmail);
        userEmail.setText(userEmail.getText() + " " + email);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefHelper.setUserEmail(v.getContext(), null);
                startActivity(new Intent(v.getContext(), LoginActivity.class));
                finish();
            }
        });
    }
}
