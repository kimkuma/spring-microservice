## Dorkcer 커맨드
###빌드
```shell
docker build -t product-service .
```
###실행
```shell
docker run -d -p8080:8080 -e "SPRING_PROFILES_ACTIVE=docker" --name my-prd-srv product-service 
```