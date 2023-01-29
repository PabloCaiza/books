git clone https://github.com/PabloCaiza/library_v2.git && cd ./library_v2 && cd ./app-authors && gradlew build && docker build -t pjcaiza/app-authors:1.0 . && cd .. && cd ./app-books && gradlew jar && gradlew copyLibs && docker build -t pjcaiza/app-books:1.0 . && cd .. && cd ./app-web && gradlew jar && gradlew copyLibs && docker build -t pjcaiza/app-web:1.0 . && docker push pjcaiza/app-web:1.0 && docker push pjcaiza/app-books:1.0 && docker push pjcaiza/app-authors:1.0 && cd .. && docker compose up




