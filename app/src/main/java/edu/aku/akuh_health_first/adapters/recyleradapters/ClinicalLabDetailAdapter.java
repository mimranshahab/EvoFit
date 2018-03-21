package edu.aku.akuh_health_first.adapters.recyleradapters;

/**
 * Created by aqsa.sarwar on 1/25/2018.
 */

public class ClinicalLabDetailAdapter{
//        extends RecyclerView.Adapter<ClinicalLabDetailAdapter.ViewHolder> {

//    private final ArrayList<LstLaboratorySpecimenResults> arrClinicalLabLists;
//    private BaseActivity activity;
//    private OnItemClickListener onItemClickListener;
//
//    public ClinicalLabDetailAdapter(BaseActivity activity, ArrayList<LstLaboratorySpecimenResults> arrClinicalLabLists, OnItemClickListener onItemClickListener) {
//        this.activity = activity;
//        this.arrClinicalLabLists = arrClinicalLabLists;
//        this.onItemClickListener = onItemClickListener;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = null;
//        itemView = LayoutInflater.from(activity)
//                .inflate(R.layout.item_clinical_lab_detail, parent, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//
//        final LstLaboratorySpecimenResults model = arrClinicalLabLists.get(holder.getAdapterPosition());
//
//        holder.txtName.setText(model.getReportName() +" "+ model.getResult() +" "+ model.getUnit());
//        holder.txtRange.setText("Range " + model.getNormalRangeFormatted() + " Normal");
////        holder.txtComments.setText(model.getComments());
//        setListener(holder, model);
//    }
//
//    private void setListener(final ClinicalLabDetailAdapter.ViewHolder holder, final LstLaboratorySpecimenResults neurophysiology) {
//        holder.cardView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onItemClickListener.onItemClick(holder.getAdapterPosition(), neurophysiology);
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return arrClinicalLabLists.size();
//    }
//
//    static class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.txtName)
//        AnyTextView txtName;
//        @BindView(R.id.txtRange)
//        AnyTextView txtRange;
////        @BindView(R.id.txtComments)
////        AnyTextView txtComments;
//        @BindView(R.id.cardView2)
//        CardView cardView2;
//
//        ViewHolder(View view) {
//            super(view);
//            ButterKnife.bind(this, view);
//        }
//    }
}
