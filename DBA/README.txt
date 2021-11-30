- https://dev.mysql.com/doc/refman/8.0/en/char.html


06.09.2021

Databázové systémy
------------------

- uspodářádaná soustava dat (papírová, digitální) uspořádanost <=> snadno upravovat, doploňovat dat

- předchůdci dnešních DB
- možnost uspořádání položek
- nevýhoda: prostorné, manuální operace

DĚRNÉ ŠTÍTKY
- sčítaní lidu v r. 1890 v USA

COBOL
- Commons Business-Oriented Language
- 1959 první univerzální DB jazyk
- vznikl na základě konference průmyslových a obchodních firem a ministerstva obrany
- stále se někde ... využívásf

HIERARCHICKÝ DB MODEL
- data uspořádaná ve vztahu rodič-potomek
- nedostatečné

SÍŤOVÝ DB MODEL
- doplňuje hierarchický o vztah více ku více, potomek tak může mít více rodičů
- nejznámější: IDMS od fy Culliane Corp.
- dnes se vracejí díky potřebě zaznamenat sociální vazby

1970 - KONCEPCE RELAČNÍ DB
- data uschovávána v tabulkách do řádků a sloupci
- údaje, ukládané v některých sloupcích jsou pro různé tabulky společné a slouží tak k vyjádření vzájemných vztahů, tkv. RELACÍ mezi jednotlivými datovými tabulkami
- velice plodná koncektura, základ dnešních DB
- IBM vyvinula 1. verzi jazyka SQL (Structured Query Language)

DATABÁZOVÉ SYSTÉMY
- starší (MS-DOS) - dBase, FoxPro (velmi efektivní), Paradox a další
- současnost: Microsoft Access - součást MS Office
              Miscrosoft SQL server - výkonný
              Oracle - velice výkonný
              MySQL - zejména pro tvorbu webu
              ProstgreSQL - svobodný, otevřený, Linux
              SQLite - jednoduchý. nízká výkonnost

DALŠÍ DATABÁZOVÉ SYSTÉMY
- NoSQL (ne-relační)
 -- grafové DB
 -- objektové DB
 -- ArrayDB - rastová data (Oracle GeoRaster)
- time series DB - záznam z měřících přístrojů (InfluxDB)

SŘBD (DBMS)
- Systém Řízení Bázových Dat
- softwarové vybavení, které zajišťuje práci s databází
- tvoří rozhraní mezi aplikačními programy a uleženími programy a uloženými daty
- společně s bází dat tvoří databázový systém


		      Databázový systém
	------------------
	|   __________   |
	|  /          \  |
	|  |   SŘBD   |  |            ________
	|  \__________/  |    ___    |        |
	|                |    ___    |  DATA  |
	|   __________   |    ___    |  BÁZE  |
	|  /          \  |           |________|
	|  |   data   |  |
	|  \__________/  |
	|                |
	------------------

