package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.Database;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.User;

public class ListUsersActivityViewModel extends ViewModel {

    private final Database database;
    private LiveData<List<User>> users;

    public ListUsersActivityViewModel(Database database) {
        this.database = database;
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = database.getUsers();
        }
        return users;
    }

    public void deleteUser(User user) {
        database.deleteUser(user);
    }

}
