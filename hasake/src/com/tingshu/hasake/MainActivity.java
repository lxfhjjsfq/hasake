package com.tingshu.hasake;

import com.tingshu.hasake.test.PlayActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button navigate_play;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigate_play = (Button) findViewById(R.id.navigate_play);
        navigate_play.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent it = new Intent(MainActivity.this, PlayActivity.class);
				startActivity(it);
			}
		});
    }

}