13.09.2021

  -------------------------------------------------------------------
  |                      PŘÁKAZY NA DATABÁZE                        |
  -------------------------------------------------------------------
  |   SET sql_mode = 'ANSI_QUOTES';                                 |
  |   create database "student_jmeno.prijmeni_dbname";              |
  |   show databases;                                               |
  |   use student_jmeno...                                          |
  |   SELECT * FROM nazev limit NUM;                                |
  |   SET names 'UTF8';                                             |
  |   show create table sklady;                                     |
  |   create table vc (v VARCHAR(4), c CHAR(4));                    |
  |   INSERT INTO vc VALUES ('ab ', 'ab ');                         |
  |   SELECT CONCAT('(', v, ')'), CONCAT('(', c, ')') FROM vc;      |
  |   create table cas (id int, cas timestamp);                     |
  |   show tables;                                                  |
  |   select * from cas;                                            |
  |   insert into cas values (1,'2021-09-13 15:20:00.534641';       |
  |   create table autoinc (id serial, kod char(2));                |
  |   insert into autoinc (kod) VALUES ('cz'),('sk'),...;           |
  |   delete from autoinc (kod) ID (5);                             |
  |   UPDATE vyrobky SET z_dovozu=True WHERE cislo=82;              |
  |   DELETE FROM vyrobky WHERE cislo=82;                           |
  |   SELECT cislo,vystaveno,splatnost,zaplaceno FROM faktury WHERE is not zaplaceno=NULL;
  |   SELECT * FROM vyrobky INNER JOIN sklady on sklady.cislo=vyrobky.sklad ORDER BY cena ASC;
  |   SELECT * FROM zamestnanci ORDER BY pocet_deti DESC LIMIT 6;   |
  |   SELECT cislo AS 'Objednaci kod', nazev AS 'Komodita' FROM vyrobky;
  |   SELECT * FROM vyrobky WHERE cena > 50;                        |
  |   SELECT ... OFFSET [num]                                       | 
  |   SELECT * FROM obraty WHERE year(datum) <= 2018;               |
  |   SELECT DISTINCT sklad FROM vyrobky WHERE z_dovozu = 1;        |
  |   SELECT cislo,nazev,cena FROM vyrobky WHERE cena BETWEEN 20 AND 50;
  |   SELECT prijmeni, jmeno, pocet_deti FROM zamestnanci WHERE pocet_deti IN (1,3);
  |   SELECT * FROM obraty WHERE datum in ('2018-02-18', '2018-02-22');
  |   SELECT * FROM vyrobky WHERE nazev LIKE 'Pivo%';               |
  |   SELECT nazev FROM vyrobky WHERE nazev RLIKE '^[TR].*';        |
  |   SELECT DATE_ADD('2017-06-15', INTERVAL 10 DAY);               |
  |   SELECT id, faktura, cislo_vyrobku, CASE WHEN castka > 10000 THEN castka-castka/10 ELSE castka END AS 'cena se slevou' from polozky;
  |   SELECT nazev, cena, CASE WHEN z_dovozu THEN 'Zahranicni' ELSE 'Domaci' END AS 'puvod_vyrobku' FROM vyrobky;
  |   SELECT CAST('200105' AS FLOAT);                               |
  |   SELECT pocet_deti, IFNULL(pocet_deti, 0) FROM zamestnanci;    |
  |   SELECT nazev, cena, IF(cena < 50, 0.1, 0.2)*cena AS clo FROM vyrobky;
  |   SELECT * FROM faktury WHERE YEAR(vystaveno) = 2018;           |
  -------------------------------------------------------------------

  - mysql -h vydb1.spsmb.cz -p -u jan.polacek student_jan.polacek_borek < X:\stemberk\verejne_zaci\tabulky_UTF8_MySQL.sql
    - importování/přihlašování tabulek/souborů


Struktura DB a Tabulek
----------------------

 - pojmy řádek (row), sloupec
 - číselné typy: (MySQL)
                 TINYINT (8), SMALLINT (16), MEDIUMINT (24), INT (32), BIGINT (64)
                 FLOAT (32) (+-3,4.10^38), DOUBLE (64) (+-1,8.10^308)
                 DECIMAL (m, d) m:1-65, d: 0-30, kde m = počet znaků, d = počet desetinných míst
 - textové typy: (N)CHAR (MAX) - vždy alokuje MAX počet znaků;
                 (N)VARCHAR (MAX) - alokuje potřebný počet znaků + 1¨
                 (N)TEXT - rozsáhlé údaje (N) = UNICODE
                 BINARY, VARBINARY - totéž, ale pro binární hodnoty
 - datum a čas: (MySQL)
                DATETIME 'YYYY-MM-DD hh:mm:ss', rozsah '1000-01-01 00:00:00' - '9999-12-31 23:59:59'
                TIMESTAMP 'YYYY-MM-DD hh:mm:ss,[fraction]', rozsah '1970-01-01 00:00:00.000000' - 'XXXX-XX-...'
                ...nestihl jsem
 - logický typ bit: TRUE / FALSE
 - index v tabulce - klíč (key)
   - dopňující informace zaznamenávající pořdí řádky
   - možné si jej představit jako další skrytý sloupcec:

          |     mesto      | index |
          |----------------|-------|
          | Mladá Boleslav |   2   |
          | Aš             |   1   |
          | Pardubice      |   3   |

    - zpomaluje ukládaní dat (INSERT)
    - výrazně urychluje ostatní operace, zejména SELECT
    - regulérní - hodnoty v indexovém sloupci se mohou opakovat
    - unikátní (UNIQUE) - hodnoty i indexovaném sloupci se nemohou opakovat

        " CREATE [UNIQUE]INDEX ID_vc_v ON vc (v) "

 - primární klíč
   - PK - primary key
   - unikátní inde, pro každou tabulku max. jeden
   - zvýšeníá efektivity pro práci s tabulkou
   - nezbytné pro tvorbu relací mezi tabulkami
   - většinou nutnost pro úpravy, či mazání řádků
 - hodnota NULL
   - NULL při tvorbě vzorce dává NULL výsledek
   - NULL != NULL (NULL == NULL) = False
   - NULL a "" je rozdíl pro textové typy
   - výchozí hodnotou pro všechny sloupce, možné jí zakázat (záznam nepůjde uložit) nebo změnit (např. 0, či funkce ...*šéfe, jste na mě nějak rychlej*)
 - automatické číslování
   - vhodné pro PK
   - při vkládání hodnot tak máme jedinečný (neopakující se) identifikátor řádků
   - čísla v totmto sloupci jednou vymazaných řádků se již nepoužijí
   - typ SERIAL je alias pro:

        " BIGINT UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE "

 - SELECT * FROM vyrobky INNER JOIN skupiny ON skupiny.cislo = vyrobky.skupina;
   - * => vyrobky.nazev,vyrobky,cislo
   - propojeni tabulek


MySql - WEBOVÉ ROZHRÁNÍ
-----------------------
 - https://vydb1.spsmb.cz/phpmyadmin
 - DML (Data Modification Language)
   - příkazy pro práci s daty

 - ... LIKE '%' - jakýkoliv řetězec
            '_' - jakýkoliv znak
 - .. RLIKE '*' - 0 a více opakování
            '+' - 1 a více
            '?' - 0 nebo 1 výskyt
          [a-z] - množina znaků

 - ROUND(x, [kolik_desetinych_mist])
 - DATE_ADD('2017-11-09', INTERVAL 10 DAY)
 - DATEIFF(CURDATE(), splatnost)


 - %Y - rok
 - %y - rok bez stoleti
 - ...
