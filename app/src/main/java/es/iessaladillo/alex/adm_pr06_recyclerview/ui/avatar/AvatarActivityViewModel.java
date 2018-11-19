package es.iessaladillo.alex.adm_pr06_recyclerview.ui.avatar;

import java.util.List;

import androidx.lifecycle.ViewModel;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.AvatarDatabase;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.Avatar;

public class AvatarActivityViewModel extends ViewModel {

    private final AvatarDatabase avatarDatabase = AvatarDatabase.getInstance();
    private Avatar avatar;

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void changeAvatar(int position) {
        this.avatar = avatarDatabase.queryAvatar(avatarDatabase.queryAvatars().get(position).getId());
    }

    public List<Avatar> queryAvatars() {
        return avatarDatabase.queryAvatars();
    }
}
