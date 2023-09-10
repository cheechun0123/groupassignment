package com.example.anew;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ScanQR extends AppCompatActivity {

    private static final int GALLERY_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button scanCameraButton = findViewById(R.id.scanCameraButton);
        Button scanGalleryButton = findViewById(R.id.scanGalleryButton);
        Button displaydataButton = findViewById(R.id.displayButton);

        scanCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start scanning QR code from the camera
                new IntentIntegrator(ScanQR.this).initiateScan();
            }
        });

        scanGalleryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the gallery for selecting an image
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });

        displaydataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScanQR.this, CheckinRecord.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            // Handle image selected from gallery
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);

                // Use ZXing to process the bitmap for QR code scanning
                Result result = scanQRCode(bitmap);

                if (result != null) {
                    String scannedData = result.getText();

                    // Pass the scanned data to the scansuccess activity
                    Intent intent = new Intent(ScanQR.this, ScanSuccess.class);
                    intent.putExtra("scannedData", scannedData); // Pass the scanned data as an extra
                    startActivity(intent);
                } else {
                    // Handle the case where no QR code is found
                    // You can display an error message or take appropriate action
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Handle QR code scan result from the camera
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() == null) {
                    // Handle the case where scanning was canceled
                } else {
                    String scannedData = result.getContents();
                    Intent intent = new Intent(ScanQR.this, ScanSuccess.class);
                    intent.putExtra("scannedData", scannedData); // Pass the scanned data as an extra
                    startActivity(intent);
                    // Handle or display the scanned data
                }
            }
        }
    }

    // Function to decode QR code from a Bitmap using ZXing
    private Result scanQRCode(Bitmap bitmap) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] pixels = new int[width * height];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);

            RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            return reader.decode(binaryBitmap);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }}