package com.mobileapp.mukhtabir.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.mobileapp.mukhtabir.R;

public class RoleListAdapter extends RecyclerView.Adapter<RoleListAdapter.ViewHolder>
{
    Context mContext;
    private OnItemClickListener onItemClickListener;
    int selected=-1;
    int [] imgList={R.drawable.ic_student,R.drawable.img_trainer};
    String [] titleList={"طالب","مدرس"};

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public RoleListAdapter(Context context)
    {
        mContext=context;
    }

    public void setSelected(int selected) {
        this.selected = selected;
        notifyDataSetChanged();
    }

    public int getSelected() {
        return selected;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_role, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount()
    {
        return 2;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        String item=titleList[position];
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mTxt;
        private MaterialCardView cardBack;
        private ImageView imgSource;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mTxt=itemView.findViewById(R.id.chip_txt);
            imgSource=itemView.findViewById(R.id.img_source);
            cardBack=itemView.findViewById(R.id.card_background);
            itemView.setOnClickListener(this);
        }

        public void setDetails(final String item,int pos)
        {
            mTxt.setText(item);
            imgSource.setImageResource(imgList[pos]);
            if(pos==selected)
            {
                cardBack.setCardBackgroundColor(Color.WHITE);
                cardBack.setStrokeColor(Color.parseColor("#008FFE"));
            }
            else
            {
                cardBack.setCardBackgroundColor(Color.parseColor("#f6f6f6"));
                cardBack.setStrokeColor(Color.parseColor("#f6f6f6"));
            }
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}


