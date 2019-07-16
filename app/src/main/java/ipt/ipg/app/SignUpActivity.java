package ipt.ipg.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;


public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();
    private TextView displayError;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private RadioGroup radioGroup;
    private boolean loginOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setTitle("Registo de impressão digital do Android");
        editTextUsername = (EditText)findViewById(R.id.editTextUsername);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                if(id == R.id.with_fingerprint){
                    loginOption = false;
                }
                if(id == R.id.with_fingerprint_and_password){
                    loginOption = true;
                }
            }
        });

        Button signUpButton = (Button) findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameValue = editTextUsername.getText().toString();
                String emailValue = editTextEmail.getText().toString();
                String passwordValue = editTextPassword.getText().toString();

                int selectedButtonId = radioGroup.getCheckedRadioButtonId();

                if(TextUtils.isEmpty(usernameValue) || TextUtils.isEmpty(emailValue)|| TextUtils.isEmpty(passwordValue)){
                    Toast.makeText(SignUpActivity.this, "Todos os campos devem ser preenchidos", Toast.LENGTH_LONG).show();
                }else if(selectedButtonId == -1){
                    Toast.makeText(SignUpActivity.this, "Opção de login deve ser selecionada", Toast.LENGTH_LONG).show();
                }else{
                    Gson gson = ((CustomApplication)getApplication()).getGsonObject();
                    UserObject userData = new UserObject(usernameValue, emailValue, passwordValue, loginOption);
                    String userDataString = gson.toJson(userData);
                    CustomSharedPreference pref = ((CustomApplication)getApplication()).getShared();
                    pref.setUserData(userDataString);

                    editTextUsername.setText("");
                    editTextEmail.setText("");
                    editTextPassword.setText("");

                    Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
            }
        });
    }
}