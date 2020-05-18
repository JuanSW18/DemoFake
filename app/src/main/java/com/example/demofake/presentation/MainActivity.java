package com.example.demofake.presentation;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demofake.R;
import com.example.demofake.data.repository.remote.Service;
import com.example.demofake.data.repository.remote.request.GetRequest;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Context context;

    RadioGroup rgListOperations;
    EditText etFirstNumber;
    EditText etSecondNumber;
    Button btnRunOperation;
    TextView tvResult;

    public static final String SUMAR = "SUMAR";
    public static final String RESTAR = "RESTAR";
    public static final String MULTIPLICAR = "MULTIPLICAR";
    public static final String DIVIDIR = "DIVIDIR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        rgListOperations = findViewById(R.id.rgListOperations);
        etFirstNumber = findViewById(R.id.etFirstNumber);
        etSecondNumber = findViewById(R.id.etSecondNumber);
        btnRunOperation = findViewById(R.id.btnRunOperation);
        tvResult = findViewById(R.id.operationResult);

    }

    public void getOperationResult(String operationType, int firstNumber, int secondNumber){

        GetRequest getRequest = Service.getInstance(GetRequest.class);
        Call<Integer> objectCall = null;

        switch (operationType){
            case SUMAR:
                objectCall = getRequest.sumNumbers(firstNumber, secondNumber);
                break;
            case RESTAR:
                objectCall = getRequest.sustractNumbers(firstNumber, secondNumber);
                break;
            case MULTIPLICAR:
                objectCall = getRequest.multiplyNumbers(firstNumber, secondNumber);
                break;
            case DIVIDIR:
                objectCall = getRequest.divideNumbers(firstNumber, secondNumber);
                break;
        }

        objectCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                int statusCode = response.code();
                if( statusCode == 200 ){
                    tvResult.setText( String.valueOf(response.body()) );
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast toast = Toast.makeText(context, "Ocurrio un error. Intente de nuevo por favo.", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }

    public void runOperation(View view) {

        if( etFirstNumber.getText().toString().isEmpty() ||
                etSecondNumber.getText().toString().isEmpty() ){

            Toast toast = Toast.makeText(context, "Complete todos los campos!", Toast.LENGTH_SHORT);
            toast.show();

        }else {

            switch ( rgListOperations.getCheckedRadioButtonId() ){
                case R.id.rbtnSuma:
                    getOperationResult(SUMAR,
                            Integer.parseInt(etFirstNumber.getText().toString()),
                            Integer.parseInt(etSecondNumber.getText().toString()));
                    break;
                case R.id.rbtnResta:
                    getOperationResult(RESTAR,
                            Integer.parseInt(etFirstNumber.getText().toString()),
                            Integer.parseInt(etSecondNumber.getText().toString()));
                    break;
                case R.id.rbtnMultiplicacion:
                    getOperationResult(MULTIPLICAR,
                            Integer.parseInt(etFirstNumber.getText().toString()),
                            Integer.parseInt(etSecondNumber.getText().toString()));
                    break;
                case R.id.rbtnDivision:
                    getOperationResult(DIVIDIR,
                            Integer.parseInt(etFirstNumber.getText().toString()),
                            Integer.parseInt(etSecondNumber.getText().toString()));
                    break;
                default:
                    break;
            }

        }
    }
}
