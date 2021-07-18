package org.owntracks.android.ui.qrcodepopup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.support.JWTUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import timber.log.Timber;

public class QrCodePopUp extends AppCompatActivity {

    private ImageView qrCodeIV;
    private TextView showInfoParkingLocation;
    private final String formatHour = "HH:mm:ss'";
    private final String formatDate = "'dd-MM-yyyy";
    public static QrCodePopUp instance = null;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_qr_code_pop_up);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String strJWT = extras.getString(getResources().getString(R.string.jwt));

            try {
                String jwt = JWTUtils.decodeJWT(strJWT);
                JSONObject jwtObject = new JSONObject(jwt);
                String keyID = jwtObject.getString(getResources().getString(R.string.keyid));
                String fieldName = jwtObject.getString(getResources().getString(R.string.fieldname));
                long tst = jwtObject.getLong(getResources().getString(R.string.tst));
                SimpleDateFormat formatter = new SimpleDateFormat(formatHour+getString(R.string.qrCodePopUpThree)+formatDate);
                formatter.setTimeZone(TimeZone.getTimeZone(getResources().getString(R.string.GMT)));
                String strDateTime = formatter.format(new Date(tst*1000));

                showInfoParkingLocation = (TextView) findViewById(R.id.infoParkingLocation);
                showInfoParkingLocation.setText(getString(R.string.qrCodePopUpOne)+keyID+" - "+fieldName+getString(R.string.qrCodePopUpTwo)+strDateTime);

                qrCodeIV = (ImageView) findViewById(R.id.qrCodeImageView);
                qrgEncoder = new QRGEncoder(strJWT, null, QRGContents.Type.TEXT, 1000);

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