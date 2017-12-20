#Warsztaty_2

Celem	warsztatów	jest	napisanie	obiektowej,	bazodanowej	warstwy	aplikacji	dla	szkoły
programowania.
Aplikacja	będzie	zawierać	częściowy	wycinek	potencjalnych	funkcjonalności	-
przechowywanie	rozwiązań	dla	zadań	wykonywanych	przez	kursantów.

Program	1	UserManager-	zarządzanie
użytkownikami
Po	wpisaniu	i	zatwierdzeniu	odpowiedniej
opcji	program	odpyta	o	dane:
Program	po	uruchomieniu	wyświetli	na
konsoli	listę	wszystkich	użytkowników.
add	-	wszystkie	dane	występujące	w
klasie	User	bez	id
edit	-	wszystkie	dane	występujące	w
klasie	User	oraz	id
delete	-id	użytkownika	którego	należy
usunąć
Następnie	wyświetli
"Wybierz	jedną	z	opcji:"
add	-	dodanie	użytkownika
edit	-	edycja	użytkownika
delete	-	edycja	użytkownika
quit	-	zakończenie	programu
Po	wykonaniu	dowolnej	z	opcji,	program
ponownie	wyświetli	listę	danych	i	zada
pytanie	o	wybór	opcji.
W	przypadku	quit	-	program	zakończy
działanie.

Program	2	ExcerciseManager-	zarządzanie	zadaniami
Po	wpisaniu	i	zatwierdzeniu	odpowiedniej
opcji	program	odpyta	o	dane:
Program	po	uruchomieniu	wyświetli	na
konsoli	listę	wszystkich	zadań.
add	-	wszystkie	dane	występujące	w
klasie	Exercise	bez	id
edit	-	wszystkie	dane	występujące	w
klasie	Exercise	oraz	id
delete	-id	zadania	które	należy	usunąć
Następnie	wyświetli	w	konsoli	napis
"Wybierz	jedną	z	opcji:"
add	-	dodanie	zadania
edit	-	edycja	zadania
delete	-	edycja	zadania
quit	-	zakończenie	programu
Po	wykonaniu	dowolnej	z	opcji,	program
ponownie	wyświetli	listę	danych	i	zada
pytanie	o	wybór	opcji.

Program	3	GroupManager-	zarządzanie	grupami
Po	wpisaniu	i	zatwierdzeniu	odpowiedniej
opcji	program	odpyta	o	dane	i	wykona
odpowiednią	operacje:
Program	po	uruchomieniu	wyświetli	na
konsoli	listę	wszystkich	grup.
Następnie	wyświetli	w	konsoli	napis
add	-	wszystkie	dane	występujące	w
klasie	Group	bez	id
edit	-	wszystkie	dane	występujące	w
klasie	Group	oraz	id
delete	-id	grupy	którą	należy	usunąć
"Wybierz	jedną	z	opcji:"
add	-	dodanie	grupy
edit	-	edycja	grupy
delete	-	edycja	grupy
quit	-	zakończenie	programu
Po	wykonaniu	dowolnej	z	opcji,	program
ponownie	wyświetli	listę	danych	i	zada
pytanie	o	wybór	opcji.


Program	UserManager	-	program użytkownika
Program	po	uruchomieniu	wyświetli	w
konsoli	napis
Program	przyjmie	jeden	parametr
podawany	podczas	uruchamiania	z	konsoli
lub	IDE,	który	będzie	symbolizował
identyfikator	użytkownika.
"Wybierz	jedną	z	opcji:"
view	-	przeglądanie	swoich	rozwiązań
add	-	dodawanie	rozwiązania

Dla	uproszczenia	przyjmujemy	że	dodanego
rozwiązania	nie	możemy	usuwać,	ani
edytować.
Po	wybraniu	odpowiedniej	opcji	program
odpyta	o	dane	i	wykona	odpowiednią
operacje:
add	-	wyświetli	listę	zadań	do	których
użytkownik	nie	dodał	jeszcze
rozwiązania,	a	następnie	odpyta	o	id
zadania	do	którego	ma	zostać	dodane
rozwiązanie.
W	przypadku	próby	dodania	rozwiązania	do
zadania,	które	już	istnieje	program	ma
wyświetlić	komunikat.
Pole	updated	-	wypełni	się	automatycznie,
użytkownik	ma	zostać	odpytany	o
rozwiązanie	zadania.
W	przypadku	quit	-	program	zakończy
działanie.


