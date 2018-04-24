package com.arloid.alarmcall.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "languages")
public class Language {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String code;
  private String name;
  private String intro;
}
