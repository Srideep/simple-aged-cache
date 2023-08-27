
package io.collective;

import java.time.Clock;
import java.time.Instant;
import java.time.Duration;

import io.collective.UtilityLib.*;

public class SimpleAgedCache {
    private final Clock clk;
    private final HashMap<Object, CacheValue> cacheMap;


    public SimpleAgedCache(Clock clock) {
        this.clk = clock;
        this.cacheMap = new HashMap<>();
    }

    public SimpleAgedCache() {
        this(Clock.system(Clock.systemDefaultZone().getZone()));
    }

    

    public void put(Object key, Object value, int retentionInMillis) {
        this.cache_reset();
        Duration duration = Duration.ofMillis(retentionInMillis);
        Instant expiredTime = clk.instant().plus(duration);
        CacheValue cacheItem = new CacheValue<>(value, expiredTime);

        cacheMap.put(key, cacheItem);
    }

    public boolean isEmpty() {
        return cacheMap.isEmpty();
    }

    public int size() {
        this.cache_reset();
        return cacheMap.size();
    }

    public Object get(Object key) {
        this.cache_reset();
        CacheValue cache_Item = cacheMap.get(key);
        
        if(cache_Item != null && cache_Item.isNotExpired(this.clk.instant())){
            return cache_Item.get_Value();
        }
        cacheMap.remove(key);
        return null;
    }

    public void cache_reset() {
        Instant currentTime = clk.instant();
        for (Object key : cacheMap.keySet()) {
            CacheValue cacheItem = cacheMap.get(key);
            if (cacheItem.isExpired(currentTime)) {
                cacheMap.remove(key);
            }
        }
    }

    
}

