package com.arloid.alarmcall.dao;

import com.arloid.alarmcall.entity.ui.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UCallRepository extends Repository<Call, Long> {
  @Query(
    nativeQuery = true,
    value =
        "SELECT c.id, cn.number, c.creation, c.updated, cs.value, c.duration, c.cost, l.name, c.parent_id, c.cost"
            + " FROM public.call AS c"
            + "  JOIN public.call_status AS cs ON c.call_status_id = cs.id"
            + "  JOIN public.alarm AS a ON c.alarm_id = a.id"
            + "  JOIN public.languages AS l ON a.language_id = l.id"
            + "  JOIN public.call_number AS cn ON c.call_number_id = cn.id ",
    countQuery =
        "SELECT count(c.id) FROM public.call AS c"
            + "  JOIN public.call_status AS cs ON c.call_status_id = cs.id"
            + "  JOIN public.alarm AS a ON c.alarm_id = a.id"
            + "  JOIN public.languages AS l ON a.language_id = l.id"
            + "  JOIN public.call_number AS cn ON c.call_number_id = cn.id "
  )
  Page<Call> find(Pageable pageable);

  @Query(
    nativeQuery = true,
    value =
        "SELECT c.id, cn.number, c.creation, c.updated, cs.value, c.duration, c.cost, l.name, c.parent_id, c.cost"
            + " FROM public.call AS c"
            + "  JOIN public.call_status AS cs ON c.call_status_id = cs.id"
            + "  JOIN public.alarm AS a ON c.alarm_id = a.id"
            + "  JOIN public.languages AS l ON a.language_id = l.id"
            + "  JOIN public.call_number AS cn ON c.call_number_id = cn.id "
            + " WHERE cn.number LIKE CONCAT('%', :number, '%')",
    countQuery =
        "SELECT count(c.id) FROM public.call AS c"
            + "  JOIN public.call_status AS cs ON c.call_status_id = cs.id"
            + "  JOIN public.alarm AS a ON c.alarm_id = a.id"
            + "  JOIN public.languages AS l ON a.language_id = l.id"
            + "  JOIN public.call_number AS cn ON c.call_number_id = cn.id "
            + " WHERE cn.number LIKE CONCAT('%', :number, '%')"
  )
  Page<Call> findByNumber(@Param("number") String number, Pageable pageable);
}
