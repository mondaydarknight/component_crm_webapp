# component_crm_webapp

## Prerequisites

Make sure you've installed the following requirements before building the proejct.

|                                             |                  |
|---------------------------------------------|------------------|
| **Docker** | Docker enables to build, develop, run your environments. Please install the latest for your platform. [Docker](https://docs.docker.com/install). |
| **Maven**  | Maven is a software proejct management tool. Please install the latest for your platform. [Maven](https://maven.apache.org/download.cgi).        |

## Setup dev environment
1. Run maven package command to build the project and generate JAR file.

    ```bash
    mvn clean package
    ```

2. Pull the Docker image and startup your local environment. 

    ```bash
    docker build . -t crm_webapp
    ```

3. Build the Docker container of CRM web application

    ```bash
    docker run --name crm_webapp -p 16500:8080 -v $(pwd)/target/:/tmp/target/ -it crm_webapp
    ```

## Demo
You have two options:
1.  Using CURL command to interact with REST APIs

    a. Request to register user authentication information. Specify the following field properties.
    
    | Name      | Type   | Description  |
    |-----------|--------| -------------|
    | username  | String | The username | 
    | password  | String | The password | 
    | authority | String | The role-based access control the endpoints, list the following roles and permissions. <ul><li>ROLE_ADMIN: grant all permissions to the endpoints.</li><li>ROLE_MANAGER: grant permission to modify, delete and view the endpoints</li><li>ROLE_OPERATOR: grnat the permission to create and modify the endpoints.</li></ul> |

       ```bash
       curl -v -X POST \
       -H 'Content-Type: application/json' \
       -d '{"username": "smartbee_test_01", "password": "test", "authority": "ROLE_OPERATOR"}' \
       'http://localhost:16500/api/crm/v1/users'
       ```

    b. Authenticate user permission with created username and password, it would be authorized and generate unique JWT token to the client.

       ```bash
       curl -v -X POST \
       -H 'Content-Type: application/json' \
       -d '{"username": "smartbee_test_01", "password": "test"}' \
       'http://localhost:16500/api/crm/v1/authenticate'
       
       {"token":"..."}
       ```
    
    c. Request `company`, `client` resource with authorization header.

       ```bash
       curl -v -X POST 
       -H 'Content-Type: application/json'
       -H 'Authorization: Bearer {JWT token}'
       -d '{"name": "test", "email": "crm_test_01@smartbee.com", "phone": "0905000000", "companyId": 1}' \
       'http://localhost:16500/api/crm/v1/clients'
       ```
2.  Visit the OpenAPI (Swagger) dashboard via http://localhost:16500/swagger-ui/index.html
