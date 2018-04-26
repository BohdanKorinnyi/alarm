package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.CallStatus;
import com.arloid.alarmcall.repository.CallStatusRepository;
import com.arloid.alarmcall.service.CallStatusService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CallStatusServiceImpl implements CallStatusService {
  public static final String CREATED = "created";
  public static final String COMPLETED = "completed";
  private final CallStatusRepository callStatusRepository;

  private LoadingCache<String, CallStatus> statusCache;

  @PostConstruct
  public void init() {
    this.statusCache =
        CacheBuilder.newBuilder()
            .maximumSize(15)
            .build(
                new CacheLoader<String, CallStatus>() {
                  public CallStatus load(String key) {
                    return callStatusRepository.findByName(key);
                  }
                });
  }

  @Override
  public List<CallStatus> findAll() {
    return callStatusRepository.findAll();
  }

  @Override
  public CallStatus findByName(String name) {
    return statusCache.getUnchecked(name);
  }
}
