package ru.smeleyka.bgrebooter.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;

public class HostgroupFragment extends Fragment {

    private String TAG = "HostgroupFragment.class";
    private ArrayList<HostgroupGetResponse.Hostgroup> hostgroupList;

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
        initRecyclerView();

        return view;
    }

    private void initRecyclerView() {

        mLinearLayoutManager = new LinearLayoutManager(mContext);
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(hostgroupList);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public void addItemToRecyclerView(HostgroupGetResponse.Hostgroup hostgroup) {
        myRecyclerViewAdapter.setItemByOne(hostgroup);
    }

    public void inflateView(){
        getActivity().getLayoutInflater().inflate(R.layout.recycler_view_item_two_line,((ViewGroup)getView().getParent()),true);
//        TextView groupName = getView().findViewById(R.id.groupe_name);
//        groupName.setText(hostgroup.getName());
    }

    public void setHostgroupList(ArrayList<HostgroupGetResponse.Hostgroup> hostgroupList) {
        this.hostgroupList = hostgroupList;
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private String TAG = "MyRecyclerViewAdapter.class";

        List<HostgroupGetResponse.Hostgroup> hostgroupList;

        public MyRecyclerViewAdapter() {
            this.hostgroupList = new ArrayList<>();
        }

        public MyRecyclerViewAdapter(ArrayList<HostgroupGetResponse.Hostgroup> hostgroupList) {
            this.hostgroupList = hostgroupList;
            Log.d(TAG,"hostgroupList count= "+hostgroupList.size());
        }

        public void setItems(Collection<HostgroupGetResponse.Hostgroup> hostgroups) {
            hostgroupList.addAll(hostgroups);
            notifyDataSetChanged();
        }

        public void setItemByOne(HostgroupGetResponse.Hostgroup hostgroup) {
            hostgroupList.add(hostgroup);
            notifyDataSetChanged();
            Log.d(TAG,"hostgroupList count= "+hostgroupList.size());
        }

        public void clearItems() {
            hostgroupList.clear();
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.recycler_view_item_two_line, parent, false);

            Log.d(TAG, "Class of view: " + view.getClass().toString());
            Log.d(TAG, "LayoutParams of view is null: " + (view.getLayoutParams() == null));

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
private String TAG = "MyViewHolder.class";

        TextView groupeNameTextView;
        TextView groupeIdTextView;
        TextView groupeHostsCountTextView;
        TextView groupeImgTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            Log.d(TAG, "Class of view: " + itemView.getClass().toString());
            Log.d(TAG, "LayoutParams of view is null: " + (itemView.getLayoutParams() == null));
            groupeNameTextView = itemView.findViewById(R.id.recycler_item_text_first);
            groupeIdTextView = itemView.findViewById(R.id.recycler_item_text_groupe_id);
            groupeHostsCountTextView = itemView.findViewById(R.id.recycler_item_text_host_count);
            groupeImgTextView = itemView.findViewById(R.id.recycler_item_image);
        }

        public void bind(HostgroupGetResponse.Hostgroup hostgroup) {

            groupeImgTextView.setText(""+hostgroup.getName().charAt(0));
            groupeNameTextView.setText(""+hostgroup.getName());
            groupeIdTextView.setText("id: "+hostgroup.getGroupid());
            groupeHostsCountTextView.setText("hosts: "+hostgroup.getHosts().length);

        }
    }


}





