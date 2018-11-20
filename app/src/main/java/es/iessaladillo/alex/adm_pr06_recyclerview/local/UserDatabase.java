package es.iessaladillo.alex.adm_pr06_recyclerview.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.User;

public class UserDatabase {

    private static UserDatabase instance;
    private List<User> users = new ArrayList<>();
    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

    private UserDatabase() {
        users = new ArrayList<>(Arrays.asList(
                new User("Baldo", "baldo@mero.com", 666666666, "La casa de baldo", "http://www.marca.com"),
                new User("Pollo", "pollo@frito.es", 962746501, "El hogar", "https://www.youtube.com"),
                new User("Aguacate", "Aguacatecito@sinmas.com", 972906203, "No tengo", "https://asoftmurmur.com/")
        ));
        updateUsersLiveData();
    }

    public static UserDatabase getInstance() {
        if (instance == null) {
            synchronized (UserDatabase.class) {
                if (instance == null) {
                    instance = new UserDatabase();
                }
            }
        }
        return instance;
    }

    public void updateUsersLiveData() {
        usersLiveData.setValue(users);
    }

    public void addUser(User user) {
        users.add(user);
        updateUsersLiveData();
    }

    public void deleteUser(User user) {
        users.remove(user);
        updateUsersLiveData();
    }

    public void saveEditedUser(User user) {
        users.set(users.indexOf(user), user);
        updateUsersLiveData();
    }


}
