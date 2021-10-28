package com.example.passwordgenerator.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.passwordgenerator.MainActivity2;
import com.example.passwordgenerator.Password;
import com.example.passwordgenerator.R;
import com.example.passwordgenerator.UpdateDeleteData;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private Context context;
    private List<Password> pd;

    public RecyclerViewAdapter(Context context, List<Password> pd) {
        this.context = context;
        this.pd = pd;
    }

    // Where to get the single card from
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    // What do after we get the viewholder objects
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Password password = pd.get(position);

        holder.nameOfPass.setText("Name : "+password.getName());
        holder.originalPass.setText("Password : "+password.getPass());
    }

    // How many items
    @Override
    public int getItemCount() {
        return pd.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nameOfPass;
        public TextView originalPass;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            nameOfPass = itemView.findViewById(R.id.nameOfPass);
            originalPass = itemView.findViewById(R.id.originalPass);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAdapterPosition();
            Password password = pd.get(position);
            String name = password.getName();
            String pass = password.getPass();

            Intent intent = new Intent(context, UpdateDeleteData.class);
            intent.putExtra("message_key3",name);
            intent.putExtra("message_key4",pass);
            context.startActivity(intent);
        }
    }
}
