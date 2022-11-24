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


<p align="right">
  <a href="./..">Go Back</a>
</p>
