package eugene.com.alias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityInformation extends AppCompatActivity {
    ListView listTeam;
    Button buttonPlay;
    ArrayList<String> myTeams = new ArrayList<>();
    ArrayList<String> myCategories = new ArrayList<>();
    int wordsResult, timeResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        wordsResult = getIntent().getExtras().getInt("wordsResult");
        timeResult = getIntent().getExtras().getInt("timeResult");
        myCategories = getIntent().getExtras().getStringArrayList("myCategories");
        myTeams = getIntent().getExtras().getStringArrayList("myTeams");
        ListMyTeam();
    }

    public void ListMyTeam(){
        listTeam = (ListView) findViewById(R.id.listTeam);
        ArrayAdapter arrayAdapterTeam = new ArrayAdapter(this, R.layout.list_view_team,myTeams);
        listTeam.setAdapter(arrayAdapterTeam);
        listTeam.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
    }
}
