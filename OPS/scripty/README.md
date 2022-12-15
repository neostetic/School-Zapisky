## <a href="https://github.com/neostetic/School-Zapisky/tree/main/OPS">📴 Operační Systémy (OPS) - Scripty</a>


### Základy
#### Pracování s shell scripty
- Vytvoření a otevření **shell scriptu**
```
root@debian:~# touch prvni.sh
root@debian:~# chmod 755 prvni.sh
root@debian:~# nano prvni.sh
root@debian:~# bash prvni.sh
```

```
---------------------- nano ----------------------  
#! /bin/bash
#
clear
cislo=123
echo $cislo
read jmeno
echo $jemno
expr 2 + 2
expr 2 - 2
den=patek
echo "Dnes je $den `date`"
--------------------------------------------------  
```

#### Podmínka
```
---------------------- nano ----------------------  
#! /bin/bash
#
clear
if cat $1
  then
  echo -e "\n\Soubor nalezen a zobrazen."
else
  echo -e "\n\Soubor nenalezen."
fi
--------------------------------------------------  
```

#### Ciklus
```
---------------------- nano ----------------------  
#! /bin/bash
#
clear
x=0
while [ "$x" -le 10 ]; do
  echo "Aktualni hodnota x: $x"
  x=$(expr $x + 1)
  sleep 1
done
--------------------------------------------------  
```
```
---------------------- nano ----------------------  
#! /bin/bash
#
for i in 1 2 3 4 5
do
echo "$i. ciklus"
done
--------------------------------------------------  
```
```
---------------------- nano ----------------------  
#! /bin/bash
#
if [ $# -eq 0 ]
then
  echo "Nezadali jste cislo jako argument."
  echo "Syntaxe: $0 cislo."
  echo "Vypiste nasobky zadaneho cislo."
  exit 1
fi
n=$1
for i in 1 2 3 4 5 6 7 8 9 10
do
  echo "$n * #i = `expr $i \* $n`"
--------------------------------------------------  
```

#### Case
```
---------------------- nano ----------------------  
#! /bin/bash
#
if [ -z $1 ]; then
  barva="nezadana"
elif [ -n $1 ]
then
  barva=$1
fi

case $barva in
  "zluta") echo "Vybrali jste barvu - $barva";;
  "cervena") echo "Vybrali jste barvu - $barva";;
  "modra") echo "Vybrali jste barvu - $barva";;
  ...
  *) echo "Barva $barva neni v seznamu";;
esac
--------------------------------------------------
root@debian:~# chmod 755 vyber.sh
root@debian:~# ./vyber.sh cervena
```

#### Ciklus 2
```
---------------------- nano ----------------------  
#! /bin/bash
#
for (( i = 1; i <= 5; i++ ))
do
      for (( j = 1; j <= 5; j++ ))
      do
      echo -n "$i "
      done
echo " "
done
--------------------------------------------------
1 1 1 1 1
2 2 2 2 2
3 3 3 3 3
4 4 4 4 4
5 5 5 5 5
```

```
---------------------- nano ----------------------  
#! /bin/bash
#
for (( i = 1; i <= 8; i++ ))
do
      for (( j = 1; j <= 8; j++ ))
      do  
            tot=`expr $i + $j`
            tmp=`expr $tot % 2`
            if [ $tmp -eq 0 ]; then
                  echo -e -n "\033[47m "
            else
                  echo -e -n "\033[40m "
            fi
      done
      echo -e -n " \033[40m "
      echo " "
done
--------------------------------------------------
▉  ▉  ▉  ▉  
  ▉  ▉  ▉  ▉
▉  ▉  ▉  ▉  
  ▉  ▉  ▉  ▉
▉  ▉  ▉  ▉  
  ▉  ▉  ▉  ▉
▉  ▉  ▉  ▉  
  ▉  ▉  ▉  ▉
```

#### Ciklus 2
```
---------------------- nano ----------------------  
#! /bin/bash
#
cisla="1 2 3 4 5 6 7 8 9 10 11 12"
for cislo in $cisla
do
        Q=`expr $cislo % 2`
        if [ $Q -eq 0 ]
        then
                echo "Cislo $cislo je sude"
                continue
        fi
        echo "Cislo $cislo je liche"
done
--------------------------------------------------
Cislo 1 je liche
Cislo 2 je sude
Cislo 3 je liche
Cislo 4 je sude
Cislo 5 je liche
Cislo 6 je sude
Cislo 7 je liche
Cislo 8 je sude
Cislo 9 je liche
Cislo 10 je sude
Cislo 11 je liche
Cislo 12 je sude
```


<p align="right">
  <a href="./..">Go Back</a>
</p>
