package in.co.madguy.springbootpoc.cache;

import com.google.common.cache.CacheBuilder;
import in.co.madguy.springbootpoc.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserCache implements Cache<User> {
    private com.google.common.cache.Cache<String, User> userCache;

    @PostConstruct
    public void init() {
        initUserCache();
    }

    @Override
    public List<User> get() {
        return userCache.asMap()
            .values()
            .stream()
            .collect(Collectors.toList());
    }

    @Override
    public User get(String id) {
        return userCache.getIfPresent(id);
    }

    @Override
    public void add(User user) {
        userCache.put(user.getUserId(), user);
    }

    @Override
    public void remove(User user) {
        userCache.invalidate(user.getUserId());
    }

    private void initUserCache() {
        this.userCache = CacheBuilder.newBuilder()
            .maximumSize(2500)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();
    }
}
