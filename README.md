# MyGiphy

나만의 우아하고, 대단한 GIPYHY입니다.

<br>

### 필수 기능

 GIPHY어플리케이션의 Search 화면과 Favorites 화면의 기능 셋을 유사하게 구현하는 것이 이번 프로젝트의 목표입니다. 자세한 목록은 다음과 같습니다.

1. Keyword 검색
2. 검색된 이미지 목록 화면과 상세 화면
   상세화면에서 Favorites On/Off 기능 구현
3. Favorites된 이미지의 목록화면과 상세 화면
   상세화면에서 Favorites On/Off 기능 구현
4. Favorites된 목록은 앱을 다시 시작해도 유지되도록 구현

<br>



### Git 관리

- 커밋 메세지 규칙

  커밋 메세지를 작성할 때는 다음과 같은 규칙을 따르도록 권장합니다.

  - [UPDATE] 기능의 추가나 변동을 나타냅니다.
  - [UI] UI관련 로직이나 레이아웃을 변경했을 때 사용합니다.
  - [REFAC] 이미 작성된 코드를 리팩토링했을 때 사용합니다
  - [FIX] 이전에 대응하지 못했거나 눈치채지 못했던 부분을 수정합니다.

  커밋 메세지는 최대한 제목, 본문을 작성하도록 노력합니다.  

  

- 브랜치 전략

  브랜치는 크게 2가지로 이루어집니다. 제품으로 출시하지 않기 때문에 master브랜치가 곧 dev브랜치를 뜻합니다. 

  - master 기존 Git-Flow에서의 dev브랜치와 유사합니다. 
  - feature 각각의 기능은 feature를 통해서  개발된 후 master로 merge합니다.



<br>

### 네이밍 규칙

