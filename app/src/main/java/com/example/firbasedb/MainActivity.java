package com.example.firbasedb;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class MainActivity extends AppCompatActivity  {

    ImageView imageView;
    TextView button ;
    EditText name , email , phonNo;
    Button save , sting;
    FirebaseStorage storage ;
    StorageReference storageReference,riversRef;
    Uri uri;
    profile profile;
    Bitmap bitmap;
    String e_mail , Name ,  avatar;
    DatabaseReference myRef;
    FirebaseDatabase database;
    Integer phone;
    String newRandomId = String.valueOf(Calendar.getInstance().getTimeInMillis());

    public static String randoumkey;
    ActivityResultLauncher<Intent> someActivityResultLauncher;
    ActivityResultLauncher<Intent> someActivityResultLauncher1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.editprofile);

        name = (EditText) findViewById(R.id.EditText1);
        email = (EditText) findViewById(R.id.EditText2);
        phonNo = (EditText) findViewById(R.id.EditText3);
sting = (Button)findViewById(R.id.button4);
        imageView = (ImageView) findViewById(R.id.imageView3);
        button = (TextView) findViewById(R.id.textView11);
        save = (Button) findViewById(R.id.save);
        View view = this.getCurrentFocus();

        getSomeActivityResultLauncher();
        showalldata();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference("profile");

        profile = new profile();


    }

    public void gallery(View view) {

        showImagePicDialog();
    }

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



    public void uplodpictor() {

        randoumkey= UUID.randomUUID().toString();
        riversRef = storageReference.child("images/").child(randoumkey);

         //storageReference = storage.getReference().child("image/").child(randomkey());
        System.out.println("path name"+riversRef.getPath());

//       riversRef.putFile(uri)
//               .addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(getApplicationContext(),"failed to upload",Toast.LENGTH_LONG).show();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Snackbar.make(findViewById(android.R.id.content),"image upload",Snackbar.LENGTH_LONG).show();
//
//            }
//        });
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
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
                Snackbar.make(findViewById(android.R.id.content),"image upload",Snackbar.LENGTH_LONG).show();
            }
        });




    }

    private void showImagePicDialog() {
        String options[] = {"Camera", "Gallery"};
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pick Image From");
        builder.setItems(options, new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (which == 0) {

                            if (!checkCameraPermission()) {
                                requestCameraPermission();
                                Toast.makeText(MainActivity.this, "successful Image",Toast.LENGTH_LONG).show();
                            }
                        } else if (which == 1) {

                            if (!checkStoragePermission()) {
                                requestStoragePermission();
                                Toast.makeText(MainActivity.this, "successful add Image",Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                }
        );
        builder.create().show();
        Toast.makeText(MainActivity.this, "cant open", Toast.LENGTH_LONG).show();

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



    public void Save(View view) {
        database = FirebaseDatabase.getInstance();
         myRef = database.getReference("Account").child("Users");

     Name = name.getText().toString();
    e_mail = email.getText().toString().trim();
     phone = Integer.valueOf(phonNo.getText().toString());
     avatar = randoumkey;


        if(Name.isEmpty()){
            name.setError("Not Empty filed Allowed... ");
            name.requestFocus();
            return;
        }

        if( e_mail.isEmpty()){
                email.setError("Not Empty filed Allowed... ");
                email.requestFocus();
                return;
            }


        if( phonNo.getText().toString().isEmpty()){
                       phonNo.setError("Not Empty filed Allowed... ");
                       phonNo.requestFocus();
                return;
                }

        profile.setAvatar(avatar);
        profile.setName(Name);
        profile.setEmail(e_mail);
        profile.setPhonNo(phone);

       // myRef.child(newRandomId).setValue(profile);
        uplodpictor();
if (changephone()){
    Toast.makeText(MainActivity.this, "your update data...",Toast.LENGTH_LONG).show();
}else {
    Toast.makeText(this, "the data is same not change", Toast.LENGTH_SHORT).show();
}


    }//037c309808ac

    private boolean changephone() {
        if (!Name.equals(name.getText().toString())){
            myRef.child(newRandomId).child("phonNo").setValue(name.getText().toString());
            return true;
        }else
            return false;

    }

//    private boolean changeemail() {
//    }
//
//    private boolean changename() {
//    }
private void showalldata(){
        Intent intent = getIntent();
String user_name = intent.getStringExtra("name");

    String phone_no = intent.getStringExtra("phonNo");

    String email_e = intent.getStringExtra("email");
    String avater_path = intent.getStringExtra("avatar");

    name.setText(user_name);  email.setText(email_e);
    phonNo.setText(phone_no);

    myRef = database.getReference("Account").child("Users");



}

}

//changename() || changeemail()||


//    profile user1 = new profile();
//                            user1.setEmail(e_mail);
//                                    user1.setPhonNo(Integer.valueOf(phone));
//                                    FirebaseDatabase.getInstance().getReference("Account").child("Users")
//                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                                    .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
//@Override
//public void onComplete(@NonNull Task<Void> task) {
//        if (task.isSuccessful()) {
//        Toast.makeText(SignUp.this, "Successfully Register User Created.", Toast.LENGTH_SHORT).show();
//
//
//        } else {
//        Toast.makeText(SignUp.this, "Error! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        }
//        });


//
//
//    private void isuser() {
//        String userenteremail = email.getText().toString().trim();
//        Integer userenterphone = Integer.valueOf(phoneNo.getText().toString().trim());
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Account").child("Users");
//        Query checkuser = reference.orderByChild("email").equalTo(userenteremail);
//        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    String phonenofromdata = snapshot.child(userenteremail).child("phonNo").getValue(String.class);
//                    if(phonenofromdata.equals(userenterphone)){
//                        String namefromdata = snapshot.child(String.valueOf(userenterphone)).child("name").getValue(String.class);
//                        String emailfromdata = snapshot.child(String.valueOf(userenterphone)).child("email").getValue(String.class);
//                        Intent intent = new Intent(getApplicationContext(),profile.class);
//                        intent.putExtra("name",namefromdata);
//                        intent.putExtra("phonNo",phonenofromdata);
//                        intent.putExtra("email",emailfromdata);
//                        startActivity(intent);
//
//                    }
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//});
























//



//name.getText().toString(), email.getText().toString(),  phonNo.getText().toString()



