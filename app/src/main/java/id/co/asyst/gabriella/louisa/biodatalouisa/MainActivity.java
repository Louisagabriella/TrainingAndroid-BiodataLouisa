package id.co.asyst.gabriella.louisa.biodatalouisa;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

import id.co.asyst.gabriella.louisa.biodatalouisa.utility.Constant;
import id.co.asyst.gabriella.louisa.biodatalouisa.utility.DateUtils;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener,
        AdapterView.OnItemSelectedListener,
        RadioGroup.OnCheckedChangeListener,
        CompoundButton.OnCheckedChangeListener,
        DatePickerDialog.OnDateSetListener {
    Spinner sRiwayatPendidikan;
    TextView tvDate;
    EditText etName, etplace, etLainnya, etAlamat, etEmail;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;
    CheckBox cbListeningMusic, cbPlayingGame, cbTravelling, cbLainnya;
    Button bSubmit;
    ImageView ivDate;
    ArrayList<String> listPendidikan = new ArrayList<>();
    ArrayList<String> listHobby = new ArrayList<>();
    String selectedPendidikan, selectedGender, name, place, alamat, lainnya = "", email, pendidikan, hobby = "", result, date;
    DatePickerDialog dpd;
    Calendar now;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sRiwayatPendidikan = findViewById(R.id.spinnerRiwayatPendidikan);
        etName = findViewById(R.id.editTextName);
        etplace = findViewById(R.id.editTextPalce);
        etLainnya = findViewById(R.id.editTextHobiLainnya);
        etAlamat = findViewById(R.id.editTextAlamat);
        etEmail = findViewById(R.id.editTextEmail);
        cbListeningMusic = findViewById(R.id.checkboxListeningMusic);
        cbPlayingGame = findViewById(R.id.checkboxPlayingGame);
        cbTravelling = findViewById(R.id.checkboxTravelling);
        cbLainnya = findViewById(R.id.checkboxHobiLainnya);
        rgGender = findViewById(R.id.radioGroupGender);
        rbMale = findViewById(R.id.radioButtonMale);
        rbFemale = findViewById(R.id.radioButtonFemale);
        bSubmit = findViewById(R.id.buttonSubmit);
        tvDate = findViewById(R.id.textViewDate);
        ivDate = findViewById(R.id.imageViewDate);
        bSubmit.setOnClickListener(this);
        sRiwayatPendidikan.setOnItemSelectedListener(this);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listPendidikan);
        listPendidikan.add("SD");
        listPendidikan.add("SMP");
        listPendidikan.add("SMA/SMK/Sederajat");
        listPendidikan.add("D-I");
        listPendidikan.add("D-III");
        listPendidikan.add("D-IV");
        listPendidikan.add("S-1");
        listPendidikan.add("S-2");
        listPendidikan.add("S-3");
        listPendidikan.add("Tidak Memiliki Riwayat Pendidikan");
        sRiwayatPendidikan.setAdapter(arrayAdapter);
        rgGender.setOnCheckedChangeListener(this);
        cbListeningMusic.setOnCheckedChangeListener(this);
        cbTravelling.setOnCheckedChangeListener(this);
        cbPlayingGame.setOnCheckedChangeListener(this);
        cbLainnya.setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R.id.radioButtonMale)).setChecked(true);
        ((CheckBox) findViewById(R.id.checkboxListeningMusic)).setChecked(true);
        now = Calendar.getInstance();
        dpd = DatePickerDialog.newInstance(
                MainActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
        ivDate.setOnClickListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedPendidikan = sRiwayatPendidikan.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSubmit:
                if (TextUtils.isEmpty(etName.getText().toString()) || TextUtils.isEmpty(etplace.getText().toString()) || TextUtils.isEmpty(etAlamat.getText().toString()) ||
                        TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(tvDate.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Tidak Boleh Ada Yang Kosong", Toast.LENGTH_LONG).show();
                } else {
                    String emailAddress = etEmail.getText().toString();
                    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                    if (emailAddress.matches(emailPattern)) {
                        pendidikan = selectedPendidikan;
                        name = etName.getText().toString();
                        place = etAlamat.getText().toString();
                        alamat = etAlamat.getText().toString();
                        email = etEmail.getText().toString();
                        date = tvDate.getText().toString();
                        lainnya = " - " + etLainnya.getText().toString() + "\n";
                        hobby = "";
                        if (lainnya != "") {
                            for (int i = 0; i < listHobby.size(); i++) {
                                hobby = hobby + " " + listHobby.get(i);
                            }
                            result = hobby + lainnya;
                        } else {
                            for (int i = 0; i < listHobby.size(); i++) {
                                hobby = hobby + " " + listHobby.get(i);
                            }
                            result = hobby;
                        }
                        AlertDialog.Builder alert = new AlertDialog.Builder(this);
                        alert.setTitle("Confirmation")
                                .setCancelable(false)
                                .setMessage("Are You Sure?")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                        intent.putExtra(Constant.KEY_PENDIDIKAN, pendidikan);
                                        intent.putExtra(Constant.KEY_NAME, name);
                                        intent.putExtra(Constant.KEY_PLACE, place);
                                        intent.putExtra(Constant.KEY_HOBBY, result);
                                        intent.putExtra(Constant.KEY_ADDRESS, alamat);
                                        intent.putExtra(Constant.KEY_EMAIL, email);
                                        intent.putExtra(Constant.KEY_GENDER, selectedGender);
                                        intent.putExtra(Constant.KEY_DATE, date);
                                        startActivity(intent);
                                    }
                                }).setNegativeButton("No", null).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.imageViewDate:

                dpd.show(getFragmentManager(), "Datepickerdialog");
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.radioButtonMale:
                selectedGender = "Male";
                break;
            case R.id.radioButtonFemale:
                selectedGender = "Female";
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.checkboxListeningMusic:
                if (isChecked) {
                    listHobby.add("- Listening Music\n");
                } else {
                    listHobby.remove("- Listening Music\n");
                }
                break;
            case R.id.checkboxTravelling:
                if (isChecked) {
                    listHobby.add("- Travelling\n");
                } else {
                    listHobby.remove("- Travelling\n");
                }
                break;
            case R.id.checkboxPlayingGame:
                if (isChecked) {
                    listHobby.add("- Playing Game\n");
                } else {
                    listHobby.remove("- Playing Game\n");
                }
                break;
            case R.id.checkboxHobiLainnya:
                if (isChecked) {
                    etLainnya.setEnabled(true);
                } else {
                    etLainnya.setEnabled(false);
                }
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        String date = dayOfMonth + " " + (monthOfYear + 1) + " " + year;
        String birthdate = DateUtils.formatDate("dd MM yyyy", "dd MMMM yyyy", date);
        tvDate.setText(birthdate);
    }
}
