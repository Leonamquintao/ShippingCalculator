package com.shippingcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner mCountrySpinner;
    private EditText mValue;
    private RadioButton mRadiobtn1;
    private RadioButton mRadiobtn2;
    private Button mSubmitBtn;
    private TextView mTotalValue;
    private TextView mInsuranceVal;
    private TextView mFinalValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCountrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        mValue = (EditText) findViewById(R.id.value);
        mRadiobtn1 = (RadioButton) findViewById(R.id.radioBtn1);
        mRadiobtn2 = (RadioButton) findViewById(R.id.radioBtn2);
        mTotalValue = (TextView) findViewById(R.id.totalValue);
        mSubmitBtn = (Button) findViewById(R.id.btnSubmit);
        mInsuranceVal = (TextView) findViewById(R.id.insuranceVal);
        mFinalValue = (TextView) findViewById(R.id.finalValue);

        mValue.setText("0");
        mRadiobtn2.setChecked(true);


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double ton = Double.parseDouble(mValue.getText().toString());
                Double aux = 0.00, insurance = 0.0, minimunValue = 150.00, finalValue = 0.0;
                String spinnerVal = mCountrySpinner.getSelectedItem().toString();

                Double argentina = 80.00, bolivia = 115.50, chile = 105.50, mexico = 130.75,peru = 125.50;

                if(mRadiobtn1.isChecked()) {
                    argentina = 100.00;
                    bolivia = 130.50;
                    chile = 120.50;
                    mexico = 157.50;
                    peru = 145.50;
                }

                if(mValue.getText().toString().matches("") || mValue.getText().toString().matches("0")) {
                    Toast.makeText(getApplicationContext(), "Informe um peso válido!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    String msg = "Frete calculado com Sucesso!";
                    if (spinnerVal.equals("Argentina")) {
                        aux = ton * argentina;
                        mTotalValue.setText(String.valueOf("R$"+aux));
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } else if (spinnerVal.equals("Bolívia")) {
                        aux = ton * bolivia;
                        mTotalValue.setText(String.valueOf("R$"+aux));
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } else if (spinnerVal.equals("Chile")) {
                        aux = ton * chile;
                        mTotalValue.setText(String.valueOf("R$"+aux));
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } else if (spinnerVal.equals("México")) {
                        aux = ton * mexico;
                        mTotalValue.setText(String.valueOf("R$"+aux));
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } else if (spinnerVal.equals("Perú")) {
                        aux = ton * peru;
                        mTotalValue.setText(String.valueOf("R$"+aux));
                        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Informe um destino vávido!", Toast.LENGTH_LONG).show();
                    }

                    // VALOR SEGURO ADICIONAL 10% DE ACRESCIMO
                    insurance = (aux * 0.10);
                    insurance = Math.floor(insurance * 100)/100;
                    finalValue = (insurance)+(aux);

                }

                mInsuranceVal.setText(String.valueOf("R$"+insurance));
                mFinalValue.setText(String.valueOf("R$"+finalValue));
                //valor = Double.valueOf(String.format(Locale.US, "%.2f", Math.ceil(valor)));

                //TESTE DE VALOR MÍNIMO = R$150.00
                if(finalValue < 150.00) {
                    mFinalValue.setText(String.valueOf("Valor Mínimo: R$150.00"));
                }
            }
        });
    }
}