# O projekcie

### Cele:
- omówienie zagadnień związanych z organizacją pracy, na przykładach takich narzędzi jak Google Calendar, Trello oraz Obsidian;
- przedstawienie zalet i wad konceptów związanych z tzw. *plain textem*, na przykładach formatów *Markdown* oraz *JSON*;
- wprowadzenie zagadnień związanych z synchronizacją z napisanym, prostym serwerem HTTP.

### Użyliśmy:
- **Javy (Maven build)** z zewnętrznymi modułami: *JavaFX*, *CalendarFX*, *ControlsFX*, *CommonMark*, *Eclipse Jetty*, *GSON*.
- IntelliJ IDEA,
- (łącznie) trzech szarych komórek.
___
# Setup

Aby odpalić projekt należy:

1. Pobrać go z Githuba i otworzyć w IDE dla Javy (rekomendujemy IntelliJ IDEA);
2. Wykonać *reload Mavena* i w razie potrzeby ręcznie sprawdzić czy wszystkie moduły są widoczne w *classpath* lub *modules* - wszystkie potrzebne biblioteki są zawarte już w projekcie;
3. (*dla wygody, aby nie tworzyć niepotrzebnie osobnego projektu*) utworzyć własny moduł w folderze *src/main/java*, w którym będziesz wykonywać zadania.

___
# Zadania

>*Zadania można wykonać w dowolnym języku, ale ze względu na prezentację materiały są przygotowane pod Javę.*

### Zadanie 1.  

Zaimplementuj proste okienko, które będzie w poprawny sposób renderować tekst w *Markdownie* i *HTMLu* - dla Javy rekomendujemy użyć omówionych na prezentacji elementów *JavaFX*, *CommonMarka* oraz *WebView*.

>Przekaż na UPEL screenshot z owego okienka z dowolnym tekstem, który w dowolny sposób sformatujesz co najmniej trzema znacznikami.

### Zadanie 2.

Stwórz klasę *User*, która zapewni prostą funkcjonalność dla klasy użytkownika. Uwzględnij:
- **pola**: id, name, password; 
- **metody**: konstruktory (wystarczy domyślny), gettery i settery dla pól;
- **funkcjonalność serializacji** do pliku *.json* i deserializacji z niego (dla Javy proponujemy omówiony moduł *GSON*).

Nie musisz implementować więcej, ani implementować hashowania hasła, choć zachęcamy do eksperymentów.

>Na UPEL wyślij screenshot zaimplementowanej klasy lub wklej jej kod.

### Zadanie 3. 

Stwórz prosty serwer *HTTP*, którego zadaniem jest jedynie nasłuchiwanie na porcie o wybranym numerze na *localhost* i odbieranie z niego żądań POST (dla Javy proponujemy omówiony moduł *Eclipse Jetty*). Wykorzystaj go do stworzenia obiektu klasy *User* z zadania 2., gdzie pola to:
- id = twój indeks, 
- name = twoje imię, 
- password = dowolny ciąg znaków. 

Wystarczy, że ustawisz na nim prosty handler, który przy odebraniu poprawnego żądania odpowie kodem 200, a na wszystko inne w domyślny sposób.

>Na UPEL przekaż screenshot wysłanego żądania HTTP widocznego z przeglądarki, gdzie będzie także widać odpowiedź serwera.

### Zadanie 4.

Korzystając z napisanych już klas prześlij przez inspektora przeglądarki do serwera z zadania 3. dane nowego użytkownika (**które zostaną podane na zajęciach**) - serwer niech automatycznie zserializuje nowopowstały obiekt do pliku *.json*, po czym wyświetl odpowiednio sformatowane, odebrane dane w okienku z zadania 1.

>Na UPEL prześlij screenshot okienka z odebranymi i odpowiednio sformatowanymi danymi użytkownika.
