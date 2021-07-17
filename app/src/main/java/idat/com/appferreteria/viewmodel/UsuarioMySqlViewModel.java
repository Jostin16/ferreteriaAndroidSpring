package idat.com.appferreteria.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import idat.com.appferreteria.model.GenericResponse;
import idat.com.appferreteria.model.Usuario;
import idat.com.appferreteria.repositories.UsuarioRepository;


public class UsuarioMySqlViewModel extends AndroidViewModel {

    private final UsuarioRepository repository;


    public UsuarioMySqlViewModel(@NonNull Application application) {
        super(application);
        this.repository = UsuarioRepository.getInstance();
    }

    public LiveData<GenericResponse<Usuario>> login(String email, String password){
        return this.repository.login(email,password);
    }
}
