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

Res 네이밍 규칙은 [[Android] Resources Naming Rule](https://b.jy.is/android-resource-naming-rule/)을 기반으로 작성됩니다. 하지만 size는 제외합니다.



<br>

### 기술 노트
- 아키텍처 : MVVM, Clean Architecture
    
    My GIPHY의 아키텍쳐는 MVVM과 Clean Architecture를 사용합니다.
    - Clean Architecture
        
        My GIPHY는 아래의 3가지 모듈로 이루어져 있습니다. 각각의 역할은 다음과 같습니다.
        
        - 데이터
            
            이 모듈은 android library 모듈입니다. Retorift이나 Room에 직접 접근하여 Repository를 
            구현함으로써 도메인에서 비즈니스 로직을 처리할 수 있도록 합니다. 자세히는 다음과 같이 
            이루어져있습니다.
            
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
           감소와 MediatorLiveData와 같은 방식으로 리액티브 프로그래밍을 지원해 MVVM구조를 선택하게 되었
           습니다. MVVM의 구성은 다음과 같습니다.
           
           1. ViewModel : 도메인에서 작성한 UseCase를 통해서 Model에 접근합니다. 그 결과를 바탕으로
           데이터를 변경하거나 이벤트를 발생시킵니다.
           2. View : View는 Activity, Fragment가 해당할 수 있습니다. View는 ViewModel에 있는 데이터와
           이벤트를 observe하고 있습니다. ViewModel의 데이터가 변경될 때 자동으로 화면이 변경됩니다.

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

### 오픈 소스

- RxJava

    RxJava는 비동기 데이터 스트림을 관리할 수 있는 라이브러리입니다. 스케줄러 관리를 자유롭게 할 수 있기 
    때문에 비동기 작업인 API호출 (안드로이드에서는 메인 스레드에서 네트워크 호출을 할 수 없음) 을 할 때 용이합니다.
    그 외에 map, flatMap 등 스트림을 가공할 수 있는 다양한 기능을 제공하여 데이터 처리에 유용합니다. 
    
    그래서 MyGIPHY는 모든 API 호출의 응답을 RxJava의 Flowable로 처리합니다. 여기서 Observable대신 Flowable을 
    사용한 이유는 Backpressure라는 버퍼때문입니다. Backpressure Buffer는 데이터가 한번에 떠밀려와 
    성능에 영향을 주거나 OutOfMemory가 발생할 수 있다. 

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
    
    Koin은 의존성 주입을 위해서 사용했습니다. Dagger와 성능 차이는 별로 없지만 학습하기 쉽고 빠르게 코드를
    작성할 수 있어 상당히 유리하다고 생각했습니다.
         
<br>

- Glide

    Glide는 이미지외에도 GIF를 지원합니다. Glide는 다른 라이브러리 중에서도 독보적인 스피드를 자랑하며,
    사용 방법또한 간단합니다. 

<br>    
    
- Anko
    
    Anko는 개인적으로 모든 프로젝트에 적용하고 있는 오픈소스입니다. Anko는 안드로이드 개발에서 정말 쓸데없이
    귀찮은 작업을 단순화 시켜줍니다.      

(이 부분은 앞으로 개발을 진행하며 채워나갈 예정입니다.)
