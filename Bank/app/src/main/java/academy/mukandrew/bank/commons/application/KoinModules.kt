package academy.mukandrew.bank.commons.application

import academy.mukandrew.bank.data.dataSources.auth.AuthLocalDataSource
import academy.mukandrew.bank.data.dataSources.auth.AuthRemoteDataSource
import academy.mukandrew.bank.data.dataSources.statement.StatementRemoteDataSource
import academy.mukandrew.bank.data.repositories.AuthRepository
import academy.mukandrew.bank.data.repositories.StatementRepository
import academy.mukandrew.bank.data.services.local.Database
import academy.mukandrew.bank.domain.useCases.AuthUseCase
import academy.mukandrew.bank.domain.useCases.StatementUseCase
import academy.mukandrew.bank.presenter.auth.AuthViewModel
import academy.mukandrew.bank.presenter.home.HomeViewModel
import androidx.room.Room
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object KoinModules {

    fun applicationModules() = module {
        single { RetrofitConfig.create() }
        single {
            Room
                .databaseBuilder(
                    androidApplication(),
                    Database::class.java,
                    Database.dbName
                )
                .openHelperFactory(
                    SupportFactory(SQLiteDatabase.getBytes(Database.dbPassword.toCharArray()))
                )
                .build()
        }
    }

    fun getAuthModule() = module {
        single {
            AuthUseCase(
                AuthRepository(
                    AuthLocalDataSource(get()),
                    AuthRemoteDataSource(get())
                )
            )
        }
        viewModel { AuthViewModel(get()) }
    }

    fun getHomeModule() = module {
        single {
            StatementUseCase(
                StatementRepository(
                    StatementRemoteDataSource(get())
                )
            )
        }
        viewModel { HomeViewModel(get(), get()) }
    }
}