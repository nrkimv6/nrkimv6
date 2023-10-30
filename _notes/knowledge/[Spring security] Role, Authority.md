---
title: '[Spring security] Role, Authority'
updated: 2023-10-24 04:07:47Z
created: 2023-10-20 01:25:01Z
latitude: 37.44491680
longitude: 127.13886840
altitude: 0.0000
---



# 단어의 의미


Role
* 역할
* 사용자 그룹, 지위 
* 관리자, 사용자, 게스트


Authority
* 권한
* 역할은 권한의 특별한 형태


Previlege
* 특권
* 권한보다 세분화된 접근제어
* 사용자_읽기, 사용자_쓰기 등


  
  
# Spring security 에서의 의미
Authority
* USER
* org.springframework.security.core.userdetails.UserDetails 인터페이스를 통해 사용
* 일반적인 권한을 나타내는 용어
* role의 구체적인 단위
* GrantedAuthority로 표현
  

Role
* ROLE_USER
- org.springframework.security.core.userdetails.User 클래스에서 사용
- 권한의 상위집합으로 권한을 포함함
- ROLE_ 접두사 추가됨 (spring security 4~)
	- Authority 명(GrantedAuthority)이 ADMIN일 경우 Role은 'ROLE_ADMIN'

   
---
* roles, authority 개념 https://www.youtube.com/watch?v=HWp0tRYJVCY
<img src="{{ site.baseurl }}/assets/da25fd69b4b256e5264d612979069f31.png"/>
---
by chatgpt


```
UserDetails를 직접 구현할 때 실제로 Role과 Authority의 구분은 명확하지 않습니다. UserDetails 인터페이스에는 권한 목록을 반환하는 getAuthorities() 메서드만 있습니다. 이 메서드는 GrantedAuthority 객체의 컬렉션을 반환합니다.

GrantedAuthority는 권한이나 역할을 나타내는 인터페이스입니다. 여기서 "역할"이라는 용어는 널리 알려진 "ROLE_" 접두사를 사용하는 관례에 기반하여, 특정한 권한의 집합을 나타낼 때 주로 사용됩니다. 그러나 GrantedAuthority 자체는 이 구분을 직접적으로 표현하지 않습니다.

따라서, 당신이 말한 것처럼, UserDetails를 구현할 때 구체적으로 Role 클래스나 인터페이스가 별도로 존재하지 않습니다. 대신, GrantedAuthority의 구현을 통해 역할과 권한을 나타냅니다. 구분은 대개 "ROLE_" 접두사를 사용하는 관례에 따라 이루어집니다
```


  
참고  
role, authority, Users : https://chat.openai.com/share/914c3716-96b1-4e89-b061-9421daf580d9  
spring security 권한 동작 : https://gregor77.github.io/2021/04/21/spring-security-02/ 