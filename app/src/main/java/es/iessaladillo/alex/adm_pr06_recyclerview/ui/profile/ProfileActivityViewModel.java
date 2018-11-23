package es.iessaladillo.alex.adm_pr06_recyclerview.ui.profile;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.Database;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.Avatar;

public class ProfileActivityViewModel extends ViewModel {

    private final Database database = Database.getInstance();
    private Avatar avatar;
    private boolean valid_name = true;
    private boolean valid_email = true;
    private boolean valid_phonenumber = true;
    private boolean valid_address = true;
    private boolean valid_web = true;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Avatar getDefaultAvatar() {
        return database.getDefaultAvatar();
    }

    public boolean isValid_name() {
        return valid_name;
    }

    public void setValid_name(boolean valid_name) {
        this.valid_name = valid_name;
    }

    public boolean isValid_email() {
        return valid_email;
    }

    public void setValid_email(boolean valid_email) {
        this.valid_email = valid_email;
    }

    public boolean isValid_phonenumber() {
        return valid_phonenumber;
    }

    public void setValid_phonenumber(boolean valid_phonenumber) {
        this.valid_phonenumber = valid_phonenumber;
    }

    public boolean isValid_address() {
        return valid_address;
    }

    public void setValid_address(boolean valid_address) {
        this.valid_address = valid_address;
    }

    public boolean isValid_web() {
        return valid_web;
    }

    public void setValid_web(boolean valid_web) {
        this.valid_web = valid_web;
    }
}
