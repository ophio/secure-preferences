package in.co.ophio.secure.sample.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import in.co.ophio.secure.sample.KeystoreApplication;
import in.co.ophio.secure.sample.R;
import in.co.ophio.secure.sample.util.AccountUtils;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener, LoggedInFragment.LoggedInFragmentListener {

    private Toolbar actionBarToolbar;
    @Inject AccountUtils accountUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeystoreApplication.getAppContext().getAppComponent().inject(this);
        setActionBarToolbar();
        if (savedInstanceState == null) {
            if (accountUtils.isLoggedIn()) {
                openLoggedInFragment();
            } else {
                openLoginFragment();
            }
        }
    }

    private void openLoginFragment() {
        getFragmentManager()
                .beginTransaction().replace(R.id.login_container,
                MainFragment.getInstance()).commit();
    }

    private void openLoggedInFragment() {
        getFragmentManager()
                .beginTransaction().replace(R.id.login_container,
                LoggedInFragment.getInstance()).commit();
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

    @Override
    public void onLogoutClicked() {
        openLoginFragment();
    }

    @Override
    public void onLoginClicked() {
        openLoggedInFragment();
    }
}
