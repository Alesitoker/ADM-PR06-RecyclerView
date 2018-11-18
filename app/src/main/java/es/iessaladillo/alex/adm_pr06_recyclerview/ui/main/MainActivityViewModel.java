package es.iessaladillo.alex.adm_pr06_recyclerview.ui.main;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.Database;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.Avatar;

public class MainActivityViewModel extends ViewModel {

    private final Database database = Database.getInstance();
    private Avatar avatar;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Avatar getDefaultAvatar() {
        return database.getDefaultAvatar();
    }
}
