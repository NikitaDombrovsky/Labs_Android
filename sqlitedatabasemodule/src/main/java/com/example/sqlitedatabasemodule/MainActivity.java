package com.example.sqlitedatabasemodule;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/*public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}*/
public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText postTitleEdt, postTextEdt;
    private Button addPostBtn;
    private DatabaseHelper_ postsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        postTitleEdt = findViewById(R.id.idEdtPostTitle);
        postTextEdt = findViewById(R.id.idEdtPostText);
        addPostBtn = findViewById(R.id.idBtnAddPost);

        // creating a new dbhandler class
        // and passing our context to it.
        //postsDatabaseHelper = new PostsDatabaseHelper(MainActivity.this);
        // В любой Activity просто передайте контекст и используйте метод Singleton
        postsDatabaseHelper = DatabaseHelper_.getInstance(this);

        // below line is to add on click listener for our add course button.
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String postTitle = postTitleEdt.getText().toString();
                String postText = postTextEdt.getText().toString();


                if (postTitle.isEmpty() && postText.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // TODO по другому проверку
                Post post = new Post(
                        postTitle, postText
                );

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
               // postsDatabaseHelper.addPost_NotTransaction(post);
               // postsDatabaseHelper.addPost(post);

                PostRepository postRepository = new PostRepository(postsDatabaseHelper);
                postRepository.addPost(post);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Post has been added.", Toast.LENGTH_SHORT).show();
                postTitleEdt.setText("");
                postTextEdt.setText("");


            }
        });
    }
}