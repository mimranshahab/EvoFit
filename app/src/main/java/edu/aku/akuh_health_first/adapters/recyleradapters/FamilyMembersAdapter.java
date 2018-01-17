package edu.aku.akuh_health_first.adapters.recyleradapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.aku.akuh_health_first.R;
import edu.aku.akuh_health_first.activities.BaseActivity;
import edu.aku.akuh_health_first.callbacks.OnItemClickListener;
import edu.aku.akuh_health_first.models.receiving_model.FamilyMembersList;
import edu.aku.akuh_health_first.views.AnyTextView;

/**
 */
public class FamilyMembersAdapter extends RecyclerView.Adapter<FamilyMembersAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;
    private Activity activity;
    private ArrayList<FamilyMembersList> familyMembersLists;

    public FamilyMembersAdapter(Activity activity, ArrayList<FamilyMembersList> familyMembersLists, OnItemClickListener onItemClickListener) {
        this.familyMembersLists = familyMembersLists;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_family_member, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i) {

        final FamilyMembersList familyMembersList = familyMembersLists.get(i);
//        ImageLoader.getInstance().displayImage(familyMembersLists.get(i).getImageUrl(), holder.imgUser);
        holder.txtName.setText(familyMembersList.getName());
//        holder.txtViewProfile.setText(familyMembersList.getName());
        holder.txtMRN.setText("MR# " + familyMembersList.getMRNumber());
        holder.txtGender.setText("Gender " + familyMembersList.getGender());
        holder.txtAge.setText("Age " + familyMembersList.getAge());
        holder.txtEmailAddress.setText("EmailAddress " + familyMembersList.getEmailAddress());


        holder.contListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(i, familyMembersList);
            }
        });
    }

    public void addItem(ArrayList<FamilyMembersList> homeCategories) {
        this.familyMembersLists = homeCategories;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return familyMembersLists.size();
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

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
