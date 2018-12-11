package th.ac.kmitl.a59070086;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

public class Login_fragment extends Fragment {
    SharedPreferences sp;
    SQLiteDatabase myDB;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, userId VARCHAR(12), name VARCHAR(50), age INTEGER, password VARCHAR(30))");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container,false);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initRegister();
        initLogin();
        checkLogin();
    }
    void initLogin(){
        Button _loginBtn = getView().findViewById(R.id.login_btn);

        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _userId = getView().findViewById(R.id.login_userid);
                String userIdStr = _userId.getText().toString();
                EditText _password = getView().findViewById(R.id.login_password);
                String passwordStr = _password.getText().toString();
                if (userIdStr.isEmpty() || passwordStr.isEmpty()){
                    Toast.makeText(getContext(), "Please fill out this form", Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor cursor = myDB.rawQuery("select userId, name, age, password from user where userId='" + userIdStr + "' and password = '" + passwordStr + "'", null);
                    if (cursor.moveToNext()){
                        sp.edit()
                                .putString("user id", cursor.getString(0))
                                .putString("name", cursor.getString(1))
                                .putString("age", cursor.getString(2))
                                .putString("password", cursor.getString(3))
                                .apply();
                        Toast.makeText(getContext(), "Login complete", Toast.LENGTH_SHORT).show();
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Home_fragment()).addToBackStack(null).commit();
                    }
                    else {
                        Toast.makeText(getContext(), "Invalid user or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    void checkLogin(){
        if (!sp.getString("user id", "noLogin").equals("noLogin")){

        }
    }
    void initRegister(){
        TextView _reg = getView().findViewById(R.id.login_register);
        _reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Register_fragment()).addToBackStack(null).commit();
            }
        });
    }
}
