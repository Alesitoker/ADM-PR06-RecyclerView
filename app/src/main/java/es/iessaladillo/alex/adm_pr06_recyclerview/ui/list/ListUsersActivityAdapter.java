package es.iessaladillo.alex.adm_pr06_recyclerview.ui.list;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.alex.adm_pr06_recyclerview.R;
import es.iessaladillo.alex.adm_pr06_recyclerview.local.model.User;

public class ListUsersActivityAdapter extends ListAdapter<User, ListUsersActivityAdapter.ViewHolder> {

    private OnEditUserClickListener onEditUserClickListener;
    private OnDeleteUserClickListener onDeleteUserClickListener;

    public ListUsersActivityAdapter(OnEditUserClickListener onEditUserClickListener, OnDeleteUserClickListener onDeleteUserClickListener) {
        super(new DiffUtil.ItemCallback<User>() {
            @Override
            public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) && TextUtils.equals(oldItem.getEmail(), newItem.getEmail())
                        && oldItem.getPhoneNumber() == newItem.getPhoneNumber();
            }
        });
        this.onEditUserClickListener = onEditUserClickListener;
        this.onDeleteUserClickListener = onDeleteUserClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_item, parent, false), onEditUserClickListener, onDeleteUserClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public User getItem(int position) {
        return super.getItem(position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblName;
        private final TextView lblEmail;
        private final TextView lblPhonenumber;
        private final ImageView imgAvatar;
        private final Button btnEdit;
        private final Button btnDelete;

        public ViewHolder(@NonNull View itemView, OnEditUserClickListener onEditUserClickListener, OnDeleteUserClickListener onDeleteUserClickListener) {
            super(itemView);
            imgAvatar = ViewCompat.requireViewById(itemView, R.id.imgAvatar);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblEmail = ViewCompat.requireViewById(itemView, R.id.lblEmail);
            lblPhonenumber = ViewCompat.requireViewById(itemView, R.id.lblPhonenumber);
            btnEdit = ViewCompat.requireViewById(itemView, R.id.btnEdit);
            btnDelete = ViewCompat.requireViewById(itemView, R.id.btnDelete);

            btnEdit.setOnClickListener(v -> onEditUserClickListener.onItemClick(getAdapterPosition()));
            btnDelete.setOnClickListener(v -> onDeleteUserClickListener.onItemClick(getAdapterPosition()));

        }

        public void bind(User user) {
            imgAvatar.setImageResource(user.getAvatar().getImageResId());
            lblName.setText(user.getName());
            lblEmail.setText(user.getEmail());
            lblPhonenumber.setText(String.valueOf(user.getPhoneNumber()));
        }
    }
}
