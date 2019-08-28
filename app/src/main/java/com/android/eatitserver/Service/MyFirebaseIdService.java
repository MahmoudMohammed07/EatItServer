package com.android.eatitserver.Service;

import com.android.eatitserver.Common.Common;
import com.android.eatitserver.Model.Token;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String tokenRefreshed = FirebaseInstanceId.getInstance().getToken();
        updateToServer(tokenRefreshed);
    }

    private void updateToServer(String tokenRefreshed) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tokens = database.getReference("Token");
        Token token = new Token(tokenRefreshed, true);
        tokens.child(Common.currentUser.getPhone()).setValue(token);
    }
}
