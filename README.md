<b><h3>Jak uruchomić wszystki usługi?</h3></b> <br/>
Z poziomu terminala wywołaj komendę "<b>mvn install</b>" - w zależności od uprawnień użytkownika do aplikacji Docker i Docker-compose może być niezbędne użycia <b>"sudo"</b>.
Komenda zarówno pobierze pobierze zależności mavena, zbuduje aplikację jak i pobierze zadockowane obrazy mikroserwisów z hub.docker.com (wojewodkainteca/family) jak i je odpali za pomocą skryptu docker-compose.yml, który znajduje się w projekcie API. </br>

Całość projektu składa się z trzech usług:
API(wojewodkainteca/family:api) (java8, maven, spring boot, JDBC) - usługa uruchamia się na porcie 8080 i na tym samym porcie działa w kontenerze. 
Usługa korzysta z prostego, autorskiego ORMa (inspirowany na Hibernacie) i CRUDa który pośredniczy w komunikacji z drugą usługą.
Aplikacja wykorzytuje również mechanizm FlyWay, który zarządza odpalanymi skryptami (w tym przypadku jednym skryptem) bazodanowym (resources/db/migration).
Wykaz dostępnych usług świadczonych przez API to:
/api/family/create - Methoda: POST, odbiera jsona (FamilyRequestModel) - tworzy, zapisuje i zwraca nową rodzinę utworzoną na podstawie przesłanych danych.
/api/family/search - Methoda: GET, szuka rodziny na podstawie parametrów przesłanych w zapytaniu (ChilRequestModel)
/api/family/get - Metoda: GET, zwraca obiekt rodziny (Family) jako json na podstawie parametru "fatherId", który jest jednoznaczny z identyfikatorem "głowy rodziny"
/api/family/member - Metoda: GET, zwraca obiekt typu FamilyMember (Child, Father, Family) na podstawie przesłanego parametru memberType (kod typu członka rodziny) i jego ID.



Baza danych (wojewodkainteca/family:database) (PostgreSQL 9.3) - serwer bazy danych działa na porcie 5433(!) a w konterze na 5432.
Przy budowaniu bazy danych za pomocą skryptu dockerowego (Dockerfile_pgSQL) jest tworzony użytkownik <b>intecauser</b> z hasłem dostępu <b>intecauser</b> oraz baza danych inteca_db.

Aplikacja frontowa(wojewodkainteca/family:webapp) (Angular 6, Material) - usługa jest postawiona na standardowym porcie 4200 i tak samo jest w przypadku kontenera.
