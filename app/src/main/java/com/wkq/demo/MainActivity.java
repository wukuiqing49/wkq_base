package com.wkq.demo;

        import android.databinding.DataBindingUtil;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

        import com.wkq.base.utils.AlertUtil;
        import com.wkq.demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setOnClick(this);
    }

    @Override
    public void onClick(View v) {
        AlertUtil.showDeftToast(this, "跳转");
        MvpDemoActivity.startActivity(this);
    }
}
