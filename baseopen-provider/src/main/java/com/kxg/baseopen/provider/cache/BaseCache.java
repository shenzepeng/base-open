package com.kxg.baseopen.provider.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 要写注释呀
 */
@Slf4j
public abstract class BaseCache<K, V>  {
    private static final long DEFAULT_CACHE_SIZE = 1024L;
    private LoadingCache<K, Optional<V>> cache;

    public BaseCache() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(DEFAULT_CACHE_SIZE)
                .build(new CacheLoader<K, Optional<V>>() {
                    @Override
                    public Optional<V> load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
    }

    public BaseCache(long duration, TimeUnit timeUtil) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(DEFAULT_CACHE_SIZE)
                .expireAfterWrite(duration, timeUtil)
                .build(new CacheLoader<K, Optional<V>>() {
                    @Override
                    public Optional<V> load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
    }

    public BaseCache(long maxSize) {
        cache = CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .build(new CacheLoader<K, Optional<V>>() {
                    @Override
                    public Optional<V> load(K k) throws Exception
                    {
                        return loadData(k);
                    }
                });
    }

    protected abstract Optional<V> loadData(K k) throws Exception;

    public V getCache(K k) {
        try {
            Optional<V> op = cache.getUnchecked(k);
            if (!op.isPresent()) {
                log.warn("the key:{} not exists", k);
                return null;
            }
            return op.get();
        } catch (Exception e) {
            throw new RuntimeException("internal cache fetch error", e);
        }
    }

    public void refresh(K k) {
        cache.refresh(k);
    }

    public void put(K k, V v) {
        cache.put(k, Optional.ofNullable(v));
    }

}
