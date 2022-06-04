# Boruto API

an API for Boruto anime heroes using _[Ktor](https://ktor.io/)_ and _[Koin](https://ktor.io/)_

## 1.Plugins
Many applications require common functionality that is out of scope of the application logic. This could be things like serialization and content encoding, compression, headers, cookie support, etc. All of these are provided in Ktor by means of what we call Plugins.

_[Plugins](https://ktor.io/docs/plugins.html)_ :

- _[Routing](https://ktor.io/docs/plugins.html)_ : Routing is the core Ktor plugin for handling incoming requests in a server application. When the client makes a request to a specific URL (for example, /hello), the routing mechanism allows us to define how we want this request to be served.
- _[Content negotiation and serialization](https://ktor.io/docs/serialization.html)_ : The ContentNegotiation plugin serves two primary purposes:

  - Negotiating media types between the client and server. For this, it uses the "Accept" and "Content-Type" headers.

  - Serializing/deserializing the content in a specific format. Ktor supports the following formats out-of-the-box: JSON, XML, and CBOR.

- _[Default headers](https://ktor.io/docs/default-headers.html)_ : The DefaultHeaders plugin adds the standard Server and Date headers into each response. Moreover, you can provide additional default headers and override the Server header.
- _[Call logging](https://ktor.io/docs/call-logging.html)_ : Ktor provides the capability to log application events using the SLF4J library. The CallLogging plugin allows you to log incoming client requests.
- _[Status pages](https://ktor.io/docs/status-pages.html)_ : The StatusPages plugin allows Ktor applications to respond appropriately to any failure state based on a thrown exception or status code.

## 2.Dependency Injection

_[What is dependency injection?](https://developer.android.com/training/dependency-injection)_

Dependency injection (DI) is a technique widely used in programming and well suited to Android development. By following the principles of DI, you lay the groundwork for good app architecture.

Implementing dependency injection provides you with the following advantages:

- Reusability of code
- Ease of refactoring
- Ease of testing


_[What is Koin?](https://insert-koin.io/docs/reference/introduction)_

Koin is a pragmatic and lightweight dependency injection framework for Kotlin developers.

`Koin is a DSL, a light container and a pragmatic API`

_[Koin for Ktor](https://github.com/InsertKoinIO/koin-ktor)_

Additional Resources: 
- _[Installing Koin plugin to Ktor (2.0.0-beta-1) is not working](https://github.com/InsertKoinIO/koin/issues/1295)_
- _[Koin for Ktor GitHub](https://github.com/InsertKoinIO/koin-ktor)_

## 3.Testing

### Ktor

_[Ktor Testing Documentation](https://ktor.io/docs/testing.html)_

Ktor provides a special testing engine that doesn't create a web server, doesn't bind to sockets, and doesn't make any real HTTP requests. Instead, it hooks directly into internal mechanisms and processes an application call directly. This results in quicker tests execution compared to running a complete web server for testing.

### Koin

_[Koin Testing Documentation](https://insert-koin.io/docs/reference/koin-test/testing)_

By tagging your class KoinTest, your class become a KoinComponent and bring you:

- by inject() & get() - function to retrieve your instances from Koin
- checkModules - help you check your configuration
- declareMock & declare - to declare a mock or a new definition in the current context
