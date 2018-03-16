package ru.smeleyka.bgrebooter.model.api;

abstract public class Repo
{
    protected ApiService getApi()
    {
        return ApiHolder.getInstance().getApi();
    }
}
