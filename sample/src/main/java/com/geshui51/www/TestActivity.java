package com.geshui51.www;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.igeshui.sdk.tax.AuthorizationCallback;
import com.igeshui.sdk.tax.TaxLib;


public class TestActivity extends AppCompatActivity {

    private EditText etCityName;
    private EditText etCityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        etCityName = (EditText) findViewById(R.id.et_city_name);
        etCityId = (EditText) findViewById(R.id.et_city_id);

        findViewById(R.id.btn_authorization).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //2sdk授权
                TaxLib.getInstance().authorization("XW0201020000070000", "123", new AuthorizationCallback() {
                    @Override
                    public void success() {
                        Toast.makeText(TestActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failed(String msg) {
                        Toast.makeText(TestActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        findViewById(R.id.btn_goLit_default).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //3进入当前城市
                TaxLib.getInstance().enterTaxSDK(TestActivity.this, null, null);
//                startActivity(new Intent(TestActivity.this, TaxLoginActivity.class));
            }
        });

        findViewById(R.id.btn_goLit_city).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = etCityName.getText().toString().trim();
                String cityId = etCityId.getText().toString().trim();

                if (isEmpty(cityName) || isEmpty(cityId)) {
                    showToast("城市名称或城市ID为空进入当前城市");
                }

                TaxLib.getInstance().enterTaxSDK(TestActivity.this, cityName, cityId);
                //3进入指定城市
//                startActivity(new Intent(TestActivity.this, TaxLoginActivity.class)
//                        .putExtra("City", cityName)
//                        .putExtra("CityId", cityId));
            }
        });


    }

    public boolean isEmpty(String result) {
        return result == null || "".equals(result.trim());
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
