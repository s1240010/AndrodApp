package com.mamezou.android.bmicalc3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMICalculator3Activity extends Activity {
	private EditText textHeight;
	private EditText textGender;
	private TextView labelPreviousHeight;
	private TextView labelPreviousWeight;
	private double sw;
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		Log.v("EXAMPLE", "onCreate was called.");
		setTitle("Standard Weight(S/W)Calculator");
		setContentView(R.layout.main);
		textHeight = (EditText) findViewById(R.id.text_height);
		textGender = (EditText) findViewById(R.id.text_gender);
		Button button = (Button) findViewById(R.id.button_calculate);
		Button buttonShowNextActivity = (Button) findViewById(R.id.button_show_next_activity);

		labelPreviousHeight = (TextView) findViewById(R.id.label_previous_height);
		labelPreviousWeight = (TextView) findViewById(R.id.label_previous_gender);

		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.label_sw_description);
		builder.setPositiveButton(R.string.button_close_dialog, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				setResult(RESULT_OK);
			}
		});
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				int height = Integer.parseInt(textHeight.getText().toString());
				String gender = textGender.getText().toString();
				double height2 = (double)height/100;
				if(gender.equals("M"))
					sw = height2 * height2 * 22;
				else
					sw = height2 * height2 * 21;
				builder.setMessage(String.valueOf(sw));
				builder.create();
				builder.show();
			}
		});
		buttonShowNextActivity.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(BMICalculator3Activity.this, ResultActivity.class);
				intent.putExtra("HEIGHT", Integer.parseInt(textHeight.getText().toString()));
				intent.putExtra("GENDER",textGender.getText().toString());
				startActivityForResult(intent, 0);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			SharedPreferences preferences = getSharedPreferences("PREVIOUS_RESULT", MODE_PRIVATE);
			labelPreviousHeight.setText(String.valueOf(preferences.getInt("PREVIOUS_HEIGHT", 0)));
			labelPreviousWeight.setText(String.valueOf(preferences.getString("PREVIOUS_GENDER"," ")));
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.v("EXAMPLE", "onStart was called.");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.v("EXAMPLE", "onRestart was called.");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.v("EXAMPLE", "onResume was called.");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.v("EXAMPLE", "onPause was called.");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.v("EXAMPLE", "onStop was called.");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("EXAMPLE", "onDestroy was called.");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.v("EXAMPLE", "onConfigurationChanged was called.");
	}
}