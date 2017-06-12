import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.*;
import ru.yandex.qatools.allure.model.SeverityLevel;

@Title("Тестирование Яндекс Маркета")
@Description("Тестирование Яндекс Маркета описание")
public class TestMarket extends Assert {
    private WebDriver webDriver;
    private final int expectedElementCount = 12; //ожидаемое количество элементов в списке
    private final String chromeDriverPath = "C:\\Program Files\\Java\\Drivers\\chromedriver.exe"; //путь к chromedriver
    private WebDriverWait wait;


    @Title("Подготовка к тесту")
    @Before
    public void setUp() {
        System.out.println("Подготовка");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        webDriver = new ChromeDriver();
        webDriver.get("https://market.yandex.ru/");
        System.out.println(webDriver.getTitle());
        System.out.println(webDriver.getCurrentUrl());
        wait = new WebDriverWait(webDriver, 30, 500);
    }

    @Title("Завершение теста")
    @After
    public void tearDown() throws java.lang.InterruptedException {
        //webDriver.wait(1000,5000);
        if (webDriver != null)
            webDriver.quit();
    }


    //@Ignore
    @Severity(SeverityLevel.CRITICAL)
    @Title("Тест 1 testNoteName")
    @Description("Тестирование Яндекс Маркета тест 1 testNoteName")
    @Test
    public void testNoteName() {
        System.out.println("Тест 1 testNoteName");

        try {
            //4. Выбрать раздел Компьютеры
            selectSection("Компьютеры");

            //5. Выбрать раздел Ноутбуки
            selectSection("Ноутбуки");

            //6. Зайти в расширенный поиск
            setShowMore();

            //7. Задать параметр поиска до 30000 рублей
            setToSum(30000);

            //8. Выбрать производителя HP и Lenovo
            setBrend("HP");
            setBrend("Lenovo");

            //9. Нажать кнопку Применить
            clickApplay();

            //10. Проверить, что элементов на странице 10
            checkElementCount(expectedElementCount);

            //11. Запомнить первый элемент в списке
            String elementName = saveName(1);

            //12. В поисковую строку ввести запомненное значение
            searcText(elementName);

            //13. Найти и проверить, что наименование товара соответствует запомненному значению
            isSavedName(elementName);

        } catch (Exception e) {
            System.out.println("Елемент не найден");
        }

    }


    @Severity(SeverityLevel.NORMAL)
    @Title("Тест 2 testTabletName")
    @Description("Тестирование Яндекс Маркета тест 2 testTabletName")
    @Test
    public void testTabletName() {
        System.out.println("Test 2 testTabletName");
        //webDriver.get("https://market.yandex.ru/");

        try {
            //4. Выбрать раздел Компьютеры
            selectSection("Компьютеры");

            //5. Выбрать раздел Планшеты
            selectSection("Планшеты");
            //webDriver.findElement(By.cssSelector("div.catalog-menu__item:nth-child(1) > div:nth-child(2) > a:nth-child(1)")).click();

            //6. Зайти в расширенный поиск
            setShowMore();

            //7. Задать параметр поиска от 20000 рублей
            setFromSum(20000);

            //8. Задать параметр поиска до 25000 рублей
            setToSum(25000);

            //9. Выбрать производителей Acer и DELL
            setBrend("Acer");
            setBrend("DELL");

            //10. Нажать кнопку Применить
            clickApplay();

            //11. Проверить, что элементов на странице 10
            checkElementCount(expectedElementCount);

            //12. Запомнить первый элемент в списке
            String elementName = saveName(1);

            //13. В поисковую строку ввести запомненное значение
            searcText(elementName);

            //14. Найти и проверить, что наименование товара соответствует запомненному значению
            isSavedName(elementName);

        } catch (Exception e) {
            System.out.println("Елемент не найден");
        }

    }

    @Step("Выбрать раздел Компьютеры")
    public void selectComp() {
        //Выбрать раздел Компьютеры
        System.out.println("Выбрать раздел Компьютеры");
        webDriver.findElement(By.cssSelector("li.topmenu__item:nth-child(2)")).click();
    }

    @Step("Выбрать раздел {0}")
    public void selectSection(String sectionName) {
        //Выбрать раздел
        System.out.println("Выбрать раздел " + sectionName);
        webDriver.findElement(By.linkText(sectionName)).click();
//            webDriver.findElement(By.cssSelector("div.catalog-menu__item:nth-child(1) > div:nth-child(2) > a:nth-child(2)")).click();
    }

    @Step("Зайти в расширенный поиск")
    public void setShowMore() {
        //Зайти в расширенный поиск
        System.out.println("Зайти в расширенный поиск");
        webDriver.findElement(By.cssSelector(".n-filter-panel-aside__show-more")).click();
    }


