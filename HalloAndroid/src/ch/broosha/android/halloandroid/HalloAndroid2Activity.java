package ch.broosha.android.halloandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HalloAndroid2Activity extends Activity {
	
	private static final int BILD_WAEHLEN = 1;
	private Button buttonChooseImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.halloandroid2layout);
		
		buttonChooseImage = (Button)findViewById(R.id.buttonChooseImage);
		buttonChooseImage.setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						waehleEinBild();
					}
				}
	    	);
	}

	protected void waehleEinBild() {
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
		intent.setType("image/*");
		startActivityForResult(intent, BILD_WAEHLEN);
		
		
	}

}
