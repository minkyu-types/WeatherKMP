
# Kotlin Multi Platform
### 아키텍처
- androidApp / shared(composeApp) / iosApp으로 구성되어 있습니다.
- androidApp/iosApp은 의존성 주입을 위한 koin 코드와 설정 파일만 존재하므로, 아래 다이어그램은 shared 모듈 내부의 의존성 관계를 나타냅니다.
- 모듈 단위로 구성되어 있으며, shared/commonMain에서는 피쳐별 domain 모듈에만 의존성을 가집니다.
- 리포지토리 구현체 바인딩을 위해 shared/androidMain, shared/iosMain에서 피쳐별 data 모듈에 의존성을 가지게 하여 의존성 주입만을 수행합니다.
<img width="2265" height="1940" alt="Image" src="https://github.com/user-attachments/assets/8991f1fb-9a89-4650-ba25-8b5aae62f429" />

### Tech stack
- Kotlin multiplatform + Compose multiplatform
- Kotlin `2.2.0`
- Jetpack Compose, Workmanager, Paging3, Room, Lifecycle
- Ktor `3.2.3` + Ktorfit `2.6.4` + HTTP Client(Android: OkHttp, iOS: Darwin)
- Koin `3.6.4` + Ksp `2.2.0-2.0.2`
- Coroutines `1.8.1`
- Coil `3.3.0`
- Kotlinx serialization + datetime
- Kermit `2.0.4`
