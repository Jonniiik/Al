package eugene.com.alias;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class ActivityTeam2 extends AppCompatActivity implements View.OnClickListener {

    ImageButton dogTeam;
    ImageButton catTeam;
    ImageButton birdsTeam;
    ImageButton squirrelTeam;
    ImageButton haresTeam;
    Button buttonInformation;

    boolean newTeamDog = false;
    boolean newTeamCat = false;
    boolean newTeamBirds = false;
    boolean newTeamSquirrel = false;
    boolean newTeamHares = false;
    ArrayList<String> myTeams = new ArrayList<>();
    ArrayList<String> myCategories = new ArrayList<>();
    int wordsResult, timeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team2);
        wordsResult = getIntent().getExtras().getInt("wordsResult");
        timeResult = getIntent().getExtras().getInt("timeResult");
        myCategories = getIntent().getExtras().getStringArrayList("myCategories");


        dogTeam = (ImageButton) findViewById(R.id.dogTeam);
        dogTeam.setOnClickListener(this);

        catTeam = (ImageButton) findViewById(R.id.catTeam);
        catTeam.setOnClickListener(this);


        birdsTeam = (ImageButton) findViewById(R.id.birdsTeam);
        birdsTeam.setOnClickListener(this);

        squirrelTeam = (ImageButton) findViewById(R.id.squirrelTeam);
        squirrelTeam.setOnClickListener(this);

        haresTeam = (ImageButton) findViewById(R.id.haresTeam);
        haresTeam.setOnClickListener(this);

        buttonInformation = (Button) findViewById(R.id.buttonInformation);
        buttonInformation.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dogTeam:
                if (newTeamDog == false) {
                    dogTeam.setImageResource(R.drawable.team_dog_a);
                    newTeamDog = true;
                    myTeams.add("Песики");
                } else {
                    dogTeam.setImageResource(R.drawable.team_dog);
                    newTeamDog = false;
                    myTeams.remove("Песики");
                }
                break;
            case R.id.catTeam:
                if (newTeamCat == false) {
                    catTeam.setImageResource(R.drawable.team_cat_a);
                    newTeamCat = true;
                    myTeams.add("Котики");
                } else {
                    catTeam.setImageResource(R.drawable.team_cat);
                    newTeamCat = false;
                    myTeams.remove("Котики");

                }
                break;
            case R.id.birdsTeam:
                if (newTeamBirds == false) {
                    birdsTeam.setImageResource(R.drawable.team_birds_a);
                    newTeamBirds = true;
                    myTeams.add("Птички");

                } else if (newTeamBirds == true){
                    birdsTeam.setImageResource(R.drawable.team_birds);
                    newTeamBirds = false;
                    myTeams.remove("Птички");

                }
                break;
            case R.id.squirrelTeam:
                if (newTeamSquirrel==false) {
                    squirrelTeam.setImageResource(R.drawable.team_squirrel_a);
                    newTeamSquirrel = true;
                    myTeams.add("Белочки");
                } else {
                    squirrelTeam.setImageResource(R.drawable.team_squirrel);
                    newTeamSquirrel = false;
                    myTeams.remove("Белочки");
                }
                break;
            case R.id.haresTeam:
                if (newTeamHares==false) {
                    haresTeam.setImageResource(R.drawable.team_hares_a);
                    newTeamHares = true;
                    myTeams.add("Зайчики");
                } else {
                    haresTeam.setImageResource(R.drawable.team_hares);
                    newTeamHares = false;
                    myTeams.remove("Зайчики");
                }
                break;
            case R.id.buttonInformation:
                Intent intent = new Intent(ActivityTeam2.this, ActivityPlay.class);
                intent.putStringArrayListExtra("myCategories",myCategories);
                intent.putStringArrayListExtra("myTeams",myTeams);
                intent.putExtra("wordsResult", wordsResult);
                intent.putExtra("timeResult", timeResult);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void onBackPressed(){
        Intent intent = new Intent(ActivityTeam2.this, ActivityCategories.class);
        intent.putExtra("wordsResult", wordsResult);
        intent.putExtra("timeResult", timeResult);
        startActivity(intent);
    }
}
