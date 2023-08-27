package io.collective.UtilityLib;
import java.time.Instant;;

public class CacheValue<V> {
    private V value;
    private Instant expirationTime;

    public CacheValue(V value, Instant expirationTime){
        this.value = value;
        this.expirationTime = expirationTime;
    }

    

    public V get_Value(){
        return value;
    }

    public boolean isExpired(Instant currentTime){
        long curr_time = currentTime.toEpochMilli();
        long expire_time = this.expirationTime.toEpochMilli();
        return curr_time >= expire_time;
    }

    public boolean isNotExpired(Instant currentTime){
        return !isExpired(currentTime);
    }


}
