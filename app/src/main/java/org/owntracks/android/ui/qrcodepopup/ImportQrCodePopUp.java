package org.owntracks.android.ui.qrcodepopup;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.owntracks.android.R;
import org.owntracks.android.support.JWTUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import timber.log.Timber;

public class ImportQrCodePopUp extends AppCompatActivity {
    private ImageView qrCodeIV;
    private TextView showInfoInvite;
    private final String formatHour = "HH:mm:ss '";
    private final String formatDate = "' dd-MM-yyyy";
    public static ImportQrCodePopUp instance = null;

    Bitmap bitmap;
    QRGEncoder qrgEncoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_imported_qr_code_pop_up);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String strJWT = extras.getString(getResources().getString(R.string.jwt));

            try {
                String jwt = JWTUtils.decodeJWT(strJWT);
                JSONObject jwtObject = new JSONObject(jwt);
                String keyID = jwtObject.getString(getResources().getString(R.string.keyid));
                String fieldName = jwtObject.getString(getResources().getString(R.string.fieldname));
                String senderUser = jwtObject.getString(getResources().getString(R.string.senderUser));
                long tst = jwtObject.getLong(getResources().getString(R.string.tst));
                SimpleDateFormat formatter = new SimpleDateFormat(formatHour+getString(R.string.importQRCodePopUpFour)+formatDate);
                formatter.setTimeZone(TimeZone.getDefault()); //formatter.setTimeZone(TimeZone.getTimeZone(getResources().getString(R.string.GMT)));
                String strDateTime = formatter.format(new Date(tst*1000));

                showInfoInvite = (TextView) findViewById(R.id.infoInvited);
                showInfoInvite.setText(getResources().getString(R.string.importQRCodePopUpOne)+"\n"+keyID+ " - "+fieldName+getResources().getString(R.string.importQRCodePopUpTwo)+strDateTime+getResources().getString(R.string.importQRCodePopUpThree)+senderUser);

                qrCodeIV = (ImageView) findViewById(R.id.importQrCodeImageView);
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
