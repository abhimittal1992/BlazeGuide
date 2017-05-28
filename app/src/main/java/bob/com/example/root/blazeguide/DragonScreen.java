package bob.com.example.root.blazeguide;

import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DragonScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }
        setContentView(R.layout.activity_dragon_screen);
        Toolbar t1 = (Toolbar) findViewById(R.id.dragontoolbar);
        setSupportActionBar(t1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.dragon_collapsing_toolbar);
        collapsingToolbar.setTitle("Monster Hunting");
        ImageView placePicutre = (ImageView) findViewById(R.id.dragonimage);

        Resources resources = getResources();
        String mobDetail = resources.getString(R.string.monsterkill);
        TextView placeDetail = (TextView) findViewById(R.id.monster_kill);
        placeDetail.setText(mobDetail);
    }
}
