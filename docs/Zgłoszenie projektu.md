# Aplikacja do zarządzania projektami

## Skład zespołu:

### Stanisław Niemczewski

<sniemczewski@student.agh.edu.pl>  

### Paweł Młodkowski

<mlodkowskip@student.agh.edu.pl>

## Cel projektu

> Stworzenie aplikacji organizacyjnej *w języku Java*, służącej do zarządzania projektami (szczególnie zespołowej, np. nad tworzeniem oprogramowania);
### Etap 1 - podstawowa architektura po stronie klienta

- Tworzenie i edycja kalendarza z wydarzeniami w nim, wraz z integracją z popularnymi kalendarzami online, np. z kalendarzem Google;
- Tworzenie i edycja tablic *kanban*;
- Obsługa notatek i opisów we wszystkich modułach zgodnie z otwartym standardem Markdown;
-  Przechowywanie danych użytkowników lokalnie, w czytelnych dla ludzi standardach, jak JSON lub TOML;
- Zadania wraz z integracją z resztą modułów aplikacji, systemem kontroli wersji Git, oraz serwisem GitHub, m.in.:
	- "Przypinanie" konkretnych *branchy*, *pull requestów* lub *issues* - zależnie od ich stanu zadania będą automatycznie oznaczane jako wykonane;
	- Dodawanie zadań to tablic *kanban*;
	- Przypisywanie użytkowników do zadań;
	- Dodawanie dat rozpoczęcia i zakończenia zadania, które zostaną automatycznie dodane do kalendarzy przypisanych do danego zadania użytkowników;
- Podstawowy interfejs graficzny

### Etap 2 - obsługa wielu użytkowników poprzez centralny serwer

- Współdzielenie przestrzeni roboczej przez wielu użytkowników z tego samego zespołu;
- Serwer HTTPS odbierający i wysyłający dane od użytkowników w celu synchronizacji klientów;
- Obsługa uprawnień użytkowników;
- Nasłuchiwanie komunikatów o zmianie przestrzeni roboczej przez klienta

### Etap 3 - interfejs graficzny i szyfrowanie

- Nowoczesny interfejs graficzny klienta;
- **Opcjonalnie:** funkcjonalność szyfrowania przechowywanych lokalnie danych[^1]

[^1]: Wstępnie będzie to RSA, może się to zmienić (o ile w ogóle to wyjdzie).

## Przedmiot demonstracji

- [ ] ⏫ ➕ 2024-03-04 TODO przedmiot demonstracji
	- [ ] Zagadnienia jakie zostaną wykorzystane i będą przedstawione:
		- obsługa danych w formatach JSON, TOML
		- elegancka obsługa *plain textu*, na przykładzie Markdowna
		- obsługa kalendarzy i ich integracja sieciowa (?)
		- synchronizacja z GitHubem (???)
		- jakaś automatyzacja zadań (???)[^2]
	- [ ] Dlaczego chcemy je przedstawić?
	- [ ] *Ewentualnie:* Inspiracja? - doświadczenia z trello wystarczą xD

[^2]: Czy to na pewno wystarczy na dobrą prezentację? XD
## Zakres demonstracji

- [ ] ⏫ ➕ 2024-03-04 TODO zakres demonstracji
	- Co i dlaczego ***realnie*** przedstawimy (i zdążymy przedstawić);
	- Plusy danych rozwiązań/podejść:
		- ...
	- Minusy danych rozwiązań/podejść:
		- ...
	- Ograniczenia aplikacji jako takiej:
		- ...
