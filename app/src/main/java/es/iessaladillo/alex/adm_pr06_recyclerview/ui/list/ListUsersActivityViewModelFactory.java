package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.UserDatabase;

public class ListUsersActivityViewModelFactory implements ViewModelProvider.Factory {

    private UserDatabase userDatabase;

    public ListUsersActivityViewModelFactory(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ListUsersActivityViewModel(userDatabase);
    }
}
