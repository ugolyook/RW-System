package com.sveta;

import com.sveta.train.Train;

public class Main {
    public static void main(String[] args) {
        TrainFactory factory = new TrainFactory(18);
        try {
            // 3. Создаём поезд с 5 вагонами
            System.out.println("=== СОЗДАНИЕ ПОЕЗДА ===");
            Train train = factory.createTrain(5);

            // 4. Выводим информацию о поезде
            System.out.println("\n=== ИНФОРМАЦИЯ О ПОЕЗДЕ ===");
            System.out.println(train);

            // 5. Дополнительные проверки
            System.out.println("\n=== ДОПОЛНИТЕЛЬНАЯ ИНФОРМАЦИЯ ===");
            System.out.println("Количество вагонов: " + train.getCarriageCount());
            System.out.println("Общая вместимость: " + train.getTotalPassengerCap() + " мест");

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.err.println("❌ Ошибка: " + e.getMessage());
        }

        // 6. Пример с ошибкой (слишком много вагонов)
        System.out.println("\n=== ПРОВЕРКА ЛИМИТОВ ===");
        testLimit(factory);
    }

    // Метод для проверки ограничений
    private static void testLimit(TrainFactory factory) {
        try {
            System.out.println("Попытка создать поезд с 20 вагонами (максимум 18)...");
            Train bigTrain = factory.createTrain(20);
            System.out.println(bigTrain);
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Ожидаемая ошибка: " + e.getMessage());
        }
    }
}

