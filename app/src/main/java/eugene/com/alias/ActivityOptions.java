package eugene.com.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOptions extends AppCompatActivity {
    private SeekBar seekBarWord;
    private SeekBar seekBarTime;
    private TextView wordsView;
    private TextView timeView;
    private Switch switchNotWords;
    private Switch switchFine;
    private Boolean Fine = false;
    private Boolean notWords = false;
    Button buttonCategories;
    int wordsResult, timeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        addSeekBarWordsResult();
        addSeekBarTimeResult();
        addSwitch();
        addButtonCategories();

    }

    public void addSeekBarWordsResult() {
        seekBarWord = (SeekBar) findViewById(R.id.seekBarWord);
        wordsView = (TextView) findViewById(R.id.wordsView);

        String wordText = String.valueOf(seekBarWord.getProgress());
        wordsView.setText(wordText);

        seekBarWord.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressWords = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressV, boolean fromUser) {
                progressWords = progressV;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String resultTextWords = String.valueOf(progressWords);
                wordsView.setText(resultTextWords);
            }
        });
    }

    public void addSeekBarTimeResult() {
        seekBarTime = (SeekBar) findViewById(R.id.seekBarTime);
        timeView = (TextView) findViewById(R.id.timeView);

        String timeText = String.valueOf(seekBarTime.getProgress());
        timeView.setText(timeText);

        seekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressTime = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progressT, boolean fromUser) {
                progressTime = progressT;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String resultTextTime = String.valueOf(progressTime);
                timeView.setText(resultTextTime);
            }
        });

    }

    public void addSwitch() {
        switchFine = (Switch) findViewById(R.id.switchFine);
        switchFine.setChecked(false);
        switchFine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Fine = true;
                    // Toast.makeText(getApplicationContext().getApplicationContext(), "Fine  " + Fine, Toast.LENGTH_SHORT).show();
                } else {
                    Fine = false;
                    // Toast.makeText(getApplicationContext().getApplicationContext(), "Fine  " + Fine, Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchNotWords = (Switch) findViewById(R.id.switchNotWords);
        switchNotWords.setChecked(false);
        switchNotWords.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    notWords = true;
                    //Toast.makeText(getApplicationContext().getApplicationContext(), "notWords  " + notWords, Toast.LENGTH_SHORT).show();
                } else {
                    notWords = false;
                    //Toast.makeText(getApplicationContext().getApplicationContext(), "notWords  " + notWords, Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void addButtonCategories() {
        buttonCategories = (Button) findViewById(R.id.buttonCategories);
        buttonCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordsResult = Integer.parseInt(wordsView.getText().toString());
                timeResult = Integer.parseInt(timeView.getText().toString());
                Intent intent = new Intent(ActivityOptions.this, ActivityCategories.class);
                intent.putExtra("wResult", wordsResult);
                intent.putExtra("tResult", timeResult);
                startActivity(intent);


//                Toast.makeText(getApplicationContext().getApplicationContext(), "timeResult  " + timeResult + "   wordsResult  " + wordsResult + "  notWords " +
//                        " " + notWords + "      Fine " + Fine, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(ActivityOptions.this, ActivityTeam.class);
    }

}
