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

import org.owntracks.android.R;

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
            String keyID = extras.getString("keyID");
            String fieldName = extras.getString("fieldName");
            String timeInParkingSlot = extras.getString("time");
            String dateInParkingSlot = extras.getString("date");
            int accessCode = extras.getInt("accessCode");

            showInfoParkingLocation = (TextView) findViewById(R.id.infoParkingLocation);
            showInfoParkingLocation.setText("You are in parking location "+keyID+ " - "+fieldName+"\n at "+timeInParkingSlot+" "+dateInParkingSlot);

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
    public void finish(){
        super.finish();
        instance = null;
    }
}