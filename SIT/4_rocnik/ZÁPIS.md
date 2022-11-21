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

##### Stáhnutí DHCP ve Windows Serveru
- Manage > Add Roles and Features Wizard > Server Roles (zapnout *DHCP Server*) > Install
- <img src="https://user-images.githubusercontent.com/83291717/200296278-954af966-0291-4ccd-a625-553fc5727982.png" width="400px"/>
- Skip ...
- Tools > DHCP

##### Nastavování DHCP ve Windows Serveru
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

##### Stáhnutí DNS server ve Windows Serveru
- Manage > Add Roles and Features Wizard > Sever Roles > (zapnout *DNS Server*) > Install
- Skip...
- **Tools > DNS**

##### Nastavování DNS server ve Windows Serveru
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

##### AD (Active Directory) - jakoby teorie
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

##### Nastavování a stáhnutí AD ve Windows Serveru
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
