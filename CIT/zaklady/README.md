## [💯 Číslicová Technika (CIT)](./..)
### Základní registry
- PORTx - výstup (výstupní registr – určuje výstupní hodnotu pinu nastaveného jako výstup, zapíná nebo
vypíná pull-up rezistory u pinů nastavených na vstup.)
- DDRx - vývody nastavuje na I/O (data direction register – nastavuje příslušný port na vstup nebo na výstup)

### LED Diody Switch
```
#define F_CPU 8000000UL

#include <avr/io.h>
#include <util/delay.h>


int main(void)
{
    
    DDRA=0b11111111;
    DDRC=0x4; // DDRC|=(1<<PORTC2)
    int mssa = 1024;
	while (1) 
    {
		while (1)
		{
			PORTA = 0b10101010;
			_delay_ms(mssa);
			PORTA = 0b01010101;
			_delay_ms(mssa);	
		}
    }
}
```

### LED
- **Vf** - minimální napětí, které musíme připojit pro funkci LED
- **If** - minimální proud, která je deklarovaný vyrobcem LED
- **Vr** - maximální napětí v závěrném směru
- **Ir** - maximální proud, který bude protékat, když ji zapojáme v závěrném směru
- ***Vbat*** - napětí baterie

#### Jaký rezostor zvolit?
```
Vf = 2V
If = 30mA
Vbat = 5V
------------
Vlef = Vbat - Vf
Vled = 5 - 2 = 3V
## Ohmův zákon : R = Vled/If ##
R = 3/0,03 = 100 Ω
             -----
```
- pomocí různých řad Rezistorů si můžeme dočíst jakou vybrat


<p align="right">
  <a href="./..">Go Back</a>
</p>
