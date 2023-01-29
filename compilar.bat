git clone https://github.com/PabloCaiza/library_v1.git && cd ./library_v1 && cd ./author-microservice && gradlew build -Dquarkus.package.type=uber-jar && docker build -t pjcaiza/app-authors:1.0 . && cd .. && cd ./book-microservice && gradlew jar && gradlew copyLibs && docker build -t pjcaiza/app-books:1.0 . && cd .. && cd ./book-client && gradlew jar && gradlew copyLibs && docker build -t pjcaiza/app-web:1.0 . && docker push pjcaiza/app-web:1.0 && docker push pjcaiza/app-books:1.0 && docker push pjcaiza/app-authors:1.0 && cd .. && docker compose up




