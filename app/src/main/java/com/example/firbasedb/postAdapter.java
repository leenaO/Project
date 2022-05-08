package com.example.firbasedb;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class postAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private ArrayList<Recipe> mContentList;
    FirebaseAuth firebaseAuth;

//postAdapter

    public postAdapter(Context mContext, ArrayList<Recipe> recipeList) {
        this.mContext = mContext;
        this.mContentList = recipeList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.account_custom_item, parent, false);
        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView rImg;
        private TextView rName;
        private TextView rMaker;
        private ImageButton delete;
        private ImageButton postFav;
        private ImageButton edit;


        public ViewHolder(View itemView) {
            super(itemView);

            // Find all views ids edit
            cardView = (CardView) itemView.findViewById(R.id.postCard);
            rImg = (ImageView) itemView.findViewById(R.id.postImg);
            rName = (TextView) itemView.findViewById(R.id.postNam);
            delete = (ImageButton) itemView.findViewById(R.id.delete);
//            edit = (ImageButton) itemView.findViewById(R.id.edit);


        }
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mainHolder,  final int position) {
        ViewHolder holder = (ViewHolder) mainHolder;
        firebaseAuth = FirebaseAuth.getInstance();

        final Recipe model = mContentList.get(position);
        // setting data over views
        String imgUrl = model.getImg();
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .into(holder.rImg);

        }

        holder.rName.setText(model.getRecipeName());
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("recipe");
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(view.getRootView().getContext());
                builder1.setTitle("Delete post");
                builder1.setMessage("Are you sure that you want delete the post?");
                builder1.setCancelable(true);
                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ref.child(model.getRecipeKey()).removeValue();
                    }
                });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        });
    }
    @Override
    public int getItemCount() {
        return mContentList.size();
    }

}









//
//"https://images.immediate.co.uk/production/volatile/sites/30/2022/01/Roast-Pork-Shoulder-686b22a.jpg"
//"https://images.immediate.co.uk/production/volatile/sites/30/2022/01/Kale_Kimchi_Fried_Rice_broken_yolk-e1288a9.jpg"
//"https://images.immediate.co.uk/production/volatile/sites/30/2022/01/Roast-Pork-Shoulder-686b22a.jpg"
//
//
//"https://images.immediate.co.uk/production/volatile/sites/30/2022/01/Roast-Pork-Shoulder-686b22a.jpg"
//"https://images.immediate.co.uk/production/volatile/sites/30/2022/01/Roast-Pork-Shoulder-686b22a.jpg"
//



