package org.owntracks.android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
import timber.log.Timber;

public class QrCodePopUp extends AppCompatActivity {

    private ImageView qrCodeIV;
    private TextView showInfoParkingLocation;
    private QRGSaver qrgSaver;
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_pop_up);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String keyID = extras.getString("keyID");
            String fieldName = extras.getString("fieldName");
            int accessCode = extras.getInt("accessCode");

            showInfoParkingLocation = (TextView) findViewById(R.id.infoParkingLocation);
            showInfoParkingLocation.setText("You are in parking location ");//+keyID+ " - "+fieldName);

            qrCodeIV = (ImageView) findViewById(R.id.qrCodeImageView);
            qrgEncoder = new QRGEncoder(String.valueOf(accessCode), null, QRGContents.Type.TEXT, 1000);

            Timber.d("KeyID is "+keyID);
            Timber.d("FieldName is "+fieldName);
            Timber.d("AccessCode is "+accessCode);
            try {
                // Getting QR-Code as Bitmap
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.getBitmap();
                // the bitmap is set inside our image
                // view using .setimagebitmap method.
                qrCodeIV.setImageBitmap(bitmap);
            } catch (Exception e) {
                Log.e("Tag", e.toString());
            }


        }
    }
}