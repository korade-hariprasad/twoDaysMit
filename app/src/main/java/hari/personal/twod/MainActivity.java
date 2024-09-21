package hari.personal.twod;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import hari.personal.twod.fragments.AddFragment;
import hari.personal.twod.fragments.ApiFragment;
import hari.personal.twod.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    //declaration
    Toolbar toolbar;
    FrameLayout fragmentContainer;
    BottomNavigationView bottomNavigationView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        toolbar = findViewById(R.id.toolbar);
        fragmentContainer = findViewById(R.id.fragmentContainer);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        loadFragment(new HomeFragment());

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.action_home) loadFragment(new HomeFragment());
                if(item.getItemId()==R.id.action_add) loadFragment(new AddFragment());
                if(item.getItemId()==R.id.action_api) loadFragment(new ApiFragment());
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment){
        /*
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.commit();
        */
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
    }
}