# O projekcie

### Cele:
- omówienie zagadnień związanych z organizacją pracy, na przykładach takich narzędzi jak Google Calendar, Trello oraz Obsidian;
- przedstawienie zalet i wad konceptów związanych z tzw. *plain textem*, na przykładach formatów *Markdown* oraz *JSON*;
- wprowadzenie zagadnień związanych z synchronizacją z napisanym, prostym serwerem HTTP i narzędziami zewnętrznymi, takimi jak Google Calendar oraz Github.

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

Napisz (zaimplementowany w dowolnym, prostym okienku) parser trzech znaczników Markdowna: \**dla kursywy*\*, \*\***dla pogrubienia**\*\* i ***\*\*\*pogrubionej kursywy***\*\*\* - <u>nie musisz uwzględniać sytuacji z zagnieżdżonymi znacznikami</u>. 

>Przekaż na UPEL screenshot z owego okienka z dowolnym tekstem, który w dowolny sposób sformatujesz tymi trzema znacznikami.

### Zadanie 2.

Stwórz klasę *User*, która zapewni prostą funkcjonalność dla użytkownika. Uwzględnij:
- **pola**: id, name, password; 
- **metody**: konstruktory (wystarczy domyślny), gettery i settery dla pól;
- dodaj do klasy **funkcjonalność serializacji** do pliku *.json* i deserializacji z niego (dla Javy proponujemy omówiony moduł *GSON*).

>Na UPEL wyślij screenshot zaimplementowanej klasy lub wklej jej kod.

### Zadanie 3. 

Stwórz prosty serwer *HTTP*, którego zadaniem jest jedynie nasłuchiwanie na porcie **8080** na *localhost* i odbieranie z niego żądań (dla Javy proponujemy omówiony moduł *Eclipse Jetty*). Wykorzystaj go do stworzenia obiektu klasy *User* z zadania 2., gdzie pola to:
- id = twój indeks, 
- name = twoje imię, 
- password = dowolny ciąg znaków. 

>Na UPEL przekaż treść żądania HTTP (screenshot lub wklejony tekst) odebraną przez aplikację serwera przy tworzeniu klasy *User*.

### Zadanie 4.

Korzystając z napisanych już klas prześlij przez przeglądarkę (*https://localhost:8080/\[treść]*) do serwera *HTTP* dane nowego użytkownika (**które zostaną podane na zajęciach**) - serwer niech automatycznie zserializuje nowopowstały obiekt do pliku *.json*, po czym wyświetli odpowiednio sformatowane, odebrane żądanie w okienku z zadania 1.

>Na UPEL prześlij screenshot sformatowanego (w okienku z zadania 1.), odebranego żądania *HTTP* z odpowiednim tekstem.
