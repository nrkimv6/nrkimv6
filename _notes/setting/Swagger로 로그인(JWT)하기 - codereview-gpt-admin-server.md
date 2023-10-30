---
title: Swagger로 로그인(JWT)하기 - codereview-gpt-admin-server
updated: 2023-09-26 06:00:11Z
created: 2023-09-25 09:36:43Z
latitude: 37.44491680
longitude: 127.13886840
altitude: 0.0000
---

testcase<br>
server : [http://172.90.4.13:8080/swagger-ui/index.html](http://172.90.4.13:8080/swagger-ui/index.html)<br>
email : string@str.ss<br>
password : string<br><br>


* 참고 : 현재 기준(9/25) 계정생성은....막아두었습니다<br>
<br><br>

1. sign-in<br>
	![1580d2e1f321eb9100e5dcfbd68a4c02.png](../_resources/1580d2e1f321eb9100e5dcfbd68a4c02.png) 
2. 결과에서 토큰 복사<br>
	![a803a4268c34cabf11e3a3533ec06d93.png](../_resources/a803a4268c34cabf11e3a3533ec06d93.png)
3. 복사한 토큰을 Authrization에 넣는다.<br>
	![76d679b4d1455ea04d744ab3fecf360e.png](../_resources/76d679b4d1455ea04d744ab3fecf360e.png)
	넣을 때 앞에 bearer 추가하기<br>
	![2357372b899e866831ebd30ac156621f.png](../_resources/2357372b899e866831ebd30ac156621f.png)
4. Authorize 후 임의의 요청이 정상 작동함<br>
	![306f6a976edf2bce92b140cc1f5cdf7c.png](../_resources/306f6a976edf2bce92b140cc1f5cdf7c.png)
	

참고) Authorization : bearer + 값이 들어와야 정상 로그인 작동 함.<br><br>
만약 없을경우 뭔가 백엔드 구현에 문제가 있을 수 있음.
![51746630023dc856f9816f3b5b6b23dc.png](../_resources/51746630023dc856f9816f3b5b6b23dc.png)

API
[http://172.90.4.13:8080/swagger-ui/index.html?urls.primaryName=codereview-gpt%20join%26login#/auth](http://172.90.4.13:8080/swagger-ui/index.html?urls.primaryName=codereview-gpt%20join%26login#/auth)
