package in.co.ophio.keystore_preferences.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.co.ophio.keystore_preferences.R;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener {

    private Toolbar actionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarToolbar();
        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction().replace(R.id.login_container,
                    MainFragment.getInstance()).commit();
        }
    }

    protected Toolbar setActionBarToolbar() {
        if (actionBarToolbar == null) {
            actionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
            //https://code.google.com/p/android/issues/detail?id=77763
            setSupportActionBar(actionBarToolbar);

        }
        actionBarToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.
                        finish();
            }
        });
        actionBarToolbar.setNavigationIcon(R.mipmap.ic_launcher);
        return actionBarToolbar;
    }

}
