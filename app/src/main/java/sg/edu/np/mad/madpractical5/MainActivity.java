package sg.edu.np.mad.madpractical5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent receiver1 = getIntent();
        String valuu = receiver1.getStringExtra("key");
        Intent receiver2 = getIntent();
        String valu2 = receiver2.getStringExtra("key1");
        Intent receiver3 = getIntent();
        String valu3 = receiver3.getStringExtra("key2");
        Intent receiver4 = getIntent();
        boolean valu4 = receiver4.getBooleanExtra("key3",true);
        int valuu3 = Integer.parseInt(valu3);
        sg.edu.np.mad.madpractical5.User user = new sg.edu.np.mad.madpractical5.User("" + valuu,""+valu2, valuu3, valu4);

        TextView tvName = findViewById(R.id.textView2);
        TextView tvDescription = findViewById(R.id.textView5);
        Button btnFollow = findViewById(R.id.button3);

        tvName.setText(user.name);
        tvDescription.setText(user.description);
        if(!user.getFollowed()){
            btnFollow.setText("Unfollow");
        }
        else{
            btnFollow.setText("Follow");
        }

        DatabaseHandler db = new DatabaseHandler(MainActivity.this);
        btnFollow.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(btnFollow.getText().toString().equals("Follow"))
                {
                    btnFollow.setText("Unfollow");
                    Toast.makeText(getApplicationContext(),"Followed",Toast.LENGTH_SHORT).show();
                    user.setFollowed(true);
                    db.updateUser(user);

                }
                else{
                    btnFollow.setText("Follow");
                    Toast.makeText(getApplicationContext(),"Unfollowed",Toast.LENGTH_SHORT).show();
                    user.setFollowed(false);
                    db.updateUser(user);
                }
            }
        });

    }


}