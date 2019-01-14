package eugene.com.alias;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ActivityGames extends AppCompatActivity {
    private TextView textViewTxtScore, textViewScore, textViewChronometer, textViewWords;
    Button buttonYes, buttonNo;
    ArrayList<Team> myTeams = new ArrayList<>();
    ArrayList<Team> myTeamsSorted = new ArrayList<>(myTeams.size());
    ArrayList<String> myCategories = new ArrayList<>();
    ArrayList<EndOfTurnTerms> EoTTerms = new ArrayList<>();

    int wordsResult, timeResult, score;
    int attempt = 0;
    CountDownTimer timer;
    boolean emptyCategories, play = false, flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        wordsResult = getIntent().getExtras().getInt("wResult");
        timeResult = getIntent().getExtras().getInt("tResult");
        myCategories = getIntent().getExtras().getStringArrayList("Categories");
        myTeams = (ArrayList<Team>)getIntent().getSerializableExtra("Teams");
        Collections.shuffle(myTeams);
        newAttempt();
        addListenerOnButtonYes();
        addListenerOnButtonNo();
    }
    public int turn(int attempt, ArrayList<Team> myTeams) { return (attempt % myTeams.size()); }

    public String getTeam(int attempt, ArrayList<Team> myTeams) { return (myTeams.get(turn(attempt, myTeams)).getName()); }

    public String getReader(int attempt, ArrayList<Team> myTeams){
        if ((attempt / myTeams.size()) % 2 == 0) { return (myTeams.get(turn(attempt, myTeams)).getPlayer1()); }
        else{ return (myTeams.get(turn(attempt, myTeams)).getPlayer2()); }
    }

    public String getListener(int attempt, ArrayList<Team> myTeams){
        if ((attempt / myTeams.size()) % 2 == 0) { return (myTeams.get(turn(attempt, myTeams)).getPlayer2()); }
        else{ return (myTeams.get(turn(attempt, myTeams)).getPlayer1()); }
    }

    public void onBackPressed(){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityGames.this, android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Alert");
        builder.setMessage("Хотите выйти?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                timer.cancel();
                Intent intent = new Intent(ActivityGames.this, ActivityStart.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        android.app.AlertDialog quit = builder.create();
        quit.show();
    }

    public void addListenerOnButtonYes(){
        textViewTxtScore = (TextView)findViewById(R.id.textViewTxtScore);
        textViewWords = (TextView)findViewById(R.id.textViewWords);
        textViewScore = (TextView)findViewById(R.id.textViewScore);
        buttonYes = (Button)findViewById(R.id.buttonYes);
        buttonYes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score++;
                        EoTTerms.add(new EndOfTurnTerms(myCategories.get(0), "+"));
                        myCategories.remove(0);
                        if (myCategories.isEmpty()){
                            emptyCategories = true;
                            timer.cancel();
                            SeeTerms();
                        }
                        else {
                            textViewWords.setText(myCategories.get(0));
                            textViewScore.setText(String.valueOf(score));
                            if (score == 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.blue));
                                textViewScore.setTextColor(getResources().getColor(R.color.blue));
                            }
                            else if (score > 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.green));
                                textViewScore.setTextColor(getResources().getColor(R.color.green));
                            }
                            else {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.red));
                                textViewScore.setTextColor(getResources().getColor(R.color.red));
                            }
                        }
                    }
                }
        );
    }

    public void addListenerOnButtonNo(){
        textViewTxtScore = (TextView)findViewById(R.id.textViewTxtScore);
        textViewWords = (TextView)findViewById(R.id.textViewWords);
        textViewScore = (TextView)findViewById(R.id.textViewScore);
        buttonNo = (Button)findViewById(R.id.buttonNo);
        buttonNo.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        score--;
                        EoTTerms.add(new EndOfTurnTerms(myCategories.get(0), "-"));
                        myCategories.remove(0);
                        if (myCategories.isEmpty()){
                            emptyCategories = true;
                            timer.cancel();
                            SeeTerms();
                        }
                        else {
                            textViewWords.setText(myCategories.get(0));
                            textViewScore.setText(String.valueOf(score));
                            if (score == 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.blue));
                                textViewScore.setTextColor(getResources().getColor(R.color.blue));
                            } else if (score > 0) {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.green));
                                textViewScore.setTextColor(getResources().getColor(R.color.green));
                            } else {
                                textViewTxtScore.setTextColor(getResources().getColor(R.color.red));
                                textViewScore.setTextColor(getResources().getColor(R.color.red));
                            }
                        }
                    }

                }
        );
    }

    public void newAttempt(){
        EoTTerms.clear();
        GetReady();
        textViewScore = (TextView) findViewById(R.id.textViewScore);
        textViewTxtScore = (TextView)findViewById(R.id.textViewTxtScore);
        textViewWords = (TextView) findViewById(R.id.textViewWords);
        textViewTxtScore.setTextColor(getResources().getColor(R.color.blue));
        textViewScore.setTextColor(getResources().getColor(R.color.blue));
        textViewScore.setText("0");
        myCategories.remove(0);
        if (myCategories.isEmpty()){
            emptyCategories = true;
            timer.cancel();
            SeeTerms();
        }
        else { textViewWords.setText(""); }
    }

    public void GetReady(){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityGames.this, android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Готовы: " + getTeam(attempt, myTeams));
        builder.setMessage("Команда: " + getReader(attempt, myTeams) + "\nListener: " + getListener(attempt, myTeams));
        builder.setNeutralButton("Начинаем", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                timer = new CountDownTimer((timeResult + 1) * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        if (millisUntilFinished < 10000 && flag == false) {
                            flag = true;
                        }
                        textViewChronometer = (TextView)findViewById(R.id.textViewChronometer);
                        textViewChronometer.setText(String.valueOf(millisUntilFinished / 1000));
                    }
                    @Override
                    public void onFinish() {
                        flag = false;
                        SeeTerms();
                    }
                };
                timer.start();
                textViewWords.setText(myCategories.get(0));
                dialogInterface.cancel();
            }
        });
        android.app.AlertDialog getReady = builder.create();
        getReady.setCancelable(false);
        getReady.setCanceledOnTouchOutside(false);
        getReady.show();
    }

    public void Highscore(){
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityGames.this, android.app.AlertDialog.THEME_HOLO_LIGHT);
        myTeamsSorted.clear();
        if (emptyCategories == true){
            builder.setTitle("Sorry, Все закончилось\nHIGHSCORE");
        }
        else {
            builder.setTitle("HIGHSCORE");
        }
        myTeams.get(turn(attempt, myTeams)).addScore(score);
        score = 0;
        for (Team team : myTeams) {
            myTeamsSorted.add(team);
        }
        Collections.sort(myTeamsSorted, new Comparator<Team>(){
            public int compare(Team t1, Team t2){
                return t2.getScore() - t1.getScore();
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myTeamsSorted.size(); i++){
            sb.append(i+1 + ". " + myTeamsSorted.get(i).getName() + " " +  myTeamsSorted.get(i).getScore() + "\n");
        }

        builder.setMessage(sb.toString());
        builder.setNeutralButton("Процесс", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (myTeams.get(turn(attempt, myTeams)).getScore() >= wordsResult || emptyCategories == true){
                    dialogInterface.cancel();
                    announceWinner();
                }
                else {
                    attempt++;
                    dialogInterface.cancel();
                    newAttempt();
                }
            }
        });
        android.app.AlertDialog highscore = builder.create();
        highscore.setCancelable(false);
        highscore.setCanceledOnTouchOutside(false);
        highscore.show();
    }

    public void SeeTerms() {
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityGames.this, android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Счет  " + score);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < EoTTerms.size(); i++) {
            sb.append(EoTTerms.get(i).getTerm() + " " + EoTTerms.get(i).getResult() + "\n");
        }
        builder.setMessage(sb.toString());
        builder.setPositiveButton("Процесс", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                Highscore();
            }
        });
        final android.app.AlertDialog seeTerms = builder.create();
        seeTerms.setCanceledOnTouchOutside(false);
        seeTerms.setCancelable(false);
        textViewChronometer = (TextView)findViewById(R.id.textViewChronometer);
        textViewChronometer.setText("");
        seeTerms.show();
    }

    public void announceWinner(){
        if (play == false){

        }
        play = true;
        final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityGames.this, android.app.AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("Поздравляю");
        builder.setMessage(myTeamsSorted.get(0).getName() + " are the real ....");
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ActivityGames.this, ActivityStart.class);
                startActivity(intent);
            }
        });
        builder.setNeutralButton("BACK TO HIGHSCORES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Highscore();
            }
        });
        android.app.AlertDialog winner = builder.create();
        winner.setCanceledOnTouchOutside(false);
        winner.setCancelable(false);
        winner.show();
    }
}
