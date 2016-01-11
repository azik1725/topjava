package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);


    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        repository.remove(id);
        return true;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        return repository.put(user.getId(),user);
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        List<User> userList=new ArrayList<>(repository.values());
        Collections.sort(userList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        return userList;
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        User result=null;
        for (Map.Entry<Integer,User> integerUserEntry :repository.entrySet()) {
            if (integerUserEntry.getValue().getEmail().equals(email))
                result=integerUserEntry.getValue();
        }
        return result;
    }
}
