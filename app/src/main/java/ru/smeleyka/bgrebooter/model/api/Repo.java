package ru.smeleyka.bgrebooter.model.api;

abstract public class Repo
{
    protected ApiService getApi()
    {
        return ApiHolder.getInstance().getApi();
    }
    protected ApiService getTestApi()
    {
        return ApiHolder.getInstance().getTestApi();
    }
}
