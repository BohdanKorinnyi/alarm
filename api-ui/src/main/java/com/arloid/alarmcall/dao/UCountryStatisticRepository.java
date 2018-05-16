package com.arloid.alarmcall.dao;

import com.arloid.alarmcall.entity.ui.CountryStatistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UCountryStatisticRepository extends Repository<CountryStatistic, Long> {
  @Query(
    nativeQuery = true,
    value =
        "SELECT count(*), country_codes.name AS country "
            + "FROM call "
            + " JOIN call_number ON call.call_number_id = call_number.id"
            + " JOIN country_codes ON call_number.number LIKE CONCAT('+', country_codes.phone_code, '%') "
            + "GROUP BY country_codes.name"
  )
  List<CountryStatistic> getCountryStatistic();
}
