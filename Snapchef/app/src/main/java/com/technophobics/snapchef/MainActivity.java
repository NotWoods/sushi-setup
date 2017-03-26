package com.technophobics.snapchef;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.microsoft.projectoxford.vision.rest.VisionServiceException;
import com.technophobics.snapchef.data.ComparableTag;
import com.technophobics.snapchef.data.Grocery;
import com.technophobics.snapchef.data.Ingredient;
import com.technophobics.snapchef.data.Recipe;
import com.technophobics.snapchef.data.SushiHelper;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    // The URI of photo taken from gallery
    private Uri mUriPhotoTaken;

    // File of the photo taken with camera
    private File mFilePhotoTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            SushiHelper.loadRecipes(this);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<String> GroceryItems = Grocery.listAllText();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, GroceryItems);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("ImageUri", mUriPhotoTaken);
    }

    // Recover the saved state when the activity is recreated.
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mUriPhotoTaken = savedInstanceState.getParcelable("ImageUri");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void takePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null) {
            // Save the photo taken to a temporary file.
            File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//            try {
//                mFilePhotoTaken = File.createTempFile(
//                        "IMG_",  /* prefix */
//                        ".jpg",         /* suffix */
//                        storageDir      /* directory */
//                );
                mFilePhotoTaken = new File(storageDir.getPath());


                // Create the File where the photo should go
                // Continue only if the File was successfully created
                if (mFilePhotoTaken != null) {
//                    mUriPhotoTaken = FileProvider.getUriForFile(this,
//                            "com.technophobics.snapchef",
//                            mFilePhotoTaken);
                    mUriPhotoTaken = Uri.fromFile(storageDir);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);

                    // Finally start camera activity
                    startActivityForResult(intent, 0);
                }
//            } catch (IOException e) {
//                setInfo(e.getMessage());
//            }
        }
    }

    public void gotoRecipe(View view) {
        // FloatingActionButton btn = (FloatingActionButton)findViewById(R.id.fab2);
        Intent intent = new Intent(this, RecipeDisplay.class);

        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent();
            intent.setData(Uri.fromFile(mFilePhotoTaken));
            setResult(RESULT_OK, intent);
            //finish();
            try {
                InputStream stream = new FileInputStream(mFilePhotoTaken);
                List<ComparableTag> recipes = AnalyzeFood.analyze(stream);
                Grocery.saveFromTags(recipes);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();

            } catch (VisionServiceException e){
                e.printStackTrace();

            }

        }
    }

    // Set the information panel on screen.
    private void setInfo(String info) {
        TextView textView = (TextView) findViewById(R.id.info);
        textView.setText(info);
    }
}
