package pl.emb.covidsupport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.CustomViewHolder> {

    // create a inner class
    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public CustomViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.textMessage);
        }
    }

    List<ResponseMessage> responseMessages;
    Context context;

    public MessageAdapter(List<ResponseMessage> responseMessages, Context context) {
        this.responseMessages = responseMessages;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {          // method to check, which layout we should show
        if(responseMessages.get(position).isUser()) {
            return R.layout.user_bubble;
        }
        return R.layout.bot_bubble;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.textView.setText(responseMessages.get(position).getTextMessage());
    }

    @Override
    public int getItemCount() {
        return responseMessages.size();
    }
}
