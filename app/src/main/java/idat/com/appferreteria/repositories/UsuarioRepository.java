package idat.com.appferreteria.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import idat.com.appferreteria.api.ConfigApi;
import idat.com.appferreteria.api.UsuarioApi;
import idat.com.appferreteria.model.GenericResponse;
import idat.com.appferreteria.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsuarioRepository {

    private static UsuarioRepository repository;
    private final UsuarioApi api;


    public UsuarioRepository() {
        this.api = ConfigApi.getUsuarioApi();
    }

    public static UsuarioRepository getInstance(){
        if (repository == null){
            repository = new UsuarioRepository();
        }
        return repository;
    }

    public LiveData<GenericResponse<Usuario>> login(String email, String password){
        final MutableLiveData<GenericResponse<Usuario>> mld = new MutableLiveData<>();
        this.api.login(email,password).enqueue(new Callback<GenericResponse<Usuario>>() {
            @Override
            public void onResponse(Call<GenericResponse<Usuario>> call, Response<GenericResponse<Usuario>> response) {
                mld.setValue(response.body());
            }

            @Override
            public void onFailure(Call<GenericResponse<Usuario>> call, Throwable t) {
                mld.setValue(new GenericResponse<>());
                System.out.println(t.getMessage());
                t.printStackTrace();
            }
        });
        return mld;
    }
}
