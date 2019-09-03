package com.android.eatitserver.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.android.eatitserver.Model.Request;
import com.android.eatitserver.Model.User;
import com.android.eatitserver.Remote.APIService;
import com.android.eatitserver.Remote.FCMRetrofitClient;
import com.android.eatitserver.Remote.IGeoCoordinates;
import com.android.eatitserver.Remote.RetrofitClient;

public class Common {
    public static User currentUser;
    public static Request currentRequest;

    public static String PHONE_TEXT = "userText";

    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";

    public static final int PICK_IMAGE_REQUEST = 71;

    public static final String BASE_URL = "https://maps.googleapis.com";
    private static final String FCM_URL = "https://fcm.googleapis.com/";

    public static String convertCodeToStatus(String code) {
        if (code.equals("0")) {
            return "Placed";
        } else if (code.equals("1")) {
            return "On my way";
        } else {
            return "Shipped";
        }
    }

    public static IGeoCoordinates getGeoCodeServices() {
        return RetrofitClient.getClient(BASE_URL).create(IGeoCoordinates.class);
    }

    public static APIService getFCMClient() {
        return FCMRetrofitClient.getClient(FCM_URL).create(APIService.class);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float scaleX = newWidth / (float) bitmap.getWidth();
        float scaleY = newHeight / (float) bitmap.getHeight();
        float pivotX = 0, pivotY = 0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX, scaleY, pivotX, pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, 0, 0, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

}
