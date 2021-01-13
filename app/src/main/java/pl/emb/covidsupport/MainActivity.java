package pl.emb.covidsupport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import pl.emb.covidsupport.global.GlobalFragment;
import pl.emb.covidsupport.information.InformationActivity;
import pl.emb.covidsupport.poland.MapFragment;

/***
 * Main activity, which manages bottomNavigationMenu
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomMenu = findViewById(R.id.bottomMenu);
        bottomMenu.setSelectedItemId(R.id.globalStats);
        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragmentContainer, new GlobalFragment()).commit();
        bottomMenu.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment;
            int id = item.getItemId();
//            Choose app section
            if (id == R.id.globalStats) {
                selectedFragment = new GlobalFragment();
            } else if (id == R.id.polandStats) {
                selectedFragment = new MapFragment();
            } else {
                selectedFragment = new InformationActivity();
            }

//            Show selected fragment
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction().replace(
                        R.id.fragmentContainer, selectedFragment).commit();
            }
            return true;
        });

    }
}