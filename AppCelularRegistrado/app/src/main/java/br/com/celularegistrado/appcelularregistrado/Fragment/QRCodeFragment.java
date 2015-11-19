package br.com.celularegistrado.appcelularregistrado.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import br.com.celularegistrado.appcelularregistrado.Activity.QRCodeInfoActivity;
import br.com.celularegistrado.appcelularregistrado.Activity.ResultadoActivity;
import br.com.celularegistrado.appcelularregistrado.Model.Celular;
import br.com.celularegistrado.appcelularregistrado.R;
import br.com.celularegistrado.appcelularregistrado.WS.RestClient;


public class QRCodeFragment extends Fragment implements QRCodeReaderView.OnQRCodeReadListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView txtHtml;
    private View v;
    public String campoPesquisa;
    public CardView card_view;

    private QRCodeReaderView qrdecoderview;
    public FloatingActionButton fab;
    public TextView txtAlertaQRCode;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static QRCodeFragment newInstance(String param1, String param2) {
        QRCodeFragment fragment = new QRCodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public QRCodeFragment() {
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
        v = inflater.inflate(R.layout.fragment_qrcode, container, false);

        txtHtml = (TextView) v.findViewById(R.id.txtHtml);
        card_view = (CardView) v.findViewById(R.id.card_view);
        txtAlertaQRCode = (TextView) v.findViewById(R.id.txtAlertaQRCode);

        txtHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), QRCodeInfoActivity.class);
                startActivity(i);
            }
        });

        txtHtml.setText(Html.fromHtml("<font>QR Code (</font><font color=\"#36B9BD\">O que é Isso?</font>)"));

        checkCameraHardware(this.getActivity());


        qrdecoderview = (QRCodeReaderView) v.findViewById(R.id.qrdecoderview);
        qrdecoderview.setOnQRCodeReadListener(this);
        qrdecoderview.setFilterTouchesWhenObscured(true);

        fab = (FloatingActionButton) v.findViewById(R.id.fab);
        fab.setEnabled(false);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetQRCodeTask();
            }
        });

        return v;
    }

    public void GetQRCodeTask(){

        new AsyncTask<Void, Void, Celular>(){

            Celular celular;

            @Override
            protected Celular doInBackground(Void... params) {

                celular = RestClient.getInstance().getCelularQRCode(campoPesquisa);
                return celular;
            }

            @Override
            protected void onPostExecute(Celular celular) {
                super.onPostExecute(celular);

                if(celular != null) {
                    card_view.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                    fab.setEnabled(false);
                    Intent i = new Intent(getActivity(), ResultadoActivity.class);
                    i.putExtra("celular",celular);
                    startActivity(i);
                }else{
                    Toast.makeText(getActivity(), "Celular não cadastrado em nossa base de dados!", Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {

        if (text != null) {
            campoPesquisa = text;
            qrdecoderview.getCameraManager().stopPreview();
            card_view.setBackgroundColor(getResources().getColor(R.color.colorAlertPositivo));
            fab.setEnabled(true);

        } else {
            Toast.makeText(getActivity(), "QRCode inválido !", Toast.LENGTH_SHORT).show();
            card_view.setBackgroundColor(getResources().getColor(R.color.colorAlertNegativo));
        }

    }

    @Override
    public void cameraNotFound() {
       // Toast.makeText(getActivity(), "QRCode não instalado !", Toast.LENGTH_SHORT).show();
       // qrdecoderview.setEnabled(false);
       // qrdecoderview.getCameraManager().stopPreview();
       // txtAlertaQRCode.setVisibility(View.VISIBLE);
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {
        //Toast.makeText(getActivity(), "QRCode não instalado !", Toast.LENGTH_SHORT).show();
        //qrdecoderview.setEnabled(false);
        //qrdecoderview.getCameraManager().stopPreview();
        //txtAlertaQRCode.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResume() {
        super.onResume();
        qrdecoderview.getCameraManager().startPreview();
    }

    @Override
    public void onPause() {
        super.onPause();
        qrdecoderview.getCameraManager().stopPreview();
    }

    private boolean checkCameraHardware(Context context) {
        if (!context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT)){
                // this device has a front camera
                return true;
            }
            else if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
                // this device has any camera
                return true;
            }
            else {
                // no camera on this device
                return false;
            }
        } else {
            // this device has a camera
            return true;
        }
    }

}
