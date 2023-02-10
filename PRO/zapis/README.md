## <a href="https://github.com/neostetic/School-Zapisky/tree/main/PRO">💾 Programování (PRO)</a>

### Co je to Java?
- Objektově orientovaný, vysoko-úrovňový jazyk
- **JVM** - Java Virtual Machine
- **JIT** - Just In Time
- **JDK** - Java Development Kit
- **JRE** - Java Runtime Enviroment

```
i lost last progress, so fuck me... this shit needs fix => FIX ME LATER
```

### Operátory
#### Binární
- `+`, `-`, `*`, `/`, `%`
- `&` - AND
- `|` - OR, `^` - XOR
- `~` - negace
- `<<`, `>>` - 1011>>1 = 0101 *(bitový posun)*

#### Zkratky
- `++`, `--`

#### Logické
- `True` nebo `False`
- `==`, `!=`, `>`, `<`, `>=`, `<=`, `&&`, `||`

```
if(*podminka* & *podminka*) {}    // podminka se spusti 2x
if(*podminka* && *podminka*) {}   // podminka se spusti 1x
```

### Anotace
```
@Override
```

- Tvorba anotace
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunThis {
}
```

- Podmínka na invoke anotací
```
Method[] methods = Main.class.getDeclaredMethods();
for (Method method : methods) {
    RunThis annotationRunThis = method.getAnnotation(RunThis.class);
    if (annotationRunThis == null) {
        continue;
    }
    method.invoke(Main.class, null);
}
```

- Anotace s vlatními parametry
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunThis {
    int run();
}
```
```
@RunThis(run = 3)
...
Method[] methods = Main.class.getDeclaredMethods();
for (Method method : methods) {
    RunThis annotationRunThis = method.getAnnotation(RunThis.class);
    if (annotationRunThis == null) {
        continue;
    }
    for (int i = 0; i < annotationRunThis.run(); i++) {
        method.invoke(Main.class, null);
    }
}
```

### [Maturitní okruhy 2022/23](https://github.com/KRBNJSF/Maturita-okruhy#maturitn%C3%AD-okruhy-pro-20222023)

<p align="right">
  <a href="https://github.com/neostetic/School-Zapisky/tree/main/PRO">Go Back</a>
</p>
