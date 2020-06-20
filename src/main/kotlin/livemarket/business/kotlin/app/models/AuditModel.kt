package api.mongodb.livemarket.business.mogoapi.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.Data
import lombok.EqualsAndHashCode
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AuditorAware
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

/**
 * User: franc
 * Date: 09/09/2018
 * Time: 4:14
 */
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = ["createdAt", "updatedAt"], allowGetters = true)
@Data
abstract class AuditModel  // Getters and Setters (Omitted for brevity)
    : Serializable, AuditorAware<String> {
    @CreatedDate
    var createdAt: LocalDateTime? = null
        set(createdAt) {
            field = createdAt
            if (this.createdAt == null) {
                field = LocalDateTime.now()
            }
        }

    override fun getCurrentAuditor(): Optional<String> {

        // get your user name here
        return Optional.of("xxxx")
    }

    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
        set(updatedAt) {
            field = updatedAt
            if (updatedAt == null) {
                field = LocalDateTime.now()
            }
        }

}
