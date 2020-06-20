package livemarket.business.kotlin.app.models

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Transaction : Serializable {
    @Id
    private val id: String? = null
    private val amount = 0.0
    private val type: String? = null
    private val creationDate: String? = null
    private val accountId = 0

    companion object {
        private const val serialVersionUID = -2772158462042804334L
    }
}
