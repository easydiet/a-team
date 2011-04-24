package at.easydiet.util;

import java.util.Collection;

public abstract class CollectionCache<T>
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CollectionCache.class);
    
    protected abstract Collection<T> getOriginal();
    protected abstract void refreshCache();
    
    private int _cacheState = -1;
    private Collection<T> _cache;
    protected void setCache(Collection<T> newCache)
    {
        _cache = newCache;
        _cacheState = getOriginal().hashCode();
    }
    
    public Collection<T> getCache()
    {
        if(_cacheState != getOriginal().hashCode())
        {
            refreshCache();
        }
        return _cache;
    }
}
