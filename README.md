
# ID Token Validator  
  
A lightweight [ID Token](https://openid.net/specs/openid-connect-core-1_0.html#IDToken) validator library  for Android

  
## Using IDTokenValidator in your project
  
#### Requirement
Minumum required Android API version is 21 or later and Java 8+.

You need in  `build.gradle`  to target Java 8 byte code for Android and Kotlin plugins respectively.
```
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = '1.8'
    }
}
```

#### Dependency Declaration

This library is not public available yet so you need to download this repository into your project's folder and add **library-android**  module as project dependency

```
api(project(":library-android"))
```
  
#### ID Token Validation
`IDTokenValidator` validates given jwt token value based on [ID Token Validation specs](https://openid.net/specs/openid-connect-core-1_0.html#IDTokenValidation)

To validate your token first you need access `IDTokenValidator` using `IDTokenValidatorFactory`

```
 val tokenValidator: IDTokenValidator = IDTokenValidatorFactory.idTokenValidator;
```
then you need to invoke `tokenValidator.validate` function where you need pass;
* `token :String`  which is expected as Base64 encoded jwt token
* `issuer :String` which is the "issuer" value used in the given token creation
* `clientId:String` which is used "issuer" values used in the given token creation
properties

```
val result: Result = tokenValidator.validate("token", "issuer", "clientId")
```
if the token is validated result will be `Result.VALID` otherwise it will `Result.INVALID` which has subtype based on validation error

* **MalformedRawToken**: given jwt token is not valid
* **MissingPropertyInToken** : one of the [required field](https://openid.net/specs/openid-connect-core-1_0.html#IDToken) is missing
* **MismatchedProperty**: given token's properties not matches with request params (issuer, clientId)


## Contributing  
  
Feel free to open a issue or submit a pull request for any bugs/improvements

## License 
  
This project is licensed under the MIT license. See the [LICENSE](https://github.com/gokhanaliccii/IDTokenValidator/blob/main/LICENSE) file for more info.
