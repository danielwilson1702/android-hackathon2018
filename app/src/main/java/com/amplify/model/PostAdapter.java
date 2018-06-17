package com.amplify.model;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.amplify.R;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    List<Post> mData;
    private PostClickListener mListener;
    private Context mContext;

    public PostAdapter(Context context, List<Post> data, PostClickListener listener) {
        mContext = context;
        mListener = listener;
        mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
            return new MyViewHolder(view, mListener);
        }
        else if(viewType == TYPE_HEADER){
            //This is a header view
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_header, parent, false);
            return new HeaderViewHolder(view, mListener);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private Post getItem(int position) {
        return mData.get(position - 1);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder) {
            Post current = getItem(position);
            MyViewHolder myVh = (MyViewHolder) holder;
            if (current != null) {
                if (myVh.name != null)
                    myVh.name.setText(String.format("by %s", current.getUsername()));
                if (myVh.time != null)
                    myVh.time.setText(current.getDateString());
                if (myVh.message != null)
                    myVh.message.setText(current.getMessage());
                if (myVh.upvotes != null) {
                    if(TextUtils.isEmpty(current.getPrice())) {
                        myVh.upvotes.setText(String.valueOf(current.getUpvotes()));
                        myVh.upvotes.setTextColor(mContext.getColor(R.color.colorAccent));
                        myVh.upvotes.setBackgroundColor(mContext.getColor(android.R.color.white));
                        myVh.upvote.setVisibility(View.VISIBLE);
                        myVh.downvote.setVisibility(View.VISIBLE);
                    }
                    else{
                        myVh.upvotes.setText(String.valueOf(current.getPrice()));
                        myVh.upvotes.setTextColor(mContext.getColor(android.R.color.white));
                        myVh.upvotes.setBackgroundColor(mContext.getColor(R.color.green));
                        myVh.upvote.setVisibility(View.GONE);
                        myVh.downvote.setVisibility(View.GONE);
                    }
                }
            }
        }
        else{
            HeaderViewHolder myVh = (HeaderViewHolder) holder;

            if(myVh.selectDistance != null){
                String[] years = {"500m","1km","1.5km","2km"};
                ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(mContext, R.layout.spinner_item, years);
                langAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
                myVh.selectDistance.setAdapter(langAdapter);
            }
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name, message, time, upvotes;
        ImageButton upvote, downvote;
        View cardView;
        PostClickListener mListener;

        public MyViewHolder(View itemView, PostClickListener listener) {
            super(itemView);
            mListener = listener;
            name = (TextView) itemView.findViewById(R.id.item_post_user_name);
            message = itemView.findViewById(R.id.item_post_message);
            time = itemView.findViewById(R.id.item_post_time);
            upvotes = itemView.findViewById(R.id.item_post_upvote_count);

            upvote = (ImageButton) itemView.findViewById(R.id.item_post_upvote);
            upvote.setOnClickListener(this);

            downvote = (ImageButton) itemView.findViewById(R.id.item_post_downvote);
            downvote.setOnClickListener(this);

            cardView = itemView.findViewById(R.id.item_post_cardview);
            if (cardView != null) {
                cardView.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            Post clickedItem = null;
            if (getAdapterPosition() > -1)
                clickedItem = mData.get(getAdapterPosition());

            if(v instanceof CardView)
                mListener.onPostClick(v, clickedItem);
            else if(v.getId() == R.id.item_post_upvote) {
                v.setSelected(!v.isSelected());

                /*if(v.isSelected()){
                    long upvotes = clickedItem.getUpvotes() + 1;
                    clickedItem.setUpvotes(upvotes);
                    notifyDataSetChanged();
                }*/
                mListener.onUpvoteClick(v, clickedItem, v.isSelected());
            }
            else if(v.getId() == R.id.item_post_downvote){
                v.setSelected(!v.isSelected());

                /*if(v.isSelected()){
                    long downVotes = clickedItem.getUpvotes() - 1;
                    clickedItem.setUpvotes(downVotes);
                    notifyDataSetChanged();
                }*/
                mListener.onDownvoteClick(v, clickedItem, v.isSelected());
            }
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button createPost;
        Spinner selectDistance;
        EditText postEditText;

        public HeaderViewHolder(View itemView, PostClickListener listener) {
            super(itemView);
            mListener = listener;
            createPost = (Button) itemView.findViewById(R.id.item_post_header_post_button);
            createPost.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    mListener.onCreateClick(postEditText.getText().toString());
                }
            });
            selectDistance = itemView.findViewById(R.id.item_post_header_spinner);
            postEditText = itemView.findViewById(R.id.item_post_header_post_et);
        }

        @Override
        public void onClick(View v) {
            Post clickedItem = null;
            if (getAdapterPosition() > -1)
                clickedItem = mData.get(getAdapterPosition());

            if(v instanceof CardView)
                mListener.onPostClick(v, clickedItem);
            else if(v.getId() == R.id.item_post_upvote) {
                v.setSelected(!v.isSelected());

                if(v.isSelected()){
                    long upvotes = clickedItem.getUpvotes() + 1;
                    clickedItem.setUpvotes(upvotes);
                    notifyDataSetChanged();
                }
                mListener.onUpvoteClick(v, clickedItem, v.isSelected());
            }
            else if(v.getId() == R.id.item_post_downvote){
                v.setSelected(!v.isSelected());
                mListener.onDownvoteClick(v, clickedItem, v.isSelected());
                if(v.isSelected()){
                    long downVotes = clickedItem.getUpvotes() - 1;
                    clickedItem.setUpvotes(downVotes);
                    notifyDataSetChanged();
                }
            }
        }
    }
}