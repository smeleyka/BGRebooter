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

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.HostsOfGroupGetResponse;
import ru.smeleyka.bgrebooter.view.MainActivityView;

public class HostsFragment extends Fragment {

    private String TAG = "HostsFragment.class";

    Context mContext;

    MainActivityView mainActivityView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    RecyclerView.LayoutManager mLinearLayoutManager;
    RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainActivityView = (MainActivityView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement MainActivityView.class");
        }
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
        if (myRecyclerViewAdapter == null && mLinearLayoutManager == null) {
            mLinearLayoutManager = new LinearLayoutManager(mContext);
            myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        }
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    public void subscribeToHostsOfGroup(Observable<HostsOfGroupGetResponse.Host> hostObservable) {
        myRecyclerViewAdapter.setItemSubscription(hostObservable);
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {
        private String TAG = "MyRecyclerViewAdapter.class";

        List<HostsOfGroupGetResponse.Host> hostList;

        public MyRecyclerViewAdapter() {
            this.hostList = new ArrayList<>();
        }


        public void setItemSubscription(Observable<HostsOfGroupGetResponse.Host> hostObservable) {
            hostObservable.observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            s -> {
                                hostList.add(s);
                                notifyDataSetChanged();
                            },
                            throwable -> {
                                Log.d(TAG, throwable.getMessage());
                                mainActivityView.showError(throwable.getMessage());
                                mainActivityView.hideLoading();
                            }
                    );
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext)
                    .inflate(R.layout.recycler_view_item_two_line, parent, false);
            return new MyViewHolder(view,myRecyclerViewAdapter);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.bind(hostList.get(position));
        }

        @Override
        public int getItemCount() {
            return hostList.size();
        }

        @Override
        public void onClick(View v) {
            MyViewHolder viewHolder = (MyViewHolder) v.getTag();
            int position = viewHolder.getLayoutPosition();
            mainActivityView.showError("Clicked " + hostList.get(position).getName());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private String TAG = "MyViewHolder.class";
        //private MyRecyclerViewAdapter myRecyclerViewAdapter;

        TextView groupeNameTextView;
        TextView secondText;
        TextView groupeIdTextView;
        TextView groupeHostsCountTextView;
        TextView groupeImgTextView;

        public MyViewHolder(View itemView, MyRecyclerViewAdapter myRecyclerViewAdapter) {
            super(itemView);

            //this.myRecyclerViewAdapter=myRecyclerViewAdapter;

            Log.d(TAG, "Class of view: " + itemView.getClass().toString());
            Log.d(TAG, "LayoutParams of view is null: " + (itemView.getLayoutParams() == null));
            groupeNameTextView = itemView.findViewById(R.id.recycler_item_text_first);
            groupeIdTextView = itemView.findViewById(R.id.recycler_item_text_groupe_id);
            secondText = itemView.findViewById(R.id.recycler_item_text_second);
            groupeHostsCountTextView = itemView.findViewById(R.id.recycler_item_text_host_count);
            groupeImgTextView = itemView.findViewById(R.id.recycler_item_image);
            itemView.setTag(this);
            itemView.setOnClickListener(myRecyclerViewAdapter);

        }

        public void bind(HostsOfGroupGetResponse.Host host) {

            groupeImgTextView.setText("" + host.getName().charAt(0));
            groupeNameTextView.setText("" + host.getName());
            secondText.setText(""+ host.getHost());
            groupeIdTextView.setText("active: " + host.getStatus());

        }
    }


}





