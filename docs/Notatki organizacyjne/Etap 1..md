>Z rozpędu po polsku zacząłem - trzymajmy kod po angielsku, a nasze notatki się ewentualnie potem przetłumaczy, by było jednolicie.

- [ ] Postawienie projektu w *IntelliJ*:
	- postawienie mu *JavaFX* - tym razem żeby zadziałało, bo mi ostatnio się buntował gagatek;
	- chcemy *log4j*? Tak
	- coś jeszcze z takich bardziej podstawowych bibliotek?
	- dzielimy pracę na Gicie na branche itp.? nie wiem sam czy jest sens, choć może to najzwyczajniej ładnie wyglądać;
- [ ] Obsługa *Markdowna:*
	- ogarnięcie [tego](https://github.com/JPro-one/markdown-javafx-renderer) fajnego linku z GitHuba;
		- *jeśli starczy czasu*, to oczywiście implementacja autorska :)
	- jakieś pojedyncze testy i do przodu, nie ma co czekać;
- [ ] Zaimplementowanie *kanbanów*:
	- trzeba zaplanować obiekty *table/list* i *card*:
		- chyba, że chcemy mieć tu całą strukturę, jak w *Trello*, czyli *board* $\leftarrow$ *lists* $\leftarrow$ *cards*;
		- proponuję, żeby:
			- *jeśli zaimplementujemy board*, to żeby miał tytuł i sloty na *table/list*[^1],
			- *table/list* miał tytuł, pole na krótki opis i sloty na *card*,
			- *card* miał ***etykiety?***, tytuł i zawartość: format notatki (jak w *Obsidianie*) i pod nią komentarzy w *Markdownie* (jak na *Trello*);
		- do *card* można przypisać użytkownika (lub kilku);
		- ***==podpinanie rzeczy z GitHuba???==***
	- mają one z miejsca obsługiwać format *Markdowna*;
	- możliwe jest swobodne przesuwanie kartek pomiędzy listami;
	- **myślę, że podstawowo można tu skończyć, jak będzie czas to się wróci po więcej zabawek**;

[^1]: *board* byłby wtedy jeden na dzień w kalendarzu, czy kilka?