package com.arloid.alarmcall.controller;

import com.arloid.alarmcall.dto.CallStatusDto;
import com.arloid.alarmcall.entity.AlarmCall;
import com.arloid.alarmcall.service.AlarmCallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@AllArgsConstructor
@Api(tags = "Calls")
@RequestMapping("calls")
public class AlarmCallController {
  private static final File FILE = new File("/home/mp3/pixel.mp3");
  private final AlarmCallService alarmCallService;

  @GetMapping("statistics")
  @ApiOperation(value = "Returns statistic of calls by status id")
  public ResponseEntity getCallStatisticByStatus(CallStatusDto statusDto) {
    return ok(alarmCallService.getStatisticByStatus(statusDto.convert()));
  }

  @GetMapping
  @ApiOperation(value = "Returns all calls")
  public ResponseEntity<Page<AlarmCall>> findAll(Pageable pageable) {
    return ok(alarmCallService.findAll(pageable));
  }

  @GetMapping("numbers/{numberId}")
  @ApiOperation(value = "Returns all calls by the number id")
  public ResponseEntity<Page<AlarmCall>> findByCallNumberId(
      @PathVariable long numberId, Pageable pageable) {
    return ok(alarmCallService.findByCallNumberId(pageable, numberId));
  }

  @PostMapping("clients/{clientId}")
  @ApiOperation(value = "Makes a call by the client id")
  public ResponseEntity makeToClient(@PathVariable long clientId) {
    log.info("Calling to client {}", clientId);
    alarmCallService.makeByClientId(clientId);
    return ok().build();
  }

  @SneakyThrows
  @GetMapping("closes/{clientId}")
  @ApiOperation(
    value = "Returns an empty MP3 file and tracks the end of the call, Twilio service uses it"
  )
  public ResponseEntity completeCallByClientId(@PathVariable long clientId) {
    log.info("Client {} has listened to call completely", clientId);
    alarmCallService.complete(clientId);
    FileSystemResource systemResource = new FileSystemResource(FILE);
    byte[] content = new byte[(int) systemResource.contentLength()];
    IOUtils.readFully(systemResource.getInputStream(), content);
    return ResponseEntity.ok()
        .contentLength(FILE.length())
        .contentType(MediaType.parseMediaType("audio/mp3"))
        .body(content);
  }
}
