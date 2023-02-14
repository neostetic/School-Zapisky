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

- celočíselné dělení
  - `34 / 10 = 3`
  - `34 % 10 = 4`

```
#define F_CPU 100000UL

#include <avr/io.h>
#include <util/delay.h>
#include <stdbool.h>

void showNumber(char num)
{
	switch(num)
	{
		case 1: PORTA=0b01001000; break;
		case 2: PORTA=0b00111101; break;
		case 3: PORTA=0b01101101; break;
		case 4: PORTA=0b01001011; break;
		case 5: PORTA=0b01100111; break;
		case 6: PORTA=0b01110111; break;
		case 7: PORTA=0b01001100; break;
		case 8: PORTA=0b11111111; break;
		case 9: PORTA=0b01101111; break;
		case 0: PORTA=0b01111110; break;
	}
}

// uint8_t = 0;
// bool tl = 0;

int main(void)
{
	DDRA=0b11111111;
	DDRC=0xFF;
	int mssa = 64;
    while (1) 
    {
		for (int i = 0; i < 100; i++) {
			int i1 = i / 10;
			int i2 = i % 10;
			for (int i = 0; i < 100; i++) {
				PORTC=0b00000100;
				showNumber(i1);
				_delay_ms(mssa);
				PORTC=0b00001000;
				showNumber(i2);
				_delay_ms(mssa);				
			}
		}
    }
}
```

<p align="right">
  <a href="./..">Go Back</a>
</p>
