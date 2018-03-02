package co.edu.udea.compumovil.gr02_20181.lab1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

    }

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRegistrarPlatos:
                Intent intentPlatos = new Intent(this,PlatosActivity.class);
                /*Bundle bun = new Bundle();
                onSaveInstanceState(bun);
                intentPlatos.putExtras(bun);*/
                startActivity(intentPlatos);

                break;

            case R.id.btnRegistrarBebida:
                Intent intentBebidas = new Intent(this,BebidasActivity.class);
                /*Bundle bunB = new Bundle();
                onSaveInstanceState(bunB);
                intentBebidas.putExtras(bunB);*/
                startActivity(intentBebidas);
                break;

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.itemMenupplSalir:
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
   /* public void onRestoreInstanceState(Bundle savedInstanceState){

        /*image = savedInstanceState.getParcelable("BitmapImage");
        imgPlato.setImageBitmap(image);
        targetUri= data.getdata();
        textTargetUri.setText(savedInstanceState.getString("path_to_picture"));


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

    }*/

}
