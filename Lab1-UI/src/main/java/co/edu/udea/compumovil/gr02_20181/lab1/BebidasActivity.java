package co.edu.udea.compumovil.gr02_20181.lab1;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.Manifest;
import android.view.ViewGroup;





import java.util.ArrayList;

import gun0912.tedbottompicker.TedBottomPicker;

public class BebidasActivity extends AppCompatActivity {

    public RequestManager mGlideRequestManager;
    ImageView imgBebida;
    Uri selectedUri;
    private ViewGroup mSelectedImagesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bebidas);

        mGlideRequestManager = Glide.with(this);
        imgBebida = (ImageView) findViewById(R.id.imgBebida);
        mSelectedImagesContainer = (ViewGroup) findViewById(R.id.selected_photos_container);
        setSingleShowButton();
    }

    public void onClick(View v) {

        switch (v.getId()) {

             case R.id.btnAgregarBebida:
                /*Bundle bundle = new Bundle();
                onSaveInstanceState(bundle);*/
                RelativeLayout relativeLayoutInfoBebida = (RelativeLayout)findViewById(R.id.relativeLayoutInfoBebida);
                relativeLayoutInfoBebida.setVisibility(View.VISIBLE);
                mostrarInfoBebida();
                break;

        }

    }

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


    private void setSingleShowButton() {



        Button btn_single_show = (Button) findViewById(R.id.btnAgregarImgBebida);
        btn_single_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                PermissionListener permissionlistener = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {

                        TedBottomPicker bottomSheetDialogFragment = new TedBottomPicker.Builder(BebidasActivity.this)
                                .setOnImageSelectedListener(new TedBottomPicker.OnImageSelectedListener() {
                                    @Override
                                    public void onImageSelected(final Uri uri) {
                                        Log.d("ted", "uri: " + uri);
                                        Log.d("ted", "uri.getPath(): " + uri.getPath());
                                        selectedUri = uri;

                                        imgBebida.setVisibility(View.VISIBLE);
                                        //mSelectedImagesContainer.setVisibility(View.GONE);
                                        imgBebida.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                mGlideRequestManager
                                                        .load(uri)
                                                        .into(imgBebida);
                                            }
                                        });
                                        /*
                                        Glide.with(MainActivity.this)
                                                //.load(uri.toString())
                                                .load(uri)
                                                .into(iv_image);
                                         */
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
                        Toast.makeText(BebidasActivity.this, "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                    }


                };

                TedPermission.with(BebidasActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        .check();


            }
        });
    }


    public void mostrarInfoBebida(){
        ImageView imgBebidaGuardado = (ImageView)findViewById(R.id.imgBebidaGuardado);

        EditText nombreBebida = (EditText)findViewById(R.id.txtNombreBebida);
        EditText precioBebida = (EditText)findViewById(R.id.txtPrecioBebida);
        EditText ingredientesBebida = (EditText)findViewById(R.id.txtIngredientesBebida);

        TextView nombreBebidaGuardado = (TextView) findViewById(R.id.txtNombreBebidaGuardado);
        TextView precioBebidaGuardado = (TextView) findViewById(R.id.txtPrecioBebidaGuardado);
        TextView ingredientesBebidaGuardado = (TextView) findViewById(R.id.txtIngredientesBebidaGuardado);




        imgBebidaGuardado.setImageURI(selectedUri);
        nombreBebidaGuardado.setText(nombreBebida.getText().toString());


        precioBebidaGuardado.setText(precioBebida.getText().toString());
        ingredientesBebidaGuardado.setText(ingredientesBebida.getText().toString());






    }

}
