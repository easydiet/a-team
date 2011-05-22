package at.easydiet.util;

import org.apache.pivot.collections.Sequence;

public abstract class CollectionCache<T>
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CollectionCache.class);
    
    protected abstract Sequence<T> getOriginal();
    protected abstract void refreshCache();
    
    private int _cacheState = -1;
    private Sequence<T> _cache;
    protected void setCache(Sequence<T> newCache)
    {
        _cache = newCache;
        _cacheState = getOriginal().hashCode();
    }
    
    public Sequence<T> getCache()
    {
        if(_cacheState != getOriginal().hashCode())
        {
            refreshCache();
        }
        return _cache;
    }
}
