package com.labo.kaji.swipeawaydialog.example;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.labo.kaji.swipeawaydialog.support.v4.SwipeAwayDialogFragment;

import java.util.Random;

/**
 * @author kakajika
 * @since 15/08/15.
 */
public class ExampleDialogFragment extends SwipeAwayDialogFragment {

    private static final Random sRandom = new Random();

    private interface DialogBuilder {
        @NonNull
        Dialog create(Context context, ExampleDialogFragment fragment);
    }

    public enum Type implements DialogBuilder {
        APPCOMPAT() {
            @Override
            public
            @NonNull
            Dialog create(Context context, ExampleDialogFragment fragment) {
                return new android.support.v7.app.AlertDialog.Builder(context)
                        .setTitle("Title")
                        .setMessage("Message")
                        .setIcon(R.mipmap.ic_launcher)
                        .setPositiveButton("OK", null)
                        .setNegativeButton("Cancel", null)
                        .create();
            }
        }
    }

    public static ExampleDialogFragment newInstance(Type type) {
        ExampleDialogFragment f = new ExampleDialogFragment();
        Bundle args = new Bundle();
        args.putSerializable("type", type);
        f.setArguments(args);
        return f;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Type type = (Type) getArguments().getSerializable("type");
        return type.create(getActivity(), this);
    }

}
