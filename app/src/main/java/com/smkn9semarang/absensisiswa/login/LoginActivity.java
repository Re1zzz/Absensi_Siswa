package com.smkn9semarang.absensisiswa.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.smkn9semarang.absensisiswa.R;
import com.smkn9semarang.absensisiswa.network.ServiceClient;
import com.smkn9semarang.absensisiswa.network.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    //deklarasikan komponen layout yang mau dihubungkan dengan komponen logic
    EditText etNIS,etPass;
    Button  btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //hubungkan komponen ke layoutnya, jangan sampai tertukar
        etNIS = findViewById(R.id.et_nis);
        etPass = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        //menempelkan fungsi pendengaran ke tombol login
        //ketika tombol di ketik tombol akan merespon
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //munculkan loading
                ProgressDialog loading = new ProgressDialog(LoginActivity.this);
                loading.setMessage("Cek User di database...");
                loading.show();

                //menangkap inputan user yg dilayout
                //simpan di variable nis dan password
                //yg bertipe string
                String nis = etNIS.getText().toString();
                String password = etPass.getText().toString();

                //mengirim nis dan password melalui
                //network yang kita buat

                //buat service
                ServiceClient serviceClient = ServiceGenerator.createService(ServiceClient.class);
                //pilih jenis service
                //dan mau disimpan dimana respon
                Call<ResponseLogin> cekLogin = serviceClient.login(
                        "login",
                        ""+nis,
                        ""+password
                );

                //mengirim request ceklogin ke server
                cekLogin.enqueue(new Callback<ResponseLogin>() {
                    @Override
                    public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                        //loading di hilangkan
                        loading.dismiss();

                        //tangkap hasil login di variable hasil
                        String hasil = response.body().getHasil();
                        //cek apakah login sukses atau gagal
                        if(hasil.equals("sukses")) {
                            //munculkan notif
                            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseLogin> call, Throwable t) {
                        //loading di hilangkan
                        loading.dismiss();
                        Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
