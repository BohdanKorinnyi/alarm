package com.arloid.alarmcall.service;

import com.arloid.alarmcall.entity.ui.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UCallService {
  Page<Call> find(Pageable pageable);
}
