package edu.aku.evofit.adapters.recyleradapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import edu.aku.evofit.R;
import edu.aku.evofit.activities.BaseActivity;
import edu.aku.evofit.callbacks.OnItemClickListener;
import edu.aku.evofit.libraries.imageloader.ImageLoaderHelper;
import edu.aku.evofit.models.DepartmentModel;
import edu.aku.evofit.models.NewsFeedModel;
import edu.aku.evofit.widget.AnyTextView;

/**
 */
public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.ViewHolder> {


    private final OnItemClickListener onItemClick;



    private BaseActivity activity;
    private ArrayList<NewsFeedModel> arrData;

    public NewsFeedAdapter(BaseActivity activity, ArrayList<NewsFeedModel> arrData, OnItemClickListener onItemClickListener) {
        this.arrData = arrData;
        this.activity = activity;
        this.onItemClick = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(activity)
                .inflate(R.layout.item_newsfeed, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int i) {
        NewsFeedModel model = arrData.get(i);
        holder.txtDesc.setText(model.getDesc());
        holder.txtTitle.setText(model.getTitle());
        holder.txtUserName.setText(model.getUserName());


        ImageLoader.getInstance().displayImage(model.getImageURL(), holder.imgUser);


    }


    @Override
    public int getItemCount() {
        return arrData.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgUser)
        CircleImageView imgUser;
        @BindView(R.id.txtUserName)
        AnyTextView txtUserName;
        @BindView(R.id.txtTitle)
        AnyTextView txtTitle;
        @BindView(R.id.txtDesc)
        AnyTextView txtDesc;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
