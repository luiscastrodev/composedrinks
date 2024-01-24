package di

class AppModule {
    fun appModule() = listOf(
        Modules.providehttpClientModule,
        Modules.provideRepositoryModule,
        Modules.provideviewModelModule
    )
}