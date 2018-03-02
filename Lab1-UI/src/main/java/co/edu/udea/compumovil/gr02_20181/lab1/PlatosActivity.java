package co.edu.udea.compumovil.gr02_20181.lab1;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.Uri;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
//import android.widget.FrameLayout;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.Manifest;

import android.widget.TextView;
import android.widget.TimePicker;
import android.app.DialogFragment;

import java.util.Calendar;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import gun0912.tedbottompicker.TedBottomPicker;

public class PlatosActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener  {


    //informacion del picker
    private TextView mTimeDisplay;
    private TextView mDateDisplay;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    /*Bitmap image , bitmap;
    String picture_location;
    Uri targetUri;*/



    public RequestManager mGlideRequestManager;
    ImageView imgPlato;
    ArrayList<Uri> selectedUriList;
    Uri selectedUri;
    private ViewGroup mSelectedImagesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platos);

        Glide.with(this);

        mGlideRequestManager = Glide.with(PlatosActivity.this);

        //picker
        // Capture our View elements
        //mDateDisplay = (TextView) findViewById(R.id.dateDisplay);
        mTimeDisplay = (TextView) findViewById(R.id.txtTiempoPlato);
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);



        imgPlato = (ImageView) findViewById(R.id.imgPlato);
        //mSelectedImagesContainer = (ViewGroup) findViewById(R.id.selected_photos_container);

        setSingleShowButton();

        updateDisplay();
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.itemMenuBorrar:
                //borrarInfo();
                return true;

            case R.id.itemMenuSalir:
                Salida();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @SuppressLint("NewApi")
    public void Salida(){
        finishAffinity();
    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnTiempoPlato:
                DialogFragment timePickerFragment = new TimePickerFragment();
                timePickerFragment.show(getFragmentManager(), "timePicker");
                break;

            case R.id.btnAgregarPlato:
                RelativeLayout relativeLayoutInfo = (RelativeLayout)findViewById(R.id.relativeLayoutInfoPlato);
                relativeLayoutInfo.setVisibility(View.VISIBLE);
                mostrarInfoPlato();

                break;

        }

    }


    //agregar imagen

    private void setSingleShowButton() {



        Button btn_single_show = (Button) findViewById(R.id.btnAgregarImagen);
        btn_single_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    PermissionListener permissionlistener = new PermissionListener() {

                    @Override
                    public void onPermissionGranted() {

                        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(PlatosActivity.this)
                                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                                    @Override
                                    public void onImageSelected(final Uri uri) {
                                        Log.d("ted", "uri: " + uri);
                                        Log.d("ted", "uri.getPath(): " + uri.getPath());
                                        selectedUri = uri;

                                        imgPlato.setVisibility(View.VISIBLE);
                                        //mSelectedImagesContainer.setVisibility(View.GONE);
                                        imgPlato.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                mGlideRequestManager
                                                        .load(uri)
                                                        .into(imgPlato);
                                            }
                                        });

                                        /*Glide.with(PlatosActivity.this)
                                                //.load(uri.toString())
                                                .load(uri)
                                                .into(imgPlato);*/

                                    }
                                })
                                //.setPeekHeight(getResources().getDisplayMetrics().heightPixels/2)
                                .setSelectedUri(selectedUri)
                                //.showVideoMedia()
                                .setPeekHeight(1200)
                                .create();

                        bottomSheetDialogFragment.show(getSupportFragmentManager());



                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                        Toast.makeText(PlatosActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };

                TedPermission.with(PlatosActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();


            }
        });

        }

    //
    private void updateDisplay() {
        mTimeDisplay.setText(new StringBuilder()
                .append(String.format("%02d",mHour)).append(":").append(String.format("%02d", mMinute)));


    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        mHour = hourOfDay;
        mMinute = minute;
        updateDisplay();
    }




    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        ImageView imagenPlato = (ImageView)findViewById(R.id.imgPlato);

       /*
        savedInstanceState.putParcelable("BitImage", bitmap);
        savedInstanceState.putString("path_to_picture",picture_location);*/

        //savedInstanceState.putString("PATH_IMAGE",selectedUri.toString());

        EditText nombrePlato = (EditText)findViewById(R.id.txtNombrePlato);
        CheckBox horarioMananaPlato = (CheckBox)findViewById(R.id.checkBoxManana);
        CheckBox horarioTardePlato = (CheckBox)findViewById(R.id.checkBoxTarde);
        CheckBox horarioNochePlato = (CheckBox)findViewById(R.id.checkBoxNoche);
        RadioButton tipoEntradaPlato = (RadioButton)findViewById(R.id.radioBtnEntrada);
        RadioButton tipoPlatoFuerte = (RadioButton)findViewById(R.id.radioBtnPlatoFuerte);
        EditText precioPlato = (EditText)findViewById(R.id.txtPrecioPlato);
        TextView tiempoPlato = (TextView)findViewById(R.id.txtTiempoPlato);
        EditText ingredientesPlato = (EditText)findViewById(R.id.txtIngredientes);



        savedInstanceState.putString("nombrePlato",nombrePlato.getText().toString());
        savedInstanceState.putBoolean("horarioMananaPlato",horarioMananaPlato.isChecked());
        savedInstanceState.putBoolean("horarioTardePlato",horarioTardePlato.isChecked());
        savedInstanceState.putBoolean("horarioNochePlato",horarioNochePlato.isChecked());
        savedInstanceState.putBoolean("tipoEntradaPlato",tipoEntradaPlato.isChecked());
        savedInstanceState.putBoolean("tipoPlatoFuerte",tipoPlatoFuerte.isChecked());
        savedInstanceState.putString("precioPlato",precioPlato.getText().toString());
        savedInstanceState.putString("tiempoPlato",tiempoPlato.getText().toString());
        savedInstanceState.putString("ingredientesPlato",ingredientesPlato.getText().toString());

    }

    public void onRestoreInstanceState(Bundle savedInstanceState){

        /*image = savedInstanceState.getParcelable("BitmapImage");
        imgPlato.setImageBitmap(image);
        targetUri= data.getdata();
        textTargetUri.setText(savedInstanceState.getString("path_to_picture"));
        */

        EditText nombrePlato = (EditText)findViewById(R.id.txtNombrePlato);
        CheckBox horarioMananaPlato = (CheckBox)findViewById(R.id.checkBoxManana);
        CheckBox horarioTardePlato = (CheckBox)findViewById(R.id.checkBoxTarde);
        CheckBox horarioNochePlato = (CheckBox)findViewById(R.id.checkBoxNoche);
        RadioButton tipoEntradaPlato = (RadioButton)findViewById(R.id.radioBtnEntrada);
        RadioButton tipoPlatoFuerte = (RadioButton)findViewById(R.id.radioBtnPlatoFuerte);
        EditText precioPlato = (EditText)findViewById(R.id.txtPrecioPlato);
        TextView tiempoPlato = (TextView)findViewById(R.id.txtTiempoPlato);
        EditText ingredientesPlato = (EditText)findViewById(R.id.txtIngredientes);

        nombrePlato.setText(savedInstanceState.getString("nombrePlato"));
        horarioMananaPlato.setChecked(savedInstanceState.getBoolean("horarioMananaPlato"));
        horarioTardePlato.setChecked(savedInstanceState.getBoolean("horarioTardePlato"));
        horarioNochePlato.setChecked(savedInstanceState.getBoolean("horarioNochePlato"));
        tipoEntradaPlato.setChecked(savedInstanceState.getBoolean("tipoEntradaPlato"));
        tipoPlatoFuerte.setChecked(savedInstanceState.getBoolean("tipoPlatoFuerte"));
        precioPlato.setText(savedInstanceState.getString("precioPlato"));
        tiempoPlato.setText(savedInstanceState.getString("tiempoPlato"));
        ingredientesPlato.setText(savedInstanceState.getString("ingredientesPlato"));

    }


    public void mostrarInfoPlato(){
        ImageView imgPlatoGuardado = (ImageView)findViewById(R.id.imgPlatoGuardado);

        EditText nombrePlato = (EditText)findViewById(R.id.txtNombrePlato);
        CheckBox horarioMananaPlato = (CheckBox)findViewById(R.id.checkBoxManana);
        CheckBox horarioTardePlato = (CheckBox)findViewById(R.id.checkBoxTarde);
        CheckBox horarioNochePlato = (CheckBox)findViewById(R.id.checkBoxNoche);
        RadioButton tipoEntradaPlato = (RadioButton)findViewById(R.id.radioBtnEntrada);
        RadioButton tipoPlatoFuerte = (RadioButton)findViewById(R.id.radioBtnPlatoFuerte);
        EditText precioPlato = (EditText)findViewById(R.id.txtPrecioPlato);
        TextView tiempoPlato = (TextView)findViewById(R.id.txtTiempoPlato);
        EditText ingredientesPlato = (EditText)findViewById(R.id.txtIngredientes);

        TextView nombrePlatoGuardado = (TextView) findViewById(R.id.txtNombreGuardado);
        TextView horarioPlatoGuardado = (TextView) findViewById(R.id.txtHorarioGuardado);
        TextView tipoPlatoGuardado = (TextView) findViewById(R.id.txtTipoPlatoGuardado);
        TextView precioPlatoGuardado = (TextView) findViewById(R.id.txtPrecioPlatoGuardado);
        TextView tiempoPlatoGuardado = (TextView) findViewById(R.id.txtTiempoPlatoGuardado);
        TextView ingredientesGuardado = (TextView) findViewById(R.id.txtIngredientesGuardado);




            imgPlatoGuardado.setImageURI(selectedUri);


            nombrePlatoGuardado.setText(nombrePlato.getText().toString());

            if (horarioMananaPlato.isChecked()) {

                horarioPlatoGuardado.setText(horarioMananaPlato.getText().toString());
            }
            if (horarioTardePlato.isChecked()) {

                horarioPlatoGuardado.setText(horarioTardePlato.getText().toString());
            }
            if (horarioNochePlato.isChecked()) {

                horarioPlatoGuardado.setText(horarioNochePlato.getText().toString());
            }


            if (tipoEntradaPlato.isChecked()) {
                tipoPlatoGuardado.setText(tipoEntradaPlato.getText().toString());
            } else {
                tipoPlatoGuardado.setText(tipoPlatoFuerte.getText().toString());
            }

            precioPlatoGuardado.setText(precioPlato.getText().toString());
            tiempoPlatoGuardado.setText(tiempoPlato.getText().toString());
            ingredientesGuardado.setText(ingredientesPlato.getText().toString());






    }


}
