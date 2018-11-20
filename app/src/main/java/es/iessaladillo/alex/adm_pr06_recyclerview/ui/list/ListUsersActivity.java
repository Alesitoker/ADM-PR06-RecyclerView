package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import es.iessaladillo.alex.adm_pr06_recyclerview.R;
import es.iessaladillo.alex.adm_pr06_recyclerview.databinding.ActivityListBinding;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.UserDatabase;

public class ListUsersActivity extends AppCompatActivity {

    private ActivityListBinding b;
    private ListUsersActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_list);
        viewModel = ViewModelProviders.of(this, new ListUsersActivityViewModelFactory(UserDatabase.getInstance())).get(ListUsersActivityViewModel.class);
    }
}
