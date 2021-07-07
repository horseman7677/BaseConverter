package com.horseman.baseconverstion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class MainActivity extends AppCompatActivity {
    private MaterialCardView btn, clear_btn;
    private EditText in_num, in_b1, in_b2;
    private TextView output_result;
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        in_num = findViewById(R.id.input_num);
        in_b1 = findViewById(R.id.input_base1);
        in_b2 = findViewById(R.id.input_base2);
        output_result = findViewById(R.id.output_result);
        clear_btn = findViewById(R.id.clear_btn);

        clear_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                in_num.setText("");
                in_b1.setText("");
                in_b2.setText("");
                output_result.setText("");
            }
        });
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output_result.setText("");
                if (in_num.getText().toString().isEmpty()) {
                    in_num.setError("empty");
                    in_num.requestFocus();
                    return;
                } else if (in_b1.getText().toString().isEmpty()) {
                    in_b1.setError("empty");
                    in_b1.requestFocus();
                    return;
                } else if (in_b2.getText().toString().isEmpty()) {
                    in_b2.setError("empty");
                    in_b2.requestFocus();
                    return;
                }

                String s = in_num.getText().toString().trim();

                int base1 = Integer.parseInt(in_b1.getText().toString());
                int base2 = Integer.parseInt(in_b2.getText().toString());

                if (base1 < 2 || base1 > 36) {
                    in_b1.setError("2<=base<=36");
                    in_b1.requestFocus();
                    return;
                } else if (base2 < 2 || base2 > 36) {
                    in_b2.setError("2<=base<=36");
                    in_b2.requestFocus();
                    return;
                }


                String result = convertBase(s, base1, base2);

                output_result.setText(result);
            }
        });
    }

    private static String convertBase(String s, int base1, int base2) {

        boolean isNegative = s.startsWith("-");
        int numAsInt = 0;
        for (int i = (isNegative ? 1 : 0); i < s.length(); ++i) {
            numAsInt *= base1;

            numAsInt += Character.isDigit(s.charAt(i))
                    ? s.charAt(i) - '0' : s.charAt(i) - 'A' + 10;
        }
        return (isNegative ? 0 : "")
                + (numAsInt == 0 ? "0" : constructFromBase(numAsInt, base2));
    }

    private static String constructFromBase(int numAsInt, int base2) {
        return numAsInt == 0 ? "" : constructFromBase(numAsInt / base2, base2)
                + (char) (numAsInt % base2 >= 10 ? 'A' + numAsInt % base2 - 10 : '0' + numAsInt % base2);
    }


}