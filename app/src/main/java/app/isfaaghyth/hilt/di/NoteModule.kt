package app.isfaaghyth.hilt.di

import app.isfaaghyth.hilt.data.repository.NoteRepository
import app.isfaaghyth.hilt.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@InstallIn(ActivityComponent::class)
@Module abstract class NoteModule {

    @Binds
    @ActivityScoped
    abstract fun bindLocalDataScore(data: NoteRepositoryImpl): NoteRepository

}