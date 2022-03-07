Login:
 - debian / root:***
 - martin:martin2

Příkazy:
 - apt-get update (aktualizace informací o balíčcích     ; 1.)
 - apt-get upgrade (auktualizovat bezpečnostní záplaty   ; 2.)
 - apt-get install [WORD] (ex.: apt-get install man/tree ; 3.)
 - apt-get install man net-tools ssh (instaluje balíčky na sever)
 - apt-get install stress (instaluje balíček stress)
 - apt-get install apache2
 - apt-get install screen (proces za obrazovkou)
 - apt-get install htop (správce úloh)
 - stress --help
 - stress -c [NUM_1] -t [NUM_2] (stresuje cpu na [NUM_1]x po dobu [NUM_2] vteřin)
 - clear (zkratka: Ctrl + L)
 - [PŘÍKAZ] --help (vypíše help pro příkaz, ex.: ping --help)
 - [PŘÍKAZ] |more ("|" přídává příkazu k příkaz, "more" rozdělí příkaz na obrazovce)
 - [PŘÍKAZ] |grep [TEXT] (vyhledej [TEXT])
 - apropos [WORD] (helper)
 - jobs (zobrazení běžících úloh)
 - fg [NUM] (zapnutí [NUM]-tého programu)
 - ls (dir v cmd)
 - ls -al (zobrazení výpisu adresáře a skrytých složek)
 - cd [DIRECTORY] (cmd posun do složky)
 - su [USERNAME] (switch user)
 - cat [FILE] (vypis souboru)
 - cat /proc/filesystems
 - pwd (vypíše v jakém adresáři se nachází)
 - ifconfig (vypíše ip masku a broadcast)
 - netstat -lpnt (sluzby na protokolu TCP)
 - ip address show
 - top (správce úloh/procesů)
 - ps -aux |grep [PRC] (hledá spuštěné procesi s názvem [PRC])
 - kill [PID] (vypne proces s id [PID])
 - nano [FILENAME] (vytvoří soubor [FILENAME])
 - emacs [FILENAME] (vytvoří soubor [FILENAME])
 - vim [FILENAME] (vytvoří soubor [FILENAME])
 - update-alternatives --config editor (změna hl. editoru)
 - editor (otevře editor)
 - chmod [CMDS] [FILENAME] (změní oprávnění)
 - ./[FILE] (otevři a odkazuj na [FILE])
 - dmesg (vypsání operací hardwaru a softwaru po startu systemu)
 - pstree - rodičovský strom procesů
 - systemctl stop [PROCESS] (vypni process [PROCESS] ; př: "systemctl stop apache2")
 - systemctl start [PROCESS] (zapni process [PROCESS] ; př: "systemctl start apache2")
 - htop (správce úloh)
 - nice -n [NUM] [PROCESS] (nastavení ohlduplnosti na [NUM] (-20 až 20) procesu [PROCESS])
 - renice -n [NUM] [PROCESS]
 - ps -aux | 

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


Formátování nového disku:
 - Příkazy
   - dmesg | grep sdb
   - fdisk [adresář k disku] (fdisk /dev/sdb)
     - Command (m for help): n
     - Select (default p): [Enter_Key]
     - Partition number (1-4, default 1): [Enter_Key]
     - First sector (2048-204799, default 2048): [Enter_Key]
     - Last sector...: [Enter_Key]
     - Command (m for help): w
   - fdisk -l
   - mkfs.ext4 /dev/sdb1
   - mount --rw /dev/sdb1 /mnt
   - cd /mnt
   - ls -la
   - cat /etc/fstab

Uložené file:
 - red
 - blue
 - green
 - default

Screen Ovládání
 - [Ctrl] + [a] ; [d] - odejít
 - v konzoli : screen -ls
   	       screen -r [proces id]
 
Ve složce "etc" se nachází veškerá konfigurace

Posílání mailů:
 - apt-get install msmtp
 - vim /etc/msmtprc

 - ve vim
    defaults
    port 587
    tls on
    tls_starttls on

    account gmail
    host smtp.gmail.com
    from [name@email]
    auth on
    user [name]
    password [password]
 - Escape, :w, Ctrl+Z - echo -e "Subject: Vlož sem subjekt!" | msmtp [name@email]
 - vim ~/.profile
    ...
    time=$(date)
    echo -e "Subject: New Login $time" | msmtp [name@email]


Go back : https://github.com/neostetic/School-Zapisky/tree/main/OPS
