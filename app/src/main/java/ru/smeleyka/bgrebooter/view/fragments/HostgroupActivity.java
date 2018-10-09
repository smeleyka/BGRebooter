package ru.smeleyka.bgrebooter.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import ru.smeleyka.bgrebooter.App;
import ru.smeleyka.bgrebooter.R;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.data.DataManager;
import ru.smeleyka.bgrebooter.model.data.GsonHelper;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetRequest;
import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;

public class HostgroupActivity extends AppCompatActivity implements HostgroupView {

    String TAG = "HostgroupActivity";

    List<HostgroupGetResponse.Hostgroup> hostgroup;


//    @BindView(R.id.main_activity_layout)
//    RecyclerView recyclerView;

    @Inject
    protected DataManager dataManager;
    @Inject
    protected GsonHelper gsonHelper;
    @Inject
    protected ZabbixRequest zabbixRequest;

    Scheduler mainThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        App.getInstance().getAppComponent().inject(this);
        mainThread = AndroidSchedulers.mainThread();

    }

    public void getHostgroups() {
        String request = gsonHelper.toJson(new HostgroupGetRequest(dataManager.getAuthKey()));
        zabbixRequest
                .senRequestToZabbixServer(request)
                .observeOn(mainThread)
                .subscribe(
                        s -> {
                            Log.d(TAG, s);
                            HostgroupGetResponse hostgroupGetResponse = gsonHelper.fromJson(s, HostgroupGetResponse.class);
                            if (hostgroupGetResponse.getResult() != null) {

                            } else {
                                System.out.println((hostgroupGetResponse.getError().getData()));

                            }
                        },
                        throwable -> {
                            System.out.println(throwable.getMessage());
                        }
                );
}

    @Override
    public HostgroupGetResponse.Hostgroup getHostsGroup() {
        return null;
    }
}
