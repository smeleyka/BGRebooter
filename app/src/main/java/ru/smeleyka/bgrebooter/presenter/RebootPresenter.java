package ru.smeleyka.bgrebooter.presenter;

import android.util.Log;

import com.google.gson.GsonBuilder;

import io.reactivex.Scheduler;
import ru.smeleyka.bgrebooter.model.api.ZabbixRequest;
import ru.smeleyka.bgrebooter.model.entity.LoginRequest;
import ru.smeleyka.bgrebooter.model.entity.LoginResponse;
import ru.smeleyka.bgrebooter.view.LoginView;
import ru.smeleyka.bgrebooter.view.RebootView;

/**
 * Created by smeleyka on 14.03.18.
 */

public class RebootPresenter {
    final static String TAG = "RebootPresenter.class";

    private Scheduler mainThread;
    private RebootView rebootView;

    public RebootPresenter(RebootView rebootView, Scheduler mainThread){
        this.rebootView=rebootView;
        this.mainThread = mainThread;
    }


}