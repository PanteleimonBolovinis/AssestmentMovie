package com.movieexplorer.mobileapp.Models;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

public class MyThread implements Runnable {
    private Handler handler;
    private boolean isRunning = true;

    Context context;

    public MyThread(Context context){
        this.context = context;
    }

    public void stopThread() {
        isRunning = false;
        handler.removeCallbacksAndMessages(null);
    }

    public void start() {
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(this, 3000);
    }

//    @Override
//    public void run() {
//        Looper.prepare();
//        handler = new Handler();
//
//        while (isRunning) {
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//
//                    Log.e("MyThread", "run: ?<><>><><><><><><<>: Thread is running every 3 seconds");
//                    System.out.println("Thread is running every 5 seconds");
//                }
//            });
//
//            try {
//                handler.postDelayed(context, 3000); // Sleep for 5 seconds
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Looper.loop();
//    }

    @Override
    public void run() {
        // Your code to be executed every 5 seconds
        // Example: Log a message

        if (isConnected()) {
//            Toast.makeText(context, "Internet Connected", Toast.LENGTH_SHORT).show();
        } else {

            showAlertDialog(context, "Oops!", "You got disconnected from the Internet");

        }

        Log.e("MyThread", "Thread is running every 5 seconds");

        if (isRunning) {
            handler.postDelayed(this, 3000);
        }
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }
    private void showAlertDialog(Context context, String title, String message) {

        stopThread();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle positive button click
                        dialog.dismiss();
                        start();
                    }
                });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}

