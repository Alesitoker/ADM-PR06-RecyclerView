package es.iessaladillo.alex.adm_pr06_recyclerview.local;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.User;

public class UserDatabase {

    private static UserDatabase instance;
    private List<User> users;
    private MutableLiveData<List<User>> usersLiveData = new MutableLiveData<>();

    private UserDatabase() {
        AvatarDatabase avatarDatabase = AvatarDatabase.getInstance();
        users = new ArrayList<>(Arrays.asList(
                new User(avatarDatabase.queryAvatar(1), "Baldo", "baldo@mero.com", 666666666, "La casa de baldo", "http://www.marca.com"),
                new User(avatarDatabase.queryAvatar(2), "Pollo", "pollo@frito.es", 962746501, "El hogar", "https://www.youtube.com"),
                new User(avatarDatabase.queryAvatar(3), "Aguacate", "Aguacatecito@sinmas.com", 972906203, "No tengo", "https://asoftmurmur.com/")
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

    public LiveData<List<User>> getUsers() {
        return usersLiveData;
    }

    private void updateUsersLiveData() {
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
