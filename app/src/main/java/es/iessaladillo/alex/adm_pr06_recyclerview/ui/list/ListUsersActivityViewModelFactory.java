package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.Database;

public class ListUsersActivityViewModelFactory implements ViewModelProvider.Factory {

    private Database database;

    public ListUsersActivityViewModelFactory(Database database) {
        this.database = database;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ListUsersActivityViewModel(database);
    }
}
