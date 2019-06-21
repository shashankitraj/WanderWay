package example.shashankitraj.wanderway;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText etEmail,etPassword,etName,etNumber;
    Button btnRegister;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.editTextEmailRegister);
        etPassword = findViewById(R.id.editTextPasswordRegister);
        etName = findViewById(R.id.editTextNameRegister);
        etNumber = findViewById(R.id.editTextPhoneRegister);
        btnRegister = findViewById(R.id.buttonRegister);
        mAuth = FirebaseAuth.getInstance();
        dbRef = FirebaseDatabase.getInstance().getReference("Users");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                funcRegister();
            }
        });
    }

        private void funcRegister() {
            final String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            final String phone = etNumber.getText().toString();
            final String name = etName.getText().toString();
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email should be entered");
                etEmail.requestFocus();
                Toast.makeText(getApplicationContext(), "Email Required", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(password)) {
                etEmail.setError("Password should be entered");
                etEmail.requestFocus();
                Toast.makeText(getApplicationContext(), "Password Required", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(name)) {
                etEmail.setError("Name should be entered");
                etEmail.requestFocus();
                Toast.makeText(getApplicationContext(), "Name Required", Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(phone)) {
                etEmail.setError("Phone Number should be entered");
                etEmail.requestFocus();
                Toast.makeText(getApplicationContext(), "Phone Number Required", Toast.LENGTH_SHORT).show();
            }
            else if (phone.length() != 10) {
                etNumber.setError("Number should be 10 digits");
                etNumber.requestFocus();
                Toast.makeText(getApplicationContext(), "Number should be 10 digits", Toast.LENGTH_SHORT).show();
            }
            else {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String TAG = "Register Activity";
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");

                            String id = dbRef.push().getKey();
                            UserInfo userInfo = new UserInfo() {
                                @Override
                                public String getUid() {
                                    return null;
                                }

                                @Override
                                public String getProviderId() {
                                    return null;
                                }

                                @Nullable
                                @Override
                                public String getDisplayName() {
                                    return name;
                                }

                                @Nullable
                                @Override
                                public Uri getPhotoUrl() {
                                    return null;
                                }

                                @Nullable
                                @Override
                                public String getEmail() {
                                    return email;
                                }

                                @Nullable
                                @Override
                                public String getPhoneNumber() {
                                    return phone;
                                }

                                @Override
                                public boolean isEmailVerified() {
                                    return false;
                                }
                            };
                            dbRef.child(id).setValue(userInfo);
                            finish();
                            Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else{
                            Log.v(TAG,"createUserWithEmail:failure");
                            Toast.makeText(getApplicationContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }
