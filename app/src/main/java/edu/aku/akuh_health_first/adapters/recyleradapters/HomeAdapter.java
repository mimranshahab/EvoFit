package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.helperclasses.ui.helper.AnimationHelper;
import edu.aku.akuh_health_first.libraries.imageloader.ImageLoaderHelper;
import edu.aku.akuh_health_first.models.receiving_model.UserDetailModel;
import edu.aku.akuh_health_first.widget.AnyTextView;

/**
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;


    private BaseActivity activity;
    private ArrayList<UserDetailModel> userList;

    public HomeAdapter(BaseActivity activity, ArrayList<UserDetailModel> userList, OnItemClickListener onItemClickListener) {
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
//        holder.txtViewProfile.setText(user.getName());
//        ImageLoader.getInstance().displayImage(users.get(i).getImageUrl(), holder.imgIcon);
        holder.txtName.setText(user.getName());
        holder.txtMRN.setText(user.getMRNumber());
        holder.txtGenderAge.setText(user.getGenderDescription() + " / " + user.getAge());
        holder.txtRelation.setText("(" + user.getRelationshipDescription() + ")");

        if (user.getProfileImage() == null || user.getProfileImage().isEmpty()) {
            if (user.getGender().equals("F")) {

                holder.imgUser.setImageResource(R.drawable.female_icon);
            } else {
                holder.imgUser.setImageResource(R.drawable.male_icon);
            }

        } else {
            ImageLoaderHelper.loadImageWithConstantHeadersWithoutAnimation(activity, holder.imgUser, user.getProfileImage());
        }
        TransitionDrawable transition = (TransitionDrawable) holder.contListItem.getBackground();

        if (user.isSelected()) {
//            holder.contListItem.setBackgroundResource(R.drawable.round_edges_two_sides_selected);
            transition.startTransition(800);
            holder.txtMRN.setTextColor(activity.getResources().getColor(R.color.c_white));
            holder.txtGenderAge.setTextColor(activity.getResources().getColor(R.color.c_white));
            holder.cardView2.setCardBackgroundColor(activity.getResources().getColor(R.color.colorPrimaryDark));
            holder.txtName.setTextColor(activity.getResources().getColor(R.color.c_white));
            holder.txtRelation.setTextColor(activity.getResources().getColor(R.color.c_white));
            holder.imgNext.setColorFilter(activity.getResources().getColor(R.color.c_white));
        } else {
//            holder.contListItem.setBackgroundResource(R.drawable.selected_user_transition);
            transition.resetTransition();
            holder.imgNext.setColorFilter(activity.getResources().getColor(R.color.base_amber));
            holder.txtName.setTextColor(activity.getResources().getColor(R.color.base_amber));
            holder.txtRelation.setTextColor(activity.getResources().getColor(R.color.base_amber));
            holder.cardView2.setCardBackgroundColor(activity.getResources().getColor(R.color.base_amber));
            holder.txtMRN.setTextColor(activity.getResources().getColor(R.color.txt_aku_blue));
            holder.txtGenderAge.setTextColor(activity.getResources().getColor(R.color.txt_aku_blue));
        }
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
        @BindView(R.id.imgIcon)
        CircleImageView imgUser;
        @BindView(R.id.txtName)
        AnyTextView txtName;
        @BindView(R.id.txtRelation)
        AnyTextView txtRelation;
        @BindView(R.id.txtMRN)
        AnyTextView txtMRN;
        @BindView(R.id.txtGender_age)
        AnyTextView txtGenderAge;
        @BindView(R.id.contListItem)
        LinearLayout contListItem;
        @BindView(R.id.cardView2)
        CardView cardView2;
        @BindView(R.id.imgNext)
        ImageView imgNext;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
