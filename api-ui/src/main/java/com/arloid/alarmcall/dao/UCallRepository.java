package com.arloid.alarmcall.dao;

import com.arloid.alarmcall.entity.ui.Call;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface UCallRepository extends Repository<Call, Long> {
  @Query(
    nativeQuery = true,
    value =
        "SELECT public.call.id, public.call_number.number,"
            + "  public.call.creation,"
            + "  public.call.updated,"
            + "  public.call_status.value,"
            + "  public.call.duration,"
            + "  public.call.cost,"
            + "  public.languages.name,"
            + "  public.call.parent_id"
            + " FROM public.call "
            + "  JOIN public.call_status ON public.call.call_status_id = public.call_status.id"
            + "  JOIN public.alarm ON public.call.alarm_id = public.alarm.id"
            + "  JOIN public.languages ON public.alarm.language_id = public.languages.id"
            + "  JOIN public.call_number ON public.call.call_number_id = public.call_number.id ",
    countQuery =
        "SELECT count(public.call.id)"
            + " FROM public.call "
            + "  JOIN public.call_status ON public.call.call_status_id = public.call_status.id"
            + "  JOIN public.alarm ON public.call.alarm_id = public.alarm.id"
            + "  JOIN public.languages ON public.alarm.language_id = public.languages.id"
            + "  JOIN public.call_number ON public.call.call_number_id = public.call_number.id "
  )
  Page<Call> find(Pageable pageable);
}
