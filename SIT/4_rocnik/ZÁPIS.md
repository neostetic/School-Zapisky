## <a href="./..">🔌 Počítačové Sítě (SIT) - 4. ročník - zápis</a>
- [🗒️ Soubory zápisu](./soubory)

### Routování
 - **statické** (ručné v Routovací Tabulce)
 - **dynamické** (pomocí Routovací Protokolů)
   - **IGP** (Interior Gateway Protocol)
     - Distance-Vector
       - **RIP**, IGRP, EIGRP
     - Link-State
       - IS-IS, **OSPF** 
   - **EGP** (Exterior Gateway Protocol)
     - Path-Vector
       - BGP  
 
### IPv4
- Rozdělujeme do 5 kategorií (ABCDE (D - multicast, E - rezerva))
  - **Kategorie A** - 0-127
  - **Kategorie B** - 128-191
  - **Kategorie C** - 192-223
  - **Kategorie D** - 224-239
  - **Kategorie E** - 240-255
- Privátní IP adresy
  - 10.0.0.0 - 10.255.255.255
  - 172.16.0.0 - 172.31.255.255
  - 192.168.0.0 - 192.168.255.255
  - **Loopback** - 127.0.0.0 - 127.255.255.255
  - **Lokální spojení** - 169.254.0.0 - 169.255.255.255

### IPv6
- 128 bitů - 8 skupin po 16 bitech
- `2001:db8:8:800:200c:471a` unicast
- `ff01::101` multicast
- `::1` loopback (localhost)
- `::` nespecifikovaná adresa
- síťový prefix + adresa pc
- namá broadcast

### VirtualBox
- NAT (vlastní)
- bridge (impostor)
- síť s hostem (uzavrena)
- vnitrni síť (oddělená)
 
### DHCP (Dynamic Host Configuration Protocol)
![image](https://user-images.githubusercontent.com/83291717/191496239-92e173f9-977b-4066-981f-2a94904e76ff.png)<br>
*proces přidělování adresy + OBNOVA*

OBNOVA - Zopakuje se DHCPREQUEST, DHCPACK

 - **DHCPACK** - příjmutí requestu klientem - zápůjční doba adresy, konfigurační parametry
 - **DHCPNAK** - zamítnutí requestu DHCP serverem - pokud klient požaduje adresu z jiného subnetu (Vypršela zápůjční doba), proces jede od začátku
 - **DHCPDECLINE** - zamítnutí requ klientem - ip adresy, již v síti existuje
 - **DHCPRELEASE** - uvolnění ip adresy klientem
 - **DHCPINFORM** - klient pošle response serveru, že již adresu má a zároveń pošle request informačních údajů

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
 - IPv4 - má největší doménu in-addr.arpa.
 - IPv6 - má největší doménu ip6.arpa.

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
        allow-nxdomain { povoleno; };
        auth-nxdonaub no;
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
- Server Manager
  - **Local Server** - Nastavování Lokálního serveru
  - **All Servers** - Nastavování všech připojených serverů 
  - **File and Storage Servicies** - Nastavování diskovejch svazků, souborů, ...
