
# Kotlin Multi Platform
### 아키텍처
- androidApp / shared(composeApp) / iosApp으로 구성되어 있습니다.
- androidApp/iosApp은 의존성 주입을 위한 koin 코드와 설정 파일만 존재하므로, 아래 다이어그램은 shared 모듈 내부의 의존성 관계를 나타냅니다.
- 모듈 단위로 구성되어 있으며, shared/commonMain에서는 피쳐별 domain 모듈에만 의존성을 가집니다.
- 리포지토리 구현체 바인딩을 위해 shared/androidMain, shared/iosMain에서 피쳐별 data 모듈에 의존성을 가지게 하여 의존성 주입만을 수행합니다.
- shared/commonMain에서 사용하는 ViewModel은 안드로이드 의존성이 없는 플랫폼 독립적인 요소이며, MVI 구조에서 계층을 나누기 위해 사용하였습니다.
  Store에서 비즈니스 로직을 수행하며 State와 SideEffect를 갱신하고, ViewModel에서는 UI와 비즈니스 로직의 연결부 역할을 수행합니다.
<img width="2265" height="1940" alt="Image" src="https://github.com/user-attachments/assets/8991f1fb-9a89-4650-ba25-8b5aae62f429" />

### 사용 기술
- Kotlin multiplatform + Compose multiplatform
- Kotlin `2.2.0`
- Clean Architecture + MVI 패턴(Orbit 프레임워크)
- Jetpack Compose, Workmanager, Paging3, Room, Lifecycle
- Ktor `3.2.3` + Ktorfit `2.6.4` + HTTP Client(Android: OkHttp, iOS: Darwin)
- Koin `3.6.4` + Ksp `2.2.0-2.0.2`
- Coroutines `1.8.1`
- Coil `3.3.0`
- Kotlinx serialization + datetime
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
