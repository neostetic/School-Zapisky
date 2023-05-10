
## <a href="./..">🔌 Počítačové Sítě (SIT) - Maturitní otázky</a>
### 1. Vrstvové modely a základní dělení sítí
- **vrstvové modely**
	- **proč používáme vrstvové modely**
		- kvůli přenosu dat po síti se museli zavést vrstvy komunikace
		- v každé vrstvě/úrovni je jiný krok komunikace 
	- **modely ISO/OSI a TCP/IP**
		- *TCP/IP je starší než ISO/OSI*
		- ![image](https://github.com/neostetic/School-Zapisky/assets/83291717/1bc2d9e8-78e6-485a-9938-a03e9893c5e3)
		- TCP/IP
			- *Aplikační vrstva*
			- *Transportní vrstva*
			- *Síťová (IP) vrstva*
			- *Vrstva síťového rozhrání*
		- ISO/OSI
			- *Aplikační vrstva*
			- *Prezenční vrstva*
			- *Relační vrstva*
			- *Transportní vrstva*
			- *Síťová vrstva*
			- *Linková vrstva*
			- *Fyzická vrstva*
	- **popište jednotlivé vrstvy výše uvedených modelů a porovnejte je**
		- *Fyzická vrstva*
			- zajišťuje přenos jednotlivých bitů mezi příjemcem a odesílatelem
		- *Linková vrstva*
			- zajišťuje propojení dvou stanic (rámce obsahují: záhlaví, zápatí, cílová a zdrojová adresa, kontrolní výpočet)
			- cílová adresa je vždy první kvůli *switchové metodě* `cut-through switching`, která dělá přeposílá informace ještě před úplném přijetí dat
			- *zapouzdřování* je takzvané zabalování dat pro síťovou vrstvu
		- *Síťová vrstva*
		- využívají se protokoly - ARP, ICMP, IP
		- zajišťuje směrování (hledání cesty od odesílatele k přijemci přes různé cesty a prtokoly, jako jsou například RIP, OSPF, BGP, ...)
			- ARP - překládá MAC adresy na IP adresy a obráceně
			- ICMP - kontrolní řídící zprávy, `ping`
		- **router** 
	- *Transportní vrstva*
		- na transportní vrstvě fungují porty
		- zajišťuje přenos mezi jednostlivými uzly
		- provádí přenos adres transpotních adres na síťové (nestará se o směrování)
		- puživá se **TCP** nebo **UDP**
		- **TCP je spojový** a **UDP nespojový** protokol a tím pádem je TCP pomalejší oproti UDP
		- TCP kontoluje spojení, zatím co UDP nekotroluje spojení
	- *Relační vrstva*
		- zabezpečuje výměnu dat mezi aplikacemi
		- vytváření spojení, dává synchronizační značky a porty
		- například při stahování díky těchto značkách se při výpadku může najít spojení a pokračovat tam, kde se skončilo
	- *Prezenční vrstva*
		- transformuje data pro aplikace
		- zabývá se jejich strukturou
		- například ASCII kódování
	- *Aplikační vrstva*
		- slouží pro napojování aplikací a pro předání dat ostatním vrstvám
		- protokoly ***TELNET, DHCP, DNS, SMTP, FTP***
- **dělení sítí**
	- podle rozlehlosti
		- **LAN - Local Area Network** -> často doma, ve firmách, ...
		- **WAN - Wide Area Network** -> menší sítě
		- **MAN - Metropolitan Area Network** -> WiFi ve městě
		- **PAN - Personal Area Network** -> osobní sítě, Bluetooth
	- podle topologie
		- **sběrnicová** - pro malé dočasné sítě, omezená delkou a počtem kabelů a stanic
		- **hvězdicová** - přes HUB/SWITCH, při selhání jednoho selže všechno
		- **stromová** - spojení několika **hvězdicových**
	- podle provozovatele
		- **privátní** - používají se privátní adresy v IPv4 (192.168.0.0/16, 172.16.0.0/12, 10.0.0.0/8)
		- **veřejná** - používají se veřejně, například pro poskytování internetového připojení
		- **virtuální** - jedná se o pomucku na napojování několikati počítačů, aby mohly mezi sebou komunikovat, jako kdyby byly v rámci uzavřené privátní sítě... ověřují se přes **digitální certifikáty**
	- podle postavení uzlů
		- **Peer-To-Peer** - klienti komunikují přimo se sebou, s vyšším počtem se zvyčuje přenosová kapacita
		- **Klient-Server** - klienti se napojují na server a přes něj komunikují mezi sebou nebo mezi více klientama (Email, Web)
### 2. Strukturovaná kabeláž
- **vysvětlete pojem strukturovaná kabeláž, její složení**
	- metalické a optické kabely, které umožňují propojení uživatelů v rámci počítačové sítě přenosem analogového nebo digitálního signálu
- **zásady pro plánování strukt. kabeláže**
	- vždy je potřeba dražší zařízení, než je potřeba
	- potřeba plánovat do budoucna *(např. pro 20 pc budeme plánovat dvojnásobek)*
- **dělení metalických spojů a jejich značení, kategorie, použití, náhradní schéma**
	- *dříve* se používal **koaxiální kabel**, *dnes* **kroucená dvoulinka** 
	- **Stíněné**
		- *STP* - každý vodič stíněn samostatnou fólií *(S jako "every Single one")*
		- *FTP* - celý kabel je stíněn fólií *(F jako "Full; the whole")*
		- *S-STP* - STP + opleten mědí
		- *S-FTP* - FTP + opleten mědí
	- **Nestíněné**
		- *UTP* - bez stínění, samotnou fólií
		- **Kategorie**
			- *Cat 5* - 100 MHz, 100 Mbps
			- *Cat 5e* - 100 MHz, 1,000 Mbps
			- *Cat 6* - 250 MHz, 1,000-10,000 Mbps
			- ***Cat 6a*** - 500 MHz, 10,000 Mbps
			- ***Cat 7*** - 600-700 MHz, 10,000 Mbps 
			- liší se od sebe v **rychlosti přenosu**, **frekvencí**, **dosahem**, **pužitým materiálem** a **průřezy vodičů**
		- pomocí UTP lze přenášet pakety mapříklad jako jsou IP kamery, telefonní linky, čtečky, ...
	- **PoE - Power over Ethernet**
		- *napájení Ethernetem* - zařízení jsou navrženy, aby se mohly napájet přímo přes Ethernetový kabel, za pomocí Switche
	- **Propojení prvků/zařízení**
		- *Full duplex* - zařízení je schopno v jednu chvíli současně přijímat a odesílat data
		- *Half duplex* - zařízení je schopno v jednu chvíli pouze přijímat a pouze odesílat data
		- v dnešní době má většina zařízení **MDI-X**, což zajišťuje že připojený typ kabelu dostane *určitý duplex*
		- u UTP kabelů 10BASE-T a 100BASE-T
			- **pro routery, firewally a pc** 
				- **1. a 2. pin prvního zařízení s 1. a 2. pinem druhého pro získávání dat** a **pro odesílání dat se připojuje 3. a 6. pin prvního s 3. a 6. pinem druhého**
			- **pro switche** -> přesně naopak
				- pokud by switch neměl **MDI-X** tak je potřeba křižový kabel
	- **Náhradní schéma**
		- říká nám, jaké faktory se ovlivňují na kroucené dvoulince
		- délka + kroucení = kabel se chová podobně jako cívka - elektrický odpor
		- nedostatečná izolace
		- materiál degraduje
		- může se vyskytovat parazitní proud
		- *tyto veličiny nám udává veličina **IMPEDANCE** což je zdánlivý odpor vodiče*
			- odpor, indukčnost, vodivost, kapacita 
- **spojování metalických kabelů, krimpování – praktický příklad**
	- buď přímé nebo křížené
	- **konektor RJ-45**
		- ![Pasted image 20230501175321](https://github.com/neostetic/School-Zapisky/assets/83291717/01fdd378-b386-4dfb-8297-5ebf42d0b8d1)
- **optická vlákna, Snellův zákon, složení optického vlákna, používané materiály**
	- hodně tlustá izolace
	- **Fiber opric cable**
	- data se přenášejí přes **světlo** namísto elektřiny
	- několik vrstev a min. 2 vlákna
	- **Schnellův zákon**
		- paprsky se lámou vždy směrem ke kolmici a tím paprsek proudí na druhý konec
	- **složení optického vlákna**
		- ochranný obal, optické vlákno, výplňový materiál, dělící vrstva, svazek vláken, plastová býstuha
	- **materiály** - skleněné, plastové, hybridní
		- plast se využívá v podřadných přenosech, jako je například zvuková stopa
- **dělení optických vláken a jejich značení**
	- **singlemode (lepší)**
		- jádro paprsku je tenké, takže se světlo nešíří pomocí odrazů
		- na větší vzdálenosti
		- **dražší**
		- přenos *v* **desítkách Gbitů**
	- **multimode**
		- paprsek se přenáší pomocí odrazů
		- na kratší vzdálenosti, 500-600 metrů
		- **levnější**
		- přenos *na* **10 Gbitů**
- **spojování optických vláken**
	- *krimpování konektorů* - může obsahovat nepřesnosti
	- *lepící sada* - zdlouhavý proces, musí se čekat než kabel zatvrdne
	- *přes pig tail* - zakončení kabelu svářením
	- nejvýhodnějším je **sváření**, což je nejodolnější způsob
	- do switchů se zapojují optické kabely do *transceiverů*, které světelný signál převádí na elektrický signál 
### 3. Ethernet
- uveďte, jaké vrstvy pokrývá Ethernet v modelu ISO/OSI a v modelu TCP/IP
- detailně popište ethernetový rámec
- detailně vysvětlete přístupovou metodu CSMA/CD
- jaké jsou standardy Ethernetu
- uveďte rychlosti Ethernetu od historických až po nejnovější
- co to je fyzická adresa
- co to je ARP
- porovnejte hub a switch, u switche popište základní operace
### 4. Bezdrátový přenos
- základy bezdrátové komunikace
- vysvětlete pojmy wifi, bluetooth
- vlnová délka, pásma, kanály, dělení kmitočtu
- uveďte a popište rozdělení bezdrátových sítí
- základní vlastnosti, popis a použití wi-fi
- Fresnelova zóna
- CSMA/CA, RTS/CTS
- bezpečnost bezdrátového přenosu
- v praktické části nastavte a zabezpečte wi-fi router
### 5. Síťová vrstva
- vysvětlete funkci síťové vrstvy, vyjmenujte a popište služby síťové vrstvy
- detailně popište PDU používané v síťové vrstvě
- co je směrování, popište ho podrobně krok za krokem
- popište adresu IPv4 a IPv6
- pomocí zadané IP adresy a masky spočítejte parametry daného subnetu
- popište zařízení pracující na síťové vrstvě
### 6. Kyberbezpečnost – kybernetické útoky
- rozdělte kybernetické útoky do jednotlivých kategorií a popište je
  - útoky na síťovou infrastrukturu a serverové služby
  - útoky na bezdrátové sítě wifi
  - útoky na emailovou komunikaci
  - útoky na autentizační údaje
  - útoky na databázové systémy
### 7. Směrování – směrovací protokol RIP
- zařazení protokolu RIP dle velikosti sítě, principu činnosti
- verze RIP protokolu a rozdíly mezi nimi
- podrobný popis principu aktualizace směrovacích tabulek (zprávy)
- časové intervaly, nekonečná vzdálenost
- změny v topologii – split horizon, triggered update, reverse cache poison
### 8. Směrování – směrovací protokol OSPF a BGP
- Zařazení protokolu OSPF dle velikosti sítě, princip činnosti
- Popis algoritmu použitého v protokolu OSPF
- Typy OSPF oblastí (Area)
- Typy routerů v OSPF
- Kde se používá protokol BGP
- Vlastnosti protokolu BGP
### 9. Transportní vrstva
- vysvětlete význam transportní vrstvy, vyjmenujte a popište služby transportní vrstvy
- adresace v transportní vrstvě, uveďte příklad
- detailně popište PDU používané v transportní vrstvě, popište datagram a segment
- popište službu TCP a UDP, u TCP podrobně popište proces navázání a ukončení spojení
### 10. Windows Server
- struktura Windows Serveru
  - Popište strukturu serveru. Vysvětlete funkci HAL, jádra, služeb, GUI.o Co je třeba uvážit před počátkem instalace?
  - Jaký je rozdíl mezi jednotlivými edicemi serveru (essentials, standard, datacenter...)?
  - Jaké jsou možnosti ovládání Windows Serveru?
- role, funkce, služby; jejich instalace a konfigurace
  - Vysvětlete, co jsou u WS role a funkce, jaký je jejich vztah ke službám?
  - Jako příklad nainstalujte roli DHCP server. Vysvětlete všechna dílčí nastavení.
### 11. Autentifikace a důvěra v systémech AD
- Jaký je rozdíl mezi autentifikací a autorizací?
- Jak autentifikace probíhá v systémech Windows bez domény?
- Jak autentifikace probíhá v systémech Windows s AD doménou?
- Popište službu SSO v doméně AD a to, jak ovlivňuje přihlašování do dalších serverů Microsoft
(Exchange, SQL atd.)
- Popište účel a princip činnosti protokolu Kerberos v sítích s AD doménou.
- Co je Windows Vault?
### 12. Active Directory
- struktura Active Directory
  - Co je Active Directory a k čemu slouží?
  - Jaké typy objektů v AD existují, vysvětlete funkci jednotlivých organizačních jednotek,
vytvořených po instalaci AD
  - Operation master roles
- základní operace, tvorba objektů, nastavování vlastností
  - Vytvořte uživatelský účet se zadanými vlastnostmi
  - Vytvořte zabezpečovací skupinu
  - Nastavte zadanou složku jako sdílenou, definujte vlastnosti sdílení a zabezpečení, vysvětlete
význam jednotlivých nastavení
  - Co jsou zděděná oprávnění? Jak je v případě potřeby odstranit?
- uživatelské účty a skupiny
  - Místní a cestovní profil uživatele
  - Definice síťové domovské složky
  - Přiřazení skriptu uživateli
  - Zařazení do skupin, změna zařazení, výchozí skupina
### 13. Instalace a konfigurace DNS Serveru na platformě Windows Serveru
- kořenové servery
  - Jak se instaluje a konfiguruje DNS server?
  - K čemu slouží DNS server?
  - S jakými protokoly DNS server pracuje?
  - Propojení DNS serverů, nadřazené DNS servery- zóna a typ DNS záznamu
  - Zóna dopředného vyhledávání - co je, co obsahuje?
  - Zóna zpětného vyhledávání - co je, co obsahuje?
  - Typy záznamů v DNS serveru
- autoritativní servery, rekurzivní vyhledávání, cachovací DNS Server
  - Vysvětlete pojmy
- nakonfigurujte DNS server pod OS Windows 2016 Server
### 14. Instalace a konfigurace DHCP Serveru na platformě Windows Serveru
- konfigurace oboru adres
  - Co je DHCP server, k čemu slouží?
  - Jak se instaluje a konfiguruje? Proveďte!
  - Co je obor adres? Definujte!
  - Jak probíhá přidělení IP adresy, popište průběh komunikace
- výjimky, rezervace, nastavení doby zapůjčení
  - K čemu a kdy se používají výjimky?
  - K čemu a kdy se provádějí rezervace?
  - Co je doba zapůjčení?
- množina oborů
  - K čemu slouží
  - Vytvořte obor adres pro každou vnitřní síťovou kartu serveru. Jak zajistíte připojení pracovní
stanice na zadanou kartu a do předepsaného oboru adres?
### 15. Politiky GPO
- vysvětlení pojmu, místo uložení
  - Co jsou to GPO? Kam se ukládají?
  - Co je Default Domain Policy a Default Domain Controller Policy?
  - Pomocí jakých nástrojů spravujeme GPO?
- vazby, pořadí provádění GPO
  - Na jaké objekty se mohou GPO vázat?
  - V jakém pořadí se GPO vyhodnocují?
- nastavování pravidel pro uživatele a počítač
  - Co představují pravidla pro uživatele a pro počítač? Kdy která aplikujeme?
  - Vytvořte pro danou organizační jednotku GPO, která dané skupině uživatelů zajistí
předepsané nastavení. Ověřte přihlášením uživatele!
### 16. Exchange server
- popište funkce, instalaci a konfiguraci Exchange Serveru 2013
- konfigurace poštovních schránek
  - vytvoření poštovní schránky
  - konfigurace databáze
  - konfigurace limitůo konfigurace konektorů
- protokoly elektronické pošty - IMAP4, POP3, SMTP
- Outlook Web Access
- nastavení DNS
### 17. IIS
- webové služby Microsoft Serveru
- nastavení přístupu k webovým stránkám
  - propojení na AD pro ověření
- konfigurace pro statické webové stránky
- konfigurace pro aktivní webové stránky
- protokoly pro webové služby – http, https
- nastavení SSL/TLS
### 18. Konfigurace síťových rozhraní (statické, dynamické, resolvery, statické routování,
diagnostika sítě)
- Konfigurační soubory síťových rozhraní, jmenné služby, nástroje pro diagnostiku síťového
připojení, nástroje pro routování, rozdělení routovacích protokolů
- Nakonfigurujte 3 virtuální PC s dist. Debian, z nichž jedno PC bude sloužit jako router mezi
dvěma ostatními, kde bude mít každý ze zbývajících PC IP adresy z jiného rozsahu.
### 19. DHCP server
- Popište komunikaci klient vs. DHCP server, DHCP relay, včetně konfiguračních souborů.
- Nakonfigurujte 2 virtuální PC s dist. Debian, 1. PC jako DHCP server, druhé PC jako klient, oba na
virtuální vnitřní síti.
- Klient bude schopen získat konfigurační údaje z DHCP serveru
- Popište změny v konfiguračních souborech.
### 20. DNS server
- Topologie systému DNS a jeho částí (typy serverů), struktura zón, zónových souborů a DNS
záznamů.
- Nainstalujte na jedno virtuální PC s dist. Debian DNS server BIND
- Nakonfigurujte zónové soubory pro místní doménu s příponou ".local"
- Zprovozněte druhé PC, které bude jako klient využívat služby tohoto DNS serveru.
### 21. Nftables
- Základy firewallů (typy filtrů), NAT, PAT, struktura nftables, význam tabulek, řetězců, hooků,
syntaxe pravidel nftables
- Nakonfigurujte na virtuálním PC s distribucí Debian jednoduchý firewall, který bude sloužit
pouze pro toto PC- Nakonfigurujte komunikaci na protokolech icmp, tcp, udp.
### 22. Samba
- Základy protokolů SMB/CIFS (SMB, NetBIOS), role samby, funkce daemonů, struktura
nainstalované samby (konfigurační soubory, nástroje), základní části hlavního konfiguračního
souboru.
- Nainstalujte na virtuální PC s dist. Debian balík samba
- Proveďte nasdílení veřejné složky a domovských složek uživatelů pro pracovní skupinu pod Win
- Na druhém virtuálním PC s OS Win 10 otestujte funkci samby.
### 23. Web server Apache
- Základy webu, protokoly http, https, php, struktura balíku Apache a konfiguračních souborů.
- Nainstalujte na virtuálním PC balík Apache2, php
- Zprovozněte web s podporou TLS a vytvořte jednoduchou stránku typu "Ahoj světe!" s použitím
html a php.
- Na druhém virtuálním PC s OS Win 10 předveďte funkčnost stránky a popište změny provedené
v konfiguračních souborech.
### 24. Mailový server
- Základní pojmy (MTA, MDA, MUA)
- Protokoly (SMTP, POP3, IMAP)
- Konfigurační soubory Exim4, Dovecot, Squirrelmail (nebo Roundcube) a Spamassassin
- Nakonfigurujte mailový server na virtuálním PC s dist. Debian
- Nastavte poštovní server s využitím zmíněných MTA, MDA a antispamovou kontrolou.
### 25. Zabezpečení dat přenášených přes internet, základy šifrování
- Základy šifrování – symetrická vs. asymetrická šifra, hash
- Princip elektronického podpisu, rozšíření S/MIME u elektronické pošty
- Handshake protokolů SSL a TLS


<p align="right">
  <a href="./..">Go Back</a>
</p>
