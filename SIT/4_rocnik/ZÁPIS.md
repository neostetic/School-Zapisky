## <a href="./..">🔌 Počítačové Sítě (SIT) - 4. ročník - zápis</a>
- [!!! Soubory zápisu](./soubory)

### Routování
 - statické (ručné v Routovací Tabulce)
 - dynamické (pomocí Routovací Protokolů)

#### Routovací protokoly
 - činnost
   - DV
   - LSA
 - použití
   - IGP
   - EGP
 ...
 
 ### DHCP
![image](https://user-images.githubusercontent.com/83291717/191496239-92e173f9-977b-4066-981f-2a94904e76ff.png)<br>
*proces přidělování adresy + OBNOVA*

OBNOVA - Zopakuje se DHCPREQUEST, DHCPACK

 - **DHCPACK** - příjmutí requestu klientem - zápůjční doba adresy, konfigurační parametry
 - **DHCPNAK** - zamítnutí requestu DHCP serverem - pokud klient požaduje adresu z jiného subnetu (Vypršela zápůjční doba), proces jede od začátku
 - **DHCPDECLINE** - zamítnutí requ klientem - ip adresy, již v síti existuje
 - **DHCPRELEASE** - uvolnění ip adresy klientem
 - **DHCPINFORM** - klient pošle response serveru, že již adresu má a zároveń pošle request informačních údajů
