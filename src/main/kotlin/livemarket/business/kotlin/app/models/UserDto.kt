package livemarket.business.kotlin.app.models

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Builder
import lombok.Data
import lombok.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Data
@Value
@Builder
class UserDto : UserDetails {
    var id: String? = null
    private var username: String? = null
    var email: String? = null

    @JsonIgnore
    private var password: String? = null
    private var authorities: Collection<GrantedAuthority?>? = null
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return authorities!!
    }

    override fun getPassword(): String {
        return password!!
    }

    override fun getUsername(): String {
        return username!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setAuthorities(authorities: Collection<GrantedAuthority?>?) {
        this.authorities = authorities
    }






}