    @Step("Задать параметр поиска от {0} рублей")
    public void setFromSum(Integer sum) {
        //Задать параметр поиска от 20000 рублей
        System.out.println("Задать параметр поиска от " + sum + " рублей");
        webDriver.findElement(By.cssSelector("#glf-pricefrom-var")).clear();
        webDriver.findElement(By.cssSelector("#glf-pricefrom-var")).sendKeys(sum.toString());
    }

    @Step("Задать параметр поиска до {0} рублей")
    public void setToSum(Integer sum) {
        //Задать параметр поиска до 30000 рублей
        System.out.println("Задать параметр поиска до " + sum + " рублей");
        webDriver.findElement(By.cssSelector("#glf-priceto-var")).clear();
        webDriver.findElement(By.cssSelector("#glf-priceto-var")).sendKeys(sum.toString());
    }


    @Step("Выбрать производителя {0}")
    public void setBrend(String brendName) throws InterruptedException {
        System.out.println("Выбрать производителя " + brendName);
        try {
            webDriver.findElement(By.className("button_size_xs")).click();
            webDriver.findElement(By.linkText(brendName)).click();
        } catch (Exception e) {
            System.out.println("Производитель " + brendName + " не найден");
        }
        //webDriver.findElement(By.className("checkbox__label")).findElement(By.linkText("Acer")) ;
    }

    @Step("Проверить, что элементов на странице {0}")
    public void checkElementCount(int expectElementCount) {
        System.out.println("Проверить, что элементов на странице " + expectElementCount);
        int acualElementCount = webDriver.findElements(By.className("island")).size();
        //List<WebElement> list = webDriver.findElements(By.className("island"));
        //countElem = list.size();
        System.out.println("Проверить, что элементов на странице " + expectElementCount + ". Найдено элементов: " + acualElementCount);
        assertTrue(expectElementCount == acualElementCount);
    }

    @Step("Нажать кнопку Применить")
    public void clickApplay() throws InterruptedException {
        //Нажать кнопку Применить
        System.out.println("Нажать кнопку Применить");
//        webDriver.findElement(By.cssSelector(".button_action_n-filter-apply")).click();//кнопка Применить
        webDriver.findElement(By.cssSelector("a.button:nth-child(2)")).click();//кнопка Показать подходящие
        //Thread.sleep(5000);
    }

    @Step("Запомнить {0} элемент в списке")
    public String saveName(int elementPosition) {
        System.out.println("Запомнить " + elementPosition + " элемент в списке");
        String elementName = webDriver.findElement(By.cssSelector("body > div.main > div:nth-child(4) > div.layout.layout_type_search.i-bem > div.layout__col.layout__col_search-results_normal.i-bem > div.filter-applied-results.metrika.i-bem.filter-applied-results_js_inited > div.snippet-list.snippet-list_type_vertical.island.metrika.i-bem.snippet-list_js_inited.metrika_js_inited > div:nth-child(" + elementPosition + ") > div.snippet-card__content > div > div:nth-child(1) > div > h3 > a > span")).getText();
        System.out.println("Запомнить " + elementPosition + " элемент в списке: " + elementName);
        return elementName;
    }

    @Step("В поисковую строку ввести запомненное значение: {0}")
    public void searcText(String text) {
        //В поисковую строку ввести запомненное значение
        System.out.println("В поисковую строку ввести запомненное значение: " + text);
        webDriver.findElement(By.id("header-search")).clear();
        webDriver.findElement(By.id("header-search")).sendKeys(text);
        webDriver.findElement(By.cssSelector("button.button2")).click();
    }

    @Step("Найти и проверить, что наименование товара соответствует запомненному значению")
    public boolean isSavedName(String savedName) {
        //Найти и проверить, что наименование товара соответствует запомненному значению
        System.out.println("Найти и проверить, что наименование товара соответствует запомненному значению");

        //wait.until(ExpectedConditions.visibilityOf(webDriver.findElement(By.id(".n-title__text > h1:nth-child(1)"))));

        try {
            webDriver.findElement(By.cssSelector(".n-title__text > h1:nth-child(1)"));
        } catch (Exception e) {
            System.out.println("Товар не найден");
            assert (false);
        }
        assertEquals(savedName, webDriver.findElement(By.cssSelector(".n-title__text > h1:nth-child(1)")).getText());

        System.out.println("Наименование соответствует первому элементу из полученного списка");
        return savedName.equals(webDriver.findElement(By.cssSelector(".n-title__text > h1:nth-child(1)")).getText());
    }


}
