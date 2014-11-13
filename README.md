Jersey Container API
====================

Discover cotainer implementation from classpath and loads your JAX-RS resource.

Checkout last version [here](https://bintray.com/guestful/maven/guestful.module.jersey-container/view)

[![Build Status](https://drone.io/github.com/guestful/module.jersey-container/status.png)](https://drone.io/github.com/guestful/module.jersey-container/latest)

Setup
-----

Add in your POM:

```
<repositories>
    <repository>
        <id>bintray</id>
        <url>http://dl.bintray.com/guestful/maven</url>
    </repository>
</repositories>
```

and

```
<dependency>
    <groupId>com.guestful.module</groupId>
    <artifactId>guestful.module.jersey-container</artifactId>
    <version>?</version>
</dependency>
```

Then choose between these two implementation:

* [Jetty](/module.jersey-container-jetty)
* [Undertow](/module.jersey-container-undertow)

Example
-------

`GApplication` is a subclass of `ResourceConfig`.

```java
class Main extends GApplication {

    @Override
    void configure(Container container) {
        container
            .setPort(8080)
            .setMaxWorkers(50) // determine max. concurrent request threads (i.e. 50 is a max for Heroku)
            .setContextPath('/api')
    }

    @Override
    void initialize() {
        //register your resources here
        registerClasses(
            RootResource,
            MonitoringResource
            // etc.
        )
    }

    // start your app
    public static void main(String... args) throws Exception {
        new Main().run(args);
    }

}
```
