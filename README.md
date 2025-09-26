
# Kotlin Multi Platform
### 공통
- Room, Orbit, Koin, Paging3, Coil 등의 핵심 라이브러리들이 KMP를 지원하므로 호환성 관련 문제는 없었습니다.
- 다만 빌드 퍼포먼스가 네이티브에 비해 많이 떨어지는 편이며, 트러블슈팅이 어렵다는 단점이 있었습니다.
- 안드로이드/iOS 각각의 네이티브 지식이 어느 정도 갖추어 지지 않았다면 프로젝트 환경 구성에 들어가는 리소스가 클 것으로 예상됩니다.
- 멀티 모듈 아키텍처와 DI 프레임워크/라이브러리를 함께 사용할 경우 의존성 관계가 복잡해질 수 있어 주의해야 합니다.

### 아키텍처
- androidApp / shared(composeApp) / iosApp으로 구성되어 있습니다.
- androidApp/iosApp은 의존성 주입을 위한 koin 코드와 설정 파일만 존재하므로, 아래 다이어그램은 shared 모듈 내부의 의존성 관계를 나타냅니다.
- 모듈 단위로 구성되어 있으며, shared/commonMain에서는 피쳐별 domain 모듈에만 의존성을 가집니다.
- 리포지토리 구현체 바인딩을 위해 shared/androidMain, shared/iosMain에서 피쳐별 data 모듈에 의존성을 가지게 하여 의존성 주입만을 수행합니다.(99.9%의 코드가 공통 모듈인 commonMain에 존재하므로, data 계층에 대한 의존성으로 인해 문제가 발생할 리스크가 없습니다)
- shared/commonMain에서 사용하는 ViewModel은 안드로이드 의존성이 없이 플랫폼 독립적으로 구현되어 iOS에서 별도의 ViewModel 클래스를 구현하지 않았으며, MVI 구조에서 비즈니스/프레젠테이션 로직을 나누기 위해 Store를 주입 받아 사용하였습니다.
  Store에서 비즈니스 로직을 수행하며 State와 SideEffect를 갱신하고, ViewModel에서는 UI와 비즈니스 로직의 연결부 역할을 수행합니다.
<img width="2265" height="1940" alt="Image" src="https://github.com/user-attachments/assets/8991f1fb-9a89-4650-ba25-8b5aae62f429" />

### 사용 기술
- Kotlin multiplatform + Compose multiplatform
- Kotlin `2.2.0`
- Clean Architecture + MVI 패턴(Orbit 프레임워크 `10.0.0`)
- Jetpack Compose, Workmanager, Paging3, Room, Lifecycle
- Ktor `3.2.3` + Ktorfit `2.6.4` + HTTP Client(Android: OkHttp, iOS: Darwin)
- Koin `3.6.4` + Ksp `2.2.0-2.0.2`
- Coroutines `1.8.1`
- Coil `3.3.0`
- Kotlinx serialization, datetime
- Kermit `2.0.4`
- Ai assistant(Chat GPT 5 + Copilot)

### 주요 기능
- 날씨
  - Lat/Lon 값 기반 오늘 날씨 데이터 조회(openweather 무료 api)
  - Lat/Lon 값 기반 3시간 간격으로 5일치 날씨 데이터 조회(openweather 무료 api)
  - 사용자 위치의 날씨 정보 제공
    - 일몰, 일출
    - 바람, 돌풍, 방향
    - 체감 온도
    - 강수량(지난 1시간)
    - 습도, 기압
- 할 일
  - 할 일 리스트 조회
  - 할 일 추가/수정/삭제
