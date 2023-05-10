
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
- **uveďte, jaké vrstvy pokrývá Ethernet v modelu ISO/OSI a v modelu TCP/IP**
	- **ISO/OSI** - fyzickou a linkvou
	- **TCP/IP** - síťové rozhrání
- **detailně popište ethernetový rámec**
	- **Preambule** - synchronizace hodin příjemece a odesílatele, aby počítače jeli na stejné vlně, **7B**
	- **SFD (značka)** - ***značka, která označuje zasčátek rámce***, **1B**
	- **MAC cíle** - **6B**
	- **MAC zdroje** - MAC cíle je první, kvůli rychlejšímu přenosu, **6B**
	- **Typ/Délka** - dálka pole dat, **2B**
	- **Data** - pokud jsou data příliš malá, následuje výplň (min. 46B, max. 1500B), pokud se hranice překročí, tak se zahodí nebo rozkouskuje _(fragmentuje)_
	- **CRC32 (kontrolní součet)** - ***kontrolní součet***, ověřuje intergritu dat, **4B**
	- **Mezera mezi rámci** - **12B**
- **detailně vysvětlete přístupovou metodu CSMA/CD**
	- **detekuje kolize**
	- zařízení naslouchají, jestli na vedení nevysílá někdo jiný, _jestli tam je volno_
	- pokud je vedení obsazené, tak chvilku počkají, než se uvolní a poté začnou vysílat
	- pokud se signál rozšíří po celé délce, tak při vysílaní jiného zařízění může dojít ke kolizi
	- **jak se kolize ošetřuje?**
		- uzel který vysílá, zároveň naslouchá na té síti, jestliže je všechno v pořádku a pokud dojde ke kolizi _(ze začne vysílat někdo jiný)_, tak signál znehodnotí
		- po nalezení kolize se vyšle JAM signál a počká se, dokud dané ostatní uzly nezačnou vysílat znovu
	- **jak je zajištěné to, aby nezačaly všechny uzly vysílat ve stejný čas?**
		- **čas je náhodný**, mocninou dva a náhodného čisla - _metoda stochastická_
- **jaké jsou standardy Ethernetu**
	- _podle **IEEE 802.3**_
	- **`10BASE5`** - _Původní Ethernet_ na koaxiálním kabelu, **10Mbit/s**
	- **`10BASE2`** - _Původní Ethernet_ na koaxiálním _tenkém_ kabelu, **10Mbit/s**
	- **`10BASE-T`** - _Původní Ethernet_, kroucená dvoulinka, **10Mbit/s**
	- **`100BASE-FX`** - _Fast Ethernet_ přes dvě optické vlákna
	- **`1000BASE-T`** - _Gigabit Ethernet_ s 4 páry UTP kavelu, **1000Mbit/s**
	- **`1000BASE-CX`** - _Gigabit Ethernet_, pro krátké vzdálenosti
	- **`1000BASE-SX`** - _Gigabit Ethernet_, _mnohovidové optické vlákno_, do _stovek_ metrů
	- **`1000BASE-LX`** - _Gigabit Ethernet_, _jednovidové optické vlákno_, do _desítek_ metrů
	- **`10GBASE-T`** - _Ten Gigabit Ethernet_, do 55 metrů _(kabel kategorie 6)_, do 100 metrů _(kabel kategorie 6a)_, **10Gbit/s**
	- **`40GBASE` a `100GBASE`** - optické vlákna do 10 metrů, **40-100Gbit/s**
- **uveďte rychlosti Ethernetu od historických až po nejnovější**
	- **Půbodní Ethernet** -> 10MBit/s
	- **Fast Ethernet** -> 100MBit/s
	- **Gigabitový Ethernet** -> 1000MBit/s
	- **Ten Gigabitový Ethernet** -> 10, 40, 100, 400GBit/s
	- _Full duplex_ = může odesílat a přijímat data
	- _Half duplex_ = může jenom posílat nebo přijímat data
- **co to je fyzická adresa**
	- **Fyzická/Hardwarová adresa (MAC)** - identifikátor síťového zařízení
		- `00:B0:D0:63:C2:26` - MAC adresa je oddělená dvojtečkami, šestice dvojciferných hexadecimálních čísel
		- IP adresy se váží k MAC adresám pomocí ARP; ARP pracuje na _síťové_ vrstvě a spolupracuje s _linkovu_ vrstvou
		- první polovina = výrobce
		- druhá polovina = pořadové číslo HW výrobku 
- **co to je ARP**
	- slouží v _TCP/IP_ k získání **linkové adresy síťového rozhraní** **_protistrany_** ve stejné podstíi **pomocí známé ip adresy**
	- **ARP request se pošle broadcastem na všechny hosty v síti**
	- _ARP request_ obsahuje **cílovou a zdrojovou IP adresu** a **zdrojovou MAC adresu**, jako _**cílová MAC adresa**_ je broadcast (`FF:FF:FF:FF:FF:FF`)
	- pokud ARP REQUEST dojde k požadovanému cíly, tak se odešle zpátky ARP REPLY se stejnými údaji ale na místo MAC adresy zdroje uvede svoji adresu a místo MAC adresy cíle uvede adresu ze které request přišel
	- po cestě zpátky ARP REPLY si switch zapíše do tabulky MAC adresu a příslušné rozhrání ze kterého APR REPLY přišel
	- poté se pošle `known unicast frame`, která značí že se cesta zpátky našla a doputuje tam
