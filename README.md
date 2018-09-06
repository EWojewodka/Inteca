<b><h3>Jak uruchomić wszystki usługi?</h3></b> <br/>
Z poziomu terminala wywołaj komendę "<b>mvn install</b>" - w zależności od uprawnień użytkownika do aplikacji Docker i Docker-compose może być niezbędne użycia <b>"sudo"</b>.
Komenda zarówno pobierze pobierze zależności mavena, zbuduje aplikację jak i pobierze zadockowane obrazy mikroserwisów z hub.docker.com (wojewodkainteca/family) jak i je odpali za pomocą skryptu docker-compose.yml, który znajduje się w projekcie API. </br>

Całość projektu składa się z trzech usług: </br>
API(wojewodkainteca/family:api) (java8, maven, spring boot, JDBC) - usługa uruchamia się na porcie 8080 i na tym samym porcie działa w kontenerze. </br>
Usługa korzysta z prostego, autorskiego ORMa (inspirowany na Hibernacie) i CRUDa który pośredniczy w komunikacji z drugą usługą.</br>
Aplikacja wykorzytuje również mechanizm <b>FlyWay</b>, który zarządza odpalanymi skryptami (w tym przypadku jednym skryptem) bazodanowym (resources/db/migration).</br>
Wykaz dostępnych usług świadczonych przez API to:</br></br>
<b>/api/family/create</b> - Methoda: POST, odbiera jsona (FamilyRequestModel) - tworzy, zapisuje i zwraca nową rodzinę utworzoną na podstawie przesłanych danych.</br></br>
<b>/api/family/search</b> - Methoda: GET, szuka rodziny na podstawie parametrów przesłanych w zapytaniu (ChilRequestModel)</br></br>
<b>/api/family/get</b> - Metoda: GET, zwraca obiekt rodziny (Family) jako json na podstawie parametru "fatherId", który jest jednoznaczny z identyfikatorem "głowy rodziny"</br></br>
<b>/api/family/member</b> - Metoda: GET, zwraca obiekt typu FamilyMember (Child, Father, Family) na podstawie przesłanego parametru memberType (kod typu członka rodziny) i jego ID.</br></br>



Baza danych (wojewodkainteca/family:database) (PostgreSQL 9.3) - serwer bazy danych działa na porcie 5433(!) a w konterze na 5432.
Przy budowaniu bazy danych za pomocą skryptu dockerowego (Dockerfile_pgSQL) jest tworzony użytkownik <b>intecauser</b> z hasłem dostępu <b>intecauser</b> oraz baza danych inteca_db.</br>
</br>
Aplikacja frontowa(wojewodkainteca/family:webapp) (Angular 6, Material) - usługa jest postawiona na standardowym porcie 4200 i tak samo jest w przypadku kontenera.</br>
