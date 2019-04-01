package com.niejingwei.countdown;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class CountDownFragment extends Fragment {
    View mRoot;
    int mTime;
    TextView number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.fragment_count_down, container, false);
        return mRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTime = getArguments().getInt("time");
        number = mRoot.findViewById(R.id.number);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= mTime; i++) {
                    final int temp = i;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            number.setText(String.valueOf(temp));
                        }
                    });
                    number.setAnimation(AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.repeat));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.remove(CountDownFragment.this);
                transaction.commit();
            }
        }).start();
    }
}
