package th.ac.kmitl.a59070086;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class Setup_fragment extends Fragment {
    SQLiteDatabase myDB;
    SharedPreferences sp;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        }
        catch (NullPointerException e){

        }
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, userId VARCHAR(12), name VARCHAR(50), age INTEGER, password VARCHAR(30))");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_setup, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }
    void initData(){
        EditText userId = getView().findViewById(R.id.setup_userid);
        EditText name = getView().findViewById(R.id.setup_name);
        EditText age = getView().findViewById(R.id.setup_age);
        EditText password = getView().findViewById(R.id.setip_password);
        EditText quote = getView().findViewById(R.id.setup_Quote);
        userId.setText(sp.getString("user id", "user id not found"));
        name.setText(sp.getString("name", "name not found"));
        age.setText(sp.getString("age", "---"));
        password.setText(sp.getString("password", "password not found"));
    }
}
