package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import es.iessaladillo.alex.adm_pr06_recyclerview.R;
import es.iessaladillo.alex.adm_pr06_recyclerview.databinding.ActivityListBinding;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.UserDatabase;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.User;
import es.iessaladillo.alex.adm_pr06_recyclerview.ui.profile.ProfileActivity;

public class ListUsersActivity extends AppCompatActivity {

    private static final int RC_EDIT = 13;
    private ActivityListBinding b;
    private ListUsersActivityViewModel viewModel;
    private ListUsersActivityAdapter listAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = DataBindingUtil.setContentView(this, R.layout.activity_list);
        viewModel = ViewModelProviders.of(this, new ListUsersActivityViewModelFactory(UserDatabase.getInstance())).get(ListUsersActivityViewModel.class);
        setupViews();
        observerUsers();
    }

    private void observerUsers() {
        viewModel.getUsers().observe(this, this::refresherListAdapter);
    }

    private void refresherListAdapter(List<User> users) {
        listAdapter.submitList(users);
        b.lblEmptyView.setVisibility(users.size() == 0 ? View.VISIBLE : View.INVISIBLE);
    }

    private void setupViews() {
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        listAdapter = new ListUsersActivityAdapter(position -> editUser(listAdapter.getItem(position)), position -> viewModel.deleteUser(listAdapter.getItem(position)));

        b.lstUsers.setHasFixedSize(true);
        b.lstUsers.setLayoutManager(new GridLayoutManager(this, getResources().getInteger(R.integer.lstUsers_columns)));
        b.lstUsers.setItemAnimator(new DefaultItemAnimator());
        b.lstUsers.setAdapter(listAdapter);

    }

    private void editUser(User user) {
        ProfileActivity.startForResult(this, RC_EDIT, user);
    }
}
