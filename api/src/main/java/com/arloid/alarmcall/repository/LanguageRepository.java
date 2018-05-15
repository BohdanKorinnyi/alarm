package com.arloid.alarmcall.repository;

import com.arloid.alarmcall.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Integer> {
  Language findByCode(String code);

  Language findByCodeOrName(String code, String name);
}
