---
title: '[Springboot] LogBook'
updated: 2023-10-06 06:33:49Z
created: 2023-10-06 06:14:31Z
latitude: 37.44491680
longitude: 127.13886840
altitude: 0.0000
---






* Http 요청의 서버측 수신 / 응답 로그를 간단하게 보여주는 Java 라이브러리
* 별도 설정을 application.yml과 Java class로 관리하기 때문에 기존 코드를 건드릴 필요가 없는 장점이 있음
* 다양한 조건, 필터 등 설정 가능



* sample
```
2023-10-06T15:14:01.580+09:00 TRACE 27252 --- [0.0-4040-exec-1] org.zalando.logbook.Logbook              : Incoming Request: ab017151d398e599
Remote: 0:0:0:0:0:0:0:1
GET http://localhost:4040/api/v1/admin/admin-users?page=0&size=20 HTTP/1.1
accept: */*
accept-encoding: gzip, deflate, br
accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
connection: keep-alive
cookie: sessionId=g3TXY2ndV5JBIbmkwWvKMK; JSESSIONID.d8627061=node013q5if688qvfzxtzhk672mflo0.node0
host: localhost:4040
referer: http://localhost:4040/swagger-ui/index.html
sec-ch-ua: "Google Chrome";v="117", "Not;A=Brand";v="8", "Chromium";v="117"
sec-ch-ua-mobile: ?0
sec-ch-ua-platform: "macOS"
sec-fetch-dest: empty
sec-fetch-mode: cors
sec-fetch-site: same-origin
```

```
user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/117.0.0.0 Safari/537.36
2023-10-06T15:14:01.582+09:00 TRACE 27252 --- [0.0-4040-exec-1] org.zalando.logbook.Logbook              : Outgoing Response: ab017151d398e599
Duration: 25 ms
HTTP/1.1 403 Forbidden
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Expires: 0
Pragma: no-cache
X-Content-Type-Options: nosniff
X-Frame-Options: DENY
X-XSS-Protection: 0
```





프로젝트 적용 방법: 


build.gradle - dependencies
- 추가
 ```
implementation 'org.zalando:logbook-spring-boot-webflux-autoconfigure:3.4.0'
implementation 'org.zalando:logbook-spring-boot-starter:3.4.0'
```



application.yml
* 추가
```
logging:
  level:
    root: info
    sql: debug
#    kr.co.mz.codereviewgptadminserver: debug
    org:
      zalando:
        logbook: TRACE
```


* 추가
```
logbook:
  include: /api/**
  filter:
    enabled: ${LOGBOOK_FILTER_ENABLED:true}
  format:
    style: http
  obfuscate:
    write:
      category: http.wire-log
  write:
    chunk-size: ${LOGBOOK_CHUNK_SIZE:1000}
    max-body-size: ${LOGBOOK_WRITE_MAX_BODY_SIZE:100000}
    level: ${LOGBOOK_WRITE_LEVEL:INFO}
```




LogBookConfig.java
* 파일 추가
```
package kr.co.mz.codereviewgptadminserver.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;


@Configuration
class LogbookConfig {

    @Bean
    public Logbook logbook() {
        return Logbook.builder().build();
    }
}
```




참고 : https://medium.com/@sybrenbolandit/zalando-logbook-ee923c08e359

https://github.com/zalando/logbook
