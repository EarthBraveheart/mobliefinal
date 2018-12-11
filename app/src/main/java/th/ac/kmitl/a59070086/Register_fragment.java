package th.ac.kmitl.a59070086;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_fragment extends Fragment {
    SQLiteDatabase myDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, userId VARCHAR(12), name VARCHAR(50), age INTEGER, password VARCHAR(30))");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initNewAccount();
    }
    void initNewAccount(){
        Button newAccountBtn = getView().findViewById(R.id.reg_btn);
        newAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = getView().findViewById(R.id.reg_userid);
                EditText _name = getView().findViewById(R.id.reg_name);
                EditText _age = getView().findViewById(R.id.reg_age);
                EditText _password = getView().findViewById(R.id.reg_password);
                String userIdStr = _userId.getText().toString();
                String nameStr = _name.getText().toString();
                String ageStr = _age.getText().toString();
                String passwordStr = _password.getText().toString();
                boolean pass = true;
                int ageInt = -1;
                if (userIdStr.length() <6 || userIdStr.length() >12){
                    pass = false;
                    Toast.makeText(getActivity(), "UserId needs 6-12 characters", Toast.LENGTH_SHORT).show();
                }
                if (!nameStr.contains(" ")){
                    pass = false;
                    Toast.makeText(getActivity(), "name need a ' ' between name and surname", Toast.LENGTH_SHORT).show();
                }
                try {
                    ageInt = Integer.parseInt(ageStr);
                    if (ageInt < 10 || ageInt > 80){
                        pass = false;
                        Toast.makeText(getActivity(), "age needs 10-80", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    pass = false;
                }
                if (passwordStr.length() < 6){
                    pass = false;
                    Toast.makeText(getActivity(), "password needs more 6 characters", Toast.LENGTH_SHORT).show();
                }
                if (pass == true){
                    ContentValues row = new ContentValues();
                    row.put("userId", userIdStr);
                    row.put("name", nameStr);
                    row.put("age", ageInt);
                    row.put("password", passwordStr);
                    myDB.insert("user", null, row);
                    Toast.makeText(getActivity(), "register complete", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Login_fragment()).addToBackStack(null).commit();
                }
            }
        });

    }
}
