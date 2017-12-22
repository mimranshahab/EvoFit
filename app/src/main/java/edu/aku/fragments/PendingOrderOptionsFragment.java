package edu.aku.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ctrlplusz.anytextview.AnyTextView;
import edu.aku.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by khanhamza on 19-May-17.
 */

public class PendingOrderOptionsFragment extends Fragment {
    @BindView(R.id.txtAddRemove)
    AnyTextView txtAddRemove;
    @BindView(R.id.txtEditScheduling)
    AnyTextView txtEditScheduling;
    @BindView(R.id.txtDelete)
    AnyTextView txtDelete;
    @BindView(R.id.txtClose)
    AnyTextView txtClose;
    Unbinder unbinder;
    private int position;
    private View.OnClickListener onClickListener;

    public static PendingOrderOptionsFragment newInstance(int position, View.OnClickListener onClickListener) {

        Bundle args = new Bundle();

        PendingOrderOptionsFragment fragment = new PendingOrderOptionsFragment();
        fragment.onClickListener = onClickListener;
        fragment.position = position;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setListeners();
    }


    private void setListeners() {
        txtAddRemove.setTag(position);
        txtAddRemove.setOnClickListener(onClickListener);

        txtEditScheduling.setTag(position);
        txtEditScheduling.setOnClickListener(onClickListener);

        txtClose.setTag(position);
        txtClose.setOnClickListener(onClickListener);

        txtDelete.setTag(position);
        txtDelete.setOnClickListener(onClickListener);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view


        View rootView =  inflater.inflate(R.layout.fragment_pending_options, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
