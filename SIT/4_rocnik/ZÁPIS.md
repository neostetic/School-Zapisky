## <a href="./..">🔌 Počítačové Sítě (SIT) - 4. ročník - zápis</a>
- [🗒️ Soubory zápisu](./soubory)
- [🌐 LEARNIIT.tech / SÍTĚ](https://learniit.tech/site)

### Routování
- **statické** (ručné v Routovací Tabulce)
- **dynamické (DHCP)** (pomocí Routovací Protokolů)
  - **IGP** (Interior Gateway Protocol)
    - Distance-Vector
      - **RIP**, IGRP, EIGRP
    - Link-State
      - IS-IS, **OSPF** 
  - **EGP** (Exterior Gateway Protocol)
    - Path-Vector
      - BGP
- pro malé sítě stačí routování statické
- pro komunikaci s okolními sítěmi
  - gateway (adresa nejbližšího serveru)
  - dns server (překládá IP na doménová jména)

#### Nastavení adres v Linuxu
- diagnostika v linuxu
  - `ifconfig` 
  - `ip addr` 
  - `router (netstat -rn)` 
- nastavení síťových rozhrání v Debianu = `/etc/network/interfaces`
- **struktura dynamická**
```  
auto lo                   // localhost, auto znaci zapnout rozhrani pri startu systemu
iface lo inet loopback

allow-hottplug            // zapne pripojeni rozhrani pri zapojeni kabelu
iface eth0 inet dhcp
```
- **struktura statická**
```
auto lo
iface lo inet loopback

allow-hottplug
iface eth0 inet static
      address 10.0.0.1
      netmask 255.255.255.0
gateway 10.0.0.200
```
- **virtuální rozhrání**
```
up ip addr add ip:*adresa/maska* dev $IFACE label $IFACE:cislo 
down ip addr del ip:*adresa/maska* dev $IFACE label $IFACE:cislo 
```
- **DNS server** se zapisuje do `/etc/resolv.conf` : `nameserver 10.0.0.200`
  - jsou uvedeny pořadně podle důležitosti
  - direktiv **domain** a **search** - uvedení lokální a vyhledných domén
- bez DNS zjistíme názvy počítačů v `/etc/host`
  - obsahuje *ipadresu počítače* a *jmenný název* 

#### IPv4 (IPversion4)
- např.: `192.168.42.69/19`
- rozdělujeme do 5 kategorií (ABCDE (D - multicast, E - rezerva))
  - **Kategorie A** - 0-127
  - **Kategorie B** - 128-191
  - **Kategorie C** - 192-223
  - **Kategorie D** - 224-239
  - **Kategorie E** - 240-255
- privátní IP adresy
  - 10.0.0.0 - 10.255.255.255
  - 172.16.0.0 - 172.31.255.255
  - 192.168.0.0 - 192.168.255.255
  - **Loopback** - 127.0.0.0 - 127.255.255.255
  - **Lokální spojení** - 169.254.0.0 - 169.255.255.255

#### IPv6 (IPversion6)
- 128 bitů - 8 skupin po 16 bitech
- `2001:db8:8:800:200c:471a` unicast
- `ff01::101` multicast
- `::1` loopback (localhost)
- `::` nespecifikovaná adresa
- síťový prefix + adresa pc
- namá broadcast

#### VirtualBox
- NAT (vlastní)
- bridge (impostor)
- síť s hostem (uzavrena)
- vnitrni síť (oddělená)
 
### DHCP (Dynamic Host Configuration Protocol)
- rozšíření původního BOOTP protokolu
- protokol = čtyřfázový IP přidělovací proces

![image](https://user-images.githubusercontent.com/83291717/191496239-92e173f9-977b-4066-981f-2a94904e76ff.png)<br>
*proces přidělování adresy + OBNOVA*

- **DHCPACK** - příjmutí requestu klientem - zápůjční doba adresy, konfigurační parametry
- **DHCPNAK** - zamítnutí requestu DHCP serverem - pokud klient požaduje adresu z jiného subnetu (Vypršela zápůjční doba), proces jede od začátku
- **DHCPDECLINE** - zamítnutí requ klientem - ip adresy, již v síti existuje
- **DHCPRELEASE** - uvolnění ip adresy klientem
- **DHCPINFORM** - klient pošle response serveru, že již adresu má a zároveń pošle request informačních údajů
- server ukládá informace o "zapůjčených" adres
- **OBNOVA** - zopakuje se *DHCPREQUEST*, *DHCPACK*

### DNS (Domain Name System)
![image](https://user-images.githubusercontent.com/83291717/194287396-79981a19-1309-4293-9ffe-21ecae382b90.png)<br>
*strom DNS serverů*

 - mapuje jmenné názvy na IP adresy a obráceně
 - má stromovou strukturu
 - nejobecnější informace v doménovém jménu jsou vpravo (čteme zprava doleva)
 - vyhledávání v systému DNS probíhá rekuzivně
 - hledání probíhá v databázích DNS serverů (zóny), každý DNS server má na starosti určutou část stromu

#### DNS Servery
 - kořenové
 - autoratitativní
 - rekurzivní (cachovací)

#### Kořenové DNS servery
 - obsluhují kořenovou doménu "."
 - obsahují informace o adresách DNS serverů, které se starají o domény 1. úrovně
 - logický počet serverů je 13
 - fyzicky je jich mnohem více, jsou mezi sebou zrcadlené
 - označují se písmeny abecedy A-M
 - logických je jich 13, protože se všechny vejdou do 1 UDP packetu s dotazem (512 B)
 - dotaz probíhá vždy na nejbliž nejblížší kořenový server, který je zachycený anycastem
 - zátěž je mezi servery rovnoměrně rozložená

#### Autoritativní servery
 - mají na starosti konkrétní čáast DNS stromu (zónu)
 - zóny jsou uloženy v tzv. zónových souborech

#### Rekurzivní (cachovací) servery
 - vyřízují dotazy v DNS místo klienta
 - vyřízené požadavky ukládají do cache
 - snižují zátěž celého systému

#### DNS zóny
 - dopředné (forward) zóny
   - mapuje jmenné názvy na ip adresy
   - typy záznamů (SOA, NS, A, AAAA, CNAME, MX)
 - zpětné (reverse) zóny 
   - mapují IP adresy na jmenné názvy
   - typy záznamů (SOA, NS, PTR)
 - IPv4 - má největší doménu `in-addr.arpa`
 - IPv6 - má největší doménu `ip6.arpa`

#### DNS záznamy
 - **A** - mapuje název a IP adresu `www    IN    A    10.0.0.1`
 - **AAAA** - jako A, ale používá se v IPv6
 - **CNAME** - alias - jiný název pro již existující název `server    IN    CNAME    www`
 - **MX** - Mail eXchange - řídí tok elektronické pošty (email) `seznam.cz    IN    MX    10    mail.seznam.cz`
 - **NS** - Naming Server - udává DNS servery dané domény `seznam.cz    IN    NS    ams.seznam.cz`
 - **SOA** - Start Of Authority - označuje začátek zóny
```
seznam.cz    IN    SOA    primarni_jmenny_server    email_spravce
             refresh - v jakém intervalu si sekundární servey stahují
                       informace z primárního
             retry - pokud se nepovede refresh, za jak dlouho to
                     má sekundární server zkusit znovu
             expire - pokud se refresh nepovede vůbec, tak to uplynutí
                      času budou infornace na sekundarním serveru
                      neplatné
             ttl - cache (u bind v linuxu se jedná o negativní cache)
```
 - **PTR** - Pointer Record - mapuje IP adresy na jmenné názvy

#### Nástroje DNS
 - nslookup (win, linux)
 - host (linux)
 - dig (linux)

![image](https://user-images.githubusercontent.com/83291717/194294670-7188a259-a9c2-4fca-a852-dc4eabc3c045.png)<br>

```
root@debian:~# dig @a.root-servers.net www.seznam.cz
...
>> b.ns.nic.cz
```

```
root@debian:~# dig @b.ns.nic.cz www.seznam.cz
...
>> amw.seznam.cz
```


```
root@debian:~# dig @amw.seznam.cz www.seznam.cz
```

#### Konfigurace DNS Linuxu (bind)
```
root@debian:~# apt install bind9 bind9-doc bind9utils
root@debian:/etc/ind# cat db.255
root@debian:/etc/bind# cat db.127 // zpětná zóna Localhost
root@debian:/etc/bind# cat db.local
root@debian:/etc/bind# cat named.conf // propojuje vsechny konfiguracni soubory
root@debian:/etc/bind# cat named.conf.default-zones
root@debian:/etc/bind# cat named.conf.local // vlastni zony DNS
root@debian:/etc/bind# cat named.conf.options // zakladni vlastnosti DNS serveru
root@debian:/etc/bind# cat zones.rfc1918 // privátní adresy
```

- V `named.conf.options` si nastavíme naše vlastní DNS
```
root@debian:/etc/bind# nano named.conf.options
---nano---
acl "povoleno" {
         127.0.0.0/8; // localhost
         10.0.0.0/24; // nase sit
};

options {
        directory "/var/cahce/bind";
        
        forwarders {
                8.8.8.8; // google presmerovac
                8.8.4.4; 
        };
        
        dnssec-validation no;
        
        listen-on-v6 { none; };
        listen-on { 127.0.0.1; 10.0.0.1; };
        allow-recursion { povoleno; };
        auth-nxdomain no;
};
```

- A v `named.conf.local` nastavíme DNS lokálně

```
root@debian:/etc/bind# nano named.conf.local
---nano---
zone "franta.local" {
        type master;
        file "/etc/bind/zones/db.franta.local"
};

zone "0.0.10.in-addr.arpa" {
        type master;
        file "/etc/bind/zones/db.10.0.0";
};
```

- Nyní si vytvoříme zóny které jsme dávali do `named.conf.local` a nakonfigurujeme

```
root@debian:/etc/bind# mkdir zones
root@debian:/etc/bind# cp db.emty zones/db.franta.local
root@debian:/etc/bind# cp db.emty zones/db.10.0.0
```

```
root@debian:/etc/bind/zones# nano db.franta.local
```

![image](https://user-images.githubusercontent.com/83291717/194843644-8186d2fa-cddb-4826-93aa-5f02952f89c0.png)

```
root@debian:/etc/bind/zones# nano db.franta.local
```
![image](https://user-images.githubusercontent.com/83291717/194844345-05418977-0136-4737-8cbd-bfb277021601.png)


```
root@debian:/etc/bind/zones# named-checkconf // kotrola chyb (krom logickejch)
root@debian:/etc/bind/zones# named-checkzone franta.local db.franta.local
root@debian:/etc/bind/zones# named-checkzone 0.0.10.in-addr.arpa db.10.0.0
root@debian:/etc/bind/zones# systemctl restart bind9
root@debian:/etc/bind/zones# systemctl status bind9
```

```
root@debian:/etc/bind/zones# nano /etc/dhcp/dhcpd.conf
----nano----
 option domain-name-servers 10.0.0.1;
------------
root@debian:/etc/bind/zones# systemctl restart isc-dhcp-server.services
```

#### Kontrola u klienta
```
root@debian:~# cat /etc/resolv.conf
root@debian:~# nslookup 10.0.0.1
```

- Pokud `nameserver` se rovná našemu DNS, tak správně a přes `nslookup` musí obsahovat naše sítě

### Konfigurace DHCP a DNS - Single File
- [Klikni pro zobrazení konfigurace](https://github.com/neostetic/School-Zapisky/blob/main/SIT/4_rocnik/DHCPaDNS/README.md)

### Windows Server
- Služby DHCP, DNS, FileServer, MailServer
- Podstata jako v linuxu, s oldišností - cena


#### Stuktura Windows Serveru
- jádro (kernal)
  - monolitický, hybridní, mikrojádro 
- HAL (Hardware Abstraction Layer)
  - zprostředkovává komunikaji jádra s hardwarem 
- GUI (Graphical User Interface)

#### Edice Windows Server:
- **essentials** - neobsahuje CAL, pouze na fyzickej server, limit 50 zařízení a ram (64 GB)
- **standart** - obsahuje CAL, limit ram (4 TB), omezení virtuálů
- **datacenter** - obsahuje CAL, limit ram (4 TB), neomezení virtuálů pouze fyzickém serverem, co server zvládne
- **Hyper-V** - rozjezd pro virtuální serverů, pro **standart** a **datacenter**
<details>
 <summary>Tabulka Edicí</summary>
 
| Windows Server 2022 Edition | Ideal for                                             | Licensing model                    | CAL requirements[1] | Suggested Retail Price (MSRP) |
|-----------------------------|-------------------------------------------------------|------------------------------------|---------------------|----------------------------------|
| Datacenter                  | Highly virtualized datacenters and cloud environments | Core-based                         | Windows Server CAL  | $6,155                           |
| Standard                    | Physical or minimally virtualized environments        | Core-based                         | Windows Server CAL  | $1069                            |
| Essentials                  | Small businesses with up to 25 users and 50 devices   | Specialty servers (server license) | No CAL required     | $501                             |
 </details>
 
 #### CAL (Client Access License)
 - user CAL - 1 license pro uživatele
 - device CAL - 1 license pro PC na kterém pracuje víc uživatelů

#### Ovládání Windows Serveru
- příkazový řádek
  - CMD - klasický příkazový řádek (standradní příkazy + net, netsh apod.)
  - PowerShell
  - skripty - cscirpt, vbscript, powershell
  - sconfig - interaktivní textové menu
  - vzdálené přes RSAT (Remote Server Administration Toolkit)
- GUI (Grahical User Interface)
  - Windows Server Manager (jako Explorer.exe)
    - Role - základní funkcionalita srveru (DHCP server, DNS server, fileserver, webserver, mailserver) 
    - Funkce - slouží jako podpora rolí a nástrojů pro vývoj apod. (toolboxy, .NET framework apod.) 
    - Služby - procesy na pozadí zajičťují fce rolí + samostatné aplikace (DHC klient, ...)  

##### Nastavování Windows Serveru (příkazový řádek)
- přidání IP adressy
```
C:\Users\Administrator>netsh interface ip set address "Ethernet" static 10.0.0.2 255.255.255.252
```
- vyhledání příkazů
```
PS C:\Users\Administrator>Get-Command
...
PS C:\Users\Administrator>Get-NetAdapter
...
PS C:\Users\Administrator>Get-NetAdapter | fl
...
```

##### Nastavování Windows Serveru (GUI)
<img src="https://user-images.githubusercontent.com/83291717/200291121-35aefc90-f407-4e6a-b8c8-6159f1639129.png">

- Server Manager
  - <details><summary><b>Local Server</b> - Nastavování Lokálního serveru</summary><br><img src="https://user-images.githubusercontent.com/83291717/200294973-5913e83c-9013-41cd-bdf1-84ab4c5c94a4.png"></details>
  - <details><summary><b>All Servers</b> - Nastavování všech připojených serverů </summary><br><img src="https://user-images.githubusercontent.com/83291717/200297822-a836c421-fc32-4311-9c2a-58d198f1deba.png"></details>
  - <details><summary><b>File and Storage Servicies</b> - Nastavování diskovejch svazků, souborů, ...</summary><br><img src="https://user-images.githubusercontent.com/83291717/200298137-2192a971-ac43-4391-9789-08109fc30b73.png"></details>

##### Nastavování Windows IP Adresy
![image](https://user-images.githubusercontent.com/83291717/200293608-aa4fa9c4-36eb-4021-8707-b418622855cb.png)

#### Stáhnutí DHCP ve Windows Serveru
- Manage > Add Roles and Features Wizard > Server Roles (zapnout *DHCP Server*) > Install
- <img src="https://user-images.githubusercontent.com/83291717/200296278-954af966-0291-4ccd-a625-553fc5727982.png" width="400px"/>
- Skip ...
- Tools > DHCP

#### Nastavování DHCP ve Windows Serveru
![image](https://user-images.githubusercontent.com/83291717/201077380-550c4b98-0fae-45ac-a35d-439eba84ad65.png)
- Tools > DHCP > IPv4 (Right Click) > New Scope
- <img src="https://user-images.githubusercontent.com/83291717/200296883-d6648de4-48a9-447d-add4-eb3629ce2a5d.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/200297227-7debc5b7-7964-438a-bd87-27b82f96f0b0.png" width="400px">
- **Reservations** - nastavení rezervace
  - *Automaticky* - Tools > DHCP > IPv4 > Scope > Address Leases > Client (Right Click) > Add to Reservation
  - *Ručně* - Tools > DHCP > IPv4 > Scope > Reservation (Right Click) > Add New Reservation (on linux - ifdown eth0 & ifup eth0)
- **Filtry** - filtrování uživatelů, Access a Deny
  - **Tools > DHCP > Filters**
    - Deny (Right Click) > Enable
      - použití ACCESS je potřeba zadat MAC adresy všech klientů
      - Address Leases > Client (Right Click) > Add to Filter > Deny  
- **Policies** - pravidla pro uskupení klientů a dalších nastavení
- **Scope Options**
  - **Scope = rozsahová skupina IP adres klientů** 
- **Server Options**
- **Adress Pool** - základní rozsahy IP adres
  - můžeme dělat vyjímky 
- **Server Bindings** - na kterých síťových rozhrání se bude server orientovat
- **Backup & Restore** - zálohy
- **Properties** - vlasnosti
  - dá se zjistit kde jsou uloženy DHCP soubory => Log soubory
- **ad1.[nazev_databaze].local** (Right Click)
  - **Add/Remove Bindings**
  - **Backup... Restore..** - zálohování a obnova klientů
  - **All Tasks**
    - zastavení, spuštění, restartování
  - **Delete** - vyhození z okna *DHCP* (neodstraní)
  - **Properties** - vlastnisti
    - nastavení **Databázové** a **Backupových** cest
      - `C:\Windows\system32\dhcp` - obsahuje logy - odděluje se dny v týdnu
- **IPv4** - protokol (Right Click)
  - **Display Statistics** - self-explenatory
  - **New Scope**
  - **New Superscope**
  - **New Multicast Scope** - pro multicast
  - **Failover** - zajištění dostupnosti služeb
    - *Configure* a *Replicate Scope*
  - **Define User Classes** a **Define Vendor Classes** - definování značek zařízení
  - **Reconcile All Scopes** - opravuje databáze
  - **Set Predefined Options**
  - **Properties**
    - *General* - nastavování logování, aktualizace statistik
    - *DNS* - nastavování DNS záznamů
    - *Filters* - nastavování filtrování uživatelů, Access a Deny
    - *Failover* - nastavení zálohování dostupnosti; obsahuje info o druhém serveru 
    - *Advanced* - cesta DB, detekce kolizí, bindings, credeantials (jméno a heslo pro kominikaci DNS)
- **Scope**
  - **Display Statistics**
  - **Advanced > *Split-Scope*** - rozděšlení DHCP scopu na více
  - **Reconcile** - oprava DB
  - **Deactivate** - odstaví aby ho uživatelé nomohly používat
  - **Properties**
    - *General* - název
    - *DNS* - updaty mezi vlastními protokoly DNS
    - *Advanced* - nastavení protokolů (DHCP nebo BOOTP)

#### Stáhnutí DNS server ve Windows Serveru
- Manage > Add Roles and Features Wizard > Sever Roles > (zapnout *DNS Server*) > Install
- Skip...
- **Tools > DNS**

#### Nastavování DNS server ve Windows Serveru
- AD1 (Right Click) > Configure a DNS Server
- **Průvodce vytvoření**
- <img src="https://user-images.githubusercontent.com/83291717/201080506-c83dd97a-8def-4aaf-a967-526ea711be56.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201080597-f38f8d8c-a848-43bf-aaf5-2fa00d30bb5e.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201080651-c17eb353-8317-4172-ab27-a270e83545ba.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201080734-d8334a8a-8152-4a71-b949-21b158e1e0e4.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201080836-c63f2b56-084f-4eb1-9b1c-5f2d83ad4ad3.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201080907-59e417f3-c1b7-4d38-bdd1-a1c8481c1410.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201081052-eb0acd3f-2fe9-4d49-b355-c6732eb67acc.png" width="400px"> <img src="https://user-images.githubusercontent.com/83291717/201081431-52abb981-a3b7-4f19-b2a7-73e80b3fb43f.png" width="400px">
- Finish > OK
- ![image](https://user-images.githubusercontent.com/83291717/201082412-554f988e-4296-4b86-a331-4b570745908f.png)
- **Forward Lookup Zones** > SOA (Right Click)
  - **Properties**
    - *General* - Aging... - vybírá neplatné, staré záznamy 
    - *Name Server*
    - *Zone Transfers*
    - *WINS* - Samba
  - **[nazev_databaze].local** > ad1 (Double Click)
    - zaškrtnout `[ ] Update associated pointer (PTR) record` pro PTR na Reverse 
    -  ![image](https://user-images.githubusercontent.com/83291717/201083436-707a5035-a87a-49a8-a0a6-f8f2abc46822.png)
    -  *New Host, Alias, Mail Exchange* - vytvoření nových DNS záznamů
    -  *New Domain* - spravování doménové zóny
    -  *Delete* - smazání zóny
    -  *Properties* - základní příkazy, SOA záznamy, WINS, ...
- **Reverse Lookup Zones**
  -  ...
- **Conditional Forwarders** - podmíněné přesměrování
- *otestaováhí přes klient linux*
```
root@debian:~# nslookup ad1
...
root@debian:~# nslookup 10.0.0.2
...
```

#### AD (Active Directory) ve Windows
- **technologie od *miscrosoftu* pro spravování počítačové správě**
  - uživatelů
  - serverů
  - pracovních stanic 
  - sdílení složek
  - autentizačních mechanizmů
- *od verze Windows 2000*
- založena na protokolu adresové služby **LDAP** *(Light-weight Directory Active Protocol)*
- **vyžaduje DNS**
- stromová struktura - *AD schema*
- **struktura**
  - **fyzická**
    - **DC** *(domain controller)* - stará se o doménu
    - **site** 
  - **logická**
    - *Forest (les)* - bezpečnostní hranice kde jsou sdíleny objekty v rámci AD
![image](https://user-images.githubusercontent.com/83291717/203039612-ce97901c-cf7b-4779-b1c5-9b86790d3cca.png)
- **Operation Master Roles**
  - **shema master** - správa AD schématu
  - **operations naming master** - přídává/odebírá domény z lesa
  - **Relative Identifier (RID) master** - spravuje tzv. "RID bloky" (slouží pro generování SID) 
  - **Primary Domain Controller (PDC) emulator** - podpora historických systémů
  - **Infrastucture master** - řídí vztahy mezi objekty z různých domén
    - *GC (Global Catalog)* - slouží pro řízení

##### SSO (Single Sing On)
- jednotné přihlašování na vzdáleném PC 

#### Nastavování a stáhnutí AD ve Windows Serveru
- *Tools > DNS > Smazat zóny pokud potřeba*
- **Manage > Add Roles and Features > Server Roles
![image](https://user-images.githubusercontent.com/83291717/203043843-4f110a8c-6ba7-434c-886c-359a724ca1ca.png)
- **Notifikace > Promote this server to a domain controller**
- vyrobíme nový les s DHCP doménou
![image](https://user-images.githubusercontent.com/83291717/203045022-91ad3bce-1239-4f14-9dac-f96a595bb0e4.png)
- přidáme unikátní heslo `Admin123.`
![image](https://user-images.githubusercontent.com/83291717/203046209-fcb70374-948e-4dcd-9718-de0b8181d1f4.png)
- čekat na vytvoření NetBIOS domény *(vytvoří se sama)*
- čekat na požadavky *(Prerequisites Check)*
- *Install*
- čekat na restart
- podíváme se do *DNS Forward Zones*
  - pokud zóna vypadá správně, tak ji máme správně
- ![image](https://user-images.githubusercontent.com/83291717/203537796-0419e047-b210-4291-bc0f-db281c49fd8e.png)
- *Next > Commit*
- **Tools** - nástroje pro správu AD
  - **AD Administartive Center** - obdoba server manageru 
  - **AD Domains and Trusts** - nástroj pro vztahy mezi doménami
    - *Properties* - název, popisek, verze, ...
    - *Trusted* - vztahy mezi doménami
    - *Raise Domain Functional Level* - povýší verzy directory schématu
  - **AD Module for Windows PowerShell** - PowerShell modul pro řízení v příkazovém řádku
    - `Get-ADUser -filter * | sort name | select name` - zobrazí usery
  - **AD Sites and Services** - pohled na fyzickou strukturu AD
  - **ADSI Edit** - editor AD schématu
  - ***AD Users and Computers*** - HLAVNÍ NÁSTROJ PRO SPRÁVU AD
    - `franta.local` - nejvýše postavená = doména
      - `Builtin` - výcohzí skupiny které se vytvoří po instalaci AD
      - `Computers` - zde se objevují počítače přidané v doméně
      - `Domain Controllers` - doménové řadiče :)
      - `ForeignSecurityPrincipals` - objekty zabezpečené z jiných domén
      - `Managed Service Accounts` - složka pro účty které slouží pro spuštění síťových služeb (SQL, ...)
      - `Users` - výchozí složka pro uživatele + skupiny a uživatele generované po instalaci AD
    - *Pravým > New > Organization > název*
      - ![image](https://user-images.githubusercontent.com/83291717/203542043-9f2f39b8-54b7-40c5-b125-4d7148fb33ad.png)
      - *založení uživatelů* - *Pravým > New > User > vyplnit informace, heslo (Heslo123.)*
        - `[x] Password never expires` - heslo slouží pouze 30 dní, zaškrtnout aby zůstalo
        - *vlastnosti uživatelů*
          - *Acount* - příhlašovací jméno, změna domény
            - Logon Hours - nastavení přihlašovacích hodin
            - Log On To - nastavení pro přihlášení na určité PC
            - Unlock account - nastavení odemčení účtu po špatné správě účtu
            - Account Options - detailní nastavení účtu
              - Smart card - čip přihlašování PIN
            - Account Expires - nastavování splatnosti účtu
          - *Profile*
            - Profile path - cesta uložení profilu
            - Logon script - přihlašovací script... self-explanatory
          - *Remote control* - nastavování vzdáleného přistupu
          - *Remote Desktop Service Profile* - nastavení vzádelené plochy
          - *COM+* - Component Object Model
          - *Member Of* - nastavování bezpečnostních skupin uživatele
          - *Dial-in* - nastavení přístupu přes VPN
          - *Enviroment* - nastavení po spuštění
            - Client devices - nastavení připojení HW a SW na vzdálené ploše 
          - *Sessions* - kontrola přihlášení k serveru, nastavení limitu, ...
      - **Domain Controllers** - počítače
        - *vlastnosti počítačů*
          - *General*
          - *Operating System* - operační systém
          - *Member Of* - spravování zabezpečovacích skupin
          - *Delegation* - delegování práv uživatelů <sub>(nedoporučuje pan Zvěřina)</sub>
          - *Location* - doménová adresa
          - *Managed By* - nastavování správce, jeho kontaktní údaje
          - *Dial-in* - nastavení VPN na PC
      -  *Operation Masters* - 3 role - RID, PDC, Infrastructure

##### Vztah důvěry
- **vztah mezi vaší doménou a spravovací stanicí** *(tutorial)*
  - **1)** zapnout cizí Windows s **Vnitřní Sítí**
  - **2)** Průzkumník > Vlastnosti PC > Upřesnit vlastnosti systému > Název počítače
  - **3)** Změnit
    - ![image](https://user-images.githubusercontent.com/83291717/203775069-83c4a863-9395-4ee0-aec1-326bd64e54da.png)
  - **4)** Nastavit člena
    - `Název počítače:` : libovolný název 
    - `[x] Domény:` : naše doména
    - `[ ] Pracovní skupiny:` 
    - ![image](https://user-images.githubusercontent.com/83291717/203775490-76d11057-d0b8-462c-bba4-82efd2be1a51.png)
  - **5)** Přihlášení za **Administrator** *(heslo: Admin123.)*
  - **6)** Restartování
  - **7)** Jiný uživatel > Přihlásíme se za uživatela na našem Windows serveru
  - **8) Vytořili jsme vztah důvěry**
- na serveru v AD se projeví změna v **Computers**

##### Nastavení vzdáleného přístupu
- **1)** Ovládací panely > Firewall v programu Windows Defender (ne pokročilý) > Povilit aplikaci nebo funkci ...
  - ![image](https://user-images.githubusercontent.com/83291717/204261036-8397b759-de64-419a-8c16-c4dc69abab3d.png)
- **2)** Změnit nastavení
  - ![image](https://user-images.githubusercontent.com/83291717/204261204-0038f57a-2f2e-431f-a006-1a4792847ebb.png)
  - Kotrolujeme zda jsme v *Dom0n2*
- **3)** Povolit u Domény
  - `[x] Sdílení souborů a tiskáren`, `[x] Vzdálená plocha`, všechna `[x] Vzdálený správa ...`
- **4)** Na serveru *Computer* počítač přesuneme do *skola.pocitace*
  - Na PC *(pravy)* **Manage**
    - ![image](https://user-images.githubusercontent.com/83291717/204263126-900e3b4e-7948-425e-9438-8c2e34df87fc.png)
    - *Services and Application* - správa služeb a aplikací :)
    - *Local Users and Groups* - správa lokálních uživatelů PC
    - *Shared Folders*
      - *Shares* - sdílené složky
      - *Sessions* - přihlášení uživatelé sdílení
      - *Open Files* - otevřené soubory u sdíleného uživatele
    - *Event Viewer* - zobrazí co se děje na pc - Log
    - *Task Scheduler* - plánování úloh 
- ***Dostaneme se přes explorer na vzálený PC přes URL : `\\stanice\c$`****
- **5)** Nastavení vzdáleného přístupu u klienta
  - Vzdálená plocha : `[x] Umožnit vzdálené připojení k tomuto počítači`
  - Přidat uživatele : `test` : Vyhledat : Ok
- **6)** Na serveru **Remote Desktop Connection**
  - `STANICE` : More choices : `test`

##### Nastavování sdílených složek
- **přidáme si nový pevný disk k serveru** *(VirtualBox)*
- **inicializace disku**
  - Tools > Computer Management > Disc Management > Ok
  - Pravým na disk > New simple volume > Změnit name na `Data`
- **1)** Na novém disku vytvoříme strom složek
```
Data (E:)
 + data
   + verejne
```
- **2)** `verejne` > Properties
  - Sharing > Advanced Sharing > `[x] Share this folder` > Permissions
    - `Change - [x] Allow - [ ] Deny`
  - Security 
    - Edit > Add.. > `Everyone`
      - `Modify - [x] Allow - [ ] Deny`
    - Advanced > Enable inheritance > Convert ... > `Users...` > Remove
      - *Permissions* - pravomoce
      - *Share* - sdílení 
      - *Auditing* - při zapnutí loggování; větší práce pro PC
      - *Effective Access* - testovací "modelář"; ukazuje zda určitý uživatel má přístup
      - *Owner: `name` > Change* - změna vlastníka
- **Otestování složek**
  - *Windows* - `\\pocitac\cesta`
  - *Linux* - `//pocitac/cesta`

##### Nastavování scriptů po spuštění
- **Cesta** : *This PC > Local Disk (C:) > SYSVOL > sysvol > franta.local > scripts* `C:\Windows\SYSVOL\sysvol\franta.local\scripts`
  - zapneme si raději `[x] File name extensions`
- scrpitik.bat
```
net use z: \\ad1\verejne /y      // NASTAVI DISK PO ZAPNUTI SYSTEMU NA :z
```
- v **Active Directory Users and Computer** nastavíme uživateli
  - `Logon script: skriptik.bat`

##### Nastavování složek uživatelů
```
Data (E:)
 - data
   + User$
   - verejne
```
- **1)** - `verejne` > Properties
  - Sharing > Advanced Sharing... > `[x] Share this folder` > Persmissions
    - ![image](https://user-images.githubusercontent.com/83291717/205034980-c6ebba4e-1e39-41af-968d-266b8936d4e2.png)
  - Security > Advanced
    - `[Disable inheritance]`
    - Add
    - Autheticated Users
      - ![image](https://user-images.githubusercontent.com/83291717/205035530-db0c35ba-c9e0-46a6-a1e2-a2bc9025b228.png)
    - odstranit uživatele **Users**
- **2)** - Active Directory Users and Computers
  - uzivatele > `Testovaci ucet` > Properties > Profile
    - `[ ] Local path:`
    - `[x] Connect` : `Y:` - `To` : `\\ad1\User$\test` - vytvoří složku v `User$`
    - ![image](https://user-images.githubusercontent.com/83291717/205036738-73725871-b8c7-4f58-9417-7a956e9d136b.png)

##### Nastavování profilů
- **místiní** *(lokální)*
  - vytvoří se po přihlášení v `/C/Users/...` - to je takový lokální profil 
- **cestovní** *(roaming)*
  - profil který je uložený *NĚKDE* na serveru
  - *nevýhody*
    - jiné verze mezi sebou nejsou kompatibilní
- **1)** 
```
Data (E:)
 - data
   + Profile$
   - User$
   - verejne
```
- **2)** Properties
  - Sharing > Advanced Sharing... > `[x] Share this folder` > Persmissions
    - `Administrators` - Full controll
    - `Everyone` - Full controll
    - `SYSTEM` - Full controll
  - Security > Advanced
    - `[Disable inheritance]`
    - odstranění uživatelů `Users`
    - Add
      - Everyone
      - `This folder only`
      - `Show advanced options`
        - ![image](https://user-images.githubusercontent.com/83291717/205043394-9ee22778-e7b2-4886-b853-bd6006e8384e.png)
- **3)** - Active Directory Users and Computers
  - uzivatele > `Testovaci ucet` > Properties > Profile
    - `Profile path` : `\\ad1\Profile$\%username%`  

#### GPO (Group Policy Objects) politiky ve Windows
- nastavování politik
  - chování Windowsů
  - nastavování nástrojů, které se budou používat
  - nastavování hesel
  - nastavování jednotlivách aplikací
  - instalace softwarových aplikací *(vzdáleně a hromadně)*
  - instalace aktulizací a aplikací
- **závisí na AD !!!**
- *GPO Policy se ukládá do `C:\Windows\SYSVOL\sysvol\franta.local\Policies`* - obsahuje 2 politiky
  - **Default domain policy** - výchozí politika domény
  - **Default domain controlles policy** - výchozí politika na jednotlivé řadiče
- **Server Manager > Tools > Group Policy Management**
  - *Domains* > `franta.local`
    - *Domain controllers* - obsahuje základní politiky
    - *Group Policy Objects* - obsahuje všechny politiky
    - *WMI Filters* - zacílí politiky na základě hardwaru
    - *Starter GPOs* - šablony
  - *Sites*
  - *Group Policy Modeling* - simulace, jakej by měl dopad
  - *Group Policy Results* - ukazuje výsledek simulace
- **GPO se vážou na (GPO Link)**
  -  *doménu*
  -  *doménový řadič*
  -  *OU (organizační jednotky)*
  -  *Site*

##### Politiky
- `uzivatele` > (Right Click) > Create a GPO in this domain, and Link it here...
  - **Linked Group Policy Objects** - zobrazení propojených politik
  - **Group Policy Inheritance**
  - **Delegation** - nastavování přistupových práv
  - Rozkliknutí naší politiky - `test`
    - **Scope** - Linkování, ochranný a WMI filter
    - **Details** - základní informace
      - *GPO Status* - nastavuje určitou PC část a uživatelskou část
        - *PC část* - aplikuje se na PC bez ohledu Uživatele
        - *Uživatelská část* - aplikuje se Uživatele bez ohledu na PC
    - **Settings** - jaký nastavení politika obsahuje
    - **Delegation** - určuje kdo si smí tu politiku stáhnout, naaplikovat, prohlídnout
  - Nastavení politiky > (Right Click) Edit
    - **Computer** x **User Configuration**
      - **Policies**
        - *Software Settings* - slouží pro vzálenou instalaci softwaru na stanicí
        - *Windows Settings* - základní věci; scripty, ochranné prvky (hesla apod.)
        - *Administrative Templates* - nastavování ovládacích panelů, taskmanagerů, ...
      - **Preferences**
        - *Windows Settubgs* - nastavování prostředí, souborů, složek, rigistrů, ...
        - *Control Panel Settings* - nastavení lokálních uživatelů, tiskáren, služeb, ...
- `uzivatele` > (Right Click) Block Inheritance
  - jako první se blokují politiky který mají nižší prioritu (vyšší číslo)
  - politika která má vyšší prioritu, tak přepisuje nižší
    - ![image](https://user-images.githubusercontent.com/83291717/205629926-19db6110-fbf4-4fd1-9e66-93a5799b9a22.png)
- (Right Click) Enforced
  - nastaví prioritu a nemůže přepsat JI jiná politika
 
#### SAMBA protokol
- **softwarový balíček který slouží pro sdílení souborů a tiskáren**
- sdílení sériových portů
- *založená na protokolu SMB/CIFS (Service Message Block / Commonn Internet File System)*
- založena Miscrosoftem
- vznikla z důvodu kompability operačního systému **Linux** a **Windows**
- pro čistou Linux síť stačí protokol - *NFS (Network File System)*

##### Samba Komunikace
- **přímá komunikace** (doporučená)
  - *port komunikace:* `TCP/UDP 445`
  - používá *DNS* 
- **nepřímá komunikace**
  - *port komunikace:* `UDP 137,138`, `TCP 137,139`
    - `TCP/UDP 137` - jmenné služby
    - `UDP 138` - bezstavové spojení
    - `TCP 139` - stavové spojení
  - používá mezi-vrstvu *API NetBIOS*
  - **mapování názvů**
    - broadcast dotaz
    - NBNS (centrální jmenná služba) *(WINS (Windows Internet Naming Service))*

##### Samba Role
- **Standalone server**
  - není členem žádné domény
  - sám si řeší autentizaci uživatelů, ...
- **Domain member server**
  - členem určité *AD domény* nebo *Win NT 4 domény*
- **Domain controller**
  - funkce doménového řadiče
    - *PDC (Primary Domain Controller)* - z důvodu kompatibility *Win NT 4*
    - *BDC (Backup Domain Controller)* - --//--
    - *AD domain Controller*

##### Samba Daemon
- *Daemon* - služba běžící na pozadí
  - **nmbd** - jmenná služba, spracovávání názvů
  - **smbd** - zajišťuje sdílení služeb a tiskáren
  - **winbindd** - pro spolupráci s windows doménami

##### Samba Konfigurace - Linux
- **naintalujeme si balíčky** - `apt install samba smbclient`
- **otevřeme si konfiguraci smb** - `/etc/samba/smb.conf`
  - *vymažeme zbytečné komentáře*
  - ![image](https://user-images.githubusercontent.com/83291717/206170124-90bd365a-9804-492d-a992-7736507520f6.png)
  - `[global]` - základní nastavení Samby
    - `workgroup = SKUPINKA` - název skupiny
    - `interfaces = 127.0.0.0/8 eth0`
    - `bind interfaces only = yes`
    - `server role = standalone server` - nastavení roly Samby
    - `map to guest = bad user` - co se stane s uživatelem se špatnými přihlašovacími údaji
    - + `security = user` - způsob přihlášení
    - + `netbios name = server` - název serveru
    - + `server string = Samba server %v` - při použití samby se ozve TÍMHLE STRINGEM
    - + `dns proxy = no` - ne dns 👎 
  - `[homes]` - definice domovských složek uživatelů
    - `comment = Home Directories` - komentář
    - + `path = /home/shares/%S` - cesta ke složkám uživatelů
    - + (nebude potřeba) `root preexec = bash -c '[ -d /home/shares/%S ] || mkdir -m 0700 /home/shares/%S && chown %S:%S /home/shares/%S'` - script; pokud složka uživatele neexistuje, tak se složka vytvoří
    - `browseable = no` - zda se bude složka zobrazovat
    - `read only = no` - přístup pro zápis
    - `create mask = 0700` - jaká práva budou mít nově vytvořené soubory ve složce
    - `directory mask - 0700` - jaká práva budou mít nově vytvořené složky ve složce
    - `valid users = %S` - který uživatel má právo složku používat
  - `[printers]` - definice sdílených tiskáren *(nepotřebujeme)*
  - `[print$]` - definice sdílených složek ovladačů tiskáren *(nepotřebujeme)*
  - + `[verejne]`
    - + `comment = Verejna slozka`
    - + `path = /home/shares/verejne`
    - + `valid users = @users` - zavináč znamená skupina
    - + `force group = users`
    - + `create mask = 0661`
    - + `directory mask = 0771`
    - + `writable = yes`
    - ![image](https://user-images.githubusercontent.com/83291717/207025335-83bf02b2-00af-4402-ab6c-d53e8e6be0fb.png)
  - **definice sdílených složek - `[nazev]` !!!**
- **vyvoříme si složku *verejne* - `mkdir -p /home/shares/verejne`**
- **změníme práva složky *verejne***
  - `chown root:users verejne/`
  - `chmod 770 verejne/`

##### Založení uživatelů
- **`testparm`** - zkontroluje syntaxy souboru
- `useradd -s /usr/sbin/nologin -U test -G users`
  - `grep test /etc/passwd` - kontrola, zda se uživatel vytvořil 
  - `passwd test` - nastavení hesla uživatele
- `smbpasswd [parametr] [uzivatel]` - tvorba sambového uživatele
  - *parametry*
    - `-a` - add
    - `-x` - remove
    - `-e` - enable
    - `-d` - disable 
  - `pdbedit -L` - zobrazí všechny uživatele SAMBY
- `systemctl restart nmbd.service`
- `systemctl restart smbd.service`
- `systemctl status nmbd.service`
- `systemctl status smbd.service`
- `smbstatus` - 
- **přidáme do BIND zóny nový záznam**
  - ![image](https://user-images.githubusercontent.com/83291717/207030542-a46fc79f-d0d0-4402-a3d7-3c181ce8a833.png)
- `rndc reload` - restart :)
- na klientovy - `ping server`
 
