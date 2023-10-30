---
title: '[postgresql] serial'
updated: 2023-10-17 01:35:50Z
created: 2023-10-17 01:30:42Z
latitude: 37.44491680
longitude: 127.13886840
altitude: 0.0000
---


데이터 유형 `smallserial`및 `serial`는 `bigserial`==**실제 유형이 아니지만 단지 고유 식별자 열을 생성하기 위한 표기상의 편의일 뿐**==입니다( `AUTO_INCREMENT`일부 다른 데이터베이스에서 지원하는 속성과 유사). 
```sql
CREATE TABLE tablename (
    colname SERIAL
);
```

이는 다음을 지정하는 것과 같습니다.

```
CREATE SEQUENCE `tablename`_ `colname`_seq AS 정수; CREATE TABLE `tablename`( `colname`정수 NOT NULL DEFAULT nextval(' `tablename`​​_ `colname`_seq')  ); ALTER SEQUENCE `tablename`_ `colname`_seq 소유 BY `tablename`. `colname`; 
```





따라서 우리는 정수 열을 생성하고 해당 기본값이 시퀀스 생성기에서 할당되도록 배열했습니다. `NOT NULL`null 값을 삽입할 수 없도록 제약 조건이 적용됩니다 . `UNIQUE`(대부분의 경우 실수로 중복된 값이 삽입되는 것을 방지하기 위해 또는 제약 조건을 첨부하려고 할 수도 `PRIMARY KEY`있지만 자동으로 수행되지는 않습니다.) 마지막으로 시퀀스는 열에 " 소유 " 로 표시되어 삭제됩니다. 열이나 테이블이 삭제된 경우 열에 시퀀스의 다음 값을 삽입하려면 `serial`해당 `serial`열에 기본값이 할당되어야 함을 지정합니다. 이는 명령문의 열 목록에서 해당 열을 제외하거나 키워드를 `INSERT`사용하여 수행할 수 있습니다.`DEFAULT`

유형 이름 `serial`과 `serial4`동일합니다. 둘 다 `integer`열을 생성합니다. 유형 이름은 `bigserial`열 을 `serial8`생성한다는 점을 제외하면 동일한 방식으로 작동합니다 `bigint`. 테이블 수명 동안 2 <sup>31개</sup>`bigserial` 이상의 식별자를 사용할 것으로 예상되는 경우 사용해야 합니다 . 유형 이름은 열 을 생성한다는 점을 제외하면 동일한 방식으로 작동합니다 .`smallserial``serial2``smallint`

열에 대해 생성된 시퀀스는 `serial`소유 열이 삭제되면 자동으로 삭제됩니다. 열을 삭제하지 않고 시퀀스를 삭제할 수 있지만 이렇게 하면 열 기본 표현식이 강제로 제거됩니다.


---



테스트 예시




```
CREATE TABLE user_account_temp (
                                   id serial PRIMARY KEY,
                                   email varchar(255) UNIQUE,
                                   name varchar(255),
                                   team varchar(255),
                                   memo varchar(255),
                                   last_worked_in_at timestamp(6),
                                   created_at timestamp,
                                   created_by integer,
                                   modified_at timestamp,
                                   modified_by integer
);
```





-- 시퀀스 생성 확인

<img src="{{ site.baseurl }}/assets/스크린샷%202023-10-17%20오전%209.39.14.png"/>





-- 시퀀스 단독으로 삭제 불가


<img src="{{ site.baseurl }}/assets/스크린샷%202023-10-17%20오전%209.40.44.png"/>







-- 테이블 삭제 시 함께 삭제됨



<img src="{{ site.baseurl }}/assets/스크린샷%202023-10-17%20오전%209.41.28.png"/>

