package in.co.madguy.springbootpoc.cache;

import java.util.List;

public interface Cache<T> {
    List<T> get();
    T get(String id);
    void add(T user);
    void remove(T user);
}
