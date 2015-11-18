package br.com.celularegistrado.appcelularregistrado.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import br.com.celularegistrado.appcelularregistrado.Activity.ImeiInfoActivity;
import br.com.celularegistrado.appcelularregistrado.R;
import br.com.celularegistrado.appcelularregistrado.Activity.ResultadoActivity;


public class ImeiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView txtHtml;
    private View v;
    private TextView sinalizador;
    private TextView txtObrigatorio;
    private EditText txtCampo;


    // TODO: Rename and change types and number of parameters
    public static ImeiFragment newInstance(String param1, String param2) {
        ImeiFragment fragment = new ImeiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ImeiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_imei, container, false);

        txtHtml = (TextView) v.findViewById(R.id.txtHtml);
        txtHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ImeiInfoActivity.class);
                startActivity(i);
            }
        });
        txtHtml.setText(Html.fromHtml("<font>IMEI (</font><font color=\"#36B9BD\">O que é Isso?</font>)"));

        txtObrigatorio = (TextView)  v.findViewById(R.id.txtObrigatorio);
        sinalizador = (TextView) v.findViewById(R.id.sinalizador);
        txtCampo = (EditText) v.findViewById(R.id.txtCampo);
        txtCampo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sinalizador.setTextColor(getResources().getColor(R.color.colorAlertPositivo));
                sinalizador.setBackgroundColor(getResources().getColor(R.color.colorAlertPositivo));
                txtObrigatorio.setTextColor(getResources().getColor(R.color.colorAlertPositivo));

            }
        });
        FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtCampo.length()>5) {
                    sinalizador.setTextColor(getResources().getColor(R.color.colorAlertPositivo));
                    sinalizador.setBackgroundColor(getResources().getColor(R.color.colorAlertPositivo));
                    txtObrigatorio.setTextColor(getResources().getColor(R.color.colorAlertPositivo));
                    Intent i = new Intent(getActivity(), ResultadoActivity.class);
                    startActivity(i);
                }else{
                    sinalizador.setTextColor(getResources().getColor(R.color.colorAlertNegativo));
                    sinalizador.setBackgroundColor(getResources().getColor(R.color.colorAlertNegativo));
                    txtObrigatorio.setTextColor(getResources().getColor(R.color.colorAlertNegativo));
                }
            }
        });

        return v;
    }

}
