# "Aplikacja do zarządzania pracą nad projektami"
___
## Skład zespołu:

### Stanisław Niemczewski

<sniemczewski@student.agh.edu.pl>  

### Paweł Młodkowski

<mlodkowskip@student.agh.edu.pl>

___
## Cel projektu

Celem projektu jest stworzenie aplikacji organizacyjnej **w języku Java** (wstępnie na PC), służącej do zarządzania pracą nad projektami, w szczególności pracy zespołowej, np. nad tworzeniem oprogramowania - będzie ona mogła funkcjonować jako aplikacja stacjonarna (*offline*) oraz jako sieciowa, gdzie wykorzystamy udostępnione nam przez AGH usługi serwerów z kontami *UNIX*. 

Naszą inspiracją są istniejące już narzędzia - takie jak *Trello*, *Obsidian*, *Google Calendar* i *GitHub* - których funkcjonalności cenimy, ale które chcemy ulepszyć i połączyć tak, aby uzyskać kompletne i wygodne narzędzie do zarządzania pracą.

___
## Przedmiot demonstracji

W poniższej tabeli zilustrowaliśmy tematy, które podejmiemy tym projektem, wraz z komentarzem dotyczącym naszych motywacji:

| Zagadnienie, które chcemy przedstawić                                                                              | Motywacja naszego wyboru                                                                                                                               |
| :----------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------- |
| obsługa danych w formatach *.json*, *.ical*                                                                        | pokazanie zalet formatów przechowywania danych zrozumiałych dla ludzi, a także przekonanie do nich                                                     |
| obsługa wielu kalendarzy i ich synchronizacja (w tym z innymi, zewnętrznymi, przede wszystkim z *Google Calendar*) | chęć stworzenia jednego narzędzia, skupiającego w sobie najważniejsze funkcjonalności dotyczące zarządzania pracą, które mogą pochodzić z wielu źródeł |
| implementacja i obsługa tablic *kanban*                                                                            | pokazanie zalet i zachęcenie do wspomnianej metody planowania pracy, opartej na tablicach i przypinanych do nich kartach z tekstem                     |
| obsługa języka znaczników *Markdown*                                                                               | pokazanie zalet tzw. *plain textu*, czyli formatu zapisu, który nie będzie tracić czytelności wraz z postępem w informatyce                            |
| synchronizacja z głównymi właściwościami *GitHuba* (tj. przede wszystkim z *pull-requestami* i *branchami*)        | współczesna konieczność znajomości tego narzędzia i umiejętności pracy z nim, na jakimkolwiek stanowisku w IT, a także chęć uproszczenia tego aspektu  |
| synchronizacja sieciowa z danymi przechowywanymi na serwerze                                                       | jest to kwestia konieczna, jeśli chcemy, by to narzędzie było dostępne dla zespołów, a także by wygodnie korzystać z niego z wielu miejsc.             |
___
## Zakres demonstracji

Powyższe tematy zostaną przedstawione w większości w niepełnym, podstawowym zakresie, ponieważ dogłębna analiza jest naturalnie niemożliwa w ok. 90 minut. Należy zaznaczyć, że:
- skupimy się na wstępnym przedstawieniu zagadnień, które jako takie nie są trudne oraz na pokazaniu ich najważniejszych zalet i wad. Mówimy tutaj o *Markdownie*, formatach *.json* i *.ical* oraz tablicach *kanban* - będzie to jednak krótsza część demonstracji;
- tematy synchronizacji aplikacji z kontami na *GitHubie*, z kalendarzami *Google*, czy z pracą z serwerem są bardziej wymagające i trudniejsze technicznie do przedstawienia, więc zostanie im poświęcone nieco więcej czasu tak, aby przedstawić ogólny obraz pracy, możliwości, a także wad i zalet.
