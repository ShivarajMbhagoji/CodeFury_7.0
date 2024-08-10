package com.hackathon.di

import com.hackathon.data.dto.DisasterRepo
import com.hackathon.data.dto.DisasterRepoImpl
import com.hackathon.data.dto.DisastersApi
import com.hackathon.data.dto.EventsUseCases
import com.hackathon.data.dto.GetEvents
import com.hackathon.emergency.ContactRepository
import com.hackathon.emergency.ContactRepositoryImpl
import com.hackathon.emergency.EmergencyUseCases
import com.hackathon.emergency.GetContacts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{


    @Provides
    @Singleton
    fun providesNewsApi():DisastersApi{
        return Retrofit.Builder()
            .baseUrl("https://api.disastercheckin.app/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DisastersApi::class.java)
    }


    @Provides
    @Singleton
    fun providesEventsRepository(
        eventsApi: DisastersApi
    ):DisasterRepo=DisasterRepoImpl(eventsApi)


    @Provides
    @Singleton
    fun providesEventsUseCases(
        disasterRepo: DisasterRepo
    ):EventsUseCases{
        return EventsUseCases(
            getEvents = GetEvents(disasterRepo)
        )
    }

    @Provides
    @Singleton
    fun providesEmergencyUseCases(
        contactRepository: ContactRepository
    ): EmergencyUseCases {
        return EmergencyUseCases(
            getContacts = GetContacts(contactRepository)
        )
    }

    @Provides
    @Singleton
    fun providesContactRepository(
        eventsApi: DisastersApi
    ): ContactRepository = ContactRepositoryImpl(eventsApi)

}