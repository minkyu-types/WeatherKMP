package org.dosys.todo_data.local

//@Suppress("KotlinNoActualForExpect")
//actual object TodoDatabaseConstructor : RoomDatabaseConstructor<TodoDatabase> {
//    private lateinit var appContext: Context
//
//    fun provideApplicationContext(context: Context) {
//        appContext = context.applicationContext
//    }
//
//    actual override fun initialize(): TodoDatabase {
//        if (!::appContext.isInitialized) {
//            throw IllegalStateException("Application context is not initialized")
//        }
//        return getDatabaseBuilder(appContext)
//            .fallbackToDestructiveMigration(false)
//            .build()
//    }
//}