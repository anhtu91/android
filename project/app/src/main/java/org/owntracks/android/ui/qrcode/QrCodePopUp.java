package org.owntracks.android.ui.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.support.JWTUtils;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;
import timber.log.Timber;

public class QrCodePopUp extends AppCompatActivity {

    private ImageView qrCodeIV;
    private TextView showInfoParkingLocation;
    private QRGSaver qrgSaver;

    public static QrCodePopUp instance = null;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_pop_up);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String strJWT = extras.getString("JWT");

            try {
                String jwt = JWTUtils.decodeJWT(strJWT);
                JSONObject jwtObject = new JSONObject(jwt);
                String keyID = jwtObject.getString("keyID");
                String fieldName = jwtObject.getString("fieldName");
                String time = jwtObject.getString("time");
                String date = jwtObject.getString("date");

                showInfoParkingLocation = (TextView) findViewById(R.id.infoParkingLocation);
                showInfoParkingLocation.setText("You are in parking location "+keyID+ " - "+fieldName+"\n at "+time+" on "+date);

                qrCodeIV = (ImageView) findViewById(R.id.qrCodeImageView);
                qrgEncoder = new QRGEncoder(strJWT, null, QRGContents.Type.TEXT, 1000);

                Timber.d("KeyID is "+keyID);
                Timber.d("FieldName is "+fieldName);

                // Getting QR-Code as Bitmap
                // getting our qrcode in the form of bitmap.
                bitmap = qrgEncoder.getBitmap();
                // the bitmap is set inside our image
                // view using .setimagebitmap method.
                qrCodeIV.setImageBitmap(bitmap);
            } catch (Exception e) {
                Timber.e("Error while display QRCode from received JWT "+e.toString());
            }

            qrCodeIV.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    finish();
                    return true;
                }
            });
        }

        instance = this;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @Override
    public void finish(){
        super.finish();
        instance = null;
    }
}