# zappyride-quarkus project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew clean assemble

```
Packing the local docker image:
```shell script
docker build -f lambda/session/src/main/docker/Dockerfile.jvm -t ricardocunha/quarkus .
```

Running docker compose:
```shell script
docker-compose up
```

## Frameworks

* Vert.x for REST
* Gradle with Kotlin DSL 
* Quarkus 1.10.5

## Application Description

This is a function lambda that provides a simple example about how to use an API to get data from DynamoDB.

# Request to save data

POST http://localhost:8080/person

Sample data to POST
`
{"id":"000000000002","name":"Jane Doe"}
`

# Request to get all the data
GET http://localhost:8080/person

# Request to get and specifc person
GET http://localhost:8080/person/:name



## Using local

Start the local DynamoDB (default port is 8000)
```shell script
./dynamodb-dev.sh
```

Start Quarkus in development
```shell script
./gradlew quarkusDev
```

## Deploy on AWS (Using native compilation)

Requisites:

* AWS CLI configured
* AWS SAM CLI
* A S3 bucket to upload the code


To provide access from Lambda function to DynamoDB, it is necessary to change the following properties:
```
quarkus.dynamodb.aws.region=eu-central-1
quarkus.dynamodb.aws.credentials.static-provider.access-key-id=test-key
quarkus.dynamodb.aws.credentials.static-provider.secret-access-key=test-secret
```

It is necessary an S3 bucket to upload the code.

Compilation(You should have GRAALVM_HOME environment variable configured, if you don't it will use Docker to build):
```shell script
./gradlew clean assemble -Dquarkus.package.type=native
```

Packaging application (Replace the bucket name):
```shell script
sam package --template-file lambda/session/build/sam.native.yaml --output-template-file packaged-native.yml --s3-bucket YOUR_BUCKET_NAME
```

Deploying application (Replace the stack name, can be anything for this example):
```shell script
sam deploy --template-file /home/ricardo/IdeaProjects/session-management/packaged-native.yml --stack-name mynewstack-native --capabilities CAPABILITY_IAM
```
