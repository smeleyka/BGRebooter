package ru.smeleyka.bgrebooter.view.hostsView;

import java.lang.reflect.Type;

import ru.smeleyka.bgrebooter.model.entity.HostgroupGetResponse;
import ru.smeleyka.bgrebooter.model.entity.ZabbixApiObject;

public  interface HostgroupView {
    public HostgroupGetResponse.Hostgroup getHostsGroup();

    }
