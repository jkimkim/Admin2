package app.web.jkimtech.admin2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    private AppBarConfiguration mAppBarConfiguration;
    private LinearLayout adminOptions;
    private StorageReference storageReference;
    private List<User> downloadUrls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        downloadUrls = new ArrayList<>();
        adminOptions = findViewById(R.id.adminSection);
        adminOptions.setOnClickListener(v -> {
            Dialog progressDialog=new Dialog(MainActivity.this);
            progressDialog.setContentView(R.layout.progressdialog);
            progressDialog.setCancelable(false);
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            progressDialog.show();

            new Fetcher().fetchApplications(new CompleteListener() {
                @Override
                public void onUploadFetched(List<Upload> uploads) {
                    progressDialog.dismiss();
                    AdminAdapterDialog.showDialog(getSupportFragmentManager(),uploads);
                }
            });
        });
        checkUserStatus();
    }

    private void checkUserStatus() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // user is signed in stay here
            // set email of logged in user
        } else {
            // user not signed in, go to main activity
            startActivity(new Intent(MainActivity.this, AdminLogin.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("AdminActivity", "popping backstack");
            fm.popBackStack();
        } else {
            Log.i("AdminActivity", "nothing on backstack, calling super");
            super.onBackPressed();
        }
    }

}