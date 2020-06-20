package livemarket.business.kotlin.app.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.resource.*
import java.io.IOException
import java.util.*
import javax.servlet.http.HttpServletRequest

@Configuration
@OpenAPIDefinition(info = Info(title = "My API", version = "v1"))
@SecurityScheme(name = "bearerAuth", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
class SpringdocSwaggerFix : WebMvcConfigurer {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/**/*.html")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .resourceChain(false)
                .addResolver(WebJarsResourceResolver())
                .addResolver(PathResourceResolver())
                .addTransformer(IndexPageTransformer())
    }

    inner class IndexPageTransformer : ResourceTransformer {
        @Throws(IOException::class)
        override fun transform(request: HttpServletRequest, resource: Resource,
                               transformerChain: ResourceTransformerChain): Resource {
            return if (resource.url.toString().endsWith("/index.html")) {
                var html = getHtmlContent(resource)
                html = overwritePetStore(html)
                TransformedResource(resource, html.toByteArray())
            } else {
                resource
            }
        }

        private fun getHtmlContent(resource: Resource): String {
            return try {
                val inputStream = resource.inputStream
                val s = Scanner(inputStream).useDelimiter("\\A")
                val content = s.next()
                inputStream.close()
                content
            } catch (e: IOException) {
                throw RuntimeException(e)
            }
        }

        private fun overwritePetStore(html: String): String {
            return html.replace("https://petstore.swagger.io/v2/swagger.json",
                    "/api-docs")
        }
    }
}
