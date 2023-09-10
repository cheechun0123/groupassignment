package com.example.asgm2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

public class PostAnnouncement extends AppCompatActivity {

    private EditText titleET, contentET;
    private Button postAnnouncementBtn, selectImgBtn;
    private String title, content;
    private FirebaseFirestore db;
    private Uri imageUri;
    private ImageView announcementImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_announcement);

        titleET = findViewById(R.id.titleET);
        contentET = findViewById(R.id.contentET);
        postAnnouncementBtn = findViewById(R.id.addAnnouncementBtn);
        selectImgBtn = findViewById(R.id.selectImgBtn);
        announcementImg = findViewById(R.id.imageView);

        db = FirebaseFirestore.getInstance();

        selectImgBtn.setOnClickListener(v -> imageChooser());
        postAnnouncementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = titleET.getText().toString();
                content = contentET.getText().toString();

                Announcements announcements = new Announcements(title, content);
                db.collection("announcements")
                        .add(announcements)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(PostAnnouncement.this,"Your Announcement has posted", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(PostAnnouncement.this, "Fail to post announcement", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    void imageChooser(){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        launchSomeActivity.launch(i);
    }

    ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Intent data = result.getData();

            if (data != null
                    && data.getData() != null) {
                imageUri = data.getData();
                Bitmap selectedImageBitmap = null;
                try {
                    selectedImageBitmap
                            = MediaStore.Images.Media.getBitmap(
                            this.getContentResolver(),
                            imageUri);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                announcementImg.setImageBitmap(
                        selectedImageBitmap);
            }
        }
    });
}