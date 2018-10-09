package id.co.asyst.gabriella.louisa.biodatalouisa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.co.asyst.gabriella.louisa.biodatalouisa.utility.Constant;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvname, tvplace, tvdate, tvgender, tvhobby, tvaddress, tvemail, tvpendidikan;
    Button bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvname = findViewById(R.id.textViewName);
        tvplace = findViewById(R.id.textViewPlace);
        tvdate = findViewById(R.id.textViewBirthday);
        tvgender = findViewById(R.id.textViewGender);
        tvhobby = findViewById(R.id.textViewHobby);
        tvaddress = findViewById(R.id.textViewAddress);
        tvemail = findViewById(R.id.textViewEmail);
        tvpendidikan = findViewById(R.id.textViewRiwayatAkhir);
        bBack = findViewById(R.id.buttonBack);
        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            tvname.setText(bundle.getString(Constant.KEY_NAME));
            tvplace.setText(bundle.getString(Constant.KEY_PLACE));
            tvdate.setText(bundle.getString(Constant.KEY_DATE));
            tvgender.setText(bundle.getString(Constant.KEY_GENDER));
            tvhobby.setText(bundle.getString(Constant.KEY_HOBBY));
            tvaddress.setText(bundle.getString(Constant.KEY_ADDRESS));
            tvemail.setText(bundle.getString(Constant.KEY_EMAIL));
            tvpendidikan.setText(bundle.getString(Constant.KEY_PENDIDIKAN));
        } else {
        }
        bBack.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_menu_input:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Confirmation")
                        .setCancelable(false)
                        .setMessage("are you sure to edit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("No", null).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonBack:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Confirmation")
                        .setCancelable(false)
                        .setMessage("are you sure to exit?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        }).setNegativeButton("No", null).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Confirmation")
                .setCancelable(false)
                .setMessage("are you sure to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", null).show();
    }
}
