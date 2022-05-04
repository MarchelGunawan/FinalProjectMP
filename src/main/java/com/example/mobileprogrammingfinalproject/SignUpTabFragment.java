package com.example.mobileprogrammingfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SignUpTabFragment extends Fragment {

    float v = 0;
    EditText email, password, confirmPassword;
    Button SignUp;
    String s = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.emailsignup);
        password = root.findViewById(R.id.passwordsignup);
        confirmPassword = root.findViewById(R.id.passwordsignupconfirm);
        SignUp = root.findViewById(R.id.btnsignup);

        email.setTranslationX(800);
        password.setTranslationX(800);
        confirmPassword.setTranslationX(800);
        SignUp.setTranslationX(800);

        email.setAlpha(v);
        password.setAlpha(v);
        confirmPassword.setAlpha(v);
        SignUp.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        password.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        confirmPassword.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        SignUp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        SignUp.setOnClickListener(View -> insertData());

        return root;
    }

    public void insertData(){
        try{
            if(password.equals(confirmPassword)){
                String urlParameters  = "Username="+ email.getText().toString() + "&Password=" + password.getText().toString();
                byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
                int    postDataLength = postData.length;
                URL url = new URL("https://mobileprog2019.000webhostapp.com/insertData.php");
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
                Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getActivity(), "Password and Confirm Password not same", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
