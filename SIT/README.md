# Počítačové Sítě

<b>Zdroje:</b>
 - http://samuraj-cz.com
 - http://toolkit.g6.cz

## Privátní sítě (IP)

### Privátní sítě:
 - 192.168.0.0/16
 - 10.0.0.0/8
 - 172.16.0.0/12
 - VLSM (Variable Length Subnet Masking)
   - x.x.x.x/xx 

### Dynamický routing:
 - Dynamic routing
   - IGP (Interior Gateway Protocol)
     - Dystance vektor
     - Link state 
   - EGP (Exterior Gateway Protocol)
     - Path vektor

### RIP (Routing Information Protocol):
 - RIP verze 1
   - aktualizace směrování nezahrnují informace o masce sítě, podpora IPv4, chybí podpora pro CIDR
 - RIP verze 2
   - podpora CIDR a IPv4, omezení 15 skoků (hop count), 224.0.0.9 (multicast)
 - RIPng
   - podpora IPv6, chybí aktualizovaných autentizací a připojování k libovolných směrovačům

## Masky sítí (IP) 

| Maska                 | Celkem IP | Použitelné IP | S Gateway |
|-----------------------|-----------|---------------|-----------|
| /30 - 255.255.255.252 | 4         | 2             | 1         |
| /29 - 255.255.255.248 | 8         | 6             | 5         |
| /28 - 255.255.255.240 | 16        | 14            | 13        |
| /27 - 255.255.255.224 | 32        | 30            | 29        |
| /26 - 255.255.255.192 | 64        | 62            | 61        |
| /25 - 255.255.255.128 | 128       | 126           | 125       |
| /24 - 255.255.255.0   | 256       | 254           | 253       |
| /23 - 255.255.254.0   | 512       | 510           | 509       |
| ...                   | ...       | ...           | ...       |

## Nastavení routerů:

| Název příkazu     | Příkaz                                     |
|-------------------|--------------------------------------------|
| Uživatelský mód   | CLI table                                  |
| Privilegovaný mód | enable                                     |
| Globální mód      | configure terminal                         |
| Rozhrání kabelu   | interface [cable]                          |
| IP adresa         | ip address [ip] [mask]                     |
| Nastavení RIPu    | router rip                                 |
| Verze RIPu        | version [1/2/ng]                           |
| Nastavení Network | network [global-ip]                        |
| Nevypnout         | no shutdown                                |
| Exit sub-mód      | end / exit                                 |
| Uložit            | write / copy running-config startup-config |
