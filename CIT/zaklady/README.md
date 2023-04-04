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
	while (1) {
		while (1) {
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

<details>
	<summary>Zdrojový kód klikání a přepínání</summary>

```
#define F_CPU 1000000UL

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
	int mssa = 50;
	int i = 0;
	int h = 0;
	while (1)
	{
		if (bit_is_clear(PIND, PIND7)) {
			_delay_us(50);
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
```
	
</details>

### Timery/Countery
- `_delay_ms()` - funkce k počkání na **hlavním** procesoru
- na procesoru *ATMEGA8* se přidaly nové samostatné vlákna *(Timer a Counter)*
- **Timery**
  - **základní funkce** - stopky, počítá určitý nastavený čas
  - **režimy**
    - **normal** - počítá od 0 do MAX, a poté jede odznova
    - **CTC** - počítá od 0 do vlastního MAX
    - **PWM** - řízení moderního osvělení, motorů, generování zvuků
- **Countery**
  - **základní funkce** - počítadlo jevů *(stisk tlačítka, apod.)*

#### Tabulka Timeru/Counteru
  
<details>
	<summary>Tabulka</summary>
	
	
| Mode  | WGM13 | WGM12 (CTC1) | WGM11 (PWM11) | WGM10 (PWM10) | Timer/Counter Mode of Operation(1)  | TOP | Update of OCR1x | TOV1 Flag Set on |
|---|---|---|---|---|---|---|---|---|
| 0  | 0  | 0  | 0  | 0  | Normal  | 0xFFFF  | Immediate  | MAX |
| 1  | 0  | 0  | 0  | 1  | PWM, Phase Correct, 8-bit  | 0x00FF  | TOP  | BOTTOM |
| 2  | 0  | 0  | 1  | 0  | PWM, Phase Correct, 9-bit  | 0x01FF  | TOP  | BOTTOM |
| 3  | 0  | 0  | 1  | 1  | PWM, Phase Correct, 10-bit  | 0x03FF  | TOP  | BOTTOM |
| 4  | 0  | 1  | 0  | 0  | CTC  | OCR1A  | Immediate  | MAX |
| 5  | 0  | 1  | 0  | 1  | Fast PWM, 8-bit  | 0x00FF  | BOTTOM  | TOP |
| 6  | 0  | 1  | 1  | 0  | Fast PWM, 9-bit  | 0x01FF  | BOTTOM  | TOP |
| 7  | 0  | 1  | 1  | 1  | Fast PWM, 10-bit  | 0x03FF  | BOTTOM  | TOP |
| 8  | 1  | 0  | 0  | 0  | PWM, Phase and Frequency Correct  | ICR1  | BOTTOM  | BOTTOM |
| 9  | 1  | 0  | 0  | 1  | PWM, Phase and Frequency Correct  | OCR1A  | BOTTOM  | BOTTOM |
| 10  | 1  | 0  | 1  | 0  | PWM, Phase Correct  | ICR1  | TOP  | BOTTOM |
| 11  | 1  | 0  | 1  | 1  | PWM, Phase Correct  | OCR1A  | TOP  | BOTTOM |
| 12  | 1  | 1  | 0  | 0  | CTC  | ICR1  | Immediate  | MAX |
| 13  | 1  | 1  | 0  | 1  | (Reserved)  | –  | –  | – |
| 14  | 1  | 1  | 1  | 0  | Fast PWM  | ICR1  | BOTTOM  | TOP |
| 15  | 1  | 1  | 1  | 1  | Fast PWM  | OCR1A  | BOTTOM  | TOP |

</details>
	
#### Hardwarové přerušení *(HW interrupt)*
- potřeba si určit frekvenci timeru pomocí tabulky
	
| CS12  | CS11  | CS10  | Description |
|---|---|---|---|
| 0  | 0  | 0  | No clock source. (Timer/Counter stopped) |
| 0  | 0  | 1  | clkI/O/1 (No prescaling) |
| 0  | 1  | 0  | clkI/O/8 (From prescaler) |
| 0  | 1  | 1  | clkI/O/64 (From prescaler) |
| 1  | 0  | 0  | clkI/O/256 (From prescaler) |
| 1  | 0  | 1  | clkI/O/1024 (From prescaler) |
| 1  | 1  | 0  | External clock source on T1 pin. Clock on falling edge |
| 1  | 1  | 1  | External clock source on T1 pin. Clock on rising edge |

```
Ftimer = ?
F_CPU = n
PS = n
------------PŘÍKLAD------------
F_CPU = 1000000 Hz
t = 1 s
[ Ft = (F_CPU / PS) * t ]
PS = 8 => Ft = 125000 Hz
               125000 !< 65535
PS = 64 => Ft = 15625 Hz
                15625 < 65535
-------------------------------
```

#### ATMEGA8 hodiny v Simulide
```
#define F_CPU 1000000UL

#include <avr/io.h>
#include <util/delay.h>
#include <avr/sfr_defs.h>
#include <stdbool.h>
#include <avr/interrupt.h>

void showNumber(char num)
{
	switch(num)
	{
		case 1: PORTB=0b01001000; break;
		case 2: PORTB=0b00111101; break;
		case 3: PORTB=0b01101101; break;
		case 4: PORTB=0b01001011; break;
		case 5: PORTB=0b01100111; break;
		case 6: PORTB=0b01110111; break;
		case 7: PORTB=0b01001100; break;
		case 8: PORTB=0b11111111; break;
		case 9: PORTB=0b01101111; break;
		case 0: PORTB=0b01111110; break;
	}
}

uint8_t sec = 0;
uint8_t min = 0;
uint8_t hour = 0;

int main(void)
{
	int mssa = 1;
	DDRB = 0xFF;				// vystup pro LCD
	DDRC = 0xFF;				// vystup pro LCD
	TCCR1B |= (1<<WGM12);			// Timer, rezim CTC, kanal A
	TCCR1B |= (1<<CS11) | (1<<CS10);	// nastavi prescaler timeru na hodnotu 64
	OCR1A = 156;				// nastavi MAX hodnotu v timeru v rezimu CTC
	TIMSK |= (1<<OCIE1A);			// vyvola preruseni pri napocitani MAX timeru v rezimu CTC, kanal A
	sei();					// zapne moznost pouzivat Hardwarove preruseni
	while (1)
	{
		showNumber(sec/10);
		PORTC=0b11111101;
		_delay_ms(mssa);
		showNumber(sec%10);
		PORTC=0b11111110;
		_delay_ms(mssa);
		showNumber(min/10);
		PORTC=0b11101111;
		_delay_ms(mssa);
		showNumber(min%10);
		PORTC=0b11011111;
		_delay_ms(mssa);
		showNumber(hour%10);
		PORTC=0b11111011;
		_delay_ms(mssa);
		showNumber(hour/10);
		PORTC=0b11110111;
		_delay_ms(mssa);
	}
}

ISR(TIMER1_COMPA_vect) {
	sec++;
	if (sec == 60) {
		sec = 0;
		min++;	
	}
	if (min == 60)
	{
		min = 0;
		hour++;
	}
	if (hour == 24)
	{
		hour = 0;
	}
}
```

### Hodiny
```
#define F_CPU 1000000UL

#include <avr/io.h>
#include <util/delay.h>
#include <avr/sfr_defs.h>
#include <stdbool.h>
#include <avr/interrupt.h>

void showNumber(char num)
{
	switch(num)
	{
		case 1: PORTB=0b01001000; break;
		case 2: PORTB=0b00111101; break;
		case 3: PORTB=0b01101101; break;
		case 4: PORTB=0b01001011; break;
		case 5: PORTB=0b01100111; break;
		case 6: PORTB=0b01110111; break;
		case 7: PORTB=0b01001100; break;
		case 8: PORTB=0b11111111; break;
		case 9: PORTB=0b01101111; break;
		case 0: PORTB=0b01111110; break;
	}
}

uint8_t sec = 0;
uint8_t min = 0;
uint8_t hour = 0;

int main(void)
{
	int mssa = 1;
	DDRB = 0xFF;											// vystup pro LCD
	DDRC = 0xFF;		// vystup pro LCD
	TCCR1B |= (1<<WGM12);									// Timer, rezim CTC, kanal A
	TCCR1B |= (1<<CS11) | (1<<CS10);						// nastavi prescaler timeru na hodnotu 64
	OCR1A = 16;											// nastavi MAX hodnotu v timeru v rezimu CTC
	TIMSK |= (1<<OCIE1A);									// vyvola preruseni pri napocitani MAX timeru v rezimu CTC, kanal A
	sei();													// zapne moznost pouzivat Hardwarove preruseni
	while (1)
	{
		showNumber(sec/10);
		PORTC=0b11111101;
		_delay_ms(mssa);
		showNumber(sec%10);
		PORTC=0b11111110;
		_delay_ms(mssa);
		showNumber(min/10);
		PORTC=0b11101111;
		_delay_ms(mssa);
		showNumber(min%10);
		PORTC=0b11011111;
		_delay_ms(mssa);
		showNumber(hour%10);
		PORTC=0b11111011;
		_delay_ms(mssa);
		showNumber(hour/10);
		PORTC=0b11110111;
		_delay_ms(mssa);
	}
}

ISR(TIMER1_COMPA_vect) {
	if (bit_is_clear(PINC, PINC6)) {
		sec++;
		if (sec == 60) {
			sec = 0;
			min++;
		}
		if (min == 60)
		{
			min = 0;
			hour++;
		}
		if (hour == 24)
		{
			hour = 0;
		}	
	}
}
```

### ADC
- slouží pro převádění anologového signálu _(zvuk, vlnové dělky)_ na digitální
- **anologový signál**
  - spojovný, nepřerušovaný
- **digitální**
  - diskrétní bitový signál, plný hodnot
- využívá se všude, kde jsme schopný převést měřenou veličinu na výši napětí
  - využívá se při měřením proudu *(ampérmetrem)*
- **ATMEGA8** a **ATMEGA16** obsahují ADC
  - **ATMEGA16A**
    - (./files/ATMEGA16.pdf)[ATMEGA16 *(kap. 22, str. 196)*]
    - 8 single-ended vstupů
    - 7 diff. vstupů
      - integrovaný zesilovač signálu
    - rozlišení 10b *(1024 úrovní)*
    - **čas konverze** - *13-260µs*
    - **vzorkovací frekvence** - 0-25kHz *(optimální 50-200kHz)*
    - **interní referenční napětí** - `Vref = 2.56V`
    - **chybovost** - `±2LSB (least significant bit)`
    - **typy konverzí**
      - *single conversion* - změří se jenom jedna hodnosta
      - *free running mode* - konverze probíhá cyklem, dokud ho nevypneme
      - *triggered mode* - čeká se do nějaké události, poté se zapne
  - **ATMEGA8**
    - (./files/ATMEGA8.pdf)[ATMEGA8]
    - 6 diff. vstupů
    - v podstatě stejný jako *ATMEGA16A*, bez diferenčního režimu
- postupná apromximace
  - základem *A/D převodníku* je *D/A převodník (DAC)*
    - pomocí D/A převodníku výrabíme a porovnáváme signál
  - analogový komparátor - určuje, které napětí je větší
  - ![image](https://user-images.githubusercontent.com/83291717/229710681-b80fe117-2dc3-466f-882b-d134ff4b41a9.png)
    - **nad Vref** - první bitem z leva 1
    - **pod Vref** - první bitem z prava 0
    - **nad Vref/2** - dalším bitem z leva 1
    - **pod Vref/2** - dalším bitem z prava 0
    - **nad Vref/4** - dalším vitem z leva 1
    - **pod Vref/4** - dalším bitem z prava 0
- **registr bity**
  - *ADMUX* - nastavuje ref napětí zarovnání bitů z leva nebo z prava, bolba vývodu pro AD převodník
  - *ADVAR* 
  - *ADCSRA*
  - *ADC* - skládá se ze dvou částÍ *(ADCL, ADCR)*
  - `ADC = ( Vin * 1024 ) / Vref`
	
```
#define F_CPU 1000000UL
#include <avr/io.h>
#include <util/delay.h>
#include <avr/interrupt.h>
#include <math.h>

void showNumber(char num)
{
	switch(num)
	{
		case 1: PORTB=0b01001000; break;
		case 2: PORTB=0b00111101; break;
		case 3: PORTB=0b01101101; break;
		case 4: PORTB=0b01001011; break;
		case 5: PORTB=0b01100111; break;
		case 6: PORTB=0b01110111; break;
		case 7: PORTB=0b01001100; break;
		case 8: PORTB=0b11111111; break;
		case 9: PORTB=0b01101111; break;
		case 0: PORTB=0b01111110; break;
	}
}

float voltage = 0;
uint8_t timer = 0;

float conversion() {
	ADCSRA |= (1 << ADSC)					// zapíše 1 na bit ADSC
	loop_until_bit_is_clear(ADCSRA, ADSC)	// čeká na dokončení koverze
	return ADC;								// vrátí hodnotu po dokončéní konverze
}

EMPTY_INTERRUPT(ADC_vect);

int main(void) {
	DDRB = 0xFF;
	DDRC |= (1 << PC2) | (1 << PC3);
	PORTC |= (1 << PC2) | (1 << PC3);
	ADMUX |= (3 << REFS0);					// nastavení interního referenčního napětí
	ADCSRA |= (3 << ADPS0);					// nastaví prescaler ADC na 8 (125kHz)
    ADCSRA |= (1 << ADEN);					// zapne funkci ADC
	TCCR1B |= (1 << WGM12);					// 10b timer, režim CTC, kanál A
	TCCR1B |= (1 << CS10) | (1 << CS11);	// nastaví prescaler 64 pro timer
	TIMSK |= (1 << OCIE1A);					// při shodě CRC se zavolá HW přerušení
	OCR1A = 15624;							// vlastní max. pro režim CTC
	sei();									// zapne gloválně HW interrupt
	while (1) {
    }
}	
```

<p align="right">
  <a href="./..">Go Back</a>
</p>
