# Treść zadania

Należy stworzyć program w języku Java czytający kursy walut USD/EUR z portalu stooq.pl
Co minutę program powinien zaciągnąć aktualny stan z adresu https://stooq.pl/q/l/?s=usdeur&f=sd2t2ohlc&h&e=csv i zapisać dane jako obiekt.
Kolumna „Zamkniecie” oznacza kurs w danym momencie i na niej należy się skupić.
Program powinien działać przez 5 minut i wypisać największy wzrost kursu w stosunku do poprzedniego odczytu i przedział czasu w którym ten wzrost nastąpił
Program powinien mieć też drugi tryb zależne od parametru (podanego w dowolnej formie), który będzie analizował dane historyczne.
Dane historyczne dostępne są pod adresem https://stooq.pl/q/d/l/?s=usdeur&i=d i należy je wczytać jednorazowo. Następnie należy wypisać największy wzrost kursu w odniesieniu do poprzedniego rekordu (nadal interesuje nas tylko kolumna „Zamknięcie”) wraz z przedziałem czasu w którym ten wzrost nastąpił


## Środowisko

- Zmienna środowiskowa JAVA_HOME musi być skonfigurowana na JDK 17

## Uruchomienie oraz konfiguracja

Do zainstalowania zależności:
```bash
mvn dependency:resolve

```
Po zainstalowaniu zależności:
```bash
mvn clean compile test exec:java

```
Konfiguracja projektu znajduje się w folderze resources pliku app.properties. W tym pliku mamy możliwość konfiguracji trybu działania programu oraz ścieżek URL.

-  analyticType=1 -> pierwszy tryb 
-  analyticType=2 -> drugi tryb 


```
currencyCsvUrl = https://stooq.pl/q/l/?s=usdeur&f=sd2t2ohlc&h&e=csv  <= URL DO PLIKU CSV Z POJEDYNCZYM KURSEM 
historicalCurrencyCsvUrl = https://stooq.pl/q/d/l/?s=usdeur&i=d <= URL DO PLIKU CSV Z HISTORYCZNYMI KURSAMI
analyticType = 1 

```

