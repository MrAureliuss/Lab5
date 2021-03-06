# Lab5
> Если у меня будет сын, то я назову его Несудитестрогоясделалвсечтосмог Эрикович Симонян
>
**В данном репозитории находится исходный код пятой лабораторной работы. В основе архитектуры лежит паттерн "Команда"**

## Содержание <a name="Содержание"></a> 
* [Задание лабораторной работы.](#Задание)
* [Объяснение основных моментов кода.](#Пояснения)
* [Заключение.](#Заключение)

### Задание лабораторной работы <a name="Задание"></a>
![Alt-текст](https://i.imgur.com/lpShRKQ.jpg)
![Alt-текст](https://i.imgur.com/7o0ADIy.jpg)
![Alt-текст](https://i.imgur.com/4AqAKRX.jpg)
[Содержание :arrow_up:](#Содержание)


### Объяснение основных моментов кода <a name="Пояснения"></a>
Как вы уже поняли, в основе архитектуры программы лежит паттерн "Команда". Более подробно о нем Вы можете прочитать на:  
[Википедии](https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0_(%D1%88%D0%B0%D0%B1%D0%BB%D0%BE%D0%BD_%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F))  
[Refactoring.guru](https://refactoring.guru/ru/design-patterns/command)   
  
***Настоятельно НЕ рекомендую смотреть видео-ролики с объяснениями данного паттерна на youtube и подобных псевдо-кодерских ресурсах, так как разные люди, трактуют паттерн, зачастую, неверно и путают его части.***

Для более легкого понимания архитектуры программы предлагаю, разделить его на несколько составляющих частей:  
1. Консольный менеджер (ConsoleManager.java)
2. Вызыватель (далее будем называть его инвокером, CommandInvoker.java)
3. Получатель (далее будем называть его ресивером, CommandReceiver.java)
4. Менеджер-коллекции (CollectionManager.java)
5. Команды (будь-то абстрактная команда или конкретные команды)
6. Creater'ы и Reader'ы (находятся в директории /src/Commands/Utils)
7. Базовые классы

Код вызывается из класса Main. Для понимания того, что происходит в ходе работы программы мы должны понять, что делает каждая из вышеописсаных
частей, для этого разберемся с каждой из них.  
1. **Консольный менеджер**:
     + Создает экземпляры классов ресивера и инвокера.
     + Инициализирует коллекцию (в моем случае это LinkedList. Происходит это в методе initList менеджера-коллекции.
    ```Java
        public static void initList() {
                if (linkedList == null) { linkedList = new LinkedList<>(); creationDate = ZonedDateTime.now(); }  // Как видим, создается он только в том случае, если он null, то есть еще не создан. Более детально можно разобраться в самом исходном коде класса.
        }
     ```
     + Регистрирует команды. Для этого он использует метод register инвокера. Далее детальнее разберем этот момент.
     ```Java
     commandInvoker.register("help", new Help(commandReceiver));  // Пример регистрации команды help.
     ```
     + Запускает Scanner при помощи try с ресурсами и передает инвокеру введеную команду с аргументом.
     ```Java
       try (Scanner scanner = new Scanner(System.in)) {  // Собственно try с ресурсами.
            while (scanner.hasNextLine()) {  // Читаем каждую строку в цикле. Метод hasNextLine гарантирует нам, что мы сможем читать даже пустые строки.
                commandInvoker.executeCommand(scanner.nextLine().trim().split(" "));  // Разделяем введенную команду на части(на саму команду и аргумент для нее.)
            }
        }
     ```
2. **Инвокер**:
    + Регистрирует команды(путем добавлении их в HashMap)
    ```Java
    private static final HashMap<String, Command> commandMap = new HashMap<>();

    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);  // Регистрируем ее как строку с названием команды и сам класс команды.
    }
     ```
    + Выполняет введеную команду с аргументом.
    + Возвращает список УЖЕ зарегистрированных команд.  
    

   ***Важно! Данный класс по-факту нет смысла менять, так как у всех он должен получиться одинаковым.***
3. **Ресивер**:
    + Содержит логику команды. Если логика завязана на работе с коллекцией, то делегирует ее менеджеру-коллекции.
4. **Менеджер-коллекции**:
    + Содержит логику команд которые работают непосредственно с коллекцией.
    + Возвращает нашу коллекцию.
5. **Команды**:
    + Любая команда создается на основе [Абстрактного класса](/src/Commands/Command.java). 
    + Любая команда должна хранить информацию о себе, посредством переопределения абстрактного метода writeInfo. В будущем эта информация будет выводиться командой help.
    + Любая команда должна уметь обращаться к методу ресивера, где должен быть создан метод для класса с логикой. Делается это посредством переопределения абстрактного метода execute.
    + В архитектуре конкретные команды хранятся в директории [ConcreteCommands](/src/Commands/ConcreteCommands).
    
    ***Пример класса-команды [add](/src/Commands/ConcreteCommands/Add.java)***
6. **Creater'ы и Reader'ы**
    + Creater'ы и Reader'ы можно найти в соответствующих директориях директории [Utils](/src/Commands/Utils).
    + **Вот сейчас очень внимательно. Ну прям напрягись. Подойди к этой информации с полным пониманием! Эта информация страшнее всяких архивов ЦРУ. Она заключается в том, что... Creater'ы создают, а Reader'ы читают. Я надеюсь, что ты не погиб от этой страшной информации.**
    + Ридеры разделены на две части, на считыватели ENUM'ов и считыватели примитивов и их классов-оберток.
    + В метод ридера "read" передается строка, которая будет выведена в консоль. Например, "Введите возраст: ". Также передается лимит для численных методов и может ли ввод пользователя быть пустым(то есть станет null, для примитивов данного поля нет по понятным причинам. **Если тебе не понятна причина, то как ты сдал первую лабу?)**
    
    ***[Пример reader'а](src/Commands/Utils/Readers/PrimitiveAndReferenceReaders/PrimitiveIntReader.java)***  
    ***Ридеры для примитивов и их оберток, Вам, скорее всего, НЕ придется менять.***
7. Базовые классы
    + Тут ничего сложного. Просто кидаешь в директорию [Базовых классов](/src/BasicClasses), то, что тебе даст генератор. Дополняя это все конструкторами, где надо.

[Содержание :arrow_up:](#Содержание)
    
### Заключение <a name="Заключение"></a>
Подытожим, для того, чтобы создать новую команду тебе надо:
1. Создать класс в директории ConcreteCommands, наследуя абстрактный класс Command.
2. Зарегистрировать его в косольном менеджере("commandName.register"). В эту функцию передается имя команды (по нему будет искаться команда, а также экземляр вышесозданного класса).
3. Создать метод в ресивере для команды, где будет описана логика. **Не забудь вернуться в класс из пункта 1, в функции execute класса-команды ты вызываешь функцию из ресивера.**
4. При работе с коллекцией, делигируй логику в менеджер-коллекции (вызови этот метод в ресивере).

**Из неприятных новостей могу выделить тот факт, что тебе придется все же немного самому покопаться в исходниках, чтобы до конца понять что и как работает**

Если:
1. У тебя остались какие-то вопросы, на которые ты все же не смог ответить в процессе разборки лабы
2. Ты нашел неточность в лабе или описании лабы 
3. Хочешь сказать мне спасибо (зачем это делать?) 
4. Послать меня
5. Сказать, что я говнокодер  
то, можешь написать мне в [ВК](https://vk.com/eriksimohyan)
6. Данный исходный код помог тебе, то не поленись и нажми сверху на звездочку, дабы поднять мой ЧСВ и дать мне понять, что я хоть кому-то помог.

**Спасибо всем за свечки, я сдал физику!**

**Все.**  
[Содержание :arrow_up:](#Содержание)
