#Example just for Renovaste
FROM alpine:3.16.3
RUN apk add --no-cache mysql-client
ENTRYPOINT ["mysql"]
