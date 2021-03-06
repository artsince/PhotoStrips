package com.ahmetkizilay.image.photostrips.dialogs;

import com.ahmetkizilay.image.photostrips.R;
import com.ahmetkizilay.modules.donations.PaymentDialogFragment;
import com.ahmetkizilay.modules.donations.ThankYouDialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class AboutMeDialogFragment extends DialogFragment{
	
	public static AboutMeDialogFragment newInstance() {
		AboutMeDialogFragment frag = new AboutMeDialogFragment();
		Bundle args = new Bundle();
		frag.setArguments(args);
		
		return frag;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {

        PackageManager pm = getActivity().getPackageManager();
        String versionName = "1.0.0";
        try {
            versionName = pm.getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (Exception e) {}

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage(
				"Developed by Ahmet Kizilay\n\nThis is a free and open-sourced app. Please consider making a donation if you enjoy this app.\n\nPERISONiC Sound And Media")
				.setCancelable(false)
				.setTitle("PhotoStrips - v" + versionName)
				.setIcon(R.drawable.ic_photostrips)
				.setNeutralButton("DONATE", new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
                        if(mCallback != null) {
                            mCallback.onDonationsRequested();
                        }
					}
				})
                .setPositiveButton("RATE ME", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int which) {

                       Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName()));
                       startActivity(rateIntent);
                   }
                });
		return builder.create();
	}

    private RequestListener mCallback;

    public void setRequestListener(RequestListener callback) {
        this.mCallback = callback;
    }

    public interface RequestListener {
        public void onDonationsRequested();
    }

}
