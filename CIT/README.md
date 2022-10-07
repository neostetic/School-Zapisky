<h3 align="center">Práce v programovacím jazyce C</h3>

```
printf("%x", 6 & 4); // 4
printf("%x", 0x26 >> 1); // 0x13
printf("%x", 0x26 >> 3); // 0x4
printf("%x", 0x26 << 1); // 0x4c
```

<p align="center">
  Takže z <code>(26)^4 = (100110)^2</code> se stane <code>(13)^4 = (010011)^2</code>, protože se bity dvojkové soustavy posunou do prava o 1 místo a tím se vytvoří nový hexadecimální zápis.
</p>

<details>
<summary><b>Základní převody jednotek</b></summary>
<br>

| Binární | Desítková | Šestnáctková |
|---------|-----------|--------------|
| 0000    | 0         | 0            |
| 0001    | 1         | 1            |
| 0010    | 2         | 2            |
| 0011    | 3         | 3            |
| 0100    | 4         | 4            |
| 0101    | 5         | 5            |
| 0110    | 6         | 6            |
| 0111    | 7         | 7            |
| 1000    | 8         | 8            |
| 1001    | 9         | 9            |
| 1010    | 10        | A            |
| 1011    | 11        | B            |
| 1100    | 12        | C            |
| 1101    | 13        | D            |
| 1110    | 14        | E            |
| 1111    | 15        | F            |

</details>
<p align="right"><a href="https://github.com/neostetic/School-Zapisky">Go Back</p>
