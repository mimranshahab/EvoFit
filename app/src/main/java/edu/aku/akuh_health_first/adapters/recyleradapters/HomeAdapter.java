package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.helperclasses.ui.helper.AnimationHelper;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;
    private Activity activity;
    private ArrayList<UserDetailModel> userList;

    public HomeAdapter(Activity activity, ArrayList<UserDetailModel> userList, OnItemClickListener onItemClickListener) {
        this.userList = userList;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_user, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {

        final UserDetailModel user = userList.get(holder.getAdapterPosition());
//        ImageLoader.getInstance().displayImage(users.get(i).getImageUrl(), holder.imgUser);
        holder.txtName.setText(user.getName());
//        holder.txtViewProfile.setText(user.getName());
        holder.txtMRN.setText("MR# " + user.getMRNumber());
        holder.txtGender.setText("Gender " + user.getGender());
        holder.txtAge.setText("Age " + user.getAge());
        holder.txtEmailAddress.setText("EmailAddress " + user.getEmailAddress());


        AnimationHelper.fade(((ViewHolder) holder).viewSelected, 0.0f, View.VISIBLE, View.VISIBLE, 0.3f, 300);

        setListener(holder, user);
    }

    private void setListener(final ViewHolder holder, final UserDetailModel user) {
        holder.contListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(holder.getAdapterPosition(), user);
            }
        });
    }

    public void addItem(ArrayList<UserDetailModel> homeCategories) {
        this.userList = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return userList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgUser)
        ImageView imgUser;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtViewProfile)
        AnyTextView txtViewProfile;
        @BindView(R.id.txtMRN)
        AnyTextView txtMRN;
        @BindView(R.id.txtGender)
        AnyTextView txtGender;
        @BindView(R.id.txtAge)
        AnyTextView txtAge;
        @BindView(R.id.txtEmailAddress)
        AnyTextView txtEmailAddress;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.viewSelected)
        View viewSelected;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
