package in.co.ophio.secure.sample.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.co.ophio.secure.sample.KeystoreApplication;
import in.co.ophio.secure.sample.R;
import in.co.ophio.secure.sample.util.AccountUtils;

/**
 * @author ragdroid (garima.my.way@gmail.com)
 */
public class LoggedInFragment extends Fragment {

    @Inject AccountUtils accountUtils;
    private LoggedInFragmentListener fragmentListener;

    public static LoggedInFragment getInstance() {
        LoggedInFragment fragment = new LoggedInFragment();
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        try {
            fragmentListener = (LoggedInFragmentListener) activity;
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
        View view = inflater.inflate(R.layout.fragment_logged_in, container, false);
        ButterKnife.inject(this, view);
        KeystoreApplication.getAppContext().getAppComponent().inject(this);
        return view;
    }

    @OnClick(R.id.logout_button)
    public void onLogoutClicked() {
        accountUtils.logout();
        fragmentListener.onLogoutClicked();
    }

    public interface LoggedInFragmentListener {
        void onLogoutClicked();
    }

}
