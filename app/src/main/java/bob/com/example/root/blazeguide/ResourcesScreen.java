package bob.com.example.root.blazeguide;

import android.content.res.Resources;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

public class ResourcesScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources_screen);
        Toolbar t1 = (Toolbar) findViewById(R.id.rsstoolbar);
        setSupportActionBar(t1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.rss_collapsing_toolbar);
        collapsingToolbar.setTitle("Resource Farm");
       ImageView placePicutre = (ImageView) findViewById(R.id.rssimage);

    }
}
