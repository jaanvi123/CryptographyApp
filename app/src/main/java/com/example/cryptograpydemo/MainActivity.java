package com.example.cryptograpydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView,textViewd;
    Button button,buttond;
    Cipher cipher;
    public static final String ALGO="AES";
    Key key;
    byte[] def_key=new byte[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText)findViewById(R.id.edittextp);
        textView=(TextView)findViewById(R.id.textview);
        button=(Button)findViewById(R.id.button);
        buttond=(Button)findViewById(R.id.buttond);
        textViewd=(TextView)findViewById(R.id.textviewd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String text=  editText.getText().toString();
                key=new SecretKeySpec(def_key,ALGO);
                String enc_dat=encryptdata(text);
                textView.setText(enc_dat);
            }
        });

        buttond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String dect= textView.getText().toString();
                key=new SecretKeySpec(def_key,ALGO);
                String dec_dat=decryptdata(dect,key);
                textViewd.setText(dec_dat);
            }
        });
    }
    String encryptdata(String Data)
    {
        try
        {
            cipher=Cipher.getInstance(ALGO);
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] encvalue=cipher.doFinal(Data.getBytes());
            String encryptedvalue= Base64.encodeToString(encvalue,Base64.DEFAULT);
            return encryptedvalue;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
    String decryptdata(String Data,Key keydec)
    {
        try
        {
            cipher=Cipher.getInstance(ALGO);
            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] temp=Base64.decode(Data.getBytes(),Base64.DEFAULT);
            byte[] decvalue=cipher.doFinal(temp);
            String decryptvalue= new String(decvalue);
            return decryptvalue;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "";
        }
    }
}