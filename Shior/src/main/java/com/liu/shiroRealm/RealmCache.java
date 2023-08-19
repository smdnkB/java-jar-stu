package com.liu.shiroRealm;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;

public class RealmCache<K,V> implements Cache<K,V> {
    @Override
    public Object get(Object o) throws CacheException {
        System.out.println("=====RealmCache get=====");
        return null;
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        System.out.println("=====RealmCache put=====");
        return null;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        System.out.println("=====RealmCache remove=====");
        return null;
    }

    @Override
    public void clear() throws CacheException {
        System.out.println("=====RealmCache clear=====");
    }

    @Override
    public int size() {
        System.out.println("=====RealmCache size=====");
        return 0;
    }

    @Override
    public Set keys() {
        System.out.println("=====RealmCache keys=====");
        return null;
    }

    @Override
    public Collection values() {
        System.out.println("=====RealmCache values=====");
        return null;
    }
}
