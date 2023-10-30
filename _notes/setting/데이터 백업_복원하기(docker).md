---
title: 데이터 백업/복원하기(docker)
updated: 2023-10-19 08:10:23Z
created: 2023-10-19 08:00:49Z
latitude: 37.44491680
longitude: 127.13886840
altitude: 0.0000
---

준비물 : 도커 postgresql


# 세팅하기

- 아래 두 파일을 받고 컨테이너 아이디를 고쳐줌


[backup.sh](../_resources/backup.sh)
[restore.sh](../_resources/restore.sh)


저는 도커 하나에 서버랑 같이 돌리는데, 쓰고있다면 단독이어도 상관은 없습니다

![KakaoTalk_Image_2023-10-19-16-48-39.jpeg](../_resources/KakaoTalk_Image_2023-10-19-16-48-39.jpeg)


![KakaoTalk_Image_2023-10-19-16-48-43.jpeg](../_resources/KakaoTalk_Image_2023-10-19-16-48-43.jpeg)

![KakaoTalk_Image_2023-10-19-16-48-44.jpeg](../_resources/KakaoTalk_Image_2023-10-19-16-48-44.jpeg)


터미널에서 권한설정해주기

```
chmod +x ./backup.sh
chmod +x ./restore.sh
```




# 백업하기

```
./backup.sh
```

출력파일명 : prod_db_dump_현재날짜시간.bak





# 복원하기

```
./restore.sh 복원파일명
```


![스크린샷 2023-10-19 오후 4.52.49.png](../_resources/스크린샷%202023-10-19%20오후%204.52.49-1.png)

삽입이나 codereviewgpt관련 에러만 없으면 문제 없음
Database restored and permissions granted successfully! 이전에 특별한 에러가 있으면 문제있음..


# 주의사항
* 아이디  맞는지 확인필요합니다
* 복원할경우 데이터베이스 codereviewgpt 다 밀어버리게 되니 혹시 다른 용도로 쓴다면 주의하세요

