package eugene.com.alias;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class ActivityCategories extends AppCompatActivity {

    private ListView listViewCategories;
    private Button buttonTeam;
    private TextView textView_length;
    ArrayList<String> myAssets = new ArrayList<>();
    ArrayList<String> copeMyAssets = new ArrayList<>();
    ArrayList<String> myCategories = new ArrayList<>();
    String term = null;
    int wordsResult, timeResult;
    int length = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        wordsResult = getIntent().getExtras().getInt("wResult");
        timeResult = getIntent().getExtras().getInt("tResult");
        getOpenAssets();
        addNewArray();
        addListenerButtonSelectCategories();
    }

    private void getOpenAssets() {
        AssetManager assetManager = getApplicationContext().getAssets();
        try {
            for (String file : assetManager.list("")) {
                if (file.endsWith(".txt"))
                    myAssets.add(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String clone : myAssets) {
            copeMyAssets.add(clone.replace(".txt", ""));
        }
        listViewCategories = (ListView) findViewById(R.id.listViewCategories);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.list_view, copeMyAssets);
        listViewCategories.setAdapter(arrayAdapter);
        listViewCategories.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView_length = (TextView) findViewById(R.id.textView_length);
                CheckedTextView checkedTextView = (CheckedTextView) view;
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(myAssets.get(position))));
                    while ((term = reader.readLine()) != null) {
                        if (checkedTextView.isChecked()) {
                            length++;
                        } else {
                            length--;
                        }
                    }
                    reader.close();
                    textView_length.setText(String.valueOf(length));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void addNewArray() {
        SparseBooleanArray sparseBooleanArray = listViewCategories.getCheckedItemPositions();
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < sparseBooleanArray.size(); i++) {
            if (sparseBooleanArray.valueAt(i) == true) {
                indexes.add(sparseBooleanArray.keyAt(i));
            }
        }
        for (int i = 0; i < indexes.size(); i++) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(myAssets.get(indexes.get(i)))));
                while ((term = reader.readLine()) != null) {
                    myCategories.add(term);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addListenerButtonSelectCategories() {
        buttonTeam = (Button) findViewById(R.id.buttonTeam);
        buttonTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewArray();
                if (myCategories.isEmpty()) {
                    Toast.makeText(getApplicationContext().getApplicationContext(), "Выбери Категорию", Toast.LENGTH_SHORT).show();
                } else {
                    Collections.shuffle(myCategories);
                    Intent intent = new Intent(ActivityCategories.this, ActivityTeam.class);
                    intent.putStringArrayListExtra("Categories", myCategories);
                    intent.putExtra("wResult", wordsResult);
                    intent.putExtra("tResult", timeResult);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (resultCode == Activity.RESULT_OK){
            Uri uri = null;
            if (resultData != null){uri = resultData.getData();}
            readTextFile(uri);
        }
    }

    private void readTextFile(Uri uri) {
        InputStream inputStream = null;
        try{
            inputStream = getContentResolver().openInputStream(uri);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((term = reader.readLine())!=null){
                myCategories.add(term);
            }
            Collections.shuffle(myCategories);
            Intent intent = new Intent(ActivityCategories.this, ActivityTeam.class);
            intent.putStringArrayListExtra("Categories", myCategories);
            intent.putExtra("wResult", wordsResult);
            intent.putExtra("tResult",timeResult);
            reader.close();
            inputStream.close();
            startActivity(intent);
//            Toast.makeText(getApplicationContext(),""+ textView_length, Toast.LENGTH_SHORT).show();
        } catch (Exception e){e.printStackTrace();}
    }
    public void onBackPressed(){
        Intent intent = new Intent(ActivityCategories.this, ActivityOptions.class);
        startActivity(intent);
    }
}
