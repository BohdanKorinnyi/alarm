package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.repository.CallStatusRepository;
import com.arloid.alarmcall.service.CallStatusService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CallStatusServiceImpl implements CallStatusService {
    private final CallStatusRepository callStatusRepository;

    private LoadingCache<String, CallStatus> statusCache;

    @PostConstruct
    public void init() {
        this.statusCache = CacheBuilder.newBuilder()
                .maximumSize(15)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build(new CacheLoader<String, CallStatus>() {
                    public CallStatus load(String key) {
                        return callStatusRepository.findByName(key);
                    }
                });
    }


    @Override
    public CallStatus findByName(String name) {
        return statusCache.getUnchecked(name);
    }
}
