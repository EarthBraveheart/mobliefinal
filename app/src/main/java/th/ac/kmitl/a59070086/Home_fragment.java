package th.ac.kmitl.a59070086;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Home_fragment extends Fragment {
    SharedPreferences sp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        } catch (NullPointerException e) {
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSetname();
        initSetup();
        initSignout();
    }

    void initSetname(){
        TextView name = getView().findViewById(R.id.home_name);
        name.setText(sp.getString("name", "not found"));
    }

    void initSetup(){
        Button setupBtn = getView().findViewById(R.id.home_setup);
        setupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Setup_fragment()).addToBackStack(null).commit();
            }
        });
    }
    void initSignout(){
        Button signoutBtn = getView().findViewById(R.id.home_signout);
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().clear().apply();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Login_fragment()).addToBackStack(null).commit();
            }
        });
    }
}
