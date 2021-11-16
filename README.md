# essme-backend

## clone
(If you can't clone with https)
1. Generate SSH key
    1. [Generating a new SSH key](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)
    2. [Adding SSH key to your GitHub account](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account)
2. Clone  
   `git clone git@github.com:Lampx83/essme-backend.git`

## Run
After clone  
`cd essme-backend`  
`./mvnw spring-boot:run`

## Build jar and run
After clone  
`cd essme-backend`  
`./mvnw package`  
`java -jar target/ESSME*.jar`