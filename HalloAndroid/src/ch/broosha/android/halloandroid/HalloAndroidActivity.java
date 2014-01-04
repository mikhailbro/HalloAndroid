package ch.broosha.android.halloandroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HalloAndroidActivity extends Activity implements OnClickListener {
    
	private static final int WILLKOMMEN_DIALOG = 1;
	
	private TextView textViewHalloAndroid;
	private EditText editTextVornameNachname;
	private Button buttonSagHallo;
	private Button buttonToast;
	private Button buttonWillkommen;
	private Button buttonStartActivity2;
	private ImageView imageViewHalloAndroidKopf;
	private AnimationDrawable animatedSkater; 
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halloandroidlayout);
        
        textViewHalloAndroid = (TextView)findViewById(R.id.textViewHalloAndroid);
        editTextVornameNachname = (EditText)findViewById(R.id.editTextVornameNachname);
    	buttonSagHallo = (Button)findViewById(R.id.buttonSagHallo);
    	buttonToast = (Button)findViewById(R.id.buttonToast);
    	buttonWillkommen = (Button)findViewById(R.id.buttonWillkommen);
    	buttonStartActivity2 = (Button)findViewById(R.id.buttonStartActivity2);
    	imageViewHalloAndroidKopf = (ImageView)findViewById(R.id.imageViewHalloAndroidKopf);
    	
    	buttonSagHallo.setOnClickListener(this);
    	buttonToast.setOnClickListener(this);
    	buttonWillkommen.setOnClickListener(this);
    	
    	buttonStartActivity2.setOnClickListener(
			new OnClickListener() {
				@Override
				public void onClick(View v) {
					startActivity2();
				}
			}
    	);
    	
    	if (this.imageViewHalloAndroidKopf.getDrawable() instanceof AnimationDrawable) {
    		animatedSkater = (AnimationDrawable) this.imageViewHalloAndroidKopf.getDrawable();
    		// Animation verzögert starten:
    		Handler handler = new Handler(getMainLooper());
    		handler.postDelayed(new Runnable(){
    			@Override
    			public void run() {
    				animatedSkater.start();
    			}
    		}
    		, 1500);
    	}
    	
    }

	protected void startActivity2() {
		
		// explizites Intent zum Aufrufen von Activity HalloAndroid2Activity:
		Intent intent = new Intent(this, HalloAndroid2Activity.class);
		startActivity(intent);
	}

	@Override
	public void onClick(View view) {
		
		switch (view.getId()) {
			case R.id.buttonSagHallo:
				sagHallo();
				break;
			case R.id.buttonToast:
				toastAussprechen();
				break;
			case R.id.buttonWillkommen:
				willkommen();
				break;
			default:
			break;
		}
	}

	private void willkommen() {
		this.showDialog(WILLKOMMEN_DIALOG);
		
	}

	private void sagHallo() {
		CharSequence vornameNachname = this.editTextVornameNachname.getText();
		CharSequence saghalloText = this.getResources().getString(R.string.saghallotext, vornameNachname);
		this.textViewHalloAndroid.setText(saghalloText);
		
	}

	private void toastAussprechen() {
		CharSequence vornameNachname = this.editTextVornameNachname.getText();
		CharSequence toastText = this.getResources().getString(R.string.toast, vornameNachname);
		Toast.makeText(this, toastText, Toast.LENGTH_LONG).show();
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog = null;
				
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		CharSequence vornameNachname = this.editTextVornameNachname.getText();
		builder.setMessage(this.getResources().getString(R.string.saghallotext, vornameNachname))
				.setTitle(R.string.halloandroid)
				.setIcon(R.drawable.androidrobot3)
				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						okGedrueckt(dialog);
						dialog.dismiss();
					}
				})
				.setNegativeButton(R.string.abbruch, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						abbruchGedrueckt(dialog);
						dialog.dismiss();
					}
				});
		dialog = builder.create();
		
		return dialog;
	}
	
	private void okGedrueckt(DialogInterface dialog) {
		Toast.makeText(this, "OK gedrückt", Toast.LENGTH_LONG).show();
	}
	
	private void abbruchGedrueckt(DialogInterface dialog) {
		Toast.makeText(this, "Abbruch gedrückt", Toast.LENGTH_LONG).show();
	}
	
}