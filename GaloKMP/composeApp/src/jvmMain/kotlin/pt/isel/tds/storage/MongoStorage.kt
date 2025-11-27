package pt.isel.tds.storage

import com.mongodb.MongoWriteException
import pt.isel.tds.storage.mongo.*

class MongoStorage<Key, Data>(
    collectionName: String,
    driver: MongoDriver,
    private val serializer: Serializer<Data>
) : Storage<Key,Data> {

    data class Doc(val _id: String, val data: String)
    private fun Doc(key: Key, data: Data) =
        Doc(key.toString(), serializer.serialize(data))
    val docs = driver.getCollection<Doc>(collectionName)

    // CRUD operations
    override fun create(k: Key, data: Data) {
        try{ docs.insertDocument(Doc(k, data)) }
        catch (_: MongoWriteException){
            error("Document $k already exists")
        }   }
    override fun read(k: Key): Data? =
        docs.getDocument(k.toString())
            ?.let { serializer.deserialize(it.data) }

    override fun update(k: Key, data: Data) {
        check(docs.replaceDocument(k.toString(), Doc(k, data)))
        { "Document $k does not exist to update" }
    }
    override fun delete(k: Key) {
        check(docs.deleteDocument(k.toString()))
        { "Document $k does not exist to delete" }
    }
}
