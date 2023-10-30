---
title: '[Springboot] Swagger'
updated: 2023-10-13 09:51:08Z
created: 2023-10-13 06:48:45Z
latitude: 37.44491680
longitude: 127.13886840
altitude: 0.0000
---


# Swagger


## 개요
* 구현 로직 없이 API 리소스를 시각화하고 상호 작용할 수 있음
* OpenAPI(이전의 Swagger) 사양에서 자동으로 생성되며, 시각적 문서가 제공되므로 백엔드 구현 및 클라이언트 측에서 쉽게 사용할 수 있다


* Swagger : 원래 API 명세를 위한 프레임워크 였었음


### OpenAPI : 
* 예전 이름 : Swagger Specification -> OpenAPI Initiative
* OAS(OpenAPI Specifiction) :  HTTP API에 대한 프로그래밍 언어에 구애받지 않는 표준 인터페이스 설명을 정의
* 현재) RESTful API 디자인에 대한 정의(Specification) 표준



## 특징
*  API 문서를 자동으로 생성할 수 있는 기능
* 스키마 및 모델 정의를 통해 명확한 요구사항 기록
*  API 테스트 편리
* 언어중립적 : Java에만 있는건 아님
* UI : 시각적으로 볼 수 있음
* 플러그인 호환성

### API 문서화의 중요성
* 통신의 명확성: API는 여러 시스템 간의 통신 방법이므로, 그 규약이 명확하지 않으면 협업이나 연계 작업이 어려워짐
* 개발 속도 향상: 문서화된 API는 개발자가 기능을 이해하고 테스트하기 쉽고 이로 인해 통합 테스트의 시간이 단축 가능
* 유지 보수: 시간이 지나도 API의 기능과 스펙을 쉽게 파악할 수 있어, 유지 보수 작업에 도움
* 사용자 및 고객의 신뢰도 향상: 외부 개발자나 고객이 해당 API를 사용할 때, 잘 문서화된 API는 신뢰도를 높이고 오류 가능성을 줄여줌


### API Test
Try it out 눌러서 테스트가 가능함

<img src="{{ site.baseurl }}/assets/e0de4f8a1bacc1859981ccd85a3e33bd.png"/>

<img src="{{ site.baseurl }}/assets/241d649c0cfec758711b469e6bdcd7c6.png"/>
Execute 눌러보면 아래에 curl 요청 코드도 볼 수 있는데, 이 코드를 통해서 backend가 원하는 요청 정보와 front-end의 요청 구현사항을 비교해 볼 수 있음 (또는 개발자모드/네트워크에서 비교 가능함)



Example value와 Schema 정의 확인 가능
<img src="{{ site.baseurl }}/assets/938ad10899d538fe6e08ceece8ed0087.png"/>
<img src="{{ site.baseurl }}/assets/aa54b4160afbb778bf0d7c0b52717c2f.png"/>

페이지 페이지 하단에 Response, Request를 위한 Dto를 모아서 볼 수 있다.



### json으로 출력도 가능
http://172.90.4.13:4040/v3/api-docs/codereview-gpt



### Authorization 가능
* 추가 설정이 필요하지만 JWT Token 처리할 수 있다.
<img src="{{ site.baseurl }}/assets/f896e65a0524ee698d3662916ef7353b.png"/>




참고
swagger codegen



---

# Swagger 설정 방법
* 세팅 : SwaggerConfiguration.java에 되어있음
* 2가지로 개발되었으나 현재는 openApi것만 유지보수 되고있음
	* springfox
		* springboot 2.x
	* openapi
		* Springboot 3.x 기준 openApi 3.0 을 따름

* gradle(예시 - codereview-gpt-admin-server)
```
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
implementation 'org.springdoc:springdoc-openapi-starter-webmvc-api:2.0.2'
```





* 기본설정 : yml
* 구현 : Java는 Annotation을 많이 쓰지만 yml로도 가능

* 서버에서 특별한 설정을 하지 않아도 endpoint (e.g. @Controller, @GetMapping) 구현을 해 놓으면 리스트에 저절로 추가가 되어서 테스트 정도는 해볼 수 있음
* 아래의 Annotation 설정을 하면 이름, 설명, example 초기값 등이 지정된다.


## 기본 경로
* 서버경로가 localhost:8080 👉 http://localhost:8080/swagger-ui.html
* yml 설정을 통해서 변경 가능






# Annotation 종류 - springboot 3 기준
* swagger 2 버전(springboot 2.x)은 다름
	* https://oingdaddy.tistory.com/272


