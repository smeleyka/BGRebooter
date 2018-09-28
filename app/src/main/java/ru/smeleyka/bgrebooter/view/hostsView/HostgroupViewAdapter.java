package ru.smeleyka.bgrebooter.view.hostsView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;

public class HostgroupViewAdapter extends RecyclerView.Adapter<HostgroupViewAdapter.ViewHolder> {
    private List<HostgroupGetResponse.Hostgroup> mHostGroups;

    public HostgroupViewAdapter(List<HostgroupGetResponse.Hostgroup> hostGroups) {
        mHostGroups = hostGroups;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View hostgroupView = inflater.inflate(R.layout.main_activity_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(hostgroupView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HostgroupGetResponse.Hostgroup hostgroup = mHostGroups.get(position);
        TextView hostgroupNameTextView = holder.hostGroup;
        TextView hostgroupIdTextView = holder.hostGroupId;
        hostgroupNameTextView.setText(hostgroup.getName());
        hostgroupIdTextView.setText(hostgroup.getGroupid());

    }

    @Override
    public int getItemCount() {
        return mHostGroups.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.host_group)
        TextView hostGroup;

        @BindView(R.id.host_group_id)
        TextView hostGroupId;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);


        }



    }
}
