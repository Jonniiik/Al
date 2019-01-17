package eugene.com.alias;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityTeam extends AppCompatActivity implements View.OnClickListener {
    private Button buttonAddTeam, buttonPlay, buttonEditTeamName;
    private ImageButton buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6;
    private ImageButton buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6;
    private EditText editTextTeamName, editTextPlayer1Name, editTextPlayer2Name;
    private TextView textViewTeam1, textViewTeam2, textViewTeam3, textViewTeam4, textViewTeam5, textViewTeam6;
    String teamName, playerOneName, playerTwoName = null;
    int wordsResult, timeResult;
    int attempt = 0;//index of the TeamsArray
    int currentSize = 0;
    ArrayList<Team> myTeams = new ArrayList<>();
    ArrayList<String> myCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        wordsResult = getIntent().getExtras().getInt("wordsResult");
        timeResult = getIntent().getExtras().getInt("timeResult");
        myCategories = getIntent().getExtras().getStringArrayList("Categories");

        buttonPlay = (Button) findViewById(R.id.buttonPlay);
        buttonAddTeam = (Button) findViewById(R.id.buttonAddTeam);
        buttonEditTeamName = (Button) findViewById(R.id.buttonEditTeamName);

        buttonEditTeam1 = (ImageButton) findViewById(R.id.buttonEditTeam1);
        buttonEditTeam2 = (ImageButton) findViewById(R.id.buttonEditTeam2);
        buttonEditTeam3 = (ImageButton) findViewById(R.id.buttonEditTeam3);
        buttonEditTeam4 = (ImageButton) findViewById(R.id.buttonEditTeam4);
        buttonEditTeam5 = (ImageButton) findViewById(R.id.buttonEditTeam5);
        buttonEditTeam6 = (ImageButton) findViewById(R.id.buttonEditTeam6);

        buttonDelTeam1 = (ImageButton) findViewById(R.id.buttonDelTeam1);
        buttonDelTeam2 = (ImageButton) findViewById(R.id.buttonDelTeam2);
        buttonDelTeam3 = (ImageButton) findViewById(R.id.buttonDelTeam3);
        buttonDelTeam4 = (ImageButton) findViewById(R.id.buttonDelTeam4);
        buttonDelTeam5 = (ImageButton) findViewById(R.id.buttonDelTeam5);
        buttonDelTeam6 = (ImageButton) findViewById(R.id.buttonDelTeam6);

        editTextTeamName = (EditText) findViewById(R.id.editTextTeamName);
        editTextPlayer1Name = (EditText) findViewById(R.id.editTextPlayer1Name);
        editTextPlayer2Name = (EditText) findViewById(R.id.editTextPlayer2Name);

        textViewTeam1 = (TextView) findViewById(R.id.textViewTeam1);
        textViewTeam2 = (TextView) findViewById(R.id.textViewTeam2);
        textViewTeam3 = (TextView) findViewById(R.id.textViewTeam3);
        textViewTeam4 = (TextView) findViewById(R.id.textViewTeam4);
        textViewTeam5 = (TextView) findViewById(R.id.textViewTeam5);
        textViewTeam6 = (TextView) findViewById(R.id.textViewTeam6);

        buttonPlay.setVisibility(View.GONE);
        buttonEditTeamName.setVisibility(View.GONE);
        buttonEditTeam1.setVisibility(View.GONE);
        buttonEditTeam2.setVisibility(View.GONE);
        buttonEditTeam3.setVisibility(View.GONE);
        buttonEditTeam4.setVisibility(View.GONE);
        buttonEditTeam5.setVisibility(View.GONE);
        buttonEditTeam6.setVisibility(View.GONE);
        buttonDelTeam1.setVisibility(View.GONE);
        buttonDelTeam2.setVisibility(View.GONE);
        buttonDelTeam3.setVisibility(View.GONE);
        buttonDelTeam4.setVisibility(View.GONE);
        buttonDelTeam5.setVisibility(View.GONE);
        buttonDelTeam6.setVisibility(View.GONE);
        buttonPlay.setOnClickListener(this);

        addListenerOnButtonSign();
        //addListenerOnButtonPlay();
        addListenerOnButtonEdit1();
        addListenerOnButtonEdit2();
        addListenerOnButtonEdit3();
        addListenerOnButtonEdit4();
        addListenerOnButtonEdit5();
        addListenerOnButtonEdit6();
        addListenerOnButtonDel1();
        addListenerOnButtonDel2();
        addListenerOnButtonDel3();
        addListenerOnButtonDel4();
        addListenerOnButtonDel5();
        addListenerOnButtonDel6();
    }

    public void addListenerOnButtonSign() {
        final TextView[] textViews = {textViewTeam1, textViewTeam2, textViewTeam3, textViewTeam4, textViewTeam5, textViewTeam6};
        final ImageButton[] editButtons = {buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6};
        final ImageButton[] delButtons = {buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6};
        buttonAddTeam.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        teamName = editTextTeamName.getText().toString();
                        playerOneName = editTextPlayer1Name.getText().toString();
                        playerTwoName = editTextPlayer2Name.getText().toString();
                        if (teamName.length() < 1) {
                            Toast.makeText(ActivityTeam.this, "Введи название команды", Toast.LENGTH_SHORT).show();

                        } else if (playerOneName.length() < 1 || playerTwoName.length() < 1) {
                            playerOneName = "Игрок 1";
                            playerTwoName = "Игрок 2";
                        } else if (teamName.length() > 15 || playerOneName.length() > 15 || playerTwoName.length() > 15) {
                            Toast.makeText(ActivityTeam.this, "Sorry, максимум 15 символов", Toast.LENGTH_SHORT).show();
                        } else {

                            textViews[attempt].setText(teamName);
                            editButtons[attempt].setVisibility(View.VISIBLE);
                            delButtons[attempt].setVisibility(View.VISIBLE);
                            attempt++;
                            myTeams.add(new Team(teamName, playerOneName, playerTwoName));
                            editTextTeamName.setText("");
                            editTextPlayer1Name.setText("");
                            editTextPlayer2Name.setText("");
                            if (attempt == 6) {
                                buttonAddTeam.setVisibility(View.GONE);
                            }
                            if (attempt == 2) {
                                buttonPlay.setVisibility(View.VISIBLE);
                            }
                            hideKeyboard();
                        }
                    }
                }
        );
    }

    public void edit(final ImageButton button, final TextView textView, final int index) {
        final ImageButton[] editButtons = {buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6};
        final ImageButton[] delButtons = {buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6};
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editTextTeamName.setText(myTeams.get(index).getName());
                        editTextPlayer1Name.setText(myTeams.get(index).getPlayer1());
                        editTextPlayer2Name.setText(myTeams.get(index).getPlayer2());
                        buttonAddTeam.setVisibility(View.GONE);
                        buttonPlay.setVisibility(View.GONE);
                        for (int i = 0; i < myTeams.size(); i++) {
                            delButtons[i].setVisibility(View.GONE);
                            editButtons[i].setVisibility(View.GONE);
                        }
                        buttonEditTeamName.setVisibility(View.VISIBLE);
                        buttonEditTeamName.setOnClickListener(
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        teamName = editTextTeamName.getText().toString();
                                        playerOneName = editTextPlayer1Name.getText().toString();
                                        playerTwoName = editTextPlayer2Name.getText().toString();
                                        myTeams.get(index).setName(editTextTeamName.getText().toString());
                                        myTeams.get(index).setPlayer1(editTextPlayer1Name.getText().toString());
                                        myTeams.get(index).setPlayer2(editTextPlayer2Name.getText().toString());
                                        textView.setText("");
                                        textView.setText(teamName);
                                        editTextTeamName.setText("");
                                        editTextPlayer1Name.setText("");
                                        editTextPlayer2Name.setText("");
                                        buttonEditTeamName.setVisibility(View.GONE);
                                        if (myTeams.size() >= 2) {
                                            buttonPlay.setVisibility(View.VISIBLE);
                                        }
                                        buttonAddTeam.setVisibility(View.VISIBLE);
                                        for (int i = 0; i < myTeams.size(); i++) {
                                            delButtons[i].setVisibility(View.VISIBLE);
                                            editButtons[i].setVisibility(View.VISIBLE);
                                        }
                                        hideKeyboard();
                                    }
                                }
                        );
                    }
                }
        );
    }

    public void del(final ImageButton button, final TextView textView, final int index) {
        final TextView[] textViews = {textViewTeam1, textViewTeam2, textViewTeam3, textViewTeam4, textViewTeam5, textViewTeam6};
        final ImageButton[] editButtons = {buttonEditTeam1, buttonEditTeam2, buttonEditTeam3, buttonEditTeam4, buttonEditTeam5, buttonEditTeam6};
        final ImageButton[] delButtons = {buttonDelTeam1, buttonDelTeam2, buttonDelTeam3, buttonDelTeam4, buttonDelTeam5, buttonDelTeam6};
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        myTeams.remove(index);
                        textViews[index].setText("");
                        editButtons[index].setVisibility(View.GONE);
                        delButtons[index].setVisibility(View.GONE);
                        attempt--;
                        buttonAddTeam.setVisibility(View.VISIBLE);
                        if (attempt == 1) {
                            buttonPlay.setVisibility(View.GONE);
                        }
                        currentSize = myTeams.size();
                        for (int i = 0; i < attempt + 1; i++) {
                            textViews[i].setText("");
                            editButtons[i].setVisibility(View.GONE);
                            delButtons[i].setVisibility(View.GONE);
                        }
                        for (int i = 0; i < currentSize; i++) {
                            textViews[i].setText(myTeams.get(i).getName());
                            editButtons[i].setVisibility(View.VISIBLE);
                            delButtons[i].setVisibility(View.VISIBLE);
                        }
                    }
                }
        );
    }

    public void addListenerOnButtonEdit1() {
        edit(buttonEditTeam1, textViewTeam1, 0);
    }

    public void addListenerOnButtonEdit2() {
        edit(buttonEditTeam2, textViewTeam2, 1);
    }

    public void addListenerOnButtonEdit3() {
        edit(buttonEditTeam3, textViewTeam3, 2);
    }

    public void addListenerOnButtonEdit4() {
        edit(buttonEditTeam4, textViewTeam4, 3);
    }

    public void addListenerOnButtonEdit5() {
        edit(buttonEditTeam5, textViewTeam5, 4);
    }

    public void addListenerOnButtonEdit6() {
        edit(buttonEditTeam6, textViewTeam6, 5);
    }

    public void addListenerOnButtonDel1() {
        del(buttonDelTeam1, textViewTeam1, 0);
    }

    public void addListenerOnButtonDel2() {
        del(buttonDelTeam2, textViewTeam2, 1);
    }

    public void addListenerOnButtonDel3() {
        del(buttonDelTeam3, textViewTeam3, 2);
    }

    public void addListenerOnButtonDel4() {
        del(buttonDelTeam4, textViewTeam4, 3);
    }

    public void addListenerOnButtonDel5() {
        del(buttonDelTeam5, textViewTeam5, 4);
    }

    public void addListenerOnButtonDel6() {
        del(buttonDelTeam6, textViewTeam6, 5);
    }

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ActivityTeam.this, ActivityCategories.class);
        intent.putExtra("wordsResult", wordsResult);
        intent.putExtra("timeResult", timeResult);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonPlay:
                Intent intent = new Intent(ActivityTeam.this, ActivityGames.class);
                intent.putExtra("wordsResult", wordsResult);
                intent.putExtra("timeResult", timeResult);
                intent.putStringArrayListExtra("Categories", myCategories);
                intent.putParcelableArrayListExtra("myTeams", myTeams);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
