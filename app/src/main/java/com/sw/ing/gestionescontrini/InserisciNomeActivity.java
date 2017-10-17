package com.sw.ing.gestionescontrini;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

public class InserisciNomeActivity extends Activity {

    private EditText nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserisci_nome);
        nome = findViewById(R.id.inserisci_nome);

    }

    public void onClick(View v){
        exit();
    }

    public void exit(){
        Intent data = new Intent();
        String text = nome.getText().toString();
        data.setData(Uri.parse(text));
        setResult(RESULT_OK, data);
//---close the activity---
        finish();
    }

}
