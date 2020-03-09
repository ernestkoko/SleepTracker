/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// this is the DB
@Database(entities = [SleepNight::class], version = 1, exportSchema = false)//this tells the DB the entity to use
abstract class SleepDatabase: RoomDatabase(){

    abstract val sleepDatabaseDao: SleepDatabaseDao // this tells the DB to use the DAO specified

    // companion objects allow one to use the DB without instantiating it
    companion object{
        @Volatile // Volatile ensure the value of Db is always up-to-date across various threads
        private var INSTANCE: SleepDatabase? = null
        fun getInstance(context: Context): SleepDatabase{
            synchronized(this){ // this runs on one thread
                var instance = INSTANCE  // we have to assign INSTANCE to a local var before we return it
                if (instance == null){
                    instance = Room.databaseBuilder(context.applicationContext,
                    SleepDatabase::class.java,
                    "sleep_histroy_database").fallbackToDestructiveMigration().build()

                    INSTANCE = instance

                }
                return instance
            }
        }

    }
}
