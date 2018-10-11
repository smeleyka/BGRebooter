package ru.smeleyka.bgrebooter.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;

public class HostgroupFragment extends Fragment {

    Context mContext;

    MyRecyclerViewAdapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager mLinearLayoutManager;

    RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = inflater.getContext();
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        //initRecyclerView();

        return view;
    }

    private void initRecyclerView() {

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(null);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public void addItemToRecyclerView(HostgroupGetResponse.Hostgroup hostgroup) {
        myRecyclerViewAdapter.setItemByOne(hostgroup);
    }

    public void inflateView(){
        getActivity().getLayoutInflater().inflate(R.layout.recycler_view_item,((ViewGroup)getView().getParent()),true);
//        TextView groupName = getView().findViewById(R.id.groupe_name);
//        groupName.setText(hostgroup.getName());
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<HostgroupGetResponse.Hostgroup> hostgroupList;

        public MyRecyclerViewAdapter(List<HostgroupGetResponse.Hostgroup> hostgroupList) {
            this.hostgroupList = hostgroupList;
        }

        public void setItems(Collection<HostgroupGetResponse.Hostgroup> hostgroups) {
            hostgroupList.addAll(hostgroups);
            notifyDataSetChanged();
        }

        public void setItemByOne(HostgroupGetResponse.Hostgroup hostgroup) {
            hostgroupList.add(hostgroup);
            notifyDataSetChanged();
        }

        public void clearItems() {
            hostgroupList.clear();
            notifyDataSetChanged();
        }




        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycler_view_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(hostgroupList.get(position));
        }



        @Override
        public int getItemCount() {
            return hostgroupList.size();
        }


    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView groupeNameTextView;
        TextView groupeIdTextView;
        TextView groupeHostsCountTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            groupeNameTextView = itemView.findViewById(R.id.groupe_name);
            //groupeIdTextView = itemView.findViewById(R.id.groupe_id);
            //groupeHostsCountTextView = itemView.findViewById(R.id.groupe_host_count);

        }

        public void bind(HostgroupGetResponse.Hostgroup hostgroup) {
            groupeNameTextView.setText(hostgroup.getName());
            //groupeIdTextView.setText(hostgroup.getGroupid());
            //groupeHostsCountTextView.setText(hostgroup.getHosts().length);

        }
    }


}





