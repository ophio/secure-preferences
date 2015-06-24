package in.co.ophio.keystore_preferences.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import in.co.ophio.keystore_preferences.KeystoreApplication;
import in.co.ophio.keystore_preferences.R;
import butterknife.ButterKnife;
import in.co.ophio.keystore_preferences.entity.UserCredential;
import in.co.ophio.keystore_preferences.util.AccountUtils;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class MainFragment extends Fragment {

    @Inject AccountUtils accountUtils;
    @InjectView(R.id.user_name) EditText userName;
    @InjectView(R.id.password) EditText password;
    private MainFragmentListener fragmentListener;

    public static MainFragment getInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        try {
            fragmentListener = (MainFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        fragmentListener = null;
        super.onDetach();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        KeystoreApplication.getAppContext().getAppComponent().inject(this);
        return view;
    }

    @OnClick(R.id.login_button)
    public void onLoginButtonClicked() {
        if (isEntryValid()) {
            accountUtils.login(new UserCredential(
                    userName.getText().toString(),
                    password.getText().toString()));
            fragmentListener.onLoginClicked();
        } else {
            Toast.makeText(getActivity(), "fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isEntryValid() {
        return !TextUtils.isEmpty(userName.getText())
                && !TextUtils.isEmpty(password.getText());
    }

    public interface MainFragmentListener {

        void onLoginClicked();
    }

}
