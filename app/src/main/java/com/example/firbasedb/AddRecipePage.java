package com.example.firbasedb;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.Random;

public class AddRecipePage extends AppCompatActivity {
    Button addIng;
    ImageView recipe_photo;
    TextView take_picture;
    EditText recapiname, howtomake;
    ActivityResultLauncher<Intent> someActivityResultLauncher1;

    Uri uri;
    int request_code;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    ProgressDialog progressDialog;
    ArrayAdapter<String> myAdpt;
    Recipe recipe_set;


    public void add_post(View view) {

        progressDialog.setMessage("Promote Post...");
        progressDialog.show();
        inputrecipename = recapiname.getText().toString().trim();
        inputhowmaket = howtomake.getText().toString().trim();
        recipe_set = new Recipe();

        addpost();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe_page);
        Spinner mySpin = (Spinner) findViewById(R.id.spinnerD);
        myAdpt = new ArrayAdapter<String>(AddRecipePage.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Diet));
        myAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpin.setAdapter(myAdpt);

        addIng = (Button) findViewById(R.id.buttonIng);
        recipe_photo = (ImageView) findViewById(R.id.photo_rcipe);
        take_picture = (TextView) findViewById(R.id.take_picture);
        recapiname = (EditText) findViewById(R.id.addpname);
        howtomake = (EditText) findViewById(R.id.howtomake);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("please wait");
        progressDialog.setCanceledOnTouchOutside(true);
        checkuser();
        getSomeActivityResultLauncher();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("recipe").push();
        storageReference = FirebaseStorage.getInstance().getReference("images_Post/");


        recapiname.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
        howtomake.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

    }

    private void checkuser() {
        firebaseAuth = FirebaseAuth.getInstance();
       user = firebaseAuth.getCurrentUser();
        if (user !=null) {
            addpost();
        } else {
            startActivity(new Intent(getApplicationContext(), SignIn.class));
            finish();
        }
    }
    public void resIng(View v) {
       startActivity(new Intent(AddRecipePage.this,addRecpieIngredients.class));

    }



    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void add_Image(View view) {
        showImagePicDialog();


    }

    public ActivityResultLauncher<Intent> getSomeActivityResultLauncher() {


        someActivityResultLauncher1 = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent data = result.getData();
                    if (result.getResultCode() == AppCompatActivity.RESULT_OK && data != null) {
                        if (request_code == 100) {
                            uri = data.getData();
                            recipe_photo.setImageURI(uri);
                            Toast.makeText(getApplicationContext(), "uri gallery is " + uri.getPath(), Toast.LENGTH_LONG).show();

                        } else if (request_code == 200) {
//
                            try {
                                ContentResolver cr = getContentResolver();
                                try {
                                    // Creating a Bitmap with the image Captured
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(cr, uri);
                                    // Setting the bitmap as the image of the
                                    recipe_photo.setImageBitmap(bitmap);
                                    Toast.makeText(getApplicationContext(), "uri is camera" + uri.getPath(), Toast.LENGTH_LONG).show();

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } catch (IllegalArgumentException e) {
                                if (e.getMessage() != null)
                                    Log.e("Exception", e.getMessage());
                                else
                                    Log.e("Exception", "Exception");
                                e.printStackTrace();

                            }
                        }
                    }
                }
        );

        return someActivityResultLauncher1;
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
                            }
                        } else if (which == 1) {

                            if (!checkStoragePermission()) {
                                requestStoragePermission();

                            }
                        }
                    }
                }
        );
        builder.create().show();
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
        request_code = 100;
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        someActivityResultLauncher1.launch(intent);

    }

    private void requestCameraPermission() {
        request_code = 200;
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new-photo-name.jpg");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        someActivityResultLauncher1.launch(intent);


    }

  private  String inputrecipename, inputhowmaket;

    private String getfileextiontio(Uri u){
        ContentResolver resolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(resolver.getType(u));
    }
    private void addpost() {

        if (uri != null) {
          //  DatabaseReference recipedata = FirebaseDatabase.getInstance().getReference().child("recipe").push();

            StorageReference storageReference1 =storageReference.child(System.currentTimeMillis()+"."+getfileextiontio(uri));
            storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            DatabaseReference namerefrence = FirebaseDatabase.getInstance().getReference().child("Account").child(firebaseAuth.getUid()).child("name");
                            namerefrence.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String namevalue = snapshot.getValue(String.class);
                                    recipe_set.setRecipeName(inputrecipename);
                                    recipe_set.setHowmake(inputhowmaket);
                                    recipe_set.setRecipeMaker(namevalue);
                                    recipe_set.setDite(myAdpt.getItem(1));
                                    recipe_set.setImg(String.valueOf(uri));
                                    recipe_set.setID(firebaseAuth.getUid());
                                    recipe_set.setID(databaseReference.getKey());
                                    recipe_set.setRcipeID(databaseReference.push().getKey());
                                   // recipedata.setValue(recipe_set);
                                    databaseReference.setValue(recipe_set).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(getApplicationContext(), "Finally is completed", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });



                            Toast.makeText(getApplicationContext(), "great! your add post", Toast.LENGTH_LONG).show();

                            progressDialog.dismiss();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();


                        }
                    });
                }  }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Error!" + e.getMessage(), Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                        }
                    });


        } else {
            Toast.makeText(getApplicationContext(), "Please Chose Image", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
    }



}



