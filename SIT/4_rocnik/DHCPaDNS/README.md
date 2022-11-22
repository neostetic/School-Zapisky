## <a href="./..">🔌 Počítačové Sítě (SIT) - Konfigurace DHCP a DNS</a>
### Kofigurace
- před zapnutím nastavíme síťové karty virtuálu
  - *Server* - NAT a Vnitřní síť
  - *Klient* - Vnitřní síť
#### Nastavení DHCP na Server
- potřebujeme stáhnout DHCP aplianci : `apt install isc-dhcp-server`
- dále po instalaci v konfiguraci `/etc/network/interfaces` si zapneme naši *novou* síťovou kartu a nastavíme ji na statickou
```
allow-hotplug eth1
iface eth1 inet static
        address 10.0.0.1/24
```
- rozhraní poté přes `ifdown` a `ifup` vypneme a zapneme
- v **defaultní** konfiguraci `/etc/default/isc-dhcp-server` nastavíme název naši síťové karty do `INTERFACESv4="eth1"` a zbytek zakomentářujeme nebo odstraníme
- poté se vrhneme na samotné DHCP v konfiguraci `/etc/dhcp/dhcp.conf`
- řádky `authoritative;` a `log-facility local7;` odkomentářujeme
- najdeme si nejdejší *subnet template* a ten si upravíme
```
authoritative;

log facility local7;

subnet 10.0.0.0 netmask 255.255.255.0 {         // pridame ip site a masku
  range 10.0.0.30 10.0.0.50;                    // nastavime rozsah
  option domain-name-server 10.0.0.1;           // zmenime na nasi DNS, pokud nechceme, tak na google servery (8.8.8.8, 8.8.4.4)
  option domain-name "franta.local";            // nastavime nazev NASI domeny
#  option broadcast-address 10.5.5.31;          // NEPOTREBUJEME
  default-lease-time 600;
  max-lease-time 7200;
}
```
- poté přes `systemctl restart` a `systemctl status` restartujeme a oveříme si zda nám `isc-dhcp-server` běží správně
  - *musíme brát v potaz že máme nastavený `domain-name-server` na naši už přidělanou DNS !!!*
#### Kontola DHCP u Klienta
- přes `ifconfig` si můžeme zkontrolovat zda nám na `ethX` běží naše DHCP
#### Nastavení DNS na Server
- **kdyz se neco zda v neporadku `named-checkconf` pomuze (syntaxe) => pozor na středníky**
- potřebujeme stáhnout DNS aplianci : `apt install bind9 bind9-doc bind9utils`
- první co si nastavíme je `/etc/bind/named.conf.options`
- přídáme si svoje `acl` pro `allow-recursion` - ip adresy sítě a jejich prefixy
- dále přidáme `listen-on` pro IPv4 *ip adresy lokálu a našeho DHCP*, `listen-on-v6` IPv6 vypneme
- přídáme `allow-recursion` a do něho vložíme naše **acl**
- **přídáme nově `allow-transfer { none; }` a `auth-nxdomain yes`**
```
acl "tohleJeAclIdk" {
        127.0.0.0/8;                          // lokalni
        10.0.0.0/24;                          // nase
};

options {
        directory "/var/cache/bind";
        
        forwarders {                          // pridame google servery
          8.8.8.8;
          8.8.4.4;
        };
        
        dnssec-validation no;                 // upravime na "no"
        listen-on-v6 { none; };               // vypneme a nastavime pro IPv4
        listen-on { 127.0.0.1; 10.0.0.1; };   // ip adresy
        allow-recursion { tohleJeAclIdk; };   // sem si pridame nase acl
        allow-transfer { none; };             // !TOHLE ZAPAMATOVAT
        auth-nxdomain yes;                    // !TOHLE ZAPAMATOVAT
};
```
- dále se vrhneme na přidávání zón v `/etc/bind/named.conf.local`
- zóny budeme potřebovat dvě, jednu pro náš local a druhý pro ip adresu
- je potřeba vypsat k jednotlivým zónám `type master` a cestu k nim
```
zone "franta.local" {
         type master;
         file "/etc/bind/zones/db.franta.local";  // cesta k zonovem souboru
}

zone "0.0.10.in-addr.arpa" {                      // jmeno se sklada z otocene ip adresy bez posledniho oktetu
         type master;                             // -- a s "in-addr.arpa" pro IPv4
         file "/etc/bind/zones/db.10.0.0";        // cesta k zonovem souboru (bez posledniho oktetu)
}
```
- teď so vytvoříme zóny pomocí zóny prázdné `db.empty`
```
mkdir /etc/bind/zones
cp /etc/bind/db.empty /etc/bind/zones/db.franta.local
cp /etc/bind/db.empty /etc/bind/zones/db.10.0.0
```
- upravíme nějaké údaje, většinou se jedná jenom o názvy
- **domény musí obsahovat "." na konci**
- `db.franta.local`
```
@TTL    86400     SOA     ns1.franta.local.   root.franta.local   // upravit nazvy na nase (pr. ns1.franta.local)
                          2022101001          ; Serial            // casto dnesni datum + nejake cislo
                              604800          ; Refresh
                               86400          ; Retry
                             2419200          ; Expire
                               86400          ; Negative Cache TTL
;
@       IN        NS      ns1.franta.local.   // !TOHLE ZAPAMATOVAT
ns1     IN        A       10.0.0.1            // !TOHLE ZAPAMATOVAT
test    IN        CANAME  ns1                 // alias - neni potreba
```
- `db.10.0.0`
```
@TTL    86400     SOA     ns1.franta.local.   root.franta.local   // upravit nazvy na nase (pr. ns1.franta.local)
                          2022101001          ; Serial            // casto dnesni datum + nejake cislo
                              604800          ; Refresh
                               86400          ; Retry
                             2419200          ; Expire
                               86400          ; Negative Cache TTL
;
@       IN        NS      ns1.franta.local.   // !TOHLE ZAPAMATOVAT
ns1     IN        PTR     ns1.franta.local.   // !TOHLE ZAPAMATOVAT - odkaz IP adresy
```
- pro kontrolu můžeme zkusit odpovědi `named-checkzone franta.local db.franta.local` a `named-checkzone 0.0.10.in-addr.arpa db.10.0.0`
- poté přes `systemctl restart` a `systemctl status` restartujeme a oveříme si zda nám `bind9` běží správně
#### Kontola DNS u Klienta
- buď přes `cat /etc/resolv.conf` nebo `nslookup` s určitou adresou

<p align="right">
  <a href="./..">Go Back</a>
</p>
