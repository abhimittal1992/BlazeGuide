package bob.com.example.root.blazeguide;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {


    private InterstitialAd interstitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(android.R.color.black));
        }
        if(!getSharedPreferences("APP_PREFERENCE", Activity.MODE_PRIVATE).getBoolean("IS_ICON_CREATED", false)){
            createShortCut();
            getSharedPreferences("APP_PREFERENCE", Activity.MODE_PRIVATE).edit().putBoolean("IS_ICON_CREATED", true).commit();
        }        setContentView(R.layout.activity_home_screen);
        // Adding Toolbar to Main screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.hometoolbar);
        setSupportActionBar(toolbar);



        // Setting ViewPager for each Tabs
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        FloatingActionButton fabImageButton = (FloatingActionButton)findViewById(R.id.floaticonaboutus);
        fabImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //here
                startActivity(new Intent(HomeScreen.this, AboutMe.class));
            }
        });

        // Prepare the Interstitial Ad
        interstitial = new InterstitialAd(HomeScreen.this);
        // Insert the Ad Unit ID
        interstitial.setAdUnitId("ca-app-pub-7723977720314708/2386230676");

        //Locate the Banner Ad in activity_main.xml
        AdView adView = (AdView) this.findViewById(R.id.adView);

        // Request for Ads
        AdRequest adRequest = new AdRequest.Builder()


                .build();

        // Load ads into Banner Ads
        adView.loadAd(adRequest);

        // Load ads into Interstitial Ads
        interstitial.loadAd(adRequest);

        // Prepare an Interstitial Ad Listener
        interstitial.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });
        // Set Tabs inside Toolbar
       /* TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/
    }
    public void displayInterstitial() {
        // If Ads are loaded, show Interstitial else show nothing.
        if (interstitial.isLoaded()) {
            interstitial.show();
        }
    }
        public void createShortCut(){
            Intent shortcutIntent = new Intent(getApplicationContext(), HomeScreen.class);
            shortcutIntent.setAction(Intent.ACTION_MAIN);
            Intent addIntent = new Intent();
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "Blaze of Battle Tricks");
            addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(getApplicationContext(), R.mipmap.ic_launcher));

            addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
            addIntent.putExtra("duplicate", false);  //may it's already there so don't duplicate
            getApplicationContext().sendBroadcast(addIntent);

    }
    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
       /* adapter.addFragment(new ListContentFragment(), "List");
        adapter.addFragment(new TileContentFragment(), "Tile");*/
        adapter.addFragment(new CardContentFragment(), "Card");
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


}

