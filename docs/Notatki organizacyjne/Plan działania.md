>*Myślę, że te zadania są poukładane w miarę po kolei, od najbardziej podstawowych i najbardziej potrzebnych na początku i coraz bardziej "na zewnątrz"*.
### Etap 1.: Podstawowa architektura po stronie klienta

- Obsługa notatek i opisów we wszystkich modułach zgodnie z otwartym standardem *Markdown*;
- Tworzenie i edycja tablic *kanban* (raczej całkowita implementacja?);
- Tworzenie i edycja "kalendarza"[^1] z wydarzeniami w nim, z podpinaniem do niego tablic *kanban* oraz z obsługą (poprawny zapis i odczyt) *Markdowna*;
- Przechowywanie danych dotyczących poszczególnych kalendarzy użytkowników lokalnie, w czytelnych dla ludzi standardach, jak *JSON* lub *TOML*;
- Integracja kalendarzy lokalnych z popularnymi kalendarzami online, np. *Google Calendar*;
	- *Czy tu już zrobimy ciągłe nasłuchiwanie i stałą synchronizację? Jeśli tak, to trzeba będzie od razu ogarnąć działanie aplikacji offline vs. online.*
- Implementacja zadań wraz z integracją z resztą modułów aplikacji, systemem kontroli wersji Git, oraz serwisem GitHub, m.in.:
	- "Przypinanie" konkretnych *branchy*, *pull requestów* lub *issues* - zależnie od ich stanu zadania będą automatycznie oznaczane jako wykonane, w trakcie lub po prostu będzie pokazany ich ostatni status?;
	- Dodawanie zadań to tablic *kanban*;
	- Przypisywanie użytkowników do zadań (*tutaj jeszcze tylko lokalnie*);
	- Dodawanie dat rozpoczęcia i zakończenia zadania, które zostaną automatycznie dodane do kalendarzy przypisanych do danego zadania użytkowników (z *Googlem* może być w tej kwestii problem, miejmy to z tyłu głowy);
- Podstawowy interfejs graficzny, który umożliwi po prostu niebolesne poruszanie się po aplikacji i testowanie jej.
### Etap 2.: Implementacja centralnego serwera i obsługiwanie poprzez niego wielu użytkowników, wstępna synchronizacja

- Zaplanowanie i utworzenie formy "zespołów", a także uprawnień, jakie w takim mogą istnieć (raczej w prostej formie, bez przesady);
- Postawienie serwera *HTTPS*[^2] przechowującego, a także wymieniającego dane użytkowników z klientami w celu synchronizacji między nimi:
	- stworzenie i przygotowanie struktury serwera jako takiej;
	- ogarnięcie własnej formy zapytań, nadawania i weryfikacji uprawnień?
	- łączność poprzez wifi;
- Nasłuchiwanie komunikatów o zmianie przestrzeni roboczej przez klienta i wdrażanie takowych;
- Współdzielenie przestrzeni roboczej przez wielu użytkowników z tego samego zespołu jednocześnie;
- Dostosowanie GUI do nowych funkcjonalności;
### Etap 3.: Bardziej zaawansowany interfejs graficzny i szyfrowanie

- Nowoczesny interfejs graficzny klienta - żeby był bardziej fancy ~~pluję na frontend~~;
- Ostatnie szlifowanie i ewentualnie optymalizacja niektórych funkcjonalności, jeśli będzie taka możliwość;
- **Opcjonalnie:** funkcjonalność szyfrowania przechowywanych lokalnie danych[^3]\;
- **Opcjonalnie:** obsługa kluczy SSH[^4];

[^1]: Takowy jest de facto całą przestrzenią roboczą, ale dla wygody nazwany będzie jeszcze kalendarzem.
[^2]: Wstępnie, pewnie będzie wymagać weryfikacji i/lub lekkich zmian (nie znamy się na tym jeszcze), ale to się okaże.
[^3]: Wstępnie będzie to RSA, może się to zmienić (o ile w ogóle to wyjdzie).
[^4]: Marzenie, raczej nierealne, ale bardzo chciałem to napisać.