package bob.com.example.root.blazeguide;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import bob.com.example.root.blazeguide.R;

public class BOBVideos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }
        setContentView(R.layout.activity_bobvideos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);   ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerForList);
        setupViewPager(viewPager);
    }

    // Add Fragments to Tabs
        private void setupViewPager(ViewPager viewPager) {
        HomeScreen.Adapter adapter = new HomeScreen.Adapter(getSupportFragmentManager());

       adapter.addFragment(new ListContentFragment(), "List");
        viewPager.setAdapter(adapter);
    }

}
