package com.mmfsin.oneworld.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.mmfsin.oneworld.data.ddbb.SharedPrefs
import com.mmfsin.oneworld.data.ddbb.daos.EventsDAO
import com.mmfsin.oneworld.data.ddbb.daos.UsersDAO
import com.mmfsin.oneworld.data.mappers.toEvent
import com.mmfsin.oneworld.data.mappers.toEventDTO
import com.mmfsin.oneworld.data.mappers.toEventList
import com.mmfsin.oneworld.data.models.EventDTO
import com.mmfsin.oneworld.domain.interfaces.IEventsRepository
import com.mmfsin.oneworld.domain.models.Event
import com.mmfsin.oneworld.utils.CREATOR_ID
import com.mmfsin.oneworld.utils.EVENTS
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import java.util.UUID
import javax.inject.Inject
import kotlin.coroutines.resume

class EventsRepository @Inject constructor(
    private val sharedPrefs: SharedPrefs,
    private val eventsDAO: EventsDAO,
    private val usersDAO: UsersDAO
) : IEventsRepository {

    //        override suspend fun getEvents(): List<Event>? {
    //            insertDataInFirestore()
    //            return emptyList()
    //        }

    override suspend fun getEvents(): List<Event>? = suspendCancellableCoroutine { cont ->
        val result = mutableListOf<Event>()
        val db = FirebaseFirestore.getInstance()
        db.collection(EVENTS).get().addOnSuccessListener { documents ->
            for (doc in documents) {
                val eventEntity = doc.toObject(EventDTO::class.java)
                result.add(eventEntity.toEvent())
            }
            cont.resume(result)
        }.addOnFailureListener {
            cont.resume(null)
        }
    }

    override suspend fun createEvent(event: Event) {
        val user = usersDAO.getActiveUser() ?: throw IllegalStateException("No active user")

        val updatedEvent = event.copy(
            creatorId = user.id,
            creatorName = user.name
        )

        eventsDAO.insertSingleEvent(updatedEvent.toEventDTO())

        FirebaseFirestore.getInstance()
            .collection(EVENTS)
            .document(event.id)
            .set(updatedEvent)
            .await()
    }

    override suspend fun getMyEventsCreated(userId: String): List<Event>? {
        return if (sharedPrefs.checkUserEventsFromServer()) {
            val db = FirebaseFirestore.getInstance()
            val snapshot = db.collection(EVENTS).whereEqualTo(CREATOR_ID, userId).get().await()
            val events = snapshot.documents.mapNotNull { doc -> doc.toObject(EventDTO::class.java) }

            /** Save in Room */
            eventsDAO.insertUserEvents(events)

            sharedPrefs.searchEventsInServer(value = false)
            events.toEventList()

        } else eventsDAO.getUserEvents(userId).toEventList()
    }

    /**********************************************************************************************************************************/
    /**********************************************************************************************************************************/
    /**********************************************************************************************************************************/

    suspend fun insertDataInFirestore() {
        val db = FirebaseFirestore.getInstance()
        val batch = db.batch()

        val listToInsert = listToInsert()
        val usersCollection = db.collection(EVENTS)

        for (data in listToInsert) {
            val id = data["id"]?.toString() ?: continue
            val newDocRef = usersCollection.document(id)
            batch.set(newDocRef, data)
        }

        batch.commit().await()
    }

    private fun listToInsert(): List<HashMap<String, Any>> {
        return listOf(
            hashMapOf(
                "id" to UUID.randomUUID().toString(),
                "image" to "https://firebasestorage.googleapis.com/v0/b/entre-mentes.firebasestorage.app/o/Packs%2Fhappy.png?alt=media&token=c9e91343-8be3-49bc-b6f3-693b68d83fbc",
                "title" to "Titulo evento 1",
                "description" to "dlaksjflksj lskdjlkfsjlk lkjsd lksjdflkj slkdlkjsd ",
                "creatorName" to "Mufasita",
                "time" to "23:02",
                "address" to "c/La tontería 32",
            ),
            hashMapOf(
                "id" to UUID.randomUUID().toString(),
                "image" to "https://firebasestorage.googleapis.com/v0/b/entre-mentes.firebasestorage.app/o/Packs%2Fhappy.png?alt=media&token=c9e91343-8be3-49bc-b6f3-693b68d83fbc",
                "title" to "Titulo evento 2",
                "description" to "dlaksjflksj lskdjlkfsjlk lkjsd lksjdflkj slkdlkjsd ",
                "creatorName" to "Mufasita",
                "time" to "23:02",
                "address" to "c/La tontería 32",
            ),
            hashMapOf(
                "id" to UUID.randomUUID().toString(),
                "image" to "https://firebasestorage.googleapis.com/v0/b/entre-mentes.firebasestorage.app/o/Packs%2Fhappy.png?alt=media&token=c9e91343-8be3-49bc-b6f3-693b68d83fbc",
                "title" to "Titulo evento 2",
                "description" to "dlaksjflksj lskdjlkfsjlk lkjsd lksjdflkj slkdlkjsd ",
                "creatorName" to "Mufasita",
                "time" to "23:02",
                "address" to "c/La tontería 32",
            ),
            hashMapOf(
                "id" to UUID.randomUUID().toString(),
                "image" to "https://firebasestorage.googleapis.com/v0/b/entre-mentes.firebasestorage.app/o/Packs%2Fhappy.png?alt=media&token=c9e91343-8be3-49bc-b6f3-693b68d83fbc",
                "title" to "Titulo evento 3",
                "description" to "dlaksjflksj lskdjlkfsjlk lkjsd lksjdflkj slkdlkjsd ",
                "creatorName" to "Mufasita",
                "time" to "23:02",
                "address" to "c/La tontería 32",
            ),
            hashMapOf(
                "id" to UUID.randomUUID().toString(),
                "image" to "https://firebasestorage.googleapis.com/v0/b/entre-mentes.firebasestorage.app/o/Packs%2Fhappy.png?alt=media&token=c9e91343-8be3-49bc-b6f3-693b68d83fbc",
                "title" to "Titulo evento 4",
                "description" to "dlaksjflksj lskdjlkfsjlk lkjsd lksjdflkj slkdlkjsd ",
                "creatorName" to "Mufasita",
                "time" to "23:02",
                "address" to "c/La tontería 32",
            ),
        )
    }
}