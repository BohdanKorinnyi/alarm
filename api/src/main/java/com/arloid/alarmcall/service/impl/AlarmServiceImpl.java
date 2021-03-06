package com.arloid.alarmcall.service.impl;

import com.arloid.alarmcall.entity.Alarm;
import com.arloid.alarmcall.entity.Client;
import com.arloid.alarmcall.entity.Language;
import com.arloid.alarmcall.exception.SaveExistingEntityException;
import com.arloid.alarmcall.repository.AlarmRepository;
import com.arloid.alarmcall.service.AlarmService;
import com.arloid.alarmcall.service.S3Service;
import com.arloid.alarmcall.service.TwilioService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@AllArgsConstructor
public class AlarmServiceImpl implements AlarmService {
  private final TwilioService twilioService;
  private final S3Service s3Service;
  private final AlarmRepository alarmRepository;

  @Override
  public Page<Alarm> find(Pageable pageable) {
    return alarmRepository.findAll(pageable);
  }

  @Override
  public Alarm save(Client client, Alarm.AlarmRecordType type, String data, Language language) {
    Alarm alarm = alarmRepository.findByClientId(client.getId());
    if (nonNull(alarm)) {
      throw new SaveExistingEntityException(
          "You can't create a new alarm for client "
              + client.getId()
              + ". Please, update existing alarm if you need.");
    }
    return alarmRepository.save(
        Alarm.builder()
            .type(type)
            .client(client)
            .addressRecord(data)
            .active(true)
            .language(language)
            .build());
  }

  @Override
  public String upload(MultipartFile multipartFile, String externalClientId) {
    return s3Service.upload(multipartFile);
  }

  @Override
  public Alarm findByClientId(long clientId) {
    log.info("Getting alarm by client id {}", clientId);
    return alarmRepository.findByClientId(clientId);
  }

  @Override
  public String getTwiMlByClient(long clientId) {
    Alarm alarm = alarmRepository.findByClientId(clientId);
    if (alarm.getType() == Alarm.AlarmRecordType.SPEECH) {
      return twilioService.generateSayTwiMl(
          alarm.getAddressRecord(), alarm.getLanguage(), clientId);
    }
    return twilioService.generatePlayTwiMl(alarm.getAddressRecord(), alarm.getLanguage(), clientId);
  }
}
