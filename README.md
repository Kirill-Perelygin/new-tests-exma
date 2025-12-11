# Автотесты для Wikipedia

Этот проект содержит автоматизированные тесты для веб-сайта Wikipedia и мобильного приложения Wikipedia.

## Что нужно для запуска

### Для веб-тестов:
- Java 11 или новее
- Maven
- Браузер Chrome или Firefox

### Для мобильных тестов:
- Java 11 или новее
- Maven
- Appium
- Android эмулятор или телефон
- Приложение Wikipedia должно быть установлено
- Android SDK (обычно устанавливается вместе с Android Studio)

## Как установить

1. Скачайте проект
2. Установите зависимости:
   ```bash
   mvn clean install
   ```

## Настройка мобильных тестов

### 1. Установка Node.js
Скачать и установить Node.js по официальной ссылке - https://nodejs.org/en/download

### 2. Установка Appium

Через npm:
```bash
npm install -g appium
appium driver install uiautomator2
```

### 3. Установка Anroid Studio
Скачать и установить Android Stdio по официальной ссылке - https://developer.android.com/studio/run/emulator?hl=ru

### 4. Настройка переменных окружения Android

Appium требует переменные `ANDROID_HOME`;

1. Найдите путь к Android SDK:
   - Откройте Android Studio → **File → Settings → Android SDK**
   - Скопируйте путь из поля **"Android SDK Location"**
   - Обычно: `C:\Users\ВашеИмя\AppData\Local\Android\Sdk`

2. Установите переменную окружения:
   - Нажмите `Win + R`, введите `sysdm.cpl`, нажмите Enter
   - Вкладка **"Дополнительно"** → **"Переменные среды"**
   - В разделе **"Системные переменные"** нажмите **"Создать"**
   - **Имя:** `ANDROID_HOME`
   - **Значение:** путь к Android SDK
   - Нажмите **OK**

3. Добавьте в PATH:
   - Найдите `Path` в системных переменных → **"Изменить"**
   - Добавьте: `%ANDROID_HOME%\platform-tools`
   - Нажмите **OK**

4. **ПЕРЕЗАПУСТИТЕ** IntelliJ IDEA и терминал

### 5. Запуск эмулятора и проверка подключения

1. Запустите эмулятор в Android Studio
2. Проверьте подключение:
   ```bash
   adb devices
   ```
   Должно показать что-то вроде:
   ```
   emulator-5554    device
   ```
   Запишите имя устройства (например, `emulator-5554`)

### 6. Установка Wikipedia на эмулятор

Через Google Play Store на эмуляторе или через APK:
```bash
adb install путь_к_файлу/wikipedia.apk
```

### 6. Настройка проекта

Откройте файл `src/test/java/com/example/utils/AppiumDriverManager.java`

Найдите строку 18 и замените `emulator-5554` на имя вашего устройства:
```java
options.setDeviceName("emulator-5554"); // Замените на ваше имя
```

### 7. Запуск Appium Server

```bash
appium
```

Должно появиться:
```
[Appium] Appium REST http interface listener started on 0.0.0.0:4723
```

**Важно:** Оставьте Appium запущенным во время выполнения тестов!

## Как запустить тесты

### Веб-тесты

Через IDE - правый клик на файле `WikipediaWebTests` -> Run 'WikipediaWebTests'

**Важно:** Для веб-тестов необходимо установить браузер Google Chrome!


### Мобильные тесты

Перед запуском убедитесь, что:
- Appium запущен
- Эмулятор/телефон подключен
- Wikipedia установлена и открыта на главной странице

Через IDE - правый клик на файле `WikipediaMobileTests` -> Run 'WikipediaMobileTests'

## Доказательства успешных запусков тестов

### Веб-тесты
![1](https://github.com/Kirill-Perelygin/new-tests-exma/blob/master/src/test/resources/images/web/ChromePhoto.png)
![2](https://github.com/Kirill-Perelygin/new-tests-exma/blob/master/src/test/resources/images/web/WebTestsPassed.png)

### Мобильные тесты
![1](https://github.com/Kirill-Perelygin/new-tests-exma/blob/master/src/test/resources/images/mobile/AndroidStudioPhoto.png)
![2](https://github.com/Kirill-Perelygin/new-tests-exma/blob/master/src/test/resources/images/mobile/MobileTestsPassed.png)
