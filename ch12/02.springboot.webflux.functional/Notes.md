# WebFlux Using a Functional Programming Model
Spring framework 5 introduced a new functional style programming model built on top of the reactive foundation in addition to the annotation based programming model.


Instead of using annotations to define request handling methods, you can implement HandlerFunctions as Java 8 lambdas and map the request URL patterns to HandlerFunctions using RouterFunctions.


HandlerFunction<ServerResponse> echoHandlerFn =
        (request ) -> ServerResponse.ok().body(fromObject(request. queryParam("name")));
                
RequestPredicate predicate = RequestPredicates.GET("/echo");
RouterFunction<ServerResponse> routerFunction = RouterFunctions.route(predicate,echoHandlerFn);


# Handler Function
The HandlerFunction is a functional interface that takes ServletRequest and returns ServletResponse.

@FunctionalInterface
public interface HandlerFunction<T extends ServerResponse>
{
    Mono<T> handle(ServerRequest request);
}

ServerRequest and ServerResponse are immutable interfaces built on top of Reactor types.


# ServerRequest

The org.springframework.web.reactive.function.server.ServerRequest interface represent a server- side HTTP request

HttpMethod method = request.method();
String path = request.path();
String id = request.pathVariable("id");
Map<String, String> pathVariables = request.pathVariables();
Optional<String> email = request.queryParam("email");
URI uri = request.uri();

You can convert a request body into a Mono or Flux using the bodyToMono() and bodyToFlux() methods.

Mono<User> userMono = request.bodyToMono(User.class);
Flux<User> usersFlux = request.bodyToFlux(User.class);



The bodyToMono() and bodyToFlux() methods are actually instances of BodyExtractor, which is used to extract the request body and deserialize it into an object. You can use the BodyExtractors utility class to extract a request body into a Mono or Flux as follows:

Mono<User> userMono = request.body(BodyExtractors.toMono(User.class));
Flux<User> userFlux = request.body(BodyExtractors.toFlux(User.class));

You can use ParameterizedTypeReference if you want to convert a request body into a generic type.

ParameterizedTypeReference<Map<String, List<User>>> typeReference = new ParameterizedTypeReference<Map<String, List<User>>>() {};
Mono<Map<String, List<User>>> mapMono = request.body(BodyExtractors.toMono(typeReference));


# ServerResponse

The org.springframework.web.reactive.function.server.ServerResponse interface represents a server-side HTTP response. The ServerResponse is an immutable interface and provides many static builder methods to construct the response with status, contentType, cookies, headers, body, etc.

ServerResponse.ok().contentType(APPLICATION_JSON).body(userMono, User.class);
ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(user));
ServerResponse.created(uri).build();
ServerResponse.notFound().build();


# RouterFunction
RouterFunction maps the incoming request to a HandlerFunction using RequestPredicate.
You can use the RouterFunctions utility class static methods to build the RouterFunction as follows :

RouterFunctions.route(GET("/echo"), request -> ok().body(fromObject(request.queryParam("name"))));

You can compose multiple route definitions into a new route definition that routes to the first handler function that matches the predicate.


import static org.springframework.web.reactive.function.server.RequestPredicates.*; 

RouterFunctions.route(GET("/echo"), request -> ok().body(fromObject(request.queryParam("name"))))
            .and(route(GET("/home"), request -> ok().render("home")))
            .andRoute(POST("/users"), request -> ServerResponse.ok().build());
            
            
Suppose you need to compose multiple routes with the same prefix. Instead of repeating the URL path in every route you can use RouterFunctions.nest() as follows:            
            
RouterFunctions.nest(path("/api/users"),
    nest(accept(APPLICATION_JSON),
        route(GET("/{id}"), request -> ServerResponse.ok().build())
        .andRoute(method(HttpMethod.GET), request -> ServerResponse.ok().build())));


# HandlerFilterFunction

If you have to compare annotation based approaches to functional approaches, RouterFunction is similar to the @RequestMapping annotation and HandlerFunction is similar to the method annotated with @RequestMapping. The new functional web framework also provides HandlerFilterFunction, which is similar to the servlet Filter or @ControllerAdvice methods.


@FunctionalInterface
public interface HandlerFilterFunction<T extends ServerResponse, R extends ServerResponse>
{
    Mono<R> filter(ServerRequest request, HandlerFunction<T> next);
    //other methods
}

# Registering HandlerFunctions as Method References

Instead of defining the HandlerFunctions using inline lambdas, it would be better to define them as methods and use method references in the route configuration

@Component
class EchoHandler
{
    public Mono<ServerResponse> echo(ServerRequest request)
    {
        return ServerResponse.ok().body(fromObject(request.queryParam("name")));
    }
}

@SpringBootApplication
class Applications
{
    @Autowired
    EchoHandler echoHandler;
    @Bean
    public RouterFunction<ServerResponse> echoRouterFunction() {
        return RouterFunctions.route(GET("/echo"), echoHandler::echo);
    }
}

By default, spring-boot-starter-webflux uses reactor-netty as the runtime engine. You can exclude reactor-netty and use some other server that supports reactive non-blocking I/O, such as Undertow, Jetty, or Tomcat as well.