## 그룹 정의
**<mark style="background: #ffd400">@Tag</mark>**
<img src="{{ site.baseurl }}/assets/8a2095e2adb20799af421f7dffd0487b.png"/>
```
@Tag(name = "관리자 관리", description = "admin-users 관리를 위한 api 입니다.")
```
* 컨트롤러마다 정의




## API 정의

**<mark style="background: #ffd400">@Operation</mark>**
* 요청 메서드의 API 이름, 속성  정의
<img src="{{ site.baseurl }}/assets/0cbfe34627cdd12c37de837f4245fe3d.png"/>

```
@Operation(summary = "관리자 전체 조회", description = "페이지 별 관리자 리스트를 반환한다.")
```



### Http 응답 정보 정의
**<mark style="background: #ffd400">@ApiResponses</mark>**
**<mark style="background: #ffd400">@ApiResponse</mark>**
* 메서드 위에 Operation과 같이 정의
<img src="{{ site.baseurl }}/assets/dea15433ec44be0b53b6b08513049454.png"/>

e.g.
 ```
@ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ExampleAdminUserListResponse.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청", content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "401", description = "인가 확인되지 않은 접근", content = @Content(schema = @Schema(implementation = Example401Response.class))),
            @ApiResponse(responseCode = "403", description = "인증이 확인되지 않은 접근", content = @Content(schema = @Schema(implementation = Example403Response.class))),
            @ApiResponse(responseCode = "500", description = "서버 오류 발생", content = @Content(schema = @Schema()))
    })
```

* 리턴할 수 있는 httpStatus코드를 정의해주기
* httpStatus코드 별 리턴 타입 정의 가능 (httpStatus.OK경우 기본 리턴 타입이 자동으로 매핑됨)





### 요청 타입 및 값 정의
<img src="{{ site.baseurl }}/assets/5239666c4657b490680de87cdfb40d2b.png"/>
*  **<mark style="background: #ffd400">@Parameter</mark>**
*  	이름 : 컨트롤러에서 요청하는 함수 내의 파라미터  
* 숨기고 싶을때 @Hidden


e.g.

  ```
@GetMapping("/search")
    public Page<AdminUserResponse> searchUsers(
            @Parameter(name = "SearchAdminUserRequest", description = "검색 요청 정보")
            SearchAdminUserRequest requestDto,
            @Parameter(name = "pageable", description = "페이지 정보",
                    example = """
                            {
                                "page" : 0,
                                "size" : 10,
                                "sort" : []
                            }
                            """)
            Pageable pageable) {}
```
* name : 안쓰면 문서상에서 arg0, arg1 이런식으로 표시된다.
* example을 @Parameter 안에 넣을 수 있고
* 안쓰면 👉 객체의 정의 @Schema안에서 가져올 수도 있다.
* 	 Pageable 등 직접 지정하기 어려운 경우에 직접 구현하였으나 코드가 길어짐
* 	 @Schema : 반환타입 참고




### 반환타입 및 값 정의
<img src="{{ site.baseurl }}/assets/917ab53bcc8a0cb62608af3e7507bafe.png"/>
* class, record, interface 모두 가능
* 컨트롤러에서 할 수도 있지만 타입을 정의하는 위치(class 타입 정의)에서 사용

**<mark style="background: #ffd400">@Schema</mark>**
- 반환타입의 이름 (클래스) 정의
	- e.g.
```
@Schema(description = "관리자 가입정보")
```
* 반환타입 내 멤버(변수)도 정의
	* e.g. 
 ```
@Schema(description = "이메일", example = "string@str.ss")
        @Email String email,
```


* 참고) 컨트롤러에서 명시하기
* * 안 써도 기본적으로는 알아서 매핑시켜줌.
```
@ApiResponse(content = @Content(schema = @Schema(implementation=클래스명.class)))
```




### 참고 : 반환 타입이 제네릭인 경우
* 기본적으로는 지원해줌 
e.g. Page&lt;AdminUserResponse&gt; 👉 PageAdminUserResponse

<img src="{{ site.baseurl }}/assets/d66d15f876b3d5a4804d2f0aa54ee37e.png"/>
* 또는 (필요하다면) Example을 위한 클래스를 선언해준다.

<img src="{{ site.baseurl }}/assets/3e19d8c499344652ce250c4a864b759f.png"/>


```
public abstract class ExampleAdminUserListResponse implements Page<AdminUserResponse>{
}
```


```
@ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(schema = @Schema(implementation = ExampleAdminUserListResponse.class))),
```



















