package livemarket.business.kotlin.app.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "roles")
class Role(@Id var id: String?, var name: String?)
{

    data class Builder(
            var id: String? = null,
            var name: String? = null
            ) {

        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name= name}
        fun build() = Role(id, name)
    }
}
