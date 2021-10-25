Login:
 - debian / root:Hicmm602/
 - martin:martin2

Příkazy:
 - apt-get update (aktualizace informací o balíčcích     ; 1.)
 - apt-get upgrade (auktualizovat bezpečnostní záplaty   ; 2.)
 - apt-get install [WORD] (ex.: apt-get install man/tree ; 3.)
 - apt-get install man net-tools ssh (instaluje balíčky na sever)
 - apt-get install stress (instaluje balíček stress)
 - stress --help
 - stress -c [NUM_1] -t [NUM_2] (stresuje cpu na [NUM_1]x po dobu [NUM_2] vteřin)
 - clear (zkratka: Ctrl + L)
 - [PŘÍKAZ] --help (vypíše help pro příkaz, ex.: ping --help)
 - [PŘÍKAZ] |more ("|" přídává příkazu k příkaz, "more" rozdělí příkaz na obrazovce)
 - apropos [WORD] (helper)
 - jobs (zobrazení běžících úloh)
 - fg [NUM] (zapnutí [NUM]-tého programu)
 - ls (dir v cmd)
 - ls -al (zobrazení výpisu adresáře a skrytých složek)
 - cd [DIRECTORY] (cmd posun do složky)
 - su [USERNAME] (switch user)
 - pwd (vypíše v jakém adresáři se nachází)
 - ifconfig (vypíše ip masku a broadcast)
 - netstat -lpnt (sluzby na protokolu TCP)
 - ip address show
 - top (správce úloh/procesů)
 - ps aux |grep [PRC] (hledá spuštěné procesi s názvem [PRC])
 - kill [PID] (vypne proces s id [PID])
 - nano [FILENAME] (vytvoří soubor [FILENAME])
 - emacs [FILENAME] (vytvoří soubor [FILENAME])
 - vim [FILENAME] (vytvoří soubor [FILENAME])

Zkratky:
 - Ctrl + L (clear)
 - Ctrl + C (stop)
 - Ctrl + Z (pauza - běží na pozadí)
 - Ctrl + S (pauza - neběží na pozadí)
 - Ctrl + Q (unpauza, "fg [NUM]")
 - Ctrl + D (logout)
 - Ctrl + K (od kurzoru doprava, copy)
 - Ctrl + U (od kurzoru doleva, copy)
 - Ctrl + Y (paste)
 - Ctrl + R (history search)
 - Tab (dokončuje příkazy)
 - Alt + F1-F6 (přepnutí na další konzoli)

Znaky v LS:

 owner  group  other
  ---    ---    ---

  r-- --- --- - pouze vlastnik
  --- r-- --- - pouze vlastníkova skupina
  rw- r-- r-- - vlastník může upravovat a číst můžou všichni

  . před souborem = skrytý soubor

 - example : drw-------
           : -rw-r--r--
 
 - r - read-only
 - w - úprava
 - x - (právo na) spuštění
 - d - directory

 PID - proces id
  
Manual:
 - https://archiv.linuxsoft.cz/article.php?id_article=1083 
 
Go back : https://github.com/neostetic/School-Zapisky
