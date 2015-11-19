package br.com.celularegistrado.appcelularregistrado.WS;

import java.util.List;

import br.com.celularegistrado.appcelularregistrado.Model.Celular;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by renanjunior on 06/03/15.
 */
public interface AppService {


    /**
     *  Busca Pet por ID
     *
     * @param id
     * @return
     */
    @GET("/cad_celular/imei/{id}")
    public Celular getCelularImei(@Path("id") Integer id);

    @GET("/cad_celular/tag/{id}")
    public Celular getCelularTag(@Path("id") Integer id);

    @GET("/cad_celular/qrcode/{id}")
    public Celular getCelularQRCode(@Path("id") String id);


}
