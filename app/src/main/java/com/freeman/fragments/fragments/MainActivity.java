package com.freeman.fragments.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FrameLayout container;
    private Button addBtn, removeBtn, replaceBtn;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_v2);
        container = (FrameLayout) findViewById(R.id.fragments_container);
        addBtn = (Button) findViewById(R.id.add_frag_btn);
        removeBtn = (Button) findViewById(R.id.remove_frag_btn);
        replaceBtn = (Button) findViewById(R.id.replace_frag_btn);
        addBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        replaceBtn.setOnClickListener(this);
        MyFragment fragment = new MyFragment();
        manager = getFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
//        transaction.add(R.id.fragments_container,fragment,"My_Fragment");
//        transaction.commit();

    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (v.getId()){
            case R.id.add_frag_btn:
                removeFragments(transaction);
                MyFragment myFragment = new MyFragment();
                transaction.add(R.id.fragments_container,myFragment,"My_Fragment");
                transaction.addToBackStack("AddFragmentA");
                break;
            case R.id.remove_frag_btn:
                removeFragments(transaction);
                transaction.addToBackStack("RemoveAllFragments");
                break;
            case R.id.replace_frag_btn:
                SecondFragment secondFragment = new SecondFragment();
                transaction.replace(R.id.fragments_container,secondFragment,"Second_Fragment");
//                transaction.addToBackStack("ReplaceOnSecondFragment");
                break;
        }
        transaction.commit();
    }

    private void removeFragments(FragmentTransaction transaction){
        Fragment fragment = null;
        fragment = manager.findFragmentByTag("My_Fragment");
        if (fragment!=null){
            transaction.remove(fragment);
        }
        fragment = manager.findFragmentByTag("Second_Fragment");
        if (fragment!=null){
            transaction.remove(fragment);
        }
    }
}
