package ru.smeleyka.bgrebooter.model.api;

public class Repo
{
    protected ApiService getApi()
    {
        return ApiHolder.getInstance().getApi();
    }
}
