package com.niejingwei.countdown;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager supportFragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = supportFragmentManager.beginTransaction();
                CountDownFragment fragment = new CountDownFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("time", 5);
                fragment.setArguments(bundle);
                transaction.add(R.id.fragment_contaniner, fragment, "countdown");
                transaction.show(fragment);
                transaction.commit();
            }
        });
    }
}
