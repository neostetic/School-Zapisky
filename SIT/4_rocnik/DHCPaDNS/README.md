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
subnet 10.0.0.0 netmask 255.255.255.0 {         // pridame ip site a masku
  range 10.0.0.30 10.0.0.50;                    // nastavime rozsah
  option domain-name-server 10.0.0.0;           // zmenime na nasi DNS, pokud nechceme, tak na google servery (8.8.8.8, 8.8.4.4)
  option domain-name "franta.local";            // nastavime nazev NASI domeny
#  option broadcast-address 10.5.5.31;          // NEPOTREBUJEME
  default-lease-time 600;
  max-lease-time 7200;
}
```
- poté přes `systemctl restart` a `systemctl status` restartujeme a oveříme si zda nám `isc-dhcp-server` běží správně
#### Kontola DHCP u Klienta
- přes `ifconfig` si můžeme zkontrolovat zda nám na `ethX` běží naše DHCP
#### Nastavení DNS na Server

<p align="right">
  <a href="./..">Go Back</a>
</p>
