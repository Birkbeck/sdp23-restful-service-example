# Helidon SE Employee Example

## Corrections to the official tutorial instructions

1. Make sure that the following dependencies are included in the `pom.xml` file:

```
        <dependency>
            <groupId>javax.json.bind</groupId>
            <artifactId>javax.json.bind-api</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>org.eclipse</groupId>
            <artifactId>yasson</artifactId>
            <version>1.0</version>
        </dependency>
        <dependency>
            <groupId>io.helidon.media.jsonb</groupId>
            <artifactId>helidon-media-jsonb-server</artifactId>
        </dependency>
```

2. Include the following line in to `Rounting.builder()` in method `createRouting` in class `Main`:

```.java
                .register(JsonBindingSupport.create())
```

3. Comment out `.addLiveness(HealthChecks.healthChecks())` in method `createRouting` in class `Main`.
