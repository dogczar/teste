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
     *  Busca lista de todos os Pets
     *  */
    @GET("/pets/")
    public List<Celular> getPets();


    /**
     *  Busca Pet por ID
     *
     * @param id
     * @return
     */
    @GET("/pets/{id}")
    public Celular getPetId(@Path("id") Integer id);


    /**
     *  Busca Pets por tipo
     *
     * @param tipo
     * @return
     */
    @GET("/pets/tipo/{tipo}")
    public List<Celular> getPetsByTipo(@Path("tipo") String tipo);


    /**
     *  Deleta Pet por ID
     *
     * @param id
     * @return
     */
    @DELETE("/pets/{id}")
    public Celular RemovePetId(@Path("id") Integer id);


    /**
     * Salva ou atualiza um Pet
     * @param pet
     */

    @POST("/pets")
    public void SavePet(@Body Celular pet, Callback<String> cb);


}
