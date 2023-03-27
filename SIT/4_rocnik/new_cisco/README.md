## <a href="./..">🔌 Počítačové Sítě (SIT) - 4. ročník - New Cisco</a>
 
- vytváření import apliance
  - `en`
  - `conf t`
  - `host R0`
  - `ban motd "R0"`
  - `line con 0`
  - `pass cisco`
  - `login`
  - `exit`
  - `ip domain-name franta.local`
  - `crypto key generate rsa general-key modulus 4096`
  - `line vty 0 15`
  - `pass cisco`
  - `transport input ssh`
  - `login local`
  - `user franta secret cisco`
  - `enable secret cisco`
  - `service password encryption`
  - `exit`
  - `do wr` / `wr`
- nyní running konfiguraci exportujeme
a nacteme na jiný router
- na jiném routeru
  - `en`
  - `reload`
  - heslo bude `cisco`
  - `host R1`
  - `ban motd "R1"`
  - ...
- IPv6
  - Router 0
    - `en`
    - `conf t`
    - `ipv6 unicast-routing`
    - `ipv6 cef`
    - `int gi0/0`
    - `ipv6 address 2001:0:0:9::/64 eui-64`
    - `no sh`
    - `int gi0/1`
    - `ipv6 address 2001:0:0:8::/64 eui-64`
    - `no sh`
    - `int gi0/2`
    - `ipv6 address 2001:0:0:10::/64 eui-64`
    - `no sh`
  - Router 1
    - `en`
    - `conf t`
    - `ipv6 unicast-routing`
    - `ipv6 cef`
    - `int gi0/0`
    - `ipv6 address 2001:0:0:9::/64 eui-64`
    - `no sh`
  - Ping
    - `do sh ipv6 int br` - pro ukázku adres
    - `ping [adresa]`  
- OSPF
  - Router 1 
    - `en`
    - `conf t` 
    - `ipv5 router ospf 1`
    - `router-id 1.1.1.1`
    - `exit`
    - `int gi0/0`
    - `ipv6 ospf 1 area 0` 

<p align="right">
  <a href="./..">Go Back</a>
</p>
