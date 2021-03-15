///*
// * This file is property of the Parlacom Telecommunications Brazil
// * This computer program is protected by copyright law and international treaties. Unauthorized reproduction
// * or distribution of this program, or any portion of it, may result in severe civil criminal penalties,
// * and will be prosecuted to the maximum extent possible under the law.
// */
//package net.parlacom.iot.sigfox.config
//
//import io.swagger.v3.oas.models.Components
//import io.swagger.v3.oas.models.OpenAPI
//import io.swagger.v3.oas.models.info.Contact
//import io.swagger.v3.oas.models.info.Info
//import io.swagger.v3.oas.models.info.License
//import io.swagger.v3.oas.models.security.SecurityScheme
//import org.springdoc.core.GroupedOpenApi
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//
///**
// * https://github.com/springdoc/springdoc-openapi-demos/blob/master/springdoc-openapi-test-app2/src/main/java/org/springdoc/demo/app2/Application.java
// * https://github.com/swagger-api/swagger-core/wiki/Swagger-2.X---Annotations#OpenAPIDefinition
// *
// * @author Luiz Moraes
// * @version 1.0, 2018-10-24
// */
//@Configuration
//class ApiDocConfig {
//    /**
//     * TODO
//     *
//     * @return
//     */
//    @Bean
//    fun apiOpenApi(): GroupedOpenApi? {
//        val paths = arrayOf("/v1/sigfox/**")
//        val packagedToMatch = arrayOf("net.parlacom")
//        return GroupedOpenApi.builder().setGroup("sigfox").pathsToMatch(*paths).packagesToScan(*packagedToMatch)
//            .build()
//    }
//
//    /**
//     * TODO
//     *
//     * @param title
//     * @param description
//     * @param licenseName
//     * @param licenseUrl
//     * @param termsOfService
//     * @param contactName
//     * @param contactUrl
//     * @param contactEmail
//     * @param version
//     * @return
//     */
//    @Bean
//    fun customOpenAPI(
//        @Value("\${springdoc.swagger-ui.title}") title: String,
//        @Value("\${springdoc.swagger-ui.description}") description: String,
//        @Value("\${springdoc.swagger-ui.license-name}") licenseName: String,
//        @Value("\${springdoc.swagger-ui.license-url}") licenseUrl: String,
//        @Value("\${springdoc.swagger-ui.terms-of-service}") termsOfService: String,
//        @Value("\${springdoc.swagger-ui.contact-name}") contactName: String,
//        @Value("\${springdoc.swagger-ui.contact-url}") contactUrl: String,
//        @Value("\${springdoc.swagger-ui.contact-email}") contactEmail: String,
//        @Value("\${springdoc.swagger-ui.version}") version: String
//    ): OpenAPI? {
//        return OpenAPI()
//            .components(
//                Components().addSecuritySchemes(
//                    "basicScheme",
//                    SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")
//                )
//            )
//            .info(
//                Info().title("Petstore API").version(version).description(
//                    "This is a sample server Petstore server.  You can find out more about Swagger at [http://swagger.io](http://swagger.io) or on [irc.freenode.net, #swagger](http://swagger.io/irc/).      For this sample, you can use the api key `special-key` to test the authorization     filters."
//                ).contact(
//                    Contact().name(contactName).email(contactEmail).url(contactUrl)
//                )
//                    .termsOfService("http://swagger.io/terms/")
//                    .license(License().name("Apache 2.0").url("http://springdoc.org"))
//            )
//    }
//}