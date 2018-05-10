package com.example.gs.rentme.core.users.getall;

import com.example.gs.rentme.data_model.createaccount;
import com.example.gs.rentme.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GetUsersInteractor implements GetUsersContract.Interactor {
    private static final String TAG = "GetUsersInteractor";

    private GetUsersContract.OnGetAllUsersListener mOnGetAllUsersListener;


    public GetUsersInteractor(GetUsersContract.OnGetAllUsersListener onGetAllUsersListener) {
        this.mOnGetAllUsersListener = onGetAllUsersListener;
    }


    @Override
    public void getAllUsersFromFirebase() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        String userEmail = firebaseAuth.getCurrentUser().getEmail();
        userEmail = userEmail.replace(".","");

        final String finalUserEmail = userEmail;

        final String finalUserEmail1 = userEmail;
        FirebaseDatabase.getInstance().getReference().child("chat_rooms").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<User> users = new ArrayList<>();

                if (dataSnapshot.hasChildren()) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {


                        if (dataSnapshot1.getKey().split("_")[0].equals(finalUserEmail1) || dataSnapshot1.getKey().split("_")[1].equals(finalUserEmail1))

                        {
                            if(dataSnapshot1.getKey().split("_")[1].equals(finalUserEmail1))
                            {
                                User data = new User();

                                data.email = dataSnapshot1.getKey().split("_")[0];

                                data.firebaseToken = dataSnapshot1.getKey().split("_")[0];

                                data.uid = dataSnapshot1.getKey().split("_")[0];

                                users.add(data);
                            }

                            else {

                                User data = new User();

                                data.email = dataSnapshot1.getKey().split("_")[1];

                                data.firebaseToken = dataSnapshot1.getKey().split("_")[1];

                                data.uid = dataSnapshot1.getKey().split("_")[1];

                                users.add(data);
                            }



                        }
                    }

                }

                mOnGetAllUsersListener.onGetAllUsersSuccess(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                mOnGetAllUsersListener.onGetAllUsersFailure(databaseError.getMessage());
            }
        });
    }

    @Override
    public void getChatUsersFromFirebase() {
        /*FirebaseDatabase.getInstance().getReference().child(Constants.ARG_CHAT_ROOMS).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> dataSnapshots=dataSnapshot.getChildren().iterator();
                List<User> users=new ArrayList<>();
                while (dataSnapshots.hasNext()){
                    DataSnapshot dataSnapshotChild=dataSnapshots.next();
                    dataSnapshotChild.getRef().
                    Chat chat=dataSnapshotChild.getValue(Chat.class);
                    if(chat.)4
                    if(!TextUtils.equals(user.uid,FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                        users.add(user);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
