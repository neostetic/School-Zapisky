## [💯 Číslicová Technika (CIT)](./..)
### Základní registry
- **PIN** - zjišťujeme, zda je port vypnutý, nebo zapnutý *(detekce vstupu)*
  - *vyhodnocení* - `int bit_is_clear(PINx, PINxn);` 
- **PORTx** - výstup (výstupní registr – určuje výstupní hodnotu pinu nastaveného jako výstup, zapíná nebo
vypíná pull-up rezistory u pinů nastavených na vstup.)
- **DDRx** - vývody nastavuje na I/O (data direction register – nastavuje příslušný port na vstup nebo na výstup)

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

![image](https://user-images.githubusercontent.com/83291717/220274945-a3d83a18-8ffd-4ced-a992-f3076b7964a2.png)

### Spínače
- **problémy**
  - **zákmity spínače *(switch bounce)*** - díky pruřnosti kontaktů, může mezi kontakty probíhat elektrický proud i po vypnutí spínače
    - **řešení problému**
      - *software* - počká se určitý úsek, aby se nezachytávaly elektromagnetické vlny
      	- ![image](https://user-images.githubusercontent.com/83291717/220280419-3c1cd303-5773-4f71-8c58-63ea5bdadac1.png) 
      - *hardware* - zkrze *rezistor* se nabijí *kondenzátor* do určitého napětí spínače
  - **rušení** - nesepnutý spínač může fungovat jako anténa a vývod může přes elektromagnetické rušení spínač sepnout
    - **řešení problému**
      - *pull-up rezistor* - mezi vývod a spínač se zapojí *pull-up rezistor*, jedna část obvodu se stáhne k nule a druhá k výši proudu
      	- ![image](https://user-images.githubusercontent.com/83291717/220279833-8cedd8dd-a293-4f6a-b77f-3e0fecfe7912.png)
      - *pull-down rezistor* - funguje opačně, jak *pull-up*, místo vytáhnutí, proud sníží
        - ![image](https://user-images.githubusercontent.com/83291717/220280892-d97b6a8c-befe-4f66-9b93-8e83c4938e27.png)
- *dělička napětí* - sériové zapojení rezistorů, napětí mezi nimi je poloviční
```
Ubat = 10 V
R1 = 10 kΩ
R2 = 0,1 Ω
------------
I = U / R = 10 / 10000,1 = (cca) 0,001 A
Ur1 = R1 * I1 = 10000 * 0,001 = 10 V

| VELIČINA | R1    | R2    | CELKEM     |
|----------|-------|-------|------------|
| U[V]     | 10    | 0     | 10         |
| I[A]     | 0,001 | 0,001 | 0,001      |
| R[Ω]     | 10000 | 0,1   | 10000,1    |
```
- **ATMEGA**
  - *zapnutí pull-up**
    - **DDRx** -
    - **PORTx** - 
```
DDRD  = 0b[0]0000000;
PORTD = 0b[1]0000000;
	   - zapíná proud pull-up
if(bit_is_clear(PIND, PIND7)) { ... }; 
```

<spoiler>
	<details>Zdrojový kód klikání a přepínání</details>
<code>
#define F_CPU 100000UL

#include <avr/io.h>
#include <util/delay.h>
#include <avr/sfr_defs.h>
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
	DDRC|=(1<<PORTC2)|(1<<PORTC3);
	PORTD|=(1<<PORTD7);
	int mssa = 64;
	int i = 0;
	int h = 0;
	while (1)
	{
		if (bit_is_clear(PIND, PIND7)) {
			if (!h) {
				if (i == 100) { i=0; };
				i++;
				h++;
			}
		} else { h=0; };
		int i1 = i / 10;
		int i2 = i % 10;
		PORTC=0b00000100;
		showNumber(i1);
		_delay_ms(mssa);
		PORTC=0b00001000;
		showNumber(i2);
		_delay_ms(mssa);
	}
}
	</code>
	</spoiler>

<p align="right">
  <a href="./..">Go Back</a>
</p>
