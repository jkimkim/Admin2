package app.web.jkimtech.admin2;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Fetcher {
    private ValueEventListener eventListener, genderListener;
    private ValueEventListener eventListener2;
    List<Upload> uploadList = new ArrayList<>();

    public void fetchApplications(CompleteListener listener) {
        Query reference = FirebaseDatabase.getInstance().getReference("requests").orderByChild("status").equalTo("wait for allocation");
        eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.e("data", dataSnapshot.toString());
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }
                Log.e("uploadlist", uploadList.toString());
                reference.removeEventListener(eventListener);
                listener.onUploadFetched(uploadList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addListenerForSingleValueEvent(eventListener);

        Query reference2 = FirebaseDatabase.getInstance().getReference("requests").orderByChild("status").equalTo("Allocated");

        eventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Log.e("data", dataSnapshot.toString());
                    Upload upload = dataSnapshot.getValue(Upload.class);
                    uploadList.add(upload);
                }
                Log.e("uploadlist", uploadList.toString());
                reference2.removeEventListener(eventListener2);
                listener.onUploadFetched(uploadList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference2.addListenerForSingleValueEvent(eventListener2);

    }


}
