package ru.smeleyka.bgrebooter.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.smeleyka.bgrebooter.R;

/**
 * Created by smeleyka on 14.03.18.
 */

public class RebootActivity extends AppCompatActivity {

    @BindView(R.id.reboot_button)   Button rebootButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reboot);
        ButterKnife.bind(this);
    }
}
