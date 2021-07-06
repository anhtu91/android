package org.owntracks.android.ui.register;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.databinding.UiRegisterBinding;
import org.owntracks.android.ui.base.BaseActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
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
    private Button btnCancel;
    private final String SIGNUP = "/signup";
    private final String HTTP = "http://";
    private final String HTTPS = "https://";
    private final String COLON = ":";
    private final int NUMBER_CHARACTER_USERNAME_PASSWORD = 6;
    private CallRegisterAPI callRegisterAPI = null;

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
        btnCancel = (Button) findViewById(R.id.btnCancel);

        host.setText(viewModel.getHost());
        port.setText(viewModel.getPort());

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendHTTPRequest();
                //Save to preference
                viewModel.setHost(host.getText().toString());
                viewModel.setPort(port.getText().toString());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void sendHTTPRequest(){
        String strHost = host.getText().toString().trim();
        String strPort = port.getText().toString().trim();
        String strUsername = username.getText().toString().trim();
        String strPassword = password.getText().toString().trim();
        String strRePassword = rePassword.getText().toString().trim();
        String strEmail = email.getText().toString().trim();
        String strCertPassword = certificatePassword.getText().toString().trim();

        callRegisterAPI = new CallRegisterAPI();

        if(strUsername.length() < NUMBER_CHARACTER_USERNAME_PASSWORD){
            Toast.makeText(getApplicationContext(), getText(R.string.usernameContain), Toast.LENGTH_SHORT).show();
        }else if(strPassword.length() < NUMBER_CHARACTER_USERNAME_PASSWORD){
            Toast.makeText(getApplicationContext(), getText(R.string.passwordContain), Toast.LENGTH_SHORT).show();
        }else if(strEmail.isEmpty()){
            Toast.makeText(getApplicationContext(), getText(R.string.emptyEmail), Toast.LENGTH_SHORT).show();
        } else if(strCertPassword.length() < NUMBER_CHARACTER_USERNAME_PASSWORD){
            Toast.makeText(getApplicationContext(), getText(R.string.certPasswordContain), Toast.LENGTH_SHORT).show();
        } else {
            if(!isValidEmail(strEmail)) {
                Toast.makeText(getApplicationContext(), getText(R.string.incorrectEmail), Toast.LENGTH_SHORT).show();
            }else{
                if(!strPassword.equals(strRePassword)){
                    Toast.makeText(this, getText(R.string.passwordRegisterError), Toast.LENGTH_SHORT).show();
                }else{
                    if(strHost.contains(HTTP) || strHost.contains(HTTPS)){
                        callRegisterAPI.execute(strHost+COLON+strPort+SIGNUP, strUsername, strPassword, strEmail, strCertPassword);
                    }else{
                        callRegisterAPI.execute(HTTP+strHost+COLON+strPort+SIGNUP, strUsername, strPassword, strEmail, strCertPassword);
                    }
                }
            }
        }
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private class CallRegisterAPI extends AsyncTask<String, Void, String> {
        private final String METHOD = "POST";
        private final String CONTENT = "Content-Type";
        private final String TYPE_REQUEST = "application/json; charset=UTF-8";
        private final String ACCEPT = "Accept";
        private final String TYPE_RESPONSE = "application/json";
        private final String USERNAME = "username";
        private final String PASSWORD = "password";
        private final String EMAIL = "email";
        private final String CERTIFICATE_PASSWORD = "certPassword";
        private final String OK = "OK";
        private final String FOR_SCANNER = "\\A";
        private final String STATUS = "status";
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(RegisterActivity.this);
            progressDialog.setMessage(getText(R.string.wait));
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            String username = params[1];
            String password = params[2];
            String email = params[3];
            String certificatePassword = params[4];
            HttpURLConnection connection = null;
            DataOutputStream dataOutputStream = null;

            try {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod(METHOD);
                connection.setRequestProperty(CONTENT, TYPE_REQUEST);
                connection.setRequestProperty(ACCEPT, TYPE_RESPONSE);
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.setDoOutput(true);
                connection.setDoInput(true);

                JSONObject jsonParam = new JSONObject();
                jsonParam.put(USERNAME, username);
                jsonParam.put(PASSWORD, password);
                jsonParam.put(EMAIL, email);
                jsonParam.put(CERTIFICATE_PASSWORD, certificatePassword);

                Timber.i("Send register request "+jsonParam.toString());
                dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes(jsonParam.toString());

                dataOutputStream.flush();
                dataOutputStream.close();

                Timber.i("Response Register "+connection.getResponseMessage() + "");
                Timber.i("Status code "+connection.getResponseCode());

                if(connection.getResponseMessage().equals(OK) && connection.getResponseCode() == 200){
                    DataInputStream dataInputStream = new DataInputStream(connection.getInputStream());
                    Scanner scanner = new Scanner(dataInputStream).useDelimiter(FOR_SCANNER);
                    String result = scanner.hasNext() ? scanner.next() : "";
                    JSONObject jsonObject = new JSONObject(result);
                    String status = jsonObject.getString(STATUS);
                    connection.disconnect();
                    return status;
                }else{
                    connection.disconnect();
                    return getText(R.string.errorWhileConnectingServer).toString();
                }
            } catch (Exception e) {
                Timber.e("Error while registering "+e.toString());
            }
           return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            if(result != null){
                Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(RegisterActivity.this, getText(R.string.timeoutConnection), Toast.LENGTH_LONG).show();
            }
        }
    }
}
