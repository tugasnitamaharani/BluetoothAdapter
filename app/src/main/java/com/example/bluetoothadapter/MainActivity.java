package com.example.bluetoothadapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final int BLUETOOTH_REQ_CODE = 1;
    Button buttonBlue;
    BluetoothAdapter bluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBlue = findViewById(R.id.btnBlue);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter == null){
            Toast.makeText(MainActivity.this, "This device doesn't support Bluetooth", Toast.LENGTH_SHORT).show();
        }

        if (!bluetoothAdapter.isEnabled()){
            buttonBlue.setText("Turn Bluetooth ON");
        }else{
            buttonBlue.setText("Turn Bluetooth OFF");
        }

        buttonBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bluetoothAdapter.isEnabled()) {
                    Intent bluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(bluetoothIntent, BLUETOOTH_REQ_CODE);
                }else{
                    bluetoothAdapter.disable();
                    buttonBlue.setText("Turn Bluetooth ON");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Toast.makeText(MainActivity.this, "Bluetooth is ON", Toast.LENGTH_SHORT).show();
            buttonBlue.setText("Turn Bluetooth OFF");
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Bluetooth operation is cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}