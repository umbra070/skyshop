package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Article> articles;
    private final Map<UUID, Product> products;

    public StorageService() {
        articles = new HashMap<>();
        products = new HashMap<>();
        tempSetArticles();
        tempSetProducts();
    }

    public StorageService(Map<UUID, Article> articles, Map<UUID, Product> products){
        this.articles = articles;
        this.products = products;
    }

    public Map<UUID, Article> getArticles() {
        return articles;
    }

    public Map<UUID, Product> getProducts() {
        return products;
    }


    //Вот этот метод тестируем
    //Возвращает товар по ID
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    //И вот этот метод тестируем
    //Возвращает коллекцию всех имеющихся статей и товаров
    public Set<Searchable> getAllContent() {
        Set<Searchable> allContent = new HashSet<>();
        allContent.addAll(products.values());
        allContent.addAll(articles.values());
        return allContent;
    }

    //Временная затычка в качестве источника данных с товарами
    private void tempSetProducts() {
        SimpleProduct product1 = new SimpleProduct("Samsung Galaxy S22+", 50000, UUID.randomUUID());
        DiscountProduct product2 = new DiscountProduct("Чехол для Samsung Galaxy S22+", 5000, 40, UUID.randomUUID());
        FixPriceProduct product3 = new FixPriceProduct("Беспроводное зарядное устройство Samsung BXH2890", UUID.randomUUID());
        SimpleProduct product4 = new SimpleProduct("Кабель USB Type-A USB Type-C 3.1", 890, UUID.randomUUID());
        SimpleProduct product5 = new SimpleProduct("Набор беспроводной клавиатура + мышь Logitech CPD3918", 7000, UUID.randomUUID());
        SimpleProduct product6 = new SimpleProduct("IPhone 16+", 90000, UUID.randomUUID());

        products.put(product1.getId(), product1);
        products.put(product2.getId(), product2);
        products.put(product3.getId(), product3);
        products.put(product4.getId(), product4);
        products.put(product5.getId(), product5);
        products.put(product6.getId(), product6);
    }

    //Временная затычка в качестве источника данных со статьями
    private void tempSetArticles() {
        Article article1 = new Article("iPhone 16 Plus: кратко о главном", "iPhone 16 Plus получил обновлённый дизайн с алюминиевой рамкой и керамическим стеклом, которое в два раза устойчивее к царапинам, чем у конкурентов.\n" +
                "\n" +
                "Устройство оснащено мощным процессором Apple A18 на 3 нм техпроцессе, обеспечивающим на 30 % более высокую производительность CPU и на 40 % — графического ускорителя по сравнению с предыдущим поколением.\n" +
                "\n" +
                "Среди ключевых нововведений — сенсорная кнопка Camera Control для управления камерой, поддержка пространственной съёмки фото и видео, а также увеличенное время автономной работы (до 27 часов воспроизведения видео).", UUID.randomUUID());
        Article article2 = new Article("Что важно знать о Galaxy S22 +", """
                Samsung Galaxy S22 + сочетает элегантный дизайн с прочным корпусом из стекла Gorilla Glass Victus Plus
                120‑Гц Dynamic AMOLED 2X‑экран диагональю 6,6 дюйма обеспечивает плавное изображение и отличную читаемость даже при ярком солнце (пиковая яркость — до 1750 нит).
                Производительность базируется на процессоре Exynos 2200 с графикой Xclipse 920, 8 ГБ ОЗУ и накопителем UFS 3.1, а тройная камера (50 Мп + 10 Мп с 3× зумом + 12 Мп ультраширик) позволяет снимать детализированные фото и видео в разных сценариях.""", UUID.randomUUID());
        Article article3 = new Article("Samsung BXH2890: три факта о новинке", """
                Samsung BXH2890 — компактное беспроводное зарядное устройство с поддержкой стандарта Qi, обеспечивающее безопасную и эффективную зарядку смартфонов и других совместимых гаджетов.
                
                Устройство выдаёт мощность до 20 Вт, что позволяет быстро восполнять заряд батареи: смартфон с ёмкостью 4000 мА·ч заряжается до 50 % всего за 30 минут.
                
                Стильный минималистичный дизайн, противоскользящее покрытие и встроенная система защиты (от перегрева, перенапряжения и короткого замыкания) делают BXH2890 удобным и надёжным решением.""", UUID.randomUUID());
        Article article4 = new Article("Три причины выбрать Logitech CPD3918", """
                Logitech CPD3918 — удобный комплект из беспроводной клавиатуры и мыши, совместимый с Windows и macOS, который подключается через USB‑ресивер или Bluetooth и работает на расстоянии до 10 м.
                
                Клавиатура с мембранным механизмом и 104 клавишами обеспечивает мягкий, тихий ход и комфортную печать, а мышь с оптическим сенсором на 1600 dpi гарантирует точное управление курсором и плавное скольжение.
                При цене в 7000 рублей набор предлагает хорошую автономность (до 12 месяцев от одной батарейки для клавиатуры и до 6 месяцев для мыши), эргономичный дизайн и надёжную сборку — оптимальное решение для дома и офиса.""", UUID.randomUUID());
        Article article5 = new Article("Быстрый взгляд на чехол для Galaxy S22+", """
                Чехол для Samsung Galaxy S22+ за 5000 рублей обеспечивает надёжную защиту смартфона от царапин, сколов и небольших падений благодаря прочным материалам и продуманной конструкции с усиленными углами.
                
                Модель точно повторяет контуры устройства, сохраняет доступ ко всем портам и кнопкам, а тактильно приятное покрытие предотвращает скольжение в руке и снижает риск случайного выпадения.
                Дополнительно чехол придаёт смартфону стильный внешний вид — доступен в нескольких цветовых решениях, которые подчеркнут индивидуальность владельца без ущерба для функциональности.""", UUID.randomUUID());
        Article article6 = new Article("Кабель Type-A – Type-C 3.1 за 890: стоит ли брать?", """
                Кабель USB Type-A – USB Type-C 3.1 за 890 рублей поддерживает скорость передачи данных до 5 Гбит/с, что позволяет быстро копировать файлы и синхронизировать устройства.
                
                Модель рассчитана на зарядку с током до 3 А и мощностью до 60 Вт — подходит для смартфонов, планшетов и ноутбуков с разъёмом Type-C, обеспечивая эффективное восполнение заряда.
                Прочная оплётка и усиленные разъёмы повышают износостойкость, а длина в 1 м делает кабель удобным для повседневного использования дома и в дороге.""", UUID.randomUUID());

        articles.put(article1.getId(), article1);
        articles.put(article2.getId(), article2);
        articles.put(article3.getId(), article3);
        articles.put(article4.getId(), article4);
        articles.put(article5.getId(), article5);
        articles.put(article6.getId(), article6);
    }
}
