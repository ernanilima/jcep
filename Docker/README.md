Gerar a imagem personalizada:
* Copie o arquivo .war para a pasta do Dockerfile com o comando `cp ../target/jcep-*.war .`
* Criar imagem com base no Dockerfile `docker build -t "jcep:v0.1-test" .`

Gerar os containers:
* Para criar os containers com base no docker-compose.yml execute o comando `docker-compose up -d`

OBS: caso precise o container pode ser acessado com o comando `docker exec -it docker_container_jcep_1 bash`
