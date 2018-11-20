package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.UserDatabase;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.User;

public class ListUsersActivityViewModel extends ViewModel {

    private final UserDatabase userDatabase;
    private LiveData<List<User>> users;

    public ListUsersActivityViewModel(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public LiveData<List<User>> getStudents(boolean forceLoad) {
        if (users == null || forceLoad) {
            users = userDatabase.getUsers();
        }
        return users;
    }

    public void addUser(User user) {
        userDatabase.addUser(user);
    }

    public void deleteUser(User user) {
        userDatabase.deleteUser(user);
    }

    public void saveEditedUser(User user) {
        userDatabase.saveEditedUser(user);
    }
}
