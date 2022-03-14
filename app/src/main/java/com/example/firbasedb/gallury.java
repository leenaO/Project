package com.example.firbasedb;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

public class gallury extends AppCompatActivity {
    ImageView imageView;
    TextView button ;
    private static String randoumkey;
    Bitmap bitmap;
    Button save;
    Uri uri;
    FirebaseStorage storage ;
    StorageReference storageReference;
    StorageReference riversRef;
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    ActivityResultLauncher<Intent> someActivityResultLauncher1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        imageView = (ImageView) findViewById(R.id.imageView3);
        button = (TextView) findViewById(R.id.textView11);
        save = (Button) findViewById(R.id.save);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("profile");

        getSomeActivityResultLauncher();




    }



    public void uplodpictor() {

        randoumkey= UUID.randomUUID().toString();
        riversRef = storageReference.child("images/").child(randoumkey);

        System.out.println("path name"+riversRef.getPath());
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = riversRef.putBytes(data);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(),"failed to upload",Toast.LENGTH_LONG).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Snackbar.make(findViewById(android.R.id.content),"image upload",Snackbar.LENGTH_LONG).show();
            }
        });




    }



//button on click to open dialog
    public void gallery(View view) {

        showImagePicDialog();
    }






//to show image in imageview
    public ActivityResultLauncher<Intent> getSomeActivityResultLauncher() {



        someActivityResultLauncher1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        Intent data = result.getData();
                        uri = data.getData();
                        imageView.setImageURI(uri);




                    }
                }
        );

        someActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                        Intent data = result.getData();
                        bitmap= (Bitmap)  data.getExtras().get("data");
                        imageView.setImageBitmap(bitmap);

                    }
                }
        );
        return someActivityResultLauncher;
    }




    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (which == 0) {

                            if (!checkCameraPermission()) {
                                requestCameraPermission();
                                Toast.makeText(gallury.this, "successful Image",Toast.LENGTH_LONG).show();
                            }
                        } else if (which == 1) {

                            if (!checkStoragePermission()) {
                                requestStoragePermission();
                                Toast.makeText(gallury.this, "successful add Image",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                }
        );
        builder.create().show();
        Toast.makeText(this, "cant open", Toast.LENGTH_LONG).show();

    }
    private Boolean checkStoragePermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    private Boolean checkCameraPermission() {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
        return result && result1;
    }
    private void requestStoragePermission() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        someActivityResultLauncher1.launch(intent);

    }
    private void requestCameraPermission() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        someActivityResultLauncher.launch(intent);
    }


// Button Save
    public void Save(View view) {

        uplodpictor();
        Toast.makeText(gallury.this,"successfully insert",Toast.LENGTH_LONG).show();




    }}
