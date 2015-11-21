package br.com.celularegistrado.appcelularregistrado.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import br.com.celularegistrado.appcelularregistrado.Model.Celular;
import br.com.celularegistrado.appcelularregistrado.R;

public class ResultadoActivity extends AppCompatActivity {

    public Celular celular;
    private TextView nome_usuario;
    private TextView status_celular;
    private TextView modelo_celular;
    private TextView operadora_celular;
    private TextView imei_celular;
    private CardView card_negativo;
    private CardView card_positivo;
    private CircularImageView foto_usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        Intent i = getIntent();
        celular = (Celular)i.getSerializableExtra("celular");
        nome_usuario = (TextView) findViewById(R.id.nome_usuario);
        status_celular = (TextView) findViewById(R.id.status_celular);
        modelo_celular = (TextView) findViewById(R.id.modelo_celular);
        operadora_celular = (TextView) findViewById(R.id.operadora_celular);
        imei_celular = (TextView) findViewById(R.id.imei_celular);
        card_negativo = (CardView) findViewById(R.id.card_negatigo);
        card_positivo = (CardView) findViewById(R.id.card_positivo);
        foto_usuario = (CircularImageView) findViewById(R.id.foto_usuario);
        Picasso.with(this).load(R.mipmap.loading).into(foto_usuario);
        if(celular.getFoto_usuario()==null){
            Picasso.with(this).load(R.mipmap.no_avatar).into(foto_usuario);
        }else {
            Picasso.with(this).load(celular.getFoto_usuario()).into(foto_usuario);
        }
        nome_usuario.setText(celular.getNome_usuario());
        if(celular.getAlerta_celular()==1){
            status_celular.setText("Roubado");
            status_celular.setTextColor(getResources().getColor(R.color.colorAlertNegativo));
            card_negativo.setVisibility(View.VISIBLE);
            card_positivo.setVisibility(View.GONE);
        }else{
            status_celular.setText("Regular");
            status_celular.setTextColor(getResources().getColor(R.color.colorAlertPositivo));
            card_negativo.setVisibility(View.GONE);
            card_positivo.setVisibility(View.VISIBLE);
        }
        modelo_celular.setText(celular.getNome_modelo() + "/" + celular.getNome_fabricante());
        operadora_celular.setText(celular.getNome_operadora());
        imei_celular.setText(celular.getImei_celular());


    }
}
