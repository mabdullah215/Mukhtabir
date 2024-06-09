package com.mobileapp.mukhtabir.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobileapp.mukhtabir.R;
import com.mobileapp.mukhtabir.model.Subject;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SubjectListAdapter extends RecyclerView.Adapter<SubjectListAdapter.ViewHolder>
{
    private List<Subject> dataList=new ArrayList<>();
    Context mContext;
    Picasso picasso;
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    SimpleDateFormat dateFormat=new SimpleDateFormat("EEEE, dd MMMM YYYY");


    public List<Subject> getDataList() {
        return dataList;
    }

    public SubjectListAdapter(Context context, List<Subject> eventList)
    {
        this.dataList.addAll(eventList);
        mContext=context;
        picasso=Picasso.get();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_offer, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount()
    {
        return dataList == null? 0: dataList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        final Subject item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public void updateList(List<Subject> items)
    {
        this.dataList.clear();
        this.dataList.addAll(items);
        notifyDataSetChanged();
    }

    public void setDataList(List<Subject>items)
    {
        this.dataList.clear();
        this.dataList.addAll(items);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView mImage;
        private TextView mTitle;
        private TextView mDesc;
        private TextView mValidity;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mImage=itemView.findViewById(R.id.img_source);
            mTitle=itemView.findViewById(R.id.tv_title);
            mValidity=itemView.findViewById(R.id.tv_valid);
            mDesc=itemView.findViewById(R.id.tv_details);
            itemView.setOnClickListener(this);
        }

        public void setDetails(final Subject item,int pos)
        {
            mTitle.setText(item.getTitle());
            Picasso.get().load(item.getImage()).fit().into(mImage);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

}