- **porovnejte hub a switch, u switche popište základní operace**
	- **Hub (rozbočovač)** – vezme data od jednoho, pošle je všem
	- **Switch (přepínač)** – vezme data od jednoho, pošle je žadateli
		- když na switch příjde rámec, tak když neví kam ho má poslat, tak ho pošle na všechny rozhrání (Broadcast), ale zároveň si označí odkud rámec přišel a tuto informaci si uloží do tabulky MAC adres a pak dokáže najít cestu k určitému rozhrání a při dalším posílání rámců to pošle přímo na PORT, který odpovídá adres
		- pokud má plnou tabulku, tak to odešle Broadcastem všem
		- **metody**
			- **store and forward** - příjme rámec, uloží si ho do paměti a pomocí něho kontroluje ostatní hlavičky a odesílá je do příslušného rozhrání
			- **cut-through switching** - analizuje pouze začátek rámce a tím se urychluje odesílání
			- **fragment free** - čeká na 64. byt, a tím má jistotu že nevznikla kolize
			- **adaptive switching** - automatické přepínání mezi *cut-through switching* a *store and forward*
### 4. Bezdrátový přenos
- **základy bezdrátové komunikace**
	- přenos dat, mezi dvěmi komunikujícími stranami bez použití kabelů
- **vysvětlete pojmy wifi, bluetooth**
	- **WiFi**
		- standardy _IEEE 802.11_ popisující bezdrátovou komunikaci v počítačových sítí _(též jako Wireless LAN; WLAN)_ 
		- využívá se tzv. _bezlicenční frekvenční pásmo_, proto je ideální pro budování levné ale výkonné sítě bez nutnosti kabelů
		- **Wireless Fidelity _(bezdrátová věrnost)_**
		- **Wifi slouží pro budování počítačových sítí, má větší dosah jak bluetooth**
	- **BlueTooth**
		- otevřený standard pro bezdrátovou komunikaci mezi dvěmi či více elektronickými zařízeními _(mobilní telefony, bezdrátová sluchátka, hodinky)_
		- _definována standardem IEEE 802.15.1_
		- spadá do kategorie **PAN _(Personal Area Network)_**
		- má několik verzí. dnes **5.0** 
		- **Bluetooth slouží pro osobní sítě, jako třeba propojování různých zařízení mezi sebou, např. sluchátka s mobilem**
- **vlnová délka, pásma, kanály, dělení kmitočtu**
	- pro šíření dat se využívá _elektromagnetické vlnění_, které vzniká pomocí cívky, kterou proudí elektrický proud 
	- **Vlnová délka**
		- označuje vzdálenost dvou nejbližších bodů postupného periodického vlnění
		- základní vzorec pro výpočet vlnění = **`λ = c / f`**
			- `λ` - vlnová délka  
			- `c` - rychlost světla ve vakuu (300 000 km/h)  
			- `f` - frekvence  
	- **Pásma, Kanály, Dělení kmitočtů**
		- existuje něco, čemu se říká _Elektromagnetické spektrum_, což jsou frekvence od vysílání analogového až po gamma záření, a některé části **(pásma)** jsou určeny pro provoz Wi-Fi sítí
		- **2,4 GHz**
			- 13 kanálů
			- šířka 20-40MHz
			- nesmí se překročit 100mW _EIRP_
		- **5 Ghz**
			- 3 subpásma
			- _5,150 - 5,250 GHz_ - max 200mW _EIRP_
			- _5,250 - 5,350 GHz_ - max 200mW _EIRP_ s regulací výkonu
			- _5,450 - 5,725 GHz_ - max 1W _EIRP_ s regulací výkonu a výběrem frekvencí
		- **EIRP** - _efektivní izotropický výkon_ - výkon, vyzařovaný bodovou anténou, která vyzařuje stejně _do všech směrů_
		- **1. a 2. pásmo 5 GHz** jsou pro provoz uvnitř budov
		- **3. pásmo 5 GHz** je pro venkovní přenosy, kde mají povinnou funkci detekování meteoradarů, se kterými se potom perou ale _meteoradary mají přednost_
- **uveďte a popište rozdělení bezdrátových sítí**
	- **infrastucture mode** - centrální bod _(access point)_, ke kterému se připojují klienti
	- **ad-hoc** - podobný _peer to peer_ (komunikace mezi sebou), ruční nastavení
- **základní vlastnosti, popis a použití wi-fi**
	- původním cílem **Wi-Fi** bylo zajišťovat vzájemné bezdrátové připojení přenosných zařízení a propojování na LAN
	- dnes se využívá hlavně pro připojení do sítě na internet, pomocí tzv. hotspotů
- **Fresnelova zóna**
	- tzv. rotařční elipsoid uvádějící se kolem spojnice mezi vysílající a přijímající anténou
	- nejšiřší je zóna uprostřed, která při jejím narušení má negativní vliv na kvalitu a stabilitu
- **CSMA/CA, RTS/CTS**
	- protokoly používané v bezdrátových sítích pro řízení přístupu k médiu a řešení problémů s kolizemi
	- **CSMA/CA**
		- naslouchá zda je přenosové pásmo volné a jestli někdo jiný nevysílá
		- snaží se vyhnout kolizím
	- **RTS/CTS**
		- požádá o povelní vysílat a na nějakou dobu vysílá, nikdo jiný nevysílá
- **bezpečnost bezdrátového přenosu**
	- skrýt název sítě - SSID
	- šifrování provozu
		- **WEP, WPA, WPA2, WPA3**
		- heslo od WPA3 musí mít 8 znaků
		- šifrování metodou **TKIP** nebo **AES**
	- přístup do sítě se dá zabezpečit pomocí filtrováním MAC adres
- **v praktické části nastavte a zabezpečte wi-fi router**
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
