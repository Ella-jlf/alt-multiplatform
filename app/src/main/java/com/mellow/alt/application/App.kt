package com.mellow.alt.application

import android.app.Application
import com.mellow.alt.data.repository.net.service.LoginService
import com.mellow.alt.domain.usecase.LoginSendCodeUseCase
import com.mellow.alt.domain.usecase.RegisterSendCodeUseCase
import com.mellow.alt.domain.usecase.RegisterSendUserInfoUseCase
import com.mellow.alt.domain.usecase.SendPhoneUseCase
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.rx.RealmObservableFactory
import org.kodein.di.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private lateinit var kodeinStored: DI

class App : Application() {

    companion object {
        val di: DI
            get() = kodeinStored
    }

    private val baseModule = DI.Module("baseModule") {
        import(databaseModule)
        import(auxiliaryModule)
        import(netModule)
    }

    private val databaseModule = DI.Module("databaseModule") {

        bind<Realm>() with singleton {
            Realm.init(this@App)

            Realm.getInstance(
                RealmConfiguration.Builder()
                    .rxFactory(RealmObservableFactory(false))
                    .deleteRealmIfMigrationNeeded()
                    .build()
            )
        }
    }

    private val netModule = DI.Module("netModule") {
        constant("apiUrl") with "http://172.20.10.4:8000"

        bind<Retrofit>() with singleton {
            Retrofit.Builder()
                .baseUrl(instance<String>("apiUrl"))
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        bind<LoginService>() with singleton {
            instance<Retrofit>().create(LoginService::class.java)
        }

        bind<LoginSendCodeUseCase>() with provider {
            LoginSendCodeUseCase(instance())
        }

        bind<RegisterSendCodeUseCase>() with provider {
            RegisterSendCodeUseCase(instance())
        }

        bind<RegisterSendUserInfoUseCase>() with provider {
            RegisterSendUserInfoUseCase(instance())
        }

        bind<SendPhoneUseCase>() with provider {
            SendPhoneUseCase(instance())
        }
    }

    private val auxiliaryModule = DI.Module("auxiliaryModule") {

    }

    override fun onCreate() {
        if (::kodeinStored.isInitialized.not()) {
            kodeinStored = DI {
                import(baseModule)
            }
        }

        super.onCreate()
    }

}