package livemarket.business.kotlin.app.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import java.util.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size


@Document(collection = "User")
class User(
        @Id var id: String?,
        @DBRef
        var roles: Set<Role?>? = HashSet(),
        var username: String? = null,
        @NotBlank @Size(max = 2048) var password: String? = null,
        var enabled: Boolean? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var resetToken: String? = null,
        var email: String? = null
)
{


    data class Builder(
            var id: String? = null,
            var username: String? = null,
            var password: String? = null,
            var enabled: Boolean? = null,

            var roles: Set<Role?>? = HashSet(),

            var firstName: String? = null,
            var lastName: String? = null,
            var resetToken: String? = null,
            var email: String? = null
    ) {

        fun id(value: String) = apply { this.id = id }
        fun username(value: String) = apply { this.username= value}
        fun password(value: String) = apply { this.password= value}

        fun firstName(value: String) = apply { this.firstName= value}
        fun lastName(value: String) = apply { this.lastName= value}
        fun roles(roles: Set<Role?>) = apply { this.roles= roles}
        fun enabled(value: Boolean) = apply { this.enabled= value}

        fun email(value: String) = apply { this.email= value}

        fun build() = User(
                id,

         roles,
        username,
        password,
        enabled,
        firstName,
        lastName,
        resetToken,
        email)
    }
}
