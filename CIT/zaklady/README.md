## [💯 Číslicová Technika (CIT)](./..)
### Základní registry
- PORTx - výstup
- DDRx - výrody nastavuje na I/O

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
<p align="right">
  <a href="./..">Go Back</a>
</p>
