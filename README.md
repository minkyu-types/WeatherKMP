
# Kotlin Multi Platform
### 아키텍처
- androidApp / shared(composeApp) / iosApp으로 구성되어 있습니다.
- androidApp/iosApp은 의존성 주입을 위한 koin 코드와 설정 파일만 존재하므로, 아래 다이어그램은 shared 모듈 내부의 의존성 관계를 나타냅니다.
- 각 컨테이너는 모듈로써, 
<img width="2261" height="1856" alt="Image" src="https://github.com/user-attachments/assets/06bf91cb-1749-4417-9f38-638f47d2cc2a" />

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
