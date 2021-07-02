package org.owntracks.android.ui.register;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiRegisterBinding;
import org.owntracks.android.ui.base.BaseActivity;

import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import timber.log.Timber;

public class RegisterActivity extends BaseActivity<UiRegisterBinding, RegisterMvvm.ViewModel> implements RegisterMvvm.View{

    private EditText host;
    private EditText port;
    private EditText username;
    private EditText password;
    private EditText rePassword;
    private EditText email;
    private EditText certificatePassword;
    private Button btnRegister;
    private final String SIGNUP = "/signup";
    private final String HTTP = "http://";
    private final String HTTPS = "https://";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindAndAttachContentView(R.layout.ui_register, savedInstanceState);
        setSupportToolbar(binding.toolbar);
        setDrawer(binding.toolbar);

        host = (EditText) findViewById(R.id.registerHostText);
        port = (EditText) findViewById(R.id.registerPortText);
        username = (EditText) findViewById(R.id.registerUsernameText);
        password = (EditText) findViewById(R.id.registerPasswordText);
        rePassword = (EditText) findViewById(R.id.registerRePasswordText);
        email = (EditText) findViewById(R.id.registerEmailText);
        certificatePassword = (EditText) findViewById(R.id.registerCertificatePasswordText);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        host.setText(viewModel.getHost());
        port.setText(viewModel.getPort());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setHost(host.getText().toString());
                viewModel.setPort(port.getText().toString());
                sendHTTPRequest();
            }
        });
    }

    private void sendHTTPRequest() {
        String strHost = host.getText().toString();
        String strPort = port.getText().toString();
        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();
        String strRePassword = rePassword.getText().toString();
        String strEmail = email.getText().toString();
        String strCertPassword = certificatePassword.getText().toString();

        CallRegisterAPI callRegisterAPI = new CallRegisterAPI();

        if(!strPassword.equals(strRePassword)){
            Toast.makeText(this, "Password and confirm password are not same", Toast.LENGTH_SHORT).show();
        }else{
            if(strHost.contains(HTTP) || strHost.contains(HTTPS)){
                callRegisterAPI.execute(strHost+":"+strPort+SIGNUP, strUsername, strPassword, strEmail, strCertPassword);
            }else{
                callRegisterAPI.execute("http://"+strHost+":"+strPort+SIGNUP, strUsername, strPassword, strEmail, strCertPassword);
            }
        }
    }

    public class CallRegisterAPI extends AsyncTask<String, Void, Boolean> {
        private final String METHOD = "POST";
        private final String CONTENT = "Content-Type";
        private final String TYPE_REQUEST = "application/json; charset=UTF-8";
        private final String ACCEPT = "Accept";
        private final String TYPE_RESPONSE = "application/json";
        private final String USERNAME = "username";
        private final String PASSWORD = "password";
        private final String EMAIL = "email";
        private final String CERTIFICATE_PASSWORD = "certPassword";
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            String urlString = params[0];
            String username = params[1];
            String password = params[2];
            String email = params[3];
            String certificatePassword = params[4];
            HttpURLConnection connection = null;
            DataOutputStream os = null;

            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod(METHOD);
                connection.setRequestProperty(CONTENT, TYPE_REQUEST);
                connection.setRequestProperty(ACCEPT, TYPE_RESPONSE);
                connection.setDoOutput(true);
                connection.setDoInput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put(USERNAME, username);
                jsonParam.put(PASSWORD, password);
                jsonParam.put(EMAIL, email);
                jsonParam.put(CERTIFICATE_PASSWORD, certificatePassword);

                Timber.i("Send register request "+jsonParam.toString());
                os = new DataOutputStream(connection.getOutputStream());
                os.writeBytes(jsonParam.toString());

                os.flush();
                os.close();

                Timber.i("Response Register "+connection.getResponseMessage() + "");
                Timber.i("Status code "+connection.getResponseCode());
                connection.disconnect();

                if(connection.getResponseMessage().equals("OK") && connection.getResponseCode() == 200){
                    return true;
                }else{
                    return false;
                }
            } catch (Exception e) {
                Timber.e("Error while registering "+e.toString());
            }

            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if(result){
                Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RegisterActivity.this, "Register unsuccessful ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
