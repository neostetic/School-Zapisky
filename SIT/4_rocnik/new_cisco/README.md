## <a href="./..">🔌 Počítačové Sítě (SIT) - 4. ročník</a>
 
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
  - `do wr`
- nyní running konfiguraci exportujeme
a nacteme na jiný router
- na jiném routeru
  - `en`
  - `reload`

<p align="right">
  <a href="./..">Go Back</a>
</p>