Res 네이밍 규칙은 [[Android] Resources Naming Rule](https://b.jy.is/android-resource-naming-rule/)을 기반으로 작성됩니다.



<br>

### 기술 노트

    주요 개발 내용을 정리하는 공간

- 아키텍처 : MVVM, Clean Architecture
    
    My GIPHY의 아키텍쳐는 MVVM과 Clean Architecture를 사용합니다.
    - Clean Architecture
        
        My GIPHY는 아래의 3가지 모듈로 이루어져 있습니다. 각각의 역할은 다음과 같습니다.
        
        - 데이터
            
            이 모듈은 android library 모듈입니다. Retorift이나 Room에 직접 접근하여 Repository를 
            구현함으로써 도메인에서 비즈니스 로직을 처리할 수 있도록 합니다. 자세히는 다음과 같이 
            이루어져 있습니다.
            
            1. DataSource : GiphyApi와 Room의 Dao중 하나를 선택하여 접근합니다.
            2. Repository : 도메인의 Repository를 구현합니다. data를 entity로 매핑하는 과정을 거칩니다.  


        - 도메인
           
           도메인은 코어 비즈니스 로직을 담은 계층입니다. 순수한 자바/코틀린 모듈로 이루어져 있으며, 
           데이터에서 구현한 repository를 기반으로 다양한 비즈니스 로직을 다룹니다. 
           
           1. Service : 도메인 계층에서는 서비스에 데이터에서 구현한 repository를 기반으로
           비즈니스 로직을 다룹니다. 
           2. UseCase :  UseCase를 통해서 표현 계층으로 도메인에서 처리한 로직을 담은 Flowable
           을 전달해줍니다.
           
        - 표현 (presentation)
           
           표현 계층은 MVVM 구조가 위치합니다. MVP와 고민을 잠시 했지만 DataBinding을 통한 View 로직 
           감소와 MediatorLiveData와 같은 방식으로 리액티브 프로그래밍을 지원해 MVVM구조를 선택하게 되었습니다. 
           MVVM의 구성은 다음과 같습니다.
           
           1. ViewModel : 도메인에서 작성한 UseCase를 통해서 Model에 접근합니다. 그 결과를 바탕으로
           데이터를 변경하거나 이벤트를 발생시킵니다.
           2. View : View는 Activity, Fragment가 해당할 수 있습니다. View는 ViewModel에 있는 데이터와
           이벤트를 observe하고 있습니다. ViewModel의 데이터가 변경될 때 자동으로 화면이 변경됩니다.

        비즈니스 로직을 따로 거칠 필요가 없으면 Presentation Layer (ViewModel)에서 직접 Data Layer에
        접근하여 I/O를 진행합니다. 예를 들어서 검색 결과를 불러오는 로직은 아주 간단합니다. 이런 로직을 domain
        계층에까지 넘겨 처리하게 된다면 코드의 복잡성이 증가할 것을 우려해 이런 경우는 ViewModel에서 바로 data의
        Dao에 접근할 수 있도록 권장합니다.

<br>

- Error Handling
    
    얼마 전 우연히 [Android: error handling in clean architecture](https://medium.com/@phamduy.uit/android-error-handling-in-clean-architecture-844a7fc0dc03)
    라는 글을 읽게 되었습니다. 이 글의 내용을 요약해보자면 presentation 계층은 UI 로직에 집중해야 하지만 HttpException이나 401, 404과 
    같은 data의 구체적인 내용까지 알고 있어 원래 역할에 집중할 수 없다는 의견입니다. 저도 이 글에 동의하는 부분이 많았습니다. presentation
    에서는 401, 404과 같이 코드에 따라 에러를 처리하는 것이 아닌 '인증 실패', '찾을 수 없음' 등 그 내용에 따라서 처리하는 것이 각각의 역할에
    만족한다고 생각합니다.
    
    1. 블로그에서 제시한 해결책과 문제점
    
        블로그에서는 ErrorEntity에 따라서 ViewModel의 로직을 처리하도록 권장하고 있습니다.
        하지만 Resource라는 클래스를 이용해 Success인지 Error인지 판단하는 조건이 추가되어야 했고,
        또 에러에서 어떤 에러인지 판단하기 위해서는 다시 조건문을 이용해야 했습니다.
        결과적으로 들여쓰기가 남발해 가독성이 떨어지고 쓸데없이 코드가 길어졌습니다.
        그리고 에러 처리를 onError가 아닌 onNext에서 하지만 테스트 코드를 작성한다면 빈 함수를 onError에 넘겨줘야 테스트 코드를 성공시킬
        수 있습니다.  
        
    2. 내가 제시한 해결책
        
        이전 프로젝트에서 에러 처리는 에러가 발생했을 때 HttpException을 throw하는 방법을 사용했습니다. 이 부분에서 아이디어를 얻어
        '상황에 맞는 Exception을 던져주면 깔끔하지 않을까?' 라고 생각하게 되었습니다. 그래서 기존 예외에 따라 ErrorEntity로 변경해주던
        ErrorHandler를 상황에 맞는 Exception을 반환해주도록 변경했습니다. Exception은 직접 NotFoundException, InternalException 등
        직접 선언한 클래스를 사용하게 되었습니다. domain의 ErrorHandler interface를 data에서 구현해주면서 presentation, domain은
        HttpException을 알 필요가 없어졌습니다.
        
        결과적으로 이전 문제였던 조건문의 남발, onError에서 처리, 가독성 등 다양한 문제를 해결할 수 있었습니다. 
     

<br>

- Travis-CI
    
    My GIPHY는 Travis를 통해 CI를 진행합니다. CI를 사용하는 이유는 master브랜치에 병합하는
    과정에서 나의 컴퓨터 환경이 아닌 실제 배포 환경과 비슷한 상황에서 애플리케이션이 잘 동작하는지
    확인하기 위해서 적용했습니다. Travis에선 지속적 배포(CD)를 지원하지만 이번 프로젝트에서는 배포의
    필요성이 없다고 판단하여 적용하지 않았습니다. 프로세스는 다음과 같습니다.
    
    1. feature 브랜치의 작업 내용을 master로 병합 시도
    2. Travis에서 빌드 -> 테스트 코드를 실행
    3. 결과를 Email로 전송
    
<br>

- 오프라인 모드
    
    My GIPHY는 오프라인 모드를 지원합니다. 한국은 전국 거의 모든 장소에서 네트워크를 사용할 수 있습니다.
    하지만 다른 일부 나라는 네트워크 상태가 원활하지 못한 경우도 있습니다. 그래서 인터넷이 갑자기 불안정하거나
    연결하지 않고 앱에 접속하는 경우 Room 데이터베이스에 저장된 데이터를 사용하게 됩니다. 방식은 다음과 같습니다.
    
    1. API를 통해서 데이터를 요청
    2. 만약 성공한다면 그 데이터를 Room을 통해서 저장합니다.
    3. 이후에 응답을 받을 수 없는 경우에 저장된 데이터를 사용합니다.
    4. 만약 로컬에서 불러온 데이터라면 사용자에게 SnackBar 노출해 재시도를 유됴합니다.

<br>

- 다크테마
    
    다크테마는 최근 들어서 관심있게 사용하는 기술입니다. 대마장터라는 프로젝트에서 실제로 적용해본 경험이 
    있었는데 color, drawable 시스템이 중요하다는 것을 깨달았습니다. 그래서 이번 프로젝트에선 처음부터 
    다크테마를 고려하면서 작성해야겠다고 생각했습니다. 그런데 막상 사용하는 색상이 다양하지 않고 고려할 부분은
    적었습니다. 그래도 성공적으로 다크테마를 적용하게 되었습니다.

<br>

### 최종 결과 스크린샷

![스크린샷](https://user-images.githubusercontent.com/36754680/70515261-67387480-1b78-11ea-90ca-bf6f4689bfbd.png)

<br>

### 오픈 소스

- RxJava

    RxJava는 비동기 데이터 스트림을 관리할 수 있는 라이브러리입니다. 스케줄러 관리를 자유롭게 할 수 있기 
    때문에 비동기 작업인 API호출 (안드로이드에서는 메인 스레드에서 네트워크 호출을 할 수 없음) 을 할 때 용이합니다.
    그 외에 map, flatMap 등 스트림을 가공할 수 있는 다양한 기능을 제공하여 데이터 처리에 유용합니다. 
    
    그래서 MyGIPHY는 모든 API 호출의 응답을 RxJava의 Flowable로 처리합니다. 여기서 Observable대신 Flowable을 
    사용한 이유는 Backpressure라는 버퍼때문입니다. Backpressure Buffer는 데이터가 한 ㅇ번에 떠밀려와 
    성능에 영향을 주거나 OutOfMemory를 발생시키지 않도록 예방할 수 있습니다.

<br>    
    
- Retrofit & OkHttp

    Retrofit은 Giphy API를 호출하기 위해서 사용하는 HTTP API 라이브러리입니다. RxJava를 지원하기 때문에
    응답을 Flowable로 받을 수 있습니다.
    
    OkHttp는 Interceptor를 통해서 자동으로 api_key를 쿼리에 붙여주거나, 로그를 출력하기 위해서 사용되었습니다.
    로그를 통해서 request url과 status code를 확인할 수 있어 쉽게 디버깅할 수 있습니다.
     
<br>    
    
- lifecycle (AAC)
   
   안드로이드 아키택쳐 컴포넌트의 lifecycle에서 LiveData와 ViewModel을 사용했습니다. 기존 안드로이드 생명주기는
   너무나 복잡하고, 화면 회전 등 방해 요소가 많습니다. 그래서 ViewModel과 LiveData를 통해서 안드로이드
   생명주기로 부터 자유로워질 수 있습니다. 
         
<br>

- Room
    
    Room은 로컬 데이터베이스 라이브러리입니다. 룸은 직접 SQL문을 작성할 수 있고 관계를 설정할 수 있습니다.
    Room 또한 RxJava를 지원하여 간단하게 데이터를 처리할 수 있습니다. 

<br>

- Paging Library
    
    My GIPHY같은 경우에는 RecyclerView Paging을 구현해야 하는 경우가 많습니다. Paging 라이브러리를 
    활용하면 쉽게 구현할 수 있고 직접 처리해야 하는 로직을 줄일 수 있어 유용할 것이라 생각했습니다.
    
<br>
    
- koin
    
    Koin은 의존성 주입을 위해서 사용했습니다. Dagger와 성능 차이는 별로 없지만 빠르게 코드를작성할 수 있어 상당히 유리하다고 생각했습니다.
         
<br>

- Glide

    Glide는 이미지외에도 GIF를 지원합니다. Glide는 다른 라이브러리 중에서도 독보적인 스피드를 자랑하며,
    사용 방법또한 간단합니다. 

<br>    
    
- Anko
    
    Anko는 개인적으로 모든 프로젝트에 적용하고 있는 오픈소스입니다. Anko는 안드로이드 개발에서 정말 쓸데없이
    귀찮은 작업을 단순화 시켜줍니다.      
