package com.john.payment.simple;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public record RecordTest(
    String r1,
    String r2
) {
    public void logTest() {
      log.info("r1: {}, r2: {}", r1, r2);
    }
}
