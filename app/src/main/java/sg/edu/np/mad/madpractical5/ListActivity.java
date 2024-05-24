package sg.edu.np.mad.madpractical5;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Random stuffz = new Random();
        ArrayList<User> randomuserz = new ArrayList<>();
        DatabaseHandler db = new DatabaseHandler(ListActivity.this);
        if (db.needsInitialization()) {
            while (randomuserz.size() < 20) {
                User user = new User("John Doe", "MAD Developer", false);
                int therandonumbe = Math.abs(stuffz.nextInt(999999));
                int theothernum = Math.abs(stuffz.nextInt(999999));
                user.setName("Name" + String.valueOf(therandonumbe));
                user.setDescription("Description " + String.valueOf(theothernum));
                user.setFollowed(stuffz.nextBoolean());
                randomuserz.add(user);
                db.addUser(user);
            }
        }
        else{
            randomuserz = db.getUsers();
        }

        UserAdapter userAdapter = new UserAdapter(randomuserz, this);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userAdapter);

    }

}
