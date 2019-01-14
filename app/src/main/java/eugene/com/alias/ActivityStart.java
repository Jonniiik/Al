package eugene.com.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityStart extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart, buttonSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        buttonSetting = (Button) findViewById(R.id.buttonSetting);
        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStart:
                Intent intent = new Intent(ActivityStart.this, ActivityOptions.class);
                startActivity(intent);
                break;
            case R.id.buttonSetting:
                Toast.makeText(getApplicationContext(), "Пока не работает", Toast.LENGTH_SHORT).show();
                break;
                default:
                    break;
        }
    }
}