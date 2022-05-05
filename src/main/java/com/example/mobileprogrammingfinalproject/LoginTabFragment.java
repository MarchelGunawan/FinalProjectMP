
package com.example.mobileprogrammingfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class LoginTabFragment extends Fragment {

    float v = 0;
    EditText email, password;
    TextView forgetPassword;
    Button login;
    String s = ""   ;
    boolean lgn = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = (EditText) root.findViewById(R.id.emaillogin);
        password = (EditText) root.findViewById(R.id.passwordlogin);
        forgetPassword = root.findViewById(R.id.forget_password);
        login = root.findViewById(R.id.btnlogin);

        email.setTranslationX(800);
        password.setTranslationX(800);
        forgetPassword.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        forgetPassword.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgetPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        login.setOnClickListener(view -> sendData());

        return root;
    }

    public void sendData(){
        try{
            String urlParameters  = "Username="+ email.getText().toString() + "&Password=" + password.getText().toString();
            byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
            int    postDataLength = postData.length;
            URL url = new URL("https://mobileprog2019.000webhostapp.com/checkLogin.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput( true );
            conn.setInstanceFollowRedirects( false );
            conn.setRequestMethod( "POST" );
            conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty( "charset", "utf-8");
            conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
            conn.setUseCaches( false );
            try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
                wr.write( postData );
            }
            InputStream in = conn.getInputStream();
            InputStreamReader isw = new InputStreamReader(in);
            int data = isw.read();
            while(data != -1){
                char current = (char) data;
                s = s + current;
                data = isw.read();
            }
            if (s.equals("Successfully SignIn")){
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                lgn = true;
            }else{
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                s = "";
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
        }
        if(lgn){
            s = "";
            Intent intent = new Intent(getActivity(), LoadingMenu.class);
            startActivity(intent);
        }
    }
}
